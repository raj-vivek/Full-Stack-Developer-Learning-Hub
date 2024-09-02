# Annotations in Java

## Theory

- Annotations in Java are a form of metadata that provide data about a program but are not part of the program itself.
- They have no direct effect on the operation of the code they annotate.
- Annotations can be used for various purposes like providing information to the compiler, runtime processing, and code generation.
- Annotations can be an alternative to XML and Java marker interfaces.

### Key Points

1. **Definition**:

   - Annotations are special markers in the code that provide additional information about the code.
   - They can be applied to classes, methods, variables, parameters, and packages.

2. **Built-in Annotations**:

   - **@Override**: Indicates that a method is intended to override a method in a superclass.

     ```java
     @Override
     public String toString() {
         return "MyClass";
     }
     ```

   - **@Deprecated**: Marks a method or class as deprecated, indicating that it should no longer be used.

     ```java
     @Deprecated
     public void oldMethod() {
         // ...
     }
     ```

   - **@SuppressWarnings**: Instructs the compiler to suppress specified warnings.

     ```java
     @SuppressWarnings("unchecked")
     public void myMethod() {
         // ...
     }
     ```

3. **Custom Annotations**:

   - Creating a custom annotation involves defining an annotation type using the `@interface` keyword.
     ```java
     @interface MyAnnotation {
         String value();
     }
     ```
   - Applying a custom annotation:
     ```java
     @MyAnnotation(value = "example")
     public void myMethod() {
         // ...
     }
     ```

4. **Retention Policies**:

   - **RetentionPolicy.SOURCE**: Annotations are retained only in the source code and discarded during compilation.
   - **RetentionPolicy.CLASS**: Annotations are recorded in the class file but not available at runtime.
   - **RetentionPolicy.RUNTIME**: Annotations are recorded in the class file and available at runtime.

   Example:

   ```java
   @Retention(RetentionPolicy.RUNTIME)
   @interface MyRuntimeAnnotation {
       String value();
   }
   ```

5. **Target Types**:

   - ElementType.ANNOTATION_TYPE: Can be applied to an annotation type.
   - ElementType.CONSTRUCTOR: Can be applied to a constructor.
   - ElementType.FIELD: Can be applied to a field or property.
   - ElementType.LOCAL_VARIABLE: Can be applied to a local variable.
   - ElementType.METHOD: Can be applied to a method.
   - ElementType.PACKAGE: Can be applied to a package.
   - ElementType.PARAMETER: Can be applied to a parameter.
   - ElementType.TYPE: Can be applied to any class, interface (including annotation type), or enum declaration.

Example:

```java
@Target(ElementType.METHOD)
@interface MyMethodAnnotation {
    String value();
}
```

### 5 categories of annotations as listed:

1. Marker Annotations
2. Single value Annotations
3. Full Annotations
4. Type Annotations
5. Repeating Annotations

### Use Cases

1. Documentation: Provide additional information to the developers.
2. Runtime Processing: Used by frameworks (e.g., Spring, Hibernate) for configuration and behavior control.
3. Compile-time Processing: Used by tools and the compiler to generate code or perform checks.

### Questions

1. What are annotations in Java and what are their primary purposes?
2. Explain the differences between the retention policies in annotations.
3. Provide examples of built-in annotations and their uses.
4. How do you create and apply a custom annotation?
5. Describe the different target types for annotations and provide examples.
