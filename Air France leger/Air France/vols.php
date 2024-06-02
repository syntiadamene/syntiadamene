
<h3> Gestion des vols</h3>

<br/>
<?php

    $unControleur->setTable("pilote");
    $lesPilotes = $unControleur->selectAll();

    $unControleur->setTable("avion");
    $lesAvions = $unControleur->selectAll();
    $unControleur->setTable("vol");
if (isset($_SESSION['role']) && $_SESSION['role'] == "admin") {
   
    $leVol = null;

    if (isset($_GET['action']) && isset($_GET['idVol'])) {
        $action = $_GET['action'];
        $idVol = $_GET['idVol'];
        $where = array("idvol" => $idVol);

        switch ($action) {
            case "sup":
                $unControleur->setTable("vol");
                $unControleur->delete($where);
                break;
            case "edit":
                $leVol = $unControleur->selectWhere($where);
                break;
            
        }
    }
$unControleur->setTable("vol");
   
    require_once("vue/vue_insert_vol.php");

    if (isset($_POST['Modifier'])) {
        $tab = array(
            "numero_vol" => $_POST['numero_vol'],
            "date_depart" => $_POST['date_depart'],
            "heure_depart" => $_POST['heure_depart'],
            "heure_arrivee" => $_POST['heure_arrivee'],
            "ville_depart" => $_POST['ville_depart'],
            "ville_arrivee" => $_POST['ville_arrivee'],
            "classe_vol" => $_POST['classe_vol'],
            "idpilote" => $_POST['idpilote'],
            "idavion" => $_POST['idavion']
        );
        $where = array("idvol" => $_POST['idvol']);
        $unControleur->setTable("vol");
        $unControleur->update($tab, $where);
        echo "</br> Modification réussie du vol </br>";
    }

    if (isset($_POST['Valider'])) {
        $tab = array(
            "numero_vol" => $_POST['numero_vol'],
            "date_depart" => $_POST['date_depart'],
            "heure_depart" => $_POST['heure_depart'],
            "heure_arrivee" => $_POST['heure_arrivee'],
            "ville_depart" => $_POST['ville_depart'],
            "ville_arrivee" => $_POST['ville_arrivee'],
            "classe_vol" => $_POST['classe_vol'],
            "idpilote" => $_POST['idpilote'],
            "idavion" => $_POST['idavion']
        );
        $unControleur->setTable("vol");
        $unControleur->insert($tab);
        echo "</br> Insertion réussie du vol </br>";
    }

    if (isset($_POST['Filtrer'])) {
        $mot = $_POST['mot'];
        $where = array(
            "numero_vol", "date_depart", "heure_depart", "heure_arrivee","ville_depart", "ville_arrivee", "classe_vol", "idpilote", "idavion"
        );
        $lesVols=$unControleur->selectLike($where , $mot);
        }
        else{
            $lesVols = $unControleur->selectAll();
        }
         require_once("vue/vue_les_vols.php");
     }