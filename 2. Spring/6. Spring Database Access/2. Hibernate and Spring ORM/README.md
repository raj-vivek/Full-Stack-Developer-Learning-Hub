# Spring ORM (Hibernate)

## Theory

1. Object-Relational Mapping (ORM)

   - **Definition**: ORM (Object-Relational Mapping) is a programming technique that allows developers to interact with a relational database using object-oriented programming languages. It abstracts the database interactions by mapping objects in code to database tables.

2. Hibernate

   - **Definition**: Hibernate is a popular ORM framework that provides a comprehensive solution for mapping an object-oriented domain model to a relational database. It simplifies database interactions by providing automatic mapping, managing CRUD operations, and handling entity relationships.
   - **Role**: Hibernate uses ORM principles to facilitate data persistence and retrieval in Java applications. It handles the details of object-relational mapping, session management, and transaction handling within a Java application.

3. Spring ORM
   - **Definition**: Spring ORM (Object-Relational Mapping) integrates Hibernate (and other ORM frameworks) with the Spring Framework to enhance data persistence management in Java applications.
   - **Purpose**: Spring ORM builds on Hibernate by providing additional features such as simplified configuration, enhanced transaction management, and exception handling.
   - **Integration**: It offers unified configuration and transaction management support, making it easier to manage ORM tools within a Spring-based application. It streamlines the development process by leveraging Spring's capabilities to handle ORM complexities.

### Key Concepts for Hibernate

1. Hibernate Overview

   - ORM Framework: Hibernate is an Object-Relational Mapping (ORM) framework that maps Java objects to database tables.
   - CRUD Operations: It handles Create, Read, Update, and Delete operations.
   - Entity Management: Manages relationships between entities and abstracts database interactions.
   - Session Management: Uses SessionFactory to create Session instances for database operations.

2. SessionFactory

   - Purpose: The SessionFactory is responsible for creating Session objects that interact with the database.
   - Configuration: Configured via hibernate.cfg.xml or programmatically. Handles database connection properties and mapping files.
   - Lifecycle: Typically created once per application and managed manually in a non-Spring environment.

3. Session

   - Purpose: Represents a single-threaded unit of work with the database. It is used to perform CRUD operations.
   - Transactions: Sessions are used within transactions to ensure data consistency and integrity.
   - Operations: Provides methods like save(), update(), delete(), and get() for interacting with database entities.

4. Transaction Management

   - Manual Transactions: In vanilla Hibernate, transactions are managed manually using session.beginTransaction() and session.getTransaction().commit().

5. Exception Handling

   - Hibernate Exceptions: Handles exceptions such as JDBCException and ConstraintViolationException. These are not translated into Spring’s exceptions.

### Key Concepts for Spring ORM

1. Spring ORM Integration

   - Unified Configuration: Provides integration for Hibernate and other ORM frameworks within the Spring ecosystem.
   - Spring Data Access: Manages ORM configuration and session management through Spring's dependency injection and bean lifecycle.

1. Spring ORM Features

   - Simplified Configuration: Configures ORM tools like Hibernate through Spring's configuration files or Java-based configuration.
   - Exception Translation: Translates ORM-specific exceptions into Spring’s DataAccessException for consistent error handling.

1. HibernateTemplate

   - Purpose: A Spring utility class that simplifies Hibernate interactions by providing methods for common database operations.
   - Session Management: Manages session lifecycle and reduces boilerplate code for CRUD operations.
   - Exception Handling: Translates Hibernate exceptions into Spring's DataAccessException.

1. Transaction Management

   - Declarative Transactions: Spring provides declarative transaction management using annotations like @Transactional, which abstracts transaction handling from the business logic.
   - Integration: Integrates with Hibernate to manage transactions, ensuring that database operations are performed within a transactional context.

1. Spring Data JPA (Alternative to HibernateTemplate)

   - Repositories: Provides repository abstractions like JpaRepository for simplified data access and CRUD operations.
   - EntityManager: A JPA component used for managing the persistence context and transactions in a more standardized way compared to Hibernate's Session.

### Vanilla Hibernate Example

1.  Add Dependencies

    - In your pom.xml for Maven, add:

      ```xml
      <dependencies>
          <!-- Hibernate Core -->
          <dependency>
              <groupId>org.hibernate</groupId>
              <artifactId>hibernate-core</artifactId>
              <version>6.0.0.Final</version>
          </dependency>
          <!-- H2 Database -->
          <dependency>
              <groupId>com.h2database</groupId>
              <artifactId>h2</artifactId>
              <version>2.1.214</version>
              <scope>runtime</scope>
          </dependency>
      </dependencies>
      ```

2.  Hibernate Configuration

    - Create a hibernate.cfg.xml in the src/main/resources directory:

      ```xml
      <!DOCTYPE hibernate-configuration PUBLIC "-//Hibernate/Hibernate Configuration DTD 3.0//EN" "http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd">
      <hibernate-configuration>
          <session-factory>
              <!-- Database connection settings -->
              <property name="hibernate.connection.driver_class">org.h2.Driver</property>
              <property name="hibernate.connection.url">jdbc:h2:mem:testdb</property>
              <property name="hibernate.connection.username">sa</property>
              <property name="hibernate.connection.password"></property>

              <!-- Specify dialect -->
              <property name="hibernate.dialect">org.hibernate.dialect.H2Dialect</property>

              <!-- Echo all executed SQL to stdout -->
              <property name="hibernate.show_sql">true</property>

              <!-- Drop and re-create the database schema on startup -->
              <property name="hibernate.hbm2ddl.auto">update</property>

              <!-- Mention annotated class -->
              <mapping class="com.example.model.Person"/>
          </session-factory>
      </hibernate-configuration>
      ```

