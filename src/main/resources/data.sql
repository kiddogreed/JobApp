-- Insert users (admin, jobseeker, employer)
INSERT INTO users (id, username, password, role, email) VALUES
  (1, 'admin', 'adminpass', 'ADMIN', 'admin@example.com'),
  (2, 'jobseeker', 'jobseekerpass', 'JOBSEEKER', 'jobseeker@example.com'),

  (3, 'employer', 'employerpass', 'EMPLOYER', 'employer@example.com');

-- Insert profile details for users
INSERT INTO profile (id, user_id, user_name, first_name, last_name, middle_name, address, company_name, email, phone, role)
VALUES
  (1, 1, 'admin', 'Admin', 'User', '', 'Admin Address', '', 'admin@example.com', '1111111111', 'ADMIN'),
  (2, 2, 'jobseeker', 'Job', 'Seeker', '', 'Jobseeker Address', '', 'jobseeker@example.com', '2222222222', 'JOBSEEKER'),
  (3, 3, 'employer', 'Employer', 'User', '', 'Employer Address', 'Employer Inc', 'employer@example.com', '3333333333', 'EMPLOYER');

-- Insert job posts (from JobRepository)
INSERT INTO job_post (id, title, description, experience, skills) VALUES
  (1, 'Java Developer', 'Must have good experience in core Java and advanced Java', 2, 'Core Java,J2EE,Spring Boot,Hibernate'),
  (2, 'Frontend Developer', 'Experience in building responsive web applications using React', 3, 'HTML,CSS,JavaScript,React'),
  (3, 'Data Scientist', 'Strong background in machine learning and data analysis', 4, 'Python,Machine Learning,Data Analysis'),
  (4, 'Network Engineer', 'Design and implement computer networks for efficient data communication', 5, 'Networking,Cisco,Routing,Switching'),
  (5, 'Mobile App Developer', 'Experience in mobile app development for iOS and Android', 3, 'iOS Development,Android Development,Mobile App'),
  (6, 'DevOps Engineer', 'Implement and manage continuous integration and delivery pipelines', 4, 'DevOps,CI/CD,Docker,Kubernetes'),
  (7, 'UI/UX Designer', 'Create engaging user experiences and intuitive user interfaces', 2, 'User Experience,User Interface Design,Prototyping'),
  (8, 'Cybersecurity Analyst', 'Protect computer systems and networks from cyber threats', 4, 'Cybersecurity,Network Security,Incident Response'),
  (9, 'Full Stack Developer', 'Experience in both front-end and back-end development', 5, 'JavaScript,Node.js,React,Spring,MongoDB'),
  (10, 'Cloud Architect', 'Design and implement cloud infrastructure solutions', 6, 'Cloud Computing,AWS,Azure,Google Cloud'),
  (11, 'Software Tester', 'Ensure software quality through testing and validation', 3, 'Testing,JUnit,Selenium,TestNG'),
  (12, 'React Native Developer', 'Develop cross-platform mobile applications using React Native', 2, 'React Native,JavaScript,Mobile App Development'),
  (13, 'Business Analyst', 'Analyze business processes and recommend improvements', 4, 'Business Analysis,Requirements Gathering,Process Modeling'),
  (14, 'Embedded Systems Engineer', 'Design and develop embedded systems for hardware applications', 5, 'Embedded Systems,C/C++,Microcontrollers,RTOS'),
  (15, 'Content Writer', 'Create engaging and informative content for websites and publications', 2, 'Content Writing,Copywriting,SEO,Creative Writing'),
  (16, 'Business Intelligence Analyst', 'Utilize data to provide insights and support decision-making', 4, 'Business Intelligence,SQL,Data Warehousing,Tableau'),
  (17, 'UX Researcher', 'Conduct user research to inform the design process', 3, 'User Research,Usability Testing,Human-Computer Interaction'),
  (18, 'Backend Developer', 'Build server-side logic and databases for web applications', 4, 'Java,Spring,Node.js,MongoDB'),
  (19, 'Game Developer', 'Create and optimize games for various platforms', 3, 'Game Development,Unity,C#,3D Modeling'),
  (20, 'IT Project Manager', 'Lead and manage IT projects from initiation to completion', 6, 'Project Management,Agile,Scrum,Risk Management');
