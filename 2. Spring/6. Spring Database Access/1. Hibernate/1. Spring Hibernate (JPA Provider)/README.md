# Spring Hibernate as a JPA Provider

## 1. Overview of Spring Hibernate as JPA Provider

In the **Spring Framework**, Hibernate can be used as the **Java Persistence API (JPA)** provider, which simplifies database operations and object-relational mapping (ORM). Spring offers seamless integration with Hibernate, allowing developers to use the standard JPA API while benefiting from Spring’s dependency injection and transaction management features.

JPA is a specification that defines how to manage relational data in Java applications. Hibernate is a popular implementation of JPA, and Spring provides a framework to easily manage Hibernate sessions and transactions through the `EntityManager`.

---

## 2. Key Features of Spring Hibernate as a JPA Provider

### 1. EntityManager

When using Spring with Hibernate as the JPA provider, **`EntityManager`** replaces Hibernate’s native `Session` interface. It provides an abstraction layer for managing database operations (CRUD) in an ORM-based environment. In Spring, `EntityManagerFactory` is used to create `EntityManager` instances.

#### Example: Setting up `EntityManager` in Spring

```java
@Configuration
public class JpaConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.example.entities");
        
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(hibernateProperties());

        return em;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        return properties;
    }
}
```

### 2. EntityManagerFactory
`EntityManagerFactory` is the JPA counterpart to Hibernate’s `SessionFactory`. It is responsible for providing `EntityManager` instances. Spring allows you to configure `EntityManagerFactory` using `LocalContainerEntityManagerFactoryBean`.

The `EntityManagerFactory` is thread-safe and can be reused by multiple EntityManager instances.

#### Example: Usage of EntityManagerFactory
```java
@Service
public class ProductService {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    public void saveProduct(Product product) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
```

### 3. Transaction Management
Spring handles transaction management in a declarative way, even when Hibernate is used as a JPA provider. Spring abstracts the underlying transaction system, allowing you to annotate your service methods with `@Transactional` to manage transactions automatically.

#### Example: Declarative Transaction Management
```java
@Service
public class OrderService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void placeOrder(Order order) {
        entityManager.persist(order);
    }
}
```
With `@Transactional`, you don’t need to manually manage transactions, simplifying the code and ensuring consistency.

### 4. PersistenceContext
`@PersistenceContext` is used to inject an `EntityManager` into a Spring bean. This allows you to use the JPA API in your Spring-managed services without manually creating or closing the `EntityManager`.

#### Example: Using @PersistenceContext
```java
@Repository
public class CustomerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Customer findCustomerById(Long id) {
        return entityManager.find(Customer.class, id);
    }
}
```

## 3. Improvements over Pure Hibernate
While Hibernate can be used directly without JPA, Spring Hibernate as a JPA provider brings several advantages and improvements:

### 1. Standardized API (JPA)
Using JPA with Hibernate provides a standard API that is independent of Hibernate-specific code. This decouples your code from Hibernate, making it easier to switch to another JPA provider if needed. You use standard JPA annotations such as `@Entity`, `@Table`, `@Id`, and so on.

Example: JPA Annotations with Hibernate
```java
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    // Getters and Setters
}
```

### 2. Transaction Management Simplification
In pure Hibernate, transaction management requires explicit handling using the `Session` object and `Transaction` object. With Spring's declarative transaction management, transactions can be handled using `@Transactional`, reducing boilerplate code and preventing potential errors.

### 3. Dependency Injection
With Spring, you benefit from dependency injection, making the management of EntityManager and other resources automatic. This results in cleaner code, better separation of concerns, and easier testing. You can simply inject an EntityManager or EntityManagerFactory into your beans using @PersistenceContext or @PersistenceUnit.

### 4. Integration with Spring Ecosystem
Spring provides integration with the entire Spring ecosystem, such as security, messaging, and web applications. This tight integration allows for seamless use of Spring’s powerful features, such as:

Spring Security for securing your Hibernate-based application.
Spring MVC for building web applications.
Spring AOP for aspects such as logging and auditing.
### 5. Configuration Improvements
Spring simplifies Hibernate configuration. You can define Hibernate properties directly in your Spring configuration (either using Java config or application.properties). Spring also helps in managing data source configuration, entity scanning, and transaction management, removing the need for much of the boilerplate configuration.

### 6. Testing and Mocking
Spring provides support for testing JPA/Hibernate code, allowing you to use @Transactional and @Rollback annotations to manage database state during tests. This makes unit testing easier and more reliable compared to pure Hibernate testing setups.

### Example: Testing a Repository with Spring
```java
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testSaveProduct() {
        Product product = new Product();
        product.setName("Laptop");
        product.setPrice(1000.00);

        productRepository.save(product);

        assertNotNull(productRepository.findById(product.getId()));
    }
}
```

## 4. JPA Configuration with Hibernate in Spring
Here is an example configuration for integrating Hibernate as a JPA provider in Spring.

Maven Dependency
First, you need to include Hibernate and JPA dependencies in your pom.xml:

