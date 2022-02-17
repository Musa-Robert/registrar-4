DROP TABLE IF EXISTS students;

CREATE TABLE students (
   id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
   first_name VARCHAR(250) DEFAULT NULL,
   last_name VARCHAR(250) DEFAULT NULL,
   email VARCHAR(250) DEFAULT NULL,
   phone VARCHAR(250) DEFAULT NULL,
   created_at TIMESTAMP DEFAULT NULL,
   updated_at TIMESTAMP DEFAULT NULL
);