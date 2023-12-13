INSERT INTO panneau (nomPanneau, puissance)
VALUES
('Panneau1', 10000),
('Panneau2', 10000);

INSERT INTO batterie (nomBatterie, capacite)
VALUES
('Batterie1', 50000),
('Batterie2', 50000);

INSERT INTO source (nomSource, idPanneau, idBatterie)
VALUES
('Source1', 1, 1),
('Source2', 2, 2);

INSERT INTO batiment (nomBatiment, idSource)
VALUES
('Batiment1', 1),
('Batiment2', 2);

INSERT INTO meteo (jour, heure, indice)
VALUES ('2023-12-7', 8,2 );
INSERT INTO meteo (jour, heure, indice)
VALUES ('2023-12-7', 9, 2);
INSERT INTO meteo (jour, heure, indice)
VALUES ('2023-12-7',10 ,5 );
INSERT INTO meteo (jour, heure, indice)
VALUES ('2023-12-7',11 ,7 );
INSERT INTO meteo (jour, heure, indice)
VALUES ('2023-12-7', 12, 8);
INSERT INTO meteo (jour, heure, indice)
VALUES ('2023-12-7',13 , 7);
INSERT INTO meteo (jour, heure, indice)
VALUES ('2023-12-7',14 , 9);
INSERT INTO meteo (jour, heure, indice)
VALUES ('2023-12-7',15 , 8);
INSERT INTO meteo (jour, heure, indice)
VALUES ('2023-12-7', 16, 7);
INSERT INTO meteo (jour, heure, indice)
VALUES ('2023-12-7',17 , 6);

INSERT INTO meteo (jour, heure, indice)
VALUES ('2023-12-8', 8, 4);
INSERT INTO meteo (jour, heure, indice)
VALUES ('2023-12-8', 9, 5);
INSERT INTO meteo (jour, heure, indice)
VALUES ('2023-12-8', 10, 8);
INSERT INTO meteo (jour, heure, indice)
VALUES ('2023-12-8', 11, 8);
INSERT INTO meteo (jour, heure, indice)
VALUES ('2023-12-8', 12, 9);
INSERT INTO meteo (jour, heure, indice)
VALUES ('2023-12-8',13 , 9);
INSERT INTO meteo (jour, heure, indice)
VALUES ('2023-12-8', 14, 10);
INSERT INTO meteo (jour, heure, indice)
VALUES ('2023-12-8', 15, 8);
INSERT INTO meteo (jour, heure, indice)
VALUES ('2023-12-8',16 , 7);
INSERT INTO meteo (jour, heure, indice)
VALUES ('2023-12-8',17 , 6);

INSERT INTO presence (idBatiment, datePresence, trancheHoraire, nbrEleve)
VALUES
(1, '2023-12-7', 'M', 273),
(1, '2023-12-7', 'A', 200),
(2, '2023-12-7', 'M', 150),
(2, '2023-12-7', 'A', 172),
(1, '2023-12-8', 'M', 273),
(1, '2023-12-8', 'A', 200),
(2, '2023-12-8', 'M', 150),
(2, '2023-12-8', 'A', 172);

 INSERT INTO coupure (dateCoupure, idSource, heureCoupure)
  VALUES ('2023-12-7', 1, '12:28'),
         ('2023-12-7', 2, '10:22'),
         ('2023-12-8', 1, '13:28'),
         ('2023-12-8', 2, '15:28');
