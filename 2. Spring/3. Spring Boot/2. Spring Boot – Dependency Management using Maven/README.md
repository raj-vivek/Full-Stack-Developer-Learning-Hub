# Spring Boot – Dependency Management using Maven

## Theory

- In Spring Boot, Maven is used for dependency management, which simplifies the process of adding and managing libraries and frameworks required for your application.
- Maven uses a `pom.xml` file to define project dependencies, plugins, and configurations.

### Key Points

1. **Maven Basics**:

   - Maven is a build automation tool that uses an XML file (`pom.xml`) to manage project dependencies, build configuration, and plugins.
   - Dependencies are libraries or modules that your project requires. Maven handles downloading and including these dependencies in your project.

2. **Spring Boot Starter Dependencies**:

   - Spring Boot provides "starter" dependencies to simplify the inclusion of commonly used libraries and frameworks. Examples include `spring-boot-starter-web`, `spring-boot-starter-data-jpa`, and `spring-boot-starter-security`.
   - Starters are a convenient way to include a group of related dependencies with a single entry.

3. **Dependency Management**:

   - Define dependencies in the `pom.xml` file under the `<dependencies>` section. Maven handles downloading and updating these dependencies.
   - You can specify dependency versions, scopes, and exclusions to manage conflicts and customize the build process.

4. **Dependency Scope**:

   - **`compile`**: The default scope. Dependencies are available at compile, runtime, and test phases.
   - **`provided`**: Dependencies are required at compile time but provided by the runtime environment (e.g., servlet API).
   - **`runtime`**: Dependencies are needed at runtime but not at compile time.
   - **`test`**: Dependencies are used for testing only.

5. **Version Management**:
   - Define dependency versions directly in the `pom.xml` or use a parent POM for version management across multiple projects.
   - Spring Boot provides dependency management for common versions used in its starters, simplifying version control.

### Example

#### `pom.xml` for a Spring Boot Application

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

        <!-- MySQL Connector -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!-- JUnit for Testing -->
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

### Maven Project Directory Structure

![Maven Project Directory Structure]()

#### Directory Description

- **.mvn/**: Contains Maven Wrapper files to allow users to execute Maven commands without needing a local Maven installation.
  - `wrapper/`: Directory with Maven Wrapper JAR and Downloader class.
- **src/**: Source code directory.
  - **main/**: Contains the main application source code.
    - **java/**: Java source files.
    - **resources/**: Resources like configuration files.
    - **webapp/**: Web application files (for web applications).
  - **test/**: Contains test source code.
    - **java/**: Java test files.
    - **resources/**: Resources needed for tests.
- **target/**: Directory where Maven builds the project. Contains compiled classes, packaged JARs, and other build artifacts.

- **.gitignore**: Specifies files and directories that Git should ignore.

- **pom.xml**: Maven Project Object Model file. It contains configuration information for the project, including dependencies, build settings, and plugins.

- **README.md**: This file. It provides an overview of the project and its structure.

### Maven Commands

Maven is a build automation tool used primarily for Java projects. Below are some essential Maven commands you might use in your development workflow.

#### Basic Commands

1. **`mvn clean`**

   - Cleans the project by removing the `target` directory, which contains compiled files and build artifacts.

2. **`mvn compile`**

   - Compiles the source code of the project.

3. **`mvn test`**

   - Runs the tests for the project using the test framework specified in the `pom.xml`.

4. **`mvn package`**

   - Packages the compiled code into a JAR/WAR file and places it in the `target` directory.

5. **`mvn install`**

   - Installs the package into the local Maven repository, making it available for other projects on the same machine.

6. **`mvn deploy`**

   - Uploads the packaged code to a remote repository for sharing with other developers and projects.

#### Build Lifecycle Commands

1. **`mvn validate`**

   - Validates the project structure and configuration.

2. **`mvn verify`**

   - Runs any checks to verify the quality of the package.

3. **`mvn site`**

   - Generates a site documentation for the project, which includes reports and other project-related information.

4. **`mvn clean install`**

   - Combines `clean` and `install`. Cleans the project and then installs the package to the local repository.

### Spring Boot Command

1. **`mvn springboot:run`**

#### Dependency Management Commands

1. **`mvn dependency:tree`**

   - Displays the project's dependency tree, showing how dependencies are related.

2. **`mvn dependency:resolve`**

   - Resolves and downloads the project's dependencies.

3. ## **`mvn versions:use-latest-versions`**

   - Updates all dependency versions to latest

#### Plugin and Goal Commands

1. **`mvn help:effective-pom`**

   - Displays the effective `pom.xml` file after applying all inheritance and profile settings.

2. **`mvn help:describe -Dplugin=plugin-name`**

   - Provides information about a specific Maven plugin and its goals.

#### Profiles and Configuration

1. **`mvn -Pprofile-name`**

   - Activates a specific Maven profile defined in the `pom.xml`.

2. **`mvn -Dmaven.test.skip=true`**

   - Skips tests during the build process.

### mvnw and mvnw.cmd

- These are Maven Wrapper files to run maven commands without having Maven (mvn) installed in your environment.
- Commands:
  1. Windows: `./mvnw clean compile test`
  2. Linux: `mvnw.sh clean compile test`
  3. Pre-installed: `mvn clean compile test`

### Use Cases

1. Rapid Development: Simplify the process of including libraries and managing dependencies for a Spring Boot application.
2. Version Management: Use Spring Boot's parent POM to handle dependency versions and updates automatically.
3. Build Automation: Use Maven to automate the build, test, and deployment processes for Spring Boot applications.

### Summary

Maven is a powerful tool for managing dependencies in Spring Boot applications. By using the pom.xml file, you can define project dependencies, manage versions, and configure the build process. Spring Boot starters and dependency management features make it easier to build and maintain applications.

### Questions

1. How does Maven handle dependency management in a Spring Boot application?
2. What are Spring Boot starter dependencies, and how do they simplify development?
3. Explain the different dependency scopes available in Maven.
4. How can you manage dependency versions using Maven?
5. Describe the role of the spring-boot-maven-plugin in the build process.
