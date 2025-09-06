# 📚 LearnPro - Online Learning Management System (LMS)

LearnPro is a **full-stack web application** for managing online courses.  
It provides role-based access for **Admins, Instructors, and Students**, allowing smooth course management, enrollment, and progress tracking.

---

## 🚀 Features

### 👨‍🎓 Student Dashboard
- View all available courses
- Enroll in courses (only once per course)
- Track progress (modules completed vs total)

### 👩‍🏫 Instructor Dashboard
- Create new courses
- Update existing course content (modules, lessons, materials)
- View list of enrolled students for each course

### 🛠️ Admin Dashboard
- Manage users (create/delete Instructors and Students)
- Assign roles (Admin → Instructor → Student)
- View all data in the system (users, courses, enrollments)

### 📱 Responsive UI
- Works on both desktop and mobile devices
- Built with **HTML, CSS, JavaScript** (or React / Django Templates if preferred)

---

## 🏗️ Tech Stack

### Backend
- **Java 17+/21**
- **Spring Boot 3**
- **Spring Data JPA + Hibernate**
- **MySQL**
- REST APIs with validation and error handling

### Frontend
- **HTML, CSS, JavaScript** (starter version)
- Optional: **React** or **Django Templates**

### Tools
- Maven for build automation
- Lombok for boilerplate code reduction
- Eclipse / IntelliJ IDEA for development


## 📂 Project Structure

learnpro/
│── backend/ # Spring Boot Application
│ ├── src/main/java/com/learnpro/lms
│ │ ├── controller/ # REST API Controllers
│ │ ├── model/ # Entities (User, Role, Course, Enrollment, etc.)
│ │ ├── repository/ # JPA Repositories
│ │ ├── service/ # Business Logic Layer
│ │ └── LmsApplication.java
│ ├── src/main/resources/
│ │ ├── application.properties
│ │ └── schema.sql / data.sql (optional for seed data)
│ └── pom.xml
│
│── frontend/ # Static Frontend (HTML, CSS, JS)
│ ├── index.html # Login / Landing Page
│ ├── student.html # Student Dashboard
│ ├── instructor.html # Instructor Dashboard
│ ├── admin.html # Admin Dashboard
│ └── assets/ # CSS, JS, Images
│
└── README.md



---
# 📚 LearnPro LMS

A full-stack Learning Management System (LMS) built with **Spring Boot, MySQL, and HTML/CSS/JS**.  
It allows **Admins, Instructors, and Students** to manage courses, enrollments, and track progress.

---

## 🚀 Getting Started

### 1. Clone the Repository
```bash
git clone https://github.com/your-username/learnpro-lms.git
cd learnpro-lms

#### 2.Setup Backend (Spring Boot)

Open the project in Eclipse / IntelliJ

Update src/main/resources/application.properties with your MySQL credentials:

Update src/main/resources/application.properties with your MySQL credentials:

spring.datasource.url=jdbc:mysql://localhost:3306/learnpro?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true


Run the backend:

mvn spring-boot:run


Backend runs on http://localhost:8080

3. Setup Frontend

Navigate to the frontend/ folder

Open index.html in a browser (or use a local server like Live Server in VS Code)

🗄️ Database Schema (Simplified)

Users (Admin / Instructor / Student)

Roles (many-to-many with Users)

Courses (created by Instructors, viewed by Students)

Enrollments (Student ↔ Course, with progress tracking)

Modules & ModuleProgress (track completion per student)

🖼️ Screenshots
🔑 Login Page
![Uploading {2C3C3C36-22F6-412C-AA26-37997BBB8DD6}.png…]()


👨‍🎓 Student Dashboard
<img width="1889" height="883" alt="image" src="https://github.com/user-attachments/assets/f963dc4c-e3e7-4e1d-98a2-375f410a4ec8" />
<img width="1890" height="879" alt="image" src="https://github.com/user-attachments/assets/6459c7c7-4998-4f43-9406-57b875d16a9b" />
<img width="1870" height="874" alt="image" src="https://github.com/user-attachments/assets/63889eeb-5c25-4aa8-866b-342c92e50191" />

👩‍🏫 Instructor Dashboard
<img width="1880" height="876" alt="image" src="https://github.com/user-attachments/assets/be093d6d-1c72-4b6c-bcb9-56dabd13085e" />
<img width="1883" height="883" alt="image" src="https://github.com/user-attachments/assets/7b1a27bb-de51-4810-91f7-c62a4b0615ef" />
<img width="1883" height="884" alt="{C457D925-CA55-459D-99D1-16342D1432C7}" src="https://github.com/user-attachments/assets/03bd298c-37ea-453b-becc-6e645bb673b1" />

🛠️ Admin Dashboard
<img width="1881" height="889" alt="{15553ECE-33EF-44C7-9784-F92E92E2A21C}" src="https://github.com/user-attachments/assets/fee578b7-fd5f-4404-adfa-8de612467e2d" />
<img width="1888" height="901" alt="{5A513A5C-B04C-45E1-9097-A71B0FAA65AF}" src="https://github.com/user-attachments/assets/de5bb710-93be-4ea4-a441-905aaab2673e" />



📌 API Endpoints (Sample)
User Authentication

POST /api/auth/login → login user

POST /api/auth/register → register student/instructor

Courses

POST /api/courses → create course (Instructor only)

GET /api/courses → list all courses

GET /api/courses/{id} → course details

Enrollment

POST /api/enroll/{courseId} → student enrolls in course

GET /api/enrollments/{studentId} → get student’s courses

PUT /api/progress/{moduleId} → update progress

Admin

POST /api/admin/create-instructor → create Instructor

DELETE /api/admin/delete-user/{id} → delete user
.

🧪 Future Enhancements

Add JWT authentication for secure login

Add file uploads for course content

Add quiz & certification support

Integrate email notifications

🤝 Contributing

Contributions are welcome! Please fork this repo, create a branch, and submit a pull request.

📜 License

This project is licensed under the MIT License - see the LICENSE file for details.

👩‍💻 Author

Developed by [GAGANASHREE S V]
🎓 Full-stack Java Developer | Spring Boot | MySQL | Web Development
