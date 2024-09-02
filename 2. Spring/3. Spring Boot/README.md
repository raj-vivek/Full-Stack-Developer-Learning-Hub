# Spring Boot

## Theory

- Spring Boot is an extension of the Spring framework that simplifies the setup and development of new Spring applications.
- It provides a set of tools and conventions to streamline the process of building and deploying Spring applications.
- Spring Boot helps developers by providing a default configuration, out-of-the-box features, and a simplified development experience.

### Key Points

1. **Auto-Configuration**:

   - Spring Boot automatically configures your application based on the dependencies present on the classpath.
   - It uses sensible defaults to configure beans and services, minimizing the need for manual configuration.

2. **Standalone Applications**:

   - Spring Boot allows you to create standalone applications with embedded servers like Tomcat, Jetty, or Undertow. This eliminates the need for deploying WAR files to an external server.

3. **Production-Ready**:

   - Spring Boot applications come with built-in production features.
   - The `Actuator` module provides built-in endpoints for monitoring and managing Spring Boot applications. It includes features for health checks, metrics, and application environment information.

4. **Spring Boot Starter Projects**:

   - Starter projects are a set of dependency descriptors that simplify adding common dependencies to your project.
   - For example, `spring-boot-starter-web` includes dependencies for building web applications.

5. **Spring Boot Initializr**:

   - A web-based tool for generating Spring Boot projects with pre-configured dependencies.
   - It helps in quickly setting up a new project with the necessary components and configurations.

6. **Microservice-based Architecture**:

   - Microservice, as the name suggests is the name given to a module/service which focuses on a single type of feature, exposing an API(application peripheral interface).

### Key Components

1. **Spring Boot Application**:

   - The central component is the Spring Boot application itself, typically annotated with `@SpringBootApplication`.
   - This annotation enables component scanning, auto-configuration, and configuration properties.

2. **Embedded Servers**:

   - Spring Boot applications come with embedded web servers like Tomcat, Jetty, or Undertow. This allows you to run your application as a standalone executable JAR or WAR file without needing an external server.

3. **Application Properties**:

   - Configuration properties are managed through application property files (`application.properties` or `application.yml`). These files allow you to externalize configuration and customize application behavior.

4. **Spring Boot CLI**:
   - The Spring Boot Command-Line Interface (CLI) allows you to quickly build and run Spring Boot applications from the command line using Groovy or Java.

### Notes

1. Classpath:

   - The classpath is a parameter used by the Java Virtual Machine (JVM) and Java compiler to locate classes and resources.
   - It tells the JVM where to find the application's compiled bytecode, configuration files, libraries, and other resources.
   - The classpath can include directories and JAR files, and it is essential for the JVM to load classes and resources at runtime.

2. Embedded Servers (JAR files)

   - Embedded servers are web servers that are included within your application rather than being provided by an external server environment.
   - In a Spring Boot application, you typically use embedded servers to simplify deployment and reduce configuration overhead.
   - You don't need to deploy your application (WAR files) to an external server.
   - You can package your application as a standalone JAR file with the embedded server, which simplifies the deployment process.
   - The application contains everything needed to run, including the web server, configuration, and dependencies, which makes it easy to deploy and run in various environments.
   - Apache Tomcat (default): A widely-used Java HTTP embedded server and servlet container.
   - `java -jar myapp.jar`

### JAR vs WAR

#### JAR (Java ARchive)

- **Purpose**: Primarily used for packaging Java classes, associated metadata, and resources into a single file. JAR files are often used for libraries and applications.
- **Structure**: Contains Java bytecode files (.class files), metadata, and resources. It can also include a META-INF directory with a MANIFEST.MF file that can define main classes and other attributes.
- **Usage**: JAR files can be executed if they contain a Main-Class attribute in the manifest file. They are used for distributing libraries and standalone applications.
- **Deployment**: Typically used in desktop or standalone server applications, and not meant to be deployed on a web server.

#### WAR (Web Application Archive)

