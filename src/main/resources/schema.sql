-- Table for users
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL,
    email VARCHAR(100)
);

-- Table for profiles
CREATE TABLE profile (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    user_name VARCHAR(50),
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    middle_name VARCHAR(50),
    address VARCHAR(255),
    company_name VARCHAR(100),
    email VARCHAR(100),
    phone VARCHAR(20),
    role VARCHAR(20),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Table for job posts
CREATE TABLE job_post (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    experience INT,
    skills VARCHAR(255)
);

-- Table for job applications
CREATE TABLE job_application (
    id INT AUTO_INCREMENT PRIMARY KEY,
    job_id INT NOT NULL,
    applicant_id INT NOT NULL,
    message VARCHAR(1000),
    attachment VARCHAR(255),
    FOREIGN KEY (job_id) REFERENCES job_post(id),
    FOREIGN KEY (applicant_id) REFERENCES users(id)
);
