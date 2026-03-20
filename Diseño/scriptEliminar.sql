BEGIN;

-- Tablas con referencias N:M o dependientes
DROP TABLE IF EXISTS Pedir              CASCADE;
DROP TABLE IF EXISTS Valorar            CASCADE;
DROP TABLE IF EXISTS Anunciar           CASCADE;
DROP TABLE IF EXISTS TrabajarProyeccion CASCADE;
DROP TABLE IF EXISTS TrabajarLimpieza   CASCADE;
DROP TABLE IF EXISTS Butaca             CASCADE;
DROP TABLE IF EXISTS Reserva            CASCADE;

-- Tablas intermedias y de sesión/equipo
DROP TABLE IF EXISTS Sesion CASCADE;
DROP TABLE IF EXISTS Equipo CASCADE;

-- Tablas de catálogo
DROP TABLE IF EXISTS Comida   CASCADE;
DROP TABLE IF EXISTS Anuncio  CASCADE;
DROP TABLE IF EXISTS Pelicula CASCADE;

-- Tablas de usuarios y trabajadores
DROP TABLE IF EXISTS Usuario             CASCADE;
DROP TABLE IF EXISTS TrabajadorProyeccion CASCADE;
DROP TABLE IF EXISTS TrabajadorLimpieza   CASCADE;

-- Tabla principal de salas
DROP TABLE IF EXISTS Sala CASCADE;

COMMIT;

