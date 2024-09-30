# Hibernate – One-to-One Mapping

## Theory

**One-to-One Mapping** in Hibernate is a relationship where each record in one table is associated with exactly one record in another table. This mapping is typically used to model scenarios where two entities have a unique relationship, such as a person and their passport.

### Key Concepts

1. **Bidirectional One-to-One Mapping**

   - **Description**: Both entities in the relationship reference each other. This is useful when you need to navigate the relationship from either side.
   - **Example**:

     ```java
     @Entity
     public class Person {
         @Id
         @GeneratedValue(strategy = GenerationType.IDENTITY)
         private Long id;

         @OneToOne(mappedBy = "person")
         private Passport passport;

         // Getters and Setters
     }

     @Entity
     public class Passport {
         @Id
         @GeneratedValue(strategy = GenerationType.IDENTITY)
         private Long id;

         @OneToOne
         @JoinColumn(name = "person_id")
         private Person person;

         // Getters and Setters
     }
     ```

     - `@JoinColumn` and `mappedBy` parameter work together

       1. **`@JoinColumn`** annotation specifies the column for joining the target entity, creating a new column for foreign key.

       2. **`mappedBy`** parameter is used to indicate that the given column is owned by another entity.

          - The mappedBy attribute in JPA (Java Persistence API) is used to define the inverse side of a bidirectional relationship.
          - When you use mappedBy, it indicates that the relationship is already defined by the other entity, and therefore, it does not create a new column in the database.
          - `mappedBy = "person"` tells Hibernate to look for a field named `person` in the `Passport` class and link that particular instance to the current student object.

2. **Unidirectional One-to-One Mapping**

   - **Description**: Only one entity has a reference to the other. This is simpler but less flexible if bidirectional navigation is required.
   - **Example**:

     ```java
     @Entity
     public class Person {
         @Id
         @GeneratedValue(strategy = GenerationType.IDENTITY)
         private Long id;

         @OneToOne
         @JoinColumn(name = "passport_id")
         private Passport passport;

         // Getters and Setters
     }

     @Entity
     public class Passport {
         @Id
         @GeneratedValue(strategy = GenerationType.IDENTITY)
         private Long id;

         // No reference to Person

         // Getters and Setters
     }
     ```

3. **Cascade Types**

   - **Description**: Determines how operations are propagated between the parent and child entities.
   - Used to manage the state of the target entity whenever the state of the parent entity changes.
   - Common cascade types for one-to-one mapping include -
     1. `CascadeType.ALL`
        - All operations (persist, merge, remove, refresh, detach) performed on the parent entity will be propagated to the target entity.
     2. `CascadeType.PERSIST`
        - Propagates persist from parent to target entity.
     3. `CascadeType.MERGE`
        - Propagates merge from parent to target entity.
     4. `CascadeType.REMOVE`
        - If the parent entity is deleted from the database then the target entity will also be deleted from the database.
     5. `CascadeType.REFRESH`
        - Propagates refresh from parent to target entity.
     6. `CascadeType.DETACH`
        - Propagates detach from parent to target entity
   - **Example**:

     ```java
     @Entity
     public class Person {
         @Id
         @GeneratedValue(strategy = GenerationType.IDENTITY)
         private Long id;

         @OneToOne(cascade = CascadeType.ALL)
         @JoinColumn(name = "passport_id")
         private Passport passport;

         // Getters and Setters
     }
     ```

4. **Fetch Types**

   - **Description**: Defines when the associated entity should be loaded.
   - Common fetch types include
     1. `FetchType.LAZY`: The associated entity will be fetched only when it is accessed for the first time.
     2. `FetchType.EAGER`: The associated entity will be fetched together with the main entity when the main entity is fetched from the database.
   - **Example**:

     ```java
     @Entity
     public class Person {
         @Id
         @GeneratedValue(strategy = GenerationType.IDENTITY)
         private Long id;

         @OneToOne(fetch = FetchType.LAZY)
         @JoinColumn(name = "passport_id")
         private Passport passport;

         // Getters and Setters
     }
     ```

## Use Cases

- **User Profiles**: Modeling user profiles where each user has a unique profile picture or additional details.
- **Business Entities**: Handling entities like a company and its headquarters where each company has one headquarters.
- **Administrative Systems**: Managing administrative records where each record corresponds to a single, unique administrative document.

## Example

### Bidirectional One-to-One Mapping

**Person.java**

```java
import javax.persistence.*;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Passport passport;

    // Getters and Setters
}
```

**Passport.java**

```java
import javax.persistence.*;

@Entity
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;

    // Getters and Setters
}
```

### Unidirectional One-to-One Mapping

**Person.java**

```java
import javax.persistence.*;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "passport_id")
    private Passport passport;

    // Getters and Setters
}
```

**Passport.java**

```java
import javax.persistence.*;

@Entity
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // No reference to Person

    // Getters and Setters
}
```

### Questions

1. What is the difference between bidirectional and unidirectional one-to-one mapping in Hibernate?
2. How do you configure cascade types for a one-to-one relationship in Hibernate?
3. What are the advantages of using FetchType.LAZY over FetchType.EAGER in one-to-one mapping?
4. Provide an example of a use case for one-to-one mapping in a business application.
5. How can you handle bidirectional relationships when using JPA and Hibernate?
