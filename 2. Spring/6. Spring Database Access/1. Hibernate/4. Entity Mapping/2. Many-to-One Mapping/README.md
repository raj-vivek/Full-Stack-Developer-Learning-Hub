# Hibernate – Many-to-One Mapping

## Theory

**Many-to-One Mapping** in Hibernate is a relationship where multiple records in one table are associated with a single record in another table. This is useful when one entity has multiple related entities, such as multiple orders associated with one customer.

### Notes

1. Entity Mapping Associations can be divided in to 2 types

- Non-collection type:

  - One-To-One
  - Many-To-One

- Collection Type:

  - One-To-Many
  - Many-To-Many

### Key Concepts

1. **Basic Many-to-One Mapping**

   - **Description**: Multiple instances of one entity are related to a single instance of another entity. This is often used to represent hierarchical or relational data.
   - **Example**:

     ```java
     @Entity
     public class Order {
         @Id
         @GeneratedValue(strategy = GenerationType.IDENTITY)
         private Long id;

         @ManyToOne(fetch = FetchType.LAZY)
         @JoinColumn(name = "customer_id")
         private Customer customer;

         // Getters and Setters
     }

     @Entity
     public class Customer {
         @Id
         @GeneratedValue(strategy = GenerationType.IDENTITY)
         private Long id;

         // Getters and Setters
     }
     ```

2. **Bidirectional Many-to-One Mapping**

   - **Description**: When both sides of the relationship are aware of each other, allowing navigation from either side.
   - **Example**:

     ```java
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

     @Entity
     public class Customer {
         @Id
         @GeneratedValue(strategy = GenerationType.IDENTITY)
         private Long id;

         @OneToMany(mappedBy = "customer")
         private List<Order> orders;

         // Getters and Setters
     }
     ```

## Use Cases

- **E-Commerce Systems**: Associating multiple orders with a single customer.
- **Customer Management**: Tracking multiple accounts or transactions for one client.
- **Project Management**: Managing multiple tasks assigned to one project manager or team.

### Questions

1. What is the difference between FetchType.LAZY and FetchType.EAGER in many-to-one mapping?
2. How do cascade types affect the persistence operations in a many-to-one relationship?
3. Provide an example of a scenario where many-to-one mapping would be beneficial.
4. How does bidirectional mapping differ from unidirectional mapping in Hibernate?
5. How would you handle a situation where a customer can have multiple orders but each order is linked to one customer only?
