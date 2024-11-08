# Basic SQL Syntax

Includes SQL Keywords, Data Types, Operators, and Statements

## 1. Introduction to SQL

SQL (Structured Query Language) is a domain-specific language used for managing and manipulating relational databases. SQL allows you to perform various operations like data retrieval, insertion, deletion, and updates. It also provides tools for creating and modifying database structures, controlling access to data, and ensuring data integrity.

---

## 2. SQL Keywords

SQL Keywords are reserved words used to perform specific functions in a database. These keywords are part of SQL syntax and have a special meaning.

### Common SQL Keywords:

| **Keyword**   | **Description**                                                                |
| ------------- | ------------------------------------------------------------------------------ |
| `SELECT`      | Retrieves data from one or more tables.                                        |
| `FROM`        | Specifies the table to retrieve data from.                                     |
| `WHERE`       | Filters records based on a condition.                                          |
| `INSERT INTO` | Adds new rows to a table.                                                      |
| `UPDATE`      | Modifies existing data within a table.                                         |
| `DELETE`      | Removes rows from a table.                                                     |
| `CREATE`      | Creates a new table, view, index, or other database objects.                   |
| `ALTER`       | Modifies an existing database object (e.g., table, column).                    |
| `DROP`        | Deletes a table or database object.                                            |
| `JOIN`        | Combines rows from two or more tables based on a related column between them.  |
| `GROUP BY`    | Groups rows that have the same values into summary rows.                       |
| `ORDER BY`    | Sorts the result set of a query by one or more columns.                        |
| `HAVING`      | Filters records based on conditions, but works with `GROUP BY`.                |
| `DISTINCT`    | Removes duplicate rows from a result set.                                      |
| `UNION`       | Combines the result set of two or more SELECT statements (removes duplicates). |
| `BETWEEN`     | Filters data within a specific range (inclusive).                              |
| `LIKE`        | Filters data based on a pattern.                                               |
| `IN`          | Filters data based on whether a value matches any value in a specified list.   |
| `LIMIT`       | Limits the number of rows returned by a query.                                 |
| `AS`          | Renames a column or table with an alias.                                       |
| `EXISTS`      | Tests for the existence of any record in a subquery.                           |

---

## 3. SQL Data Types

SQL data types define the type of data that can be stored in each column of a table. They ensure consistency and correctness when performing database operations.

### Common SQL Data Types:

#### a. Numeric Data Types:

| **Data Type**  | **Description**                                           | **Example**                  |
| -------------- | --------------------------------------------------------- | ---------------------------- |
| `INT`          | A standard integer value (4 bytes).                       | 123, -456                    |
| `SMALLINT`     | A smaller integer value (2 bytes).                        | 32767, -32768                |
| `BIGINT`       | A large integer value (8 bytes).                          | 9223372036854775807, -92233… |
| `DECIMAL(p,s)` | Fixed-point numbers with precision (`p`) and scale (`s`). | 123.45, -9876.54321          |
| `FLOAT`        | Approximate floating-point numbers.                       | 12.34, 56.789                |
| `NUMERIC(p,s)` | Exact numeric values with precision and scale.            | 456.789, -123.45             |

#### b. Character and String Data Types:

| **Data Type** | **Description**                                      | **Example**             |
| ------------- | ---------------------------------------------------- | ----------------------- |
| `CHAR(n)`     | Fixed-length string with length `n`.                 | 'Hello', 'A'            |
| `VARCHAR(n)`  | Variable-length string with a maximum length of `n`. | 'World', 'SQL Query'    |
| `TEXT`        | Stores large text data.                              | 'This is a large text.' |

#### c. Date and Time Data Types:

| **Data Type** | **Description**                                        | **Example**           |
| ------------- | ------------------------------------------------------ | --------------------- |
| `DATE`        | Stores date values (YYYY-MM-DD format).                | '2023-09-20'          |
| `TIME`        | Stores time values (HH:MM:SS format).                  | '12:30:45'            |
| `TIMESTAMP`   | Stores both date and time (YYYY-MM-DD HH:MM:SS format) | '2023-09-20 12:30:45' |
| `DATETIME`    | Stores both date and time values.                      | '2023-09-20 14:15:00' |

#### d. Boolean Data Types:

| **Data Type** | **Description**              | **Example** |
| ------------- | ---------------------------- | ----------- |
| `BOOLEAN`     | Stores TRUE or FALSE values. | TRUE, FALSE |

#### e. Binary Data Types:

| **Data Type** | **Description**                          | **Example**             |
| ------------- | ---------------------------------------- | ----------------------- |
| `BLOB`        | Binary Large Object, stores binary data. | Used for images, files. |

---

## 4. SQL Operators

SQL Operators are used to perform operations on data in SQL queries. These include arithmetic, comparison, logical, and bitwise operators.

### a. Arithmetic Operators:

