# Spring Cloud Eureka

## 1. Introduction

**Spring Cloud Eureka** is a **service discovery** solution based on Netflix's Eureka. It is a key component in **microservice architectures**, enabling services to automatically register themselves and discover other services without hard-coded configurations.

In a microservices system, where services often change or scale dynamically, Eureka provides a way for these services to find each other in a decentralized manner.

---

## 2. Key Concepts

### a. Service Registry

Eureka Server acts as a **service registry**. Services register with Eureka, providing metadata (like their IP address, hostname, or port) and health information. Once registered, services can discover and communicate with other services.

### b. Service Registration

When a service starts, it registers itself with Eureka Server. The registration process involves sending metadata (such as service name, instance ID, and health status) to Eureka Server. 

#### Example of Service Registration

A service registers itself with the Eureka server in `application.yml` or `application.properties`:

```yaml
spring:
  application:
    name: customer-service

eureka:
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: http://localhost:8761/eureka/
```

### c. Service Discovery
Once registered, other services can discover this service using the service name. The Eureka client fetches the service registry from the Eureka server and can use this registry to route traffic to instances of other services.

#### Example of Service Discovery
A service can use the RestTemplate or WebClient to discover other services:

```java
@Autowired
private DiscoveryClient discoveryClient;

public List<ServiceInstance> getInstances() {
    return discoveryClient.getInstances("customer-service");
}
```

### d. Heartbeats and Renewals
After registering with the Eureka server, each service sends periodic heartbeats to confirm its availability. If the Eureka server doesn't receive heartbeats for a configurable duration, the service is removed from the registry. This helps with resilience and dynamic service availability.

## 3. Eureka Server Setup
### a. Maven Dependencies
To set up Eureka Server, add the following dependencies in the pom.xml file:

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
    </dependency>
</dependencies>
```

### b. Enable Eureka Server
Create a Spring Boot application and enable Eureka Server by adding the @EnableEurekaServer annotation in the main application class:

```java
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
```

### c. Configuration for Eureka Server
In application.yml, you define the server settings for Eureka:

```yaml
server:
  port: 8761

eureka:
  client:
    register-with-eureka: false
    fetch-registry: false
  server:
    wait-time-in-ms-when-sync-empty: 0
```

The Eureka server is now running on port 8761.

## 4. Eureka Client Setup
### a. Maven Dependencies
Add the following dependencies to the services that need to register with Eureka:

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>
</dependencies>
```

### b. Enable Eureka Client
In the service’s main application class, use the @EnableEurekaClient annotation:

```java
@SpringBootApplication
@EnableEurekaClient
public class CustomerServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
}
```

### c. Service Registration Configuration
In the service's application.yml file, configure Eureka registration:

```yaml
spring:
  application:
    name: customer-service

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
```

## 5. Load Balancing with Eureka
Eureka supports client-side load balancing with Ribbon or Spring Cloud LoadBalancer. When multiple instances of a service are registered, the Eureka client automatically distributes the load across the available instances.

### a. Using RestTemplate with Load Balancing
To enable load balancing for the RestTemplate, annotate it with `@LoadBalanced`:

```java
@Bean
@LoadBalanced
public RestTemplate restTemplate() {
    return new RestTemplate();
}
```

This allows `RestTemplate` to automatically route traffic to different instances of a service registered with Eureka.

### b. Using WebClient with Load Balancing
You can also use WebClient with a load balancer:

```java
@Bean
@LoadBalanced
public WebClient.Builder webClientBuilder() {
    return WebClient.builder();
}
```

## 6. High Availability with Eureka
For high availability, Eureka supports running in a peer-to-peer cluster. Multiple Eureka servers replicate service registry information to provide redundancy.

### a. Configuring Multiple Eureka Servers
In `application.yml`, you can configure multiple Eureka servers to replicate information:

```yaml
eureka:
  client:
    service-url:
      defaultZone: http://eureka-server-1:8761/eureka/, http://eureka-server-2:8761/eureka/
```

Each server in the cluster will synchronize registry information with the other Eureka servers.

## 7. Resilience and Self-Preservation
Eureka has a self-preservation mode to handle network partitions or outages. When enabled, Eureka doesn't automatically deregister instances even if heartbeats are missed. This helps avoid situations where healthy services are mistakenly removed due to temporary network issues.

## 8. Advantages and Use Cases
### Advantages of Eureka
- **Dynamic Service Discovery**: No need for hardcoded service URLs. Services can find each other dynamically.
- **Resilience**: Eureka provides automatic failover and self-healing through heartbeats and self-preservation mode.
- **Load Balancing**: Client-side load balancing is easily enabled via Ribbon or Spring Cloud LoadBalancer.
- **High Availability**: Eureka supports peer-to-peer replication for high availability.
- **Integration with Spring**: Native support and seamless integration with Spring Cloud and Spring Boot.

### Common Use Cases
- **Microservices Architecture**: Eureka is ideal for microservices-based architectures where services scale dynamically.
- **Dynamic Service Discovery**: Helps discover services dynamically without hardcoded IPs and ports.
- **Load Balancing and Fault Tolerance**: Combined with Ribbon and Spring Cloud LoadBalancer, Eureka supports resilience and load balancing.

## 9. Limitations
- **Client-Side Load Balancing**: Eureka performs client-side load balancing, which may require more network bandwidth compared to server-side load balancers.
- **Scaling Challenges**: For very large clusters, performance degradation can occur as Eureka servers replicate large amounts of metadata.

## 10. Conclusion
Spring Cloud Eureka plays a critical role in service discovery in microservices-based architectures. It simplifies the discovery of services, enables client-side load balancing, and supports high availability with peer-to-peer clustering. Through integration with Spring Boot and other Spring Cloud projects, Eureka offers a powerful and flexible solution for building resilient, scalable microservice ecosystems.