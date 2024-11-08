# MongoDB Operations

- MongoDB provides a rich set of operations that allow developers to manipulate, query, and analyze data. The operations in MongoDB span a wide range of functionality, from basic CRUD (Create, Read, Update, Delete) to more advanced features such as bulk operations, counting documents, and schema validation.
- MongoDB Shell is an interactive JavaScript environment that provides a command-line interface for working with MongoDB databases. It allows you to execute MongoDB commands directly, making it a valuable tool for database administration, development, and testing.

---

## 1. CRUD Operations

CRUD operations are the foundational methods for interacting with documents in MongoDB.

### 1.1. Create (Insert)

MongoDB provides several ways to insert new documents into a collection:

- `insertOne()`: Inserts a single document.
- `insertMany()`: Inserts multiple documents at once.

```js
// Insert one document
db.users.insertOne({ name: "Alice", age: 25, city: "New York" });

// Insert multiple documents
db.users.insertMany([
  { name: "Bob", age: 30, city: "Chicago" },
  { name: "Charlie", age: 35, city: "Los Angeles" },
]);
```

### 1.2. Read (Query)

MongoDB's powerful query language allows for filtering documents using various operators.

- `find()`: Retrieves documents that match a query.
- `findOne()`: Retrieves the first document that matches a query.

```js
// Find all users older than 30
db.users.find({ age: { $gt: 30 } });

// Find a specific user
db.users.findOne({ name: "Alice" });
```

### 1.3. Update

MongoDB allows both updating individual fields of documents or replacing entire documents.

- `updateOne()`: Updates a single document.
- `updateMany()`: Updates multiple documents.
- `$set`: Used to set or update specific fields.

```js
// Update one user's age
db.users.updateOne({ name: "Alice" }, { $set: { age: 26 } });

// Update multiple users' city
db.users.updateMany({ city: "New York" }, { $set: { city: "San Francisco" } });
```

### 1.4. Delete

Delete operations remove documents from a collection based on the query.

- `deleteOne()`: Deletes a single document that matches the filter.
- `deleteMany()`: Deletes multiple documents that match the filter.

```js
// Delete one user
db.users.deleteOne({ name: "Charlie" });

// Delete all users from a specific city
db.users.deleteMany({ city: "Los Angeles" });
```

---

## 2. Bulk Operations

MongoDB supports executing multiple write operations in bulk using `bulkWrite()`. This is useful for optimizing the performance of batch operations.

```js
db.users.bulkWrite([
  { insertOne: { document: { name: "Eve", age: 28, city: "Boston" } } },
  { updateOne: { filter: { name: "Alice" }, update: { $set: { age: 27 } } } },
  { deleteOne: { filter: { name: "Bob" } } },
]);
```

Bulk operations can include any combination of insert, update, delete operations.

---

## 3. Counting Documents

MongoDB provides multiple ways to count documents in a collection.

- `countDocuments()`: Counts documents that match a query.
- `estimatedDocumentCount()`: Returns an estimated count of documents in a collection (faster but less accurate than `countDocuments()`).

```js
// Count all users
db.users.countDocuments();

// Count users older than 30
db.users.countDocuments({ age: { $gt: 30 } });

// Estimate the total number of documents in a collection
db.users.estimatedDocumentCount();
```

---

### 4. Query Operators

MongoDB provides a variety of query operators to match documents based on specific conditions:

- **Comparison Operators**: `$eq`, `$gt`, `$gte`, `$lt`, `$lte`, `$ne`
- **Logical Operators**: `$and`, `$or`, `$not`, `$nor`
- **Array Operators**: `$in`, `$nin`, `$all`, `$size`, `$elemMatch`

```js
// Find users whose age is greater than 25 and live in New York
db.users.find({ $and: [{ age: { $gt: 25 } }, { city: "New York" }] });
```

---

## 5. Aggregation Framework

The MongoDB aggregation framework processes data records and returns computed results. It operates as a pipeline, where each stage transforms the documents and passes the results to the next stage.

Key stages include:

- `$match`: Filters documents.
- `$group`: Groups documents by a field.
- `$project`: Modifies the document’s structure.
- `$sort`: Sorts documents.
- `$limit`: Limits the number of documents.

```js
db.users.aggregate([
  { $match: { city: "New York" } }, // Filter users from New York
  { $group: { _id: "$age", count: { $sum: 1 } } }, // Group by age and count
  { $sort: { count: -1 } }, // Sort by count in descending order
]);
```

---

## 6. Schema Validation: validate()

MongoDB allows you to enforce document structure using schema validation. This can ensure data integrity in the NoSQL environment.

- `validate()`: Allows checking for schema compliance within collections.

```js
db.runCommand({
  collMod: "users",
  validator: {
    $jsonSchema: {
      bsonType: "object",
      required: ["name", "age"],
      properties: {
        name: {
          bsonType: "string",
          description: "must be a string and is required",
        },
        age: {
          bsonType: "int",
          minimum: 18,
          description: "must be an integer and at least 18",
        },
      },
    },
  },
});

// To check schema validity
db.users.validate();
```

---

## 7. Indexing

Indexes improve the performance of queries by allowing MongoDB to quickly locate documents. MongoDB supports single field, compound, and geospatial indexes.

- `createIndex()`: Creates an index on a collection.

```js
// Create an index on the 'age' field
db.users.createIndex({ age: 1 });

// Create a compound index on 'age' and 'city'
db.users.createIndex({ age: 1, city: -1 });
```

Indexes can significantly speed up read operations, but they can slow down write operations due to the overhead of maintaining the indexes.

---

## 8. Transactions in MongoDB

Starting from MongoDB 4.0, you can use ACID-compliant transactions across multiple documents in a single or multiple collections.

```js
// Start a transaction
const session = db.getMongo().startSession();
session.startTransaction();

try {
  db.users.insertOne({ name: "Frank", age: 29 }, { session });
  db.orders.insertOne(
    {
      userId: "Frank",
      items: [
        /*...*/
      ],
    },
    { session }
  );

  // Commit transaction
  session.commitTransaction();
} catch (e) {
  // Abort transaction in case of error
  session.abortTransaction();
} finally {
  session.endSession();
}
```

---

## 10. Geospatial Queries

MongoDB supports geospatial data types and queries. You can store location-based data using 2dsphere indexes and query for nearby points.

```js
// Create 2dsphere index for geospatial queries
db.places.createIndex({ location: "2dsphere" });

// Find places near a specific location
db.places.find({
  location: {
    $near: {
      $geometry: {
        type: "Point",
        coordinates: [-73.97, 40.77], // Latitude, Longitude
      },
      $maxDistance: 5000, // Within 5 km
    },
  },
});
```

---

## Conclusion

MongoDB offers a powerful set of operations to handle diverse data needs, from basic CRUD operations to complex aggregation pipelines, geospatial queries, transactions, and bulk operations. By leveraging these operations effectively, you can build scalable, efficient applications that handle both structured and unstructured data.
