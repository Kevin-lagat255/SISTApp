CREATE DATABASE IF NOT EXISTS sist_university;
USE sist_university;

CREATE TABLE IF NOT EXISTS departments (
                                           dept_id   INT AUTO_INCREMENT PRIMARY KEY,
                                           dept_name VARCHAR(100) NOT NULL UNIQUE
    );

CREATE TABLE IF NOT EXISTS lecturers (
                                         lecturer_id   INT AUTO_INCREMENT PRIMARY KEY,
                                         lecturer_name VARCHAR(150) NOT NULL,
    dept_id       INT NOT NULL,
    FOREIGN KEY (dept_id) REFERENCES departments(dept_id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS courses (
                                       course_id   INT AUTO_INCREMENT PRIMARY KEY,
                                       course_name VARCHAR(150) NOT NULL,
    course_code VARCHAR(20)  NOT NULL UNIQUE
    );

CREATE TABLE IF NOT EXISTS lecturer_courses (
                                                lecturer_id INT NOT NULL,
                                                course_id   INT NOT NULL,
                                                PRIMARY KEY (lecturer_id, course_id),
    FOREIGN KEY (lecturer_id) REFERENCES lecturers(lecturer_id) ON DELETE CASCADE,
    FOREIGN KEY (course_id)   REFERENCES courses(course_id)     ON DELETE CASCADE
    );

INSERT INTO departments (dept_name) VALUES
                                        ('Computer Science'),
                                        ('Information Technology'),
                                        ('Software Engineering');

INSERT INTO lecturers (lecturer_name, dept_id) VALUES
                                                   ('Dr. Alice Mwangi',    1),
                                                   ('Prof. James Otieno',  1),
                                                   ('Dr. Grace Nyambura',  1),
                                                   ('Dr. Brian Ochieng',   2),
                                                   ('Ms. Faith Kemunto',   2),
                                                   ('Dr. Samuel Kirui',    2),
                                                   ('Prof. Mary Bosibori', 3),
                                                   ('Dr. Kevin Mutai',     3),
                                                   ('Ms. Diana Moraa',     3);

INSERT INTO courses (course_name, course_code) VALUES
                                                   ('Data Structures & Algorithms',   'CS101'),
                                                   ('Database Systems',               'CS201'),
                                                   ('Operating Systems',              'CS301'),
                                                   ('Computer Networks',              'CS401'),
                                                   ('Web Technologies',               'IT101'),
                                                   ('Network Administration',         'IT201'),
                                                   ('Cybersecurity Fundamentals',     'IT301'),
                                                   ('Cloud Computing',                'IT401'),
                                                   ('Software Design Patterns',       'SE101'),
                                                   ('Agile Development',              'SE201'),
                                                   ('Mobile Application Development', 'SE301'),
                                                   ('Software Testing & QA',          'SE401');

INSERT INTO lecturer_courses (lecturer_id, course_id) VALUES
                                                          (1,1),(1,2),
                                                          (2,3),(2,4),
                                                          (3,1),(3,3),
                                                          (4,5),(4,6),
                                                          (5,7),(5,8),
                                                          (6,5),(6,7),
                                                          (7,9),(7,10),
                                                          (8,11),(8,12),
                                                          (9,9),(9,11);

CREATE USER IF NOT EXISTS 'sistu'@'localhost' IDENTIFIED BY 'sist2024';
GRANT ALL PRIVILEGES ON sist_university.* TO 'sistu'@'localhost';
FLUSH PRIVILEGES;

SELECT 'Database setup complete!' AS Status;CREATE DATABASE IF NOT EXISTS sist_university;
USE sist_university;

CREATE TABLE IF NOT EXISTS departments (
                                           dept_id   INT AUTO_INCREMENT PRIMARY KEY,
                                           dept_name VARCHAR(100) NOT NULL UNIQUE
    );

CREATE TABLE IF NOT EXISTS lecturers (
                                         lecturer_id   INT AUTO_INCREMENT PRIMARY KEY,
                                         lecturer_name VARCHAR(150) NOT NULL,
    dept_id       INT NOT NULL,
    FOREIGN KEY (dept_id) REFERENCES departments(dept_id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS courses (
                                       course_id   INT AUTO_INCREMENT PRIMARY KEY,
                                       course_name VARCHAR(150) NOT NULL,
    course_code VARCHAR(20)  NOT NULL UNIQUE
    );

CREATE TABLE IF NOT EXISTS lecturer_courses (
                                                lecturer_id INT NOT NULL,
                                                course_id   INT NOT NULL,
                                                PRIMARY KEY (lecturer_id, course_id),
    FOREIGN KEY (lecturer_id) REFERENCES lecturers(lecturer_id) ON DELETE CASCADE,
    FOREIGN KEY (course_id)   REFERENCES courses(course_id)     ON DELETE CASCADE
    );

INSERT INTO departments (dept_name) VALUES
                                        ('Computer Science'),
                                        ('Information Technology'),
                                        ('Software Engineering');

INSERT INTO lecturers (lecturer_name, dept_id) VALUES
                                                   ('Dr. Alice Mwangi',    1),
                                                   ('Prof. James Otieno',  1),
                                                   ('Dr. Grace Nyambura',  1),
                                                   ('Dr. Brian Ochieng',   2),
                                                   ('Ms. Faith Kemunto',   2),
                                                   ('Dr. Samuel Kirui',    2),
                                                   ('Prof. Mary Bosibori', 3),
                                                   ('Dr. Kevin Mutai',     3),
                                                   ('Ms. Diana Moraa',     3);

INSERT INTO courses (course_name, course_code) VALUES
                                                   ('Data Structures & Algorithms',   'CS101'),
                                                   ('Database Systems',               'CS201'),
                                                   ('Operating Systems',              'CS301'),
                                                   ('Computer Networks',              'CS401'),
                                                   ('Web Technologies',               'IT101'),
                                                   ('Network Administration',         'IT201'),
                                                   ('Cybersecurity Fundamentals',     'IT301'),
                                                   ('Cloud Computing',                'IT401'),
                                                   ('Software Design Patterns',       'SE101'),
                                                   ('Agile Development',              'SE201'),
                                                   ('Mobile Application Development', 'SE301'),
                                                   ('Software Testing & QA',          'SE401');

INSERT INTO lecturer_courses (lecturer_id, course_id) VALUES
                                                          (1,1),(1,2),
                                                          (2,3),(2,4),
                                                          (3,1),(3,3),
                                                          (4,5),(4,6),
                                                          (5,7),(5,8),
                                                          (6,5),(6,7),
                                                          (7,9),(7,10),
                                                          (8,11),(8,12),
                                                          (9,9),(9,11);

CREATE USER IF NOT EXISTS 'sistu'@'localhost' IDENTIFIED BY 'sist2024';
GRANT ALL PRIVILEGES ON sist_university.* TO 'sistu'@'localhost';
FLUSH PRIVILEGES;

SELECT 'Database setup complete!' AS Status;