# FluxTask: Distributed Task Queue with Spring Boot & Kafka

FluxTask is a distributed task queue built using **Spring Boot, Kafka, PostgreSQL, and Redis**. It allows users to submit background tasks via a REST API, processes them asynchronously using Kafka consumers, and provides an API to track task status.

## 🚀 Features

- Accept **background tasks** via REST API (`POST /tasks`)
- Store tasks in **PostgreSQL** for persistence
- Publish tasks to **Kafka** for processing
- Worker services consume tasks from **Kafka** and execute them
- Automatic retry mechanism for failed tasks
- Retrieve task status via API (`GET /tasks/{taskId}`)
- Built using **Clean Architecture**
- Task execution is currently simulated with a delay for demonstration purposes

## 🏗️ Project Structure

```
fluxtask/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── fluxtask/
│   │   │               ├── FluxTaskApplication.java
│   │   │               ├── domain/
│   │   │               │   ├── entity/
│   │   │               │   │   └── Task.java
│   │   │               │   ├── exception/
│   │   │               │   │   └── TaskProcessingException.java
│   │   │               │   └── repository/
│   │   │               │       └── TaskRepository.java
│   │   │               ├── application/
│   │   │               │   ├── dto/
│   │   │               │   │   ├── TaskRequest.java
│   │   │               │   │   └── TaskResponse.java
│   │   │               │   ├── service/
│   │   │               │   │   ├── TaskService.java
│   │   │               │   │   └── TaskServiceImpl.java
│   │   │               │   └── mapper/
│   │   │               │       └── TaskMapper.java
│   │   │               ├── infrastructure/
│   │   │               │   ├── config/
│   │   │               │   │   ├── KafkaConfig.java
│   │   │               │   │   └── RedisConfig.java
│   │   │               │   ├── persistence/
│   │   │               │   │   ├── entity/
│   │   │               │   │   │   └── TaskEntity.java
│   │   │               │   │   └── repository/
│   │   │               │   │       └── TaskJpaRepository.java
│   │   │               │   ├── messaging/
│   │   │               │   │   ├── KafkaProducerService.java
│   │   │               │   │   └── KafkaConsumerService.java
│   │   │               │   └── cache/
│   │   │               │       └── RedisTaskCache.java
│   │   │               └── presentation/
│   │   │                   ├── controller/
│   │   │                   │   └── TaskController.java
│   │   │                   └── exception/
│   │   │                       └── GlobalExceptionHandler.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── schema.sql
│   └── test/
│       └── java/
│           └── com/
│               └── example/
│                   └── fluxtask/
│                       ├── domain/
│                       ├── application/
│                       ├── infrastructure/
│                       └── presentation/
└── pom.xml
```

## 🛠️ Installation & Setup

### 1️⃣ Prerequisites

- Java 17+
- Docker & Docker Compose
- Apache Kafka
- PostgreSQL

### 2️⃣ Clone the Repository

```sh
git clone https://github.com/your-username/fluxtask.git
cd fluxtask
```

### 3️⃣ Configure Environment

Modify `src/main/resources/application.yml` with your database and Kafka settings.

### 4️⃣ Run with Docker Compose

```sh
docker-compose up -d
```

### 5️⃣ Start the Application

```sh
./mvnw spring-boot:run
```

## 🖥️ API Endpoints

| Method   | Endpoint          | Description       |
| -------- | ----------------- | ----------------- |
| **POST** | `/tasks`          | Submit a new task |
| **GET**  | `/tasks/{taskId}` | Get task status   |

## 🛠️ Testing

Run unit tests with:

```sh
./mvnw test
```

## 📜 License

This project is licensed under the MIT License.

---

🚀 **Happy coding!**
