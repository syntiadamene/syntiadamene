package controleur;

import java.sql.Date;
import java.sql.Time;

public class Vol {
    private int idVol;
    private String numeroVol;
    private String dateDepart;
    private String heureDepart;
    private String heureArrivee;
    private String villeDepart;
    private String villeArrivee;
    private String classeVol;
    private int idPilote;
    private int idAvion;

    public Vol(String numeroVol, String dateDepart, String heureDepart, String heureArrivee, String villeDepart, String villeArrivee, String classeVol, int idPilote, int idAvion) {
        this.numeroVol = numeroVol;
        this.dateDepart = dateDepart;
        this.heureDepart = heureDepart;
        this.heureArrivee = heureArrivee;
        this.villeDepart = villeDepart;
        this.villeArrivee = villeArrivee;
        this.classeVol = classeVol;
        this.idPilote = idPilote;
        this.idAvion = idAvion;
    }

    public Vol(int idVol, String numeroVol, String dateDepart, String heureDepart, String heureArrivee, String villeDepart, String villeArrivee, String classeVol, int idPilote, int idAvion) {
        this.idVol = idVol;
        this.numeroVol = numeroVol;
        this.dateDepart = dateDepart;
        this.heureDepart = heureDepart;
        this.heureArrivee = heureArrivee;
        this.villeDepart = villeDepart;
        this.villeArrivee = villeArrivee;
        this.classeVol = classeVol;
        this.idPilote = idPilote;
        this.idAvion = idAvion;
    }

    public int getIdVol() {
        return idVol;
    }

    public void setIdVol(int idVol) {
        this.idVol = idVol;
    }

    public String getNumeroVol() {
        return numeroVol;
    }

    public void setNumeroVol(String numeroVol) {
        this.numeroVol = numeroVol;
    }

    public String getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(String dateDepart) {
        this.dateDepart = dateDepart;
    }

    public String getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(String heureDepart) {
        this.heureDepart = heureDepart;
    }

    public String getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee(String heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    public String getVilleDepart() {
        return villeDepart;
    }

    public void setVilleDepart(String villeDepart) {
        this.villeDepart = villeDepart;
    }

    public String getVilleArrivee() {
        return villeArrivee;
    }

    public void setVilleArrivee(String villeArrivee) {
        this.villeArrivee = villeArrivee;
    }

    public String getClasseVol() {
        return classeVol;
    }

    public void setClasseVol(String classeVol) {
        this.classeVol = classeVol;
    }

    public int getIdPilote() {
        return idPilote;
    }

    public void setIdPilote(int idPilote) {
        this.idPilote = idPilote;
    }

    public int getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(int idAvion) {
        this.idAvion = idAvion;
    }

    @Override
    public String toString() {
        return "Vol{" +
                "idVol=" + idVol +
                ", numeroVol='" + numeroVol + '\'' +
                ", dateDepart=" + dateDepart +
                ", heureDepart=" + heureDepart +
                ", heureArrivee=" + heureArrivee +
                ", villeDepart='" + villeDepart + '\'' +
                ", villeArrivee='" + villeArrivee + '\'' +
                ", classeVol='" + classeVol + '\'' +
                ", idPilote=" + idPilote +
                ", idAvion=" + idAvion +
                '}';
    }
}
