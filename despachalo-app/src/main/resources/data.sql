-- COMPANY
INSERT INTO empresa(razon_social, ruc) VALUES ('Distribuciones Despáchalo', '77001100233');

-- DISTRIBUTION CENTERS
INSERT INTO centro_distribucion(direccion, eliminado, ubi_latitud, ubi_longitud, nombre) VALUES ('Av. Gerónimo de Cabrera 470', false, -14.07559496080894, -75.72322936945595, 'Urubamba');
INSERT INTO zona_almacenamiento(capacidad_disponible, centro_distribucion_id, descripcion, capacidad_total) VALUES (20, 1, 'Zona sur S-01', 20);
INSERT INTO zona_almacenamiento(capacidad_disponible, centro_distribucion_id, descripcion, capacidad_total) VALUES (20, 1, 'Zona sur S-02', 20);
INSERT INTO zona_almacenamiento(capacidad_disponible, centro_distribucion_id, descripcion, capacidad_total) VALUES (20, 1, 'Zona este E-01', 20);
INSERT INTO zona_almacenamiento(capacidad_disponible, centro_distribucion_id, descripcion, capacidad_total) VALUES (20, 1, 'Zona este E-02', 20);

-- ROLES
INSERT INTO rol(fecha_creado, nombre, fecha_actualizado) VALUES (now(), 'ADMIN', null);
INSERT INTO rol(fecha_creado, nombre, fecha_actualizado) VALUES (now(), 'ANALISTA', null);

-- USERS
INSERT INTO usuario(activo, centro_id, fecha_creado, num_documento, tipo_documento, correo, contrasena_hash, es_admin, apellidos, nombres, rol_id, fecha_actualizado)
VALUES (true, 1, now(), '70807585', 'DNI', 'jhair.guzman@despachalo.pe', '$2a$12$S2H/0.i7lq2d8uos7ko0WOCDtrWQtEwi9t3KLH2ySsyEYHp99UqZa', true, 'Guzmán', 'Jhair', 1, null);

INSERT INTO usuario(activo, centro_id, fecha_creado, num_documento, tipo_documento, correo, contrasena_hash, es_admin, apellidos, nombres, rol_id, fecha_actualizado)
VALUES (true, 1, now(), '65607980', 'DNI', 'rocio.ventura@despachalo.pe', '$2a$12$S2H/0.i7lq2d8uos7ko0WOCDtrWQtEwi9t3KLH2ySsyEYHp99UqZa', false, 'Rocío', 'Ventura', 2, null);

INSERT INTO usuario(activo, centro_id, fecha_creado, num_documento, tipo_documento, correo, contrasena_hash, es_admin, apellidos, nombres, rol_id, fecha_actualizado)
VALUES (true, 1, now(), '65607585', 'DNI', 'jorge.arias@despachalo.pe', '$2a$12$S2H/0.i7lq2d8uos7ko0WOCDtrWQtEwi9t3KLH2ySsyEYHp99UqZa', false, 'Jorge', 'Arias', 2, null);
------------------------------------------------------------------------------------------------------------------------
-- PRODUCTS
INSERT INTO producto(codigo, fecha_creado, descripcion, fecha_actualizado)
VALUES('PROD-011', now(), 'Camisa Puma', null);

INSERT INTO producto(codigo, fecha_creado, descripcion, fecha_actualizado)
VALUES('PROD-012', now(), 'Zapatillas Puma', null);

INSERT INTO producto(codigo, fecha_creado, descripcion, fecha_actualizado)
VALUES('PROD-013', now(), 'Polera Puma', null);

-- PRODUCT DETAILS
INSERT INTO detalle_producto(fecha_creado, producto_id, fecha_actualizado, volumen, peso)
VALUES (now(), 1, null, 20, 20);

INSERT INTO detalle_producto(fecha_creado, producto_id, fecha_actualizado, volumen, peso)
VALUES (now(), 2, null, 20, 20);

