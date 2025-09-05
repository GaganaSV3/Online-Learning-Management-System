
-- Create Database:
CREATE DATABASE IF NOT EXISTS learnpro
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE learnpro;

-- Roles
CREATE TABLE roles (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(30) NOT NULL UNIQUE   -- ADMIN / INSTRUCTOR / STUDENT
);


-- Users
CREATE TABLE users (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(100) NOT NULL UNIQUE,
  email VARCHAR(255) NOT NULL UNIQUE,
  password_hash VARCHAR(255) NOT NULL,
  full_name VARCHAR(200),
  is_active BOOLEAN DEFAULT TRUE,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


-- User Roles (many-to-many)
CREATE TABLE user_roles (
  user_id BIGINT NOT NULL,
  role_id INT NOT NULL,
  PRIMARY KEY (user_id, role_id),
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
  FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

-- Courses
CREATE TABLE courses (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  description TEXT,
  is_published BOOLEAN DEFAULT FALSE,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Course Instructors
CREATE TABLE course_instructors (
  course_id BIGINT NOT NULL,
  user_id BIGINT NOT NULL,
  PRIMARY KEY (course_id, user_id),
  FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE,
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Modules
CREATE TABLE modules (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  course_id BIGINT NOT NULL,
  title VARCHAR(255) NOT NULL,
  module_order INT NOT NULL,
  FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE,
  UNIQUE (course_id, module_order)
);


-- Enrollments (Student <-> Course)
CREATE TABLE enrollments (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT NOT NULL,
  course_id BIGINT NOT NULL,
  enrolled_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  progress_percent DECIMAL(5,2) DEFAULT 0.00,
  status ENUM('ENROLLED','COMPLETED','CANCELLED') DEFAULT 'ENROLLED',
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
  FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE,
  UNIQUE (user_id, course_id)  -- prevent duplicate enrollment
);


-- Module Progress
CREATE TABLE module_progress (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT NOT NULL,
  module_id BIGINT NOT NULL,
  status ENUM('NOT_STARTED','IN_PROGRESS','COMPLETED') DEFAULT 'NOT_STARTED',
  completed_at TIMESTAMP NULL,
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
  FOREIGN KEY (module_id) REFERENCES modules(id) ON DELETE CASCADE,
  UNIQUE (user_id, module_id)
);


-- Seed Roles
-- ==============================
INSERT IGNORE INTO roles (name) VALUES ('ADMIN'), ('INSTRUCTOR'), ('STUDENT');


-- Admin user
INSERT INTO users (username, email, password_hash, full_name)
VALUES ('admin', 'admin@example.com', 'admin123', 'System Admin');

-- Instructor user
INSERT INTO users (username, email, password_hash, full_name)
VALUES ('instructor1', 'instructor1@example.com', 'instructor123', 'John Instructor');

-- Student user
INSERT INTO users (username, email, password_hash, full_name)
VALUES ('student1', 'student1@example.com', 'student123', 'Alice Student');

