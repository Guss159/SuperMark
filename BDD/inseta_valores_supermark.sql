USE supermark; 


-- id,password
-- hope_1works
-- 2,46@54GASdama
-- 3,jabon_43Pinos
-- 4,micontraseñitaA43
-- 5,mundito541MAS
-- 6,boquita$1P

 select * from usuario;

INSERT INTO usuario (id, email, sexo, nombre1, nombre2, apellido1, apellido2, dni, celular, telefono_fijo, 
fecha_nacimiento, username, pasword, created_at, modified_at, last_login) VALUES

(1, 'admin@sudo.com',  NULL, 'Mr admin', NULL, 'robot',NULL, NULL, NULL, NULL, '2000-01-01',
'admin', 
'$argon2i$v=19$m=65536,t=18,p=1$ZfLWN95aq71AQA/jFdNu9g$pJgSiXELP97OAYJsy9VaHqLt18Z+LMSKfl3Il0peqlY', 
 '2022-02-22 00:00:00', NULL, '2022-02-22 00:00:00' ),
 
(2, 'bodimeadeiron@java.com',  NULL, 'Carolan', 'Phillipe', 'Aikett', 'Footer', '17517393', '768-817-3700', '8389291', 
'1965-09-30', 'pfooter0', 
'$argon2i$v=19$m=65536,t=18,p=1$oUGkRyzxyibMNvUve+wgww$twbAbyGQaOui+kaGg/TvTT5k3uwhsT4cOYVg2/DWXqU', 
'2021-12-28 08:50:00', NULL, '2022-02-04 23:18:08'),

(3, 'lvanlewen1@google.com',  'M', 'Evy', 'Lela', 'Muggleton', NULL, '34325111', NULL, NULL, 
'1986-11-10', 'lgowrie1',
 'hash:$argon2i$v=19$m=65536,t=18,p=1$b0JTgRr1NDwf18kYUfK+nw$DVJJvx/nI9Kdb6d91OBsFzAdFPQughbXlUWhqcwq4GQ',
 '2022-01-09 05:18:10', NULL, '2022-02-15 13:11:49'),

(NULL,'elcubit12@google.com','OTRO','Celestyn', 'Franky', 'Haddow', NULL, '49766492', '953-228-1583', NULL, 
'1991-01-20', 'fhadenton2', 
'hash:$argon2i$v=19$m=65536,t=18,p=1$txWyM1SYjyuJnBmhKdm4JA$iKQVhC9sEbdKiRpF48mUqczrEMmds8CiE95+l8rM3n8', 
'2021-01-28 06:55:40', NULL, '2021-08-24 16:58:52'),

(NULL,'burne3@hotmail.com','F','Maurine', NULL, 'Katzmann', 'Boldock', NULL, '225-400-0433', NULL, 
'1977-09-06', 'aboldock3', 
'hash:$argon2i$v=19$m=65536,t=18,p=1$5vrMSS49BgMwWzf+OeJUiQ$GCOsWq7q7YZE7D8+d+LOVxu06oOWlZmGuI4dNdcZwvc',  
'2021-08-31 15:53:50', NULL, '2021-12-03 18:12:49'),

(NULL,'malcomx@hexun.com','F','Phip', 'Wileen', 'D''Elia', 'Abramof', '42665910', '232-684-8388', NULL, 
'2001-11-07', 'wabramof4', 
'hash:$argon2i$v=19$m=65536,t=18,p=1$Qb+hngLuuAxfyZU2wEOirw$06xQd5pTY2cV9p5vqlN1ywZXqSchzVynQWkDEDW2Xto',  
'2021-08-05 02:21:19', NULL, '2022-02-14 00:53:21');

INSERT INTO producto(id, nombre, precio, created_at, modified_at) VALUES
(1, 'leche La serenisima',  200.50, '2022-07-09 12:00:00', NULL),
(2, 'yogurt La serenisima',  230.50, '2022-07-09 12:00:00', NULL),
(3, 'queso 500g Sancor',  650.90, '2022-07-09 12:00:00', NULL),
(4, 'Molida Vaca 1kg', 1671.00, '2022-07-09 12:00:00', NULL),
(5, 'Pollo entero  1kg',  190.00, '2022-07-09 12:00:00', NULL),
(6, 'Copos de Maiz BuenasMañanas', 500.00, '2022-07-09 12:00:00', NULL),
(7, 'Detergente MasBrillo 25cc', 100.00, '2022-07-09 12:00:00', NULL),
(8, 'Lavandia LosAndes 1L', 370.00, '2022-07-09 12:00:00', NULL);

-- producto_inventario id = PK&FK
INSERT INTO  producto_inventario( id, cantidad, created_at, modified_at ) VALUES
(1, 198, '2022-07-09 12:00:00', NULL),
(2, 151, '2022-07-09 12:00:00', NULL),
(3, 500, '2022-07-09 12:00:00', NULL),
(4, 300, '2022-07-09 12:00:00', NULL),
(5, 299, '2022-07-09 12:00:00', NULL),
(6, 1, '2022-07-09 12:00:00', NULL),
(7, 10, '2022-07-09 12:00:00', NULL),
(8, 0, '2022-07-09 12:00:00', NULL);

INSERT INTO rol(id, role_name) VALUES
(3,'Super User'),
(2, 'Cliente regular'),
(1, 'Cliente basico');

INSERT INTO user_role(usuario_id, role_id ) VALUES
(1, 3), (2, 2),
(3, 1), (4, 1), 
(5, 1), (6, 1);

INSERT INTO descuento_producto(id, producto_id, role_id, porcentage_descuento,created_at, modified_at) VALUE 
(1, 7 ,2 , .50, '2022-07-09 12:00:00', NULL);
-- update total
INSERT INTO orden(id, total, usuario_id, created_at, modified_at) VALUES
(1, 0.0, 2, '2022-02-04 23:18:08','2022-02-04 23:18:08'),
(2, 0.0, 3, '2022-02-15 13:11:49', '2022-05-04 21:00:00');
-- update monto
INSERT INTO pago_detalle(id,id_orden, monto, created_at) VALUES
(1,2, 0.0, '2022-02-15 13:11:49'),
(2,1, 0.0, '2022-02-04 23:18:08'),
(3,2, 0.0, '2022-05-04 21:00:00');
-- update total
INSERT INTO orden_item(id, id_orden, id_producto, cantidad, total) VALUES
(NULL, 1, 7, 10, 0.0),
(NULL, 1, 6, 1, 0.0),
(NULL, 1, 5, 4, 0.0),
(NULL, 2, 8, 1, 0.0),
(NULL, 2, 3, 10, 0.0);
