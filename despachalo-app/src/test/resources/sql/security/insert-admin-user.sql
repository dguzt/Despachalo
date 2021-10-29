INSERT INTO rol(id, nombre) VALUES (1, 'ADMIN');

INSERT INTO centro_distribucion(direccion, eliminado, ubi_latitud, ubi_longitud, nombre)
VALUES ('Av. Grau', false, 0.01, 0.01, 'Ica Central');

-- user: admin@despachalo.pe
-- pass: admin
INSERT INTO usuario(centro_id, activo, num_documento, tipo_documento, correo, contrasena_hash, es_admin, apellidos, nombres, rol_id)
VALUES (1, true, '70266809', 'DNI', 'admin@despachalo.pe', '$2a$12$S2H/0.i7lq2d8uos7ko0WOCDtrWQtEwi9t3KLH2ySsyEYHp99UqZa', true, 'Test', 'Admin', 1);
