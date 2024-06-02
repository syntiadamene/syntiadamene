<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Gestion des Avions</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        h1, h3, th {
            font-family: 'Trebuchet MS', sans-serif; /* Changer la police des titres */
            font-size: 18px; /* Ajuster la taille de la police des titres */
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
    <h1>Gestion des Avions</h1>
    <h3>Liste des Avions</h3>
    <br/>
    <form method="post">
        Filtrer par: <input type="text" name="mot">
        <input type="submit" name="Filtrer" value="Filtrer">
    </form>
    <br/>
    <table border="1"> 
        <tr>
            <th>Id Avion</th>
            <th>Constructeur</th>
            <th>Modèle</th>
            <th>Capacité</th>
            <th>Id Aéroport</th>
            <th>Photo</th>
            <?php
            if (isset($_SESSION['role']) && $_SESSION['role'] == "admin") {
                echo "<th>Opérations</th>";
            }
            ?>
        </tr>
        <?php
        if (isset($lesAvions)) {
            foreach ($lesAvions as $unAvion) {
                echo "<tr>";
                echo "<td>".$unAvion['idavion']."</td>";
                echo "<td>".$unAvion['constructeur']."</td>";
                echo "<td>".$unAvion['modele']."</td>";
                echo "<td>".$unAvion['capacite']."</td>";
                echo "<td>".$unAvion['idaeroport']."</td>";
                echo "<td>".$unAvion['photo']."</td>";
                if (isset($_SESSION['role']) && $_SESSION['role'] == "admin") {
                    echo "<td> <a href='index.php?page=3&action=sup&idAvion=".$unAvion['idavion']."'><img src='images/supprimer.jpeg' height='40' width='40'></a>";
                    echo "<a href='index.php?page=3&action=edit&idAvion=".$unAvion['idavion']."'><img src='images/editer.jpeg' height='40' width='40'></a>";
                    echo "<a href='index.php?page=3&action=view&idAvion=".$unAvion['idavion']."'><img src='images/voir.jpeg' height='40' width='40'></a></td>";
                }
                echo "</tr>";
            }
        }
        ?>
    </table>
</body>
</html>
