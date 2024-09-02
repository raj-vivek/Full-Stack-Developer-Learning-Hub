# Bean LifeCycle and Bean Scopes

## Bean LifeCycle

- The Bean Life Cycle refers to the various stages through which a Spring bean passes from its creation to its destruction.
- Understanding the lifecycle helps in managing resources and performing initialization and cleanup tasks effectively.
- The lifecycle consists of several key phases, which can be customized using various interfaces and annotations provided by Spring.

### Process

1. **Spring Container Initialization**:

   - When a Spring application starts, the Spring container is initialized first. This involves setting up the application context and loading bean definitions.

2. **Bean Instantiation and Dependency Injection**:

   - After initializing the container, it creates instances of beans as needed (e.g., when they are requested or upon application startup, depending on the scope and lifecycle of the beans).
   - Dependency Injection is handled at this stage, where dependencies are injected into the beans using constructor injection, setter injection, or field injection.

3. **Bean Usage**:

   - Once initialized, the bean is ready for use by other components of the application.

4. **Bean Destruction**:
   - Beans are destroyed when the Spring container is closed or the application context is shut down. The container manages the complete lifecycle of the beans, including their destruction.

### Custom Initialization and Destruction Code:

- To execute custom code during the bean lifecycle, you can use -

1. **XML Configuration**:

   - Configure the spring XML file spring.xml and register `initMethod()` and `destroyMethod()`
   - Example -

     ```xml
     <bean id="myBean" class="com.example.MyBean" init-method="initMethod" destroy-method="destroyMethod" />
     ```

     ```java
     package beans;
     public class MyBean {

         // This method executes automatically as the bean is instantiated
         public void initMethod() throws Exception
         {
            System.out.println("Bean HelloWorld has been instantiated and I'm the init() method");
         }

         // This method executes when the spring container is closed
         public void destroyMethod() throws Exception
         {
            System.out.println("Container has been closed and I'm the destroy() method");
         }
     }
     ```

     ```java
     public static void main(String[] args) throws Exception
     {

        // Loading the Spring XML configuration file into the spring container and it will create the instance of the bean as it loads into container
        ConfigurableApplicationContext cap = new ClassPathXmlApplicationContext("resources/spring.xml");

        // It will close the spring container and as a result invokes the destroy() method
        cap.close();
     }
     ```

2. **Annotation Configuration**:

   - Methods annotated with `@PostConstruct` and `@PreDestroy`

     1. `@PostConstruct`: This annotation is used to mark a method that should be executed after the bean's properties have been set and before the bean is available for use. It's used for custom initialization logic.

     2. `@PreDestroy`: This annotation is used to mark a method that should be executed before the bean is destroyed, allowing for any cleanup code.

   - Example:

     ```java
     package beans;

     import javax.annotation.PostConstruct;
     import javax.annotation.PreDestroy;

     // HelloWorld class
     public class HelloWorld {

         // Annotate this method to execute it automatically as the bean is instantiated
         @PostConstruct
         public void init() throws Exception
         {
             System.out.println("Bean HelloWorld has been instantiated and I'm the init() method");
         }

         // Annotate this method to execute it when Spring container is closed
         @PreDestroy
         public void destroy() throws Exception
         {
             System.out.println("Container has been closed and I'm the destroy() method");
         }
     }
     ```

3. **Programmatic Configuration**:

   - Implement `InitializingBean` and `DisposableBean` interfaces:

     1. `InitializingBean` provides `afterPropertiesSet()` for custom initialization.
     2. `DisposableBean` provides `destroy()` for custon destruction.

   - These methods can be overridden to include custom initialization and destruction logic.

   - Example:

     ```java
     package beans;

     import org.springframework.beans.factory.DisposableBean;
     import org.springframework.beans.factory.InitializingBean;

     // HelloWorld class which implements the interfaces
     public class HelloWorld implements InitializingBean,
     DisposableBean {
         @Override
         // It is the init() method of our bean and it gets invoked on bean instantiation
         public void afterPropertiesSet() throws Exception {
             System.out.println("Bean HelloWorld has been instantiated and I'm the init() method");
         }

         @Override
         // This method is invoked just after the container is closed
         public void destroy() throws Exception
         {
             System.out.println("Container has been closed and I'm the destroy() method");
         }
     }
     ```

### Use Cases of Bean LifeCycle

1.  Resource Management: Initialize resources like database connections or file handlers.
    Application Startup: Set up required configurations or data at the start of the application.
2.  Resource Cleanup: Release resources or perform cleanup tasks before the application shuts down.

## Bean Scopes:

The lifecycle of a bean also depends on its scope. It defines -

- When the object of Bean will be instantiated
- How long does that object live
- How many objects will be created for that bean throughout

### 5 Types of Bean Scopes:

1. **Singleton** (Default):

   - Only 1 single instance is created and managed for a single bean definition per Spring IoC container
   - The same object will be shared for each request made for that bean.

2. **Prototype**:

   - A new instance will be created for a single bean definition every time a request is made for that bean.

For Web-aware Spring ApplicationContext -

3. **Request**:

   - One instance per HTTP request.
   - A new instance will be created for a single bean definition every time an HTTP request is made for that bean.
   - Only valid in the context of a web-aware Spring ApplicationContext.

4. **Session**:

   - One instance per HTTP session.
   - Scopes a single bean definition to the lifecycle of an HTTP Session.
   - Only valid in the context of a web-aware Spring ApplicationContext.

