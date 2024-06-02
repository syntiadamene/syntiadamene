package controleur;

public class Avion {
	private int idavion,idaeroport , capacite;
	private String constructeur, modele,photo;
	
	public Avion(int idavion, String constructeur, String modele, int capacite,String photo,int idaeroport) {
		super();
		this.idavion = idavion;
		this.constructeur = constructeur;
		this.modele = modele;
		this.capacite =capacite;
		this.photo = photo;
		this.idaeroport =idaeroport;
		
	}
	public Avion (String constructeur, String modele, int capacite,String photo,int idaeroport) {
		super();
		this.idavion  = 0;
		this.constructeur = constructeur;
		this.modele = modele;
		this.capacite =capacite;
		this.photo = photo;
		this.idaeroport =idaeroport;
	}
	
	public int getIdavion() {
		return idavion;
	}
	public void setIdavion(int idavion) {
		this.idavion = idavion;
	}
	public int getIdaeroport() {
		return idaeroport;
	}
	public void setIdaeroport(int idaeroport) {
		this.idaeroport = idaeroport;
	}
	public int getCapacite() {
		return capacite;
	}
	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}
	public String getConstructeur() {
		return constructeur;
	}
	public void setConstructeur(String constructeur) {
		this.constructeur = constructeur;
	}
	public String getModele() {
		return modele;
	}
	public void setModele(String modele) {
		this.modele = modele;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	

}