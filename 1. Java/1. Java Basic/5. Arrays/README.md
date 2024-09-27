# Arrays in Java

Arrays in Java are a fundamental data structure used to store multiple values of the same type in a single variable. They are indexed collections of data, meaning each element is accessed by its numerical index.

## 1. Key Characteristics of Arrays:

- **Fixed size**: The size of an array is defined when it is created and cannot be changed.
- **Homogeneous**: All elements in an array must be of the same type.
- **Indexed**: Elements are accessed via a 0-based index.

## 2. Initializing an Array

An array can be initialized at the time of declaration:

```java
int[] myArray = new int[5]; // Creates an array of size 5
```

Or initialized with values directly:

```java
int[] myArray = {1, 2, 3, 4, 5};
```

## 3. Multidimensional Arrays (Array of Arrays)

### 3.1 Two-Dimensional Arrays

```java
int[][] matrix = new int[3][3];  // A 3x3 matrix
matrix[0][0] = 1;
matrix[0][1] = 2;
matrix[1][1] = 3;
```

#### Initialization with Values

```java
int[][] matrix = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};
```

### 3.2 Jagged Arrays

Jagged arrays are arrays of arrays where the inner arrays can have different lengths.

```java
int[][] jaggedArray = new int[3][];
jaggedArray[0] = new int[2];
jaggedArray[1] = new int[4];
jaggedArray[2] = new int[3];
```

## 4. Common Array Operations

### 4.1 Traversing an Array

```java
int[] myArray = {1, 2, 3, 4, 5};
for (int i = 0; i < myArray.length; i++) {
    System.out.println(myArray[i]);
}
```

### 4.2 Enhanced For Loop

```java
for (int num : myArray) {
    System.out.println(num);
}
```

### 4.3 `myArray.length`

```java
int length = myArray.length;
```

## 5. Arrays Utility Class

Java provides the `java.util.Arrays` class, which includes several utility methods for working with arrays.

### 5.1 `Arrays.copyOf()`

```java
int[] copy = Arrays.copyOf(original, original.length);
```

## 5.2. `Arrays.sort()`

Ascending order

```java
Arrays.sort(numbers);
```

### 5.3 Sorting in Descending Order

```java
Arrays.sort(numbers, Collections.reverseOrder());
```

## 5.4. `Arrays.binarySearch()`

```java
int index = Arrays.binarySearch(numbers, 3);
System.out.println("Index of 3: " + index);  // Output: 2
```

### 5.5 `Arrays.toString()`:

```java
int[] arr = {1, 2, 3};
System.out.println(Arrays.toString(arr));  // Output: [1, 2, 3]
```

### 5.6 `Arrays.equals()`:

```java
int[] arr1 = {1, 2, 3};
int[] arr2 = {1, 2, 3};
System.out.println(Arrays.equals(arr1, arr2));  // Output: true
```

### 5.7 `Arrays.fill()`:

Fills an array with a specific value.

```java
int[] arr = new int[5];
Arrays.fill(arr, 9);
System.out.println(Arrays.toString(arr));  // Output: [9, 9, 9, 9, 9]
```

## 6. Cloning Arrays

An array can be cloned using the `clone()` method too:

```java
int[] original = {1, 2, 3};
int[] clone = original.clone();
System.out.println(Arrays.toString(clone));  // Output: [1, 2, 3]
```

- Use `clone()` when performance matters.
- Use `Arrays.copyOf()` when you need to adjust the array length.

## 7. Limitations of Arrays

- **Fixed size**: Once an array is created, its size cannot be changed.
- **Homogeneous**: All elements in the array must be of the same type.
- **Primitive Wrapping**: Arrays of primitive types cannot store null values, unlike arrays of objects.

## 8. Interview Questions

1. What are the key differences between an array and an ArrayList in Java?
2. How do you declare and initialize a multidimensional array?
3. How does Java handle memory allocation for arrays?
4. What are the differences between Arrays.sort() and Collections.sort()?
5. Explain the concept of jagged arrays in Java.
6. What is the difference between clone() and System.arraycopy()?
