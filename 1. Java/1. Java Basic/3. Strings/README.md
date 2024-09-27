# Strings in Java

Strings in Java are a fundamental data type and are widely used in applications. They are immutable, meaning once a string is created, it cannot be modified. The `String` class is part of the `java.lang` package, and Java offers several methods to manipulate strings efficiently.

---

## 1. String Characteristics

- **Immutable**: Once a string is created, its value cannot be changed.
- **Stored in the String Pool**: String literals are stored in a pool to avoid duplication and save memory.
- **Reference Type**: `String` is a reference type and not a primitive type, but Java handles it like a primitive type with special support.

---

## 2. Creating Strings

There are two primary ways to create strings:

### 2.1. Using String Literals

String literals are stored in the **String Pool**.

```java
String str1 = "Hello";
String str2 = "Hello"; // This will point to the same object as str1
```

### 2.2. Using the new Keyword

When a String is created using the new keyword, a new object is explicitly created.

```java
String str3 = new String("Hello"); // This creates a new object in the heap memory
```

---

## 3. Common String Methods

### 3.1. length()

Returns the length of the string.

```java
String str = "Hello";
int len = str.length();  // 5
```

### 3.2. charAt(int index)

Returns the character at the specified index.

```java
char ch = str.charAt(1);  // 'e'
```

### 3.3. substring(int startIndex, int endIndex)

Returns a substring from the string.

```java
String subStr = str.substring(1, 4);  // "ell"
```

### 3.4. concat(String str)

Concatenates the specified string to the end of another string.

```java
String result = str.concat(" World");  // "Hello World"
```

### 3.5. indexOf(String str)

Returns the index of the first occurrence of the specified substring.

```java
int index = str.indexOf("lo");  // 3
```

### 3.6. toUpperCase() and toLowerCase()

Converts all characters to upper or lower case.

```java
String upper = str.toUpperCase();  // "HELLO"
String lower = str.toLowerCase();  // "hello"
```

### 3.7. replace(char oldChar, char newChar)

Replaces all occurrences of a character.

```java
String replaced = str.replace('l', 'p');  // "Heppo"
```

### 3.8. trim()

Removes leading and trailing white spaces.

```java
String trimmed = "   Hello   ".trim();  // "Hello"
```

### 3.9 toCharArray()

Converts the string into an array of characters.

```java
char[] arrayName = yourString.toCharArray();
```

---

## 4. String Immutability

Java strings are immutable, meaning once a string object is created, it cannot be changed. Any operation that seems to modify the string (like concatenation or replacing characters) creates a new string object in memory instead of modifying the original.

### Why Immutable?

- **Thread-Safety**: Immutable objects can be shared between multiple threads without synchronization.
- **String Pooling**: Immutable strings allow for reuse from the String Pool, improving performance.
- **Security**: Immutability prevents altering sensitive data like file paths or URLs after they’ve been created.

---

## 5. String Comparison

### 5.1. equals()

Compares two strings for equality in terms of content.

```java
String str1 = "Hello";
String str2 = "Hello";
boolean isEqual = str1.equals(str2);  // true
```

### 5.2. equalsIgnoreCase()

Compares two strings for equality, ignoring case.

```java
boolean isEqualIgnoreCase = str1.equalsIgnoreCase("hello");  // true
```

### 5.3. compareTo()

Compares two strings lexicographically.

```java
int result = str1.compareTo("World");  // Negative, because "Hello" comes before "World"
```

### 5.4. == Operator

This compares the memory addresses of the objects, not the content. It should not be used for string content comparison.

```java
boolean isSame = (str1 == str2);  // true (for literals because of string pooling)
```

---

## 6. Memory Management with Strings

### 7.1. String Pool

When a string is created using a literal, it’s placed in a special pool called the String Pool. If another string with the same value is created, it will point to the same reference in the pool.

### 7.2. Heap Memory

When a string is created using the new keyword, it’s allocated on the heap, separate from the string pool. Even if two strings have the same value, they will occupy different memory locations.

---

## 8. Regular Expressions in Strings

Strings in Java support regular expressions for pattern matching, often used with methods like `matches()`, `replaceAll()`, `split()`.

### Example:

```java
String text = "apple, banana, cherry";
String[] fruits = text.split(", ");
for (String fruit : fruits) {
    System.out.println(fruit);
}
```

---

## 9. Common String Interview Questions

How is memory handled in Java for string literals and objects?
What is the difference between String, StringBuilder, and StringBuffer?
Why are strings immutable in Java?
How does the intern() method work in strings?
How can you reverse a string in Java?
Understanding strings is crucial for Java developers, as they are used frequently in many applications. Mastery over the String class, its methods, and its memory management is essential for writing efficient and performant code.
