<!DOCTYPE html>
<html>
<head>
    <title>Gestion des avions</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <h2>Gestion des avions</h2>
    <br/>
<?php
   
    $lesVols = null;

    $unControleur->setTable("aeroport");
    $lesAeroports=$unControleur->selectAll();

    $unControleur->setTable("avion");
    
    if(isset($_SESSION['role'])&&$_SESSION['role']=="admin"){
        $leAvion=null;
    if (isset($_GET['action']) && isset($_GET['idAvion'])) {
     	$action=$_GET['action'];
     	$idAvion=$_GET['idAvion'];
         $where=array("idavion"=>$idAvion);
     	switch ($action){
     		 case "sup":
             $tab =array("idAvion"=>$idAvion) ;
           
            $unControleur->delete($tab);
                break;
     		  case "edit":
    $leAvion = $unControleur->selectWhere($where);

    // Ajoutez ces lignes pour vérifier les données récupérées
    
    break;
                         
            case "view":
           
            $unControleur->setTable ("vol");
                $where =array ("idAvion"=>$idAvion) ;
                 $lesVols = $unControleur->selectWhere ($where); 

            
                

                break;
      	}
    }
    $unControleur->setTable("avion");
    require_once("vue/vue_insert_avion.php");
    if (isset($_POST['Modifier'])) {


         $tab=array(

"constructeur"=>$_POST['constructeur'],

"modele"=>$_POST['modele'],

"capacite"=>$_POST['capacite'],

"idaeroport"=>$_POST['idaeroport'],

"photo"=>$_POST['photo']


        );
        $where=array("idAvion"=>$_POST ['idavion']);
 $unControleur->setTable("avion");
        $unControleur->update($tab, $where);

    	echo "<br> Modification réusseie de l'avion <br>";
    }
    if (isset($_POST['Valider'])) {


         $tab=array(

"constructeur"=>$_POST['constructeur'],

"modele"=>$_POST['modele'],

"capacite"=>$_POST['capacite'],
"idaeroport"=>$_POST['idaeroport'],
"photo"=>$_POST['photo']


        );
    	$unControleur->insert($tab);
    	echo "<br> Insertion reussie de l'avion <br>";

    }
}
    if(isset($_POST['Filtrer']))
        {
        
            $mot = $_POST['mot'];
            $where=array("constructeur", "modele", "capacite" , "idaeroport" , "photo");
            $lesAvions=$unControleur->selectLike($where , $mot);
        }
        else{
        	$lesAvions = $unControleur->selectAll();
        }
         require_once("vue/vue_les_avions.php");

   
    echo"<br>les avions sont :".$unControleur->count();
    echo "<br/>";
     if (isset($_GET['action']) && $_GET['action'] == "view") {
        if ($lesVols == null) {
            echo "<br/> Cette avion n'a pas de vol ";

        }else{

 require_once("vue/vue_les_vols_avion.php");

        }
    }

    
?>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

