# Objects Class in Java

## Theory

### What is the Objects Class?

- **Objects Class**: A utility class in the `java.util` package that contains static methods for operating on objects. It is a part of the Java Collections Framework and is designed to help with common object-related tasks such as comparisons, hashing, and null checks.
- Introduced in Java 7
- Has 9 static utility methods for operating on objects.

### Key Points

- **Utility Methods**: The `Objects` class provides several useful static methods for:
  - Null checks
  - Equality checks
  - Hash code generation
  - String representations
  - Deep comparisons

### Important Methods

1. **Null Checks**

   - `T Objects.requireNonNull(T obj)`: Checks that the specified object reference is not null. Returns obj, if not `null` and throws `NullPointerException`, if obj is `null`.
   - `T Objects.requireNonNull(T obj, String message)`: Checks that the specified object reference is not null. Returns obj, if not `null` and throws a customized `NullPointerException` using the message provided, if obj is `null`

2. **Equality Checks**

   - `boolean equals(Object a, Object b)`: It returns `true` if both objects are equal or both are `null`. It uses the `equals()` method of the first object to determine equality.
   - `boolean deepEquals(Object a, Object b)`: It returns `true` if the two objects are deeply equal. For arrays, it performs a deep comparison of array contents. For non-array objects, it uses the `equals()` method. Especially used for arrays or nested objects.

3. **Hash Code Generation**

   - `int hash(Object... values)`: Generates a hash code for a sequence of input values. The hash code is generated as if all the input values were placed into an array, and that array were hashed by calling Arrays.hashCode(Object[]). This method is useful for implementing Object.hashCode() on objects containing multiple fields.
   - `int hashCode(Object o)`: Returns the hash code of a non-null argument and 0 for a null argument.

4. **String Representations**

   - `String toString(Object o)`: Returns the result of calling `toString` on the object or `String`: "null" if the object is `null`.
   - `String toString(Object o, String nullDefault)`: Returns the result of calling `toString` on the object or the provided default string if the object is null.

5. **Comparison**
   - `int compare(T a, T b, Comparator<? super T> c)`: Compares two objects with a specified comparator.
