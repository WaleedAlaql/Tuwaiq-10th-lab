# Job Seeking System

A web-based platform built with Spring Boot that connects job seekers with job postings. Employers can create and manage job postings, while job seekers can search for jobs and apply through the system.

---

## Tech Stack & Dependencies
* **Spring Web** (REST APIs)
* **Spring Data JPA** (Database ORM & Repositories)
* **MySQL Driver** (`mysql-connector-j`)
* **Validation** (`jakarta.validation`)
* **Lombok** (Boilerplate code reduction)

---

## Project Structure & Models

### 1. User Model (`User`)
* **id**: Generated automatically.
* **name**: Cannot be null, length > 4 characters, characters only (no numbers).
* **email**: Valid email format, unique.
* **password**: Cannot be null.
* **age**: Cannot be null, must be a number > 21.
* **role**: Cannot be null, must be either `"JOB_SEEKER"` or `"EMPLOYER"`.

### 2. JobPost Model (`JobPost`)
* **id**: Generated automatically.
* **title**: Cannot be null, length > 4 characters.
* **description**: Cannot be null.
* **location**: Cannot be null.
* **salary**: Cannot be null, non-negative number.
* **postingDate**: Auto-generated on creation.

### 3. JobApplication Model (`JobApplication`)
* **id**: Generated automatically.
* **userId**: Cannot be null (References User).
* **jobPostId**: Cannot be null (References JobPost).

---

## API Endpoints Reference

### User Endpoints (`/api/v1/user`)
* **GET /get**: Retrieve a list of all users.
* **POST /add**: Add a new user.
* **PUT /update/{id}**: Update an existing user.
* **DELETE /delete/{id}**: Delete a user (verifies existence).

### Job Post Endpoints (`/api/v1/job-post`)
* **GET /get**: Retrieve a list of all job posts.
* **POST /add**: Add a new job post.
* **PUT /update/{id}**: Update an existing job post.
* **DELETE /delete/{id}**: Delete a job post (verifies existence).

### Job Application Endpoints (`/api/v1/job-application`)
* **GET /get**: Retrieve a list of all job applications.
* **POST /apply**: Apply for a job.
* **DELETE /withdraw/{id}**: Withdraw/delete a job application (verifies existence).
