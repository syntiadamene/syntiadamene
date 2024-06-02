
DROP DATABASE IF EXISTS air_france;

CREATE DATABASE air_france;


USE air_france;


CREATE TABLE Pilote (
    idpilote INT(3) NOT NULL AUTO_INCREMENT,
    nom VARCHAR(100),
    prenom VARCHAR(50),
    email VARCHAR(50),
    mdp VARCHAR(50),
    adresse VARCHAR(50),
    statut enum ("PiloteExterne","PiloteInterne"),
    nbheuresvol INT,
    PRIMARY KEY (idpilote)
);


CREATE TABLE PiloteInterne (
    idpilote INT(3) NOT NULL,
    salaire FLOAT,
    date_entree date,
    PRIMARY KEY (idpilote),
    FOREIGN KEY (idpilote) REFERENCES Pilote(idpilote)
);
CREATE TABLE Compagnie (
    idcompagnie INT(3) NOT NULL AUTO_INCREMENT,
    libelle VARCHAR(50) NOT NULL,
    pays VARCHAR(50),
    PRIMARY KEY (idcompagnie)
);


CREATE TABLE PiloteExterne (
    idpilote INT(3) NOT NULL,
    idcompagnie INT(3) NOT NULL,
    date_debut DATE,
    date_fin DATE,
    PRIMARY KEY (idpilote),
    FOREIGN KEY (idpilote) REFERENCES Pilote(idpilote),
    FOREIGN KEY (idcompagnie) REFERENCES Compagnie(idcompagnie)
);


DELIMITER $$

CREATE PROCEDURE insererPiloteInterne ( 
    IN p_nom VARCHAR(50), 
    IN p_prenom VARCHAR(50), 
    IN p_email VARCHAR(50), 
   
    IN p_adresse VARCHAR(50), 
    IN p_nbheuresvol INT,
    IN p_statut ENUM('PiloteExterne', 'PiloteInterne'), 
    IN p_salaire FLOAT,
    IN p_date_entree DATE 
)
BEGIN
    DECLARE p_idPilote INT;

    INSERT INTO Pilote (nom, prenom, email, adresse, nbheuresvol, statut)
    VALUES (p_nom, p_prenom, p_email,  p_adresse, p_nbheuresvol, p_statut);

    SELECT idpilote INTO p_idPilote 
    FROM Pilote 
    WHERE nom = p_nom 
        AND prenom = p_prenom 
        AND email = p_email 
        
        AND adresse = p_adresse 
        AND nbheuresvol = p_nbheuresvol 
        AND statut = p_statut 
    LIMIT 1;

    INSERT INTO PiloteInterne (idpilote, salaire, date_entree)
    VALUES (p_idPilote, p_salaire, p_date_entree);
END $$

DELIMITER ;


DELIMITER $$

CREATE PROCEDURE insererPiloteExterne ( 
    IN p_nom VARCHAR(50), 
    IN p_prenom VARCHAR(50), 
    IN p_email VARCHAR(50), 
    
    IN p_adresse VARCHAR(50), 
    IN p_nbheuresvol INT,
    IN p_statut ENUM('PiloteExterne', 'PiloteInterne'), 
    IN p_idcompagnie INT(3),
    IN p_date_debut DATE,
    IN p_date_fin DATE
)
BEGIN
    DECLARE p_idPilote INT;

    INSERT INTO Pilote (nom, prenom, email,  adresse, nbheuresvol, statut)
    VALUES (p_nom, p_prenom, p_email, p_adresse, p_nbheuresvol, p_statut);

    SELECT idpilote INTO p_idPilote 
    FROM Pilote 
    WHERE nom = p_nom 
        AND prenom = p_prenom 
        AND email = p_email 
        
        AND adresse = p_adresse 
        AND nbheuresvol = p_nbheuresvol 
        AND statut = p_statut 
    LIMIT 1;

    INSERT INTO PiloteExterne (idpilote, idcompagnie, date_debut, date_fin)
    VALUES (p_idPilote, p_idcompagnie, p_date_debut, p_date_fin);
END $$

DELIMITER ;




CREATE TABLE Aeroport (
    idaeroport INT(3) NOT NULL AUTO_INCREMENT,
    nom VARCHAR(50),
    code VARCHAR(30),
    ville VARCHAR(50),
    pays VARCHAR(50),
    statut VARCHAR(50),
    PRIMARY KEY (idaeroport)
);

