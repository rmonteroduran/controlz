-- ==========================================
-- PROVINCIAS ARGENTINAS (Tabla: provincias)
-- ==========================================
INSERT INTO provincias (id, nombre) VALUES (1, 'Buenos Aires');
INSERT INTO provincias (id, nombre) VALUES (2, 'Catamarca');
INSERT INTO provincias (id, nombre) VALUES (3, 'Chaco');
INSERT INTO provincias (id, nombre) VALUES (4, 'Chubut');
INSERT INTO provincias (id, nombre) VALUES (5, 'Ciudad Autónoma de Buenos Aires');
INSERT INTO provincias (id, nombre) VALUES (6, 'Córdoba');
INSERT INTO provincias (id, nombre) VALUES (7, 'Corrientes');
INSERT INTO provincias (id, nombre) VALUES (8, 'Entre Ríos');
INSERT INTO provincias (id, nombre) VALUES (9, 'Formosa');
INSERT INTO provincias (id, nombre) VALUES (10, 'Jujuy');
INSERT INTO provincias (id, nombre) VALUES (11, 'La Pampa');
INSERT INTO provincias (id, nombre) VALUES (12, 'La Rioja');
INSERT INTO provincias (id, nombre) VALUES (13, 'Mendoza');
INSERT INTO provincias (id, nombre) VALUES (14, 'Misiones');
INSERT INTO provincias (id, nombre) VALUES (15, 'Neuquén');
INSERT INTO provincias (id, nombre) VALUES (16, 'Río Negro');
INSERT INTO provincias (id, nombre) VALUES (17, 'Salta');
INSERT INTO provincias (id, nombre) VALUES (18, 'San Juan');
INSERT INTO provincias (id, nombre) VALUES (19, 'San Luis');
INSERT INTO provincias (id, nombre) VALUES (20, 'Santa Cruz');
INSERT INTO provincias (id, nombre) VALUES (21, 'Santa Fe');
INSERT INTO provincias (id, nombre) VALUES (22, 'Santiago del Estero');
INSERT INTO provincias (id, nombre) VALUES (23, 'Tierra del Fuego, Antártida e Islas del Atlántico Sur');
INSERT INTO provincias (id, nombre) VALUES (24, 'Tucumán');

-- ==========================================
-- CIUDADES ARGENTINAS (Tabla: ciudades)
-- ==========================================
-- Provincia 1: Buenos Aires
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (1, 'La Plata', 1);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (2, 'Mar del Plata', 1);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (3, 'Bahía Blanca', 1);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (4, 'Tandil', 1);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (5, 'San Nicolás de los Arroyos', 1);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (6, 'Olavarría', 1);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (7, 'Pergamino', 1);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (8, 'Junín', 1);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (9, 'Campana', 1);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (10, 'Necochea', 1);

-- Provincia 2: Catamarca
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (11, 'San Fernando del Valle de Catamarca', 2);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (12, 'Tinogasta', 2);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (13, 'Belén', 2);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (14, 'Andalgalá', 2);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (15, 'Santa María', 2);

-- Provincia 3: Chaco
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (16, 'Resistencia', 3);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (17, 'Presidencia Roque Sáenz Peña', 3);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (18, 'Villa Ángela', 3);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (19, 'Charata', 3);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (20, 'Las Breñas', 3);

-- Provincia 4: Chubut
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (21, 'Rawson', 4);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (22, 'Comodoro Rivadavia', 4);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (23, 'Trelew', 4);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (24, 'Puerto Madryn', 4);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (25, 'Esquel', 4);

-- Provincia 5: Ciudad Autónoma de Buenos Aires
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (26, 'Ciudad Autónoma de Buenos Aires', 5);

-- Provincia 6: Córdoba
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (27, 'Córdoba', 6);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (28, 'Río Cuarto', 6);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (29, 'Villa María', 6);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (30, 'Villa Carlos Paz', 6);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (31, 'San Francisco', 6);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (32, 'Alta Gracia', 6);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (33, 'Río Tercero', 6);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (34, 'Bell Ville', 6);

-- Provincia 7: Corrientes
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (35, 'Corrientes', 7);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (36, 'Goya', 7);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (37, 'Paso de los Libres', 7);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (38, 'Curuzú Cuatiá', 7);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (39, 'Mercedes', 7);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (40, 'Santo Tomé', 7);

-- Provincia 8: Entre Ríos
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (41, 'Paraná', 8);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (42, 'Concordia', 8);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (43, 'Gualeguaychú', 8);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (44, 'Concepción del Uruguay', 8);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (45, 'Villaguay', 8);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (46, 'Chajarí', 8);

-- Provincia 9: Formosa
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (47, 'Formosa', 9);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (48, 'Clorinda', 9);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (49, 'Pirané', 9);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (50, 'El Colorado', 9);

-- Provincia 10: Jujuy
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (51, 'San Salvador de Jujuy', 10);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (52, 'San Pedro de Jujuy', 10);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (53, 'Palpalá', 10);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (54, 'La Quiaca', 10);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (55, 'Libertador General San Martín', 10);

