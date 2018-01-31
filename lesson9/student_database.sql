CREATE DATABASE student_audit;
USE student_audit;
CREATE TABLE Student
	(id				INT AUTO_INCREMENT PRIMARY KEY,
    surname			VARCHAR(15) NOT NULL,
    name			VARCHAR(15) NOT NULL,
    fathername		VARCHAR(15) NOT NULL,
    photo			BLOB,
    autobiography	BLOB,
    group_id		INT,
    join_year		YEAR NOT NULL,
    birth_year		YEAR NOT NULL,
    home_address	VARCHAR(50),
    rating			DECIMAL DEFAULT 0.0 CHECK(rating BETWEEN 0 AND 100)
    );
    
CREATE TABLE Student_Group
	(id				INT AUTO_INCREMENT PRIMARY KEY,
    name			VARCHAR(10) NOT NULL,
    specialty_id	INT NOT NULL
    );
    
CREATE TABLE Specialty
	(id				INT AUTO_INCREMENT PRIMARY KEY,
    name			VARCHAR(45) NOT NULL UNIQUE
    );

    
CREATE TABLE Course
	(id				INT AUTO_INCREMENT PRIMARY KEY,
    name			VARCHAR(45) NOT NULL,
    teacher_id		INT
    );

CREATE TABLE Teacher
	(id				INT AUTO_INCREMENT PRIMARY KEY,
    surname			VARCHAR(15) NOT NULL,
    name			VARCHAR(15) NOT NULL,
    fathername		VARCHAR(15) NOT NULL
    );

CREATE TABLE Module_Result
	(module_number	INT NOT NULL CHECK(module_number BETWEEN 1 AND 4),
    student_id		INT NOT NULL,
	course_id		INT NOT NULL,
    grade			INT	DEFAULT 0 CHECK (grade BETWEEN 0 AND 50)
    );

CREATE TABLE Semester_Result
	(semester_number	INT NOT NULL CHECK(semester_number IN (1,2)),
    student_id			INT NOT NULL,
    course_id			INT NOT NULL,
    grade				DECIMAL DEFAULT 0 CHECK (grade BETWEEN 0 AND 100),
    control_type		VARCHAR(4) DEFAULT 'test' CHECK(control_type IN('test', 'exam'))
    );
    
CREATE TABLE Scholarship
	(student_id		INT NOT NULL,
    amount			DECIMAL NOT NULL
    );
    
ALTER TABLE Student
	ADD FOREIGN KEY (group_id)
		REFERENCES Student_Group (id)
        ON UPDATE CASCADE
        ON DELETE SET NULL;
        
ALTER TABLE Course
	ADD FOREIGN KEY (teacher_id) 
		REFERENCES Teacher (id)
        ON UPDATE CASCADE
        ON DELETE SET NULL;

ALTER TABLE Student_Group
	ADD FOREIGN KEY (specialty_id)
		REFERENCES Specialty (id)
        ON UPDATE CASCADE;
        
ALTER TABLE Module_Result
	ADD FOREIGN KEY (student_id)
		REFERENCES Student (id)
        ON UPDATE CASCADE,
	ADD FOREIGN KEY (course_id)
		REFERENCES Course (id)
        ON UPDATE CASCADE;
        
ALTER TABLE Semester_Result
	ADD FOREIGN KEY (student_id)
		REFERENCES Student (id)
        ON UPDATE CASCADE,
	ADD FOREIGN KEY (course_id)
		REFERENCES Course (id)
        ON UPDATE CASCADE;
	
ALTER TABLE Scholarship
	ADD FOREIGN KEY (student_id)
		REFERENCES Student (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE;
        
INSERT INTO Specialty (name)
	VALUES 	('Information Security'),
			('Computer Engineering'),
            ('System Engineering'),
            ('Computert Science');
            
INSERT INTO Student_Group (name, specialty_id)
	VALUES 	('YI-41', 1),
			('KI-41', 2);

INSERT INTO Student (surname, name, fathername, group_id, join_year, birth_year, home_address)
	VALUES 	('Nikiforov', 'Mykyta', 'Andriyovych', 1, 2014, 1997, 'Bilhorod-Dnistrovskyi, Yevreiskaya St.'),
			('Pushkin', 'Pavlo', 'Artemovych', 1, 2014, 1995, 'Kharkiv'),
			('Syvak', 'Artem', 'Volodymyrovych', 2, 2014, 1997, 'Lisky');
            
INSERT INTO Teacher (surname, name, fathername)
	VALUES 	('Romaka', 'Volodymyr', 'Volodymyrovych'),
			('Galembo', 'Vadym', 'Adolfovych'),
            ('Piskozub', 'Andrian', 'Zbignevych');
            
INSERT INTO Course (name, teacher_id)
	VALUES 	('Information Security Management', 1),
			('Super Computers', 2),
            ('Network Security', 3);
            
INSERT INTO Module_Result (module_number, student_id, course_id, grade)
	VALUES	(1, 1, 1, 44),
			(1, 1, 3, 96),
            (1, 3, 1, 85),
            (1, 3, 2, 100), 
            (1, 2, 1, 50),
            (2, 1, 2, 78);
	
INSERT INTO Semester_Result (semester_number, student_id, course_id, grade, control_type)
	VALUES 	(1, 1, 1, 56, 'test'),
			(1, 1, 3, 60, 'exam'),
            (1, 3, 2, 100, 'exam'),
            (1, 3, 1, 95, 'exam');
            
INSERT INTO Scholarship (student_id, amount)
	VALUES	(1, 720.0),
			(3, 880.0);
            
SELECT Student.surname, Student.name, Course.name, Teacher.surname AS Teacher_Name, grade FROM Student
		JOIN Semester_Result ON student_id=id
        JOIN Course ON Course.id=course_id
        JOIN Teacher ON Teacher.id = teacher_id;
