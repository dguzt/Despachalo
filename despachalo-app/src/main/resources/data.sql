-- COMPANY
INSERT INTO empresa(razon_social, ruc) VALUES ('Distribuciones Despáchalo', '77001100233');

-- DISTRIBUTION CENTERS
INSERT INTO centro_distribucion(direccion, eliminado, ubi_latitud, ubi_longitud, nombre) VALUES ('Av. Gerónimo de Cabrera 470, Ica, Ica', false, -14.07559496080894, -75.72322936945595, 'Ica Central');
INSERT INTO zona_almacenamiento(capacidad_disponible, centro_distribucion_id, descripcion, capacidad_total) VALUES (20, 1, 'Zona sur S-01', 20);
INSERT INTO zona_almacenamiento(capacidad_disponible, centro_distribucion_id, descripcion, capacidad_total) VALUES (20, 1, 'Zona sur S-02', 20);
INSERT INTO zona_almacenamiento(capacidad_disponible, centro_distribucion_id, descripcion, capacidad_total) VALUES (20, 1, 'Zona este E-01', 20);
INSERT INTO zona_almacenamiento(capacidad_disponible, centro_distribucion_id, descripcion, capacidad_total) VALUES (20, 1, 'Zona este E-02', 20);

-- ROLES
INSERT INTO rol(fecha_creado, nombre, fecha_actualizado) VALUES (timezone('utc', now()), 'ADMIN', null);
INSERT INTO rol(fecha_creado, nombre, fecha_actualizado) VALUES (timezone('utc', now()), 'ANALISTA', null);

-- USERS
INSERT INTO usuario(activo, centro_id, fecha_creado, num_documento, tipo_documento, correo, contrasena_hash, es_admin, apellidos, nombres, rol_id, fecha_actualizado)
VALUES (true, 1, timezone('utc', now()), '70807585', 'DNI', 'jhair.guzman@despachalo.pe', '$2a$12$S2H/0.i7lq2d8uos7ko0WOCDtrWQtEwi9t3KLH2ySsyEYHp99UqZa', true, 'Guzmán', 'Jhair', 1, null);

INSERT INTO usuario(activo, centro_id, fecha_creado, num_documento, tipo_documento, correo, contrasena_hash, es_admin, apellidos, nombres, rol_id, fecha_actualizado)
VALUES (true, 1, timezone('utc', now()), '65607980', 'DNI', 'rocio.ventura@despachalo.pe', '$2a$12$S2H/0.i7lq2d8uos7ko0WOCDtrWQtEwi9t3KLH2ySsyEYHp99UqZa', false, 'Ventura', 'Rocío', 2, null);

INSERT INTO usuario(activo, centro_id, fecha_creado, num_documento, tipo_documento, correo, contrasena_hash, es_admin, apellidos, nombres, rol_id, fecha_actualizado)
VALUES (true, 1, timezone('utc', now()), '65607585', 'DNI', 'jorge.arias@despachalo.pe', '$2a$12$S2H/0.i7lq2d8uos7ko0WOCDtrWQtEwi9t3KLH2ySsyEYHp99UqZa', false, 'Arias', 'Jorge', 2, null);