| **Operator** | **Description**                            | **Example**                  |
| ------------ | ------------------------------------------ | ---------------------------- |
| `+`          | Addition of two values.                    | `SELECT 5 + 3;` (Result: 8)  |
| `-`          | Subtraction of two values.                 | `SELECT 5 - 2;` (Result: 3)  |
| `*`          | Multiplication of two values.              | `SELECT 5 * 3;` (Result: 15) |
| `/`          | Division of two values.                    | `SELECT 10 / 2;` (Result: 5) |
| `%`          | Modulo, returns the remainder of division. | `SELECT 10 % 3;` (Result: 1) |

### b. Comparison Operators:

| **Operator** | **Description**                        | **Example**                                           |
| ------------ | -------------------------------------- | ----------------------------------------------------- |
| `=`          | Checks for equality.                   | `SELECT * FROM Users WHERE Age = 30;`                 |
| `<>` or `!=` | Checks for inequality.                 | `SELECT * FROM Users WHERE Age <> 30;`                |
| `>`          | Greater than comparison.               | `SELECT * FROM Products WHERE Price > 100;`           |
| `<`          | Less than comparison.                  | `SELECT * FROM Products WHERE Price < 100;`           |
| `>=`         | Greater than or equal to.              | `SELECT * FROM Orders WHERE Quantity >= 10;`          |
| `<=`         | Less than or equal to.                 | `SELECT * FROM Orders WHERE Quantity <= 10;`          |
| `BETWEEN`    | Checks if a value lies within a range. | `SELECT * FROM Sales WHERE Price BETWEEN 50 AND 100;` |

### c. Logical Operators:

| **Operator** | **Description**                                                           | **Example**                                                 |
| ------------ | ------------------------------------------------------------------------- | ----------------------------------------------------------- |
| `AND`        | Combines multiple conditions and returns true if all conditions are true. | `SELECT * FROM Users WHERE Age > 30 AND Country = 'India';` |
| `OR`         | Combines multiple conditions and returns true if any condition is true.   | `SELECT * FROM Users WHERE Age > 30 OR Country = 'India';`  |
| `NOT`        | Reverses the result of the condition.                                     | `SELECT * FROM Users WHERE NOT Age > 30;`                   |

---

## 5. SQL Statements

SQL Statements are instructions to perform operations on a database. These include creating, modifying, querying, and deleting data or structures.

### a. **Data Definition Language (DDL) Statements**

1. **CREATE**: Used to create database objects like tables, views, indexes, etc.

   ```sql
   CREATE TABLE Customers (
       CustomerID INT PRIMARY KEY,
       Name VARCHAR(100),
       Address VARCHAR(200)
   );
   ```

2. **ALTER**: Used to modify an existing database object.

   ```sql
   ALTER TABLE Customers ADD Email VARCHAR(100);
   ```

3. **DROP**: Used to delete a database object.

   ```sql
   DROP TABLE Customers;
   ```

4. **TRUNCATE**: To delete all rows from a table without deleting the table itself.
    ```sql
    TRUNCATE TABLE users, orders CASCADE;
    ```

### b. Data Manipulation Language (DML) Statements

1. **SELECT**: Retrieves data from the database.

   ```sql
   SELECT * FROM Customers WHERE Country = 'USA';
   ```

2. **INSERT INTO**: Inserts new data into a table.

   ```sql
   INSERT INTO Customers (CustomerID, Name, Address) VALUES (1, 'John Doe', '123 Main St');
   ```

3. **UPDATE**: Modifies existing data in a table.

   ```sql
   UPDATE Customers SET Address = '456 Elm St' WHERE CustomerID = 1;
   ```

4. **DELETE**: Removes data from a table.

   ```sql
   DELETE FROM Customers WHERE CustomerID = 1;
   ```

### c. Data Control Language (DCL) Statements

1. **GRANT**: Gives a user specific privileges to perform actions on the database.

   ```sql
   GRANT SELECT, INSERT ON Customers TO 'username';
   ```

2. **REVOKE**: Removes user access rights or privileges to the database.

   ```sql
   REVOKE INSERT ON Customers FROM 'username';
   ```

### d. Transaction Control Language (TCL) Statements

1. **BEGIN TRANSACTION**: Starts a new transaction.

   ```sql
   BEGIN TRANSACTION;
   ```

2. **COMMIT**: Saves the current transaction permanently in the database.

   ```sql
   COMMIT;
   ```

3. **ROLLBACK**: Undoes changes made in the current transaction.

   ```sql
   ROLLBACK;
   ```

### e. Conditional Statements

1. **CASE**: Provides conditional logic in SQL queries (similar to if-else logic).

   ```sql
   SELECT CustomerID, Name,
       CASE
           WHEN Country = 'USA' THEN 'Domestic'
           ELSE 'International'
       END AS CustomerType
   FROM Customers;
   ```

## Conclusion

Understanding SQL keywords, data types, operators, and statements is essential for efficiently working with relational databases. SQL provides powerful tools for querying, modifying, and managing structured data. With this knowledge, you can interact with databases in various scenarios, ensuring proper data management and integrity.
