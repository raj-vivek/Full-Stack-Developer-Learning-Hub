# Data Types in MongoDB

## Introduction

MongoDB is a schema-less, NoSQL database that stores data in a flexible, JSON-like format called BSON (Binary JSON). BSON supports various data types, many of which align with JSON, and some that extend beyond to support complex operations and efficient storage. Each document in MongoDB can contain fields of different data types, and MongoDB enforces no fixed schema across documents in a collection, giving developers flexibility in how they structure their data.

---

## Overview of MongoDB Data Types

| Data Type              | Description                                                                                        |
| ---------------------- | -------------------------------------------------------------------------------------------------- |
| **String**             | Stores text data. Most commonly used data type for storing data in MongoDB.                        |
| **Integer**            | Stores 32-bit or 64-bit integer values.                                                            |
| **Double**             | Stores floating-point values.                                                                      |
| **Boolean**            | Stores `true` or `false` values.                                                                   |
| **Array**              | Stores arrays or lists of values. Can contain multiple data types within the same array.           |
| **Object**             | Stores embedded documents. Allows for nested documents within a document (similar to sub-objects). |
| **ObjectId**           | A unique identifier automatically generated for each document.                                     |
| **Date**               | Stores date-time values. Stored as the number of milliseconds since the Unix epoch.                |
| **Timestamp**          | Represents a point in time, similar to `Date`, but used primarily for internal purposes.           |
| **Binary Data**        | Stores binary data, including files such as images.                                                |
| **Null**               | Represents a null or non-existent field.                                                           |
| **Regular Expression** | Stores regular expressions.                                                                        |
| **JavaScript**         | Stores JavaScript code for execution on the MongoDB server.                                        |
| **Symbol**             | Similar to String but reserved for special use cases.                                              |
| **Min/Max Keys**       | Special types used to compare against the lowest and highest possible BSON elements.               |
| **Decimal128**         | Stores high-precision decimal values, useful for monetary values and precise calculations.         |
| **Long**               | 64-bit integers.                                                                                   |

---

## Detailed Breakdown of MongoDB Data Types

### 1. **String**

- The most commonly used data type.
- Stores UTF-8 encoded text.

#### Example:

```json
{ "name": "John Doe" }
```

### 2. Integer

- MongoDB provides two integer types: 32-bit and 64-bit.

- Use cases include counting, indexing, or storing numeric values where floating-point precision isn’t needed.

#### Example:

```json
{ "age": 25 }   // 32-bit integer
{ "balance": NumberLong(1234567890123456) } // 64-bit integer
```

### 3. Double

- Stores floating-point values.

- Useful for precise calculations involving decimals, such as in financial applications.

#### Example:

```json
{ "price": 199.99 }
```

### 4. Boolean

- Stores either true or false.

- Often used for flags or status indicators.

#### Example:

```json
{ "isActive": true }
```

### 5. Array

- Allows storing multiple values in a single field.

- Arrays can contain mixed types, including nested arrays or objects.

#### Example:

```json
{
  "hobbies": ["reading", "coding", "traveling"],
  "scores": [95, 88, 92]
}
```

### 6. Object

- Stores embedded documents (sub-objects) within a document.

- Allows you to model complex, hierarchical data structures.

#### Example:

```json
{
  "name": "John",
  "address": {
    "street": "123 Main St",
    "city": "New York"
  }
}
```

### 7. ObjectId

- A 12-byte unique identifier generated automatically for every document.

- Composed of timestamp, machine identifier, process ID, and a random counter.

#### Example:

```json
{ "_id": ObjectId("507f191e810c19729de860ea") }
```

### 8. Date

- Stores date and time values.

- Dates are stored as the number of milliseconds since January 1, 1970 (the Unix Epoch).

- MongoDB includes operators for date queries like $gt, $lt, and date manipulation.

#### Example:

```json
{ "joined": ISODate("2023-09-26T00:00:00Z") }
```

### 9. Timestamp

- Similar to Date but used primarily by MongoDB for internal operations such as replication and sharding.

- Typically not used by application developers, unless dealing with MongoDB’s internal data.

#### Example:

```json
{ "eventTime": Timestamp(1412180887, 1) }
```

### 10. Binary Data

- Used to store binary data, such as images, audio, or encrypted data.

- Useful when you want to store large binary objects (BLOBs) within MongoDB.

#### Example:

```json
{ "profilePicture": BinData(0, "someBinaryData") }
```

### 11. Null

- Represents a field with a null value.

#### Example:

```json
{ "middleName": null }
```

### 12. Regular Expression

- Stores regular expressions for pattern matching queries.

#### Example:

```json
{ "email": { "$regex": ".*@gmail.com$" } }
```

### 13. JavaScript

- Stores JavaScript code that can be executed within the database server.

- Supports storing both JavaScript code and optional scopes (context).

#### Example:

```json
{ "script": { "$code": "function() { return true; }" } }
```

### 14. Symbol

- Similar to a string but reserved for special cases where symbols are necessary (rarely used in modern MongoDB applications).

#### Example:

```json
{ "language": { "$symbol": "en_US" } }
```

### 15. Min Key and Max Key

- Special types used for internal purposes, primarily for sorting and comparison.

- MinKey compares less than all other values.

- MaxKey compares greater than all other values.

#### Example:

```json
{ "minField": MinKey() }
{ "maxField": MaxKey() }
```

### 16. Decimal128

- A high-precision 128-bit decimal type, primarily used in financial applications where exact values are crucial.

#### Example:

```json
{ "price": NumberDecimal("9.99") }
```

### 17. Long (64-bit Integer)

- Stores 64-bit signed integers for cases where larger numeric values are required.

#### Example:

```json
{ "bigNumber": NumberLong("9223372036854775807") }
```

---

## BSON and MongoDB Data Types
MongoDB stores data in BSON (Binary JSON), an extension of JSON that includes additional data types such as ObjectId, Binary, and Decimal128. BSON is designed to be efficient in both space and speed, making it well-suited for storing data in a distributed database like MongoDB.

### BSON Data Types:
- MongoDB translates between BSON and JSON, so you can insert and retrieve data in JSON format while MongoDB handles it as BSON internally.
- The BSON format is optimized for space, which allows MongoDB to handle more complex types such as embedded documents and arrays efficiently.

## Conclusion
MongoDB provides a wide range of data types, some of which go beyond the basic types offered by JSON. By supporting types like ObjectId, Decimal128, Binary, and JavaScript, MongoDB offers more flexibility and functionality for storing and manipulating complex data structures. Understanding these data types is crucial when modeling your data to take full advantage of MongoDB's capabilities.
