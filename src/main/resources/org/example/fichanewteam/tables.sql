CREATE TABLE IF NOT EXISTS jugador (
    id IDENTITY NOTNULL PRIMARY KEY,
    nombre VARCHAR NOT NULL,
    apellidos VARCHAR NOT NULL,
    fechaNacimiento DATE NOT NULL,
    fechaIncorporacion DATE NOT NULL,
    salario NUMERIC NOT NULL,
    pais VARCHAR NOT NULL,
    rol VARCHAR NOT NULL,
    posicion VARCHAR NOT NULL,
    dorsal INTEGER NOT NULL,
    altura DOUBLE NOT NULL,
    peso DOUBLE NOT NULL,
    goles INTEGER NOT NULL,
    partidosJugados INTEGER NOT NULL,
    ruta_imagen VARCHAR(255) DEFAULT 'images/default_profile.png'
)

CREATE TABLE IF NOT EXISTS entrenador (
    id IDENTITY NOTNULL PRIMARY KEY,
    nombre VARCHAR NOT NULL,
    apellidos VARCHAR NOT NULL,
    fechaNacimiento DATE NOT NULL,
    fechaIncorporacion DATE NOT NULL,
    salario NUMERIC NOT NULL,
    pais VARCHAR NOT NULL,
    rol VARCHAR NOT NULL,
    especialidad VARCHAR NOT NULL,
    ruta_imagen VARCHAR(255) DEFAULT 'images/default_profile.png'
)

CREATE TABLE IF NOT EXISTS plantilla (
    id IDENTITY NOTNULL PRIMARY KEY,
    nombre VARCHAR NOT NULL,
    apellidos VARCHAR NOT NULL,
    fechaNacimiento DATE NOT NULL,
    fechaIncorporacion DATE NOT NULL,
    salario NUMERIC NOT NULL,
    pais VARCHAR NOT NULL,
    rol VARCHAR NOT NULL,
    ruta_imagen VARCHAR(255) DEFAULT 'images/default_profile.png'
)
