# Query Operators in MongoDB

- Querying data in MongoDB is a fundamental operation, allowing you to filter and retrieve documents based on various criteria.
- MongoDB provides a rich query language that supports a variety of query operators for searching, filtering, and sorting data in collections.

- Query operators provide powerful ways to search and manipulate documents in a MongoDB collection. There are several types of query operators, including:

1. Comparison Operators
2. Logical Operators
3. Element Operators
4. Array Operators
5. Bitwise Operators
6. Evaluation Operators

---

## 1. Basic Queries

### `find()` Method

The most basic query method in MongoDB is `find()`, which retrieves all documents in a collection if no criteria are provided.

```js
// Find all documents in the 'users' collection
db.users.find();
```

You can pass a query object to filter documents based on field values.

```js
// Find all users with the name "Alice"
db.users.find({ name: "Alice" });
```

## 2. Comparison Operators:

Comparison operators allow you to compare the value of a field with specified values.

- `$eq`: Matches values that are equal to a specified value.
- `$gt`: Matches values greater than a specified value.
- `$lt`: Matches values less than a specified value.
- `$gte`: Matches values greater than or equal to a specified value.
- `$lte`: Matches values less than or equal to a specified value.
- `$ne`: Matches all values that are not equal to a specified value.

```js
// Find users with an age greater than 30
db.users.find({ age: { $gt: 30 } });

// Find users whose age is not equal to 25
db.users.find({ age: { $ne: 25 } });
```

## 3. Logical Operators:

Logical operators provide ways to combine multiple query conditions.

- `$and`: Joins query clauses with a logical AND. 
- `$or`: Joins query clauses with a logical OR.
- `$not`: Inverts the effect of a query expression.
- `$nor`: Joins query clauses with a logical NOR.

Note: `$and`/`$or`/`$nor` is only necessary when you have multiple conditions on the same field or you want to enforce a specific order for applying the conditions.

```js
// Find users who are either older than 30 or live in "New York"
db.users.find({ $or: [{ age: { $gt: 30 } }, { city: "New York" }] });

// Find users whose age is between 30 and 50 and live in "San Francisco"
db.users.find({
  $and: [{ age: { $gt: 30 } }, { age: { $lt: 30 } } { city: "San Francisco" }],
});

db.products.find({ price: { $not: { $gt: 100 } } });
```

## 4. Element Operators

- `$exists`: Matches documents that have the specified field.
- `$type`: Matches documents where the field is of a specified BSON type.

```js
// Find all users where the field 'age' exists
db.users.find({ age: { $exists: true } });

// Find all users where the 'age' field is an integer
db.users.find({ age: { $type: "int" } });
// OR
db.users.find({ age: { $type: 16 } });
```

### Some of the common BSON data types and their corresponding aliases are:

- `Double`: 1 or `double`
- `String`: 2 or `string`
- `Object`: 3 or `object`
- `Array`: 4 or `array`
- `Binary`: 5 or `binData`
- `ObjectId`: 7 or `objectId`
- `Boolean`: 8 or `bool`
- `Date`: 9 or `date`
- `Null`: 10 or `null`
- `Regex`: 11 or `regex`
- `Int32`: 16 or `int`
- `Int64`: 18 or `long`
- `Decimal128`: 19 or `decimal`

## . Array Operators

- `$in`: Matches any of the values specified in an array.
- `$nin`: Matches none of the values specified in an array.
- `$size`: Matches arrays with a specific number of elements.
- `$all`: Matches arrays that contain all specified elements.
- `$elemMatch`: Matches arrays that satisfy certain conditions.

```js
// Find users with the city "New York" or "San Francisco"
db.users.find({ city: { $in: ["New York", "San Francisco"] } });

// Find users with an array 'tags' that contains exactly 3 elements
db.users.find({ tags: { $size: 3 } });

// Find all movies with the genre "action" and "sci-fi"
db.movies.find({ genre: { $all: ['action', 'sci-fi'] } });

// Find users with an 'address' field that has a 'city' value of "Boston"
db.users.find({ address: { $elemMatch: { city: "Boston" } } });
```

