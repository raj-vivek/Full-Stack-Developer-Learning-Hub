# Conditional Statements
# Conditional Statements

Conditional statements in JavaScript are used to execute different blocks of code based on certain conditions. They are fundamental for controlling the flow of a program and making decisions during runtime.

---

## Theory

Conditional statements allow a program to choose different actions based on the evaluation of conditions. By using these statements, you can direct the flow of execution to handle various scenarios, making your code more dynamic and responsive.

---

## Key Concepts

### 1. `if` Statement

The `if` statement evaluates a condition and executes a block of code if the condition is true.

#### Syntax:

```javascript
if (condition) {
  // code to execute if condition is true
}
```

#### Example:

```javascript
let age = 20;

if (age >= 18) {
  console.log("You are an adult.");
}
```

### 2. `else` Statement

The else statement follows an if statement and executes a block of code if the if condition is false.

#### Syntax:

```javascript
if (condition) {
  // code to execute if condition is true
} else {
  // code to execute if condition is false
}
```

#### Example:

```javascript
let age = 16;

if (age >= 18) {
  console.log("You are an adult.");
} else {
  console.log("You are a minor.");
}
```

### 3. `else if` Statement

The `else if` statement allows you to check multiple conditions sequentially. It follows an if statement and provides additional conditions to evaluate.

#### Syntax:

```javascript
if (condition1) {
  // code to execute if condition1 is true
} else if (condition2) {
  // code to execute if condition2 is true
} else {
  // code to execute if neither condition1 nor condition2 is true
}
```

#### Example:

```javascript
let score = 85;

if (score >= 90) {
  console.log("Grade: A");
} else if (score >= 80) {
  console.log("Grade: B");
} else if (score >= 70) {
  console.log("Grade: C");
} else {
  console.log("Grade: D");
}
```

### 4. switch Statement

The switch statement evaluates an expression and executes the corresponding case block based on the matching value.

#### Syntax:

```javascript
switch (expression) {
  case value1:
    // code to execute if expression matches value1
    break;
  case value2:
    // code to execute if expression matches value2
    break;
  // other cases
  default:
  // code to execute if no case matches
}
```

#### Example:

```javascript
let day = 3;

switch (day) {
  case 1:
    console.log("Monday");
    break;
  case 2:
    console.log("Tuesday");
    break;
  case 3:
    console.log("Wednesday");
    break;
  case 4:
    console.log("Thursday");
    break;
  case 5:
    console.log("Friday");
    break;
  case 6:
    console.log("Saturday");
    break;
  case 7:
    console.log("Sunday");
    break;
  default:
    console.log("Invalid day");
}
```

---

## Use Cases

1. **Branching Logic**: Use conditional statements to perform different actions based on user input, application state, or other variables.
2. **Validation**: Validate user input or data before proceeding with further operations.
3. **Decision Making**: Implement decision-making algorithms to handle different scenarios in applications, such as routing in web apps.

---

## Questions

1. What is the purpose of the if statement in JavaScript?

   - The if statement allows you to execute a block of code based on whether a specified condition evaluates to true.

2. How does the else statement complement the if statement?

   - The else statement provides an alternative block of code that executes when the condition in the if statement is false.

3. When would you use else if instead of multiple if statements?

   - Use else if when you need to check multiple conditions sequentially where only one of the conditions needs to be true to execute a specific block of code.

4. How does the switch statement differ from multiple if-else statements?

   - The switch statement is often used for evaluating a single expression against multiple values and is typically more readable and efficient than a series of if-else statements when dealing with multiple discrete values.

5. What is the purpose of the break statement in a switch block?

   - The break statement exits the current case block and prevents the execution from falling through to subsequent case blocks.

---

## Conclusion

Conditional statements are essential for controlling the flow of a JavaScript program based on different conditions. Mastering these constructs allows for dynamic decision-making and flexible execution paths, crucial for developing complex applications.
