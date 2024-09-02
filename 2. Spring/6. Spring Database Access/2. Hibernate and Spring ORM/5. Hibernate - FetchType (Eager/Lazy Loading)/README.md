# Hibernate – FetchType

## Theory

**FetchType** in Hibernate defines how and when an associated entity should be fetched from the database. It is crucial for optimizing performance and managing resource usage.

### Key Concepts

1. **FetchType.LAZY**

   - **Description**: Entities are loaded on demand. The associated entities are not fetched immediately but are fetched only when they are accessed for the first time.
   - **Use Case**: Useful when dealing with large collections or entities where loading all associated entities at once is inefficient.
   - **Example**:

     ```java
     @Entity
     public class Employee {
         @Id
         @GeneratedValue(strategy = GenerationType.IDENTITY)
         private Long id;

         @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
         private List<Project> projects;

         // Getters and Setters
     }
     ```

2. **FetchType.EAGER**

   - **Description**: Entities are fetched immediately along with their parent entities. This means that all associated entities are loaded at the same time as the parent entity.
   - **Use Case**: Useful when associated entities are needed along with the parent entity, and the performance impact of loading all entities at once is acceptable.
   - **Example**:

     ```java
     @Entity
     public class Employee {
         @Id
         @GeneratedValue(strategy = GenerationType.IDENTITY)
         private Long id;

         @OneToMany(fetch = FetchType.EAGER, mappedBy = "employee")
         private List<Project> projects;

         // Getters and Setters
     }
     ```

### Default FetchType in Various Associations

1. One-to-One: Default is EAGER.
2. One-to-Many: Default is LAZY.
3. Many-to-One: Default is EAGER.
4. Many-to-Many: Default is LAZY.

### Use Cases

- **FetchType.LAZY**:

  - **Large Collections**: When dealing with large collections where fetching all records at once is inefficient.
  - **Performance Optimization**: Improves performance by loading only the necessary data.

- **FetchType.EAGER**:
  - **Immediate Data Requirement**: When associated data is required immediately and the performance impact is manageable.
  - **Complex Queries**: Useful in scenarios where complex queries need to be optimized with pre-loaded data.

### Questions

1. What are the differences between FetchType.LAZY and FetchType.EAGER?
2. How can FetchType.LAZY impact performance in large-scale applications?
3. In what scenarios would FetchType.EAGER be preferable over FetchType.LAZY?
4. How does FetchType impact the overall loading of associated entities in different types of relationships?
5. Provide an example where FetchType.LAZY would be beneficial and explain why.