```xml
<dependencies>
    <!-- Hibernate as JPA Provider -->
    <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-core</artifactId>
        <version>5.6.14.Final</version>
    </dependency>

    <!-- Spring ORM for JPA -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-orm</artifactId>
        <version>5.3.24</version>
    </dependency>
    
    <!-- Other necessary dependencies like MySQL or H2 -->
</dependencies>
```

Spring Configuration
```java
@Configuration
@EnableTransactionManagement
public class HibernateConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.example.entities");
        
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(hibernateProperties());

        return em;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        return properties;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}
```

## 5. Conclusion
By using Hibernate as a JPA provider in Spring, you get the best of both worlds:

The power of Hibernate as a mature ORM solution.
The standardized and simplified API provided by JPA.
The ease of integration and dependency management offered by the Spring Framework.
While pure Hibernate offers flexibility and native features, using it through Spring’s JPA abstraction introduces cleaner code, better testability, simplified transaction management, and improved scalability.


---

## Complete Example

The project will consist of a User entity and a UserService for basic CRUD operations. We'll manually manage the transaction lifecycle and entity persistence without using `SessionFactory` or Spring Data JPA abstractions.

### Dependencies
First, add the necessary dependencies for Spring Boot 3, Hibernate, and H2 Database in pom.xml:

```xml
<dependencies>
    <!-- Spring Boot Starter for Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Spring Boot JPA with Hibernate as provider -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- H2 In-Memory Database for Testing -->
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
    
    <!-- Lombok for Boilerplate Code Reduction -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <scope>provided</scope>
    </dependency>

</dependencies>
```

### `application.properties`
Configure an in-memory H2 database in src/main/resources/application.properties:

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.show-sql=true
```

### `User` Entity
Define the `User` entity. This class will be mapped to the database table using JPA annotations.

```java
package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    // Constructor, toString, equals, and hashCode can be generated via Lombok or written manually.
}
```

### `UserService`
We will now create a `UserService` that uses `EntityManager` to perform CRUD operations. We will manually handle transactions without relying on Spring Data JPA or SessionFactory.

```java
package com.example.demo.service;

import com.example.demo.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public User saveUser(User user) {
        entityManager.persist(user);
        return user;
    }

    @Transactional
    public User updateUser(User user) {
        return entityManager.merge(user);
    }

    public Optional<User> findById(Long id) {
        User user = entityManager.find(User.class, id);
        return Optional.ofNullable(user);
    }

    public List<User> findAll() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }
}
```

UserController
Create a REST controller that will expose endpoints to interact with the User entity via UserService.

```java
package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        Optional<User> optionalUser = userService.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            user.setPassword(userDetails.getPassword());
            return ResponseEntity.ok(userService.updateUser(user));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> optionalUser = userService.findById(id);
        return optionalUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
```

### Main Application Class
This is the main class that bootstraps the Spring Boot application:

```java
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
```

### Explanation:
1. **Dependencies**: We use Spring Boot's starter for web and data (without Spring Data JPA) and Hibernate is configured under the hood as the JPA provider.
2. **EntityManager**: The EntityManager is the JPA interface for interacting with the database. It replaces Hibernate's SessionFactory and does not use Spring Data JPA repositories.
1. **Manual Transactions**: Transactions are manually controlled using @Transactional. You can manage lifecycle operations like persist, merge, remove, and query with JPQL.
1. **H2 Database**: For simplicity, we use an in-memory H2 database that you can access via http://localhost:8080/h2-console after starting the application.

### Running the Application
- You can test the CRUD operations via Postman or any other API client by making requests to `/api/users`.

### Sample Requests:
#### Create a User (POST /api/users):

```json
{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "password": "secret123"
}
```

#### Update a User (PUT /api/users/1):

```json
{
  "name": "Jane Doe",
  "email": "jane.doe@example.com",
  "password": "newpassword123"
}
```

#### Get All Users (GET /api/users)

#### Get User by ID (GET /api/users/1)

#### Delete a User (DELETE /api/users/1)

This example demonstrates how to use Hibernate as a JPA provider without relying on Spring Data JPA or Hibernate's SessionFactory. All database interactions are handled by the EntityManager.

### No need of EntityManagerFactory
In Spring with Hibernate, you can directly inject `EntityManager` without explicitly injecting `EntityManagerFactory`. Spring manages the lifecycle of the `EntityManager` for you when you use the `@PersistenceContext` annotation.

#### **EntityManager Injection
By annotating a field with `@PersistenceContext`, Spring will provide the appropriate EntityManager instance, which is configured to work with the underlying EntityManagerFactory.

```java
@PersistenceContext
private EntityManager entityManager;
```

2. **Transaction Management**: When you annotate your service methods with @Transactional, Spring handles transaction boundaries for you, simplifying your code.

3. EntityManager Lifecycle: The EntityManager is not thread-safe, so it's recommended to use it within a method and let Spring manage its lifecycle.

Example:
Here's a quick overview:

```java
@Service
public class UserService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    // Other CRUD operations...
}
In this setup, you do not need to manually inject or manage EntityManagerFactory. Spring handles it, and using EntityManager directly is the preferred approach for most applications.