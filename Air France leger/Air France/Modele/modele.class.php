<?php
	/*
		classe qui gère la connexion au SGBD Mysql et l'ensemble 
		des requêtes injection et extraction des données
	*/
	class Modele {
		private $unPDO; //objet de la classe PDO : PHP DATA OBJECT
		//une table generique
		private $table;

		public function __construct ($serveur,$bdd,$user,$mdp){
			$this->table="";
			try{
			$url="mysql:host=".$serveur.";dbname=".$bdd;
			$this->unPDO = new PDO($url,$user,$mdp);
			}
			catch(PDOException $exp){
				echo "Erreur Connexion :".$exp->getMessage (); 
			}
		}
		//creer un getter pour savoir sur quelle table je suis
		public function getTable(){
			return $this->table;
		}
		public function setTable($table){
			$this->table=$table;
		}
		/************ Gestion des promotions ***********/
	
	    public function insert($tab){
            $champs = array();
            $donnees = array();
            foreach($tab as $cle => $valeur){
                $champs[] = $cle;
                $donnees[':'.$cle] = $valeur;
           }
           $chaineChamps = implode(", ", $champs);
           $chaineParams = implode(", ", array_keys($donnees));

           // Création de la requête en spécifiant les colonnes
           $requete = "INSERT INTO ".$this->table." (".$chaineChamps.") VALUES (".$chaineParams.");";

           $insert = $this->unPDO->prepare($requete);
           $insert->execute($donnees);
        }


		public function selectAll (){
			$requete ="select * from  ".$this->table.";";
			$select = $this->unPDO->prepare($requete); 
			$select->execute(); 
			return $select->fetchAll(); 
		}
		public function delete ($tab){
			$champs=array();
			$donnees=array(); 
			foreach ($tab as $cle=>$valeur){
				$champs[] = $cle."=:".$cle;
				$donnees[':'.$cle] =$valeur;

			}
			$chaine=implode(" and ",$champs);
			$requete="delete from  ".$this->table."  where ".$chaine.";";

			
			$delete = $this->unPDO->prepare($requete); 
			$delete->execute ($donnees); 
		}
		//$wherer est pour condation 
		public function update ($tab, $where){
			$champs = array();
			$donnees=array();
			foreach ($tab as $cle => $valeur) {
$champs[] = $cle."= :".$cle;
$donnees[':'.$cle]=$valeur;
			}
			$chaine= implode(",", $champs);



			$champsWhere = array();
			
			foreach ($where as $cle => $valeur) {
$champsWhere[] = $cle."= :".$cle;
$donnees[':'.$cle]=$valeur;
			}
			$chaineWhere= implode(" and ", $champsWhere);


			$requete ="update ".$this->table." set ".$chaine." where ".$chaineWhere.";";
	
				
			$update = $this->unPDO->prepare($requete); 
			$update->execute ($donnees); 
		}
		
	public function selectWhere($where) {
    // Vérifiez ici le contenu de $where

    $champsWhere = array();
    $donnees = array(); // Initialisez le tableau des données à utiliser dans la requête
    foreach ($where as $cle => $valeur) {
        $champsWhere[] = $cle . "= :" . $cle;
        $donnees[':' . $cle] = $valeur;
    }
    $chaineWhere = implode(" AND ", $champsWhere);
  
   $requete = "SELECT * FROM " . $this->table . " WHERE " . $chaineWhere . ";";
 

$select = $this->unPDO->prepare($requete);
$select->execute($donnees);
return $select->fetchAll();

}

		public function selectLike($where, $mot) {
			$champsWhere=array();
			foreach($where as $cle){
				$champsWhere[]=$cle." like :mot ";
				
			}
			$chaineWhere=implode(" or ", $champsWhere);
			$requete ="select * from ".$this->table." where ".$chaineWhere." ;";
			$donnees=array(":mot"=>"%".$mot."%");
			$select = $this->unPDO->prepare($requete); 
			$select->execute($donnees); 
			return $select->fetchAll();  

		}
		public function count(){
			$requete="select count(*) as nb from ".$this->table.";";
			$select=$this->unPDO->prepare($requete);
			$select->execute();
			return $select->fetch()['nb'];
		}

public function callProcedure($nomProc, $tab) {
    // Création de la chaîne des paramètres à insérer dans la requête
    $paramNames = array_keys($tab);
    $paramPlaceholders = implode(', ', array_map(function ($param) {
        return ':' . $param;
    }, $paramNames));

    // Création de la chaîne pour les paramètres liés dans la requête
    $paramBindings = implode(', ', array_map(function ($param) {
        return $param . '=:' . $param;
    }, $paramNames));

    $requete = "CALL $nomProc($paramPlaceholders);";

    $select = $this->unPDO->prepare($requete);

    // Liaison des valeurs du tableau avec les paramètres liés
    foreach ($tab as $key => $value) {
        $select->bindValue(':' . $key, $value);
    }

    $select->execute();
}






		/************ Connexion **************************/
		public function verifConnexionInjection ($email, $mdp)
		{
			$requete ="select * from user where email='".$email."' and mdp='".$mdp."';";

			//requete non sécurisée : injection sql : ' or 1=1 ; 
		
			$select = $this->unPDO->prepare($requete); 
			$select->execute (); 
			return $select->fetch (); 
		}

		public function verifConnexion ($email, $mdp)
		{
			$requete ="select * from user where email= :email and mdp= :mdp ;";
			$donnees=array(":email"=>$email, ":mdp"=>$mdp); 
			$select = $this->unPDO->prepare($requete); 
			$select->execute ($donnees); 
			return $select->fetch (); 
		}

		public function verifConnexionEmail ($email)
		{
			$requete ="select * from user where email= :email;";
			$donnees=array(":email"=>$email); 
			$select = $this->unPDO->prepare($requete); 
			$select->execute ($donnees); 
			return $select->fetch (); 
		}

		public function selectGrainSel ()
		{
			$requete = "select * from grainsel ; ";
			$select = $this->unPDO->prepare($requete); 
			$select->execute (); 
			return $select->fetch (); 
		}

		public function updateMdp ($iduser, $mdp)
		{
			$requete ="update user set mdp = :mdp where iduser = :iduser ; ";
			$donnees=array(":mdp"=> $mdp, 
						   ":iduser"=>$iduser);
			$update = $this->unPDO->prepare($requete); 
			$update->execute ($donnees); 
		}

	} 

?>