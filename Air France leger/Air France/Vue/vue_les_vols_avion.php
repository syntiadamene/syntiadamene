<h3>Liste des Vols</h3>

<table border="1">
    <tr>
        <td>Id Vol</td>
        <td>Numero vol</td>
        <td>Date Depart</td>
        <td>Heure Depart</td>
        <td>Heure Arrivee</td>
       
        <td>Ville Depart</td>
        <td>Ville Arrivee</td>
        <td>ID Pilote</td>
        <td>ID Avion</td>
    </tr>
    <?php
    if (isset($lesAvions)) {
        foreach ($lesVols as $unVol) {
            echo "<tr>";
            echo "<td>" . $unVol['idavion'] . "</td>";
            echo "<td>" . $unVol['idvol'] . "</td>";
            echo "<td>" . $unVol['numero_vol'] . "</td>";
            echo "<td>" . $unVol['date_depart'] . "</td>";
            echo "<td>" . $unVol['heure_depart'] . "</td>";
            echo "<td>" . $unVol['heure_arrivee'] . "</td>";
       
            echo "<td>" . $unVol['ville_depart'] . "</td>";
            echo "<td>" . $unVol['ville_arrivee'] . "</td>";
            echo "<td>" . $unVol['idpilote'] . "</td>";
            
            echo "</tr>";
        }
    }
    ?>
</table>
