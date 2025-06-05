# Homemade-IoC-Container

A simple Inversion of Control (IoC) container implemented in Java. This container is designed to provide dependency injection using reflection, supporting constructor injection, transitive dependencies, and configuration-based implementation selection.

## Assignment Context

This project is developed for the CBSE lab assignment: *"Homemade IoC Container"*. The goal is to understand the architecture and functionality of Dependency Injection containers like Spring or PicoContainer by building a custom version from scratch.

## Features

* Constructor-based dependency injection
* Transitive dependency resolution (multi-level)
* XML-based configuration for specifying component bindings
* Automatic implementation selection when multiple are available
* Demonstrates usage via two applications:

  * MovieLister
  * NotificationSender

---

## Structure

### 1. IoC Core (src/main/java/ioc/)

* `Container.java`: Core class responsible for parsing configuration, resolving dependencies, and providing component instances.
* `Component.java`, `Inject.java`: Custom annotations for component registration and injection point specification.
* `ConfigParser.java`: Utility to read and parse the XML configuration file.

### 2. Application 1: MovieLister (src/main/java/movieLister/)

Demonstrates a use case involving transitive dependency resolution.

* `MovieListerMain.java`: Entry point
* `MovieLister.java`: Depends on `MovieFinder`
* `MovieFinder.java` / `MovieFinderImpl.java`: Interface and implementation
* `DatabaseAccess.java` / `DatabaseAccessImpl.java`: Used by `MovieFinderImpl`
* `NetworkCommunicator.java` / `NetworkCommunicatorImpl.java`: Used by `DatabaseAccessImpl`

**Dependency Chain Example:**

```
MovieLister → MovieFinder → DatabaseAccess → NetworkCommunicator
```

### 3. Application 2: NotificationSender (src/main/java/notificationSender/)

Demonstrates a second independent component-based application.

* `NotificationSenderMain.java`: Entry point
* `NotificationSender.java`: Core logic class
* `AuthenticationService.java` / `AuthenticationServiceImpl.java`: Handles auth
* `UserRepository.java` / `UserRepositoryImpl.java`: Manages user data
* `PasswordValidator.java`: Validates passwords
* `NetworkService.java` / `NetworkServiceImpl.java`: Used for communication
* `User.java`: Model class

**Dependency Chain Example:**

```
NotificationSender → AuthenticationService → UserRepository + PasswordValidator + NetworkService
```

---

## Configuration File (config.xml)

The XML file binds interfaces to implementations. Example:

```xml
<components>
    <component interface="movieLister.MovieFinder" implementation="movieLister.MovieFinderImpl"/>
    <component interface="movieLister.MovieLister" implementation="movieLister.MovieLister"/>
    <component interface="movieLister.DatabaseAccess" implementation="movieLister.DatabaseAccessImpl"/>
    <component interface="movieLister.NetworkCommunicator" implementation="movieLister.NetworkCommunicatorImpl"/>
</components>
```

The container uses this configuration to inject the correct components recursively.

---

## How to Run

1. Clone the repository:

```bash
git clone https://github.com/Iasmina012/Homemade-IoC-Container.git
cd Homemade-IoC-Container
```

2. Compile the project:

```bash
./gradlew build
```

3. Run an application (e.g., MovieLister):

```bash
./gradlew run --args='movieLister.MovieListerMain'
```

---

## Technologies

* Java 8+
* Gradle
* Reflection API
* XML for configuration
