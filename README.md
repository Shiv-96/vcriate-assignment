# vcriate-assignment

## Table of Contents
- Prerequisites
- Google Cloud Configuration
- Project Details
- Usage
- Contact

## Prerequisites

- Java 17 or higher
- Maven
- Google Cloud account
  
## Google Cloud Configuration

1. **Create OAuth 2.0 Credentials:**
   - Go to the [Google Cloud Console](https://console.cloud.google.com/).
   - Navigate to "APIs & Services" > "Credentials".
   - Click "Create Credentials" and select "OAuth 2.0 Client ID".
   - Set the application type to "Web application".
   - Under "Authorized redirect URIs", add: `http://localhost:8080/login/oauth2/code/google`.
   - Save the credentials and note the `Client ID` and `Client Secret`.

## Project Details
1. **Clone the Repository:**

    ```sh
    git clone https://github.com/Shiv-96/vcriate-assignment
    ```
2. **Configure the Application:**
   - Before running the application update `src/main/resources/application.properties` with your Google OAuth2 credentials:

    ```properties
    spring.security.oauth2.client.registration.google.client-id=your-client-id
    spring.security.oauth2.client.registration.google.client-secret=your-client-secret
    spring.security.oauth2.client.registration.google.scope=profile, email
    spring.security.oauth2.client.registration.google.redirect-uri={baseUrl}/login/oauth2/code/google
    spring.security.oauth2.client.registration.google.authorization-grant-type=authorization_code
    spring.security.oauth2.client.registration.google.client-name=Google

    spring.security.oauth2.client.provider.google.authorization-uri=https://accounts.google.com/o/oauth2/auth
    spring.security.oauth2.client.provider.google.token-uri=https://oauth2.googleapis.com/token
    spring.security.oauth2.client.provider.google.user-info-uri=https://openidconnect.googleapis.com/v1/userinfo
    spring.security.oauth2.client.provider.google.user-name-attribute=sub

    server.port=8081
    ```
3. **Verify Dependencies:**
   - Ensure `pom.xml` includes the necessary dependencies for Spring Boot, Spring Security OAuth2, and Thymeleaf.
     
## Usage
- **Access the application**: Open your browser and navigate to `http://localhost:8081`.

## Contact
- **Email**: kshiv.dot@gmail.com
- **GitHub**: [Shiv-96](https://github.com/Shiv-96)
