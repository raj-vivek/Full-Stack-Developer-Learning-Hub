# Data Definition Language (DDL)

---

## Introduction

- Data Definition Language (DDL) is a subset of SQL used to define, modify, and manage the structure of database objects such as tables, indexes, and schemas.
- Unlike DML (Data Manipulation Language), which deals with data, DDL deals with the schema or structure of a database.
- DDL commands are auto-committed, meaning changes made using DDL are immediately saved in the database and cannot be rolled back.

---

## Key DDL Commands

### 1. **CREATE**

The `CREATE` statement is used to create new database objects like tables, views, indexes, and databases.

#### a. Creating a Table

The most common use of the `CREATE` command is to create a table.

```sql
CREATE TABLE Employees (
    EmployeeID INT PRIMARY KEY,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    Email VARCHAR(100) UNIQUE,
    HireDate DATE,
    Salary DECIMAL(10, 2)
);
```

#### b. Creating a Database

You can also create an entire database schema using the CREATE command.

```sql
CREATE DATABASE CompanyDB;
```

#### c. Creating an Index

Indexes improve the performance of queries by allowing faster retrieval of records.

```sql
CREATE INDEX idx_lastname ON Employees(LastName);
```

---

### 2. ALTER

The ALTER command is used to modify the structure of an existing database object. Common operations include adding, modifying, or dropping columns, constraints, and indexes.

#### a. Adding a Column

You can add a new column to an existing table.

```sql
ALTER TABLE Employees ADD DepartmentID INT;
```

#### b. Modifying a Column

You can change the data type or properties of an existing column.

```sql
ALTER TABLE Employees MODIFY COLUMN Salary DECIMAL(12, 2);
```

#### c. Dropping a Column

The ALTER command can also be used to remove a column from a table.

```sql
ALTER TABLE Employees DROP COLUMN Email;
```

---

### 3. DROP

The DROP command is used to delete existing objects such as databases, tables, or indexes. Be cautious when using DROP, as it permanently removes the object and its data.

#### a. Dropping a Table

When you drop a table, all the data within the table is permanently deleted.

```sql
DROP TABLE Employees;
```

#### b. Dropping a Database

Dropping a database will remove all its tables and data.

```sql
DROP DATABASE CompanyDB;
```

#### c. Dropping an Index

Indexes can also be dropped to free up space or if they are no longer needed.

```sql
DROP INDEX idx_lastname ON Employees;
```

---

### 4. TRUNCATE

The TRUNCATE command is used to delete all rows from a table while retaining the structure of the table. Unlike DELETE, which logs individual row deletions, TRUNCATE is faster as it does not log each row. It cannot be rolled back in most databases.

```sql
TRUNCATE TABLE Employees;
```

---

### 5. RENAME

The RENAME command is used to change the name of existing database objects such as tables.

#### a. Renaming a Table

You can rename a table using this command.

```sql
RENAME TABLE Employees TO Staff;
```

#### b. Renaming a Column

Some databases support renaming columns within tables.

```sql
ALTER TABLE Employees RENAME COLUMN LastName TO Surname;
```

---

## Comparison of DDL vs DML

| Aspect              | DDL                                   | DML                            |
| ------------------- | ------------------------------------- | ------------------------------ |
| Focus               | Defines and alters database structure | Manipulates the actual data    |
| Commands            | CREATE, ALTER, DROP, TRUNCATE, RENAME | SELECT, INSERT, UPDATE, DELETE |
| Transaction Control | Auto-commit (cannot be rolled back)   | Can be rolled back             |
| Impact on Schema    | Alters schema and structure           | Operates on data               |

---

## Best Practices for Using DDL

1. **Plan Before Modifying Schema**: Any changes to the schema should be carefully planned as they impact all users of the database.
2. **Back Up Before Major Changes**: Since DDL operations are auto-committed, always back up critical data before making schema modifications.
3. **Indexing Considerations**: Create indexes carefully to optimize performance, but avoid over-indexing as it can degrade performance during write operations.
4. **Avoid Dropping Tables Without Backup**: Dropping tables is irreversible. Always ensure a backup exists before performing DROP operations.

---

## Conclusion

DDL is an essential part of SQL for defining and managing the structure of a database. Understanding DDL commands and their appropriate use is critical for creating and maintaining a well-structured database, especially in environments where data integrity and performance are crucial.
