# Prime Number API

A lightweight, RESTful Spring Boot application that checks whether a given number is prime or not. Built with clean architecture principles, comprehensive error handling, and optimized algorithms.

## 🚀 Features

- **Fast Prime Checking**: Optimized O(√n) algorithm for efficient computation
- **RESTful Design**: Follows REST principles with proper HTTP methods and status codes
- **Comprehensive Error Handling**: Global exception handling with meaningful error messages
- **Multiple Input Methods**: Support for both path parameters and query parameters
- **Clean Architecture**: Layered architecture with separation of concerns
- **Browser Testable**: GET endpoints can be tested directly in browser
- **Caching Friendly**: GET requests can be cached by browsers and proxies

## 🛠️ Technologies Used

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Web MVC**
- **Maven 3.6+**

## 📋 Prerequisites

Before running this application, make sure you have the following installed:

- **Java Development Kit (JDK) 17 or higher**
  ```bash
  java -version
  # Should show version 17 or higher
  ```

- **Maven 3.6 or higher** (Optional - project includes Maven wrapper)
  ```bash
  mvn -version
  # Optional - you can use ./mvnw instead
  ```

- **Git** (for cloning the repository)
  ```bash
  git --version
  ```

## ⚡ Quick Start

### 1. Clone the Repository
```bash
git clone https://github.com/Nikhildev0904/prime-number-api.git
cd prime-number-api
```

### 2. Run the Application

#### Option A: Using Maven Wrapper (Recommended)
```bash
./mvnw spring-boot:run
```

#### Option B: Using Maven
```bash
mvn clean spring-boot:run
```

#### Option C: Using IDE
1. Import the project as a Maven project
2. Run the `PrimeNumberApplication.java` main method

### 3. Verify the Application is Running
```bash
curl http://localhost:8080/health
# Expected: "Prime Number API is running!"
```

The application will start on `http://localhost:8080` (To change the port, modify `application.properties` file in `src/main/resources`).

## 📚 API Documentation

### Base URL
```
http://localhost:8080
```

### Endpoints

#### 1. Health Check
Check if the API is running and healthy.

- **URL**: `/health`
- **Method**: `GET`
- **Success Response**:
    - **Code**: 200 OK
    - **Content**: `"Prime Number API is running!"`

**Example:**
```bash
curl http://localhost:8080/health
```

#### 2. Check Prime Number (Path Parameter)
Check if a number is prime using path parameter.

- **URL**: `/prime/check/{number}`
- **Method**: `GET`
- **URL Params**:
    - `number` [long] - The number to check (must be > 0)
- **Success Response**:
    - **Code**: 200 OK
    - **Content**:
      ```json
      {
        "number": 17,
        "is-prime": true,
        "message": "17 is a prime number"
      }
      ```

**Examples:**
```bash
# Prime number
curl http://localhost:8080/prime/check/17

# Non-prime number
curl http://localhost:8080/prime/check/20

# Edge case
curl http://localhost:8080/prime/check/2
```

#### 3. Check Prime Number (Query Parameter)
Check if a number is prime using query parameter.

- **URL**: `/prime`
- **Method**: `GET`
- **Query Params**:
    - `number` [long] - The number to check (must be > 0)
- **Success Response**: Same as above

**Examples:**
```bash
# Prime number
curl "http://localhost:8080/prime?number=17"

# Non-prime number  
curl "http://localhost:8080/prime?number=20"
```

### Error Responses

All error responses follow this format:
```json
{
  "error": "Error Type",
  "message": "Detailed error message",
  "timestamp": "2025-06-04T10:30:00.123",
  "status": 400
}
```

#### Common Error Scenarios

