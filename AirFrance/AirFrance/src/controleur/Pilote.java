package controleur;

public class Pilote {
	protected int idpilote;
	protected String nom,prenom,email,mdp,adresse,statut;
	protected float nbheuresvol;
	public Pilote(int idpilote, String nom, String prenom, String email,String mdp ,String adresse,String statut,float nbheuresvol ) {
		super();
		this.idpilote = idpilote;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.mdp = mdp;
		this.adresse = adresse;
		this.statut = statut;
		this.nbheuresvol = nbheuresvol;
	}
	public Pilote(String nom, String prenom, String email,String mdp ,String adresse,String statut,float nbheuresvol) {
		super();
		this.idpilote = 0;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.mdp = mdp;
		this.adresse = adresse;
		this.statut = statut;
		this.nbheuresvol = nbheuresvol;
	}
	public int getIdpilote() {
		return idpilote;
	}
	public void setIdpilote(int idpilote) {
		this.idpilote = idpilote;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	public float getNbheuresvol() {
		return nbheuresvol;
	}
	public void setNbheuresvol(float nbheuresvol) {
		this.nbheuresvol = nbheuresvol;
	}
	
}
