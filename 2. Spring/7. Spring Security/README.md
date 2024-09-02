# Spring Security

## Theory

Spring Security is a powerful and customizable framework for securing Java applications. It provides comprehensive security services for Java EE-based enterprise software applications. Its primary features include authentication, authorization, and protection against common security vulnerabilities.

### 1. Core Concepts

1. **Authentication**:

   - **Definition**: The process of verifying the identity of a user or system.
   - **Mechanisms**: Username and password, OAuth2, LDAP, JWT.

2. **Authorization**:

   - **Definition**: The process of granting or denying access to resources based on user roles or permissions.
   - **Mechanisms**: Role-based access control (RBAC), attribute-based access control (ABAC).

3. **Security Filters**:

   - **Definition**: Filters that intercept HTTP requests to apply security measures before they reach the application.
   - **Examples**: Authentication filter, authorization filter, CSRF protection filter.

4. **Security Context**:

   - **Definition**: Holds authentication and authorization information for the current user.
   - **Mechanisms**: `SecurityContextHolder`, `Authentication` object.

5. **Password Encoding**:
   - **Definition**: Mechanism to encode passwords for secure storage.
   - **Examples**: BCrypt, PBKDF2.

### 2. Configuring Spring Security

1. **Java Configuration**:

   - **Definition**: Configuring security using Java-based configuration.
   - **Example**: Extending `WebSecurityConfigurerAdapter` and overriding methods like `configure(HttpSecurity http)`.

2. **XML Configuration**:

   - **Definition**: Configuring security using XML files.
   - **Example**: `<http>` and `<authentication-provider>` elements in `security-config.xml`.

3. **Method Security**:
   - **Definition**: Applying security constraints to method-level access.
   - **Examples**: `@Secured`, `@PreAuthorize`, `@PostAuthorize`.

### 3. Authentication Mechanisms

1. **Basic Authentication**:

   - **Definition**: Simple authentication scheme using HTTP headers.
   - **Example**: `Authorization: Basic <credentials>`.

2. **Form-Based Authentication**:

   - **Definition**: Authentication using a login form and credentials.
   - **Configuration**: Custom login page, login processing URL.

3. **OAuth2**:

   - **Definition**: Framework for delegated access.
   - **Mechanisms**: Authorization Code Grant, Implicit Grant, Resource Owner Password Credentials.

4. **LDAP Authentication**:

   - **Definition**: Authentication against an LDAP directory.
   - **Configuration**: `LdapUserDetailsManager` and LDAP properties.

5. **JWT (JSON Web Tokens)**:
   - **Definition**: Token-based authentication using JSON Web Tokens.
   - **Configuration**: Token generation, parsing, and validation.

### 4. Authorization

1. **Role-Based Access Control (RBAC)**:

   - **Definition**: Granting access based on user roles.
   - **Configuration**: Use `@PreAuthorize("hasRole('ROLE_USER')")`.

2. **Attribute-Based Access Control (ABAC)**:

   - **Definition**: Granting access based on attributes or policies.
   - **Configuration**: Use `@PreAuthorize` with SpEL (Spring Expression Language).

3. **Method Security**:
   - **Definition**: Securing methods with annotations.
   - **Examples**: `@Secured("ROLE_ADMIN")`, `@PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")`.

### 5. Security Best Practices

1. **Secure Password Storage**:

   - **Definition**: Use strong hashing algorithms for storing passwords.
   - **Examples**: BCrypt, Argon2.

2. **CSRF Protection**:

   - **Definition**: Protecting against Cross-Site Request Forgery attacks.
   - **Configuration**: Enable CSRF protection in `HttpSecurity`.

3. **Secure Session Management**:

   - **Definition**: Manage sessions securely to prevent session fixation attacks.
   - **Configuration**: Use secure cookies, configure session timeout.

4. **Exception Handling**:
   - **Definition**: Handle security-related exceptions gracefully.
   - **Configuration**: Customize error pages and exception handling.

### 6. Spring Security Features

