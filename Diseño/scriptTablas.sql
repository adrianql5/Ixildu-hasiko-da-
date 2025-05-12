-- 1.TrabajadorLimpieza
CREATE TABLE TrabajadorLimpieza (
  dni      VARCHAR(9)   PRIMARY KEY,
  nombre   VARCHAR(25)  NOT NULL,
  ap1      VARCHAR(50)  NOT NULL,
  ap2      VARCHAR(50),
  cargo    VARCHAR(50)  NOT NULL,
  sueldo   NUMERIC(10,2) NOT NULL DEFAULT 1000.00
);

-- 2.TrabajadorProyeccion
CREATE TABLE TrabajadorProyeccion (
  dni      VARCHAR(9)   PRIMARY KEY,
  nombre   VARCHAR(25)  NOT NULL,
  ap1      VARCHAR(50)  NOT NULL,
  ap2      VARCHAR(50),
  cargo    VARCHAR(50)  NOT NULL,
  sueldo   NUMERIC(10,2) NOT NULL DEFAULT 1000.00
);

-- 3.Sala
CREATE SEQUENCE seq_sala_id_sala;

CREATE TABLE Sala (
  idSala INTEGER DEFAULT nextval('seq_sala_id_sala') NOT NULL PRIMARY KEY,
  nombre VARCHAR(50) NOT NULL
);

CREATE OR REPLACE FUNCTION controla_secuencias_sala() RETURNS trigger AS $$
DECLARE
  temp_val INTEGER; -- Variable temporal para almacenar el valor actual de la secuencia.
BEGIN
  CASE TG_OP -- Verifica la operación que activó el trigger (INSERT, UPDATE o DELETE).
    WHEN 'INSERT' THEN
      -- Crea una nueva secuencia específica para la sala recién insertada.
      EXECUTE 'CREATE SEQUENCE seq_dependientes_sala_' || CAST(NEW.idSala AS TEXT);
      RETURN NEW; -- Retorna el nuevo registro.
    WHEN 'UPDATE' THEN
      -- Si el idSala cambia, se transfiere el valor de la secuencia antigua a la nueva.
      IF OLD.idSala <> NEW.idSala THEN
        temp_val := nextval('seq_dependientes_sala_' || CAST(OLD.idSala AS TEXT)); -- Obtiene el siguiente valor de la secuencia antigua.
        EXECUTE 'DROP SEQUENCE seq_dependientes_sala_' || CAST(OLD.idSala AS TEXT); -- Elimina la secuencia antigua.
        EXECUTE 'CREATE SEQUENCE seq_dependientes_sala_' || CAST(NEW.idSala AS TEXT) || ' START ' || CAST(temp_val AS TEXT); -- Crea una nueva secuencia con el valor transferido.
      END IF;
      RETURN NEW; -- Retorna el registro actualizado.
    WHEN 'DELETE' THEN
      -- Elimina la secuencia asociada a la sala que se está eliminando.
      EXECUTE 'DROP SEQUENCE seq_dependientes_sala_' || CAST(OLD.idSala AS TEXT);
      RETURN OLD; -- Retorna el registro eliminado.
    ELSE
      RETURN NULL; -- En caso de otras operaciones, no realiza ninguna acción.
  END CASE;
END;
$$ LANGUAGE plpgsql; -- Define la función en el lenguaje PL/pgSQL.

CREATE TRIGGER afterSala
AFTER INSERT OR UPDATE OR DELETE ON Sala -- Se activa después de las operaciones INSERT, UPDATE o DELETE en la tabla Sala.
FOR EACH ROW -- Se ejecuta una vez por cada fila afectada.
EXECUTE FUNCTION controla_secuencias_sala(); -- Llama a la función definida anteriormente.


-- 4.TrabajarLimpieza (N:M Trabajador–Sala)
CREATE TABLE TrabajarLimpieza (
  dni      VARCHAR(9)   NOT NULL,
  idSala   INTEGER      NOT NULL,
  PRIMARY KEY (dni, idSala),
  FOREIGN KEY (dni)    REFERENCES TrabajadorLimpieza(dni)
                      ON DELETE CASCADE
                      ON UPDATE CASCADE,
  FOREIGN KEY (idSala) REFERENCES Sala(idSala)
                      ON DELETE RESTRICT
                      ON UPDATE CASCADE
);