| Error | Status Code | Trigger | Example |
|-------|-------------|---------|---------|
| Invalid Argument | 400 | Number ≤ 0 | `curl http://localhost:8080/prime/check/-5` |
| Type Mismatch | 400 | Non-numeric input | `curl http://localhost:8080/prime/check/abc` |
| Missing Parameter | 400 | Missing query param | `curl "http://localhost:8080/prime"` |
| Not Found | 404 | Invalid endpoint | `curl http://localhost:8080/invalid` |
| Method Not Allowed | 405 | Wrong HTTP method | `curl -X POST http://localhost:8080/prime/check/17` |


## 📁 Project Structure

```
src/
├── main/
│   ├── java/com/nikhildev/projects/prime_number/
│   │   ├── PrimeNumberApplication.java      # Main application class
│   │   ├── controller/
│   │   │   └── PrimeNumberController.java      # REST endpoints
│   │   ├── service/
│   │   │   └── PrimeNumberService.java         # Business logic
│   │   ├── dto/
│   │   │   ├── PrimeCheckResponse.java         # Response model
│   │   │   └── ErrorResponse.java              # Error response model
│   │   └── exceptions/
│   │       └── GlobalExceptionHandler.java     # Global error handling
│   └── resources/
│       └── application.properties              # Configuration
```

## ⚙️ Configuration

### Application Properties
```properties
# Server Configuration
server.port=8080

# Error Handling Configuration
server.error.whitelabel.enabled=false
spring.mvc.throw-exception-if-no-handler-found=true
spring.web.resources.add-mappings=false

```

### Environment Variables
You can override configuration using environment variables:

```bash
# Change port
export SERVER_PORT=9090
./mvnw spring-boot:run

# Or pass as arguments
./mvnw spring-boot:run -Dspring-boot.run.arguments=--server.port=9090
```

## 🎯 Algorithm Details

The application uses an optimized prime checking algorithm:

1. **Edge Cases**: Handles numbers ≤ 1, 2, and 3 directly
2. **Even Numbers**: Quickly eliminates multiples of 2 and 3
3. **Optimized Loop**: Only checks divisors of form 6k ± 1 up to √n
4. **Time Complexity**: O(√n)
5. **Space Complexity**: O(1)

This approach is significantly faster than naive algorithms, especially for large numbers.

## 🚀 Performance

The API can efficiently handle:
- **Small numbers (< 1000)**: Instant response
- **Medium numbers (1000-1M)**: Sub-millisecond response
- **Large numbers (> 1M)**: Still very fast due to O(√n) complexity

## 🔧 Development

### Building the Project
```bash
# Compile only
./mvnw compile

# Package as JAR
./mvnw package

# Run the packaged JAR
java -jar target/prime-number-api-0.0.1-SNAPSHOT.jar
```

### IDE Setup
1. **IntelliJ IDEA**: Import as Maven project
2. **Eclipse**: Import as Existing Maven Project
3. **VS Code**: Open folder with Java Extension Pack installed

## 📊 Monitoring and Logging

View logs while running:
```bash
./mvnw spring-boot:run
# Logs will appear in console
```

### Health Monitoring
Use the health endpoint for monitoring:
```bash
curl http://localhost:8080/health
```

## 🤝 Contributing

1. **Fork** the repository
2. **Create** a feature branch (`git checkout -b feature/amazing-feature`)
3. **Commit** your changes (`git commit -m 'Add some amazing feature'`)
4. **Push** to the branch (`git push origin feature/amazing-feature`)
5. **Open** a Pull Request

### Code Style
- Follow Java naming conventions
- Add unit tests for new features
- Update documentation for API changes
- Use meaningful commit messages

## 📝 License

This project is licensed under the APACHE License - see the [LICENSE](LICENSE) file for details.

## 📞 Support

If you encounter any issues or have questions:

1. **Check** the error response for detailed information
2. **Review** this README for common solutions
3. **Open** an issue on GitHub with:
    - Error message
    - Steps to reproduce
    - Your environment details

## 🎉 Acknowledgments

- Spring Boot team for the excellent framework
- Java community for continuous improvements

---

**Happy Coding!** 🚀
