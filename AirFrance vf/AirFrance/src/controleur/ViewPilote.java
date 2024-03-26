package controleur;

public class ViewPilote {
    private String nom, prenom;
    private int nombre_de_vols, totalHeures;

    public ViewPilote(String nom, String prenom, int nombre_de_vols, int totalHeures) {
        super();
        this.nom = nom;
        this.prenom = prenom;
        this.nombre_de_vols = nombre_de_vols;
        this.totalHeures = totalHeures;
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

    public int getNombre_de_vols() {
        return nombre_de_vols;
    }

    public void setNombre_de_vols(int nombre_de_vols) {
        this.nombre_de_vols = nombre_de_vols;
    }

    public int getTotalHeures() {
        return totalHeures;
    }

    public void setTotalHeures(int totalHeures) {
        this.totalHeures = totalHeures;
    }
}
