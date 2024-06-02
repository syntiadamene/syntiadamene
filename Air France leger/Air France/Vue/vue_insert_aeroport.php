<form method="post">
    <label for="nom">Nom d'aéroport:</label><br>
    <input type="text" id="nom" name="nom" value="<?= (!empty($leAeroport[0]['nom'])) ? $leAeroport[0]['nom'] : '' ?>"><br>

    <label for="code">Code aéroport:</label><br>
    <input type="text" id="code" name="code" value="<?= (!empty($leAeroport[0]['code'])) ? $leAeroport[0]['code'] : '' ?>"><br>

    <label for="ville">Ville aéroport:</label><br>
    <input type="text" id="ville" name="ville" value="<?= (!empty($leAeroport[0]['ville'])) ? $leAeroport[0]['ville'] : '' ?>"><br>

    <label for="pays">Pays aéroport:</label><br>
    <input type="text" id="pays" name="pays" value="<?= (!empty($leAeroport[0]['pays'])) ? $leAeroport[0]['pays'] : '' ?>"><br>

    <label for="statut">Statut:</label><br>
    <select id="statut" name="statut">
        <?php if (!empty($leAeroport[0]['statut'])) : ?>
            <option value="National" <?= ($leAeroport[0]['statut'] == 'National') ? 'selected' : '' ?>>National</option>
            <option value="International" <?= ($leAeroport[0]['statut'] == 'International') ? 'selected' : '' ?>>International</option>
        <?php else : ?>
            <option value="National">National</option>
            <option value="International">International</option>
        <?php endif; ?>
    </select><br>

     <input type="reset" name="Annuler" value="Annuler">
    <input type="submit" <?= isset($leAeroport[0]['idaeroport']) ? 'name="Modifier" value="Modifier"' : 'name="Valider" value="Valider"' ?>>
    <?= isset($leAeroport[0]['idaeroport']) ? '<input type="hidden" name="idaeroport" value="'.$leAeroport[0]['idaeroport'].'">' : '' ?>
</form>