-- 5.TrabajarProyeccion (N:M Trabajador–Sala)
CREATE TABLE TrabajarProyeccion (
  dni      VARCHAR(9)   NOT NULL,
  idSala   INTEGER      NOT NULL,
  PRIMARY KEY (dni, idSala),
  FOREIGN KEY (dni)    REFERENCES TrabajadorProyeccion(dni)
                      ON DELETE CASCADE
                      ON UPDATE CASCADE,
  FOREIGN KEY (idSala) REFERENCES Sala(idSala)
                      ON DELETE RESTRICT
                      ON UPDATE CASCADE
);

-- 6.Equipo
CREATE SEQUENCE seq_equipo_id_equipo;

CREATE TABLE Equipo (
  idEquipo        INTEGER       PRIMARY KEY DEFAULT  nextval('seq_equipo_id_equipo'),
  idSala          INTEGER       NOT NULL,
  nombre          VARCHAR(50)   NOT NULL,
  tipo            VARCHAR(50)   NOT NULL,
  precio          NUMERIC(14,2) NOT NULL,
  marca           VARCHAR(30)   NOT NULL,
  modelo          VARCHAR(50)   NOT NULL,
  fechaAdquisicion DATE         NOT NULL,
  FOREIGN KEY (idSala) REFERENCES Sala(idSala)
                      ON DELETE RESTRICT
                      ON UPDATE CASCADE
);

CREATE OR REPLACE FUNCTION controla_secuencias_equipo() RETURNS trigger AS $$
DECLARE
  ejemplar INTEGER; -- Variable para almacenar el valor actual de la secuencia.
BEGIN
  CASE TG_OP -- Verifica la operación que activó el trigger (INSERT, UPDATE o DELETE).
    WHEN 'INSERT' THEN
      -- Crea una nueva secuencia específica para el equipo recién insertado.
      EXECUTE 'CREATE SEQUENCE seq_dependientes_equipo_' || CAST(NEW.idEquipo AS TEXT);
      RETURN NEW; -- Retorna el nuevo registro.
    WHEN 'UPDATE' THEN
      -- Si el idEquipo cambia, transfiere el valor de la secuencia antigua a la nueva.
      IF OLD.idEquipo <> NEW.idEquipo THEN
        ejemplar := nextval('seq_dependientes_equipo_' || CAST(OLD.idEquipo AS TEXT)); -- Obtiene el siguiente valor de la secuencia antigua.
        EXECUTE 'DROP SEQUENCE seq_dependientes_equipo_' || CAST(OLD.idEquipo AS TEXT); -- Elimina la secuencia antigua.
        EXECUTE 'CREATE SEQUENCE seq_dependientes_equipo_' || CAST(NEW.idEquipo AS TEXT) || ' START ' || CAST(ejemplar AS TEXT); -- Crea una nueva secuencia con el valor transferido.
      END IF;
      RETURN NEW; -- Retorna el registro actualizado.
    WHEN 'DELETE' THEN
      -- Elimina la secuencia asociada al equipo que se está eliminando.
      EXECUTE 'DROP SEQUENCE seq_dependientes_equipo_' || CAST(OLD.idEquipo AS TEXT);
      RETURN OLD; -- Retorna el registro eliminado.
    ELSE
      RETURN NULL; -- En caso de otras operaciones, no realiza ninguna acción.
  END CASE;
END;
$$ LANGUAGE plpgsql; -- Define la función en el lenguaje PL/pgSQL.

CREATE TRIGGER afterEquipo
AFTER INSERT OR UPDATE OR DELETE ON Equipo -- Se activa después de las operaciones INSERT, UPDATE o DELETE en la tabla Equipo.
FOR EACH ROW -- Se ejecuta una vez por cada fila afectada.
EXECUTE FUNCTION controla_secuencias_equipo(); -- Llama a la función definida anteriormente.


-- 7.Película
CREATE TABLE Pelicula (
  titulo          VARCHAR(50)   PRIMARY KEY,
  duracion        TIME          NOT NULL,
  genero          VARCHAR(50),
  sinopsis        VARCHAR(1000),
  clasificacion   VARCHAR(25)   NOT NULL,
  idioma          VARCHAR(50)   NOT NULL DEFAULT 'Vasco',
  fechaEstreno    DATE          NOT NULL,
  duracionTrailer TIME          NOT NULL
);

-- 8.Sesión
CREATE SEQUENCE seq_sesion_id_sesion;

