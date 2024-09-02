# Spring Boot – Application Properties

## Theory

In Spring Boot, application properties are used to configure various aspects of your application, such as server settings, database connections, and custom configurations. These properties are typically defined in the `application.properties` or `application.yml` files located in the `src/main/resources` directory.

### Key Points

1. **Application Properties File**:

   - **`application.properties`**: A simple properties file where key-value pairs define configuration settings.
   - **`application.yml`**: An alternative to `application.properties`, using YAML syntax for hierarchical data representation.

2. **Property Sources**:

   - Spring Boot supports various property sources, including environment variables, command-line arguments, and property files. The property resolution follows a specific order of precedence.

3. **Common Properties**:

   - **Server Configuration**: Configure settings like the server port, context path, and SSL.
   - **Database Configuration**: Define database URL, username, password, and other related settings.
   - **Logging Configuration**: Customize logging levels and patterns.
   - **Custom Properties**: Define and use custom properties for your application.

4. **Profiles**:

   - Spring Boot supports profiles to define different configurations for various environments (e.g., `dev`, `test`, `prod`). Profiles are specified using `application-{profile}.properties` or `application-{profile}.yml`.

5. **Configuration Binding**:
   - Spring Boot allows binding properties to configuration classes using `@ConfigurationProperties`. This provides type-safe access to configuration values.

### Order of precedence for property resolution in Spring Boot:

1. Command-Line Arguments: Properties provided as command-line arguments take the highest precedence. They are specified using the -- prefix (e.g., --server.port=8081).

2. Java System Properties: Properties set via the -D flag on the command line (e.g., -Dserver.port=8081) come next in precedence.

3. OS Environment Variables: Environment variables set at the operating system level are considered next. These can be accessed in Spring Boot using the SPRING\_ prefix (e.g., SPRING_SERVER_PORT=8081).

4. Application Properties Files: Configuration in properties files such as application.properties or application.yml is next. If there are multiple property files, Spring Boot loads them in the following order:

   - application.properties (or application.yml) in the src/main/resources directory.
   - Profile-specific files, such as application-dev.properties or application-prod.yml, if profiles are active.

5. Default Values: Finally, default values defined within the code or in Spring Boot’s defaults are used if none of the above configurations provide a value.

#### Example of Property Resolution:

Suppose you have the following configurations:

- Command-line argument: --server.port=8081
- Java system property: -Dserver.port=8082
- Environment variable: SPRING_SERVER_PORT=8083
- application.properties file: server.port=8084

In this case, Spring Boot will use 8081 as the server port because command-line arguments have the highest precedence.

#### How It Works:

1. Command-Line Arguments: Highest priority, always overrides other configurations.
2. Java System Properties: Overrides environment variables and properties files.
3. OS Environment Variables: Override properties in files but are overridden by system properties.
4. Application Properties Files: Properties defined in files are overridden by higher-priority sources.
5. Default Values: Used if none of the other sources provide a value.

### Example

#### `application.properties`

```properties
# Server configuration
server.port=8080
server.servlet.context-path=/myapp

# Spring Application Properties
spring.application.name = userservice

# Database configuration
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root
spring.datasource.password=secret
spring.datasource.driver-class-name =com.mysql.jdbc.Driver

# H2 Database configuration
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:dcbapp
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

#MongoDB Database
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=BookStore

# Connecting with the Eureka Server
# Eureka Server is an application that holds information about all client-service applications. Every Microservice will register into the Eureka server and the Eureka server knows all the client applications running on each port and IP address. Eureka Server is also known as Discovery Server. You can write the properties like this
eureka.client.register-with-eureka=true
eureka.client.fetch-registry=true
eureka.client.service-url.defaultZone=http://localhost:9096/eureka/
eureka.instance.hostname=localhost

# Logging configuration
logging.level.org.springframework=INFO
logging.file.name=app.log

# Custom property
myapp.custom.property=value
```

#### application.yml

```yaml
server:
  port: 8080
  servlet:
    context-path: /myapp

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/mydb
    username: root
    password: secret

logging:
  level:
    org.springframework: INFO
  file:
    name: app.log

myapp:
  custom:
    property: value
```

### Configuration Binding Example

- Spring Boot allows binding properties to configuration classes using `@ConfigurationProperties`. This provides type-safe access to configuration values.

  ```java
  import org.springframework.boot.context.properties.ConfigurationProperties;
  import org.springframework.stereotype.Component;

  @Component
  @ConfigurationProperties(prefix = "myapp.custom")
  public class CustomProperties {

      private String property;

      // Getters and setters
      public String getProperty() {
          return property;
      }

      public void setProperty(String property) {
          this.property = property;
      }
  }
  ```

### Use Cases

1. Environment Configuration: Manage different settings for development, testing, and production environments.
2. Database Settings: Configure database connections and credentials.
3. Logging: Adjust logging levels and output destinations.
4. Custom Configuration: Define and access custom application-specific settings.

### Summary

- Spring Boot application properties provide a flexible way to configure various aspects of your application.
- By using application.properties or application.yml files, you can manage settings for the server, database, logging, and custom configurations.
- Profiles and configuration binding further enhance the flexibility and organization of configuration management.

### Questions

1. What are the primary ways to define application properties in Spring Boot?
2. How can profiles be used to manage different configurations for various environments?
3. Explain how to use application.properties and application.yml for configuring a Spring Boot application.
4. How does Spring Boot's configuration binding work with @ConfigurationProperties?
5. What is the order of precedence for property sources in Spring Boot?
