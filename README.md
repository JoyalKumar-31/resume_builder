#  Resume Builder

A full-stack **Resume Builder application** designed to help users create, manage, and build professional resumes through a secure and user-friendly platform.

The project is built with **Spring Boot, Java, MySQL, Spring Security, JWT, Cloudinary, Brevo SMTP, and Razorpay**, following a backend architecture designed for scalability, security, and maintainability.

---

## Features

###  Authentication & Security

* User registration and login
* Password encryption using BCrypt
* JWT-based authentication
* Spring Security integration
* Role-based access control
* Email verification
* Secure authentication flow

###  Email Verification

* Email verification during registration
* Verification code generation
* Verification-code expiry handling
* HTML email support
* SMTP integration using Brevo

###  Resume Management

* Create and manage resume information
* Store user resume data securely
* Structured backend APIs
* User-specific resume management

###  Resume Templates

* Professional resume templates
* Basic and premium template support
* Template-based resume generation

###  Premium Features

* Premium template purchasing
* Razorpay payment integration
* Secure payment processing
* Payment verification before granting premium access

###  Cloud Storage

* Cloudinary integration
* Cloud-based image/file management
* Secure media handling

###  Backend Architecture

* RESTful APIs
* Spring Data JPA
* DTO-based data handling
* Validation
* Exception handling
* Service and controller separation
* Database persistence with MySQL
* Production-oriented project structure

---

##  Tech Stack

### Backend

| Technology        | Purpose                        |
| ----------------- | ------------------------------ |
| Java 17           | Programming Language           |
| Spring Boot       | Backend Framework              |
| Spring Web        | REST APIs                      |
| Spring Data JPA   | Database Operations            |
| Spring Security   | Authentication & Authorization |
| JWT               | Token-Based Authentication     |
| Spring Validation | Request Validation             |
| Spring Mail       | Email Services                 |
| MySQL             | Database                       |
| Lombok            | Boilerplate Reduction          |
| Cloudinary        | Cloud Media Storage            |
| Razorpay          | Payment Gateway                |
| Actuator          | Application Monitoring         |
| Maven             | Dependency Management          |

---

##  Architecture

The application follows a layered backend architecture:

```text
Client / Frontend
       │
       ▼
 REST Controllers
       │
       ▼
    Services
       │
       ▼
 Repositories
       │
       ▼
     MySQL
```

Supporting components:

```text
Authentication
      │
      ├── Spring Security
      ├── JWT
      └── BCrypt

Email Services
      │
      └── Brevo SMTP

File / Image Storage
      │
      └── Cloudinary

Payments
      │
      └── Razorpay
```

---

##  Authentication Flow

```text
User Registration
       │
       ▼
Validate User Details
       │
       ▼
Encrypt Password
       │
       ▼
Generate Verification Code
       │
       ▼
Send Verification Email
       │
       ▼
Verify Email
       │
       ▼
Login
       │
       ▼
Generate JWT
       │
       ▼
Access Protected APIs
```

---

## Payment Flow

```text
User selects Premium Template
            │
            ▼
       Create Order
            │
            ▼
        Razorpay
            │
            ▼
       Make Payment
            │
            ▼
    Verify Payment
            │
            ▼
 Grant Premium Access
```

---

##  Project Structure

```text
src/
└── main/
    ├── java/
    │   └── com/
    │       └── codewithme/
    │           └── www/
    │               ├── controller/
    │               ├── service/
    │               ├── repository/
    │               ├── model/
    │               ├── dto/
    │               ├── security/
    │               ├── exception/
    │               └── config/
    │
    └── resources/
        ├── application.properties
        └── ...
```

> Package structure may evolve as new features are added.

---

## Requirements

Before running the project, make sure you have:

* Java 17+
* Maven
* MySQL
* Git
* IDE such as Spring Tool Suite, IntelliJ IDEA, or Eclipse

---

##  Configuration

Create/configure your application properties with your own credentials.

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/foodapi
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update

server.port=1010
```

### Email Configuration

Configure your SMTP provider:

```properties
spring.mail.host=YOUR_SMTP_HOST
spring.mail.port=YOUR_SMTP_PORT
spring.mail.username=YOUR_EMAIL
spring.mail.password=YOUR_PASSWORD
```

### JWT Configuration

Use a secure secret key and configure the required expiration settings.

```properties
jwt.secret=YOUR_SECRET_KEY
jwt.expiration=YOUR_EXPIRATION_TIME
```

### Cloudinary

```properties
cloudinary.cloud-name=YOUR_CLOUD_NAME
cloudinary.api-key=YOUR_API_KEY
cloudinary.api-secret=YOUR_API_SECRET
```

### Razorpay

```properties
razorpay.key-id=YOUR_KEY_ID
razorpay.key-secret=YOUR_KEY_SECRET
```

 **Never commit real passwords, API keys, JWT secrets, or payment credentials to GitHub.**

Use environment variables or a local configuration file instead.

---

##  Run the Project

### 1. Clone the repository

```bash
git clone https://github.com/JoyalKumar-31/resume_builder.git
```

### 2. Navigate to the project

```bash
cd resume_builder
```

### 3. Build the project

```bash
./mvnw clean install
```

For Windows:

```bash
mvnw.cmd clean install
```

### 4. Run the application

```bash
./mvnw spring-boot:run
```

For Windows:

```bash
mvnw.cmd spring-boot:run
```

The application will start on the configured server port.

---

##  API Testing

The REST APIs can be tested using:

* Postman
* Insomnia
* Swagger/OpenAPI, if configured
* Frontend application

Typical authentication flow:

```text
Register
   ↓
Verify Email
   ↓
Login
   ↓
Receive JWT
   ↓
Send JWT with protected requests
```

Example authorization header:

```http
Authorization: Bearer <JWT_TOKEN>
```

---

##  Security

The application includes several security mechanisms:

* BCrypt password hashing
* JWT authentication
* Spring Security
* Protected REST endpoints
* Input validation
* Email verification
* Role-based access control
* Secure payment verification

Sensitive configuration values should always be stored outside source control.

---

##  Future Improvements

Planned improvements may include:

* [ ] Refresh token authentication
* [ ] More resume templates
* [ ] Resume PDF generation improvements
* [ ] Resume preview
* [ ] AI-powered resume suggestions
* [ ] ATS resume analysis
* [ ] Job-description based resume optimization
* [ ] Redis caching
* [ ] Docker deployment
* [ ] CI/CD pipeline
* [ ] Cloud deployment
* [ ] API documentation with Swagger/OpenAPI

---

##  Project Goals

The main goals of this project are:

* Provide an easy way to create professional resumes
* Implement secure user authentication
* Provide premium resume templates
* Integrate online payments
* Practice real-world Spring Boot development
* Build a scalable REST API architecture
* Apply production-oriented backend development practices

---

##  Author

**Janampally Joyal Kumar**

B.Tech Computer Science Engineering

GitHub:
https://github.com/JoyalKumar-31

---

## License

This project is licensed under the MIT License.

---
