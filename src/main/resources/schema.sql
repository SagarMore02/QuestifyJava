-- SuperUser table
CREATE TABLE IF NOT EXISTS SuperUser (
    username VARCHAR(20) NOT NULL,
    password VARCHAR(300) NOT NULL
);
-- User_Master table
CREATE TABLE IF NOT EXISTS User_Master (
    userID SERIAL PRIMARY KEY,
    username VARCHAR(20) NOT NULL UNIQUE,
    password VARCHAR(300) NOT NULL,
    firstname VARCHAR(10) NOT NULL,
    lastname VARCHAR(10),
    usertype VARCHAR(20) NOT NULL,  -- was ENUM('Applicant','Organization','Organizer')
    Department VARCHAR(30) DEFAULT 'N/A', -- was ENUM('Computer-Science','Computer-Applications','Data-Science','IMCA','N/A')
    mobile VARCHAR(15) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    status VARCHAR(20) DEFAULT 'Pending',  -- was ENUM('Pending','Active','Inactive')
    timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
-- Organization table
CREATE TABLE IF NOT EXISTS Organization (
    organizationID INT PRIMARY KEY,
    name VARCHAR(10) NOT NULL,
    location VARCHAR(15) NOT NULL,
    timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
-- Application_Master table
CREATE TABLE IF NOT EXISTS Application_Master (
    personalID SERIAL BIGSERIAL PRIMARY KEY,
    applicationID INT NOT NULL,
    examID INT NOT NULL,
    adhaarcard VARCHAR(14) NOT NULL,
    feesstatus VARCHAR(20) NOT NULL DEFAULT 'Pending',  -- was ENUM('Paid','Pending')
    tokenid VARCHAR(30),
    appstatus VARCHAR(20) NOT NULL DEFAULT 'Pending',   -- was ENUM('Pending','Active','Inactive')
    attendance VARCHAR(20) NOT NULL DEFAULT 'Pending',  -- was ENUM('Pending','Present','Absent')
    marks INT NOT NULL DEFAULT 0,
    timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    --PRIMARY KEY (examID, applicationID)   Handle This in Java application id and user id is same
);

-- Organizer_Organization table
CREATE TABLE IF NOT EXISTS Organizer_Organization (
    organizerID INT NOT NULL,
    organizationID INT NOT NULL,
    timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    --PRIMARY KEY (organizerID, organizationID) Handle it
);
-- Exam_Master table
CREATE TABLE IF NOT EXISTS Exam_Master (
    examID SERIAL PRIMARY KEY,
    organizerID INT NOT NULL,
    Department VARCHAR(30) DEFAULT 'N/A',  -- was ENUM('Computer-Science','Computer-Applications','Data-Science','IMCA','N/A')
    name VARCHAR(15) NOT NULL,
    app_start_date DATE NOT NULL,
    app_end_date DATE NOT NULL,
    exam_start_time TIME NOT NULL,
    exam_start_date DATE NOT NULL,
    exam_end_date DATE NOT NULL,
    exam_end_time TIME NOT NULL,
    total_marks INT NOT NULL,
    passing_marks INT NOT NULL,
    status VARCHAR(20) NOT NULL,  -- was ENUM('Pending','Stopped','Completed')
    fees INT NOT NULL,
    syllabus VARCHAR(100) NOT NULL,
    timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
-- Question_Master table
CREATE TABLE IF NOT EXISTS Question_Master (
    questionID SERIAL,
    examID INT NOT NULL,
    questiontype VARCHAR(20),
    question VARCHAR(70) NOT NULL,
    optionA VARCHAR(40),
    optionB VARCHAR(40),
    optionC VARCHAR(40),
    optionD VARCHAR(40),
    optionE VARCHAR(40),
    optionF VARCHAR(40),
    answer_key VARCHAR(20) NOT NULL,  -- was ENUM('optionA','optionB','optionC','optionD','optionE','optionF','UnSelected')
    question_marks INT NOT NULL,
    timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    --PRIMARY KEY (questionID, examID) Handle it
);
-- Attempt_Master table
CREATE TABLE IF NOT EXISTS Attempt_Master (
    attemptID SERIAL UNIQUE,
    examID INT NOT NULL,
    questionID INT NOT NULL,
    applicationID INT NOT NULL,
    selected_option VARCHAR(20) NOT NULL,  -- was ENUM('optionA', 'optionB', 'optionC', 'optionD','optionE','optionF')
    timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (examID, questionID, applicationID)
);
-- Transaction_Master table
CREATE TABLE IF NOT EXISTS Transaction_Master (
    trans_id INT PRIMARY KEY UNIQUE,
    exam_id INT UNIQUE NOT NULL,
    paidfees INT NOT NULL,
    upi_token VARCHAR(40) NOT NULL,
    timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
-- Change_Log table to track changes
CREATE TABLE IF NOT EXISTS Change_Log (
    logID SERIAL PRIMARY KEY,
    table_number INT NOT NULL,
    changed_by VARCHAR(20) NOT NULL,
    change_type VARCHAR(10) NOT NULL,  -- was ENUM('INSERT','UPDATE','DELETE')
    change_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);