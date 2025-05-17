

INSERT INTO course (name, description, teacher, max_capacity, level, start_date, end_date, active, image_url, is_full) VALUES ('Inglés A1', 'Curso introductorio para principiantes', 'Profesora Smith', 20, 'A1', '2025-06-01', '2025-12-01', true, 'https://upload.wikimedia.org/wikipedia/commons/thumb/a/a4/Flag_of_the_United_States.svg/2560px-Flag_of_the_United_States.svg.png', false);
INSERT INTO course (name, description, teacher, max_capacity, level, start_date, end_date, active, image_url, is_full) VALUES ('Inglés A2', 'Mejora tu nivel básico de inglés', 'Profesor Johnson', 25, 'A2', '2025-07-01', '2025-12-01', true, 'https://upload.wikimedia.org/wikipedia/commons/thumb/a/a5/Flag_of_the_United_Kingdom_%281-2%29.svg/1200px-Flag_of_the_United_Kingdom_%281-2%29.svg.png', false);
INSERT INTO course (name, description, teacher, max_capacity, level, start_date, end_date, active, image_url, is_full) VALUES ('Inglés B1', 'Curso intermedio para reforzar gramática y vocabulario', 'Profesora Davis', 30, 'B1', '2025-05-15', '2025-11-15', true, 'https://upload.wikimedia.org/wikipedia/commons/thumb/8/88/Flag_of_Australia_%28converted%29.svg/1200px-Flag_of_Australia_%28converted%29.svg.png', false);
INSERT INTO course (name, description, teacher, max_capacity, level, start_date, end_date, active, image_url, is_full) VALUES ('Inglés B2', 'Avanza en la fluidez y comprensión oral', 'Profesor Brown', 35, 'B2', '2025-06-10', '2025-12-10', true, 'https://upload.wikimedia.org/wikipedia/commons/thumb/4/45/Flag_of_Ireland.svg/1200px-Flag_of_Ireland.svg.png', false);
INSERT INTO course (name, description, teacher, max_capacity, level, start_date, end_date, active, image_url, is_full) VALUES ('Inglés C1', 'Desarrollo avanzado de habilidades comunicativas', 'Profesora Wilson', 40, 'C1', '2025-07-05', '2025-12-15', true, 'https://upload.wikimedia.org/wikipedia/commons/thumb/d/d9/Flag_of_Canada_%28Pantone%29.svg/960px-Flag_of_Canada_%28Pantone%29.svg.png', false);
INSERT INTO course (name, description, teacher, max_capacity, level, start_date, end_date, active, image_url, is_full) VALUES ('Inglés C2', 'Dominio completo del idioma y perfeccionamiento', 'Profesor Martinez', 45, 'C2', '2025-08-01', '2025-12-20', true, 'https://upload.wikimedia.org/wikipedia/commons/thumb/0/0a/Flag_of_Jamaica.svg/1200px-Flag_of_Jamaica.svg.png', false);

INSERT INTO student (name, surname, email, age, registration_date, active, course_id, photo_path, average) VALUES ('Adriana', 'Lima', 'Adriana.Lima@example.com', 20, '2025-05-10', true, 6, 'defaultPicture.jpg', 0.0);
INSERT INTO student (name, surname, email, age, registration_date, active, course_id, photo_path, average) VALUES ('Lola', 'Torreblanca', 'lolaTorre.blanca@example.com', 20, '2025-05-10', true, 6, 'defaultPicture.jpg', 0.0);
INSERT INTO student (name, surname, email, age, registration_date, active, course_id, photo_path, average) VALUES ('Tomás', 'Vega', 'tomas.vega@example.com', 22, '2025-05-09', true, 6, 'defaultPicture.jpg', 0.0);
INSERT INTO student (name, surname, email, age, registration_date, active, course_id, photo_path, average) VALUES ('Natalia', 'Herrera', 'natalia.herrera@example.com', 21, '2025-05-08', true, 6, 'defaultPicture.jpg', 0.0);
INSERT INTO student (name, surname, email, age, registration_date, active, course_id, photo_path, average) VALUES ('Laura', 'Navarro', 'laura.navarro@example.com', 20, '2025-05-10', true, 6, 'defaultPicture.jpg', 0.0);
INSERT INTO student (name, surname, email, age, registration_date, active, course_id, photo_path, average) VALUES ('Isabel', 'Ramírez', 'isabel.ramirez@example.com', 20, '2025-05-04', true, 6, 'defaultPicture.jpg', 0.0);
INSERT INTO student (name, surname, email, age, registration_date, active, course_id, photo_path, average) VALUES ('Carmen', 'Delgado', 'carmen.delgado@example.com', 20, '2025-05-03', true, 6, 'defaultPicture.jpg', 0.0);
INSERT INTO student (name, surname, email, age, registration_date, active, course_id, photo_path, average) VALUES ('Diego', 'Fuentes', 'diego.fuentes@example.com', 22, '2025-05-02', true, 6, 'defaultPicture.jpg', 0.0);
INSERT INTO student (name, surname, email, age, registration_date, active, course_id, photo_path, average) VALUES ('Patricia', 'Serrano', 'patricia.serrano@example.com', 21, '2025-05-01', true, 6, 'defaultPicture.jpg', 0.0);
INSERT INTO student (name, surname, email, age, registration_date, active, course_id, photo_path, average) VALUES ('Raquel', 'Cano', 'raquel.cano@example.com', 20, '2025-04-30', true, 6, 'defaultPicture.jpg', 0.0);
INSERT INTO student (name, surname, email, age, registration_date, active, course_id, photo_path, average) VALUES ('Mario', 'López', 'mario.lopez@example.com', 23, '2025-04-29', true, 1, 'defaultPicture.jpg', 0.0);
INSERT INTO student (name, surname, email, age, registration_date, active, course_id, photo_path, average) VALUES ('Lucía', 'Martínez', 'lucia.martinez@example.com', 22, '2025-04-28', true, 2, 'defaultPicture.jpg', 0.0);
INSERT INTO student (name, surname, email, age, registration_date, active, course_id, photo_path, average) VALUES ('Jorge', 'Castro', 'jorge.castro@example.com', 21, '2025-04-27', true, 3, 'defaultPicture.jpg', 0.0);
INSERT INTO student (name, surname, email, age, registration_date, active, course_id, photo_path, average) VALUES ('Elena', 'Ruiz', 'elena.ruiz@example.com', 20, '2025-04-26', true, 4, 'defaultPicture.jpg', 0.0);


INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (1, 'SPEAKING', 8.5);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (1, 'WRITING', 7.2);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (1, 'LISTENING', 4);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (1, 'UOE', 6.8);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (1, 'READING', 7.5);

INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (2, 'SPEAKING', 6);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (2, 'WRITING', 8.1);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (2, 'LISTENING', 7.5);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (2, 'UOE', 8.0);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (2, 'READING', 7.2);

INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (3, 'SPEAKING', 6.5);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (3, 'WRITING', 3);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (3, 'LISTENING', 8.4);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (3, 'UOE', 6.2);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (3, 'READING', 7.8);

INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (4, 'SPEAKING', 8.5);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (4, 'WRITING', 7.2);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (4, 'LISTENING', 3);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (4, 'UOE', 6.8);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (4, 'READING', 7.5);

INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (5, 'SPEAKING', 7.9);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (5, 'WRITING', 8.1);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (5, 'LISTENING', 7.5);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (5, 'UOE', 8.0);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (5, 'READING', 7.2);

INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (6, 'SPEAKING', 6.5);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (6, 'WRITING', 7.9);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (6, 'LISTENING', 8.4);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (6, 'UOE', 6.2);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (6, 'READING', 7.8);

INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (7, 'SPEAKING', 8.5);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (7, 'WRITING', 7.2);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (7, 'LISTENING', 9.0);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (7, 'UOE', 6.8);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (7, 'READING', 7.5);

INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (8, 'SPEAKING', 4);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (8, 'WRITING', 10);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (8, 'LISTENING', 4);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (8, 'UOE', 3);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (8, 'READING', 3);

INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (9, 'SPEAKING', 6.5);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (9, 'WRITING', 7.9);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (9, 'LISTENING', 8.4);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (9, 'UOE', 6.2);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (9, 'READING', 7.8);

INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (10, 'SPEAKING', 3);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (10, 'WRITING', 7.2);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (10, 'LISTENING', 3);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (10, 'UOE', 5.5);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (10, 'READING', 7.5);

INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (11, 'SPEAKING', 7.9);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (11, 'WRITING', 8.1);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (11, 'LISTENING', 5);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (11, 'UOE', 8.0);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (11, 'READING', 7.2);

INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (12, 'SPEAKING', 7);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (12, 'WRITING', 7.9);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (12, 'LISTENING', 8.4);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (12, 'UOE', 6.2);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (12, 'READING', 7.8);

INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (13, 'SPEAKING', 7.9);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (13, 'WRITING', 5.3);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (13, 'LISTENING', 7.5);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (13, 'UOE', 9);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (13, 'READING', 7.2);

INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (14, 'SPEAKING', 1.6);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (14, 'WRITING', 7.9);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (14, 'LISTENING', 8.4);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (14, 'UOE', 4.1);
INSERT INTO student_grades (STUDENT_ID, GRADES_KEY, GRADE) VALUES (14, 'READING', 7.3);
