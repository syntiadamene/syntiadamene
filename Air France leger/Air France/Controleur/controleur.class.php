<?php
	require_once ("modele/modele.class.php"); 

	class Controleur {
		private $unModele ;

		public function __construct ($serveur,$bdd,$user,$mdp){
			//instanciation de la classe Modele 
			$this->unModele = new Modele($serveur,$bdd,$user,$mdp);
		}
		//ona ajoute cette nouvelle partie 


		public function setTable($table){
			$this->unModele->setTable($table);
		}

		public function getTable(){
			return $this->unModele->getTable();
		}
		/********************** Gestion de la promotion ***********/
		
		public function insert ($tab) {
			//controler les données avant insertion dans la table promotion 

			//on appelle la méthode du Modele 
			$this->unModele->insert($tab); 
		}
		public function selectAll() {
			
			
			return $this->unModele->selectAll();
		}
		public function selectLike($where,$mot) {
			return  $this->unModele->selectLike($where,$mot); 
			
		} 
		public function delete($tab)
		{
			$this->unModele->delete($tab);
		}

		public function update ($tab, $where)
		{
			$this->unModele->update($tab, $where);
		}


		public function count(){
			return $this->unModele->count();
		}

		public function selectWhere ($where)
		{
			return $this->unModele->selectWhere($where);
		}
        public function callProcedure($nomProc, $tab){
        	$this->unModele->callProcedure($nomProc, $tab);
        }
        public function verifConnexion ($email, $mdp)
		{
			//ici : on controle la validité d'un email et d'un mdp

			return $this->unModele->verifConnexion($email, $mdp); 
		}

		public function verifConnexionEmail ($email)
		{
			
			return $this->unModele->verifConnexionEmail($email); 
		}

		public function selectGrainSel ()
		{
			return $this->unModele->selectGrainSel(); 
		}
		public function updateMdp ($iduser, $mdp)
		{
			$this->unModele->updateMdp ($iduser, $mdp); 
		}

	}

?>
