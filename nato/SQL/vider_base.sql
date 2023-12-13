delete from presence;
delete from batiment;
delete from coupure;
delete from source;
delete from batterie;
delete from panneau;
delete from meteo;

alter sequence batiment_idbatiment_seq restart;
alter sequence batterie_idbatterie_seq restart;
alter sequence panneau_idpanneau_seq restart;
alter sequence  source_idsource_seq restart;