# RestTemplate and WebClient in Spring Boot

Spring Boot provides powerful tools for making HTTP requests to external services, with **RestTemplate** and **WebClient** being the most commonly used for synchronous and asynchronous HTTP calls, respectively.

## 1. Overview

### RestTemplate

- **Legacy Approach**: Used for synchronous client-side HTTP communication.
- **Simple** and easy to use for making basic REST API calls.
- **Deprecated in modern Spring**: While still supported, it’s recommended to use **WebClient** for new applications as it offers more functionality and is non-blocking.

### WebClient

- **Introduced in Spring WebFlux**: Designed for reactive, non-blocking communication.
- **Asynchronous** and provides better performance for high-throughput scenarios.
- Fully supports **reactive programming**.

## 2. Dependencies

Ensure you have the appropriate dependency in your `pom.xml`:

```xml
<!-- RestTemplate (comes with spring-boot-starter-web) -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<!-- WebClient (comes with spring-boot-starter-webflux) -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webflux</artifactId>
</dependency>
```

## 3. Using RestTemplate

### Basic Usage

```java
@RestController
@RequestMapping("/api")
public class RestTemplateController {

    private final RestTemplate restTemplate;

    @Autowired
    public RestTemplateController(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @GetMapping("/consume")
    public String consumeApi() {
        String url = "https://jsonplaceholder.typicode.com/posts";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }
}
```

### Configuring RestTemplate Bean

```java
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
```

### Common Methods in RestTemplate

- `getForObject()`
- `getForEntity()`
- `postForObject()`
- `postForEntity()`
- `put()`
- `delete()`

## 4. Using WebClient

### Basic Usage

```java
@RestController
@RequestMapping("/api")
public class WebClientController {

    private final WebClient webClient;

    @Autowired
    public WebClientController(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("https://jsonplaceholder.typicode.com").build();
    }

    @GetMapping("/consume")
    public Mono<String> consumeApi() {
        return webClient.get()
                .uri("/posts")
                .retrieve()
                .bodyToMono(String.class);
    }
}
```

### Configuring WebClient Bean

```java
@Configuration
public class WebClientConfig {

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}
```

### Common Methods in WebClient

- `retrieve()`: Simplified method for retrieving responses and handling status.
- `exchangeToMono()/exchangeToFlux()`: Allows detailed handling of responses.
- `bodyToMono()/bodyToFlux()`: Converts the response body to a reactive type.

## 5. Key Differences Between RestTemplate and WebClient

| Feature           | RestTemplate                  | WebClient                             |
| ----------------- | ----------------------------- | ------------------------------------- |
| Programming Model | Blocking (Synchronous)        | Non-blocking (Asynchronous)           |
| Reactive Support  | No                            | Yes                                   |
| Performance       | Limited by thread-blocking    | Efficient with high concurrency       |
| Recommended Usage | Legacy systems or simple apps | Modern applications and microservices |

## 6. Integrating with Spring Boot

RestTemplate Example: POST Request

```java
@PostMapping("/create")
public ResponseEntity<String> createPost() {
    String url = "https://jsonplaceholder.typicode.com/posts";
    Post post = new Post(1, "New Post", "This is a post content");
    return restTemplate.postForEntity(url, post, String.class);
}
```

WebClient Example: POST Request

```java
@PostMapping("/create")
public Mono<String> createPost() {
    Post post = new Post(1, "New Post", "This is a post content");
    return webClient.post()
            .uri("/posts")
            .bodyValue(post)
            .retrieve()
            .bodyToMono(String.class);
}
```

## 7. Best Practices

1. Use `WebClient` for new applications to take advantage of non-blocking and reactive capabilities.
2. Configure timeouts and connection pooling for better performance.
3. Handle errors gracefully using `onStatus()` in `WebClient`.

### Example: Error Handling with `WebClient`

```java
@GetMapping("/consume-with-error")
public Mono<String> consumeApiWithErrorHandling() {
    return webClient.get()
            .uri("/posts")
            .retrieve()
            .onStatus(HttpStatus::is4xxClientError, response -> Mono.error(new RuntimeException("4xx Error")))
            .onStatus(HttpStatus::is5xxServerError, response -> Mono.error(new RuntimeException("5xx Error")))
            .bodyToMono(String.class);
}
```

## 8. Conclusion

- `RestTemplate`: Best suited for simple, legacy projects that don't require non-blocking operations.
- `WebClient`: Modern, flexible, and designed for reactive programming, making it the go-to option for scalable applications.

Choose the client based on your application's requirements, and always ensure that your choice aligns with best practices for performance and maintainability.
