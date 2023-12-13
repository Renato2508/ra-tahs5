CREATE DATABASE solaire;
\c solaire

CREATE TABLE batterie (
   idBatterie SERIAL PRIMARY KEY,
   nomBatterie VARCHAR(255),
   capacite numeric(9,2)
);

CREATE TABLE panneau (
   idPanneau SERIAL PRIMARY KEY,
   nomPanneau VARCHAR(255),
   puissance numeric(9,2)
);

CREATE TABLE source (
   idSource SERIAL PRIMARY KEY,
   nomSource VARCHAR(255),
   idPanneau INTEGER REFERENCES panneau(idPanneau),
   idBatterie INTEGER REFERENCES batterie(idBatterie)
);

CREATE TABLE batiment (
   idBatiment SERIAL PRIMARY KEY,
   nomBatiment VARCHAR(255),
   idSource INTEGER REFERENCES source(idSource)
);

CREATE TABLE meteo (
   jour date,
   heure int,
   indice INTEGER
);

CREATE TABLE presence (
   idBatiment INTEGER REFERENCES batiment(idBatiment),
   datePresence DATE,
   trancheHoraire CHAR,
   nbrEleve INTEGER
);

CREATE TABLE coupure (
   dateCoupure DATE,
   idSource INTEGER REFERENCES source(idSource),
   heureCoupure TIME
);
