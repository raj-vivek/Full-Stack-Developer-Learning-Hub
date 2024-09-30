# Symbol in JavaScript

## Introduction

In JavaScript, **Symbol** is a unique and immutable primitive data type introduced in ECMAScript 6 (ES6). Symbols are primarily used as keys for object properties to ensure that property keys are unique, avoiding name collisions. Unlike strings or numbers, each symbol is unique, even if they have the same description.

---

## Key Concepts

### 1. Creating Symbols

You can create a new symbol by calling the `Symbol()` function. Every time you create a symbol, it generates a unique value, even if you provide the same description.

#### Example:

```javascript
const sym1 = Symbol("description");
const sym2 = Symbol("description");

console.log(sym1 === sym2); // Output: false
```

In this example, sym1 and sym2 are two distinct symbols, despite having the same description.

### 2. Using Symbols as Object Property Keys

Symbols can be used as keys for object properties. This is useful when you want to ensure that a property key is unique and won’t conflict with other keys.

#### Example:

```javascript
const sym = Symbol("uniqueKey");
const person = {
  name: "John",
  [sym]: "privateData", // Using a symbol as a property key
};

console.log(person.name); // Output: John
console.log(person[sym]); // Output: privateData
```

In this example, sym is used as a unique key for the person object. The symbol key ensures that the privateData property won’t be accidentally overwritten by other properties.

### 3. Symbols are Not Enumerable

Properties keyed by symbols are not included in loops like `for...in` or `Object.keys()`. This makes them useful for defining "hidden" or non-enumerable properties.

#### Example:

```javascript
const sym = Symbol("hidden");
const user = {
  name: "Alice",
  [sym]: "secret",
};

for (let key in user) {
  console.log(key); // Output: name (symbol-keyed properties are not logged)
}

console.log(Object.keys(user)); // Output: ["name"]
```

In this example, the symbol-keyed property is not iterated over or listed by Object.keys().

### 4. Symbol.for() and Symbol.keyFor()

The Symbol.for() method allows you to create or retrieve a symbol from a global symbol registry. Unlike regular symbols, symbols created with Symbol.for() are shared across the entire codebase, ensuring they can be referenced from different parts of the program.

#### Example:

```javascript
const sym1 = Symbol.for("sharedSymbol");
const sym2 = Symbol.for("sharedSymbol");

console.log(sym1 === sym2); // Output: true (same symbol from the global registry)
```

The Symbol.keyFor() method returns the key associated with a symbol in the global symbol registry.

#### Example:

```javascript
const sym = Symbol.for("globalSymbol");
console.log(Symbol.keyFor(sym)); // Output: globalSymbol
```

## Built-in Well-Known Symbols

JavaScript includes several well-known symbols that represent internal language behaviors. These symbols are used to customize how objects interact with certain operations like iteration, string conversion, and more.

### Common Well-Known Symbols:

1. `Symbol.iterator`: Defines the default iterator for an object (used by for...of loops).

   ```javascript
   const iterable = {
     [Symbol.iterator]() {
       let step = 0;
       return {
         next() {
           step++;
           if (step <= 3) {
             return { value: step, done: false };
           } else {
             return { done: true };
           }
         },
       };
     },
   };

   for (let value of iterable) {
     console.log(value); // Output: 1, 2, 3
   }
   ```

2. `Symbol.toStringTag`: Changes the default tag of an object when Object.prototype.toString() is called.

   ```javascript
   const person = {
     [Symbol.toStringTag]: "Person",
   };

   console.log(Object.prototype.toString.call(person)); // Output: [object Person]
   ```

3. `Symbol.toPrimitive`: Defines how an object is converted to a primitive value.

   ```javascript
   const car = {
     make: "Toyota",
     [Symbol.toPrimitive](hint) {
       return hint === "string" ? this.make : 2021;
     },
   };

   console.log(`${car}`); // Output: Toyota
   console.log(+car); // Output: 2021
   ```

## Code Examples

### 1. Using Symbols as Property Keys

```javascript
const sym = Symbol("id");
const user = {
  name: "Bob",
  [sym]: 12345,
};

console.log(user.name); // Output: Bob
console.log(user[sym]); // Output: 12345
```

### 2. Enumerability of Symbols

```javascript
const sym = Symbol("hidden");
const obj = {
  visible: "This is visible",
  [sym]: "This is hidden",
};

console.log(Object.keys(obj)); // Output: ["visible"]
for (let key in obj) {
  console.log(key); // Output: visible
}
```

### 3. Symbol in Global Registry

```javascript
const sym1 = Symbol.for("shared");
const sym2 = Symbol.for("shared");

console.log(sym1 === sym2); // Output: true (same symbol)
console.log(Symbol.keyFor(sym1)); // Output: shared
```

## Use Cases

1. **Unique Object Keys**: Use symbols when you need to ensure that object property keys are unique and won’t conflict with other keys, such as in libraries or frameworks.

2. **Private/Hidden Propertie**s: Symbols can be used to define properties that are not enumerated by default loops or object methods, which is useful for creating "private" fields.

3. **Custom Iterators**: Use Symbol.iterator to define custom iteration behavior for objects, enabling them to work with for...of loops and other iterable protocols.

4. **Customizing Object Behavior**: Leverage well-known symbols like Symbol.toStringTag and Symbol.toPrimitive to control how objects are represented or converted in certain operations.

## Interview Questions

1.  What is a symbol in JavaScript?

    - A symbol is a unique and immutable primitive data type used primarily as object property keys to avoid name collisions.

2.  How do symbols differ from other JavaScript data types like strings or numbers?

    - Unlike strings or numbers, each symbol is unique, even if two symbols have the same description. Symbols also create non-enumerable properties, which means they won’t show up in `for...in` loops or `Object.keys()`.

3.  How do you create a symbol, and how is it used as an object key?

    - A symbol is created using the `Symbol()` function. To use it as an object key, you must wrap the symbol in square brackets when defining the object property.

4.  What are well-known symbols, and how are they used in JavaScript?

    - Well-known symbols are predefined symbols in JavaScript that control specific internal behaviors, like `Symbol.iterator` for defining custom iterators and `Symbol.toStringTag` for customizing how objects are represented in string form.

5.  What is the difference between `Symbol() `and `Symbol.for()`?

    - Symbol() creates a new unique symbol each time it is called. `Symbol.for()` checks the global symbol registry for a symbol with the provided key and returns it if found, otherwise it creates and registers a new symbol.

## Conclusion

Symbols provide a powerful way to create unique object keys and customize object behaviors in JavaScript. They are particularly useful for avoiding property name collisions, defining non-enumerable properties, and working with well-known symbols for customization of built-in operations.
