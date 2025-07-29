# 🍔 Swiggato - Food Delivery Application

Swiggato is a **Food Delivery Backend Application** developed using **Java**, **Spring Boot**, **Kafka**, and **PostgreSQL**. It provides robust APIs for managing user orders, restaurants, menu items, delivery tracking, and feedback mechanisms in a scalable and secure architecture.

---

## 🛠️ Tech Stack

| Layer                   | Technology                                |
|-------------------------|-------------------------------------------|
| Backend Framework        | Spring Boot                              |
| Database                 | PostgreSQL                               |
| Messaging Queue          | Apache Kafka                             |
| API Security             | JWT (JSON Web Token)                     |
| API Testing              | Postman                                  |
| Documentation            | Swagger (OpenAPI)                        |
| Build Tool               | Maven                                    |
| Version Control          | Git & GitHub                             |

---

## 🌟 Features

- User Registration & Login with JWT Authentication.
- Restaurant & Menu Browsing with Search and Filter.
- Order Placement, Tracking, and Status Updates.
- Admin Panel for Restaurant & Menu Management.
- Customer Feedback & Rating System.
- Real-Time Order Processing using Kafka Streaming.
- Exception Handling using Spring's Global Exception Handler.
- RESTful API Design with Proper Response Wrappers.
- API Documentation with Swagger UI.

---

## 🏗️ Project Structure

src/
├── main/
│ ├── java/
│ │ └── com.swiggato
│ │ ├── controller
│ │ ├── service
│ │ ├── model
│ │ ├── repository
│ │ └── kafka
│ └── resources/
│ ├── application.properties
│ └── static/
└── test/


---

## ⚙️ Installation & Run

### Prerequisites:
- Java 17+
- Maven
- PostgreSQL
- Kafka (Apache Kafka locally or Confluent Cloud)

### Steps:
1. **Clone the Repository**
   ```bash
   git clone https://github.com/patelrambharat/Food-delivery-Application.git
   cd Food-delivery-Application
Configure Database

Update application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/swiggato_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
Configure Kafka Broker

spring.kafka.bootstrap-servers=localhost:9092
Run the Application

mvn spring-boot:run
Access API Documentation

Swagger UI: http://localhost:8080/swagger-ui.html

📂 Key API Endpoints
Method	Endpoint	Description
POST	/api/auth/register	User Registration
POST	/api/auth/login	User Login (JWT Token)
GET	/api/restaurant/list	View Restaurants & Menus
POST	/api/order/place	Place an Order
GET	/api/order/status/{orderId}	Get Order Status
PUT	/api/admin/restaurant/add	Add New Restaurant (Admin)
POST	/api/feedback	Submit Feedback

🛡️ Security & Authentication
JWT-based Authentication for User Login and Secured API Access.

Role-Based Access Control for Admin & User functionalities.

Global Exception Handling for consistent error responses.

📌 Future Enhancements
OAuth2 Login (Google/Facebook)

Payment Gateway Integration

User Notification System (Email/SMS)

Advanced Search with Pagination

Deployment on AWS with Docker & Kubernetes

🤝 Contribution
Feel free to fork this repository and raise Pull Requests. Open Issues for feature requests or bugs.
