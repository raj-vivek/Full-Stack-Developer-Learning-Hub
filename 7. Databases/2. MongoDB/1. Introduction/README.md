# MongoDB Basics

## Introduction

MongoDB is a NoSQL database that uses a document-oriented model. It stores data in flexible, JSON-like documents, unlike traditional SQL databases that use rows and columns. MongoDB's flexibility makes it a popular choice for modern applications, especially those with unstructured data or when rapid, scalable growth is required.

---

## What is MongoDB Atlas?

**MongoDB Atlas** is a fully managed cloud-based version of MongoDB, available on platforms like AWS, Google Cloud, and Microsoft Azure. It offers:

- Automated backups, updates, and security features.
- Easy scaling with sharding and replication.
- Integration with other cloud services.
- Built-in monitoring and performance optimization tools.

MongoDB Atlas is ideal for companies that want to avoid the overhead of managing their own database infrastructure and prefer a managed service with flexible pricing and high availability.

---

## MongoDB Terminology

| MongoDB Term          | Equivalent SQL Term      | Description                                                                                                                    |
| --------------------- | ------------------------ | ------------------------------------------------------------------------------------------------------------------------------ |
| **Database**          | Database                 | A container for collections of documents.                                                                                      |
| **Collection**        | Table                    | A group of MongoDB documents, analogous to a table in SQL.                                                                     |
| **Document**          | Row/Record               | A single record in MongoDB, stored in a BSON (Binary JSON) format.                                                             |
| **Field**             | Column-Row cell          | A key-value pair in a document.                                                                                                |
| **Index**             | Index                    | Similar to SQL, used to optimize queries.                                                                                      |
| **Query**             | SQL SELECT Statement     | A query in MongoDB is used to retrieve specific documents or subsets of documents from a collection based on a given condition |
| Cursor                | N/A                      | A cursor is a pointer to the result set of a query, usedto process individual documents from the result set efficiently        |
| **Sharding**          | Partitioning             | Distributing data across multiple machines for horizontal scalability.                                                         |
| **Replication**       | Master-Slave Replication | Synchronizing data across multiple servers for redundancy and availability.                                                    |
| **Embedded Document** | Foreign Key              | Storing related data directly within a document, avoiding joins.                                                               |
| `_id`                 | Primary Key              | A unique identifier automatically generated for each document within a collection.                                             |
| **Capped Collection** | N/A                      | A fixed-size collection that automatically overwrites its oldest entries.                                                      |
| **Replica Set**       | N/A                      | A set of MongoDB instances that maintain the same data for high availability.                                                  |

---

## Key Features of MongoDB

1. **Dynamic Schema** : Allows you to store a wide variety of data structures and change them over time.
2. **Scalability** : MongoDB supports horizontal scaling using sharding, which allows you to partition your data across multiple servers.
3. **Indexing** : Supports indexing to improve query performance, including unique indexes, compound indexes, and geospatial indexes.
4. **Replication** : MongoDB's replica sets provide redundancy, fault tolerance, and high availability.
5. **ACID Transactions** : Supports multi-document transactions, ensuring atomicity for critical operations.
   - Starting from version 4.0, MongoDB introduced multi-document ACID transactions, which ensure atomicity, consistency, isolation, and durability for transactions across multiple collections and databases
   - MongoDB extended its support for transactions in version 4.2, enabling distributed transactions across sharded clusters. This means you can perform multi-document transactions even in complex, distributed environments
6. **Aggregation Framework** : A powerful tool for performing data transformations and computations, analogous to SQL GROUP BY but with more flexibility.

---

## MongoDB Atlas

MongoDB Atlas is a fully managed cloud-based database service built and maintained by MongoDB. The Atlas platform is available on major cloud providers like AWS, Azure, and Google Cloud Platform, allowing developers to deploy, manage, and scale their MongoDB clusters in a seamless and efficient manner.

Some of the standout features and benefits of MongoDB Atlas include:

- **Database as a Service (DBaaS)**: MongoDB Atlas takes care of database-related operations like backups, monitoring, scaling, and security, allowing developers to focus on their application logic.

- **Global Cluster Support**: Atlas enables the creation of globally distributed clusters. Data can be stored and replicated across multiple geographies for improved performance, high availability, and reduced latency.

- **Security**: Atlas offers built-in security features, such as end-to-end encryption, role-based access control, and IP whitelisting. This ensures your data remains secure and compliant with industry standards.

- **Performance**: MongoDB Atlas provides tools for monitoring and optimizing the performance of your database. Advanced features like performance advisor and index suggestions help keep your database running at optimal speed.

- **Easy Scaling**: With Atlas, you can easily scale your cluster either vertically or horizontally, depending on your requirements. Atlas supports auto-scaling of both storage and compute resources.

- **Data Automation and Integration**: Atlas allows seamless integration with other services, like BI tools and serverless functions. The platform also supports easy data migration from on-premises or cloud-based deployments.

---

## SQL vs NoSQL

