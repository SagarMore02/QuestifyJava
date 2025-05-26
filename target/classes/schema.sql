DROP TABLE IF EXISTS SuperUser;
DROP TABLE IF EXISTS Organizer_Organization;
DROP TABLE IF EXISTS Exam_Master;
DROP TABLE IF EXISTS Question_Master;
DROP TABLE IF EXISTS Attempt_Master;
DROP TABLE IF EXISTS Application_Master;
DROP TABLE IF EXISTS Organization;
DROP TABLE IF EXISTS User_Master;
-- This script creates the necessary tables for the application.

-- SuperUser table
CREATE TABLE IF NOT EXISTS SuperUser (
    username VARCHAR(20) NOT NULL,
    password VARCHAR(300) NOT NULL
);
-- User_Master table
CREATE TABLE IF NOT EXISTS User_Master (
    user_id BIGSERIAL PRIMARY KEY,
    username VARCHAR(20) NOT NULL UNIQUE,
    password VARCHAR(300) NOT NULL,
    first_name VARCHAR(10) NOT NULL,
    last_name VARCHAR(10),
    user_type VARCHAR(20) NOT NULL,  -- was ENUM('Applicant','Organization','Organizer')
    Department VARCHAR(30) DEFAULT 'N/A', -- was ENUM('Computer-Science','Computer-Applications','Data-Science','IMCA','N/A')
    mobile VARCHAR(15) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    status VARCHAR(20) DEFAULT 'Pending',  -- was ENUM('Pending','Active','Inactive')
    timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
-- Organization table
CREATE TABLE IF NOT EXISTS Organization (
    organization_id BIGINT PRIMARY KEY,
    organization_name VARCHAR(10) NOT NULL,
    location VARCHAR(15) NOT NULL,
    timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
-- Application_Master table
CREATE TABLE IF NOT EXISTS Application_Master (
    personalID BIGSERIAL PRIMARY KEY,
    application_id BIGSERIAL NOT NULL,
    exam_id BIGSERIAL NOT NULL,
    adhaarcard VARCHAR(14) NOT NULL,
    fee_status VARCHAR(20) NOT NULL DEFAULT 'Pending',  -- was ENUM('Paid','Pending')
    tokenid VARCHAR(30),
    app_status VARCHAR(20) NOT NULL DEFAULT 'Pending',   -- was ENUM('Pending','Active','Inactive')
    attendance VARCHAR(20) NOT NULL DEFAULT 'Pending',  -- was ENUM('Pending','Present','Absent')
    marks BIGINT NOT NULL DEFAULT 0,
    timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
    --PRIMARY KEY (examID, applicationID)   Handle This in Java application id and user id is same
);

-- Organizer_Organization table
CREATE TABLE IF NOT EXISTS Organizer_Organization (
    organizer_organizationID BIGSERIAL PRIMARY KEY,
    organizer_id BIGINT NOT NULL,
    organization_id BIGINT NOT NULL,
    timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
    --PRIMARY KEY (organizerID, organizationID) Handle it
);
-- Exam_Master table
CREATE TABLE IF NOT EXISTS Exam_Master (
    exam_id BIGSERIAL PRIMARY KEY,
    organizer_id BIGINT NOT NULL,
    Department VARCHAR(30) DEFAULT 'N/A',  -- was ENUM('Computer-Science','Computer-Applications','Data-Science','IMCA','N/A')
    name VARCHAR(15) NOT NULL,
    app_start_date DATE NOT NULL,
    app_end_date DATE NOT NULL,
    exam_start_time TIME NOT NULL,
    exam_start_date DATE NOT NULL,
    exam_end_date DATE NOT NULL,
    exam_end_time TIME NOT NULL,
    total_marks BIGINT NOT NULL,
    passing_marks BIGINT NOT NULL,
    status VARCHAR(20) NOT NULL,  -- was ENUM('Pending','Stopped','Completed')
    fees BIGINT NOT NULL,
    syllabus VARCHAR(100) NOT NULL,
    timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
-- Question_Master table
CREATE TABLE IF NOT EXISTS Question_Master (
    question_id BIGSERIAL,
    exam_id BIGINT NOT NULL,
    question_type VARCHAR(20),
    question VARCHAR(70) NOT NULL,
    optionA VARCHAR(40),
    optionB VARCHAR(40),
    optionC VARCHAR(40),
    optionD VARCHAR(40),
    optionE VARCHAR(40),
    optionF VARCHAR(40),
    answer_key VARCHAR(20) NOT NULL,  -- was ENUM('optionA','optionB','optionC','optionD','optionE','optionF','UnSelected')
    question_marks BIGINT NOT NULL,
    timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
    --PRIMARY KEY (questionID, examID) Handle it
);
-- Attempt_Master table
CREATE TABLE IF NOT EXISTS Attempt_Master (
    attempt_id BIGSERIAL UNIQUE,
    exam_id BIGINT NOT NULL,
    question_id BIGINT NOT NULL,
    application_id BIGINT NOT NULL,
    selected_option VARCHAR(20) NOT NULL,  -- was ENUM('optionA', 'optionB', 'optionC', 'optionD','optionE','optionF')
    timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
    --PRIMARY KEY (examID, questionID, applicationID) Handle it
);