INSERT INTO detalle_producto(fecha_creado, producto_id, fecha_actualizado, volumen, peso)
VALUES (now(), 3, null, 20, 20);
------------------------------------------------------------------------------------------------------------------------
-- CLIENTS
-- CLIENT 1
INSERT INTO cliente(razon_social, codigo, fecha_creado, ruc, fecha_actualizado) VALUES ('Tiendas Calderón', 'CLI-121', now(), '11000000001', null);
INSERT INTO punto_destino(direccion, centro_dist_id, cliente_id, codigo, ubi_latitud, ubi_longitud) VALUES ('E,F, Parcona 11003', 1, 1, 'DEST-121-1', -14.053507168527837, -75.69183897375429);
INSERT INTO pedido(fecha_creado, despacho_id, tienda_id, fecha_actualizado, estado) VALUES (now(), null, 1, null, 'INCOMPLETO');
INSERT INTO linea_pedido(pedido_id, detalle_producto_id, cantidad_solicitada, cantidad_enviada, cantidad_enviar, cantidad_almacenada) VALUES (1, 1, 4, 0, 4, 0);

-- CLIENT 2
INSERT INTO cliente(razon_social, codigo, fecha_creado, ruc, fecha_actualizado) VALUES ('Tiendas Carrillo', 'CLI-122', now(), '22000000002', null);
INSERT INTO punto_destino(direccion, centro_dist_id, cliente_id, codigo, ubi_latitud, ubi_longitud) VALUES ('Garcilazo De La Vega, Ica 11003', 1, 2, 'DEST-122-1', -14.039284663853005, -75.69699020503809);
INSERT INTO pedido(fecha_creado, despacho_id, tienda_id, fecha_actualizado, estado) VALUES (now(), null, 2, null, 'INCOMPLETO');
INSERT INTO linea_pedido(pedido_id, detalle_producto_id, cantidad_solicitada, cantidad_enviada, cantidad_enviar, cantidad_almacenada) VALUES (2, 2, 5, 0, 5, 0);

-- CLIENT 3
INSERT INTO cliente(razon_social, codigo, fecha_creado, ruc, fecha_actualizado) VALUES ('Tiendas Marquez', 'CLI-123', now(), '33000000003', null);
INSERT INTO punto_destino(direccion, centro_dist_id, cliente_id, codigo, ubi_latitud, ubi_longitud) VALUES ('Jose C Mariategui, Ica 11002', 1, 3, 'DEST-123-1', -14.039487561804604, -75.73694805351519);
INSERT INTO pedido(fecha_creado, despacho_id, tienda_id, fecha_actualizado, estado) VALUES (now(), null, 3, null, 'INCOMPLETO');
INSERT INTO linea_pedido(pedido_id, detalle_producto_id, cantidad_solicitada, cantidad_enviada, cantidad_enviar, cantidad_almacenada) VALUES (3, 3, 2, 0, 2, 0);

-- CLIENT 4
INSERT INTO cliente(razon_social, codigo, fecha_creado, ruc, fecha_actualizado) VALUES ('Tiendas Lopez', 'CLI-124', now(), '44000000004', null);
INSERT INTO punto_destino(direccion, centro_dist_id, cliente_id, codigo, ubi_latitud, ubi_longitud) VALUES ('Av. Industrial, Ica 11002', 1, 4, 'DEST-124-1', -14.044330550599918, -75.74304216027761);
INSERT INTO pedido(fecha_creado, despacho_id, tienda_id, fecha_actualizado, estado) VALUES (now(), null, 4, null, 'INCOMPLETO');
INSERT INTO linea_pedido(pedido_id, detalle_producto_id, cantidad_solicitada, cantidad_enviada, cantidad_enviar, cantidad_almacenada) VALUES (4, 1, 3, 0, 3, 0);

-- CLIENT 5
INSERT INTO cliente(razon_social, codigo, fecha_creado, ruc, fecha_actualizado) VALUES ('Tiendas Boris', 'CLI-125', now(), '55000000005', null);
INSERT INTO punto_destino(direccion, centro_dist_id, cliente_id, codigo, ubi_latitud, ubi_longitud) VALUES ('El Parque, Ica 11004', 1, 5, 'DEST-125-1', -14.043898350482099, -75.74753287130622);
INSERT INTO pedido(fecha_creado, despacho_id, tienda_id, fecha_actualizado, estado) VALUES (now(), null, 5, null, 'INCOMPLETO');
INSERT INTO linea_pedido(pedido_id, detalle_producto_id, cantidad_solicitada, cantidad_enviada, cantidad_enviar, cantidad_almacenada) VALUES (5, 2, 5, 0, 5, 0);