| Aspect              | SQL (Relational DB)                                   | NoSQL (MongoDB)                                                                     |
| ------------------- | ----------------------------------------------------- | ----------------------------------------------------------------------------------- |
| **Data Model**      | Structured, tabular (rows and columns)                | Unstructured, document-based (BSON/JSON)                                            |
| **Schema**          | Fixed schema, strictly defined columns                | Dynamic schema, no strict definition                                                |
| **Scalability**     | Vertical scaling (limited)                            | Horizontal scaling (easier with sharding)                                           |
| **Joins**           | Supports complex joins across tables                  | No joins, uses embedded documents or references                                     |
| **Transactions**    | ACID-compliant transactions across tables             | Supports multi-document transactions since MongoDB 4.x, but not as rich as SQL      |
| **Best Suited For** | Structured, relational data (e.g., financial systems) | Unstructured or semi-structured data (e.g., social media, IoT, real-time analytics) |
| **Examples**        | MySQL, PostgreSQL, Oracle                             | MongoDB, Cassandra, CouchDB                                                         |

---

## When to Use MongoDB?

MongoDB is ideal when:

- **Dynamic, schema-less data** is required. If the data structure frequently changes, MongoDB allows for flexibility.
- **Handling Large Volumes of Data**: **Horizontal scaling** is needed for large, distributed systems. MongoDB's sharding makes it easy to distribute data across multiple servers.
- **High write/read performance** is necessary for real-time analytics, logging, or IoT applications.
- **Unstructured or semi-structured data** like JSON is being stored, such as for content management systems, user profiles, catalogs, or logs.
- **Rapid Application Development** is needed. Due to its flexibility and ease of use, MongoDB is a good choice for startups and agile development teams that require quick iterations and frequent schema changes. It allows developers to focus on implementing features without the burden of managing rigid database structures.

---

## MongoDB Document Structure

In MongoDB, data is stored as **documents** in a **collection**. These documents are similar to JSON objects but are stored in BSON (Binary JSON) format for efficiency.

### Example Document:

```json
{
  "_id": ObjectId("507f191e810c19729de860ea"),
  "firstName": "John",
  "lastName": "Doe",
  "age": 29,
  "address": {
    "street": "123 Main St",
    "city": "New York",
    "state": "NY",
    "postalCode": "10001"
  },
  "email": "john.doe@example.com"
}
```

## Key Characteristics:

- **Dynamic Schema**: Fields can vary between documents in the same collection.
- **Embedded Documents**: Nested documents within a field (e.g., address in the example).
- **Automatic `_id` Field**: Each document gets a unique `_id` field (equivalent to a primary key in SQL).

## CRUD Operations in MongoDB

### 1. Create

- To insert a single document, use `db.collection.insertOne()`.
- For inserting multiple documents, use `db.collection.insertMany()`.

```js
db.collection.insertOne({
  firstName: "John",
  lastName: "Doe",
  age: 29,
});
```

### 2. Read

- Fetch documents from a collection using `db.collection.find()`.
- To fetch only one document, use `db.collection.findOne()`.

```js
db.collection.find({ age: { $gt: 25 } });
```

### 3. Update

- Update fields or entire documents by using update operators like `$set` and `$unset` with `db.collection.updateOne()` or `db.collection.updateMany()`.

```js
db.collection.updateOne({ firstName: "John" }, { $set: { age: 30 } });
```

### 4. Delete

- Remove documents from a collection using `db.collection.deleteOne()` or `db.collection.deleteMany()` with query criteria.

```js
db.collection.deleteOne({ firstName: "John" });
```

### 5. Drop

- Permanently delete a collection or a database using `db.collection.drop()` and `db.dropDatabase()`.

## Indexes and Aggregation in MongoDB

### Indexes

Improve the performance of searches by creating indexes on fields within a collection using `db.collection.createIndex()` or build compound indexes for querying multiple fields.

### Aggregation

MongoDB's aggregation framework is used to process data and return computed results. It's similar to SQL's `GROUP BY` but more flexible and powerful, supporting various stages like `$match`, `$group`, `$sort`, `$project`, etc.

## Example Aggregation Query:

```js
db.sales.aggregate([
  { $match: { status: "A" } },
  { $group: { _id: "$cust_id", total: { $sum: "$amount" } } },
  { $sort: { total: -1 } },
]);
```

## When Not to Use MongoDB

1. **Highly relational data**: If your data has complex relationships with many joins and constraints, a relational database may be more suitable.
2. **Strong consistency requirements**: While MongoDB supports transactions, traditional RDBMS are still better suited for use cases requiring strict consistency.
3. **Transactional systems**: For financial or other systems requiring ACID compliance across many entities, SQL databases may be a better fit.

## Conclusion

MongoDB is a powerful NoSQL database well-suited for applications that require flexibility, scalability, and high performance with unstructured or semi-structured data. Its document-oriented approach, combined with features like sharding, replication, and ACID transactions, makes it a strong choice for modern applications.
