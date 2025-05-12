-- 1. TrabajadorLimpieza
INSERT INTO TrabajadorLimpieza (dni, nombre, ap1, ap2, cargo, sueldo) VALUES
('11111111A', 'Oihan', 'Sancet', 'Tirapu', 'Limpiador', 1250.00),
('22222222B', 'Iker', 'Muniain', 'Goñi', 'Supervisor', 1450.00);

-- 2. TrabajadorProyeccion
INSERT INTO TrabajadorProyeccion (dni, nombre, ap1, ap2, cargo, sueldo) VALUES
('33333333C', 'Nicholas', 'Williams', 'Arthuer', 'Proyeccionista', 1350.00),
('44444444D', 'Iñaki', 'Williams', 'Arthuer', 'Técnico', 1300.00),
('55555555E', 'Unai', 'Simón', 'Mendibil', 'Técnico', 1320.00),
('66666666F', 'Iñigo', 'Martínez', 'Berridi', 'Proyeccionista', 1370.00),
('77777777G', 'Daniel', 'Vivian', 'Moreno', 'Técnico', 1340.00);


-- Insertar 3 salas
INSERT INTO Sala (nombre) VALUES
('Sala Euskalduna'),   -- idSala = 1
('Sala Guggenheim'),   -- idSala = 2
('Sala Arriaga'),      -- idSala = 3
('Sala Zabaltegi'),    -- idSala = 4
('Sala BBK');          -- idSala = 5


-- 4. TrabajarLimpieza
INSERT INTO TrabajarLimpieza (dni, idSala) VALUES
('11111111A', 1),
('22222222B', 2);

-- 5. TrabajarProyeccion
INSERT INTO TrabajarProyeccion (dni, idSala) VALUES
('33333333C', 1),
('44444444D', 1),
('55555555E', 2),
('66666666F', 2),
('77777777G', 2);

-- 6. Equipo
INSERT INTO Equipo (idSala, nombre, tipo, precio, marca, modelo, fechaAdquisicion) VALUES
(1, 'Proyector EuskalHD', 'Proyector', 4800.00, 'Ikus', 'EH100', '2023-02-12'),
(1, 'Altavoces BasqueSound', 'Sonido', 2900.00, 'Basoa', 'BS-7.1', '2022-09-10'),
(1, 'Pantalla SuperVision', 'Pantalla', 1500.00, 'Vision', 'SV-150', '2022-05-20'),
(1, 'Mesa de mezcla AudioMaster', 'Sonido', 800.00, 'AudioTech', 'AMX-2', '2023-01-18'),
    (2, 'Proyector CineMax', 'Proyector', 5000.00, 'CineTech', 'CX-200', '2023-03-05'),
(2, 'Altavoces SoundWave', 'Sonido', 3500.00, 'SoundCorp', 'SW-10', '2022-08-15'),
(2, 'Pantalla Panorama', 'Pantalla', 1400.00, 'Vista', 'P-100', '2022-06-01'),
(2, 'Reproductor BluRay Ultra', 'Reproductor', 1200.00, 'BluRayPro', 'BR-4K', '2023-04-22'),
    (3, 'Proyector StarVision', 'Proyector', 4800.00, 'StarTech', 'SV-500', '2022-12-10'),
(3, 'Altavoces MegaSound', 'Sonido', 3200.00, 'MegaAudio', 'MS-5', '2023-01-30'),
(3, 'Pantalla CineView', 'Pantalla', 1600.00, 'CineView', 'CV-200', '2022-11-25'),
(3, 'Sistema de iluminación LuxMaster', 'Iluminación', 2500.00, 'LuxTech', 'LM-3', '2023-02-05'),
    (4, 'Proyector Zenith', 'Proyector', 5100.00, 'ZenTech', 'ZP-300', '2023-04-01'),
(4, 'Altavoces SuperAudio', 'Sonido', 3300.00, 'SuperSound', 'SA-15', '2022-10-20'),
(4, 'Pantalla UltraHD', 'Pantalla', 1700.00, 'UltraVision', 'UH-350', '2022-07-10'),
(4, 'Microfono Karaoke Pro', 'Sonido', 500.00, 'KaraokeMaster', 'KM-2', '2023-03-14'),
(5, 'Proyector Cinemax Plus', 'Proyector', 5500.00, 'Cinemax', 'CMP-1000', '2023-05-01'),
(5, 'Altavoces EliteSound', 'Sonido', 4000.00, 'EliteAudio', 'ES-12', '2022-12-05'),
(5, 'Pantalla MegaView', 'Pantalla', 1800.00, 'MegaVision', 'MV-400', '2022-04-18'),
(5, 'Sistema Dolby Atmos', 'Sonido', 7000.00, 'Dolby', 'DA-500', '2023-02-28');



