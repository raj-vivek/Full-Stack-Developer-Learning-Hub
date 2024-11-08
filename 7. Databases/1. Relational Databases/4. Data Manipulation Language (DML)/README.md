# Data Manipulation Language (DML)

## Introduction

Data Manipulation Language (DML) is a subset of SQL used to manipulate and manage the data stored in relational databases. It provides the commands necessary for inserting, updating, deleting, and retrieving data from database tables. Unlike Data Definition Language (DDL), which focuses on the structure of the database, DML is primarily concerned with the data itself.

DML commands can be classified into two categories:

1. **Procedural DML**: Requires a user to specify what data is needed and how to get it.
2. **Declarative DML**: Requires a user to specify what data is needed without detailing how to get it.

This documentation focuses on declarative DML commands, which are more commonly used in SQL.

The main commands are -

1. **SELECT**
2. **INSERT**
3. **UPDATE**
4. **DELETE**

And related keywords used are

1. `FROM`
2. `WHERE`
3. `JOIN`s
4. `GROUP BY`
5. `ORDER BY`
6. `HAVING`
7. Aggregate Queries
   - `COUNT`
   - `SUM`
   - `AVG`
   - `MIN`
   - `MAX`

### Examples

1. Find out the total sales, average order value, and the number of orders per customer in the 'USA' for the year 2023, only including customers who placed more than 5 orders.

   ```sql
   SELECT
       c.customer_id,
       c.customer_name,
       COUNT(o.order_id) AS number_of_orders,
       SUM(o.order_amount) AS total_sales,
       AVG(o.order_amount) AS average_order_value
   FROM
       Customers c
   JOIN
       Orders o ON c.customer_id = o.customer_id
   WHERE
       c.country = 'USA'
       AND YEAR(o.order_date) = 2023
   GROUP BY
       c.customer_id, c.customer_name
   HAVING
       COUNT(o.order_id) > 5
   ORDER BY
       total_sales DESC;
   ```

2. Find the highest and lowest review scores for each department in the year 2023, along with the average review score, only including departments with at least 3 employees and where the average score is greater than 75.

   ```sql
   SELECT
       d.department_id,
       d.department_name,
       MAX(pr.review_score) AS highest_score,
       MIN(pr.review_score) AS lowest_score,
       AVG(pr.review_score) AS average_score
   FROM
       Departments d
   JOIN
       Employees e ON d.department_id = e.department_id
   JOIN
       PerformanceReviews pr ON e.employee_id = pr.employee_id
   WHERE
       pr.review_year = 2023
   GROUP BY
       d.department_id, d.department_name
   HAVING
       COUNT(e.employee_id) >= 3
       AND AVG(pr.review_score) > 75
   ORDER BY
       average_score DESC;
   ```

---

## Key DML Commands

### 1. **INSERT**

The `INSERT` command is used to add new rows of data into a table. It can insert data into specific columns or all columns of the table.

#### a. Inserting Data into All Columns

When inserting data into all columns, the order of values must match the order of the columns in the table.

```sql
INSERT INTO Employees (EmployeeID, FirstName, LastName, Email, HireDate, Salary)
VALUES (1, 'John', 'Doe', 'john.doe@example.com', '2023-01-15', 60000.00);
```

#### b. Inserting Data into Specific Columns

You can specify only the columns you want to insert data into. If any columns are omitted, they must have default values or allow nulls.

```sql
INSERT INTO Employees (FirstName, LastName, Salary)
VALUES ('Jane', 'Smith', 65000.00);
```

### 2. SELECT

The SELECT command retrieves data from one or more tables. You can specify which columns to return, apply conditions, and sort the results.

#### a. Basic SELECT Statement

```sql
SELECT FirstName, LastName FROM Employees;
```

#### b. Selecting with Conditions using `WHERE`

You can use the WHERE clause to filter results based on specific criteria.

```sql
SELECT * FROM Employees WHERE Salary > 60000;
```

#### c. Sorting Results using `ORDER BY`

The ORDER BY clause is used to sort the results by one or more columns.

```sql
SELECT * FROM Employees ORDER BY HireDate DESC;
```

#### d. Using Aggregate Functions

Aggregate functions like COUNT, SUM, AVG, MIN, and MAX can be used to perform calculations on data.

```sql
SELECT COUNT(*) AS NumberOfEmployees FROM Employees;
SELECT AVG(Salary) AS AverageSalary FROM Employees;
```

### 3. UPDATE

The UPDATE command modifies existing data in a table. It requires the SET clause to specify the columns to be updated and their new values.

#### a. Basic Update Statement

```sql
UPDATE Employees
SET Salary = 70000
WHERE EmployeeID = 1;
```

#### b. Updating Multiple Columns

You can update multiple columns in a single statement by separating them with commas.

```sql
UPDATE Employees
SET Salary = 75000, Email = 'john.new@example.com'
WHERE EmployeeID = 1;
```

### 4. DELETE

The DELETE command removes existing rows from a table. It requires a WHERE clause to specify which rows to delete; otherwise, all rows will be deleted.

#### a. Basic Delete Statement

```sql
DELETE FROM Employees WHERE EmployeeID = 1;
```

#### b. Deleting All Rows

To delete all rows in a table without dropping the table itself, use the DELETE statement without a WHERE clause. This should be done with caution.

```sql
DELETE FROM Employees;
```

### 5. MERGE

The MERGE command (also known as "upsert") allows you to perform insert or update operations in a single statement. It is useful for synchronizing two tables.

```sql
MERGE INTO target_table AS target
USING source_table AS source
ON target.id = source.id
WHEN MATCHED THEN
    UPDATE SET target.column = source.column
WHEN NOT MATCHED THEN
    INSERT (id, column) VALUES (source.id, source.column);
```

## Best Practices for Using DML

1. **Always Use Transactions**: Wrap DML operations in transactions to maintain data integrity and allow rollbacks in case of errors.

   ```sql
   BEGIN TRANSACTION;
   -- DML operations
   COMMIT; -- or ROLLBACK; in case of error
   ```

2. **Use WHERE Clauses with UPDATE and DELETE**: Always specify a WHERE clause to prevent unintentional updates or deletions of all rows.

3. **Backup Before Major Changes**: Regularly back up data before executing large DML operations to prevent data loss.

4. **Limit Results with SELECT**: Use `LIMIT` (or `FETCH FIRST n ROWS ONLY` in some databases) to restrict the number of rows returned by `SELECT`, especially for large datasets.

5. **Normalize Data**: Ensure the data is normalized to reduce redundancy and improve data integrity.

## Conclusion

Data Manipulation Language (DML) is a critical component of SQL that allows users to interact with and manage data within relational databases. Mastery of DML commands is essential for any database developer or administrator, as it forms the foundation for data management and retrieval operations in applications.
