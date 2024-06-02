<h3>Ajout d'une compagnie</h3>
<br/>
<form method="post">
    Libelle </br> 
    <input type="text" name="libelle" value="<?= (!empty($laCompagnie[0]['libelle'])) ? $laCompagnie[0]['libelle'] : '' ?>">
    </br>
    
    Pays  </br>
    <input type="text" name="pays" value="<?= (!empty($laCompagnie[0]['pays'])) ? $laCompagnie[0]['pays'] : '' ?>">
    </br>

    <input type="reset" name="Annuler" value="Annuler">
    <input type="submit" <?= (!empty($laCompagnie[0]['idcompagnie'])) ? 'name="Modifier" value="Modifier"' : 'name="Valider" value="Valider"' ?>>
    <?= (!empty($laCompagnie[0]['idcompagnie'])) ? '<input type="hidden" name="idcompagnie" value="' . $laCompagnie[0]['idcompagnie'] . '">' : '' ?>
</form>
