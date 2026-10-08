# 🚀 Spring Boot Logging & Monitoring

A Spring Boot application designed to demonstrate **application logging, monitoring, caching, database integration, and production-ready observability** using Spring Boot Actuator.

The project uses **Spring Boot, Spring Data JPA, MySQL, Redis, Spring Cache, Jackson, and Actuator** to build and monitor a backend application.

---

## 📌 Features

- ✅ Spring Boot REST API
- ✅ Spring Data JPA integration
- ✅ MySQL database integration
- ✅ Redis integration
- ✅ Spring Cache implementation
- ✅ Application logging using SLF4J
- ✅ Spring Boot Actuator for monitoring
- ✅ Health and application metrics
- ✅ JSON processing using Jackson
- ✅ Exception and error monitoring
- ✅ Maven-based project
- ✅ Clean layered architecture

---

## 🛠️ Tech Stack

| Technology | Usage |
|---|---|
| Java 17 | Programming Language |
| Spring Boot 4.1.1 | Backend Framework |
| Spring WebMVC | REST APIs |
| Spring Data JPA | Database Access |
| MySQL | Relational Database |
| Redis | Caching |
| Spring Cache | Cache Abstraction |
| Spring Boot Actuator | Monitoring |
| Jackson | JSON Processing |
| Maven | Build & Dependency Management |
| Git & GitHub | Version Control |

---

## 🏗️ Project Architecture

```text
Client
  |
  | HTTP Request
  v
Controller
  |
  v
Service
  |
  +--------------------+
  |                    |
  v                    v
Repository           Redis Cache
  |
  v
MySQL Database

        |
        v
Spring Boot Actuator
        |
        v
Monitoring & Metrics
```

---

## 📂 Project Structure

```text
spring-boot-logging-monitoring/
│
├── .mvn/
│   └── wrapper/
│
├── logs/
│   └── application logs
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── amazon/
│   │   │           ├── controller/
│   │   │           ├── service/
│   │   │           ├── repository/
│   │   │           ├── entity/
│   │   │           ├── config/
│   │   │           └── SpringBootLoggingMonitoringApplication.java
│   │   │
│   │   └── resources/
│   │       ├── application.properties
│   │       └── ...
│   │
│   └── test/
│
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

> The exact package/file structure can evolve as the project grows.

---

# 📊 Logging

Logging is used to track application behavior and help developers identify errors and debug production issues.

The project uses **SLF4J** with Spring Boot's logging infrastructure.

### Example

```java
private static final Logger logger =
        LoggerFactory.getLogger(MyController.class);

logger.info("Fetching all products");

logger.debug("Request received with ID: {}", id);

logger.warn("Product not found: {}", id);

logger.error("Error while processing request", exception);
```

### Log Levels

```text
TRACE
  ↓
DEBUG
  ↓
INFO
  ↓
WARN
  ↓
ERROR
```

---

# 📁 Log Files

Application logs can be stored inside the:

```text
logs/
```

directory.

Example:

```text
logs/
└── application.log
```

Logs help with:

- Debugging application issues
- Tracking API requests
- Identifying exceptions
- Monitoring application behavior
- Troubleshooting production problems

---

# ❤️ Spring Boot Actuator

Spring Boot Actuator provides production-ready endpoints for monitoring and managing the application.

Common endpoints include:

```text
/actuator/health
/actuator/info
/actuator/metrics
```

### Health Check

```http
GET /actuator/health
```

Example response:

```json
{
  "status": "UP"
}
```

---

## 📈 Monitoring

Actuator can provide information about:

- Application health
- JVM memory
- CPU usage
- HTTP request metrics
- Database health
- Cache statistics
- Application startup information

Example:

```http
GET /actuator/metrics
```

Specific metrics can also be accessed using:

```text
/actuator/metrics/{metric-name}
```

---

# ⚡ Redis Caching

Redis is used as an in-memory data store to improve application performance.

Instead of querying MySQL for frequently requested data every time:

```text
Client
  |
  v
Controller
  |
  v
Service
  |
  v
Redis Cache
  |
  +---- Cache Hit ----> Return Data
  |
  +---- Cache Miss
           |
           v
       MySQL Database
           |
           v
       Store in Redis
           |
           v
       Return Data