1. Authorization
2. Single sign-on
3. Software Localization
4. Remember-me
5. LDAP (Lightweight Directory Access Protocol)
6. JAAS (Java Authentication and Authorization Service) LoginModule
7. Web Form Authentication
8. Digest Access Authentication
9. HTTP Authorization
10. Basic Access Authentication

Explanations:

1. Authorization:

   - This functionality is provided by Spring Security and allows the user to be authorized before accessing resources. It enables developers to set access controls for resources.

2. Single sign-on:

   - This feature allows a user to utilize a single account to access different apps (user name and password).

3. Software Localization:

   - This capability enables us to create user interfaces for applications in any language.

4. Remember-me:

   - With the help of HTTP Cookies, Spring Security provides this capability. It remembers the user and prevents them from logging in from the same workstation until they log out.

5. LDAP (Lightweight Directory Access Protocol):

   - That is an open application protocol for managing and interacting with dispersed directory information services over the Internet Protocol.

6. JAAS (Java Authentication and Authorization Service) LoginModule:

   - This is a Java-based Pluggable Authentication Module. It is supported by Spring Security’s authentication procedure.

7. Web Form Authentication:

   - Web forms capture and authenticate user credentials from the web browser during this procedure. While we wish to build web form authentication, Spring Security supports it.

8. Digest Access Authentication:

   - We can make the authentication procedure more secure with this functionality than with Basic Access Authentication. Before delivering sensitive data over the network, it requests that the browser verify the user’s identity.

9. HTTP Authorization:

   - Using Apache Ant paths or regular expressions, Spring provides this functionality for HTTP authorization of web request URLs.

10. Basic Access Authentication:

    - Spring Security has support for Basic Access Authentication, which is used to give a user name and password when performing network requests.

### 7. Features Added in Spring Security 6.0

1. OAuth 2.0 Login:

   - This feature allows users to connect to the app using their current GitHub or Google accounts. The Authorization Code Grant defined in the OAuth 2.0 Authorization Framework is used to implement this functionality.

2. Reactive Support:

   - Spring Security 6.0 adds support for reactive programming and reactive web runtimes, as well as the ability to interact with Spring WebFlux.

3. Modernized Password Encoding:

   - Spring Security 6.0 introduces the DelegatingPasswordEncoder, a new way to store passwords. The format for storing passwords is: {id} encodedPassword. List of ids f

### 8. Important Terms

1. Authentication

   - The identity of users is verified for providing the access to the system. If the user is verified as per the saved credentials, the request is accepted and the desired response is given to the client from the server. Some of the methods are as follows:

     1. Login Form: It is a web page to a website that requires user identification and authentication, performed by entering a username and password.

     2. HTTP authentication – In this, the server can request authentication information (user ID and password) from a client.

     3. Customer Authentication Method – customer authentication is a new regulation designed to prevent online transaction fraud.

2. Authorization

   - Giving the user to permission to access a specific resource or function. Some of the methods: –

     1. Access Control for URLs – Security of URLs allows you to restrict access to specific Internet sites based on the contents of the URL(keywords).

     2. Secure Objects and Methods – The Class method is called by a security interceptor implementation to ensure that the configured AccessDecisionManager supports the type of secure object or not.

     3. Access Control Lists – An ACLs specifies which users are granted access to objects, as well as what operations are allowed to them.

3. Filter

   - A filter is a function that is invoked at the time of preprocessing and postprocessing of a request. Spring Security maintains a filter chain where all filters have different responsibilities and filters are added or removed depending on which services are required.

## Use Cases

- **Enterprise Applications**: Secure web applications with comprehensive authentication and authorization mechanisms.
- **REST APIs**: Protect RESTful services with token-based authentication and role-based access.
- **Single Sign-On (SSO)**: Integrate with SSO providers for unified authentication.

## Questions

1. What are the core concepts of Spring Security?
2. How do you configure authentication and authorization in Spring Security?
3. Describe the different authentication mechanisms supported by Spring Security.
4. How can you implement method-level security using annotations?
5. What are some best practices for securing an application with Spring Security?
