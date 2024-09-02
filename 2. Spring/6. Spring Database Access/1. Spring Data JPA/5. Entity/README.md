# Spring Data JPA - @Entity Annotation

## Theory

The `@Entity` annotation in Spring Data JPA is used to mark a Java class as an entity, which means it is mapped to a database table. Entities represent the data model of an application and allow interaction with the database through JPA. Along with `@Entity`, there are several other annotations that help define how an entity is mapped to the database table, such as `@Table`, `@Column`, and `@Id`.

### Core Annotations for Entities

1. **@Entity**

   - Marks the class as an entity, making it eligible for persistence operations.
   - Must be used in conjunction with `@Id` to define the primary key of the entity.

     ```java
     @Entity
     public class User {
         @Id
         @GeneratedValue(strategy = GenerationType.IDENTITY)
         private Long id;

         private String username;

         private String email;
     }
     ```

2. **@Table**

   - Specifies the table name in the database that the entity maps to.
   - If omitted, the table name defaults to the name of the entity class.

     ```java
     @Entity
     @Table(name = "users")
     public class User {
         @Id
         @GeneratedValue(strategy = GenerationType.IDENTITY)
         private Long id;

         @Column(name = "username", nullable = false, unique = true)
         private String username;

         @Column(name = "email", nullable = false)
         private String email;
     }
     ```

3. **@Column**

   - Maps a field of the entity to a column in the database table.
   - Allows customization of column properties such as name, nullable, unique, length, etc.

     ```java
     @Entity
     public class User {
         @Id
         @GeneratedValue(strategy = GenerationType.IDENTITY)
         private Long id;

         @Column(name = "username", nullable = false, unique = true, length = 50)
         private String username;

         @Column(name = "email", nullable = false)
         private String email;
     }
     ```

4. **@Id**

   - Specifies the primary key field of the entity.
   - Every entity must have a field annotated with @Id to serve as the unique identifier.

     ```java
     @Entity
     public class User {
         @Id
         @GeneratedValue(strategy = GenerationType.IDENTITY)
         private Long id;

         private String username;

         private String email;
     }
     ```

### Advanced Features and Configuration

1. Entity Lifecycle:

   - Lifecycle Callbacks: You can use annotations like @PrePersist, @PostPersist, @PreUpdate, @PostUpdate, @PreRemove, and @PostRemove to define callback methods that execute before or after certain lifecycle events.

1. Inheritance Mapping:

Single Table Inheritance: Use @Inheritance(strategy = InheritanceType.SINGLE_TABLE) to map a class hierarchy to a single table.
Table Per Class Inheritance: Use @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) to map each class in the hierarchy to its own table.
Joined Table Inheritance: Use @Inheritance(strategy = InheritanceType.JOINED) to map each class to its own table, with relationships between tables.

1. Primary Key Generation:

Generation Strategies: Define primary key generation using @Id and @GeneratedValue. Strategies include AUTO, IDENTITY, SEQUENCE, and TABLE.
Custom Generators: Implement custom primary key generators by implementing org.hibernate.id.IdentifierGenerator.

1. Entity Relationships:

One-to-One: Use @OneToOne to define one-to-one relationships. You can also use @MapsId to share the primary key.
One-to-Many / Many-to-One: Use @OneToMany and @ManyToOne to define one-to-many and many-to-one relationships. Use @JoinColumn to specify the foreign key.
Many-to-Many: Use @ManyToMany to define many-to-many relationships. Typically, you'll need a join table to manage the association.

1. Caching:

Second-Level Cache: Configure second-level cache for entities using annotations like @Cache and configure caching providers like EHCache or Infinispan.
Query Cache: Use @Cacheable and @CachePut annotations to cache the results of queries.

1. Concurrency Control:

Optimistic Locking: Use @Version to enable optimistic locking by tracking entity versions.
Pessimistic Locking: Use the javax.persistence.LockModeType enumeration in queries to apply pessimistic locking.

1. Auditing:

Entity Auditing: Use the @CreatedDate, @LastModifiedDate, @CreatedBy, and @LastModifiedBy annotations (from Spring Data JPA) to automatically track entity changes.

1. Custom Entity Listeners:

Entity Listener Classes: Define custom entity listeners using @EntityListeners to handle specific entity events.

1. Embedded Types:

@Embeddable and @Embedded: Use @Embeddable to define reusable value types and @Embedded to include them in your entities.

1. Validation:

Bean Validation: Integrate with JSR-380 (Bean Validation 2.0) using annotations like @NotNull, @Size, @Pattern to enforce constraints on entity fields.

### Example

```java
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Version
    private Long version;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @PrePersist
    @PreUpdate
    private void beforeAnyUpdate() {
        // Some logic before persisting or updating
    }

    @PostPersist
    private void afterPersist() {
        // Some logic after persisting
    }

    // Getters and Setters

}
```

### Questions

1. What is the purpose of the @Entity annotation in Spring Data JPA?
2. How does the @Table annotation affect entity mapping?
3. What does the @Column annotation do, and how can it be customized?
4. Why is the @Id annotation important for entities?
5. Provide an example of using @Entity, @Table, @Column, and @Id in a Spring Data JPA entity.
