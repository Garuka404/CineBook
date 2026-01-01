# 🎬 Cinema Reservation System

A comprehensive cinema booking management system built with Spring Boot, providing complete functionality for movie reservations, seat management, and payment processing.

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Database Schema](#database-schema)
- [Getting Started](#getting-started)
- [API Documentation](#api-documentation)
- [Project Structure](#project-structure)
- [Configuration](#configuration)
- [Usage Examples](#usage-examples)
- [Contributing](#contributing)
- [License](#license)

## 🎯 Overview

This Cinema Reservation System is a full-featured backend application that manages cinema operations including customer registration, movie scheduling, seat booking, and payment processing across multiple branches. The system implements proper separation of concerns using DTOs, comprehensive validation, and robust error handling.

## ✨ Features

### Core Functionality
- **Customer Management**: User registration, authentication, and profile management
- **Movie Management**: Add, update, and manage movie information with scheduling
- **Multi-Branch Support**: Manage multiple cinema branches with individual halls
- **Seat Booking System**: 
  - 7 seat types (Standard, Premium, VIP, Luxury, Recliner, Couple, Wheelchair)
  - Real-time seat availability
  - Dynamic pricing based on seat type
- **Reservation Management**: 
  - Create, view, and cancel reservations
  - Automatic seat allocation
  - Total price calculation
- **Payment Processing**: Multiple payment methods support
- **Transaction Tracking**: Complete payment history and transaction logs

### Technical Features
- RESTful API architecture
- DTO pattern for request/response separation
- Comprehensive input validation
- Proper error handling with meaningful HTTP status codes
- Transaction management for data consistency
- Logging with SLF4J
- Enum-based type safety (SeatType, PaymentType)

## 🛠 Technology Stack

### Backend
- **Java 17+**
- **Spring Boot 3.x**
- **Spring Data JPA** - Database operations
- **Spring Web** - REST API
- **Spring Validation** - Input validation
- **Lombok** - Reduce boilerplate code
- **ModelMapper** - Entity-DTO mapping
- **SLF4J** - Logging

### Database
- **MySQL** / **PostgreSQL** (configurable)
- **Hibernate** - ORM

### Build Tool
- **Maven** / **Gradle**

## 🗄 Database Schema

### Entities and Relationships

```
Customer (1) ──makes──> (M) Reservation (M) ──arrive──> (1) Cinema (M) ──has──> (1) Branch
    │                         │                              │
    │                         │                              │
  (1:M)                     (1:M)                          (M:1)
    │                         │                              │
    ├──> Transaction          ├──> Seat                      └──> Movie
    │                         │
  (M:M)                     (M:1)
    │                         │
    └──> Payment_Type         └──> Branch
```

### Key Entities

#### Customer
- `CUS_Number` (PK)
- `CUS_age`
- `CUS_Name`
- `CUS_Address`
- `CUS_email`
- `CUS_id`

#### Reservation
- `Rsv_Code` (PK)
- `Con_Number`
- `Time`
- `Date`
- `Description`

#### Cinema (Hall)
- `Hall_ID` (PK)
- `Hall_Name`
- `Hall_Location`
- `Hall_Number`

#### Seat
- `seat_id` (PK)
- `row_letter`
- `seat_number`
- `seat_type` (ENUM)
- `is_available`

#### Movie
- `Flm_ID` (PK)
- `Flm_Name`
- `Date`
- `Description`
- `Endtime`

#### Branch
- `Brch_ID` (PK)
- `BranCon_Number`
- `BRCH_Name`

#### Transaction
- `tra_no` (PK)
- `total_payement`

#### Payment_Type
- `payment_type_no` (PK)
- `payment_type_name`

## 🚀 Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6+ or Gradle 7+
- MySQL 8.0+ or PostgreSQL 12+
- IDE (IntelliJ IDEA, Eclipse, or VS Code)

### Installation

1. **Clone the repository**
```bash
git clone https://github.com/yourusername/cinema-reservation-system.git
cd cinema-reservation-system
```

2. **Configure Database**

Edit `src/main/resources/application.properties`:

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/cinema_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Server Configuration
server.port=8080

# Logging
logging.level.com.cinema=DEBUG
logging.level.org.springframework.web=INFO
```

3. **Create Database**
```sql
CREATE DATABASE cinema_db;
```

4. **Build the project**

Using Maven:
```bash
mvn clean install
```

Using Gradle:
```bash
gradle build
```

5. **Run the application**

Using Maven:
```bash
mvn spring-boot:run
```

Using Gradle:
```bash
gradle bootRun
```

The application will start on `http://localhost:8080`

## 📚 API Documentation

### Base URL
```
http://localhost:8080/api
```

### Authentication
*(Add authentication details if implemented)*

### Endpoints

#### Customer Endpoints

```http
POST   /api/customers              # Create new customer
GET    /api/customers              # Get all customers
GET    /api/customers/{id}         # Get customer by ID
PUT    /api/customers/{id}         # Update customer
DELETE /api/customers/{id}         # Delete customer
```

#### Reservation Endpoints

```http
POST   /api/reservations           # Create new reservation
GET    /api/reservations           # Get all reservations
GET    /api/reservations/{id}      # Get reservation by ID
GET    /api/reservations/customer/{customerId}  # Get customer reservations
DELETE /api/reservations/{id}      # Cancel reservation
```

**POST /api/reservations - Request Body:**
```json
{
  "conNumber": "0771234567",
  "description": "Family movie night",
  "date": "2024-12-15",
  "time": "19:30:00",
  "customerId": 1,
  "branchId": 2,
  "cinemaId": 5,
  "movieId": 10,
  "seatIds": [101, 102, 103]
}
```

**Response:**
```json
{
  "message": "Reservation made successfully",
  "data": {
    "reservationId": 123,
    "conNumber": "0771234567",
    "description": "Family movie night",
    "date": "2024-12-15",
    "time": "19:30:00",
    "customer": {
      "customerId": 1,
      "firstName": "John",
      "lastName": "Doe",
      "email": "john@example.com",
      "phoneNumber": "0771234567"
    },
    "cinema": {
      "cinemaId": 5,
      "hallName": "Hall 3",
      "hallNumber": 3,
      "branch": {
        "branchId": 2,
        "branchName": "Colombo City Center",
        "branchLocation": "Colombo"
      }
    },
    "movie": {
      "movieId": 10,
      "filmName": "Avatar 3",
      "duration": 180,
      "genre": "Sci-Fi"
    },
    "seats": [
      {
        "seatId": 101,
        "seatNumber": "A1",
        "seatType": "VIP",
        "price": 1200.00
      }
    ],
    "totalPrice": 3600.00,
    "status": "CONFIRMED"
  },
  "success": true
}
```

#### Movie Endpoints

```http
POST   /api/movies                 # Add new movie
GET    /api/movies                 # Get all movies
GET    /api/movies/{id}            # Get movie by ID
PUT    /api/movies/{id}            # Update movie
DELETE /api/movies/{id}            # Delete movie
GET    /api/movies/cinema/{cinemaId}  # Get movies by cinema
```

#### Seat Endpoints

```http
GET    /api/seats                  # Get all seats
GET    /api/seats/{id}             # Get seat by ID
GET    /api/seats/cinema/{cinemaId}  # Get seats by cinema
GET    /api/seats/available        # Get available seats
POST   /api/seats                  # Create new seat
PUT    /api/seats/{id}             # Update seat
```

#### Cinema/Hall Endpoints

```http
GET    /api/cinemas                # Get all cinema halls
GET    /api/cinemas/{id}           # Get cinema by ID
GET    /api/cinemas/branch/{branchId}  # Get cinemas by branch
POST   /api/cinemas                # Add new cinema hall
PUT    /api/cinemas/{id}           # Update cinema
DELETE /api/cinemas/{id}           # Delete cinema
```

#### Branch Endpoints

```http
GET    /api/branches               # Get all branches
GET    /api/branches/{id}          # Get branch by ID
POST   /api/branches               # Create new branch
PUT    /api/branches/{id}          # Update branch
DELETE /api/branches/{id}          # Delete branch
```

#### Transaction Endpoints

```http
POST   /api/transactions           # Create transaction
GET    /api/transactions           # Get all transactions
GET    /api/transactions/{id}      # Get transaction by ID
GET    /api/transactions/customer/{customerId}  # Get customer transactions
```

## 📁 Project Structure

```
cinema-reservation-system/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── cinema/
│   │   │           ├── CinemaApplication.java
│   │   │           ├── config/
│   │   │           │   └── ModelMapperConfig.java
│   │   │           ├── controller/
│   │   │           │   ├── ReservationController.java
│   │   │           │   ├── CustomerController.java
│   │   │           │   ├── MovieController.java
│   │   │           │   ├── SeatController.java
│   │   │           │   ├── CinemaController.java
│   │   │           │   ├── BranchController.java
│   │   │           │   └── TransactionController.java
│   │   │           ├── dto/
│   │   │           │   ├── request/
│   │   │           │   │   ├── ReservationRequestDTO.java
│   │   │           │   │   ├── CustomerRequestDTO.java
│   │   │           │   │   ├── MovieRequestDTO.java
│   │   │           │   │   └── SeatRequestDTO.java
│   │   │           │   └── response/
│   │   │           │       ├── ReservationResponseDTO.java
│   │   │           │       ├── CustomerDTO.java
│   │   │           │       ├── MovieDTO.java
│   │   │           │       ├── SeatDTO.java
│   │   │           │       ├── CinemaDTO.java
│   │   │           │       ├── BranchDTO.java
│   │   │           │       └── ApiResponse.java
│   │   │           ├── entity/
│   │   │           │   ├── ReservationEntity.java
│   │   │           │   ├── CustomerEntity.java
│   │   │           │   ├── MovieEntity.java
│   │   │           │   ├── SeatEntity.java
│   │   │           │   ├── CinemaEntity.java
│   │   │           │   ├── BranchEntity.java
│   │   │           │   ├── TransactionEntity.java
│   │   │           │   └── PaymentTypeEntity.java
│   │   │           ├── enums/
│   │   │           │   ├── SeatType.java
│   │   │           │   └── PaymentType.java
│   │   │           ├── exception/
│   │   │           │   ├── ResourceNotFoundException.java
│   │   │           │   ├── InvalidRequestException.java
│   │   │           │   └── GlobalExceptionHandler.java
│   │   │           ├── mapper/
│   │   │           │   ├── ReservationMapper.java
│   │   │           │   ├── CustomerMapper.java
│   │   │           │   └── MovieMapper.java
│   │   │           ├── repository/
│   │   │           │   ├── ReservationRepository.java
│   │   │           │   ├── CustomerRepository.java
│   │   │           │   ├── MovieRepository.java
│   │   │           │   ├── SeatRepository.java
│   │   │           │   ├── CinemaRepository.java
│   │   │           │   ├── BranchRepository.java
│   │   │           │   └── TransactionRepository.java
│   │   │           └── service/
│   │   │               ├── ReservationService.java
│   │   │               ├── CustomerService.java
│   │   │               ├── MovieService.java
│   │   │               ├── SeatService.java
│   │   │               ├── CinemaService.java
│   │   │               ├── BranchService.java
│   │   │               └── impl/
│   │   │                   ├── ReservationServiceImpl.java
│   │   │                   ├── CustomerServiceImpl.java
│   │   │                   └── MovieServiceImpl.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── application-dev.properties
│   │       └── application-prod.properties
│   └── test/
│       └── java/
│           └── com/
│               └── cinema/
│                   ├── controller/
│                   ├── service/
│                   └── repository/
├── .gitignore
├── pom.xml
└── README.md
```

## ⚙ Configuration

### Seat Types Configuration

The system supports 7 seat types with different pricing:

| Seat Type | Base Price (LKR) | Description |
|-----------|------------------|-------------|
| STANDARD  | 500.00 | Regular seating |
| PREMIUM   | 800.00 | Enhanced comfort seating |
| VIP       | 1200.00 | Premium seating with extra legroom |
| LUXURY    | 1500.00 | Luxury reclining seats |
| RECLINER  | 1800.00 | Full reclining seats with footrest |
| COUPLE    | 2000.00 | Spacious double seats for couples |
| WHEELCHAIR| 500.00 | Wheelchair accessible seating |

### Environment Profiles

**Development:**
```properties
spring.profiles.active=dev
```

**Production:**
```properties
spring.profiles.active=prod
```

## 💡 Usage Examples

### 1. Creating a Reservation

```bash
curl -X POST http://localhost:8080/api/reservations \
  -H "Content-Type: application/json" \
  -d '{
    "conNumber": "0771234567",
    "description": "Evening show",
    "date": "2024-12-15",
    "time": "19:30:00",
    "customerId": 1,
    "branchId": 2,
    "cinemaId": 5,
    "movieId": 10,
    "seatIds": [101, 102, 103]
  }'
```

### 2. Checking Available Seats

```bash
curl -X GET http://localhost:8080/api/seats/available?cinemaId=5&date=2024-12-15&time=19:30:00
```

### 3. Getting Customer Reservations

```bash
curl -X GET http://localhost:8080/api/reservations/customer/1
```

### 4. Cancelling a Reservation

```bash
curl -X DELETE http://localhost:8080/api/reservations/123
```

## 🔍 Key Design Patterns

### 1. DTO Pattern
Separation of entity and data transfer objects to avoid circular references and control data exposure.

### 2. Service Layer Pattern
Business logic encapsulated in service classes, keeping controllers thin.

### 3. Repository Pattern
Data access abstraction using Spring Data JPA repositories.

### 4. Builder Pattern
Used in entity and DTO construction with Lombok annotations.

## 🧪 Testing

Run tests with:

```bash
# Maven
mvn test

# Gradle
gradle test
```

## 🐛 Common Issues & Solutions

### Issue 1: Circular Reference Error
**Solution:** Ensure you're using DTOs in responses, not entities directly.

### Issue 2: Lazy Loading Exception
**Solution:** Use `@Transactional` on service methods or fetch data eagerly when needed.

### Issue 3: Seat Already Booked
**Solution:** Implement optimistic locking or check availability in a transaction.

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'feat: add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### Commit Message Convention

```
feat: add new feature
fix: bug fix
docs: documentation changes
style: code style changes
refactor: code refactoring
test: add tests
chore: build/config changes
```

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👥 Authors

- **Garuka Gimhana** - *Initial work* - [YourGitHub](https://github.com/Garuka404)

## 🙏 Acknowledgments

- Spring Boot documentation
- Cinema booking system best practices
- Open source community

## 📞 Contact

Project Link: [https://github.com/yourusername/cinema-reservation-system](https://github.com/yourusername/cinema-reservation-system)

---

**Made with ❤️ for cinema enthusiasts**
