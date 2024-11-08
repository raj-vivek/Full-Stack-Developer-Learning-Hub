# SQL Outer Joins

## Introduction

In SQL, outer joins are used to return records from one table and the matching records from another table. If there are no matches, NULL values are returned for non-matching rows. There are three types of outer joins:

- **LEFT JOIN (or LEFT OUTER JOIN)**
- **RIGHT JOIN (or RIGHT OUTER JOIN)**
- **FULL JOIN (or FULL OUTER JOIN)**

Each of these joins serves a unique purpose based on how you want to retrieve data from the two tables.

---

## 1. LEFT JOIN (LEFT OUTER JOIN)

A `LEFT JOIN` returns all records from the left table (the first table mentioned in the query), and the matched records from the right table. If there is no match, the result is NULL on the right table.

### Syntax

```sql
SELECT columns
FROM table1
LEFT JOIN table2
ON table1.column = table2.column;
```

- `table1`: The left table.
- `table2`: The right table.

### Example

Given two tables:

#### Employees Table

| EmployeeID | FirstName | LastName | DepartmentID |
| ---------- | --------- | -------- | ------------ |
| 1          | John      | Doe      | 1            |
| 2          | Jane      | Smith    | 2            |
| 3          | Mark      | Taylor   | NULL         |
| 4          | Lucy      | Brown    | 3            |

#### Departments Table

| DepartmentID | DepartmentName |
| ------------ | -------------- |
| 1            | HR             |
| 2            | IT             |
| 3            | Marketing      |
| 4            | Sales          |

### Query

```sql
SELECT Employees.EmployeeID, Employees.FirstName, Departments.DepartmentName
FROM Employees
LEFT JOIN Departments
ON Employees.DepartmentID = Departments.DepartmentID;
```

### Result Set

| EmployeeID | FirstName | DepartmentName |
| ---------- | --------- | -------------- |
| 1          | John      | HR             |
| 2          | Jane      | IT             |
| 3          | Mark      | NULL           |
| 4          | Lucy      | Marketing      |

Here, all employees are returned, including "Mark" whose DepartmentID is NULL. Since there's no match in the Departments table for Mark, his DepartmentName is NULL.

## 2. RIGHT JOIN (RIGHT OUTER JOIN)

A `RIGHT JOIN` returns all records from the right table and the matched records from the left table. If there is no match, the result is NULL on the left table.

### Syntax

```sql
SELECT columns
FROM table1
RIGHT JOIN table2
ON table1.column = table2.column;
```

- `table1`: The left table.
- `table2`: The right table.

### Example

Given the same Employees and Departments tables, we want to find all departments and the employees in each.

### Query

```sql
SELECT Employees.EmployeeID, Employees.FirstName, Departments.DepartmentName
FROM Employees
RIGHT JOIN Departments
ON Employees.DepartmentID = Departments.DepartmentID;
```

### Result Set

| EmployeeID | FirstName | DepartmentName |
| ---------- | --------- | -------------- |
| 1          | John      | HR             |
| 2          | Jane      | IT             |
| NULL       | NULL      | Sales          |
| 4          | Lucy      | Marketing      |

Here, all departments are returned, including "Sales", which has no matching employees in the Employees table. In this case, the EmployeeID and FirstName for "Sales" are NULL.

## 3. FULL JOIN (FULL OUTER JOIN)

A FULL OUTER JOIN returns all records when there is a match in either the left or the right table. If there is no match, NULL is returned for columns from the table that doesn't have a match.

### Syntax

```sql
SELECT columns
FROM table1
FULL JOIN table2
ON table1.column = table2.column;
```

### Example

Let's use the same Employees and Departments tables, and perform a `FULL OUTER JOIN` to get all employees and departments, regardless of whether there is a match.

### Query

```sql
SELECT Employees.EmployeeID, Employees.FirstName, Departments.DepartmentName
FROM Employees
FULL JOIN Departments
ON Employees.DepartmentID = Departments.DepartmentID;
```

### Result Set

| EmployeeID | FirstName | DepartmentName |
| ---------- | --------- | -------------- |
| 1          | John      | HR             |
| 2          | Jane      | IT             |
| 4          | Lucy      | Marketing      |
| NULL       | NULL      | Sales          |
| 3          | Mark      | NULL           |

Here, all employees and departments are returned. "Mark" has no matching department (so DepartmentName is NULL), and the "Sales" department has no matching employee (so EmployeeID and FirstName are NULL).

## Comparison of JOIN Types

| JOIN Type  | Rows from Left Table | Rows from Right Table | Unmatched Rows Filled with NULL         |
| ---------- | -------------------- | --------------------- | --------------------------------------- |
| INNER JOIN | Only matching rows   | Only matching rows    | No NULLs                                |
| LEFT JOIN  | All rows             | Only matching rows    | NULL for unmatched right table rows     |
| RIGHT JOIN | Only matching rows   | All rows              | NULL for unmatched left table rows      |
| FULL JOIN  | All rows             | All rows              | NULL for unmatched rows in either table |

## Use Cases

### LEFT JOIN

Use when you want to retain all records from the left table, even if they don’t have matching records in the right table.

### RIGHT JOIN

Use when you want to retain all records from the right table, even if they don’t have matching records in the left table.

### FULL JOIN

Use when you want to retain all records from both tables, regardless of whether there is a match.

## Best Practices

- Choose the appropriate join type: Understanding which records you want to retain will help you decide which join type to use.
- Performance Considerations: JOINs, especially FULL OUTER JOIN, can be expensive in terms of performance. Ensure the relevant columns in the ON clause are indexed.
- Avoid unnecessary NULLs: Use joins carefully to avoid unnecessary NULL values that might complicate queries.
