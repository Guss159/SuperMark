DROP DATABASE supermark;
CREATE DATABASE supermark CHARACTER SET utf8mb4;
USE supermark;


CREATE TABLE usuario(
id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
nombre1 VARCHAR(30) NOT NULL,
nombre2 VARCHAR(30),
apellido1 VARCHAR(30) NOT NULL,
apellido2 VARCHAR(30),
dni VARCHAR(9),
celular VARCHAR(12),
telefono_fijo VARCHAR(12),
fecha_nacimiento DATE NOT NULL,
sexo ENUM('M','F','OTRO'),

username VARCHAR(18) UNIQUE NOT NULL,
-- to do salted pasword
pasword VARCHAR(228) NOT NULL,
email VARCHAR(321) NOT NULL, -- standard
last_login TIMESTAMP,
created_at TIMESTAMP NOT NULL,
modified_at TIMESTAMP	
);


CREATE TABLE producto(
id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(30),
precio FLOAT,
created_at TIMESTAMP NOT NULL,
modified_at TIMESTAMP
);

CREATE TABLE producto_inventario(
id INT UNSIGNED PRIMARY KEY,
cantidad INT UNSIGNED NOT NULL,
created_at TIMESTAMP NOT NULL,
modified_at TIMESTAMP,
FOREIGN KEY (id) REFERENCES producto(id) -- forcing 1to0or1 relationship ya que PK es UNIQUE
);

CREATE TABLE rol(
id TINYINT UNSIGNED NOT NULL PRIMARY KEY,
role_name VARCHAR(30)
);

CREATE TABLE user_role(
usuario_id INT UNSIGNED NOT NULL,
role_id TINYINT UNSIGNED NOT NULL,
FOREIGN KEY (usuario_id) REFERENCES usuario(id),
FOREIGN KEY (role_id) REFERENCES rol(id),
CONSTRAINT pk_user_role PRIMARY KEY (usuario_id, role_id)
);

CREATE TABLE descuento_producto(
id INT UNSIGNED AUTO_INCREMENT  PRIMARY KEY,
producto_id INT UNSIGNED NOT NULL,
role_id TINYINT UNSIGNED NOT NULL,
porcentage_descuento DECIMAL(3,3) NOT NULL,
created_at TIMESTAMP NOT NULL,
modified_at TIMESTAMP,
FOREIGN KEY (producto_id) REFERENCES producto(id),
FOREIGN KEY (role_id) REFERENCES rol(id)
);

CREATE TABLE orden(
id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
total FLOAT NOT NULL,
usuario_id INT UNSIGNED NOT NULL,
created_at TIMESTAMP NOT NULL,
modified_at TIMESTAMP,
FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

CREATE TABLE pago_detalle(
id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
monto FLOAT NOT NULL,
id_orden INT UNSIGNED ,
created_at TIMESTAMP NOT NULL,
FOREIGN KEY (id_orden) REFERENCES orden(id)
);

CREATE TABLE orden_item(
id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
id_orden INT UNSIGNED NOT NULL,
id_producto INT UNSIGNED NOT NULL,
cantidad INT UNSIGNED NOT NULL,
total FLOAT,
FOREIGN KEY (id_orden) REFERENCES orden(id),
FOREIGN KEY (id_producto) REFERENCES producto(id)
);

-- Las tablas 'session_carrito' y 'session_item' van a manejar las interacciones en vivo 
-- una vez que finalice la interacción del usuario esta información se mueve a las tablas 'orden' y 'orden_item'
-- esto permite capturar la información de la interacción de los clientes con el carrito antes de confirmar una compra
-- tambien mejora el rendimiento de estas al mover datos procesados 'orden' y 'orden_item'

CREATE TABLE session_carrito(
id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
total FLOAT NOT NULL,
usuario_id INT UNSIGNED NOT NULL,
created_at TIMESTAMP NOT NULL,
modified_at TIMESTAMP,
FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE session_item(
id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
session_id INT UNSIGNED NOT NULL,
producto_id INT UNSIGNED NOT NULL,
cantidad INT UNSIGNED NOT NULL,
total FLOAT NOT NULL,
FOREIGN KEY (session_id) REFERENCES session_carrito(id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (producto_id) REFERENCES producto(id)
);
