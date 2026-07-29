# 💬 Live Chat Application

<div align="center">

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-brightgreen)
![React](https://img.shields.io/badge/React-19-blue)
![WebSocket](https://img.shields.io/badge/WebSocket-STOMP-success)
![JWT](https://img.shields.io/badge/JWT-Authentication-red)
![MySQL](https://img.shields.io/badge/MySQL-Database-blue)
![Maven](https://img.shields.io/badge/Maven-Build-purple)

### 🚀 Enterprise Level Real-Time Chat Application

Built using **Spring Boot**, **React**, **WebSocket (STOMP)**, **JWT Authentication**, and **MySQL** following **industry-standard layered architecture**.

</div>

---

# 📌 Overview

This project is a real-time chat application inspired by modern messaging platforms.

It demonstrates:

- Enterprise Spring Boot Architecture
- JWT Authentication
- Role Based Authorization
- WebSocket Communication
- STOMP Messaging
- MySQL Persistence
- Layered Architecture
- Clean Code Principles
- DTO Pattern
- Mapper Pattern
- Repository Pattern
- Service Layer
- Security Best Practices

The project was developed with the objective of learning how enterprise Java applications are designed in real companies.

---

# 🚀 Features

## Authentication

- User Registration
- Secure Login
- BCrypt Password Encryption
- JWT Token Generation
- JWT Validation
- Stateless Authentication

---

## Security

- Spring Security
- JWT Authentication Filter
- Protected REST APIs
- Protected WebSocket Connection
- Security Context
- Role Based Authentication

---

## Chat

- Real-Time Messaging
- WebSocket Communication
- STOMP Protocol
- Join Chat
- Leave Chat
- Broadcast Messages
- Online User Tracking

---

## Database

- User Management
- Role Management
- Chat Room
- Message Persistence
- Automatic Timestamp

---

## Architecture

- Controller Layer
- Service Layer
- Repository Layer
- DTO Layer
- Mapper Layer
- Security Layer
- Entity Layer

---

# 🛠 Tech Stack

## Backend

- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- JWT
- WebSocket
- STOMP
- Maven

---

## Frontend

- React
- React Router
- Axios
- STOMP JS
- HTML5
- CSS3

---

## Database

- MySQL

---

## Tools

- IntelliJ IDEA
- VS Code
- Postman
- MySQL Workbench
- Git
- GitHub

---

# 📂 Project Structure

```
LiveChatApplication
│
├── backend
│
│   ├── controller
│   ├── service
│   ├── repository
│   ├── entity
│   ├── dto
│   ├── mapper
│   ├── security
│   │      ├── jwt
│   │      ├── config
│   │      ├── service
│   │      └── util
│   ├── websocket
│   └── config
│
└── frontend
    ├── pages
    ├── components
    ├── services
    ├── context
    ├── styles
    └── utils
```

---

# 🏗 Layered Architecture

```
                React Frontend
                       │
                       │
                 Axios / WebSocket
                       │
                       ▼
            Spring Boot Controller
                       │
                       ▼
                Service Layer
                       │
                       ▼
                Mapper Layer
                       │
                       ▼
             Repository Layer
                       │
                       ▼
                  MySQL Database
```

---

# 🔐 Authentication Flow

```
User Login

      │
      ▼

React Login Page

      │

POST /api/auth/login

      │

Spring Security

      │

AuthenticationManager

      │

UserDetailsService

      │

Database

      │

JWT Generated

      │

Token Returned

      │

React stores JWT

      │

Every API Request

      │

Authorization Header

Bearer Token

      │

JWT Filter

      │

Security Context

      │

Authenticated User
```

---

# 💬 WebSocket Flow

```
React

      │

Connect WebSocket

      │

Spring WebSocket Endpoint

      │

JWT Validation

      │

Security Context

      │

STOMP

      │

Chat Controller

      │

Chat Service

      │

Database

      │

Broadcast

      │

All Connected Users
```

---

# 💾 Message Persistence Flow

```
User Sends Message

      │

React

      │

WebSocket

      │

ChatController

      │

ChatService

      │

SecurityUtils

      │

Current Logged User

      │

User Repository

      │

Chat Room Repository

      │

Chat Message Repository

      │

MySQL

      │

Saved Successfully

      │

DTO

      │

Broadcast
```

---

# 🗄 Database Schema

```
ROLE

id
name

        │

        │

USER

id
username
email
password
online
role_id

        │

        │

CHAT_ROOM

id
room_name
created_at

        │

        │

CHAT_MESSAGE

id
content
type
timestamp
sender_id
room_id
```

---

# 🔒 Security Architecture

```
Client

      │

Authorization Header

Bearer Token

      │

JwtAuthenticationFilter

      │

Extract Username

      │

Validate Token

      │

Load User

      │

Security Context

      │

Controller
```

---

# 📌 Design Patterns Used

- Dependency Injection
- Builder Pattern
- DTO Pattern
- Repository Pattern
- Service Pattern
- Singleton Pattern
- Factory Pattern (Spring Beans)

---

# 💡 Major Concepts Implemented

- Spring Boot
- Spring Security
- JWT
- BCrypt
- AuthenticationManager
- UserDetailsService
- SecurityContextHolder
- OncePerRequestFilter
- REST API
- WebSocket
- STOMP
- Hibernate
- JPA
- DTO Mapping
- Entity Relationships
- Constructor Injection
- Exception Handling
- Stateless Authentication

---

# 📈 Performance Optimizations

- BCrypt Password Hashing
- Stateless Authentication
- Lazy Loading
- DTO Mapping
- Constructor Injection
- Repository Pattern
- Separate Service Layer
- Efficient Database Relationships

---

# 📸 Screenshots

- Login Screen
- Chat Window
- Theme Toggle
- Multiple Browser Chat
- MySQL Database
- JWT Authentication
- Postman Testing

(Add screenshots here)

---

# 🚀 Future Enhancements

- Private Chat
- Group Chat
- File Sharing
- Image Sharing
- Typing Indicator
- Read Receipts
- Push Notifications
- Refresh Token
- Forgot Password
- Email Verification
- Redis
- Docker
- Kubernetes
- AWS Deployment
- Kafka
- Elasticsearch
- Cloud Storage

---

# 🧠 Challenges Faced & Solutions

### 1. LazyInitializationException

Problem:
```
Could not initialize proxy
```

Solution:
- Used DTO Mapping
- Avoided exposing entities directly
- Loaded required data inside service layer

---

### 2. JWT Authentication Missing

Problem:
```
Authorization Header = null
```

Solution:
- Added Axios Interceptor
- Automatically attached Bearer Token

---

### 3. WebSocket Authentication

Problem:
WebSocket connection was anonymous.

Solution:
- Added JWT in STOMP CONNECT headers.
- Authenticated WebSocket handshake.

---

### 4. Message Persistence

Problem:
Messages were only broadcast, not stored.

Solution:
- Added ChatService
- Saved messages before broadcasting.

---

### 5. Database Constraints

Problem:
```
Column email cannot be null
```

Solution:
- Used authenticated user from SecurityContext instead of creating new users.

---

# 🎯 Learning Outcomes

Through this project I gained hands-on experience with:

- Enterprise Spring Boot Development
- JWT Authentication
- Spring Security
- WebSocket Communication
- STOMP Messaging
- Hibernate
- MySQL
- React Integration
- Clean Architecture
- REST API Design
- Secure Backend Development

---

# 👨‍💻 Author

**Ankush Matre**

Java Full Stack Developer

Spring Boot | React | JWT | WebSocket | MySQL | Hibernate | REST APIs
