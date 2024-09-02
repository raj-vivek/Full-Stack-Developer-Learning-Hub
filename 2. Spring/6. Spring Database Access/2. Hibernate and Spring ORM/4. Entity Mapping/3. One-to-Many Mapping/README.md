# Hibernate – One-to-Many Mapping

## Theory

**One-to-Many Mapping** in Hibernate represents a relationship where one entity is associated with multiple instances of another entity. This is useful when one record in a table is related to multiple records in another table.

### Key Concepts

1. **Basic One-to-Many Mapping**

   - **Description**: A single instance of one entity (parent) is associated with multiple instances of another entity (children). This is commonly used to model relationships where one entity has multiple related entities.
   - Child Table has no reference to parent in ORM. Note that the tables in database would remain the same. The child table (Order) will contain foreign key for parent table (Customer).
   - **Example**:

     ```java
     @Entity
     public class Customer {
         @Id
         @GeneratedValue(strategy = GenerationType.IDENTITY)
         private Long id;

         @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
         @JoinColumn(name = "customer_id") // Foreign key column in the child table
         private List<Order> orders;

         // Getters and Setters
     }

     @Entity
     public class Order {
         @Id
         @GeneratedValue(strategy = GenerationType.IDENTITY)
         private Long id;

         // Getters and Setters
     }
     ```

2. **Bidirectional One-to-Many Mapping**

   - **Description**: When both sides of the relationship are aware of each other, allowing navigation from either side.
   - **Example**:

     ```java
     @Entity
     public class Customer {
         @Id
         @GeneratedValue(strategy = GenerationType.IDENTITY)
         private Long id;

         @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
         private List<Order> orders;

         // Getters and Setters
     }

     @Entity
     public class Order {
         @Id
         @GeneratedValue(strategy = GenerationType.IDENTITY)
         private Long id;

         @ManyToOne
         @JoinColumn(name = "customer_id")
         private Customer customer;

         // Getters and Setters
     }
     ```

### Use Cases

- **E-Commerce Systems**: A customer can have multiple orders.
- **Project Management**: A project can have multiple tasks.
- **Library Systems**: An author can write multiple books.

### Questions

1. What is the difference between unidirectional and bidirectional one-to-many mapping?
2. How do FetchType.LAZY and FetchType.EAGER affect performance in one-to-many relationships?
3. Explain the impact of cascade types in one-to-many mapping.
4. Provide a use case where one-to-many mapping is essential.
5. How would you handle a situation where an order can be associated with multiple customers?
