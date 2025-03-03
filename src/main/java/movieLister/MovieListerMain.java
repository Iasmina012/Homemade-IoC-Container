package movieLister;

import core.IoCContainer;

import java.util.Objects;

public class MovieListerMain {

    public static void main(String[] args) {

        IoCContainer container = new IoCContainer();

        //container.buildConfiguration("components_movieLister.xml");
        container.buildConfiguration(
                Objects.requireNonNull(IoCContainer.class.getClassLoader().getResource("components_movieLister.xml")).getPath()
        );

        MovieLister movieLister = (MovieLister) container.getComponent("movieLister");
        movieLister.listMovies();

    }

}