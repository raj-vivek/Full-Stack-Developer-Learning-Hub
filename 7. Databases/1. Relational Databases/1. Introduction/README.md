# Relational Databases

## 1. Introduction

A **Relational Database** is a type of database that stores and organizes data in predefined tables (also called relations). Each table contains rows (records) and columns (fields) that define data types and the relationships between them. Relational databases rely on **Structured Query Language (SQL)** for querying and managing data.

Relational databases are based on **relational algebra**, where relationships between data entities are represented using keys, like **primary keys** and **foreign keys**.

---

## 2. Key Concepts of Relational Databases

### a. **Tables**

- A Relational Database has multiple tables.
- Each table represents an entity or a relationship between entities.
- A table consists of **Rows (Records)** and **Columns (Fields)**:

### b. **Primary Key**

- A **Primary Key** is a unique identifier for each record in a table.

#### Example:

```sql
CREATE TABLE Employees (
    EmployeeID INT PRIMARY KEY,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    Age INT
);
```

### c. Foreign Key

- A **Foreign Key** is a field (or collection of fields) in one table that uniquely identifies a row in another table.
- It establishes a relationship between two tables and enforces referential integrity.

#### Example:

```sql
CREATE TABLE Orders (
    OrderID INT PRIMARY KEY,
    OrderDate DATE,
    EmployeeID INT,
    FOREIGN KEY (EmployeeID) REFERENCES Employees(EmployeeID)
);
```

### d. Relationships

1. **One-to-One**: A record in one table is related to exactly one record in another table.
2. **One-to-Many**: A record in one table can relate to multiple records in another table.
3. **Many-to-Many**: Records in one table can be related to multiple records in another table, and vice versa. Many-to-many relationships require an intermediary table.

### e. Normalization

- **Normalization** is a process to organize data in a relational database to minimize redundancy and ensure data integrity. It involves dividing large tables into smaller, related tables.

The key stages of normalization are:

1. **1st Normal Form (1NF)**: Eliminate repeating groups in tables.
2. **2nd Normal Form (2NF)**: Ensure all non-key attributes are fully dependent on the primary key.
3. **3rd Normal Form (3NF)**: Remove transitive dependencies (where non-key attributes depend on other non-key attributes).

## 3. Structured Query Language (SQL)

- SQL is the standard language for managing and manipulating relational databases.

SQL commands are categorized into four types:

### a. Data Definition Language (DDL)

1. **CREATE**: To create tables and other database objects.
2. **ALTER**: To modify existing tables or objects.
3. **DROP**: To delete tables or objects.
4. **TRUNCATE**: To delete all rows from a table without deleting the table itself.

```sql
CREATE TABLE Customers (
    CustomerID INT PRIMARY KEY,
    CustomerName VARCHAR(100),
    ContactName VARCHAR(100)
);
```

### b. Data Manipulation Language (DML)

1. **SELECT**: To retrieve data from tables.
2. **INSERT**: To add new rows to a table.
3. **UPDATE**: To modify existing records.
4. **DELETE**: To remove rows from a table.

```sql
SELECT * FROM Customers;
INSERT INTO Customers (CustomerID, CustomerName, ContactName) VALUES (1, 'Acme Inc', 'John Doe');
```

### c. Data Control Language (DCL)

1. **GRANT**: To provide privileges to users.
2. **REVOKE**: To withdraw privileges from users.

```sql
GRANT SELECT, INSERT ON Customers TO 'john_doe';
```

### d. Transaction Control Language (TCL)

1. **COMMIT**: To save changes made in a transaction.
2. **ROLLBACK**: To undo changes made in a transaction.
3. **SAVEPOINT**: To set a savepoint within a transaction.

```sql
BEGIN TRANSACTION;
UPDATE Orders SET Status = 'Shipped' WHERE OrderID = 1;
ROLLBACK;
```

## 4. ACID Properties

Relational databases ensure data integrity and consistency through ACID properties:

- **Atomicity**: Ensures that all operations in a transaction are completed successfully or none at all.
- **Consistency**: Ensures the database remains in a valid state before and after the transaction.
- **Isolation**: Ensures transactions are executed independently without interference from other concurrent transactions.
- **Durability**: Ensures that once a transaction is committed, its changes are permanently saved, even in case of a system failure.

## 5. Indexing in Relational Databases

Indexing improves the speed of data retrieval by creating a data structure that allows faster lookups.

- **Clustered Index**: Alters the physical order of the data. A table can have only one clustered index.
- **Non-clustered Index**: Does not alter the physical order of the data but creates a logical order for faster searches. A table can have multiple non-clustered indexes.

#### Example:

```sql
CREATE INDEX idx_customer_name ON Customers(CustomerName);
```

## 6. Advantages of Relational Databases

- **Data Integrity**: Relationships between data are clearly defined and enforced, ensuring consistency.
- **Ease of Use**: SQL is a widely adopted language with well-established standards and community support.
- **ACID Compliance**: Relational databases offer strong consistency and transaction support, making them ideal for critical systems like financial applications.
- **Security**: Granular access control through roles and permissions.

## 7. Disadvantages of Relational Databases

- **Scalability**: Relational databases are vertically scalable (scale-up), which can limit their use in very large systems requiring horizontal scalability (scale-out).
- **Fixed Schema**: Rigid schema design can be limiting in scenarios where the data model frequently evolves.
- **Performance**: Handling large amounts of unstructured or semi-structured data can cause performance bottlenecks.

## 8. Popular Relational Databases

### a. MySQL

- **Type**: Open-source relational database management system (RDBMS).
- **Key Features**: High performance, easy-to-use, ACID compliance with InnoDB engine, widely used in web development.

### b. PostgreSQL

- **Type**: Open-source object-relational database system.
- **Key Features**: Strong emphasis on extensibility, supports advanced data types (e.g., JSON), high concurrency.

### c. Oracle Database

- **Type**: Commercial RDBMS.
- **Key Features**: High reliability, support for large-scale enterprise systems, advanced features like partitioning and clustering.

### d. Microsoft SQL Server

- **Type**: Commercial RDBMS.
- **Key Features**: Excellent integration with Microsoft products, comprehensive BI (Business Intelligence) tools, and high security.

## 9. Conclusion

Relational databases remain a cornerstone of enterprise systems, providing strong consistency, data integrity, and ease of use. They are best suited for structured data and systems that require complex transactions. While scalability and flexibility can be challenges, innovations such as cloud-hosted relational databases (e.g., Amazon RDS) are helping to bridge these gaps.
