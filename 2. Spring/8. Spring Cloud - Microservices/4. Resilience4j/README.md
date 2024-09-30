# Resilience4j in Spring

## 1. Introduction

**Resilience4j** is a lightweight, easy-to-use library for adding **resilience** patterns to your Spring applications. It helps in building fault-tolerant systems by providing tools like **circuit breakers**, **retry mechanisms**, **rate limiting**, **bulkheading**, and **caching**. Unlike **Hystrix**, Resilience4j is modular, designed for Java 8 and functional programming, and integrates seamlessly with Spring.

---

## 2. Key Features of Resilience4j

1. **Circuit Breaker**: Prevents system overload by breaking the circuit when a service is failing.
2. **Retry**: Automatically retries failed operations.
3. **Rate Limiter**: Limits the number of calls to a service.
4. **Bulkhead**: Limits the number of concurrent calls to a service.
5. **TimeLimiter**: Limits the execution time of a call.

Resilience4j can be combined with Spring Boot via annotations and configuration properties, making it easy to apply resilience patterns.

---

## 3. Adding Resilience4j Dependencies

To use Resilience4j in your Spring Boot project, add the following dependencies to your `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<dependency>
    <groupId>io.github.resilience4j</groupId>
    <artifactId>resilience4j-spring-boot2</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId> 
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

We need to add the `spring-boot-starter-actuator` dependency to monitor the application's current state as well as the circuitbreaker state through a set of exposed endpoints.

## 4. Circuit Breaker Pattern in Resilience4j
### a. What is a Circuit Breaker?
A Circuit Breaker monitors the number of failures and opens the circuit when failures exceed a threshold. Once the circuit is open, requests are automatically blocked, preventing the application from calling a service that is already failing.

### b. Configuring Circuit Breaker in Spring Boot
Resilience4j allows you to configure Circuit Breakers either through configuration files or Java code.

#### Example 1: Configuration via application.yml
```yaml
resilience4j:
  circuitbreaker:
    instances:
      backendService:
        registerHealthIndicator: true
        slidingWindowSize: 10
        failureRateThreshold: 50
        waitDurationInOpenState: 10000
        permittedNumberOfCallsInHalfOpenState: 3
        slidingWindowType: COUNT_BASED
        minimumNumberOfCalls: 5
```

#### Example 2: Applying Circuit Breaker via Annotations
```java
@RestController
public class BackendController {

    private final BackendService backendService;

    public BackendController(BackendService backendService) {
        this.backendService = backendService;
    }

    @GetMapping("/backend")
    @CircuitBreaker(name = "backendService", fallbackMethod = "fallback")
    public String callBackendService() {
        return backendService.process();
    }

    public String fallback(Throwable t) {
        return "Fallback response due to error: " + t.getMessage();
    }
}
```

#### Explanation:

- The `@CircuitBreaker` annotation is used to protect the `callBackendService` method.
- The `fallback` method is triggered when the circuit breaker opens due to failures.
- The configuration in `application.yml` specifies when the circuit should open (e.g., 50% failure rate, 10-second wait duration).

## 5. Retry Pattern in Resilience4j
### a. What is Retry?
Retry allows you to automatically retry a failed operation before giving up. You can configure the number of retries, the interval between retries, and which exceptions trigger the retry.

### b. Configuring Retry in Spring Boot
#### Example: Configuring Retry in application.yml
```yaml
resilience4j:
  retry:
    instances:
      backendService:
        maxAttempts: 3
        waitDuration: 5000