5. **Global-Session**:

   - One instance per global HTTP session.
   - Scopes a single bean definition to the lifecycle of a global HTTP Session.
   - Only valid in the context of a web-aware Spring ApplicationContext.

### Ways to define bean scopes:

1. XML Configuration:

   - Bean scopes are defined within the <bean> element using the scope attribute
   - Example:
     ```xml
     <bean id="myPrototypeBean" class="com.example.MyPrototypeBean" scope="prototype" />
     ```

2. Annotation and Programatic Configuration:
   - Using `@Scope`
   - Scope keywords: `singleton`, `prototype`, `request`, `session`, `globalSession`
   - Example:
     1. Annotation Configuration
        ```java
         @Component
         @Scope("prototype")
         public class MyPrototypeBean {
             // Bean definition
         }
        ```
     2. Programatic Configuration
        ```java
        @Configuration
        public class AppConfig {
            @Bean
            @Scope("prototype")
            public MyPrototypeBean myPrototypeBean() {
                return new MyPrototypeBean();
            }
        }
        ```

### Custom Bean Scope

While Spring provides several built-in scopes such as singleton, prototype, request, session, and globalSession, you may occasionally need to define custom scopes to suit specific requirements.

#### Creating a Custom Scope:

- To define a custom bean scope, you need to implement the `Scope` interface and register it with the Spring container.
- Example:

  ```java
  import org.springframework.beans.factory.ObjectFactory;
  import org.springframework.beans.factory.config.Scope;

  import java.util.Map;
  import java.util.concurrent.ConcurrentHashMap;

  public class CustomScope implements Scope {
      private final Map<String, Object> beans = new ConcurrentHashMap<>();

      @Override
      public Object get(String name, ObjectFactory<?> objectFactory) {
          return beans.computeIfAbsent(name, key -> objectFactory.getObject());
      }

      @Override
      public Object remove(String name) {
          return beans.remove(name);
      }

      @Override
      public void registerDestructionCallback(String name, Runnable callback) {
          // Custom destruction logic if needed
      }

      @Override
      public Object resolveContextualObject(String key) {
          return null;
      }

      @Override
      public String getConversationId() {
          return "customScope";
      }
  }
  ```

3. **Registering the Custom Scope**:

   - Register the custom scope with the Spring container in a configuration class.
   - Example:

     ```java
     import org.springframework.context.annotation.Bean;
     import org.springframework.context.annotation.Configuration;
     import org.springframework.context.support.SimpleThreadScope;

     @Configuration
     public class AppConfig {
         @Bean
         public CustomScope customScope() {
             return new CustomScope();
         }

         @Bean
         public CustomScopeConfigurer customScopeConfigurer() {
             CustomScopeConfigurer configurer = new CustomScopeConfigurer();
             configurer.setScopes(Map.of("customScope", customScope()));
             return configurer;
         }
     }
     ```

4. **Using the Custom Scope**:

   - Annotate beans with `@Scope` to use the custom scope.
   - Example:

     ```java
     import org.springframework.context.annotation.Scope;
     import org.springframework.stereotype.Component;

     @Component
     @Scope("customScope")
     public class MyBean {
         // Bean implementation
     }
     ```

### Use Cases of Bean Scopes

1. Singleton: Use for beans that should have only one instance throughout the application, such as caching configuration services.
2. Prototype: Use for beans where each request or operation needs a new instance, like user sessions.
3. Request: Use for beans that need to be created and destroyed per HTTP request, such as logging requests.
4. Session: Use for beans that should be tied to a user’s HTTP session, like user preferences.
5. Global Session: Use for beans in portlet-based applications where state needs to be shared across portlets and requests in the same global session.

### Use Cases of Custom Bean Scope

- **Custom Lifecycle Management**: When built-in scopes do not fit the application's requirements.
- **Complex State Management**: Managing bean states that require custom logic beyond standard scopes.
- **Multi-Tenant Applications**: Implementing scopes to manage beans differently for each tenant.

### Summary

- The Bean Life Cycle in Spring involves the creation, initialization, usage, and destruction of beans. It can be customized through initialization and destruction methods, and is influenced by the bean's scope. Understanding the lifecycle helps manage resources effectively and perform necessary setup and teardown operations.

- Bean scopes in Spring define the lifecycle and visibility of beans, with options for singleton (one instance per application), prototype (new instance per request), request (new instance per HTTP request), session (new instance per HTTP session), and global session (new instance per global session in portlet applications).

- Custom bean scopes in Spring allow for advanced lifecycle and visibility management of beans beyond the built-in scopes. By implementing the `Scope` interface and registering it with the Spring container, you can define and use scopes tailored to specific application needs.

### Questions

1. What are the main phases of the Spring bean life cycle?
2. How can you customize the initialization and destruction phases of a bean in Spring?
3. What are the differences between singleton and prototype bean scopes in terms of lifecycle?
4. Provide examples of using @PostConstruct and @PreDestroy annotations in Spring.
5. How can you define custom initialization and destruction methods in XML configuration?
6. What are the built-in bean scopes in Spring, and how do they differ from custom scopes?
7. How do you create and register a custom bean scope in Spring?
8. Provide an example of a custom bean scope implementation.
9. How can you use a custom scope in bean definitions?
10. What are some use cases for creating custom bean scopes in Spring?
