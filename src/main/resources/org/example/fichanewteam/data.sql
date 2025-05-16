-- Insertar jugadores
INSERT INTO jugador (nombre, apellidos, fechaNacimiento, fechaIncorporacion, salario, pais, rol, posicion, dorsal, altura, peso, goles, partidosJugados)
VALUES
    ('Lionel', 'Messi', '1987-06-24', '2023-07-01', 5000000, 'Argentina', 'Jugador', 'Delantero', 10, 1.70, 72, 30, 100),
    ('Kylian', 'Mbappé', '1998-12-20', '2022-08-10', 6000000, 'Francia', 'Jugador', 'Delantero', 7, 1.78, 73, 25, 80);

-- Insertar entrenadores
INSERT INTO entrenador (nombre, apellidos, fechaNacimiento, fechaIncorporacion, salario, pais, rol, especialidad)
VALUES
    ('Pep', 'Guardiola', '1971-01-18', '2021-06-15', 7000000, 'España', 'Entrenador', 'Táctica'),
    ('Diego', 'Simeone', '1970-04-28', '2022-01-01', 6500000, 'Argentina', 'Entrenador', 'Defensiva');

-- Insertar en plantilla
INSERT INTO plantilla (nombre, apellidos, fechaNacimiento, fechaIncorporacion, salario, pais, rol)
VALUES
    ('Andrés', 'Iniesta', '1984-05-11', '2023-08-01', 4000000, 'España', 'Asistente'),
    ('Xavi', 'Hernández', '1980-01-25', '2023-09-10', 4500000, 'España', 'Analista');