-- 7. Pelicula (temática vasca)
INSERT INTO Pelicula (titulo, duracion, genero, sinopsis, clasificacion, idioma, fechaEstreno, duracionTrailer) VALUES
('Handia', '01:54:00', 'Drama histórico', 'Basada en hechos reales, narra la historia del gigante de Altzo y su relación con su hermano durante el siglo XIX.', 'Mayores de 12', 'Vasco', '2017-10-20', '00:01:30'),
('Loreak', '01:39:00', 'Drama', 'Tres mujeres conectadas por misteriosos ramos de flores que despiertan recuerdos y emociones del pasado.', 'Mayores de 12', 'Vasco', '2014-10-31', '00:01:45'),
('Érase una vez en Euskadi', '01:40:00', 'Drama', 'Un grupo de niños vive su infancia en el País Vasco durante los años 80, marcada por la violencia y la esperanza.', 'Mayores de 13', 'Español', '2021-10-29', '00:02:30'),
('Ocho Apellidos Vascos', '01:38:00', 'Comedia romántica', 'Un andaluz viaja al País Vasco para conquistar a una joven vasca, desencadenando una serie de malentendidos culturales.', 'Mayores de 7', 'Español', '2014-03-14', '00:01:45');



-- 8. Sesion
INSERT INTO Sesion (idSala, titulo, fechaSesion, horaInicio, precio) VALUES
(1, 'Handia', '2025-07-05', '17:00:00', 7.50),
(2, 'Loreak', '2025-08-10', '19:30:00', 8.00),
(3, 'Ocho Apellidos Vascos', '2025-07-12', '21:00:00', 8.50),
(1, 'Érase una vez en Euskadi', '2025-09-01', '16:45:00', 7.00),
(2, 'Handia', '2025-07-07', '18:15:00', 7.50),
(3, 'Loreak', '2025-07-15', '20:00:00', 8.00),
(1, 'Ocho Apellidos Vascos', '2025-08-20', '22:00:00', 8.50),
(2, 'Érase una vez en Euskadi', '2025-09-03', '17:30:00', 7.00),
(3, 'Handia', '2025-07-10', '15:00:00', 6.50),
(1, 'Loreak', '2025-07-18', '19:00:00', 7.80),
(2, 'Ocho Apellidos Vascos', '2025-08-25', '20:30:00', 8.50),
(3, 'Érase una vez en Euskadi', '2025-09-05', '14:00:00', 6.00),
(4, 'Handia', '2025-07-22', '18:00:00', 7.50),
(5, 'Loreak', '2025-08-02', '20:15:00', 8.00),
(4, 'Ocho Apellidos Vascos', '2025-08-18', '22:30:00', 8.50),
(5, 'Érase una vez en Euskadi', '2025-09-10', '16:00:00', 6.50),
(3, 'Handia', '2025-07-25', '14:30:00', 6.00),
(2, 'Loreak', '2025-08-08', '21:00:00', 8.20),
(1, 'Érase una vez en Euskadi', '2025-09-12', '17:45:00', 7.00),
(5, 'Ocho Apellidos Vascos', '2025-08-30', '19:00:00', 8.50),
(4, 'Loreak', '2025-07-29', '18:30:00', 7.80),
(3, 'Handia', '2025-08-05', '20:00:00', 7.20);


-- 9. Anuncio
INSERT INTO Anuncio (tematica, duracion) VALUES
('Sidra artesanal vasca', '00:00:50'),
('Fiesta de la Tamborrada en Donostia', '00:01:00'),
('Ruta del queso Idiazábal', '00:01:15'),
('Turismo rural en el Valle de Baztán', '00:01:30'),
('Pintxos tradicionales en San Sebastián', '00:01:20'),
('Cultura del euskera en las escuelas', '00:01:45'),
('Festival de Cine de San Sebastián', '00:01:10'),
('Surf en la playa de Zarautz', '00:01:25'),
('Carnavales de Tolosa', '00:01:35'),
('Semana Grande de Bilbao (Aste Nagusia)', '00:01:40'),
('Camino de Santiago por el País Vasco', '00:01:30'),
('Museo Guggenheim y arte contemporáneo vasco', '00:01:50'),
('Concurso de aizkolaris y deportes rurales', '00:01:05'),
('Promoción del tren de cremallera al monte Igueldo', '00:01:15'),
('Mercado medieval de Hondarribia', '00:01:25'),
('Día del euskera – Euskararen Eguna', '00:01:30'),
('Gastronomía vasca: bacalao al pil-pil', '00:01:10');