CREATE TABLE Sesion (
  idSesion   INTEGER       PRIMARY KEY DEFAULT  nextval('seq_sesion_id_sesion'),
  idSala     INTEGER       NOT NULL,
  titulo     VARCHAR(50)   NOT NULL,
  fechaSesion DATE         NOT NULL,
  horaInicio TIME          NOT NULL,
  precio     NUMERIC(10,2) NOT NULL DEFAULT 7.50,
  FOREIGN KEY (idSala)  REFERENCES Sala(idSala)
                        ON DELETE RESTRICT
                        ON UPDATE CASCADE,
  FOREIGN KEY (titulo)  REFERENCES Pelicula(titulo)
                        ON DELETE RESTRICT
                        ON UPDATE CASCADE
);

CREATE OR REPLACE FUNCTION controla_secuencias_sesion() RETURNS trigger AS $$
DECLARE
  ejemplar INTEGER; -- Variable para almacenar el valor actual de la secuencia.
BEGIN
  CASE TG_OP -- Verifica la operación que activó el trigger (INSERT, UPDATE o DELETE).
    WHEN 'INSERT' THEN
      -- Crea una nueva secuencia específica para la sesión recién insertada.
      EXECUTE 'CREATE SEQUENCE seq_dependientes_sesion_' || CAST(NEW.idSesion AS TEXT);
      RETURN NEW; -- Retorna el nuevo registro.
    WHEN 'UPDATE' THEN
      -- Si el idSesion cambia, transfiere el valor de la secuencia antigua a la nueva.
      IF OLD.idSesion <> NEW.idSesion THEN
        ejemplar := nextval('seq_dependientes_sesion_' || CAST(OLD.idSesion AS TEXT)); -- Obtiene el siguiente valor de la secuencia antigua.
        EXECUTE 'DROP SEQUENCE seq_dependientes_sesion_' || CAST(OLD.idSesion AS TEXT); -- Elimina la secuencia antigua.
        EXECUTE 'CREATE SEQUENCE seq_dependientes_sesion_' || CAST(NEW.idSesion AS TEXT) || ' START ' || CAST(ejemplar AS TEXT); -- Crea una nueva secuencia con el valor transferido.
      END IF;
      RETURN NEW; -- Retorna el registro actualizado.
    WHEN 'DELETE' THEN
      -- Elimina la secuencia asociada a la sesión que se está eliminando.
      EXECUTE 'DROP SEQUENCE seq_dependientes_sesion_' || CAST(OLD.idSesion AS TEXT);
      RETURN OLD; -- Retorna el registro eliminado.
    ELSE
      RETURN NULL; -- En caso de otras operaciones, no realiza ninguna acción.
  END CASE;
END;
$$ LANGUAGE plpgsql; -- Define la función en el lenguaje PL/pgSQL.

CREATE TRIGGER afterSesion
AFTER INSERT OR UPDATE OR DELETE ON Sesion -- Se activa después de las operaciones INSERT, UPDATE o DELETE en la tabla Sesion.
FOR EACH ROW -- Se ejecuta una vez por cada fila afectada.
EXECUTE FUNCTION controla_secuencias_sesion(); -- Llama a la función definida anteriormente.



-- 9.Anuncio
CREATE SEQUENCE seq_anuncio_id_anuncio;

CREATE TABLE Anuncio (
  idAnuncio   INTEGER     PRIMARY KEY DEFAULT  nextval('seq_anuncio_id_anuncio'),
  tematica    VARCHAR(50) NOT NULL,
  duracion    TIME        NOT NULL
);

CREATE OR REPLACE FUNCTION controla_secuencias_anuncio() RETURNS trigger AS $$
DECLARE
  ejemplar INTEGER; -- Variable para almacenar el valor actual de la secuencia.
