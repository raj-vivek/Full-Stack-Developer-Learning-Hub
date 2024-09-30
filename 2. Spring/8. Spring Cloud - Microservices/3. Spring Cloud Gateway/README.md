# Spring Cloud Gateway

## 1. Introduction

**Spring Cloud Gateway** is an API Gateway built on top of **Spring WebFlux** and designed to provide a unified entry point for microservices. It offers powerful features such as route handling, request/response transformation, and built-in filters like rate-limiting, circuit breakers, and retries.

It provides two main ways to define routes:

1. **Using `application.properties` or `application.yml`**
2. **Programmatic route definition using Java configuration (`RouteLocator`)**

---

## 2. Key Features

- **Routing**: Maps incoming requests to destination services.
- **Filters**: Pre and post filters for modifying requests and responses.
- **Built on Spring WebFlux**: Provides non-blocking, reactive features.
- **Supports load balancing**: When integrated with **Eureka** or **Ribbon**, it enables client-side load balancing.
- **Integrates with Spring Cloud ecosystem**: Works well with Eureka, OpenFeign, and Hystrix for fault tolerance.

---

## 3. Adding Dependencies

To start using Spring Cloud Gateway, you need the following dependency in your `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-gateway</artifactId>
</dependency>
```

Additionally, if you plan to use Eureka for service discovery or Feign for communication with other services, you’ll need:

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```

## 4. Configuring Routes

### a. Configuring Routes in application.properties/application.yml

You can configure the routes declaratively using application.properties or application.yml. This is the simpler and more commonly used method.

#### Example of route configuration in application.yml:

```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: user-service-route
          uri: http://localhost:8081
          predicates:
            - Path=/users/**
          filters:
            - AddRequestHeader=X-Request-Foo, Bar
        - id: order-service-route
          uri: lb://ORDER-SERVICE
          predicates:
            - Path=/orders/**
          filters:
            - AddRequestParameter=source, gateway
```

#### Explanation:

- **Predicates**: Define when the route should be matched (e.g., based on path patterns like `/users/**` or `/orders/**`).
- **Filters**: Modify the request or response (e.g., adding request headers or parameters).
- **URI**: The target service the route points to. You can point to a hardcoded URL or a service registered with Eureka using lb://SERVICE-NAME.

### b. Programmatically Configuring Routes using RouteLocator

You can configure routes programmatically using a RouteLocator bean in a Spring configuration class. This allows you to leverage Java code to define more dynamic routing logic.

#### Example of programmatic route definition:

```java
@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("user-service", r -> r.path("/users/**")
                .filters(f -> f.addRequestHeader("X-Request-Foo", "Bar"))
                .uri("http://localhost:8081"))
            .route("order-service", r -> r.path("/orders/**")
                .filters(f -> f.addRequestParameter("source", "gateway"))
                .uri("lb://ORDER-SERVICE"))
            .build();
    }
}
```

#### Explanation:

- We define two routes in the `RouteLocator` bean.
- For each route, we specify a path (`/users/**` and `/orders/**`) and apply filters (like adding a request header or parameter).
- The `uri()` method points to the service. You can use either a fixed URI or leverage Eureka's service discovery by using `lb://SERVICE-NAME`.

## 5. Route Predicates and Filters

### a. Predicates

Spring Cloud Gateway uses predicates to determine which route to match based on the incoming request. Some common predicates include:

- **Path Predicate**: Matches based on URL path.
- **Method Predicate**: Matches based on the HTTP method (GET, POST, etc.).
- **Header Predicate**: Matches if a request contains a specific header.
- **Query Predicate**: Matches if a specific query parameter is present.

#### Example using Path and Method predicates:

```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: complex-route
          uri: http://localhost:8082
          predicates:
            - Path=/complex/**
            - Method=GET
            - Query=username
```

### b. Filters

Filters can be used to modify the incoming request or outgoing response. Some commonly used filters include:

- **AddRequestHeader**: Adds a custom header to the request.
- **AddRequestParameter**: Adds a query parameter to the request.
- **RewritePath**: Rewrites the request path before forwarding the request to the destination service.
- **Retry**: Adds retry logic in case of failures.
- **Circuit Breaker**: Integrates with Resilience4j to implement a circuit breaker.

#### Example of using filters:

```java
@Bean
public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
    return builder.routes()
        .route("circuit-breaker-route", r -> r.path("/fallback/**")
            .filters(f -> f.circuitBreaker(c -> c.setName("myCircuitBreaker").setFallbackUri("forward:/fallback")))
            .uri("lb://some-service"))
        .build();
}
```

## 6. Spring Cloud Gateway and Eureka Integration

Spring Cloud Gateway can integrate with Eureka for service discovery. When the Gateway routes requests, it can discover downstream services dynamically via Eureka. You specify the service name using `lb://SERVICE-NAME`, and the Gateway automatically discovers the service’s instances from Eureka.

Example using Eureka integration:

```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: order-service-route
          uri: lb://ORDER-SERVICE
          predicates:
            - Path=/orders/**
```

Here, `lb://ORDER-SERVICE` tells the Gateway to dynamically load-balance requests to instances of the `ORDER-SERVICE` registered in Eureka.

## 7. Security with Spring Cloud Gateway

Spring Cloud Gateway can be secured using Spring Security. You can define authentication and authorization policies to protect the Gateway and downstream services.

### Example configuration to secure Gateway routes:

```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: secured-route
          uri: lb://SECURED-SERVICE
          predicates:
            - Path=/secure/**
          filters:
            - RemoveRequestHeader=Cookie
            - StripPrefix=1
```

You can then configure Spring Security in the Gateway application to protect the routes:

```java
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/secure/**").authenticated()
            .anyRequest().permitAll()
            .and()
            .oauth2Login();
    }
}
```

## 8. Advantages of Using Spring Cloud Gateway

- **Declarative Route Configuration**: Easily configure routes through YAML or Java.
- **Reactive Architecture**: Built on top of Spring WebFlux, making it ideal for high-throughput systems.
- **Filter Mechanism**: Provides powerful pre and post filter mechanisms for routing.
- **Integration with Eureka and OpenFeign**: Works seamlessly with Spring Cloud projects for service discovery and inter-service communication.
- **Load Balancing**: Client-side load balancing with service discovery.

## 9. Conclusion

Spring Cloud Gateway is a robust solution for building API gateways in microservice architectures. It offers declarative route configuration, a wide array of filters, and integration with Spring Cloud components such as Eureka and OpenFeign, making it a critical piece of any cloud-native application stack.
