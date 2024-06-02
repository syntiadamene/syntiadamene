package controleur;

public class Externe extends Pilote
{
	private int idcompagnie; 
	private String dateDebut, dateFin ;
	
	public Externe(int idpilote, String nom, String prenom, String email, String mdp, String adresse, String statut,
			float nbheuresvol, int idcompagnie, String dateDebut, String dateFin) {
		super(idpilote, nom, prenom, email, mdp, adresse, statut, nbheuresvol);
		this.idcompagnie = idcompagnie;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}
	
	public Externe( String nom, String prenom, String email, String mdp, String adresse, String statut,
			float nbheuresvol, int idcompagnie, String dateDebut, String dateFin) {
		super( nom, prenom, email, mdp, adresse, statut, nbheuresvol);
		this.idcompagnie = idcompagnie;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}

	public int getIdcompagnie() {
		return idcompagnie;
	}

	public void setIdcompagnie(int idcompagnie) {
		this.idcompagnie = idcompagnie;
	}

	public String getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}
	

}
