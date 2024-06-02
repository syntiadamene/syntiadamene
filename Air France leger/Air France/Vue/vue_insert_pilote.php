<h3><?= isset($lePilote[0]['idpilote']) ? 'Édition d\'un pilote' : 'Ajout d\'un pilote' ?></h3>
<form method="post">
    <label for="nom">Nom Pilote</label><br>
    <input type="text" id="nom" name="nom" value="<?= (isset($lePilote[0]['nom'])) ? $lePilote[0]['nom'] : '' ?>" required><br>

    <label for="prenom">Prénom Pilote</label><br>
    <input type="text" id="prenom" name="prenom" value="<?= (isset($lePilote[0]['prenom'])) ? $lePilote[0]['prenom'] : '' ?>" required><br>

    <label for="email">Email Pilote</label><br>
    <input type="email" id="email" name="email" value="<?= (isset($lePilote[0]['email'])) ? $lePilote[0]['email'] : '' ?>" required><br>

    <label for="adresse">Adresse Pilote</label><br>
    <input type="text" id="adresse" name="adresse" value="<?= (isset($lePilote[0]['adresse'])) ? $lePilote[0]['adresse'] : '' ?>" required><br>

    <label for="nbheuresvol">Nombre d'heures de vol</label><br>
    <input type="number" id="nbheuresvol" name="nbheuresvol" value="<?= (isset($lePilote[0]['nbheuresvol'])) ? $lePilote[0]['nbheuresvol'] : '' ?>" required><br>

    <label for="statut">Statut Pilote</label><br>
    <select name="statut" id="statut">
        <option value="PiloteExterne" <?= (isset($lePilote[0]['statut']) && $lePilote[0]['statut'] === 'PiloteExterne') ? 'selected' : '' ?>>Externe</option>
        <option value="PiloteInterne" <?= (isset($lePilote[0]['statut']) && $lePilote[0]['statut'] === 'PiloteInterne') ? 'selected' : '' ?>>Interne</option>
    </select><br>

    <!-- Champs pour les pilotes internes -->
    <div id="PiloteInterne" style="display: <?= (isset($lePilote[0]['statut']) && $lePilote[0]['statut'] === 'PiloteInterne') ? 'block' : 'none' ?>;">
        <label for="salaire">Salaire</label><br>
        <input type="text" id="salaire" name="salaire" value="<?= (isset($lePilote[0]['salaire'])) ? $lePilote[0]['salaire'] : '' ?>"><br>

        <label for="date_entree">Date d'entrée</label><br>
        <input type="date" id="date_entree" name="date_entree" value="<?= (isset($lePilote[0]['date_entree'])) ? $lePilote[0]['date_entree'] : '' ?>"><br>
    </div>

    <!-- Champs pour les pilotes externes -->
    <div id="PiloteExterne" style="display: <?= (isset($lePilote[0]['statut']) && $lePilote[0]['statut'] === 'PiloteExterne') ? 'block' : 'none' ?>;">
        <label for="idcompagnie">ID Compagnie</label><br>
        <input type="text" id="idcompagnie" name="idcompagnie" value="<?= (isset($lePilote[0]['idcompagnie'])) ? $lePilote[0]['idcompagnie'] : '' ?>"><br>

        <label for="date_debut">Date de début</label><br>
        <input type="date" id="date_debut" name="date_debut" value="<?= (isset($lePilote[0]['date_debut'])) ? $lePilote[0]['date_debut'] : '' ?>"><br>

        <label for="date_fin">Date de fin</label><br>
        <input type="date" id="date_fin" name="date_fin" value="<?= (isset($lePilote[0]['date_fin'])) ? $lePilote[0]['date_fin'] : '' ?>"><br>
    </div>

    <br>
    <input type="reset" name="Annuler" value="Annuler">
    <input type="submit" name="Valider" value="Valider">
</form>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        var selectedValue = document.querySelector('select[name="statut"]').value;
        var internalFields = document.getElementById('PiloteInterne');
        var externalFields = document.getElementById('PiloteExterne');

        internalFields.style.display = (selectedValue === 'PiloteInterne') ? 'block' : 'none';
        externalFields.style.display = (selectedValue === 'PiloteExterne') ? 'block' : 'none';

        document.querySelector('select[name="statut"]').addEventListener('change', function() {
            var selectedValue = this.value;
            var internalFields = document.getElementById('PiloteInterne');
            var externalFields = document.getElementById('PiloteExterne');

            internalFields.style.display = (selectedValue === 'PiloteInterne') ? 'block' : 'none';
            externalFields.style.display = (selectedValue === 'PiloteExterne') ? 'block' : 'none';
        });
    });
</script>
