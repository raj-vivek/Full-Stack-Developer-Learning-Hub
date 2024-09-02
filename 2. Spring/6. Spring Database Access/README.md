# Spring Database Access

## Overview

- Spring provides a set of tools and abstractions for managing data access and persistence in Spring applications.
- This includes integration with various data access technologies, simplifying database operations, and abstracting away boilerplate code.
- Key components include Spring JDBC, Spring Data JPA, Spring ORM, and Spring Hibernate.

## Key Components

### 1. Spring JDBC

- **Description**: Provides a simplified way to interact with relational databases using JDBC (Java Database Connectivity). It helps manage database connections and execute SQL queries without dealing with the low-level JDBC API directly.
- **Features**:
  - Simplified exception handling with `DataAccessException`.
  - Support for declarative transaction management.
  - Easier integration with various data sources.
- **Usage**:

  ```java
  @Autowired
  private JdbcTemplate jdbcTemplate;

  public void addEmployee(String name, int age) {
      jdbcTemplate.update("INSERT INTO employee (name, age) VALUES (?, ?)", name, age);
  }
  ```

### 2. Spring Data JPA

- **Description**: Provides an abstraction layer for working with JPA (Java Persistence API). It simplifies the implementation of data access layers and integrates with JPA providers like Hibernate.
- **Features**:
  Repository abstraction to reduce boilerplate code.
  Support for query methods, custom queries, and pagination.
  Integration with JPA providers and transaction management.
- **Usage**:

```java
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByName(String name);
}
```

### 3. Spring ORM

- **Description**: Provides support for Object-Relational Mapping (ORM) frameworks such as Hibernate, JPA, and MyBatis. It helps in integrating these ORM frameworks with the Spring framework.
- **Features**:
  Simplified configuration and transaction management.
  Integration with ORM frameworks for entity management and data persistence.
  Exception translation for ORM exceptions.
- **Usage**:

```java
@Service
public class EmployeeService {
    @Autowired
    private SessionFactory sessionFactory;

    public void saveEmployee(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.save(employee);
    }
}
```

### 4. Spring Hibernate

- **Description**: An implementation of the ORM framework specifically designed for integrating Hibernate with Spring. It provides additional support and configuration for using Hibernate as the JPA provider.
- **Features**
  Integration with Spring’s transaction management.
  Simplified Hibernate configuration.
  Support for Hibernate-specific features and configuration.
- **Usage**:

```java
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    // getters and setters
}
```

### Steps:

1. Include Dependencies: Add necessary dependencies to your pom.xml or build.gradle file.

   - For eg:
     1. Spring Data JPA
     2. MySQL Driver

2. Configure Data Source: Set up your database connection properties in application.properties or application.yml.
3. Create Entities and Repositories: Define your JPA entities and repositories for data access.
4. Use Services: Implement your business logic and interact with the data access layer.

### Differences and Usecases for Spring Data JPA, Spring JDBC, Spring ORM (which includes Hibernate), and Spring Data JDBC

- Spring Data JPA and Spring ORM/Hibernate are prevelant in enterprise applications and the complex data models typical in banking systems.

1. Spring Data JPA

   - **Description:** Spring Data JPA is a part of the Spring Data project, which provides an abstraction over JPA (Java Persistence API). It simplifies data access layers by providing a repository abstraction that integrates with JPA providers like Hibernate.
   - **Features:**
   - Repositories: Offers a high-level repository abstraction to perform CRUD operations and queries without writing boilerplate code.
   - Query Methods: Allows defining query methods based on method names or using JPQL (Java Persistence Query Language) or SQL.
   - Entity Management: Manages entities with automatic persistence and transaction handling.
   - **Use Cases:** Ideal for applications requiring a rich object-relational mapping (ORM) layer and a complex domain model with features like lazy loading, cascading, and caching.

2. Spring JDBC

   - **Description:** Spring JDBC provides a simpler approach to interacting with databases using plain SQL queries. It simplifies the error handling and resource management associated with JDBC.
   - **Features:**
   - JdbcTemplate: Provides a template class to execute SQL queries, update statements, and handle results. It reduces boilerplate code for common JDBC operations.
   - RowMapper: Maps rows of a ResultSet to objects.
   - Exception Translation: Translates SQL exceptions into Spring's DataAccessException hierarchy.
   - **Use Cases:** Suitable for applications where the interaction with the database is straightforward and does not require complex ORM features. Ideal for scenarios with simple queries or when you need full control over SQL.

3. Spring ORM (Spring Hibernate)

   - **Description:** Spring ORM provides integration with ORM frameworks like Hibernate. It manages the lifecycle of Hibernate sessions and transactions, providing a consistent programming model.
   - **Features:**
   - Session Management: Manages Hibernate sessions and transactions.
   - Exception Handling: Provides exception translation for ORM exceptions into Spring's DataAccessException.
   - Integration: Works with different ORM providers, not just Hibernate.
   - **Use Cases:** Useful for applications using Hibernate or other ORM frameworks, where you need to handle entity management and transactions with an ORM while benefiting from Spring's capabilities.

4. Spring Data JDBC

   - **Description:** Spring Data JDBC is a newer, simpler approach compared to Spring Data JPA. It provides a more lightweight, straightforward way to interact with databases using JDBC.
   - **Features:**
   - Repositories: Provides repository support similar to Spring Data JPA but without the complexity of JPA.
   - Mapping: Maps database tables directly to POJOs without the need for complex ORM features.
   - Simpler: Focuses on simplicity and performance, avoiding the complexity of JPA/Hibernate.
   - **Use Cases:** Ideal for applications that need simple data access and want to avoid the overhead of JPA. Suitable for scenarios where a lightweight, performance-oriented approach is preferred over a full ORM solution.