-- CLIENT 6
INSERT INTO cliente(razon_social, codigo, fecha_creado, ruc, fecha_actualizado) VALUES ('Tiendas Méndez', 'CLI-126', now(), '66000000006', null);
INSERT INTO punto_destino(direccion, centro_dist_id, cliente_id, codigo, ubi_latitud, ubi_longitud) VALUES ('P R Palma 100, Ica 11004', 1, 6, 'DEST-126-1', -14.059085686900666, -75.75092802674715);
INSERT INTO pedido(fecha_creado, despacho_id, tienda_id, fecha_actualizado, estado) VALUES (now(), null, 6, null, 'INCOMPLETO');
INSERT INTO linea_pedido(pedido_id, detalle_producto_id, cantidad_solicitada, cantidad_enviada, cantidad_enviar, cantidad_almacenada) VALUES (6, 3, 6, 0, 6, 0);

-- CLIENT 7
INSERT INTO cliente(razon_social, codigo, fecha_creado, ruc, fecha_actualizado) VALUES ('Tiendas Lucas', 'CLI-127', now(), '77000000007', null);
INSERT INTO punto_destino(direccion, centro_dist_id, cliente_id, codigo, ubi_latitud, ubi_longitud) VALUES ('Av. La Victoria 925, Ica 11004', 1, 7, 'DEST-127-1', -14.066285260155095, -75.7500360933956);
INSERT INTO pedido(fecha_creado, despacho_id, tienda_id, fecha_actualizado, estado) VALUES (now(), null, 7, null, 'INCOMPLETO');
INSERT INTO linea_pedido(pedido_id, detalle_producto_id, cantidad_solicitada, cantidad_enviada, cantidad_enviar, cantidad_almacenada) VALUES (7, 1, 3, 0, 3, 0);

-- CLIENT 8
INSERT INTO cliente(razon_social, codigo, fecha_creado, ruc, fecha_actualizado) VALUES ('Tiendas Mendoza', 'CLI-128', now(), '88000000008', null);
INSERT INTO punto_destino(direccion, centro_dist_id, cliente_id, codigo, ubi_latitud, ubi_longitud) VALUES ('Prolongación Luis Geronimo de Cabrera, Ica 11001', 1, 8, 'DEST-128-1', -14.08791312747179, -75.72395538407577);
INSERT INTO pedido(fecha_creado, despacho_id, tienda_id, fecha_actualizado) VALUES (now(), null, 8, null);
INSERT INTO linea_pedido(pedido_id, detalle_producto_id, cantidad_solicitada, cantidad_enviada, cantidad_enviar, cantidad_almacenada) VALUES (8, 2, 4, 0, 4, 0);

-- CLIENT 9
INSERT INTO cliente(razon_social, codigo, fecha_creado, ruc, fecha_actualizado) VALUES ('Tiendas Molina', 'CLI-129', now(), '99000000009', null);
INSERT INTO punto_destino(direccion, centro_dist_id, cliente_id, codigo, ubi_latitud, ubi_longitud) VALUES ('Av. Nicolas de Rivera el Viejo 1104, Ica 11001', 1, 9, 'DEST-129-1', -14.081894770484471, -75.72379328724703);
INSERT INTO pedido(fecha_creado, despacho_id, tienda_id, fecha_actualizado, estado) VALUES (now(), null, 9, null, 'INCOMPLETO');
INSERT INTO linea_pedido(pedido_id, detalle_producto_id, cantidad_solicitada, cantidad_enviada, cantidad_enviar, cantidad_almacenada) VALUES (9, 3, 4, 0, 4, 0);
------------------------------------------------------------------------------------------------------------------------
-- ORIGIN POINT
INSERT INTO punto_origen(direccion, codigo, fecha_creado, fecha_actualizado)
VALUES ('Av. Nicolás de Piérola 189-403, Cercado de Lima 15491', 'ORIG-001', now(), null);

INSERT INTO punto_origen(direccion, codigo, fecha_creado, fecha_actualizado)
VALUES ('Av. Santísima Cruz, San Martín de Porres 15113', 'ORIG-002', now(), null);

