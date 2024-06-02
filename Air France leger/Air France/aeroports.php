<!DOCTYPE html>
<html>
<head>
    <title>Gestion des aéroports</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <h3>Gestion des aéroports</h3>
    <br/>
<?php
$unControleur->setTable("aeroport");

    $lesAvions = null;
    if(isset($_SESSION['role'])&&$_SESSION['role']=="admin"){
        $leAeroport=null;

    if (isset($_GET['action']) && isset($_GET['idAeroport'])) {
        $action = $_GET['action'];
        $idAeroport = $_GET['idAeroport'];
         $where=array("idaeroport"=>$idAeroport);

        switch ($action) {
            case "sup":
            $tab =array ("idAeroport"=>$idAeroport) ;
           
            $unControleur->delete($tab);
                break;
           case "edit":
    $leAeroport = $unControleur->selectWhere($where);

    // Ajoutez ces lignes pour vérifier les données récupérées


    break;

             case "view":
           
            $unControleur->setTable ("avion");
                $where =array ("idAeroport"=>$idAeroport) ;
                 $lesAvions = $unControleur->selectWhere ($where); 

            
                

                break;
        }
    }
$unControleur->setTable("aeroport");
    require_once("vue/vue_insert_aeroport.php");

    if (isset($_POST['Modifier'])) {
        $tab=array(

"nom"=>$_POST['nom'],

"code"=>$_POST['code'],

"ville"=>$_POST['ville'],

"pays"=>$_POST['pays'],

"statut"=>$_POST['statut']


        );
         $where=array("idAeroport"=>$_POST ['idaeroport']);

        $unControleur->update($tab, $where);
        echo "</br> Modification réussie de l'aéroport <br/>";
    }

    if (isset($_POST['Valider'])) {
         $tab=array(

"nom"=>$_POST['nom'],

"code"=>$_POST['code'],

"ville"=>$_POST['ville'],

"pays"=>$_POST['pays'],

"statut"=>$_POST['statut']


        );
        $unControleur->insert($tab);
        echo "</br> Insertion réussie de l'aéroport </br>";
    }
}

    if (isset($_POST['Filtrer'])) {
        $mot = $_POST['mot'];
        $where=array("nom", "code", "ville" , "pays" , "statut");
        $lesAeroports = $unControleur->selectLike($where , $mot);
    } else {
        $lesAeroports = $unControleur->selectAll();
    }

    require_once("vue/vue_les_aeroports.php");
    echo"<br>les aeroports sont :".$unControleur->count();
    echo "<br/>";
    if (isset($_GET['action']) && $_GET['action'] == "view") {
       if ($lesAvions == null) {
            echo "<br/> Cette aeroport n'a pas d'avions  ";

      }else{
        
require_once("vue/vue_les_avions_aeroport.php");


      }
   }

    
    
?>
   <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