-- 10. Anunciar
INSERT INTO Anunciar (idSesion, idAnuncio) VALUES
(1, 1),
(2, 2),
(1, 3),
(1, 4),
(2, 5),
(3, 6),
(3, 7),
(4, 8),
(4, 9),
(5, 10),
(6, 11),
(6, 12),
(7, 13),
(8, 14),
(9, 15),
(10, 16),
(11, 17),
(12, 3),
(13, 4),
(14, 6),
(15, 7),
(16, 8),
(17, 9),
(18, 10),
(19, 11),
(20, 12),
(21, 13),
(22, 14),
(22, 15);


-- 11. Usuario (vascos conocidos)
INSERT INTO Usuario (idUsuario, nombre, ap1, ap2, email, contraseña, tipoUsuario, fechaNacimiento) VALUES
('u003', 'Oihan', 'Sancet', 'Tirapu', 'oihan@cinevasco.eus', '$2a$10$c3loUkzSxWdI9HZJZG078em8V0R3.CPVc7Sy1Ythlivk5lRLg4YMe', 'Normal', '2000-04-25'), --sancet2025
('mister', 'Ernesto', 'Valverde', 'Tejedor', 'ernesto@cinevasco.eus', '$2a$10$SoXgJQGNy2ThqyWM/E6Pd.FtohTtA1fgrL4rAQ20fwqACwDYm8GO.', 'Administrador', '1964-02-09'), --ernesto2025
('u004', 'Iker', 'Muniain', 'Goñi', 'iker@cinevasco.eus', '$2a$10$eGiPm7fZZtUEtANBi2lcPufyBDG/tDO1W0rO82xPpTrQgnX4qW67y', 'Normal', '1992-12-19'); --muniain2025

-- 12. Reserva
INSERT INTO Reserva (idUsuario, idSesion, fechaReserva, horaReserva) VALUES
('u004', 2, '2025-05-02', '18:45:00'),
('u003', 1, '2025-05-01', '16:15:00');



-- Insertar 19 butacas repartidas entre las salas
INSERT INTO Butaca (idSala, idReserva, tipo) VALUES
(1, NULL, 'VIP'),
(1, NULL, 'Normal'),
(1, NULL, 'Normal'),
(1, NULL, 'VIP'),
(1, NULL, 'VIP'),
(1, NULL, 'VIP'),
(1, NULL, 'VIP'),
(1, NULL, 'VIP'),
(1, NULL, 'Normal'),
(1, NULL, 'Normal'),
(1, NULL, 'Normal'),
(1, NULL, 'Normal'),
(1, NULL, 'Normal'),
(1, NULL, 'Normal'),
(1, NULL, 'Normal'),
(1, NULL, 'Normal'),
(1, NULL, 'Normal'),
(1, NULL, 'Normal'),
(1,NULL, 'Normal'),
(2, NULL, 'VIP'),
(2, NULL, 'Normal'),
(2, NULL, 'Normal'),
(2, NULL, 'VIP'),
(2, NULL, 'VIP'),
(2, NULL, 'VIP'),
(2, NULL, 'VIP'),
(2, NULL, 'VIP'),
(2, NULL, 'Normal'),
(2, NULL, 'Normal'),
(2, NULL, 'Normal'),
(2, NULL, 'Normal'),
(2, NULL, 'Normal'),
(2, NULL, 'Normal'),
(2, NULL, 'Normal'),
(2, NULL, 'Normal'),
(2, NULL, 'Normal'),
(2, NULL, 'Normal'),
(2,NULL, 'Normal'),
(3, NULL, 'VIP'),
(3, NULL, 'Normal'),
(3, NULL, 'Normal'),
(3, NULL, 'VIP'),
(3, NULL, 'VIP'),
(3, NULL, 'VIP'),
(3, NULL, 'VIP'),
(3, NULL, 'VIP'),
(3, NULL, 'Normal'),
(3, NULL, 'Normal'),
(3, NULL, 'Normal'),
(3, NULL, 'Normal'),
(3, NULL, 'Normal'),
(3, NULL, 'Normal'),
(3, NULL, 'Normal'),
(3, NULL, 'Normal'),
(3, NULL, 'Normal'),
(3, NULL, 'Normal'),
(3,NULL, 'Normal'),
(4, NULL, 'VIP'),
(4, NULL, 'Normal'),
(4, NULL, 'Normal'),
(4, NULL, 'VIP'),
(4, NULL, 'VIP'),
(4, NULL, 'VIP'),
(4, NULL, 'VIP'),
(4, NULL, 'VIP'),
(4, NULL, 'Normal'),
(4, NULL, 'Normal'),
(4, NULL, 'Normal'),
(4, NULL, 'Normal'),
(4, NULL, 'Normal'),
(4, NULL, 'Normal'),
(4, NULL, 'Normal'),
(4, NULL, 'Normal'),
(4, NULL, 'Normal'),
(4, NULL, 'Normal'),
(4,NULL, 'Normal'),
(5, NULL, 'VIP'),
(5, NULL, 'Normal'),
(5, NULL, 'Normal'),
(5, NULL, 'VIP'),
(5, NULL, 'VIP'),
(5, NULL, 'VIP'),
(5, NULL, 'VIP'),
(5, NULL, 'VIP'),
(5, NULL, 'Normal'),
(5, NULL, 'Normal'),
(5, NULL, 'Normal'),
(5, NULL, 'Normal'),
(5, NULL, 'Normal'),
(5, NULL, 'Normal'),
(5, NULL, 'Normal'),
(5, NULL, 'Normal'),
(5, NULL, 'Normal'),
(5, NULL, 'Normal'),
(5,NULL, 'Normal');



