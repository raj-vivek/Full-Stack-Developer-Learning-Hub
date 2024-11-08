# INNER JOIN

## Introduction

- An `INNER JOIN` is the most common type of join in SQL. It returns only those rows where there is a match between two tables based on the specified condition.
- If there are no matches, the result set will not include those rows. This is often used to retrieve records that have relationships across two or more tables.
- `INNER JOIN` is the default `JOIN`

---

## Syntax

```sql
SELECT columns
FROM table1
INNER JOIN table2
ON table1.column = table2.column;
```

- `table1`: The first table from which data is being selected.
- `table2`: The second table to be joined with the first.
- `ON`: The condition that specifies how to relate the two tables.

---

## Example Tables

Let's consider two example tables:

### Table: `Employees`

| EmployeeID | FirstName | LastName | DepartmentID |
| ---------- | --------- | -------- | ------------ |
| 1          | John      | Doe      | 1            |
| 2          | Jane      | Smith    | 2            |
| 3          | Mark      | Taylor   | NULL         |
| 4          | Lucy      | Brown    | 3            |

### Table: `Departments`

| DepartmentID | DepartmentName |
| ------------ | -------------- |
| 1            | HR             |
| 2            | IT             |
| 3            | Marketing      |
| 4            | Sales          |

## INNER JOIN Example

Now, let’s say we want to retrieve the list of employees along with their department names. We can do this using an `INNER JOIN` on the `DepartmentID` column.

### Query

```sql
SELECT Employees.EmployeeID, Employees.FirstName, Employees.LastName, Departments.DepartmentName
FROM Employees
INNER JOIN Departments
ON Employees.DepartmentID = Departments.DepartmentID;
```

### Result Set

| EmployeeID | FirstName | LastName | DepartmentName |
| ---------- | --------- | -------- | -------------- |
| 1          | John      | Doe      | HR             |
| 2          | Jane      | Smith    | IT             |
| 4          | Lucy      | Brown    | Marketing      |

- Notice that `Mark Taylor`, whose `DepartmentID` is `NULL`, is not included in the result. This is because `INNER JOIN` only returns rows that have matching values in both tables.

## Use Cases for INNER JOIN

- **Fetching related data**: When you need to combine rows from different tables that are related, such as retrieving employees and their respective departments.
- **Data Integrity**: Ensures that you only retrieve rows where a valid match exists in both tables, avoiding missing or irrelevant data.

### Common Use Case

Imagine you have an order management system with two tables: `Orders` and `Customers`. You can use an `INNER JOIN` to fetch all orders along with the customer details who placed the order.

```sql
SELECT Orders.OrderID, Customers.CustomerName
FROM Orders
INNER JOIN Customers
ON Orders.CustomerID = Customers.CustomerID;
```

## Important Points to Remember

- **No NULLs**: If a row in one table doesn’t have a corresponding match in the other, it won’t be included in the results.
- **Performance**: The efficiency of INNER JOIN can vary depending on the indexing of the tables. It is important to index the columns used in the ON clause for better performance.
- **Multiple Tables**: You can join more than two tables using multiple INNER JOIN clauses.

## Multiple INNER JOIN Example

You can join multiple tables in a single query using `INNER JOIN`.

### Example

```sql
SELECT Employees.EmployeeID, Employees.FirstName, Departments.DepartmentName, Projects.ProjectName
FROM Employees
INNER JOIN Departments ON Employees.DepartmentID = Departments.DepartmentID
INNER JOIN Projects ON Employees.ProjectID = Projects.ProjectID;
```

This query retrieves employees along with their department and project names.

## Best Practices

- **Indexing**: Ensure that the columns used in the ON clause (e.g., DepartmentID) are indexed for optimal performance.
- **Selective JOINs**: Use INNER JOIN when you want to exclude rows without matches from either table. If you need to retain unmatched rows, consider using LEFT JOIN or FULL OUTER JOIN.

## Conclusion

The `INNER JOIN` is fundamental in SQL, used for combining rows from multiple tables where there is a matching condition. It’s ideal when you want to retrieve only the related data across tables. However, remember that `INNER JOIN` excludes rows without matches, which can be advantageous for data integrity but might not be suitable for all cases.