CREATE TABLE Avion (
    idavion INT(3) NOT NULL AUTO_INCREMENT,
    constructeur VARCHAR(50),
    modele VARCHAR(50),
    capacite INT,
    photo VARCHAR(50),
    idaeroport INT(3),
    PRIMARY KEY (idavion),
    FOREIGN KEY (idaeroport) REFERENCES Aeroport(idaeroport)
);

CREATE TABLE Vol (
    idvol INT(3) NOT NULL AUTO_INCREMENT,
    numero_vol VARCHAR(10),
    date_depart DATE,
    heure_depart TIME,
    heure_arrivee TIME,
    ville_depart VARCHAR(50),
    ville_arrivee VARCHAR(50),
    classe_vol VARCHAR(10),
    idpilote INT(3) NOT NULL,
    idavion INT(3) NOT NULL,
    PRIMARY KEY (idvol),
    FOREIGN KEY (idpilote) REFERENCES Pilote(idpilote),
    FOREIGN KEY (idavion) REFERENCES Avion(idavion)
);

CREATE TABLE grainsel( 
    nb VARCHAR(100) PRIMARY KEY
);
INSERT INTO grainsel VALUE ("123456789098765678987656");

CREATE TABLE User (
    iduser INT(3) NOT NULL AUTO_INCREMENT,
    nom VARCHAR(50),
    prenom VARCHAR(50),
    email VARCHAR(50),
    mdp VARCHAR(50),
    role ENUM("user", "admin"),
    PRIMARY KEY (iduser)
);
INSERT INTO User VALUES(null,'semin','kohdamani','a@gmail.com','123','admin');

INSERT INTO User VALUES(null,'intissar','abdelmalek','b@gmail.com','456','user');


  -modif pilote interne --
DELIMITER $
CREATE PROCEDURE modifierPiloteInterne (
    
    IN p_idPilote INT(3),
    IN p_salaire FLOAT, 
    IN p_date_entree date 
)
BEGIN
    UPDATE PiloteInterne SET salaire = p_salaire  , date_entree = p_date_entree WHERE idpilote = p_idPilote;
END $
DELIMITER ;



DELIMITER $
CREATE PROCEDURE supprimerPiloteInterne (
    IN p_idPilote INT(3)
)
BEGIN
    DELETE FROM PiloteInterne WHERE idpilote = p_idPilote;
    DELETE FROM Pilote WHERE idpilote = p_idPilote;
END $
DELIMITER ;


DELIMITER $
CREATE PROCEDURE modifierPiloteExterne (
    IN p_idPilote INT(3),
    IN p_idCompagnie INT(3),
    IN p_date_debut DATE,
    IN p_date_fin DATE
)
BEGIN
    UPDATE PiloteExterne 
    SET idcompagnie = p_idCompagnie, date_debut = p_date_debut, date_fin = p_date_fin 
    WHERE idpilote = p_idPilote;
END $
DELIMITER ;


DELIMITER $
CREATE PROCEDURE supprimerPiloteExterne (
    IN p_idPilote INT(3)
)
BEGIN
    DELETE FROM PiloteExterne WHERE idpilote = p_idPilote;
    DELETE FROM Pilote WHERE idpilote = p_idPilote;
END $
DELIMITER ;





DELIMITER //
CREATE TRIGGER updateTotalHeuresVolPilote AFTER INSERT ON Pilote
FOR EACH ROW
BEGIN
    DECLARE totalHeures INT;
    SELECT SUM(nbheuresvol) INTO totalHeures FROM Pilote;
    UPDATE grainsel SET nb = totalHeures;
END//
DELIMITER ;


CREATE VIEW VuePilotesInternes AS
SELECT P.idpilote, P.nom, P.prenom, P.email, P.adresse, P.nbheuresvol, P.statut , PI.salaire
FROM Pilote P, PiloteInterne PI
WHERE P.idpilote = PI.idpilote;



CREATE VIEW VuePilotesExternes AS
SELECT P.idpilote, P.nom, P.prenom, P.email, P.adresse, P.nbheuresvol,P.statut , PE.idcompagnie, PE.date_debut, PE.date_fin, C.libelle AS compagnie, C.pays AS pays_compagnie
FROM Pilote P, PiloteExterne PE, Compagnie C
WHERE P.idpilote = PE.idpilote
AND PE.idcompagnie = C.idcompagnie;