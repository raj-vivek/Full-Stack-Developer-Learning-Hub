# Authentication in Spring Security

## Introduction

Authentication in Spring Security is the process of verifying the identity of a user or entity. It involves receiving credentials (e.g., username and password) and determining if they are valid. Once authenticated, Spring Security establishes a security context that holds the authentication information (such as roles and permissions) for the user during the session.

This guide covers the internal workings, configuration, and customization of authentication in Spring Security.

---

## 1. Authentication Flow in Spring Security

The basic flow for authentication in Spring Security involves the following steps:

1. **User Submits Credentials**: The user provides credentials (e.g., username and password) via a login form or API request.
2. **Credentials Sent to AuthenticationManager**: The credentials are passed to the `AuthenticationManager`, which is responsible for authenticating the user.
3. **AuthenticationProvider**: The `AuthenticationManager` delegates the authentication process to an `AuthenticationProvider`, which verifies the credentials.
4. **Success or Failure**: If authentication is successful, an `Authentication` object is created and stored in the `SecurityContext`. If authentication fails, an error is returned (e.g., 401 Unauthorized).
5. **SecurityContext**: The `SecurityContext` holds the authenticated user's information, which is available for the duration of the session or request.

### Example:

```java
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests((requests) -> requests
            .anyRequest().authenticated()
        )
        .formLogin() // Enable form-based login
        .and()
        .httpBasic(); // Enable basic authentication for APIs
    return http.build();
}
```

---

## 2. Key Components in Authentication

### 2.1 AuthenticationManager

The `AuthenticationManager` is the central interface for managing authentication. It delegates authentication to one or more `AuthenticationProvider`s.

- **Default Implementation**: In most cases, Spring Boot automatically configures an AuthenticationManager for you, but it can be customized if needed.

#### Example:

```java
@Bean
public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
    return http.getSharedObject(AuthenticationManagerBuilder.class).build();
}
```

### 2.2 AuthenticationProvider

The `AuthenticationProvider` is responsible for performing the actual authentication logic. It takes the user credentials, validates them, and returns an Authentication object upon success.

- **DaoAuthenticationProvider**: The default `AuthenticationProvider` used with the `UserDetailsService` to load user information from a database or in-memory store.
- **Custom AuthenticationProvider**: You can implement a custom `AuthenticationProvider` for complex authentication logic, such as multi-factor authentication (MFA).

Example:

```java
@Bean
public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(customUserDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
}
```

---

## 3. UserDetailsService

The `UserDetailsService` is an interface used to retrieve user-related data. It has a single method `loadUserByUsername` that is used to load the user’s details from a persistent store (e.g., database, in-memory store).

### Example:

```java
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Load user from database or in-memory store
        return new User("user", passwordEncoder().encode("password"),
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
```

In this example:

The `CustomUserDetailsService` class implements `UserDetailsService` to load user details from a custom source (such as a database).

---

## 4. Password Encoding

Password encoding is a critical part of authentication. Spring Security provides a `PasswordEncoder` interface for securely hashing and verifying passwords.

### Example:

```java
@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(); // Use BCrypt for password hashing
}
```

In this example:

- **BCryptPasswordEncoder** is a commonly used password encoder that uses the BCrypt hashing algorithm to securely store passwords.

---

## 5. Configuring Form-Based Authentication

Form-based authentication is a common way to handle user login in web applications. Spring Security provides a default login page, but you can also customize it.

### Example:

```java
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests((requests) -> requests
            .antMatchers("/public/**").permitAll() // Public pages
            .anyRequest().authenticated() // Protect all other URLs
        )
        .formLogin((form) -> form
            .loginPage("/login") // Custom login page
            .permitAll()
        )
        .logout((logout) -> logout
            .logoutUrl("/logout") // Custom logout URL
            .permitAll()
        );
    return http.build();
}
```

In this example:

- A custom login page is provided at `/login`.
- All other URLs are protected and require the user to be authenticated.

---

## 6. Basic Authentication for APIs

For RESTful APIs, basic authentication is often used where the client provides credentials (e.g., username and password) in the HTTP request header.

### Example:

```java
@Bean
public SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf().disable() // Disable CSRF protection for APIs
        .authorizeHttpRequests((requests) -> requests
            .antMatchers("/api/public/**").permitAll() // Public API endpoints
            .antMatchers("/api/private/**").authenticated() // Protected API endpoints
        )
        .httpBasic(); // Use HTTP Basic Authentication
    return http.build();
}
```

In this example:

- Public API endpoints are accessible without authentication, while private endpoints require authentication using basic authentication.

---

## 7. JWT (JSON Web Token) Authentication

JWT is a widely-used token format for securing REST APIs. Spring Security supports JWT-based authentication, where a token is sent in the `Authorization` header and validated on each request.

### Example: JWT Filter

```java
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            String username = JwtUtil.validateTokenAndGetUsername(token);

            if (username != null) {
                UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(username, null, List.of(new SimpleGrantedAuthority("ROLE_USER")));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
```

In this example:

- The JwtAuthenticationFilter extracts and validates the JWT token from the Authorization header and sets the user’s authentication in the SecurityContext.

---

## 8. Remember-Me Authentication

Remember-me authentication allows users to stay logged in across browser sessions without needing to re-authenticate.

### Example:

```java
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests((requests) -> requests
            .anyRequest().authenticated()
        )
        .formLogin()
        .and()
        .rememberMe()
        .tokenValiditySeconds(86400); // Token valid for 1 day
    return http.build();
}
```

In this example:

- Remember-me is enabled, and the token is valid for 1 day (86400 seconds).

---

## 9. Logout

Spring Security provides built-in support for logging out users, invalidating their session, and removing cookies.

### Example:

```java
http
    .logout((logout) -> logout
        .logoutUrl("/logout")
        .logoutSuccessUrl("/login?logout")
        .invalidateHttpSession(true)
        .deleteCookies("JSESSIONID")
    );
```

In this example:

- Users are redirected to the login page upon logout.
- The session is invalidated, and the `JSESSIONID` cookie is deleted to ensure the user is fully logged out.

## 10. Custom Authentication Provider

You can create custom authentication logic by implementing the `AuthenticationProvider` interface. This is useful for integrating external systems (e.g., LDAP, OAuth, SSO) or custom user stores.

### Example:

```java
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        // Custom logic for validating username and password
        if ("customuser".equals(username) && "custompassword".equals(password)) {
            return new UsernamePasswordAuthenticationToken(username, password, List.of(new SimpleGrantedAuthority("ROLE_USER")));
        } else {
            throw new BadCredentialsException("Authentication failed");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
```

In this example:

- The `CustomAuthenticationProvider` class implements custom logic for validating the user's credentials.

---

## Questions

1. What is the purpose of the AuthenticationManager in Spring Security?

   - The AuthenticationManager handles authentication by delegating to one or more AuthenticationProviders to verify the user's credentials.

2. How does the AuthenticationProvider work in Spring Security?
3. What role does the UserDetailsService play in authentication?
4. How is password encoding handled in Spring Security?
5. How do you configure form-based authentication in Spring Security?
6. What is HTTP Basic Authentication, and how is it used in Spring Security for securing APIs?
7. What is JWT authentication, and how does it integrate with Spring Security?
8. How does Spring Security handle remember-me authentication?
9. What happens during the logout process in Spring Security?
10. How can you create a custom AuthenticationProvider in Spring Security?
