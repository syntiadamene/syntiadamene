<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Gestion des Pilotes</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        h3, th {
            font-family: 'Trebuchet MS', sans-serif; /* Changer la police des titres */
            font-size: 18px; /* Ajuster la taille de la police des titres */
            color: #333; /* Couleur des titres */
        }
        table {
            width: 70%; /* Diminuer la largeur du tableau */
            margin: 20px auto; /* Centrer le tableau */
            border-collapse: collapse;
            margin-top: 20px;
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
    <h3>Liste des Pilotes</h3>
    <form method="post">
        Filtrer par: <input type="text" name="mot">
        <input type="submit" name="Filtrer" value="Filtrer">
    </form>
    <br/>
    <table border="1">
        <tr>
            <th>Id Pilote</th>
            <th>Nom</th>
            <th>Prénom</th>
            <th>Email</th>
            <th>Adresse</th>
            <th>Nb Heures Vol</th>
            <th>Statut</th>
            <?php
            if(isset($_SESSION['role']) && $_SESSION['role'] == "admin"){
                echo"<th>Opérations</th>";
            }
            ?>
        </tr>

        <?php
        if (isset($lesPilotes)){
            foreach ($lesPilotes as $unPilote) {
                echo "<tr>";
                echo "<td>".$unPilote['idpilote']."</td>";
                echo "<td>".$unPilote['nom']."</td>";
                echo "<td>".$unPilote['prenom']."</td>";
                echo "<td>".$unPilote['email']."</td>";
                echo "<td>".$unPilote['adresse']."</td>";
                echo "<td>".$unPilote['nbheuresvol']."</td>";
                echo "<td>".$unPilote['statut']."</td>";
                
                if(isset($_SESSION['role']) && $_SESSION['role'] == "admin"){
                    echo "<td> <a href='index.php?page=2&action=sup&idPilote=".$unPilote['idpilote']."'><img src='images/supprimer.jpeg' height='40' width='40'></a>";
                    echo "<a href='index.php?page=2&action=edit&idPilote=".$unPilote['idpilote']."'><img src='images/editer.jpeg' height='40' width='40'></a>";
                }
                echo "</tr>";
            }
        }
        ?>
        
    </table>
</body>
</html>