3.  Create Entity

    - Create an entity class Person in the com.example.model package:

      ```java
      package com.example.model;

      import javax.persistence.Entity;
      import javax.persistence.GeneratedValue;
      import javax.persistence.GenerationType;
      import javax.persistence.Id;

      @Entity
      public class Person {
          @Id
          @GeneratedValue(strategy = GenerationType.IDENTITY)
          private Long id;
          private String name;

          // Getters and Setters
          public Long getId() {
              return id;
          }

          public void setId(Long id) {
              this.id = id;
          }

          public String getName() {
              return name;
          }

          public void setName(String name) {
              this.name = name;
          }
      }
      ```

4.  Create Main Class

    - Create a main class to demonstrate basic CRUD operations:

      ```java
      package com.example;

      import com.example.model.Person;
      import org.hibernate.Session;
      import org.hibernate.SessionFactory;
      import org.hibernate.cfg.Configuration;

      public class Main {
          public static void main(String[] args) {
              // Create SessionFactory
              SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

              // Create a new Person
              Person person = new Person();
              person.setName("John Doe");

              // Save the Person
              try (Session session = sessionFactory.openSession()) {
                  session.beginTransaction();
                  session.save(person);
                  session.getTransaction().commit();
              }

              // Read the Person
              try (Session session = sessionFactory.openSession()) {
                  Person retrievedPerson = session.get(Person.class, person.getId());
                  System.out.println("Retrieved Person: " + retrievedPerson.getName());
              }

              // Update the Person
              try (Session session = sessionFactory.openSession()) {
                  session.beginTransaction();
                  Person retrievedPerson = session.get(Person.class, person.getId());
                  retrievedPerson.setName("Jane Doe");
                  session.update(retrievedPerson);
                  session.getTransaction().commit();
              }

              // Delete the Person
              try (Session session = sessionFactory.openSession()) {
                  session.beginTransaction();
                  Person retrievedPerson = session.get(Person.class, person.getId());
                  session.delete(retrievedPerson);
                  session.getTransaction().commit();
              }

              // Close SessionFactory
              sessionFactory.close();
          }
      }
      ```

### Spring ORM - HibernateTemplate methods

It provides various methods which facilitate the insertion, deletion, modification, and retrieval of data from the database.

1. void clear()
2. void delete(Object entity)
3. <T> T get(Class<T> entityClass, Serializable id)
4. <T> T load(Class<T> entityClass, Serializable id)
5. <T> List<T> loadAll(Class<T> entityClass)
6. Serializable save(Object entity)
7. void saveOrUpdate(Object entity)
8. void update(Object entity)

### Hibernate with Spring ORM example

#### Maven dependencies

1. Hibernate
2. Spring Core
3. Spring Context
4. Spring JDBC
5. Spring ORM
6. MySQL Connector Java

#### Hibernate Configuration File (hibernate.cfg.xml)

```xml
<hibernate-configuration>
    <session-factory>
        <property name="hibernate.dialect">org.hibernate.dialect.MySQLDialect</property>
        <property name="hibernate.hbm2ddl.auto">update</property>
        <property name="hibernate.show_sql">true</property>
        <mapping class="com.example.User"/>
    </session-factory>
</hibernate-configuration>
```

#### Spring Configuration File (applicationContext.xml)

```xml
<!-- Define the datasource bean -->
<bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
    <property name="driverClassName" value="com.mysql.cj.jdbc.Driver"/>
    <property name="url" value="jdbc:mysql://localhost:3306/mydb"/>
    <property name="username" value="root"/>
    <property name="password" value="password"/>
</bean>

<!-- Define the session factory bean -->
<bean id="sessionFactory" class="org.springframework.orm.hibernate5.LocalSessionFactoryBean">
    <property name="dataSource" ref="dataSource"/>
    <property name="hibernateProperties">
        <props>
            <prop key="hibernate.dialect">org.hibernate.dialect.MySQLDialect</prop>
            <prop key="hibernate.show_sql">true</prop>
            <prop key="hibernate.hbm2ddl.auto">update</prop>
        </props>
    </property>
    <property name="packagesToScan">
        <list>
            <value>com.example</value>
        </list>
    </property>
</bean>

<bean id="transactionManager" class="org.springframework.orm.hibernate5.HibernateTransactionManager">
    <property name="sessionFactory" ref="sessionFactory"/>
</bean>
```

### Example Usage

#### Entity Class

```java
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;

    // Getters and setters
}
```

#### DAO Class

```java
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    private final HibernateTemplate hibernateTemplate;

    public UserDao(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public void save(User user) {
        hibernateTemplate.save(user);
    }

    public User findById(Long id) {
        return hibernateTemplate.get(User.class, id);
    }
}
```

#### Service Class

```java
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public void createUser(User user) {
        userDao.save(user);
    }

    public User getUser(Long id) {
        return userDao.findById(id);
    }
}
```

### Use Cases

1. Data Persistence: Use Hibernate to map Java objects to database tables and perform CRUD operations.
2. Transaction Management: Manage transactions declaratively using Spring's @Transactional annotation.
3. Simplified Data Access: Use HibernateTemplate to handle session management and exception translation.

### Summary

Spring ORM (Hibernate) integrates the Hibernate framework with Spring, providing a powerful solution for managing data persistence in Java applications. It simplifies ORM configuration and transaction management, making it easier to work with databases through Java objects.

### Questions

1. What is Hibernate, and how does it relate to Spring ORM?
2. How does Spring ORM simplify transaction management with Hibernate?
3. What is the role of SessionFactory in Hibernate, and how is it configured in Spring?
4. Provide an example of configuring Hibernate in a Spring application.
5. How does HibernateTemplate simplify database interactions?
