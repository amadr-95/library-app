/*INSERT INTO libro_genero (libro_id, genero_id) VALUES (1, 1);
INSERT INTO libro_genero (libro_id, genero_id) VALUES (1, 2);
INSERT INTO libro_genero (libro_id, genero_id) VALUES (1, 3);
INSERT INTO libro_genero (libro_id, genero_id) VALUES (3, 4);*/

-- Asociaciones de géneros a libros (suponiendo que los IDs de géneros y libros ya existen)
INSERT INTO libro_genero (libro_id, genero_id)
VALUES (1, 1),
       (1, 6),
       (2, 5),
       (2, 9),
       (3, 3),
       (3, 10),
       (4, 4),
       (4, 8),
       (5, 1),
       (5, 6),
       (6, 2),
       (6, 7),
       (7, 3),
       (7, 6),
       (8, 5),
       (8, 9);

