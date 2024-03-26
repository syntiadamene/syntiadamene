package controleur;

public class Aeroport {
	private int idaeroport;
	private String nom, code,ville,pays,statut;
	
	public  Aeroport (int idaeroport, String nom, String code,String ville,String pays,String statut) {
		super();
		this.idaeroport = idaeroport;
		this.nom = nom;
		this.code = code;
		this.ville =ville;
		this.pays = pays;
		this.statut =statut;
		
	}
	public  Aeroport( String nom, String code,String ville,String pays,String statut) {
		super();
		this.idaeroport   = 0;
		this.nom = nom;
		this.code = code;
		this.ville =ville;
		this.pays = pays;
		this.statut =statut;
	}
	public int getIdaeroport() {
		return idaeroport;
	}
	public void setIdaeroport(int idaeroport) {
		this.idaeroport = idaeroport;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	

}
