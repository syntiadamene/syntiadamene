<h3><?= isset($leVol[0]['idvol']) ? 'Édition d\'un vol' : 'Ajout d\'un vol' ?></h3>
<form method="post">
    Numero Vol: </br>
    <input type="text" name="numero_vol" value="<?= (isset($leVol[0]['numero_vol'])) ? $leVol[0]['numero_vol'] : '' ?>"></br>
    Date Depart: </br>
    <input type="date" name="date_depart" value="<?= (isset($leVol[0]['date_depart'])) ? $leVol[0]['date_depart'] : '' ?>"></br>
    Heure Depart: </br>
    <input type="text" name="heure_depart" value="<?= (isset($leVol[0]['heure_depart'])) ? $leVol[0]['heure_depart'] : '' ?>"></br>
    Heure Arrivee: </br>
    <input type="text" name="heure_arrivee" value="<?= (isset($leVol[0]['heure_arrivee'])) ? $leVol[0]['heure_arrivee'] : '' ?>"></br> 
   
    Ville Depart: </br>
    <input type="text" name="ville_depart" value="<?= (isset($leVol[0]['ville_depart'])) ? $leVol[0]['ville_depart'] : '' ?>"></br> 
    Ville Arrivee: </br>
    <input type="text" name="ville_arrivee" value="<?= (isset($leVol[0]['ville_arrivee'])) ? $leVol[0]['ville_arrivee'] : '' ?>"></br> 
    Classe Vol: </br>
    <input type="text" name="classe_vol" value="<?= (isset($leVol[0]['classe_vol'])) ? $leVol[0]['classe_vol'] : '' ?>"></br> 

    Pilote: </br>
    <select name="idpilote">
        <?php foreach ($lesPilotes as $unPilote) : ?>
            <?php 
                $idPilote = isset($unPilote['idpilote']) ? $unPilote['idpilote'] : '';
                $nom = isset($unPilote['nom']) ? $unPilote['nom'] : '';
                $statut = isset($unPilote['statut']) ? $unPilote['statut'] : '';

                $selected = (isset($leVol[0]['idpilote']) && $idPilote == $leVol[0]['idpilote']) ? 'selected' : ''; 
            ?>
            <option value="<?= $idPilote ?>" <?= $selected ?>>
                <?= $nom . " " . $statut ?>
            </option>
        <?php endforeach; ?>
    </select>

    <br/>
    Avion: </br>
    <select name="idavion">
        <?php foreach ($lesAvions as $unAvion) : ?>
            <?php $selected = (isset($leVol[0]['idavion']) && $leVol[0]['idavion'] == $unAvion['idavion']) ? 'selected' : ''; ?>
            <option value="<?= $unAvion['idavion'] ?>" <?= $selected ?>>
                <?= $unAvion['constructeur'] . " " . $unAvion['modele'] ?>
            </option>
        <?php endforeach; ?>
    </select>
    <br/>
    
    <input type="reset" name="Annuler" value="Annuler">
    <input type="submit" <?= (isset($leVol[0]['idvol'])) ? 'name="Modifier" value="Modifier"' : 'name="Valider" value="Valider"' ?>>
    <?= (isset($leVol[0]['idvol'])) ? '<input type="hidden" name="idvol" value="' . $leVol[0]['idvol'] . '">' : '' ?>
</form>