- **Purpose**: Specifically designed for packaging web applications. It contains everything needed to deploy a web application to a servlet container like Apache Tomcat or Jetty.
- **Structure**: Includes a specific directory structure:
- **WEB-INF/**: Contains configuration files like web.xml, and directories for classes (classes/) and libraries (lib/).
- **META-INF/**: Similar to JAR, contains metadata.
- **Static resources** like HTML, CSS, JavaScript files, and other resources are placed directly in the root or under specific folders.
- **Usage**: WAR files are used to deploy web applications on a web server. They are not typically executed directly; instead, they are deployed to a servlet container or application server.
- **Deployment**: Used in web server environments where it can be deployed to a servlet container or application server.

### Spring Boot Architacture Layers

<img src='./Spring Boot flow architecture.jpg' width=400>

The spring boot consists of the following four layers:

1. Presentation Layer – Authentication & Json Translation

   - `@Controller` and `@RestController`
   - Top layer of the spring boot architecture
   - It handles the HTTP requests and performs authentication.
   - It is responsible for converting the JSON field’s parameter to Java Objects and vice-versa.
   - Once it performs the authentication of the request it passes it to the next layer. i.e., the business layer.

2. Business Layer – Business Logic, Validation & Authorization

   - `@Service`
   - The business layer contains all the business logic.
   - It consists of services classes.
   - It is responsible for validation and authorization.

3. Persistence Layer – Storage Logic

   - `@Repository`
   - The persistence layer contains all the database storage logic.
   - It is responsible for converting business objects to the database row and vice-versa.

4. Database Layer

   - Actual Database

Explanation:

1. The Client makes an HTTP request(GET, PUT, POST, etc.)
2. The HTTP request is intercepted by the DispatcherServlet. It consults the handler mapping to find the appropriate controller.
3. The HTTP request is forwarded to the Controller. The controller maps the request. It processes the request and calls the server logic.
4. The business logic is performed in the Service layer. The spring boot performs all the logic over the data of the database which is mapped to the spring boot `Model` class through Java Persistence Library(JPA).
5. The JSP page or API response is returned as Response from the controller.

### Example

#### Creating a Simple Spring Boot Application

1. **Setup the Project**

   Use Spring Boot Initializr to generate a new project or create a `pom.xml` file with Spring Boot dependencies.

   ```xml
   <dependencies>
       <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-web</artifactId>
       </dependency>
   </dependencies>
   ```

2. **Application Class**

- Create a main application class annotated with @SpringBootApplication.

  ```java
  import org.springframework.boot.SpringApplication;
  import org.springframework.boot.autoconfigure.SpringBootApplication;

  @SpringBootApplication
  public class MySpringBootApplication {

      public static void main(String[] args) {
          SpringApplication.run(MySpringBootApplication.class, args);
      }

  }
  ```

3. **Controller Class**

- Create a REST controller to handle HTTP requests.

  ```java
  import org.springframework.web.bind.annotation.GetMapping;
  import org.springframework.web.bind.annotation.RestController;

  @RestController
  public class MyController {

      @GetMapping("/hello")
      public String hello() {
          return "Hello, Spring Boot!";
      }

  }
  ```

### Use Cases

1. **Rapid Development**: Spring Boot simplifies the development process by providing default configurations and reducing boilerplate code.
2. **Microservices**: Ideal for building microservices due to its support for embedded servers and easy integration with various Spring modules.
3. **Standalone Applications**: Perfect for creating standalone applications with embedded servers, avoiding the need for complex deployment processes.
4. **Production Readiness**: Useful for applications that need production-ready features like health checks, metrics, and easy configuration management.

### Summary

- Spring Boot streamlines the development process by providing a set of conventions and defaults, reducing the need for extensive configuration.
- It supports building standalone, production-ready applications with embedded servers and includes features for managing and monitoring applications.

### Questions

1. What are the main features of Spring Boot that simplify application development?
2. How does Spring Boot's auto-configuration work, and what are its benefits?
3. Describe how to create a basic Spring Boot application from scratch.
4. What are Spring Boot starters, and how do they simplify dependency management?
5. How can Spring Boot be used to build and deploy microservices?