```

#### Applying Retry via Annotations
```java
@Retry(name = "backendService", fallbackMethod = "fallback")
public String callBackendService() {
    return backendService.process();
}
```
#### Explanation:

- The `@Retry` annotation is used to retry the callBackendService method if it fails.
- If the method fails after 3 retries, the fallback method is invoked.

## 6. Rate Limiting in Resilience4j
### a. What is Rate Limiting?
Rate Limiting controls the rate of requests sent to a service. It ensures that a service is not overwhelmed by too many requests in a short period.

### b. Configuring Rate Limiter in Spring Boot
#### Example: Configuring Rate Limiter in application.yml
```yaml
resilience4j:
  ratelimiter:
    instances:
      backendService:
        limitForPeriod: 5
        timeoutDuration: 1000
        limitRefreshPeriod: 5000
```

#### Applying Rate Limiter via Annotations
```java
@RateLimiter(name = "backendService", fallbackMethod = "fallback")
public String callBackendService() {
    return backendService.process();
}
```

#### Explanation:

- The `@RateLimiter` annotation ensures that the method is called only a limited number of times (5) within a specified time period (5 seconds).
- If the limit is reached, the fallback method is called.

## 7. Bulkhead Pattern in Resilience4j
### a. What is Bulkhead?
Bulkheading limits the number of concurrent calls to a particular service. This helps to isolate failures and prevent a single service from overloading the entire system.

### b. Configuring Bulkhead in Spring Boot
#### Example: Configuring Bulkhead in application.yml
```yaml
resilience4j:
  bulkhead:
    instances:
      backendService:
        maxConcurrentCalls: 5
        maxWaitDuration: 1000
```

#### Applying Bulkhead via Annotations
```java
@Bulkhead(name = "backendService", fallbackMethod = "fallback")
public String callBackendService() {
    return backendService.process();
}
```

#### Explanation:

- The `@Bulkhead` annotation limits the number of concurrent calls to 5.
- If more than 5 concurrent calls are attempted, the fallback method is called.

## 8. Time Limiter Pattern in Resilience4j
### a. What is a Time Limiter?
A Time Limiter ensures that a service call completes within a given time limit. If the operation takes longer, it is interrupted, and a fallback is invoked.

### b. Configuring Time Limiter in Spring Boot
#### Example: Configuring Time Limiter in application.yml
```yaml
resilience4j:
  timelimiter:
    instances:
      backendService:
        timeoutDuration: 2s
```

#### Applying Time Limiter via Annotations
```java
@TimeLimiter(name = "backendService", fallbackMethod = "fallback")
public CompletableFuture<String> callBackendService() {
    return CompletableFuture.supplyAsync(() -> backendService.process());
}
```

#### Explanation:

- The `@TimeLimiter` annotation ensures that the service call is interrupted if it takes more than 2 seconds.
- A fallback method is invoked if the time limit is exceeded.

## 9. Combining Resilience Patterns
Resilience4j allows you to combine multiple patterns on the same method to provide comprehensive resilience.

#### Example: Combining Circuit Breaker, Retry, and Rate Limiter
```java
@CircuitBreaker(name = "backendService", fallbackMethod = "fallback")
@Retry(name = "backendService")
@RateLimiter(name = "backendService")
public String callBackendService() {
    return backendService.process();
}
```

In this example:

- The circuit breaker protects against failures.
- If the circuit breaker is closed, the retry mechanism retries failed calls.
- Rate limiting ensures that the method is not overwhelmed with too many requests.

## 10. Monitoring with Resilience4j and Micrometer
- Resilience4j provides integration with Micrometer to monitor resilience metrics like circuit breaker status, retry counts, and rate-limiter usage.
- To enable monitoring, add the following dependency:

```xml
<dependency>
    <groupId>io.github.resilience4j</groupId>
    <artifactId>resilience4j-micrometer</artifactId>
    <version>2.0.2</version>
</dependency>
```

You can then monitor metrics using tools like Prometheus or Grafana.

## 11. Conclusion
Resilience4j provides a powerful and flexible framework for adding resilience patterns to Spring applications. By integrating circuit breakers, retries, rate limiting, and more, you can ensure that your microservices are fault-tolerant and able to handle various failure scenarios. Resilience4j’s lightweight, modular design makes it ideal for modern cloud-native applications.