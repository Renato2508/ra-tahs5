CREATE VIEW v_batiment_presence AS
SELECT b.idBatiment, b.nomBatiment, b.idSource, p.datepresence, p.tranchehoraire, p.nbrEleve
FROM batiment as b
JOIN presence  as p ON  p.idBatiment = b.idBatiment ;

CREATE or replace VIEW  v_source_all AS
SELECT s.idsource, s.nomsource, p.idpanneau, p.nompanneau, p.puissance, bt.idbatterie, bt.nombatterie, bt.capacite, c.datecoupure, c.heurecoupure, vp.idbatiment, vp.nombatiment, vp.datepresence, vp.tranchehoraire, vp.nbrEleve
FROM source as s
left join coupure as c on c.idSource = s.idSource
join panneau as p on s.idPanneau = p.idPanneau
join batterie as bt on s.idBatterie = bt.idBatterie
join v_batiment_presence as vp on vp.idSource = s.idSource 
and c.datecoupure = vp.datepresence;