BEGIN
  CASE TG_OP -- Verifica la operación que activó el trigger (INSERT, UPDATE o DELETE).
    WHEN 'INSERT' THEN
      -- Crea una nueva secuencia específica para el anuncio recién insertado.
      EXECUTE 'CREATE SEQUENCE seq_dependientes_anuncio_' || CAST(NEW.idAnuncio AS TEXT);
      RETURN NEW; -- Retorna el nuevo registro.
    WHEN 'UPDATE' THEN
      -- Si el idAnuncio cambia, transfiere el valor de la secuencia antigua a la nueva.
      IF OLD.idAnuncio <> NEW.idAnuncio THEN
        ejemplar := nextval('seq_dependientes_anuncio_' || CAST(OLD.idAnuncio AS TEXT)); -- Obtiene el siguiente valor de la secuencia antigua.
        EXECUTE 'DROP SEQUENCE seq_dependientes_anuncio_' || CAST(OLD.idAnuncio AS TEXT); -- Elimina la secuencia antigua.
        EXECUTE 'CREATE SEQUENCE seq_dependientes_anuncio_' || CAST(NEW.idAnuncio AS TEXT) || ' START ' || CAST(ejemplar AS TEXT); -- Crea una nueva secuencia con el valor transferido.
      END IF;
      RETURN NEW; -- Retorna el registro actualizado.
    WHEN 'DELETE' THEN
      -- Elimina la secuencia asociada al anuncio que se está eliminando.
      EXECUTE 'DROP SEQUENCE seq_dependientes_anuncio_' || CAST(OLD.idAnuncio AS TEXT);
      RETURN OLD; -- Retorna el registro eliminado.
    ELSE
      RETURN NULL; -- En caso de otras operaciones, no realiza ninguna acción.
  END CASE;
END;
$$ LANGUAGE plpgsql; -- Define la función en el lenguaje PL/pgSQL.

CREATE TRIGGER afterAnuncio
AFTER INSERT OR UPDATE OR DELETE ON Anuncio -- Se activa después de las operaciones INSERT, UPDATE o DELETE en la tabla Anuncio.
FOR EACH ROW -- Se ejecuta una vez por cada fila afectada.
EXECUTE FUNCTION controla_secuencias_anuncio(); -- Llama a la función definida anteriormente.

-- 10.Anunciar (N:M Sesión–Anuncio)
CREATE TABLE Anunciar (
  idSesion   INTEGER NOT NULL,
  idAnuncio  INTEGER NOT NULL,
  PRIMARY KEY (idSesion, idAnuncio),
  FOREIGN KEY (idSesion)  REFERENCES Sesion(idSesion)
                          ON DELETE CASCADE
                          ON UPDATE CASCADE,
  FOREIGN KEY (idAnuncio) REFERENCES Anuncio(idAnuncio)
                          ON DELETE CASCADE
                          ON UPDATE CASCADE
);

-- 11.Usuario
CREATE TABLE Usuario (
  idUsuario      VARCHAR(50)  PRIMARY KEY,
  nombre         VARCHAR(50)  NOT NULL,
  ap1            VARCHAR(50)  NOT NULL,
  ap2            VARCHAR(50),
  email          VARCHAR(50)  NOT NULL,
  contraseña     VARCHAR(100)  NOT NULL,
  tipoUsuario    VARCHAR(50)  NOT NULL DEFAULT 'Normal',
  fechaNacimiento DATE        NOT NULL
);

-- 12.Reserva
CREATE SEQUENCE seq_reserva_id_reserva;

CREATE TABLE Reserva (
  idReserva    INTEGER       PRIMARY KEY DEFAULT  nextval('seq_reserva_id_reserva'),
  idUsuario    VARCHAR(50)   NOT NULL,
  idSesion     INTEGER       NOT NULL,
  fechaReserva DATE          NOT NULL DEFAULT CURRENT_DATE,
  horaReserva  TIME          NOT NULL,
  FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario)
                        ON DELETE RESTRICT
                        ON UPDATE CASCADE,
  FOREIGN KEY (idSesion)  REFERENCES Sesion(idSesion)
                        ON DELETE RESTRICT
                        ON UPDATE CASCADE
);

CREATE OR REPLACE FUNCTION controla_secuencias_reserva() RETURNS trigger AS $$
DECLARE
  ejemplar INTEGER; -- Variable para almacenar el valor actual de la secuencia.
