# JobApp Spring Boot Web Application

## Overview
JobApp is a demo Spring Boot web application for managing and viewing job postings. It uses JSP for the view layer and follows a simple repository pattern for storing job data in-memory.

## Features
- View all job posts with details and required tech stack
- Add new job posts (in-memory)
- Responsive UI with Bootstrap
- Example job roles: Java Developer, Frontend Developer, Data Scientist, etc.

## Technologies Used
- Java 21
- Spring Boot 3.4.7
- Spring MVC
- JSP (JavaServer Pages)
- JSTL (Jakarta Standard Tag Library)
- Bootstrap 5

## Project Structure
```
jobapp/
├── src/main/java/com/example/jrrd/jobapp/
│   ├── JobappApplication.java
│   ├── controller/JobController.java
│   ├── model/JobPost.java
│   └── repository/JobRepository.java
├── src/main/webapp/views/
│   ├── home.jsp
│   ├── addjob.jsp
│   ├── success.jsp
│   └── viewalljobs.jsp
├── src/main/resources/application.properties
├── pom.xml
```

## Getting Started
### Prerequisites
- Java 21+
- Maven (or use the Maven Wrapper: `mvnw.cmd` on Windows)

### Build and Run
1. Clone the repository:
   ```
   git clone <your-repo-url>
   cd jobapp
   ```
2. Build the project:
   ```
   .\mvnw.cmd clean package
   ```
3. Run the application:
   ```
   .\mvnw.cmd spring-boot:run
   ```
4. Open your browser and go to [http://localhost:8080/home](http://localhost:8080/home)

### Application Properties
```
spring.application.name=jobapp
spring.mvc.view.prefix=/views/
spring.mvc.view.suffix=.jsp
```

## Notes
- All job data is stored in-memory and resets on application restart.
- JSP files are located in `src/main/webapp/views/`.
- If you encounter Maven issues, use the Maven Wrapper (`.\mvnw.cmd`).

## License
This project is for demo purposes only.
