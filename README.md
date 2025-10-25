# Journal Web App - Backend

This is the **backend** of a **Journal Web Application**, developed using **Spring Boot**, **Java**, and **MongoDB**. The backend provides RESTful APIs for managing journal entries, user authentication, and sending emails (e.g., notifications or reminders). It uses **Basic Authentication** for secure access.

---

## 🛠 Tech Stack

- **Backend:** Spring Boot, Java  
- **Database:** MongoDB  
- **Authentication:** Basic Authentication (username/password)  
- **Email Service:** JavaMailSender (SMTP)  
- **Build & Run Tools:** Maven  

---

## ⚙️ Features

- User registration and login with **Basic Auth**  
- CRUD operations for journal entries (create, read, update, delete)  
- Send emails for notifications or reminders  
- RESTful API endpoints for frontend consumption  
- JWT is not used; Basic Auth handles authentication  

---

## 📂 Project Structure

Journal-App-Backend/
│
├─ src/main/java/
│ ├─ controller/
│ ├─ model/
│ ├─ repository/
│ ├─ service/
│ └─ JournalAppBackendApplication.java
├─ src/main/resources/
│ ├─ application.properties
├─ pom.xml
└─ README.md

🔐 Authentication

Basic Authentication using HTTP headers

Every request to protected endpoints must include:
Authorization: Basic <base64-encoded-username:password>

📦 Build & Deployment

-Backend: mvn clean install

-Deployment can be done on AWS EC2, Heroku, or any server supporting Java.

👨‍💻 Author

Darshan S V

-GitHub: https://github.com/Darshanshrinandi

-Email: your_email@example.com

