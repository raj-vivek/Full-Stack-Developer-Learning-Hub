# Authorization in Spring Security

## Introduction

Authorization in Spring Security is the process of determining whether a user or entity has the necessary permissions to access a resource or perform a specific action. Spring Security uses roles and authorities to manage access control. After a user is authenticated, Spring Security checks the user’s roles/authorities to determine whether they can access certain endpoints or perform actions.

---

## 1. Authorization Flow in Spring Security

1. **Authentication**: The user’s identity is verified through authentication.
2. **SecurityContext**: The authenticated user’s roles/authorities are stored in the `SecurityContext`.
3. **Access Decision**: Spring Security checks the user’s roles or permissions against the requested resource (e.g., URL, method).
4. **Decision Making**: Based on the user’s roles, Spring Security either grants or denies access to the resource.

---

## 2. Role-Based Authorization

Role-based authorization restricts access to resources based on the roles assigned to a user. Roles typically represent a group of authorities or permissions that a user possesses (e.g., `ROLE_USER`, `ROLE_ADMIN`).

### Example:

```java
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.SecurityFilterChain;

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests((requests) -> requests
            .antMatchers("/admin/**").hasRole("ADMIN") // Only users with the ADMIN role can access
            .antMatchers("/user/**").hasAnyRole("USER", "ADMIN") // Users with USER or ADMIN roles
            .antMatchers("/", "/home").permitAll() // Public access to home page
            .anyRequest().authenticated() // All other endpoints require authentication
        )
        .formLogin(); // Enable form-based login
    return http.build();
}
```
In this example:

- The `/admin/**` endpoint is restricted to users with the `ADMIN` role.
- The `/user/**` endpoint can be accessed by both `USER` and `ADMIN` roles.
- The home page (/home) is publicly accessible to everyone.

3. Authority-Based Authorization
Authorities are more granular than roles and represent permissions granted to a user. You can use authorities to control access at a more fine-grained level than roles. Instead of checking for roles, you can check for specific permissions or authorities (e.g., READ_PRIVILEGE, WRITE_PRIVILEGE).

Example:
```java
http
    .authorizeHttpRequests((requests) -> requests
        .antMatchers("/write").hasAuthority("WRITE_PRIVILEGE") // Requires WRITE_PRIVILEGE authority
        .antMatchers("/read").hasAuthority("READ_PRIVILEGE") // Requires READ_PRIVILEGE authority
        .anyRequest().authenticated()
    )
    .formLogin();
```

In this example:

- The /write endpoint is accessible only to users with the WRITE_PRIVILEGE authority.
- The /read endpoint is accessible only to users with the READ_PRIVILEGE authority.

4. Method-Level Security
In addition to securing URLs, Spring Security allows you to secure individual methods using annotations. To enable method-level security, you need to annotate your configuration class with `@EnableGlobalMethodSecurity`.

Enabling Method-Level Security
```java
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MethodSecurityConfig {
    // Custom configuration for method-level security
}
```

Using Annotations for Method Security
- `@PreAuthorize`: Secures a method by specifying a condition that must be met before invoking the method.
- `@PostAuthorize`: Secures a method by specifying a condition that must be met after the method is invoked.
Example:
```java
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @PreAuthorize("hasRole('ADMIN')")
    public void createUser() {
        // Method accessible only to users with the ADMIN role
    }

    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public String viewUser() {
        // Method accessible only to users with the READ_PRIVILEGE authority
        return "User Data";
    }
}
```

In this example:

- The createUser method is restricted to users with the ADMIN role.
- The viewUser method is restricted to users with the READ_PRIVILEGE authority.

4. Custom Access Decision Voter
Spring Security uses AccessDecisionVoter to vote on whether access to a particular resource should be granted or denied. You can create a custom voter to implement specific access control logic.

Example:
```java
public class CustomAccessDecisionVoter implements AccessDecisionVoter<Object> {

    @Override
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
        // Custom logic to allow or deny access
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("CUSTOM_PERMISSION"))) {
            return ACCESS_GRANTED;
        }
        return ACCESS_DENIED;
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
```

In this example:

The CustomAccessDecisionVoter checks if the user has the CUSTOM_PERMISSION authority before granting access.

6. Using Expressions for Authorization
You can use Spring Security SpEL (Spring Expression Language) to write more complex authorization expressions.

Example:
```java
http
    .authorizeHttpRequests((requests) -> requests
        .antMatchers("/api/**").access("hasRole('ADMIN') and hasIpAddress('192.168.1.0/24')")
    );
In this example:

The expression ensures that the user must have the ADMIN role and must be accessing from the 192.168.1.x IP range.
7. Global Method Security
To enable method-level security across your Spring application, you need to enable it in your configuration.

Example:
```java
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {
    // Configuration for method-level security
}
In this example:

@EnableGlobalMethodSecurity is used to enable method-level security annotations such as @PreAuthorize and @Secured.

8. Exception Handling in Authorization
When access is denied, Spring Security handles it by throwing an AccessDeniedException. You can customize how access denied responses are handled.

Example:
```java
http
    .exceptionHandling()
    .accessDeniedPage("/403"); // Custom access denied page
In this example:

The user will be redirected to a custom access denied page (/403) if they try to access a resource without proper authorization.

9. Custom Authentication and Authorization Services
You can customize the way Spring Security handles authentication and authorization by extending core services, such as UserDetailsService for loading user information and AccessDecisionManager for custom decision-making logic.

Example: Custom UserDetailsService for Authorization
```java
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Load user from a database or another source
        return new User("admin", passwordEncoder().encode("password"),
                        Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"),
                                      new SimpleGrantedAuthority("READ_PRIVILEGE")));
    }
}
Questions
What is the difference between role-based and permission-based authorization in Spring Security?
How does Spring Security manage method-level authorization?
What is an AccessDecisionVoter and how is it used in Spring Security?
How can you configure global method security in a Spring Boot application?
How can you customize access denied handling in Spring Security?
What is the purpose of using expressions (SpEL) for authorization in Spring Security?
How does Spring Security handle authorization exceptions?
What are the main components involved in Spring Security authorization?