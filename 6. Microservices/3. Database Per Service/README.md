# Database per Microservice in Microservices Architecture

## 1. Introduction

In a **microservices architecture**, each microservice is designed to be an independently deployable unit. This independence extends not only to the code but also to the database layer. The **Database per Microservice** pattern ensures that each microservice manages its own data, enforcing strong service boundaries and data encapsulation.

In this approach:

- Each microservice has its **own database** (schema or instance).
- Microservices do not share databases, and there is no direct access to another microservice's database.
- Communication between microservices is handled via **APIs** (e.g., REST, gRPC), not direct database queries.

---

## 2. Why Database per Microservice?

### a. Independence and Autonomy

By providing each microservice with its own database, the service can operate and scale independently, without coupling or being dependent on other services. Each service is free to use the database technology best suited to its own needs (e.g., SQL, NoSQL, in-memory databases).

### b. Loose Coupling

A core principle of microservices is **loose coupling**. Sharing databases between microservices can lead to tight coupling, where services are reliant on the same schema and changes in one service can affect others. Having a separate database avoids these dependencies.

### c. Scalability

Each microservice, along with its database, can be scaled independently based on its demand. Services that require more storage or processing power can be scaled vertically or horizontally without affecting the entire system.

---

## 3. Advantages of Database per Microservice

| Advantage                   | Description                                                                   |
| --------------------------- | ----------------------------------------------------------------------------- |
| **Data Isolation**          | Each microservice handles its own data, ensuring complete data encapsulation. |
| **Independent Scaling**     | Databases can be scaled individually based on the microservice's needs.       |
| **Technology Flexibility**  | Each microservice can choose the database technology that suits its use case. |
| **Fault Isolation**         | Failures in one microservice's database don't directly affect others.         |
| **Deployment Independence** | Each microservice can evolve its database schema without affecting others.    |

---

## 4. Challenges of Database per Microservice

### a. Data Consistency

When each microservice has its own database, ensuring **consistency** between services becomes a challenge, especially in distributed systems. Transactions that span multiple microservices are difficult to manage.

### b. Data Duplication

Some degree of **data duplication** may be required. For example, if two microservices need to share certain data, they may store copies in their own databases, increasing storage needs.

### c. Cross-Service Queries

Direct **cross-service queries** are not possible, which makes querying and retrieving data from multiple services more complicated. Microservices should communicate through APIs, which adds latency compared to direct database queries.

### d. Complex Management

Managing multiple databases for a large number of microservices increases the operational complexity. Each database needs to be monitored, maintained, and backed up independently.

---

## 5. Patterns for Managing Data Across Microservices

Given the challenges posed by the **Database per Microservice** pattern, several design patterns have emerged to handle data management effectively.

### a. Event-Driven Architecture

In an **event-driven architecture**, services communicate by emitting and listening for events. This pattern helps keep data in sync across different microservices without tightly coupling them.

For example, if a "Customer" microservice creates a new customer record, it can emit a **"CustomerCreated"** event that other microservices (e.g., Order or Billing) can listen to and update their databases accordingly.

### b. API Composition

In cases where you need to **aggregate data** from multiple microservices, an **API Composition** pattern can be used. A **composite service** is responsible for calling multiple microservices and aggregating the results.

```java
@RestController
public class OrderController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/order/{id}")
    public OrderDetails getOrderDetails(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        Payment payment = paymentService.getPaymentByOrderId(id);
        return new OrderDetails(customer, payment);
    }
}
```

### c. Sagas

Sagas are a distributed transaction pattern that helps ensure data consistency across microservices. In a saga, each step in a distributed transaction is a local transaction that updates its own database. If any step fails, compensating actions are executed to roll back the changes.

There are two types of sagas:

- **Choreography-based sagas**: Each service emits events and reacts to events emitted by other services.
- **Orchestration-based sagas**: A central orchestrator service controls the flow of transactions across multiple services.

### d. CQRS (Command Query Responsibility Segregation)

CQRS separates read and write operations for a service. Writes are handled by one model (commands), while reads are handled by another model (queries). In microservices, CQRS can be used to optimize performance, with separate databases for queries and updates.

## 6. Database Design in Microservices

### a. Polyglot Persistence

Microservices can take advantage of polyglot persistence, where each service uses the most appropriate database technology for its data requirements. For instance:

- A microservice managing user profiles may use a document-based database (e.g., MongoDB).
- A microservice handling transactions may use a relational database (e.g., PostgreSQL).

### b. Schema Evolution

As microservices evolve, their underlying database schemas may also need to evolve. Unlike monolithic applications, where a schema change might require the entire application to be redeployed, microservices allow for schema evolution at the service level, reducing impact on the rest of the system.

### c. Data Ownership

Each microservice owns its own data and should be the only one allowed to modify it. Other microservices can only access this data through well-defined APIs, ensuring data encapsulation.

## 7. Case Study: Example of Database per Microservice

Consider an e-commerce platform where different microservices handle various functionalities, such as inventory management, order processing, and payment processing. Each of these services has its own database:

| Microservice      | Database Technology | Data                               |
| ----------------- | ------------------- | ---------------------------------- |
| Inventory Service | MongoDB             | Product stock, warehouse locations |
| Order Service     | PostgreSQL          | Customer orders, order statuses    |
| Payment Service   | MySQL               | Payment transactions, invoices     |

In this system:

- The Inventory Service can update the stock for a product after an order is placed, but it does so by emitting an event that the Order Service listens to, rather than accessing the Order Service's database directly.
- The Payment Service emits a "PaymentCompleted" event after processing a payment, which the Order Service listens to in order to update the order status.

This architecture allows each service to scale independently and minimizes the risk of failures cascading across the system.

## 8. Advantages and Disadvantages Summary

### Advantages

- **Independence and Isolation**: Each service operates independently without being affected by others.
- **Scalability**: Microservices and their databases can be scaled individually.
- **Fault Tolerance**: A failure in one service or its database does not directly impact other services.
- **Technology Flexibility**: Each service can choose the database technology that fits its needs.
- **Data Ownership**: Each service owns and manages its own data, ensuring encapsulation and autonomy.

## Disadvantages

- **Data Consistency Challenges**: Maintaining data consistency across services is complex without distributed transactions.
- **Data Duplication**: Some data might need to be replicated across multiple services.
- **Increased Operational Complexity**: Managing multiple databases increases the operational overhead (e.g., backups, monitoring, scaling).
- **Cross-Service Queries**: No direct database queries across services, which adds complexity to querying aggregated data.

## 9. Conclusion

The Database per Microservice pattern is essential for achieving true service autonomy in a microservices architecture. It ensures that microservices are decoupled not just at the API level but also at the database layer. While it introduces challenges like data consistency and operational complexity, the advantages in terms of scalability, fault isolation, and technology flexibility make it a crucial pattern for building robust and scalable microservices-based systems.