-- COMMODITIES
-- COMMODITY 001
INSERT INTO mercaderia(hora_llegada, codigo, fecha_creado, estado, fecha_actualizado, placa_vehiculo, almacen_id, centro_id)
VALUES (now(), 'MERC-001', now(), 'POR LLEGAR', null, 'ABC-121', 1, 1);
INSERT INTO item_mercaderia(codigo, mercaderia_id, pedido_id, detalle_producto_id, orden_devolucion_id) VALUES ('001-2021-10-12-001', 1, 1, 1, null);
INSERT INTO item_mercaderia(codigo, mercaderia_id, pedido_id, detalle_producto_id, orden_devolucion_id) VALUES ('001-2021-10-12-002', 1, 1, 1, null);
INSERT INTO item_mercaderia(codigo, mercaderia_id, pedido_id, detalle_producto_id, orden_devolucion_id) VALUES ('001-2021-10-12-003', 1, 1, 1, null);
INSERT INTO item_mercaderia(codigo, mercaderia_id, pedido_id, detalle_producto_id, orden_devolucion_id) VALUES ('001-2021-10-12-004', 1, 1, 1, null);
INSERT INTO item_mercaderia(codigo, mercaderia_id, pedido_id, detalle_producto_id, orden_devolucion_id) VALUES ('001-2021-10-12-005', 1, 4, 1, null);
INSERT INTO item_mercaderia(codigo, mercaderia_id, pedido_id, detalle_producto_id, orden_devolucion_id) VALUES ('001-2021-10-12-006', 1, 4, 1, null);
INSERT INTO item_mercaderia(codigo, mercaderia_id, pedido_id, detalle_producto_id, orden_devolucion_id) VALUES ('001-2021-10-12-007', 1, 4, 1, null);
INSERT INTO item_mercaderia(codigo, mercaderia_id, pedido_id, detalle_producto_id, orden_devolucion_id) VALUES ('001-2021-10-12-008', 1, 7, 1, null);
INSERT INTO item_mercaderia(codigo, mercaderia_id, pedido_id, detalle_producto_id, orden_devolucion_id) VALUES ('001-2021-10-12-009', 1, 7, 1, null);
INSERT INTO item_mercaderia(codigo, mercaderia_id, pedido_id, detalle_producto_id, orden_devolucion_id) VALUES ('001-2021-10-12-010', 1, 7, 1, null);

-- COMMODITY 002
INSERT INTO mercaderia(hora_llegada, codigo, fecha_creado, estado, fecha_actualizado, placa_vehiculo, almacen_id, centro_id)
VALUES (now(), 'MERC-002', now(), 'POR LLEGAR', null, 'ABC-122', 1, 1);
INSERT INTO item_mercaderia(codigo, mercaderia_id, pedido_id, detalle_producto_id, orden_devolucion_id) VALUES ('002-2021-10-12-001', 2, 2, 2, null);
INSERT INTO item_mercaderia(codigo, mercaderia_id, pedido_id, detalle_producto_id, orden_devolucion_id) VALUES ('002-2021-10-12-002', 2, 2, 2, null);
INSERT INTO item_mercaderia(codigo, mercaderia_id, pedido_id, detalle_producto_id, orden_devolucion_id) VALUES ('002-2021-10-12-003', 2, 2, 2, null);
INSERT INTO item_mercaderia(codigo, mercaderia_id, pedido_id, detalle_producto_id, orden_devolucion_id) VALUES ('002-2021-10-12-004', 2, 2, 2, null);
INSERT INTO item_mercaderia(codigo, mercaderia_id, pedido_id, detalle_producto_id, orden_devolucion_id) VALUES ('002-2021-10-12-005', 2, 2, 2, null);
INSERT INTO item_mercaderia(codigo, mercaderia_id, pedido_id, detalle_producto_id, orden_devolucion_id) VALUES ('002-2021-10-12-006', 2, 5, 2, null);
INSERT INTO item_mercaderia(codigo, mercaderia_id, pedido_id, detalle_producto_id, orden_devolucion_id) VALUES ('002-2021-10-12-007', 2, 5, 2, null);
INSERT INTO item_mercaderia(codigo, mercaderia_id, pedido_id, detalle_producto_id, orden_devolucion_id) VALUES ('002-2021-10-12-008', 2, 5, 2, null);
INSERT INTO item_mercaderia(codigo, mercaderia_id, pedido_id, detalle_producto_id, orden_devolucion_id) VALUES ('002-2021-10-12-009', 2, 5, 2, null);
INSERT INTO item_mercaderia(codigo, mercaderia_id, pedido_id, detalle_producto_id, orden_devolucion_id) VALUES ('002-2021-10-12-010', 2, 5, 2, null);
INSERT INTO item_mercaderia(codigo, mercaderia_id, pedido_id, detalle_producto_id, orden_devolucion_id) VALUES ('002-2021-10-12-010', 2, 8, 2, null);
INSERT INTO item_mercaderia(codigo, mercaderia_id, pedido_id, detalle_producto_id, orden_devolucion_id) VALUES ('002-2021-10-12-010', 2, 8, 2, null);
INSERT INTO item_mercaderia(codigo, mercaderia_id, pedido_id, detalle_producto_id, orden_devolucion_id) VALUES ('002-2021-10-12-010', 2, 8, 2, null);
INSERT INTO item_mercaderia(codigo, mercaderia_id, pedido_id, detalle_producto_id, orden_devolucion_id) VALUES ('002-2021-10-12-010', 2, 8, 2, null);