-- 14. Valorar
INSERT INTO Valorar (fecha, titulo, idUsuario, opinion, puntuacion) VALUES
('2025-05-01', 'Handia', 'u003', 'Una joya del cine vasco. Emocionante.', 9),
('2025-05-02', 'Loreak', 'u004', 'Profunda y delicada. Muy recomendable.', 8),
('2025-05-03', 'Érase una vez en Euskadi', 'u003', 'Un relato emotivo y conmovedor de la infancia vasca, muy bien logrado.', 7),
('2025-05-04', 'Ocho Apellidos Vascos', 'u004', 'Una comedia ligera pero divertida. Un gran enfoque cultural con toques cómicos.', 8),
('2025-05-05', 'Handia', 'u003', 'Impresionante trabajo en la ambientación y la narrativa. Un buen reflejo de la historia vasca.', 9),
('2025-05-06', 'Loreak', 'u004', 'Una película íntima que conecta profundamente con el espectador.', 7),
('2025-05-07', 'Érase una vez en Euskadi', 'u003', 'Excelente película que captura muy bien los conflictos de los 80 en el País Vasco.', 8),
('2025-05-08', 'Ocho Apellidos Vascos', 'u004', 'Una comedia perfecta para conocer la cultura vasca de forma divertida.', 6),
('2025-05-09', 'Handia', 'u003', 'Una historia impresionante sobre la relación entre dos hermanos. Realmente tocante.', 10),
('2025-05-10', 'Loreak', 'u004', 'Reflexiva, aunque algo lenta. Aun así, tiene una gran carga emocional.', 7),
('2025-05-11', 'Érase una vez en Euskadi', 'u003', 'Una historia que te hace pensar sobre la violencia y la esperanza en tiempos difíciles.', 8),
('2025-05-12', 'Ocho Apellidos Vascos', 'u004', 'Comedia típica, pero con un enfoque genuinamente vasco. Muy entretenida.', 7),
('2025-05-13', 'Handia', 'u003', 'Excelente película histórica con una gran producción. Muy recomendable.', 9),
('2025-05-14', 'Loreak', 'u004', 'Un drama reflexivo con un enfoque muy delicado sobre las relaciones personales.', 8),
('2025-05-15', 'Érase una vez en Euskadi', 'u003', 'Sensible y conmovedora, la película destaca por su realismo en los momentos difíciles.', 7),
('2025-05-16', 'Ocho Apellidos Vascos', 'u004', 'Muy divertida, con un buen toque de humor sobre la identidad vasca.', 8),
('2025-05-17', 'Handia', 'u003', 'Una película impresionante, especialmente por la interpretación y el guion.', 10),
('2025-05-18', 'Loreak', 'u004', 'Una narración potente que toca el alma, pero un ritmo algo lento.', 6),
('2025-05-19', 'Érase una vez en Euskadi', 'u003', 'Una película que sabe cómo retratar la infancia en tiempos de convulsión social.', 8),
('2025-05-20', 'Ocho Apellidos Vascos', 'u004', 'La película tiene un excelente manejo del humor y la crítica cultural. Muy divertida.', 7);


-- 15. Comida (vasca)
INSERT INTO Comida (nombre, descripcion, precio, tamaño, stockDisponible) VALUES
('Pintxo de tortilla', 'Tortilla de patatas con cebolla en pan', 2.50, 'Pequeño', 50),
('Gilda', 'Aceituna, guindilla y anchoa en un palillo', 1.80, 'Pequeño', 60),
('Txistorra', 'Txistorra en bocadillo pequeño', 3.00, 'Mediano', 40),
('Sidra vasca', 'Sidra natural Euskal Sagardoa', 2.20, 'Mediano', 70);
