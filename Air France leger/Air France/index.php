<?php
	session_start(); 
	require_once("controleur/config_bdd.php"); 
	require_once("controleur/controleur.class.php"); 
	//instanciation de la classe Controleur 
	$unControleur = new Controleur($serveur, $bdd,$user,$mdp); 
?>
<!DOCTYPE html>
<html lang="fr">
<head>

	<title>Air France</title>
      <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

	<link rel="stylesheet" href="css/style.css">

</head>
<body>
<center>
	<div class="header">
        
    </div>
    
	<!--<h1> Gestion du site AIR FRANCE </h1>-->
	<div class="main-content">
	<?php

		if( ! isset($_SESSION['email'])){
			require_once("vue/vue_connexion.php"); 
		}

		if (isset($_POST['confirmer']))
		{
			$mdp = $_POST['newMdp']; 
			$mdp2 = $_POST['newMdp2'];
			if ($mdp == $mdp2){
				$iduser = $_SESSION['iduser']; 
				//hashage du mdp 
				$nb = $unControleur->selectGrainSel()['nb'];
				$mdp = $mdp.$nb ;
				$mdp = sha1($mdp);
				//**6666666666666666666666666666 dans la base 
				$unControleur->updateMdp($iduser, $mdp); 
			} 
		}

		if(isset($_POST['seConnecter']))
		{
			$email = $_POST['email']; 
			$mdp = $_POST['mdp']; 

			//hashage en MD5 
			//$mdp = md5($mdp); 


			//hashage en sha1 
			//$mdp = sha1($mdp);

			//grain de sel 
			$nb = $unControleur->selectGrainSel()['nb'];
			$mdp = $mdp.$nb ;
			$mdp = sha1($mdp);  

			//$unUser = $unControleur->verifConnexion ($email, $mdp); 

			$unUser = $unControleur->verifConnexionEmail ($email); 

			if ($unUser==null){
				echo "<br/>Veuillez vérifier vos identifiants </br>";
			}else {

				//on vérifie le mdp avec le hashage 
				if ($mdp != $unUser['mdp']) {
					
					if( ! isset($_SESSION['nbTentatives'])){
						$_SESSION['nbTentatives'] = 1; 
						 
					}else {

						$_SESSION['nbTentatives'] = $_SESSION['nbTentatives'] +1; 
						 
						if($_SESSION['nbTentatives'] >=3)
						{
							//var_dump($_SESSION);
							echo "trop de tentatives - mise en attente.";
							//bloquer le site en attente 
							sleep(10); 
							echo "Renouvelez votre connexion"; 
							// remettre le nbTentatives à 0. 
							$_SESSION['nbTentatives'] = 0;
						}
					}
				}
				else {
				//vérifier si l'utilisateur doit modifier ou non son mdp avant de passer au header. 

				$dtBdd = $unUser['dateMdp'];
				$dtJour = date("Y-m-d"); 
				$objetBdd = new DateTimeImmutable($dtBdd);
				$objetJour = new DateTimeImmutable($dtJour);

				$interval = $objetBdd->diff($objetJour);
				$nbJours = $interval->format('%R%a');
				if ($nbJours >= 30 ){
					//on re initialise le mdp 
					require_once("Vue/vue_name_mdp.php");
				}
				$_SESSION['iduser'] = $unUser['iduser']; 
				$_SESSION['email'] = $unUser['email']; 
				$_SESSION['nom'] = $unUser['nom']; 
				$_SESSION['prenom'] = $unUser['prenom']; 
				$_SESSION['role'] = $unUser['role'];
				//header("Location:index.php?page=0");  

				}//fin du else MDP 
			}
		}
	if (isset($_SESSION['email'])) {
	echo '
	<header>

    <div id="logo">
        <img src="images/logo.jpeg" height="120" width="120">
    </div>
    <div id="header" class="center-buttons">
        <div class="landing-page">
            <nav class="nav">
                <a href="index.php?page=0">
                    <div class="circle">
                        <img src="images/home.jpeg" height="80" width="80">
                    </div>
                    <br> Page Accueil </br>
                </a>
                <a href="index.php?page=1">
                    <div class="circle">
                        <img src="images/aeroport.jpeg" height="80" width="80">
                    </div>
                    <br> Les Aeroports </br>
                </a>
                <a href="index.php?page=2">
                    <div class="circle">
                        <img src="images/pilote.jpeg" height="80" width="80">
                    </div>
                    <br> Les Pilotes </br>
                </a>
                <a href="index.php?page=3">
                    <div class="circle">
                        <img src="images/avion.jpeg" height="80" width="80">
                    </div>
                    <br> Les Avions </br>
                </a>
                <a href="index.php?page=4">
                    <div class="circle">
                        <img src="images/vol.jpeg" height="80" width="80">
                    </div>
                    <br> Les Vols </br>
                </a>
                 <a href="index.php?page=5">
                    <div class="circle">
                        <img src="images/compagnie.png" height="80" width="80">
                    </div>
                    <br> Les Compagnies </br>
                </a>
                <a href="index.php?page=6">
                    <div class="circle">
                        <img src="images/deconnexion.jpeg" height="80" width="80">
                    </div>
                    <br> Déconnexion </br>
                </a>
            </nav>
        </div>
    </div>
</header>

    ';

	
	if (isset($_GET['page'])){
		$page = $_GET['page'];
	}else {
		$page = 0; 
	}
	switch ($page){
		case 0 : require_once("home.php"); break;
		case 1 : require_once("aeroports.php"); break;
		case 2 : require_once("pilotes.php"); break;
		case 3 : require_once("avions.php"); break;
		case 4 : require_once("vols.php"); break;
		case 5 : require_once("compagnies.php"); break;

		 case 6 : session_destroy();
            header("location: index.php?page=0"); break;
            default : require_once("vue/vue_erreur.php"); break;
             case 7:
            require_once("Politique.php");
            break;
            case 8 :
            require_once("informations.php");
            break;
            case 9: 
            require_once("contacts.php");
            break;
       }
        }else {
    require_once("vue/vue_connexion.php");


	}
	if (isset($_SESSION['email'])) {
		echo '
	<footer id="footer-wrapper">
		<ul>
			<nav class="footer">
				<li class="btn"><a href="index.php?page=7"> <p> Politique de Confidentialité </p></a></li>
				<li class="btn"><a href="index.php?page=8"> <p> Informations d\'accessibilités </p></a></li>
				<li class="btn"><a href="index.php?page=9"> <p> Contacts </p></a></li>
			</nav>
		</ul>
	</footer>'; 
	}
	?>
</body>
</html>





