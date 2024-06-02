<h3>Liste des avions</h3>

<table border="1">
    <tr>
         <td>Id  Avion</td>
       
       
        <td>constructeur</td>
        <td>modéle</td>
        <td>capacité</td>
        <td>photo</td>
        <td>ID aeroport</td>
      
    </tr>
    <?php
    if (isset($lesAvions)) {
        foreach ($lesAvions as $unAvion) {
            echo "<tr>";
            echo "<td>" . $unAvion['idavion'] . "</td>";
            
            
            echo "<td>" . $unAvion['constructeur'] . "</td>";
            echo "<td>" . $unAvion['modele'] . "</td>";
            echo "<td>" . $unAvion['capacite'] . "</td>";
            echo "<td>" . $unAvion['photo'] . "</td>";
            echo "<td>" . $unAvion['idaeroport'] . "</td>";

           
           
            echo "</tr>";
        }
    }
    ?>
</table>