```

### Benefits

- Faster response time
- Reduced database load
- Better application performance
- Useful for frequently accessed data

---

# 🗄️ MySQL Database

MySQL is used as the primary relational database.

Spring Data JPA provides the data-access layer.

```text
Spring Boot
     |
     v
Spring Data JPA
     |
     v
Hibernate
     |
     v
MySQL
```

---

# 🔄 Cache + Database Flow

A typical request follows this flow:

```text
        Client
          |
          v
      REST API
          |
          v
       Service
          |
     Check Redis
       /     \
    Hit       Miss
     |          |
     v          v
 Return      MySQL
 Data          |
               v
          Store in Redis
               |
               v
          Return Data
```

---

# ⚙️ Configuration

Application configuration can be maintained in:

```text
src/main/resources/application.properties
```

Example MySQL configuration:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Example Redis configuration:

```properties
spring.data.redis.host=localhost
spring.data.redis.port=6379
```

Example Actuator configuration:

```properties
management.endpoints.web.exposure.include=health,info,metrics
```

> Replace database credentials with your local configuration. Do not commit passwords or secrets to GitHub.

---

# ▶️ How to Run

## 1. Clone the Repository

```bash
git clone https://github.com/ALOKKUMARSAW/spring-boot-logging-monitoring.git
```

## 2. Navigate to the Project

```bash
cd spring-boot-logging-monitoring
```

## 3. Configure MySQL

Create a database:

```sql
CREATE DATABASE logging_monitoring;
```

Update the database configuration in:

```text
application.properties
```

---

## 4. Start Redis

Make sure Redis is running locally.

Default Redis configuration:

```text
Host: localhost
Port: 6379
```

---

## 5. Build the Project

Using Maven:

```bash
./mvnw clean install
```

On Windows:

```bash
mvnw.cmd clean install
```

---

## 6. Run the Application

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

---

# 🔍 Monitoring Endpoints

After starting the application, Actuator endpoints can be accessed using:

```text
http://localhost:8080/actuator/health
```

```text
http://localhost:8080/actuator/info
```

```text
http://localhost:8080/actuator/metrics
```

---

# 🧪 Testing

The project includes Spring Boot test dependencies for testing the application.

Run tests using:

```bash
./mvnw test
```

On Windows:

```bash
mvnw.cmd test
```

---

# 🧠 What I Learned From This Project

This project helped me understand:

- How logging works in Spring Boot
- Difference between `INFO`, `DEBUG`, `WARN`, and `ERROR`
- How to configure application logs
- How to monitor Spring Boot applications
- Spring Boot Actuator
- Health checks and application metrics
- Redis caching
- Cache hit and cache miss
- MySQL integration with Spring Data JPA
- REST API development
- Maven dependency management
- Production-oriented application monitoring

---

# 🚀 Future Improvements

Planned improvements:

- [ ] Add centralized logging
- [ ] Add ELK Stack integration
- [ ] Add Grafana dashboards
- [ ] Add Prometheus metrics
- [ ] Add Docker support
- [ ] Add Docker Compose for MySQL + Redis
- [ ] Add API documentation using Swagger/OpenAPI
- [ ] Add distributed tracing
- [ ] Add centralized exception monitoring
- [ ] Add CI/CD pipeline

---

# 🔐 Security Notes

Do not commit sensitive information such as:

```text
Database passwords
API keys
Redis credentials
JWT secrets
AWS credentials
```

Use environment variables or external configuration for production environments.

---

# 👨‍💻 Author

**Alok Kumar Saw**

Java Backend Developer

### Skills

```text
Java
Spring Boot
Spring Data JPA
REST APIs
MySQL
Redis
Microservices
Kafka
Docker
AWS
```

---

## 🔗 Connect With Me

- GitHub: https://github.com/ALOKKUMARSAW
- LinkedIn: https://www.linkedin.com/in/alok-kumar-saw/

---

## ⭐ If You Find This Useful

If this project helped you understand **Spring Boot Logging, Redis, Actuator, and Monitoring**, consider giving the repository a ⭐.

---

## 📄 License

This project is intended for learning and educational purposes.
