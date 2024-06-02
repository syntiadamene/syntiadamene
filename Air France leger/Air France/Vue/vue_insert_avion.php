<h3><?= isset($leAvion[0]['idavion']) ? 'Édition d\'un avion' : 'Ajout d\'un avion' ?></h3>
<form method="post">
    <label for="constructeur">Constructeur :</label><br>
    <input type="text" id="constructeur" name="constructeur" value="<?= isset($leAvion[0]['constructeur']) ? $leAvion[0]['constructeur'] : '' ?>"><br>

    <label for="modele">Modèle :</label><br>
    <input type="text" id="modele" name="modele" value="<?= isset($leAvion[0]['modele']) ? $leAvion[0]['modele'] : '' ?>"><br>

    <label for="capacite">Capacité :</label><br>
    <input type="text" id="capacite" name="capacite" value="<?= isset($leAvion[0]['capacite']) ? $leAvion[0]['capacite'] : '' ?>"><br>

    <label for="photo">Photo :</label><br>
    <input type="text" id="photo" name="photo" value="<?= isset($leAvion[0]['photo']) ? $leAvion[0]['photo'] : '' ?>"><br>

    <label for="idaeroport">Aéroport :</label><br>
    <select id="idaeroport" name="idaeroport">
        <?php foreach ($lesAeroports as $unAeroport): ?>
            <?php $selected = (isset($leAvion[0]['idaeroport']) && $leAvion[0]['idaeroport'] == $unAeroport['idaeroport']) ? 'selected' : ''; ?>
            <option value="<?= $unAeroport['idaeroport'] ?>" <?= $selected ?>>
                <?= $unAeroport['nom'] ?>
            </option>
        <?php endforeach; ?>
    </select><br>
    
    <input type="reset" name="Annuler" value="Annuler">
    <input type="submit" <?= isset($leAvion[0]['idavion']) ? 'name="Modifier" value="Modifier"' : 'name="Valider" value="Valider"' ?>>
    <?= isset($leAvion[0]['idavion']) ? '<input type="hidden" name="idavion" value="'.$leAvion[0]['idavion'].'">' : '' ?>
</form>
