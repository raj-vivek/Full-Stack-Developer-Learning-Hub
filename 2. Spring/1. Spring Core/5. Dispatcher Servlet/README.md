# Dispatcher Servlet

- The DispatcherServlet is a core component of the Spring Framework, specifically within the Spring MVC (Model-View-Controller) module.
- All HTTP requests are strictly routed through the DispatcherServlet.
- This servlet acts as the front controller, handling and delegating incoming requests to the appropriate controllers and handlers.

### DispatcherServlet Overview

1. **Central Dispatcher**:

   - The DispatcherServlet is the front controller in the Spring MVC framework.
   - It acts as a central dispatcher for handling HTTP requests, routing them to appropriate handlers/controllers, and returning the responses.

2. **Request Handling**:

   - When a client makes an HTTP request, the DispatcherServlet intercepts it.
   - It consults the handler mapping to find the appropriate controller.
   - It then delegates the request to the controller and processes the response.

3. **Processing Workflow**:
   - **Request Mapping**: DispatcherServlet uses handler mappings to route requests to the correct controller method.
   - **Controller Invocation**: The chosen controller processes the request and generates a `ModelAndView` object or an JSON API response.
   - **View Resolution**: The DispatcherServlet uses view resolvers to determine the view (usually a JSP, Thymeleaf template, or other view technology) that should be used to render the response.
   - **Response Rendering**: The view is rendered, and the final response is sent back to the client.

### Key Components Integrated with DispatcherServlet

1. **Handler Mappings**: Define which controller should handle a given request based on URL patterns, annotations, or other criteria.

2. **Controllers**: These are Java classes annotated with `@Controller` or `@RestController`, and they contain request-handling methods.

3. **View Resolvers**: Resolve the logical view names returned by controllers into actual view templates (e.g., JSP, Thymeleaf).

4. **Model**: Carries data from the controller to the view, typically using Model, ModelMap, or ModelAndView objects.

5. **Interceptors**: Custom pre-processing and post-processing of requests and responses.

### Configuration

- The DispatcherServlet is configured in the web.xml file (in traditional Spring MVC applications) or through Java configuration in newer applications:

#### Java Configuration (Using Spring Boot or Spring Framework's Java Config):

     ```java
     @Configuration
     @EnableWebMvc
     public class WebConfig implements WebMvcConfigurer {
     // Configuration methods here
     }
     ```

## Notes

- In Spring Boot applications, you typically don't need to explicitly configure the DispatcherServlet. Spring Boot auto-configures it based on your application's context.

- The DispatcherServlet is a powerful tool that simplifies the development of web applications by providing a clean, modular, and extensible way to handle requests and responses.