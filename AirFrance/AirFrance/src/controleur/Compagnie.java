package controleur;

public class Compagnie {
	private int idcompagnie;
	private String libelle, pays;
	public Compagnie(int idcompagnie, String libelle, String pays) {
		super();
		this.idcompagnie = idcompagnie;
		this.libelle = libelle;
		this.pays = pays;
	}
	
	public Compagnie ( String libelle ,String pays) {
		super();
		this.idcompagnie   = 0;
		this.libelle = libelle;
		this.pays = pays;
	}

	public int getIdcompagnie() {
		return idcompagnie;
	}

	public void setIdcompagnie(int idcompagnie) {
		this.idcompagnie = idcompagnie;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}
	
	
	

}
