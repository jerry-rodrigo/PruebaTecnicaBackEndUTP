-- Crear la tabla "role"
CREATE TABLE role (
  id_role BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255)
);

-- Insertar datos en la tabla "Role"
INSERT INTO role(name) VALUES('USER');
INSERT INTO role(name) VALUES('ADMIN');