3. Sorting and Limiting Results
   3.1. Sorting Results
   To sort query results, use the sort() method. You can specify sorting by one or more fields in ascending (1) or descending (-1) order.

````js
// Sort users by age in descending order
db.users.find().sort({ age: -1 });

// Sort by age in ascending order and by name in descending order
db.users.find().sort({ age: 1, name: -1 });
3.2. Limiting and Skipping Results
You can limit the number of documents returned using the limit() method and skip a number of documents using the skip() method.

```js
// Return only the first 5 users
db.users.find().limit(5);

// Skip the first 10 users and return the next 5
db.users.find().skip(10).limit(5);
4. Projection
MongoDB allows you to specify which fields to include or exclude in the result set using projection. This is done by passing a second argument to the find() method.

1 indicates that the field should be included.
0 indicates that the field should be excluded.
```js
// Find users, but only include their name and age fields
db.users.find({}, { name: 1, age: 1 });

// Find users, excluding the '_id' field
db.users.find({}, { _id: 0 });
5. Querying Subdocuments
MongoDB allows querying nested fields within subdocuments using dot notation.

```js
// Find users where the address.city is "Boston"
db.users.find({ "address.city": "Boston" });

// Find users with the specific zip code in their address
db.users.find({ "address.zip": 90210 });
6. Geospatial Queries
MongoDB supports geospatial queries with special geospatial indexes.

6.1. 2dsphere Index
This index is used for querying spherical geometry (i.e., Earth-like geometry).

```js
// Create a 2dsphere index for geospatial queries
db.places.createIndex({ location: "2dsphere" });

// Find places near a specific location
db.places.find({
  location: {
    $near: {
      $geometry: {
        type: "Point",
        coordinates: [-73.97, 40.77]  // Latitude, Longitude
      },
      $maxDistance: 5000  // 5 km radius
    }
  }
});
7. Aggregation Queries
MongoDB's aggregation framework allows for complex data manipulation through a series of pipeline stages, including $match, $group, $project, $sort, and more.

7.1. $match
Filters documents based on specified conditions.

```js
// Find users from "New York"
db.users.aggregate([
  { $match: { city: "New York" } }
]);
7.2. $group
Groups documents by a specific field and performs calculations like summing, averaging, counting, etc.

```js
// Group users by city and count the number of users in each city
db.users.aggregate([
  { $group: { _id: "$city", count: { $sum: 1 } } }
]);
7.3. $project
Used to reshape documents, including or excluding specific fields.

```js
// Project only the name and age fields of users
db.users.aggregate([
  { $project: { name: 1, age: 1 } }
]);
8. Text Search
MongoDB allows for text-based queries on collections with a text index.

8.1. Creating a Text Index
To perform text searches, a text index must be created on the field(s) that will be queried.

```js
// Create a text index on the 'bio' and 'name' fields
db.users.createIndex({ bio: "text", name: "text" });
8.2. Performing a Text Search
Use the $text operator to search for a keyword in the text-indexed fields.

```js
// Find users whose bio or name contains "developer"
db.users.find({ $text: { $search: "developer" } });
8.3. Text Search with Sorting by Relevance
MongoDB assigns a relevance score to documents based on how well they match the text search. You can sort by this score using the meta option.

```js
// Perform a text search and sort by relevance score
db.users.find(
  { $text: { $search: "developer" } },
  { score: { $meta: "textScore" } }
).sort({ score: { $meta: "textScore" } });
9. Regular Expression Queries
MongoDB supports querying documents using regular expressions. This can be useful for pattern matching.

```js
// Find users whose name starts with "A"
db.users.find({ name: { $regex: /^A/ } });

// Find users whose bio contains the word "engineer"
db.users.find({ bio: { $regex: /engineer/ } });
Conclusion
MongoDB provides a powerful, flexible, and efficient way to query data using various operators, logical conditions, and specialized queries such as geospatial and aggregation queries. Understanding how to utilize these querying techniques effectively is essential for working with MongoDB in a production environment.

````
