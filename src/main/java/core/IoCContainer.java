package core;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.lang.reflect.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class IoCContainer {

    private final Map<String, Object> components = new HashMap<>();

    //Incarcarea configuratiilor din mai multe fisiere XML
    public void buildConfiguration(String... configFilePaths) {

        try {
            //Itereaza prin fiecare fisier XML din lista de fisiere
            for (String configFilePath : configFilePaths) {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(new File(configFilePath));

                //Procesarea componentelor
                NodeList componentNodes = document.getElementsByTagName("component");

                for (int i = 0; i < componentNodes.getLength(); i++) {
                    //Obtinem fiecare element in lista de noduri
                    Element componentElement = (Element) componentNodes.item(i);
                    //Extragem atributele id si class ale fiecaruia
                    String id = componentElement.getAttribute("id");
                    String className = componentElement.getAttribute("class");
                    //Cream instanta a clasei specificata de atributul class
                    Object instance = createInstance(className, componentElement);
                    //Adaugam instanta intr-o colectie cu id ca si cheie
                    components.put(id, instance);

                    //Debugging output
                    System.out.println("Component added: " + id + " (" + className + ")");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error building IoC container: " + e.getMessage(), e);
        }

    }

    //Metoda care creaza o instanta a unei clase dinamic
    private Object createInstance(String className, Element componentElement) throws Exception {

        Class<?> clazz = Class.forName(className);

        //Cautam constructorul definit in XML
        NodeList constructorNodes = componentElement.getElementsByTagName("constructor");
        Object instance;

        if (constructorNodes.getLength() > 0) {
            instance = createUsingConstructor(clazz, (Element) constructorNodes.item(0));
        } else {
            instance = clazz.getDeclaredConstructor().newInstance();
        }

        //Salvam componenta instantiata in container
        String id = componentElement.getAttribute("id");
        components.put(id, instance);
        return instance;

    }

    private Object createUsingConstructor(Class<?> clazz, Element constructorElement) throws Exception {

        NodeList argNodes = constructorElement.getElementsByTagName("arg");
        Class<?>[] paramTypes = new Class<?>[argNodes.getLength()];
        Object[] paramValues = new Object[argNodes.getLength()];

        //Prelucrarea parametrilor constructorului
        for (int i = 0; i < argNodes.getLength(); i++) {
            Element argElement = (Element) argNodes.item(i);
            String ref = argElement.getAttribute("ref");

            //Cautam dependenta in container
            Object dependency = components.get(ref);

            if (dependency == null) {
                throw new RuntimeException("Dependency " + ref + " not found for class " + clazz.getName());
            }

            //Obtinem tipul parametrului de la constructor
            paramTypes[i] = clazz.getDeclaredConstructors()[0].getParameterTypes()[i];
            paramValues[i] = dependency;

            //Debugging output
            System.out.println("Parameter type for " + clazz.getName() + " constructor: " + paramTypes[i].getName());
        }

        Constructor<?> constructor = clazz.getDeclaredConstructor(paramTypes);
        return constructor.newInstance(paramValues);

    }

    //Metoda pentru a obține componenta din container pe baza id
    public Object getComponent(String id) {

        if (!components.containsKey(id)) {
            throw new RuntimeException("Component not found: " + id);
        }
        return components.get(id);

    }

}