BEGIN
  CASE TG_OP -- Verifica la operación que activó el trigger (INSERT, UPDATE o DELETE).
    WHEN 'INSERT' THEN
      -- Crea una nueva secuencia específica para la reserva recién insertada.
      EXECUTE 'CREATE SEQUENCE seq_dependientes_reserva_' || CAST(NEW.idReserva AS TEXT);
      RETURN NEW; -- Retorna el nuevo registro.
    WHEN 'UPDATE' THEN
      -- Si el idReserva cambia, transfiere el valor de la secuencia antigua a la nueva.
      IF OLD.idReserva <> NEW.idReserva THEN
        ejemplar := nextval('seq_dependientes_reserva_' || CAST(OLD.idReserva AS TEXT)); -- Obtiene el siguiente valor de la secuencia antigua.
        EXECUTE 'DROP SEQUENCE seq_dependientes_reserva_' || CAST(OLD.idReserva AS TEXT); -- Elimina la secuencia antigua.
        EXECUTE 'CREATE SEQUENCE seq_dependientes_reserva_' || CAST(NEW.idReserva AS TEXT) || ' START ' || CAST(ejemplar AS TEXT); -- Crea una nueva secuencia con el valor transferido.
      END IF;
      RETURN NEW; -- Retorna el registro actualizado.
    WHEN 'DELETE' THEN
      -- Elimina la secuencia asociada a la reserva que se está eliminando.
      EXECUTE 'DROP SEQUENCE seq_dependientes_reserva_' || CAST(OLD.idReserva AS TEXT);
      RETURN OLD; -- Retorna el registro eliminado.
    ELSE
      RETURN NULL; -- En caso de otras operaciones, no realiza ninguna acción.
  END CASE;
END;
$$ LANGUAGE plpgsql; -- Define la función en el lenguaje PL/pgSQL.

CREATE TRIGGER afterReserva
AFTER INSERT OR UPDATE OR DELETE ON Reserva -- Se activa después de las operaciones INSERT, UPDATE o DELETE en la tabla Reserva.
FOR EACH ROW -- Se ejecuta una vez por cada fila afectada.
EXECUTE FUNCTION controla_secuencias_reserva(); -- Llama a la función definida anteriormente.

-- 13.Butaca
CREATE SEQUENCE seq_butaca_id_butaca;

CREATE TABLE Butaca (
  idButaca   INTEGER    NOT NULL DEFAULT  nextval('seq_butaca_id_butaca'),
  idSala     INTEGER    NOT NULL,
  idReserva  INTEGER,
  tipo       VARCHAR(50) NOT NULL DEFAULT 'Normal',
  PRIMARY KEY (idButaca, idSala),
  FOREIGN KEY (idSala)     REFERENCES Sala(idSala)
                            ON DELETE CASCADE
                            ON UPDATE CASCADE,
  FOREIGN KEY (idReserva)  REFERENCES Reserva(idReserva)
                            ON DELETE SET NULL
                            ON UPDATE CASCADE
);

CREATE OR REPLACE FUNCTION controla_secuencias_butaca() RETURNS trigger AS $$
DECLARE
  ejemplar INTEGER; -- Variable para almacenar el valor actual de la secuencia.
BEGIN
  CASE TG_OP -- Verifica la operación que activó el trigger (INSERT, UPDATE o DELETE).
    WHEN 'INSERT' THEN
      -- Crea una nueva secuencia específica para la butaca recién insertada.
      EXECUTE 'CREATE SEQUENCE seq_dependientes_butaca_' || CAST(NEW.idButaca AS TEXT);
      RETURN NEW; -- Retorna el nuevo registro.
    WHEN 'UPDATE' THEN
      -- Si el idButaca cambia, transfiere el valor de la secuencia antigua a la nueva.
      IF OLD.idButaca <> NEW.idButaca THEN
        ejemplar := nextval('seq_dependientes_butaca_' || CAST(OLD.idButaca AS TEXT)); -- Obtiene el siguiente valor de la secuencia antigua.
        EXECUTE 'DROP SEQUENCE seq_dependientes_butaca_' || CAST(OLD.idButaca AS TEXT); -- Elimina la secuencia antigua.
        EXECUTE 'CREATE SEQUENCE seq_dependientes_butaca_' || CAST(NEW.idButaca AS TEXT) || ' START ' || CAST(ejemplar AS TEXT); -- Crea una nueva secuencia con el valor transferido.
      END IF;
      RETURN NEW; -- Retorna el registro actualizado.
    WHEN 'DELETE' THEN
      -- Elimina la secuencia asociada a la butaca que se está eliminando.
      EXECUTE 'DROP SEQUENCE seq_dependientes_butaca_' || CAST(OLD.idButaca AS TEXT);
      RETURN OLD; -- Retorna el registro eliminado.
    ELSE
      RETURN NULL; -- En caso de otras operaciones, no realiza ninguna acción.
  END CASE;
END;
$$ LANGUAGE plpgsql; -- Define la función en el lenguaje PL/pgSQL.

