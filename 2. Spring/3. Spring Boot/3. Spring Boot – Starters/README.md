# Spring Boot – Starters

## Theory

Spring Boot starters are a set of convenient dependency descriptors you can include in your application to get various features and libraries up and running quickly. Starters simplify the process of adding dependencies by bundling common libraries and configurations required for specific functionalities.

### Key Points

1. **What Are Starters?**

   - Starters are predefined sets of dependencies provided by Spring Boot that encapsulate common libraries and configuration settings. They are designed to simplify the setup of various functionalities.

2. **Common Starters**:

   1. **`spring-boot-starter-web`**: Includes dependencies for building web applications, such as Spring MVC, Pre-configured webserver (Tomcat), and Jackson for JSON processing.
   2. **`spring-boot-starter-data-jpa`**: Provides support for JPA-based data access, including Hibernate and Spring Data JPA.
   3. **`spring-boot-starter-security`**: Adds Spring Security to your application for authentication and authorization.
   4. **`spring-boot-starter-test`**: Contains testing libraries like JUnit, Hamcrest, and Mockito for writing unit and integration tests.
   5. **`spring-boot-starter-actuator`**: Provides production-ready features like monitoring and management endpoints.
   6. **`spring-boot-starter-data-mongodb`**: Starter for using MongoDB document-oriented database and Spring Data MongoDB

3. **How Starters Work**:

   - Starters are included as dependencies in your `pom.xml` or `build.gradle` file. When added, Maven or Gradle automatically downloads and configures all required libraries and dependencies.
   - Starters include common configurations and setup required for their functionality, reducing the need for manual configuration.

4. **Custom Starters**:
   - You can create custom starters by packaging your own dependencies and configurations into a JAR file. This allows you to encapsulate and reuse common configurations and libraries across multiple projects.

### Example

#### `pom.xml` with Starters

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/POM/4.0.0">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>my-spring-boot-app</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.0.0</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>

    <dependencies>
        <!-- Spring Boot Starter Web -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!-- Spring Boot Starter Data JPA -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>

        <!-- Spring Boot Starter Security -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>

        <!-- Spring Boot Starter Test -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

### Use Cases

1. Web Development: Use spring-boot-starter-web to quickly set up a web application with all necessary dependencies.
2. Data Access: Employ spring-boot-starter-data-jpa for JPA-based database interactions.
3. Security: Add spring-boot-starter-security to integrate authentication and authorization features.
4. Testing: Utilize spring-boot-starter-test to include testing libraries for writing and running tests.

### Summary

Spring Boot starters streamline the process of setting up a Spring Boot application by providing ready-made dependency sets and configurations. By including starters in your project, you can quickly integrate common functionalities and simplify dependency management.

### Questions

1. What are Spring Boot starters and how do they simplify dependency management?
2. Name and describe at least three common Spring Boot starters.
3. How do you include a Spring Boot starter in a Maven project?
4. What is the purpose of creating custom starters, and how can they be used in multiple projects?
5. How do starters manage common configurations and dependencies?
