package controleur;

public class Interne extends Pilote 
{
	private float salaire ; 
	private String dateEntree ;
	public Interne(int idpilote, String nom, String prenom, String email, String mdp, String adresse, String statut,
			float nbheuresvol, float salaire, String dateEntree) {
		super(idpilote, nom, prenom, email, mdp, adresse, statut, nbheuresvol);
		this.salaire = salaire;
		this.dateEntree = dateEntree;
	} 
	public Interne( String nom, String prenom, String email, String mdp, String adresse, String statut,
			float nbheuresvol, float salaire, String dateEntree) {
		super(nom, prenom, email, mdp, adresse, statut, nbheuresvol);
		this.salaire = salaire;
		this.dateEntree = dateEntree;
	}
	public float getSalaire() {
		return salaire;
	}
	public void setSalaire(float salaire) {
		this.salaire = salaire;
	}
	public String getDateEntree() {
		return dateEntree;
	}
	public void setDateEntree(String dateEntree) {
		this.dateEntree = dateEntree;
	} 
	
	
	
}