CREATE TRIGGER afterButaca
AFTER INSERT OR UPDATE OR DELETE ON Butaca -- Se activa después de las operaciones INSERT, UPDATE o DELETE en la tabla Butaca.
FOR EACH ROW -- Se ejecuta una vez por cada fila afectada.
EXECUTE FUNCTION controla_secuencias_butaca(); -- Llama a la función definida anteriormente.


-- 14.Valorar
CREATE TABLE Valorar (
  fecha       DATE        NOT NULL,
  titulo      VARCHAR(50) NOT NULL,
  idUsuario   VARCHAR(50),
  opinion     TEXT        NOT NULL,
  puntuacion  INTEGER     NOT NULL,
  PRIMARY KEY (fecha, titulo, idUsuario),
  FOREIGN KEY (titulo)    REFERENCES Pelicula(titulo)
                          ON DELETE CASCADE
                          ON UPDATE CASCADE,
  FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario)
                          ON DELETE SET NULL
                          ON UPDATE CASCADE
);

-- 15.Comida
CREATE SEQUENCE seq_comida_id_comida;

CREATE TABLE Comida (
  idComida        INTEGER       PRIMARY KEY DEFAULT  nextval('seq_comida_id_comida'),
  nombre          VARCHAR(50)   NOT NULL,
  descripcion     VARCHAR(50),
  precio          NUMERIC(10,2) NOT NULL,
  tamaño          VARCHAR(20),
  stockDisponible INTEGER
);

CREATE OR REPLACE FUNCTION controla_secuencias_comida() RETURNS trigger AS $$
DECLARE
  ejemplar INTEGER; -- Variable para almacenar el valor actual de la secuencia.
BEGIN
  CASE TG_OP -- Verifica la operación que activó el trigger (INSERT, UPDATE o DELETE).
    WHEN 'INSERT' THEN
      -- Crea una nueva secuencia específica para la comida recién insertada.
      EXECUTE 'CREATE SEQUENCE seq_dependientes_comida_' || CAST(NEW.idComida AS TEXT);
      RETURN NEW; -- Retorna el nuevo registro.
    WHEN 'UPDATE' THEN
      -- Si el idComida cambia, transfiere el valor de la secuencia antigua a la nueva.
      IF OLD.idComida <> NEW.idComida THEN
        ejemplar := nextval('seq_dependientes_comida_' || CAST(OLD.idComida AS TEXT)); -- Obtiene el siguiente valor de la secuencia antigua.
        EXECUTE 'DROP SEQUENCE seq_dependientes_comida_' || CAST(OLD.idComida AS TEXT); -- Elimina la secuencia antigua.
        EXECUTE 'CREATE SEQUENCE seq_dependientes_comida_' || CAST(NEW.idComida AS TEXT) || ' START ' || CAST(ejemplar AS TEXT); -- Crea una nueva secuencia con el valor transferido.
      END IF;
      RETURN NEW; -- Retorna el registro actualizado.
    WHEN 'DELETE' THEN
      -- Elimina la secuencia asociada a la comida que se está eliminando.
      EXECUTE 'DROP SEQUENCE seq_dependientes_comida_' || CAST(OLD.idComida AS TEXT);
      RETURN OLD; -- Retorna el registro eliminado.
    ELSE
      RETURN NULL; -- En caso de otras operaciones, no realiza ninguna acción.
  END CASE;
END;
$$ LANGUAGE plpgsql; -- Define la función en el lenguaje PL/pgSQL.

CREATE TRIGGER afterComida
AFTER INSERT OR UPDATE OR DELETE ON Comida -- Se activa después de las operaciones INSERT, UPDATE o DELETE en la tabla Comida.
FOR EACH ROW -- Se ejecuta una vez por cada fila afectada.
EXECUTE FUNCTION controla_secuencias_comida(); -- Llama a la función definida anteriormente.

-- 16.Pedir
CREATE TABLE Pedir (
  fecha      DATE        NOT NULL,
  hora       TIME        NOT NULL,
  idComida   INTEGER     NOT NULL,
  idUsuario  VARCHAR(50) NOT NULL,
  cantidad   INTEGER     NOT NULL,
  entregado  BOOLEAN     NOT NULL DEFAULT FALSE,
  PRIMARY KEY (fecha, hora, idComida, idUsuario),
  FOREIGN KEY (idComida)  REFERENCES Comida(idComida)
                          ON DELETE RESTRICT
                          ON UPDATE CASCADE,
  FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario)
                          ON DELETE RESTRICT
                          ON UPDATE CASCADE
);

