drop function elevesPresents;
CREATE OR REPLACE FUNCTION elevesPresents(datePresenceA DATE, idBatimentA INTEGER, trancheHoraireA CHAR)
RETURNS INTEGER AS $$
DECLARE
   jour SMALLINT;
   moyenne integer;
BEGIN
   SELECT EXTRACT(ISODOW FROM datePresenceA) INTO jour;
   SELECT ROUND(coalesce(AVG(nbreleve),0)) INTO moyenne
   FROM v_batiment_presence
   WHERE EXTRACT(ISODOW FROM datePresence) = jour
   AND idbatiment = idBatimentA
   AND tranchehoraire = trancheHoraireA;
   RETURN moyenne;
END;
$$ LANGUAGE plpgsql;


select elevesPresents('2023-12-14', 1,'M');