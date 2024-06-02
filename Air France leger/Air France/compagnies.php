<h3> Gestion des compagnies</h3>

<br/>
<?php
$unControleur->setTable("compagnie");
$lesPilotes = null;
$lesPilotes = $unControleur->selectAll();


if (isset($_SESSION['role']) && $_SESSION['role'] == "admin") {
    $laCompagnie = null;
 

    if (isset($_GET['action']) && isset($_GET['idcompagnie'])) {
    $action = $_GET['action'];
    $idCompagnie = $_GET['idcompagnie'];

    var_dump($action);
    var_dump($idCompagnie);

    $where = array("idcompagnie" => $idCompagnie);

    //var_dump($where);

      //  switch ($action) {
         //   case "sup":
              //  $tab = array("idCompagnie" => $idCompagnie);
           //     var_dump($tab); // Vérifiez que $tab est correctement formé
             //   $unControleur->delete($tab);
             //   break;
      //    case "edit":
  //  $laCompagnie = $unControleur->selectWhere($where);

   // break;
        }

    }
$unControleur->setTable("compagnie");
    require_once("vue/vue_insert_compagnie.php");

    if (isset($_POST['Modifier'])) {
        $tab=array(
            "libelle"=>$_POST['libelle'],

"pays"=>$_POST['pays']






        );
         $where=array("idCompagnie"=>$_POST ['idcompagnie']);
 $unControleur->setTable("compagnie");
        $unControleur->update($tab, $where);
        echo "</br> Modification réussie de la compagnie <br/>";
    }

    if (isset($_POST['Valider'])) {
           $tab=array(
            "libelle"=>$_POST['libelle'],

"pays"=>$_POST['pays']





        );
        $unControleur->insert($tab);
        echo "</br> Insertion réussie de la compagnie </br>";
    }


    if (isset($_POST['Filtrer'])) {
        $mot = $_POST['mot'];
        $where=array("libelle", "pays" );
        $lesCompagnies = $unControleur->selectLike($where , $mot);
    } else {
        $lesCompagnies = $unControleur->selectAll();
    }

    require_once("vue/vue_les_compagnies.php");
    echo"<br>les compagnies sont :".$unControleur->count();
    echo "<br/>";

    //if (isset($_GET['action']) && $_GET['action'] == "view") {
       // if ($lesPilotes == null) {
         //   echo "<br/> Cette compagnie  n'a pas de pilote ";
        

   //    // }
   // }
      
    
    
?>
