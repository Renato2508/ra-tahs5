-- import des batteries
\copy batterie(nombatterie, capacite) from 'D:\S5\achiLog\solaire\solaire\nato\data\batterie.csv' WITH DELIMITER ',' CSV HEADER;

-- panneaux
\copy panneau(nompanneau, puissance) from 'D:\S5\achiLog\solaire\solaire\nato\data\panneau.csv' WITH DELIMITER ',' CSV HEADER;

-- source
\copy source(nomsource, idpanneau, idbatterie) from 'D:\S5\achiLog\solaire\solaire\nato\data\source.csv' WITH DELIMITER ',' CSV HEADER;

-- batiment
\copy batiment(nombatiment, idsource) from 'D:\S5\achiLog\solaire\solaire\nato\data\batiment.csv' WITH DELIMITER ',' CSV HEADER;

-- presence
\copy presence(idbatiment, datepresence, tranchehoraire, nbreleve) from 'D:\S5\achiLog\solaire\solaire\nato\data\presence.csv' WITH DELIMITER ',' CSV HEADER;

-- coupures
\copy coupure(datecoupure, idsource, heurecoupure) from 'D:\S5\achiLog\solaire\solaire\nato\data\coupure.csv' WITH DELIMITER ',' CSV HEADER;

-- meteo
\copy meteo(jour, heure, indice) from 'D:\S5\achiLog\solaire\solaire\nato\data\meteo.csv' WITH DELIMITER ',' CSV HEADER;