-- COMMODITY 003
INSERT INTO mercaderia(hora_llegada, codigo, fecha_creado, estado, fecha_actualizado, placa_vehiculo, almacen_id, centro_id)
VALUES (now(), 'MERC-003', now(), 'POR LLEGAR', null, 'ABC-123', 2, 1);
INSERT INTO item_mercaderia(codigo, mercaderia_id, pedido_id, detalle_producto_id, orden_devolucion_id) VALUES ('003-2021-10-12-001', 3, 3, 3, null);
INSERT INTO item_mercaderia(codigo, mercaderia_id, pedido_id, detalle_producto_id, orden_devolucion_id) VALUES ('003-2021-10-12-002', 3, 3, 3, null);
INSERT INTO item_mercaderia(codigo, mercaderia_id, pedido_id, detalle_producto_id, orden_devolucion_id) VALUES ('003-2021-10-12-003', 3, 6, 3, null);
INSERT INTO item_mercaderia(codigo, mercaderia_id, pedido_id, detalle_producto_id, orden_devolucion_id) VALUES ('003-2021-10-12-004', 3, 6, 3, null);
INSERT INTO item_mercaderia(codigo, mercaderia_id, pedido_id, detalle_producto_id, orden_devolucion_id) VALUES ('003-2021-10-12-005', 3, 6, 3, null);
INSERT INTO item_mercaderia(codigo, mercaderia_id, pedido_id, detalle_producto_id, orden_devolucion_id) VALUES ('003-2021-10-12-006', 3, 6, 3, null);
INSERT INTO item_mercaderia(codigo, mercaderia_id, pedido_id, detalle_producto_id, orden_devolucion_id) VALUES ('003-2021-10-12-007', 3, 6, 3, null);
INSERT INTO item_mercaderia(codigo, mercaderia_id, pedido_id, detalle_producto_id, orden_devolucion_id) VALUES ('003-2021-10-12-008', 3, 6, 3, null);
INSERT INTO item_mercaderia(codigo, mercaderia_id, pedido_id, detalle_producto_id, orden_devolucion_id) VALUES ('003-2021-10-12-009', 3, 4, 3, null);
INSERT INTO item_mercaderia(codigo, mercaderia_id, pedido_id, detalle_producto_id, orden_devolucion_id) VALUES ('003-2021-10-12-010', 3, 4, 3, null);
INSERT INTO item_mercaderia(codigo, mercaderia_id, pedido_id, detalle_producto_id, orden_devolucion_id) VALUES ('003-2021-10-12-011', 3, 4, 3, null);
INSERT INTO item_mercaderia(codigo, mercaderia_id, pedido_id, detalle_producto_id, orden_devolucion_id) VALUES ('003-2021-10-12-012', 3, 4, 3, null);
INSERT INTO item_mercaderia(codigo, mercaderia_id, pedido_id, detalle_producto_id, orden_devolucion_id) VALUES ('003-2021-10-12-012', 3, 9, 3, null);
INSERT INTO item_mercaderia(codigo, mercaderia_id, pedido_id, detalle_producto_id, orden_devolucion_id) VALUES ('003-2021-10-12-012', 3, 9, 3, null);
INSERT INTO item_mercaderia(codigo, mercaderia_id, pedido_id, detalle_producto_id, orden_devolucion_id) VALUES ('003-2021-10-12-012', 3, 9, 3, null);
INSERT INTO item_mercaderia(codigo, mercaderia_id, pedido_id, detalle_producto_id, orden_devolucion_id) VALUES ('003-2021-10-12-012', 3, 9, 3, null);
------------------------------------------------------------------------------------------------------------------------
