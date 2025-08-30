# 📇 Smart Contact Manager

Smart Contact Manager is a full-stack web application that allows users to manage their contacts securely. It includes features such as user authentication, contact CRUD operations, and an intuitive user interface. This project demonstrates the use of Spring Boot, Thymeleaf, and MySQL, following the MVC design pattern.

---

## 🔍 Features

- ✅ User Registration and Login (with BCrypt password encryption)
- ✅ Role-based Authorization (Admin/User)
- ✅ Add, Update, View, and Delete Contacts
- ✅ Profile Image Upload for Contacts
- ✅ User Dashboard with Pagination and Search
- ✅ Email Integration for Contacting Support
- ✅ Session-based Messaging (Success/Error alerts)

---

## 🛠️ Tech Stack

**Backend**:
- Java 21
- Spring Boot
- Spring MVC
- Spring Data JPA
- Spring Security

**Frontend**:
- HTML, CSS, JavaScript
- Bootstrap 5
- Thymeleaf (Server-side rendering)

**Database**:
- MySQL

**Tools & Others**:
- Maven
- Hibernate
- Vscode
- Git & GitHub

---

## 📁 Project Structure

SmartContactManager/
│
├── src/
│ ├── main/
│ │ ├── java/
│ │ │ └── com.smart/
│ │ │ ├── controller/
│ │ │ ├── config/
│ │ │ ├── dao/
│ │ │ ├── entities/
│ │ │ └── SmartContactManagerApplication.java
│ │ └── resources/
│ │ ├── templates/
│ │ ├── static/
│ │ ├── application.properties
│
├── pom.xml
└── README.md

---

## 🚀 Getting Started

### Prerequisites
- Java 21
- MySQL Server
- Maven
- IDE (Vscode)

### Steps to Run

1. **Clone the Repository**
   ```bash
   git clone https://github.com/adarsh8434/SmartContactManager.git
   cd SmartContactManager
Setup Database

Create a database named smart_contact_manager in MySQL

Update the credentials in application.properties:

properties
Copy
Edit
spring.datasource.url=jdbc:mysql://localhost:3306/smart_contact_manager
-spring.datasource.username= your_username
-spring.datasource.password= your_password
-Run the Application

bash
Copy
Edit
-mvn spring-boot:run
Access the Application

Open your browser and go to: http://localhost:8081

🧑‍💻 Author
-Adarsh Kumar Choubey
-🔗[ LinkedIn](https://www.linkedin.com/in/adarshku/)
-📧 rohitadarsh02@gmail.com

🌟 Support
-If you like this project, please ⭐ the repo to support the work!

