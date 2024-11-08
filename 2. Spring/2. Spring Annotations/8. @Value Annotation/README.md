# Spring @Value Annotation

- The `@Value` annotation in Spring is used to inject externalized properties into fields, methods and constructor parameters from property files or expression evaluations.
- It provides a way to externalize configuration and make it easier to manage and change application settings.

## Example

### 1. Using @Value with Properties Files:

- `application.properties` file `src/main/resources` has the following content:

  ```
  app.name=MyApp
  app.version=1.0.0
  ```

- You can inject these values into a Spring component using the `@Value` annotation:

  ```java
  import org.springframework.beans.factory.annotation.Value;
  import org.springframework.stereotype.Component;

  @Component
  public class MyService {

      @Value("${app.name}")
      private String appName;

      @Value("${app.version}")
      private String appVersion;

      public void printAppInfo() {
          System.out.println("Application Name: " + appName);
          System.out.println("Application Version: " + appVersion);
      }
  }
  ```

- In this example:
  - `@Value("${app.name}")` injects the value of `app.name` from a properties file into the `appName` field.
  - `@Value("${app.version}")` injects the value of `app.version` into the `appVersion` field.

### 2. Using @Value with Environment Variables:

- You can also use the `@Value` annotation to inject environment variables:
- Example:

  - You have an environment variable `DB_URL` set in your system:

    ```bash
    export DB_URL=jdbc:mysql://localhost:3306/mydb
    ```

  - You can inject this environment variable into a Spring component:

    ```java
    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.stereotype.Component;

    @Component
    public class DatabaseConfig {

        @Value("${DB_URL}")
        private String dbUrl;

        // Getters and setters
    }
    ```

### 3. Using @Value with Spring Expression Language (SpEL) Expressions

```java
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyService {

    @Value("#{T(java.lang.Math).random() * 100.0}")
    private double randomNumber;

    @Value("#{systemProperties['user.home']}")
    private String userHome;
}
```

- In this example:
  - `@Value("#{T(java.lang.Math).random() \* 100.0}")` uses SpEL to inject a random number between 0 and 100.
  - `@Value(#{systemProperties['user.home']})` retrieves the user's home directory.

### Setting Up Additional Properties Files

1. Create Additional Properties Files:

   - Create a new properties file, for example, `custom-config.properties`, in the `src/main/resources` directory.

2. Configure Spring Boot to Load Additional Properties:

   - You can use the `@PropertySource` annotation to load additional properties files. Place this annotation in a configuration class:

     ```java
     import org.springframework.context.annotation.Configuration;
     import org.springframework.context.annotation.PropertySource;

     @Configuration
     @PropertySource("classpath:custom-config.properties")
     public class AppConfig {
     }
     ```

   - Alternatively, you can specify additional properties files in your `application.properties`:

     ```properties
     spring.config.import=classpath:custom-config.properties
     ```

### Use Cases

1. External Configuration: Inject configuration values from properties files or environment variables to decouple configuration from code.
2. Dynamic Values: Use SpEL to inject dynamic values or expressions that need to be evaluated at runtime.
3. Environment-Specific Configuration: Manage different configurations for different environments by using external property files.

### Questions

1. What is the purpose of the @Value annotation in Spring, and how is it used?
2. How can @Value be used to inject values from properties files into a Spring bean?
3. Explain the use of Spring Expression Language (SpEL) with the @Value annotation.
4. Provide an example of using @Value to inject dynamic values into a bean.
5. How does @Value help in managing environment-specific configurations?
