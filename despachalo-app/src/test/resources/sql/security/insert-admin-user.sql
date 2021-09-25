INSERT INTO rol(id, nombre) VALUES (1, 'ADMIN');

-- user: admin@despachalo.pe
-- pass: admin
INSERT INTO usuario(
                    activo,
                    num_documento,
                    tipo_documento,
                    correo,
                    contrasena_hash,
                    es_admin,
                    apellidos,
                    nombres,
                    rol_id
                )
                VALUES (
                        true,
                        '70266809',
                        'DNI',
                        'admin@despachalo.pe',
                        '$2a$12$S2H/0.i7lq2d8uos7ko0WOCDtrWQtEwi9t3KLH2ySsyEYHp99UqZa',
                        true,
                        'Test',
                        'Admin',
                        1
                       )
