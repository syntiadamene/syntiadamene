<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Liste des Aéroports</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        h3, th {
            font-family: 'Trebuchet MS', sans-serif; /* Changer la police des titres */
            font-size: 16px; /* Ajuster la taille de la police des titres */
            color: #333; /* Couleur des titres */
        }
        table {
            width: 70%; /* Réduire la largeur de la table */
            border-collapse: collapse;
            margin: 20px auto; /* Centrer la table */
        }
        th, td {
            padding: 6px; /* Réduire le padding des cellules */
            text-align: left;
            border-bottom: 1px solid #ddd;
            font-size: 12px; /* Réduire la taille de la police */
        }
        th {
            background-color: #f2f2f2;
        }
        form {
            width: 300px;
            margin: 20px auto; /* Centrer le formulaire */
            padding: 10px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        input[type="text"], input[type="submit"] {
            width: calc(100% - 16px);
            margin-bottom: 10px;
            padding: 6px; /* Réduire le padding des champs */
            border: 1px solid #ccc;
            border-radius: 3px;
            font-size: 12px; /* Réduire la taille de la police */
        }
        input[type="submit"] {
            width: auto;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 3px;
            cursor: pointer;
            padding: 6px 12px;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h3>Liste des Compagnies</h3>
    <br/>
    <form method="post">
        Filtrer par: <input type="text" name="mot">
        <input type="submit" name="Filtrer" value="Filtrer">
    </form>
    <br/>
    <table border="1">
        <tr>
            <th>Id Compagnie</th>
            <th>Libellé</th>
            <th>Pays</th>
        </tr>
        <?php
        if (isset($lesCompagnies)) {
            foreach ($lesCompagnies as $uneCompagnie) {
                echo "<tr>";
                echo "<td>".$uneCompagnie['idcompagnie']."</td>";
                echo "<td>".$uneCompagnie['libelle']."</td>";
                echo "<td>".$uneCompagnie['pays']."</td>";
                echo "</tr>";
            }
        }
        ?>
    </table>
</body>
</html>
