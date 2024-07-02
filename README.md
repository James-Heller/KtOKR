
# OKR System

This is an OKR (Objectives and Key Results) system developed using Kotlin2, SpringBoot3, SpringSecurity6, Ktorm, and Caffeine. The system is designed to help organizations set, track, and achieve their goals efficiently.

## Features

- **Objectives Management**: Create, update, and delete objectives.
- **Key Results Tracking**: Associate key results with objectives and track their progress.
- **User Authentication and Authorization**: Secure the application with Spring Security.
- **Database Management**: Use Ktorm for efficient database operations.
- **Caching**: Implement caching with Caffeine for improved performance.

## Technology Stack

- **Kotlin 2**: The primary programming language for the backend development.
- **Spring Boot 3**: Framework for building the application.
- **Spring Security 6**: Security framework for authentication and authorization.
- **Ktorm**: Lightweight ORM framework for database operations.
- **Caffeine**: High-performance caching library.

## Prerequisites

- **Java 17**: Ensure you have JDK 17 installed.
- **Maven**: Use Maven for dependency management.
- **Database**: Set up a PostgreSQL or MySQL database.

## Getting Started

1. **Clone the repository**:
   ```bash
   git clone https://github.com/James-Heller/KtOKR.git
   cd KtOKR
   ```

2. **Configure the database**:
   Update the `application.yml` file with your database configuration.
   ```yaml
   spring:
   datasource:
   url: jdbc:postgresql://localhost:5432/okr_db
   username: your_db_username
   password: your_db_password
   jpa:
   hibernate:
   ddl-auto: update
   ```

3. **Build the project**:
   ```bash
   ./mvnw clean install
   ```

4. **Run the application**:
   ```bash
   ./mvnw spring-boot:run
   ```

## API Documentation

The API documentation is available at `/swagger-ui.html` after starting the application.

## Contributing

We welcome contributions! Please follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Commit your changes (`git commit -am 'Add new feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Create a new Pull Request.

## License

This project is licensed under the MIT License.

## Contact

For any questions or feedback, please contact [jamestang.space](mailto:James-Heller@Outlook.com).
