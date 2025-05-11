INSERT INTO course (name, description, teacher, max_capacity, num_of_students, level, start_date, end_date, active, image_url)
VALUES 
('Inglés A1 Inicial', 'Curso introductorio para principiantes', 'Laura Martínez', 20, 0, 'A1', '2025-06-01', '2025-07-01', true, 'https://upload.wikimedia.org/wikipedia/commons/thumb/0/0a/Flag_of_Jamaica.svg/1200px-Flag_of_Jamaica.svg.png'),

('Inglés A2 Conversación', 'Refuerza tus habilidades básicas', 'Carlos López', 25, 0, 'A2', '2025-06-10', '2025-07-10', true, 'https://upload.wikimedia.org/wikipedia/commons/thumb/d/d9/Flag_of_Canada_%28Pantone%29.svg/960px-Flag_of_Canada_%28Pantone%29.svg.png'),

('Inglés B1 Intermedio', 'Mejora tu fluidez en inglés', 'Ana Rodríguez', 30, 0, 'B1', '2025-07-01', '2025-08-01', true, 'https://upload.wikimedia.org/wikipedia/commons/thumb/4/45/Flag_of_Ireland.svg/1200px-Flag_of_Ireland.svg.png');

INSERT INTO student (name, surname, email, age, registration_date, active, course_id, photo_path)
VALUES 
('Laura', 'Navarro', 'laura.navarro@example.com', 20, '2025-05-10', true, NULL, 'defaultPicture.jpg'),
('Tomás', 'Vega', 'tomas.vega@example.com', 22, '2025-05-09', true, NULL, 'defaultPicture.jpg'),
('Natalia', 'Herrera', 'natalia.herrera@example.com', 21, '2025-05-08', true, NULL, 'defaultPicture.jpg'),
('Hugo', 'Silva', 'hugo.silva@example.com', 23, '2025-05-07', true, NULL, 'defaultPicture.jpg'),
('Paula', 'Mendoza', 'paula.mendoza@example.com', 19, '2025-05-06', true, NULL, 'defaultPicture.jpg'),
('Javier', 'Ortega', 'javier.ortega@example.com', 24, '2025-05-05', true, NULL, 'defaultPicture.jpg'),
('Isabel', 'Cortés', 'isabel.cortes@example.com', 22, '2025-05-04', true, NULL, 'defaultPicture.jpg'),
('Francisco', 'Reyes', 'francisco.reyes@example.com', 20, '2025-05-03', true, NULL, 'defaultPicture.jpg'),
('Camila', 'Luna', 'camila.luna@example.com', 21, '2025-05-02', true, NULL, 'defaultPicture.jpg'),
('Mateo', 'Morales', 'mateo.morales@example.com', 23, '2025-05-01', true, NULL, 'defaultPicture.jpg'),
('Daniela', 'Paredes', 'daniela.paredes@example.com', 20, '2025-04-30', true, NULL, 'defaultPicture.jpg'),
('Santiago', 'Ibáñez', 'santiago.ibanez@example.com', 22, '2025-04-29', true, NULL, 'defaultPicture.jpg'),
('Marina', 'Campos', 'marina.campos@example.com', 21, '2025-04-28', true, NULL, 'defaultPicture.jpg'),
('Gabriel', 'Nieves', 'gabriel.nieves@example.com', 23, '2025-04-27', true, NULL, 'defaultPicture.jpg'),
('Emma', 'Valencia', 'emma.valencia@example.com', 19, '2025-04-26', true, NULL, 'defaultPicture.jpg'),
('Iván', 'León', 'ivan.leon@example.com', 24, '2025-04-25', true, NULL, 'defaultPicture.jpg'),
('Clara', 'Salas', 'clara.salas@example.com', 22, '2025-04-24', true, NULL, 'defaultPicture.jpg'),
('Martín', 'Acosta', 'martin.acosta@example.com', 20, '2025-04-23', true, NULL, 'defaultPicture.jpg'),
('Alicia', 'Peña', 'alicia.pena@example.com', 21, '2025-04-22', true, NULL, 'defaultPicture.jpg'),
('Pablo', 'Cano', 'pablo.cano@example.com', 23, '2025-04-21', true, NULL, 'defaultPicture.jpg');