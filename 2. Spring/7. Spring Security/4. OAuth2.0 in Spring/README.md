# OAuth2.0 in Spring

- OAuth2.0 or Open Authorization is a widely-used authorization framework that enables applications to gain limited access to a user's resources without exposing their credentials.
- It is commonly used to implement secure delegated access and is often integrated with Spring Security to handle authentication and authorization in Spring applications.
- This is possible as it uses authorization tokens to prove an identity between consumers and service providers.
- Our application will delegate the responsibility of user authorization to another service.

## OAuth2.0 Elements

1. **Resources** are protected data that require OAuth to access them.

### Entities:

1. User/Resource Owner:

   - The user or system that owns the protected data in the resource server and can grant access to them.
   - For example, a user's Google Drive account.

2. Application/Client:

   - The client is the third-party application that wants access to the user's protected resources and data.
   - To access resources, the Client must hold the appropriate Access Token.

3. Authorization Server:

   - OAuth’s main engine that creates access tokens.
   - This server/API receives requests from the Client for Access Tokens and issues them upon successful authentication and consent by the Resource Owner.
   - The authorization server exposes two endpoints:
     1. The Authorization endpoint, which handles the interactive authentication and consent of the user.
     2. The Token endpoint, which is involved in a machine to machine interaction.

4. Resource Server:
   - A server/API that stores and protects the user’s data and resources.
   - It receives access requests from the Client.
   - It accepts and validates an Access Token from the Client and returns the appropriate resources to it.

### Scope and Consent:

1. Scope:

   - The scopes define the specific actions that apps can perform on behalf of the user. They are the bundles of permissions asked for by the client when requesting a token.
   - For example, we can share our LinkedIn posts on Twitter via LinkedIn itself. Given that it has write-only access, it cannot access other pieces of information, such as our conversations.

2. Consent:
   - On the Consent screen, a user learns who is attempting to access their data and what kind of data they want to access, and the user must express their consent to allow third-party access to the requested data.
   - You grant access to your IDE, such as CodingSandbox, when you link your GitHub account to it or import an existing repository.

### Tokens

1. **Access Tokens**:

   - An Access Token is a piece of data containing just enough information to be able to verify a user’s identity or authorize them to perform a certain action.
   - **Example**: Suppose you (resource owner) wanted to watch the latest Marvel movie (Shang Chi and the Legends of the Ten Rings), you’d go to the ticket vendor (auth server), choose the movie, and buy the ticket(token) for that movie (scope). Ticket validity now pertains only to a certain time frame and to a specific show. After the security guy checks your ticket, he lets you into the theatre (resource server) and directs you to your assigned seat.
   - Characteristics:

     1. Anyone who has the access token can use it to make API requests. Therefore, they’re called **"Bearer Tokens"**. Eg: If you give your ticket to a friend, they can use it to watch the movie.
     2. OAuth access tokens can be created without actually including information about the user to whom they were issued. Eg: You will not find your personal information on the ticket.
     3. Like a movie ticket, an OAuth access token is valid for a certain period and then expires.
     4. Each token represents the scope and duration of access granted by the resource owner and enforced by the authorization server.

2. **Refresh token**
   - A Refresh token is a string issued to the client by the authorization server and is used to obtain a new access token when the current access token becomes invalid.
   - They do not refresh an existing access token, they simply request a new one. The expiration time for refresh tokens tends to be much longer than for access tokens.

## Example Scenario

### Uploading a picture to a photo editor

1. The resource owner or user wishes to resize the image, so he goes to the editor (client), tells the client that the image is in Google Drive (resource owner), asking the client to bring it for editing.
2. The client sends a request to the authorization server to access the image. The server asks the user to grant permissions for the same.
3. Once the user allows third-party access and logs into the website using Google, the authorization server sends a short-lived authorization code to the client.
4. Clients exchange auth codes for access tokens, which define the scope and duration of user access.
5. The Authorization Server validates the access token, and the editor fetches the image that the user wants to edit from their Google Drive account.

## Core Concepts

- **OAuth2.0 Flow**:

  - **Authorization Code Grant**: The application redirects the user to the authorization server, which then redirects back to the application with an authorization code. The application exchanges this code for an access token.
  - **Implicit Grant**: The access token is returned directly in the URL fragment without an intermediate authorization code, suitable for client-side applications.
  - **Resource Owner Password Credentials Grant**: The application obtains access tokens using the user's credentials directly. Typically used in trusted applications.
  - **Client Credentials Grant**: The application authenticates itself to obtain an access token without user involvement, usually for server-to-server interactions.

- **Scopes**: Permissions that define what resources and actions the application can access on behalf of the user.

- **Tokens**:

  - **Access Token**: Used to access protected resources.
  - **Refresh Token**: Used to obtain a new access token when the original one expires.

- **Endpoints**:
  - **Authorization Endpoint**: The URL where users are redirected to authorize the application.
  - **Token Endpoint**: The URL where the application exchanges authorization codes for tokens.
  - **User Info Endpoint**: The URL where the application retrieves user information.

## Configuration in Spring Boot

### 1. Set Up Google API Credentials

1. **Create Google API Project**:

   - Go to the [Google Cloud Console](https://console.developers.google.com/).
   - Create a new project or select an existing one.
   - Enable the "Google+ API" or "Google Identity Platform" API.
   - Create OAuth 2.0 credentials (Client ID and Client Secret).

2. **Configure OAuth Consent Screen**:
   - Set up the OAuth consent screen with the required details.
   - Add scopes and test users if necessary.

### 2. Add Dependencies

Add the Spring Security OAuth2 Client dependency to your `pom.xml` (for Maven) or `build.gradle` (for Gradle).

```xml
<!-- For Maven -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-oauth2-client</artifactId>
</dependency>
```

### 2. Configure Application Properties

Add OAuth2 client configuration to your application.properties or application.yml.

```yml
spring:
  security:
    oauth2:
      client:
        registration:
          google:
            client-id: YOUR_CLIENT_ID
            client-secret: YOUR_CLIENT_SECRET
            scope: profile, email
            redirect-uri: "{baseUrl}/login/oauth2/code/google"
            authorization-grant-type: authorization_code
        provider:
          google:
            authorization-uri: https://accounts.google.com/o/oauth2/auth
            token-uri: https://oauth2.googleapis.com/token
            user-info-uri: https://www.googleapis.com/oauth2/v3/userinfo
```

### 3. Security Configuration

Configure Spring Security to use OAuth2 login.

```java
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .anyRequest().authenticated()
                .and()
            .oauth2Login();
    }

}
```

4. User Information

Create a custom OAuth2UserService to handle user details.

```java
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2UserService;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        // Process and return the user information
        return oAuth2User;
    }

}
```

### Use Cases

1. Single Sign-On (SSO): Allow users to authenticate once and gain access to multiple applications.
2. Social Login: Enable users to log in using their Google or other social accounts.
3. API Authorization: Use OAuth2 tokens to secure API endpoints and control access.

### Questions

1. What are the different OAuth2.0 grant types, and when would you use each?
2. How do you configure OAuth2.0 client settings in a Spring Boot application?
3. What is the role of scopes in OAuth2.0, and how are they used in Spring Security?
4. How can you customize user details retrieval from an OAuth2.0 provider in Spring?
5. What are the key differences between Authorization Code Grant and Implicit Grant?
