package controleur;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.Modele;
 

public class Controleur {
    public static Pilote selectWherePilote(String email, String mdp) {
        // Contrôler l'email
        
        // Contrôler la complexité du mot de passe
        return Modele.selectWherePilote(email, mdp);
    }

    public static int getLastInsertedAvionId() {
      return Modele.getLastInsertedAvionId();
    }
    
    public static int getLastInsertedAeroportId() {
        return Modele.getLastInsertedAeroportId();
      }
    
    public static void updatePilote(Pilote unPilote) {
        Modele.updatePilote(unPilote);
    }

    public static void deletePilote(int idPilote) {
        Modele.deletePilote(idPilote);
    }

    public static void insertExterne(Externe unPilote) {
        Modele.insertExterne(unPilote);
    }

    public static ArrayList<Pilote> selectAllPilotes(String filtre) {
        return Modele.selectAllPilotes(filtre);
    }

    public static void insertAvion(Avion unAvion) {
        Modele.insertAvion(unAvion);
    }

    public static Avion selectWhereAvion(int idAvion) {
        return Modele.selectWhereAvion(idAvion);
    }

    public static void deleteAvion(int idAvion) {
        Modele.deleteAvion(idAvion);
    }

    public static void updateAvion(Avion unAvion) {
        Modele.updateAvion(unAvion);
    }

    public static ArrayList<Avion> selectAllAvions(String filtre) {
        return Modele.selectAllAvions(filtre);
    }
    
    public static void insertAeroport(Aeroport unAeroport) {
        Modele.insertAeroport(unAeroport);
    }

    public static Aeroport selectWhereAeroport(int idAeroport) {
        return Modele.selectWhereAeroport(idAeroport);
    }

    public static void deleteAeroport(int idAeroport) {
        Modele.deleteAeroport(idAeroport);
    }

    public static void updateAeroport(Aeroport unAeroport) {
        Modele.updateAeroport(unAeroport);
    }

    public static ArrayList<Aeroport> selectAllAeroports(String filtre) {
        return Modele.selectAllAeroports(filtre);
    }
    
    public static void insertVol(Vol unVol) {
        Modele.insertVol(unVol);
    }

    public static int getLastInsertedVolId() {
        return Modele.getLastInsertedVolId();
    }

    public static Vol selectWhereVol(int idVol) {
        return Modele.selectWhereVol(idVol);
    }

    public static void deleteVol(int idVol) {
        Modele.deleteVol(idVol);
    }

    public static void updateVol(Vol unVol) {
        Modele.updateVol(unVol);
    }

    public static ArrayList<Vol> selectAllVols(String filtre) {
        return Modele.selectAllVols(filtre);
    }

	public static User selectWhereUser(String email, String mdp) {
		 
		return Modele.selectWhereUser(email, mdp);
	}

	public static void updateUser(User unUser) {
		Modele.updateUser (unUser);
		
	}

	public static void insertInterne(Interne interne) {
		Modele.insertInterne(interne);
		
	}

	public static int getLastInsertedCompagnieId() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static void updateCompagnie(Compagnie uneCompagnie) {
		Modele.updateCompagnie(uneCompagnie);
		// TODO Auto-generated method stub
		
	}

	public static ArrayList<Compagnie> selectAllCompagnies(String filtre) {
		// TODO Auto-generated method stub
	
		return Modele.selectAllCompagnies(filtre);
	}

	public static void insertCompagnie(Compagnie uneCompagnie) {
		// TODO Auto-generated method stub
		Modele.insertCompagnie(uneCompagnie);
		
	}

	public static void deleteCompagnie(int idCompagnie) {
		// TODO Auto-generated method stub
		Modele.deleteCompagnie(idCompagnie);
		
	}
	public static ArrayList<ViewPilote> selectAllViewPilotes() {
		// TODO Auto-generated method stub
		return  Modele.selectAllViewPilotes();
	}


}
