# JavaScript Objects

## Introduction

In JavaScript, **objects** are collections of key-value pairs, where each key is a string (or `Symbol`) and the value can be any data type, including another object or function. Objects are fundamental in JavaScript for structuring data and building complex data models. Everything in JavaScript, except primitive types, is an object, including arrays and functions.

---

## Key Concepts

### 1. **Object Creation**

There are several ways to create objects in JavaScript:

#### a. **Object Literal**

The simplest and most common way to create an object is using an object literal.

```javascript
const person = {
  name: "John",
  age: 30,
  greet: function () {
    console.log("Hello, my name is " + this.name);
  },
};

person.greet(); // Output: Hello, my name is John
```

#### b. Object Constructor

Objects can also be created using the `new Object()` syntax.

```javascript
const car = new Object();
car.make = "Toyota";
car.model = "Corolla";
car.year = 2021;

console.log(car); // Output: { make: 'Toyota', model: 'Corolla', year: 2021 }
```

#### c. Constructor Function

A constructor function is used to create multiple objects with similar properties and methods.

```javascript
function Person(name, age) {
  this.name = name;
  this.age = age;
  this.greet = function () {
    console.log("Hello, I'm " + this.name);
  };
}

const alice = new Person("Alice", 25);
alice.greet(); // Output: Hello, I'm Alice
```

#### d. Object.create() Method

The Object.create() method allows you to create a new object using an existing object as a prototype.

```javascript
const animal = {
  type: "Mammal",
  makeSound() {
    console.log("Roar!");
  },
};

const lion = Object.create(animal);
lion.makeSound(); // Output: Roar!
console.log(lion.type); // Output: Mammal (inherited from prototype)
```

### 2. Accessing and Modifying Object Properties

- Properties are defined as key-value pairs, where the key is a string or symbol and the value can be any data type, including numbers, strings, booleans, functions, arrays, or other objects.
- You can access or modify object properties using dot notation or bracket notation.

```javascript
const student = {
  name: "Emily",
  age: 20,
};

// Dot notation
console.log(student.name); // Output: Emily

// Bracket notation
console.log(student["age"]); // Output: 20

// Modifying properties
student.name = "Emma";
student["age"] = 21;
```

### 3. Property Attributes

In JavaScript, each property has attributes that define its behavior:

1. `value`: The property’s value.
2. `writable`: When true, the property’s value can be changed
3. `enumerable`: When true, the property can be iterated over by “for-in” enumeration. Otherwise, the property is said to be non-enumerable.
4. `configurable`: If false, attempts to delete the property, change the property to be an access-or property, or change its attributes (other than [[Value]], or changing [[Writable]] to false) will fail.

```javascript
let person = {
  name: "John",
};
Object.defineProperty(person, "name", {
  writable: false,
  enumerable: true,
  configurable: false,
});
```

### 4. Iterating Over All Keys of an Object

To iterate over all enumerable properties of an object, you can use the `for…in` loop. This loop will iterate over both the object’s own properties and any enumerable properties it has inherited.

```javascript
let person = {
  gender: "male",
};

let person1 = Object.create(person);
person1.name = "Adam";
person1.age = 45;
person1.nationality = "Australian";

for (let key in person1) {
  // Output : name, age, nationality and gender
  console.log(key);
}
```

### 5. Adding and Deleting Properties

Properties can be added or deleted dynamically in JavaScript objects.

```javascript
const car = {
  make: "Toyota",
  model: "Corolla",
};

// Adding a property
car.year = 2021;

// Deleting a property
delete car.model;

console.log(car); // Output: { make: 'Toyota', year: 2021 }
```

### 6. Methods

Objects can contain functions, known as methods, which operate on the data within the object.

```javascript
const book = {
  title: "JavaScript Essentials",
  getSummary: function () {
    return `${this.title} is a great book to learn JavaScript.`;
  },
};

console.log(book.getSummary()); // Output: JavaScript Essentials is a great book to learn JavaScript.
```

### 7. The `this` Keyword

In JavaScript objects, `this` refers to the object in which the method is called. It provides a way to access object properties within methods.

```javascript
const user = {
  name: "Alice",
  greet() {
    console.log(`Hello, my name is ${this.name}`);
  },
};

user.greet(); // Output: Hello, my name is Alice
```

---

## Object Methods and Properties

### 1. `Object.keys()`

Returns an array of a given object’s property names (keys).

