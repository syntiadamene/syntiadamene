<h2>Gestion des Pilotes</h2>

<?php
$lesVols = null;
// require_once("vue/vue_insert_pilote.php");
$lesCompagnies=null;
//$lesCompagnies=$unControleur->selectAll();
$unControleur->setTable("pilote");

if (isset($_SESSION['role']) && $_SESSION['role'] == "admin") {
    $lePilote = null;
   

    if (isset($_GET['action']) && isset($_GET['idPilote'])) {
        $action = $_GET['action'];
        $idPilote = $_GET['idPilote'];
        $where = array("idpilote" => $idPilote);
       switch ($action) {
           case "sup":
               $tab = array("idPilote" => $idPilote);
                $unControleur->delete($tab);
                break;
            case "edit":
                $lePilote = $unControleur->selectWhere($where);
               
                break;
      }
    }

    $unControleur->setTable("pilote");
    require_once("vue/vue_insert_pilote.php");

    if (isset($_POST['Modifier'])) {
        $tab = array(
            "nom" => $_POST['nom'],
            "prenom" => $_POST['prenom'],
            "email" => $_POST['email'],
            "mdp" => $_POST['mdp'],
            "adresse" => $_POST['adresse'],
            "nbheuresvol" => $_POST['nbheuresvol'],
            "statut" => $_POST['statut']
        );

        if ($_POST['statut'] === "PiloteExterne") {
            $tab["idcompagnie"] = $_POST['idcompagnie'];
            $tab["date_debut"] = $_POST['date_debut'];
            $tab["date_fin"] = $_POST['date_fin'];
            $unControleur->callProcedure("insererPiloteExterne", $tab);
        } else {
            $tab["salaire"] = $_POST['salaire'];
            $tab["date_entree"] = $_POST['date_entree'];
            $unControleur->callProcedure("insererPiloteInterne", $tab);
        }

        $where = array("idpilote" => $_POST['idpilote']);
        $unControleur->setTable("pilote");
        $unControleur->update($tab, $where);
        echo "</br> Modification réussie du pilote </br>";
    }

    if (isset($_POST['Valider'])) {
        $tab = array(
            "nom" => $_POST['nom'],
            "prenom" => $_POST['prenom'],
            "email" => $_POST['email'],
           
            "adresse" => $_POST['adresse'],
            "nbheuresvol" => $_POST['nbheuresvol'],
            "statut" => $_POST['statut']
        );

        if ($_POST['statut'] === "PiloteExterne") {
            $tab["idcompagnie"] = $_POST['idcompagnie'];
            $tab["date_debut"] = $_POST['date_debut'];
            $tab["date_fin"] = $_POST['date_fin'];
            $unControleur->callProcedure("insererPiloteExterne", $tab);
        } else {
            $tab["salaire"] = $_POST['salaire'];
            $tab["date_entree"] = $_POST['date_entree'];
            $unControleur->callProcedure("insererPiloteInterne", $tab);
        }
        echo "</br> Insertion réussie du pilote </br>";
    }
}

if (isset($_POST['Filtrer'])) {
    $mot = $_POST['mot'];
    $where = array("nom", "prenom", "email", "adresse", "nbheuresvol", "statut", "idcompagnie", "date_debut", "date_fin", "salaire", "date_entree");
    $lesPilotes = $unControleur->selectLike($where, $mot);
} else {
    $lesPilotes = $unControleur->selectAll();
}

require_once("vue/vue_les_pilotes.php");
echo "<br>Le nombre de pilote est : " . $unControleur->count();
echo "<br/>";
?>