-- Provincia 11: La Pampa
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (56, 'Santa Rosa', 11);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (57, 'General Pico', 11);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (58, 'Toay', 11);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (59, 'Realicó', 11);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (60, 'Eduardo Castex', 11);

-- Provincia 12: La Rioja
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (61, 'La Rioja', 12);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (62, 'Chilecito', 12);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (63, 'Aimogasta', 12);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (64, 'Chamical', 12);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (65, 'Chepes', 12);

-- Provincia 13: Mendoza
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (66, 'Mendoza', 13);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (67, 'San Rafael', 13);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (68, 'Godoy Cruz', 13);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (69, 'Maipú', 13);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (70, 'Las Heras', 13);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (71, 'San Martín', 13);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (72, 'Luján de Cuyo', 13);

-- Provincia 14: Misiones
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (73, 'Posadas', 14);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (74, 'Oberá', 14);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (75, 'Eldorado', 14);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (76, 'Puerto Iguazú', 14);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (77, 'Apóstoles', 14);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (78, 'San Vicente', 14);

-- Provincia 15: Neuquén
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (79, 'Neuquén', 15);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (80, 'Cutral Có', 15);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (81, 'Zapala', 15);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (82, 'Centenario', 15);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (83, 'Plottier', 15);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (84, 'San Martín de los Andes', 15);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (85, 'Chos Malal', 15);

-- Provincia 16: Río Negro
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (86, 'Viedma', 16);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (87, 'San Carlos de Bariloche', 16);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (88, 'General Roca', 16);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (89, 'Cipolletti', 16);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (90, 'Villa Regina', 16);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (91, 'Cinco Saltos', 16);

-- Provincia 17: Salta
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (92, 'Salta', 17);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (93, 'San Ramón de la Nueva Orán', 17);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (94, 'Tartagal', 17);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (95, 'Metán', 17);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (96, 'General Güemes', 17);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (97, 'Cafayate', 17);

-- Provincia 18: San Juan
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (98, 'San Juan', 18);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (99, 'Villa Krause', 18);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (100, 'Caucete', 18);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (101, 'Chimbas', 18);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (102, 'Santa Lucía', 18);

-- Provincia 19: San Luis
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (103, 'San Luis', 19);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (104, 'Villa Mercedes', 19);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (105, 'Villa de Merlo', 19);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (106, 'Juana Koslay', 19);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (107, 'La Punta', 19);

-- Provincia 20: Santa Cruz
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (108, 'Río Gallegos', 20);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (109, 'Caleta Olivia', 20);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (110, 'El Calafate', 20);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (111, 'Pico Truncado', 20);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (112, 'Las Heras', 20);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (113, 'Puerto Deseado', 20);

-- Provincia 21: Santa Fe
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (114, 'Santa Fe', 21);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (115, 'Rosario', 21);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (116, 'Rafaela', 21);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (117, 'Venado Tuerto', 21);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (118, 'Reconquista', 21);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (119, 'Santo Tomé', 21);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (120, 'Villa Constitución', 21);

-- Provincia 22: Santiago del Estero
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (121, 'Santiago del Estero', 22);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (122, 'La Banda', 22);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (123, 'Termas de Río Hondo', 22);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (124, 'Frías', 22);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (125, 'Añatuya', 22);

-- Provincia 23: Tierra del Fuego, Antártida e Islas del Atlántico Sur
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (126, 'Ushuaia', 23);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (127, 'Río Grande', 23);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (128, 'Tolhuin', 23);

-- Provincia 24: Tucumán
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (129, 'San Miguel de Tucumán', 24);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (130, 'Yerba Buena', 24);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (131, 'Villa Luján', 24);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (132, 'Concepción', 24);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (133, 'Tafí Viejo', 24);
INSERT INTO ciudades (id, nombre, provincia_id) VALUES (134, 'Aguilares', 24);

-- ==========================================
-- CARGA INICIAL DE PERSONAS (Tabla: personas)
-- ==========================================
INSERT INTO personas (id, nombre, apellido, dni_cuit, telefono, email, domicilio, ciudad_id, eliminada) VALUES (1, 'Fabricio', 'Frezza', '43959046', NULL, 'ffrezza@frsf.utn.edu.ar', NULL, 114, false);
INSERT INTO personas (id, nombre, apellido, dni_cuit, telefono, email, domicilio, ciudad_id, eliminada) VALUES (2, 'María Paula', 'Gramajo', '33971435', NULL, 'Paulagramajo89@gmail.com', NULL, 129, false);
INSERT INTO personas (id, nombre, apellido, dni_cuit, telefono, email, domicilio, ciudad_id, eliminada) VALUES (3, 'Nikolai Mario', 'Vidovic', '33597487', NULL, 'vidovic@agro.uba.ar', NULL, 1, false);
INSERT INTO personas (id, nombre, apellido, dni_cuit, telefono, email, domicilio, ciudad_id, eliminada) VALUES (4, 'Rodrigo Matias', 'Montero Duran', '34493426', NULL, 'rmonteroduran@frsf.utn.edu.ar', NULL, 26, false);