```javascript
const user = { name: "John", age: 30 };
console.log(Object.keys(user)); // Output: ['name', 'age']
```

### 2. `Object.values()`

Returns an array of the object’s property values.

```javascript
const user = { name: "John", age: 30 };
console.log(Object.values(user)); // Output: ['John', 30]
```

### 3. `Object.entries()`

Returns an array of the object’s key-value pairs as arrays.

```javascript
const user = { name: "John", age: 30 };
console.log(Object.entries(user)); // Output: [['name', 'John'], ['age', 30]]
```

### 4. `Object.assign()`

Copies the values of all enumerable properties from one or more source objects to a target object.

```javascript
const target = { a: 1, b: 2 };
const source = { b: 4, c: 5 };
const result = Object.assign(target, source);

console.log(result); // Output: { a: 1, b: 4, c: 5 }
```

### 5. `Object.freeze()`

Prevents the modification of existing property values of an object.

```javascript
const user = { name: "John", age: 30 };
Object.freeze(user);
user.age = 31; // This will not change the object
console.log(user.age); // Output: 30
```

### 6. `Object.seal()`

Prevents the addition or removal of properties but allows modification of existing properties.

```javascript
const user = { name: "John", age: 30 };
Object.seal(user);
user.age = 31; // This works
delete user.name; // This will not work
console.log(user); // Output: { name: "John", age: 31 }
```

---

## Prototypes and Inheritance

Every JavaScript object has a hidden internal property called [[Prototype]] that allows for prototypal inheritance. This mechanism allows objects to inherit properties and methods from other objects.

### Example of Prototype Inheritance

```javascript
const animal = {
  eats: true,
};

const dog = Object.create(animal);
dog.barks = true;

console.log(dog.eats); // Output: true (inherited from animal)
console.log(dog.barks); // Output: true
```

### Understanding the Prototype Chain

If a property or method does not exist in an object, JavaScript looks for it in the object’s prototype. This is called the prototype chain.

---

## Code Examples

### 1. Object Literal Example

```javascript
const laptop = {
  brand: "Apple",
  model: "MacBook Pro",
  year: 2021,
  getDetails() {
    return `${this.brand} ${this.model}, ${this.year}`;
  },
};

console.log(laptop.getDetails()); // Output: Apple MacBook Pro, 2021
```

### 2. Constructor Function Example

```javascript
function Laptop(brand, model, year) {
  this.brand = brand;
  this.model = model;
  this.year = year;
  this.getDetails = function () {
    return `${this.brand} ${this.model}, ${this.year}`;
  };
}

const myLaptop = new Laptop("Apple", "MacBook Pro", 2021);
console.log(myLaptop.getDetails()); // Output: Apple MacBook Pro, 2021
```

### 3. Prototype Example

```javascript
const animal = {
  eats: true,
};

function Dog(name) {
  this.name = name;
}

Dog.prototype = animal;

const myDog = new Dog("Buddy");
console.log(myDog.eats); // Output: true (inherited from animal)
```

---

## Use Cases

1. **Modeling Real-World Entities**: Objects are ideal for modeling entities such as users, products, or vehicles with specific attributes and methods.

2. **Dynamic Data Structures**: Use objects when you need to dynamically add, modify, or remove properties during runtime.

3. **Prototypal Inheritance**: Prototypes allow you to create objects that share properties and methods, making them more memory-efficient by avoiding duplication of methods across objects.

---

## Interview Questions

1. What is an object in JavaScript, and how do you create one?

   - An object is a collection of key-value pairs. You can create one using object literals, the `new Object()` syntax, constructor functions, or `Object.create()`.

2. What is the difference between dot notation and bracket notation when accessing properties in objects?

   - Dot notation is used for static property names, while bracket notation allows for dynamic property access (e.g., properties with special characters or from variables).

3. What is the role of the `this` keyword in JavaScript objects?

   - Inside a method, `this` refers to the object that owns the method, allowing access to the object’s properties and methods.

4. What is prototypal inheritance in JavaScript?

   - Prototypal inheritance is a mechanism where an object can inherit properties and methods from another object, known as its prototype.

5. What are some useful object methods provided by JavaScript?

   - Some useful methods include `Object.keys()`, `Object.values()`, `Object.assign()`, `Object.freeze()`, and `Object.create()`.

---

## Conclusion

Objects are a core feature in JavaScript that allow developers to structure data and build models representing real-world entities
