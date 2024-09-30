# Internal Working of Spring Security

## Introduction

Spring Security is a powerful and flexible security framework that integrates with Spring-based applications to provide authentication, authorization, and protection against common security vulnerabilities. Understanding the internal working of Spring Security helps in customizing and extending it according to specific application requirements.

This guide covers how Spring Security works behind the scenes, including key components such as filters, authentication providers, security context, and how requests are processed.

---

## 1. Security Filter Chain

### What is the Security Filter Chain?

At the core of Spring Security is a chain of security filters, collectively known as the **Security Filter Chain**. Each request passes through this chain, where each filter performs a specific security task such as authentication, authorization, or session management. The filters are executed in order, and each can decide whether to allow the request to proceed further or block it.

### Key Filters in Spring Security

- **`UsernamePasswordAuthenticationFilter`**: Handles form-based authentication and processes login requests.
- **`BasicAuthenticationFilter`**: Handles HTTP Basic authentication.
- **`BearerTokenAuthenticationFilter`**: Handles authentication using bearer tokens such as JWTs.
- **`ExceptionTranslationFilter`**: Translates security exceptions into responses, such as handling access denied errors.
- **`SecurityContextPersistenceFilter`**: Retrieves the `SecurityContext` from storage (e.g., session) and restores it for the current request.
- **`LogoutFilter`**: Handles user logout functionality.

### Filter Chain Example

```java
http
    .authorizeHttpRequests((requests) -> requests
        .anyRequest().authenticated()  // All requests must be authenticated
    )
    .formLogin()  // Enable form-based login
    .logout();    // Enable logout functionality
```

In this example:

- The request must be authenticated using form-based login before accessing any URL.

---

## 2. Authentication

### How Does Authentication Work?

Authentication is the process of verifying the identity of the user. Spring Security handles authentication by delegating to an AuthenticationProvider that compares the credentials provided by the user with stored credentials (e.g., username/password from a database or LDAP).

### Key Components of Authentication

- `AuthenticationManager`: Delegates the authentication request to one or more `AuthenticationProviders`.
- `AuthenticationProvider`: Performs authentication by comparing credentials (e.g., password) with stored credentials.
- `UserDetailsService`: A service that loads user-specific data, such as retrieving user details from a database.

### Authentication Flow

1. The user submits their credentials (e.g., username and password).
2. The credentials are passed to the `AuthenticationManager`.
3. The `AuthenticationManager` delegates the authentication process to one or more `AuthenticationProvider`s.
4. If authentication is successful, an `Authentication` object is created and stored in the `SecurityContext`.

### Example:

```java
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests((requests) -> requests
            .anyRequest().authenticated()
        )
        .formLogin()
        .loginPage("/login")
        .permitAll();
    return http.build();
}

@Bean
public UserDetailsService userDetailsService() {
    UserDetails user = User.withDefaultPasswordEncoder()
        .username("user")
        .password("password")
        .roles("USER")
        .build();
    return new InMemoryUserDetailsManager(user);
}
```

---

## 3. SecurityContext and SecurityContextHolder

### What is the SecurityContext?

The SecurityContext is a container that holds the authentication information (i.e., the `Authentication` object) for the current request. This is where Spring Security stores the details of the authenticated user.

- SecurityContextHolder: A static holder that provides access to the SecurityContext. This is where Spring Security retrieves and stores the authentication information for the currently authenticated user.
  Example:

```java
Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
String username = authentication.getName();
In this example, the SecurityContextHolder is used to retrieve the current user's authentication information.
```

---

## 4. Authorization

### How Does Authorization Work?

Authorization determines whether the authenticated user has permission to access a specific resource or perform an action. After authentication, Spring Security checks the user's roles or authorities to decide if they are allowed to access the requested resource.

### Key Components of Authorization

- `AccessDecisionManager`: Decides whether access should be granted based on the user's roles/authorities and the requested resource.
- `AccessDecisionVoter`: Votes on whether to grant access to the resource based on specific conditions (e.g., role-based or attribute-based access control).
  Example:

```java
http
    .authorizeHttpRequests((requests) -> requests
        .antMatchers("/admin/**").hasRole("ADMIN")
        .anyRequest().authenticated()
    );
```

In this example:

The `/admin/**` URL pattern is restricted to users with the `ADMIN` role, and all other requests require authentication.

---

## 5. AuthenticationManager and AuthenticationProvider

### What is AuthenticationManager?

The **AuthenticationManager** is the central interface in Spring Security for handling authentication. It delegates authentication to one or more `AuthenticationProvider`s, which do the actual verification of credentials.

### What is AuthenticationProvider?

An **AuthenticationProvider** is responsible for verifying the user's credentials and returning an Authentication object if the authentication is successful.

- **DaoAuthenticationProvider**: One of the commonly used AuthenticationProviders, which retrieves user details via UserDetailsService and verifies credentials (e.g., password).
- **LdapAuthenticationProvider**: Used for authentication against an LDAP server.
  Example:

```java
@Bean
public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
    return http
        .getSharedObject(AuthenticationManagerBuilder.class)
        .authenticationProvider(new DaoAuthenticationProvider())
        .build();
}
```

---

## 6. Exception Handling

Spring Security provides built-in mechanisms to handle security-related exceptions such as unauthorized access or authentication failures.

### Key Exceptions:

- `AccessDeniedException`: Thrown when a user does not have permission to access a resource.
- `AuthenticationException`: Thrown when authentication fails (e.g., incorrect username/password).

### Handling Exceptions

The `ExceptionTranslationFilter` intercepts exceptions like `AccessDeniedException` and forwards the request to an appropriate handler.

Example:

```java
http
    .exceptionHandling()
    .accessDeniedPage("/403");  // Custom access denied page
```

---

## 7. Customizing Spring Security

You can customize Spring Security by extending its core components, such as `UserDetailsService`, `AuthenticationProvider`, and Authentic`ationManager.

### Custom UserDetailsService

```java
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Logic to load user from a database or another source
        return new User(username, "password", Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
```

In this example:

- The `CustomUserDetailsService` class loads user details from a custom source, such as a database.

---

## 8. Filters in Depth

The filters in the Spring Security filter chain are executed in a specific order. Here are some key filters and their purpose:

- `SecurityContextPersistenceFilter`: Ensures the `SecurityContext` is available throughout the lifecycle of the request.
- `AnonymousAuthenticationFilter`: Provides a default anonymous authentication object when no user is authenticated.
- `BasicAuthenticationFilter`: Handles HTTP Basic authentication.
- `UsernamePasswordAuthenticationFilter`: Processes login forms and attempts authentication.

### Custom Filter Example

```java
public class CustomFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Custom logic
        filterChain.doFilter(request, response);
    }
}

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .addFilterBefore(new CustomFilter(), UsernamePasswordAuthenticationFilter.class);
    return http.build();
}
```

---

## Questions

1. What is the role of the Security Filter Chain in Spring Security?

   - The Security Filter Chain processes each request and applies security filters in a predefined order to handle authentication, authorization, and other security-related concerns.

2. How does Spring Security handle authentication?

   - Spring Security uses the AuthenticationManager, AuthenticationProvider, and UserDetailsService to verify user credentials and create an Authentication object.

3. What is the SecurityContext in Spring Security?

   - The SecurityContext holds the authentication information (e.g., user details) for the current request and is accessed via SecurityContextHolder.

4. How can you customize authentication in Spring Security?

   - You can customize authentication by implementing UserDetailsService, AuthenticationProvider, or custom filters to meet your application's requirements.
