package modele;

import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

import controleur.Aeroport;
import controleur.Avion;
import controleur.Compagnie;
import controleur.Externe;
import controleur.Interne;
import controleur.Pilote;
import controleur.User;
import controleur.ViewPilote;
import controleur.Vol;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;



public class Modele {
	//static car la connexion estv unique 
	private static  Bdd uneBdd =new Bdd("localhost:3306","air_france","root","");
	
	 public static User selectWhereUser (String email,String mdp) {
			
			User unUser = null;
			String requete ="select * from user where email='" +email+ "' and mdp='"+mdp+"';";
			
			try {
				uneBdd.seConnecter();
				//Création d'un curseur pour éxecuter la requete
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				//execution de la requete et recuperation d'un résultat
				ResultSet unRes = unStat.executeQuery(requete);
				//s'il y a un resultat , on recupere les champs 
				if(unRes.next()) {
					unUser = new User(
							unRes.getInt("iduser"),
							unRes.getString("nom"),
							unRes.getString("prenom"),
							unRes.getString("email"),
							unRes.getString("mdp"),
							unRes.getString("role")
							
							);
				}
				unStat.close();
				uneBdd.seDeConnecter();
				
			}
			catch(SQLException exp) {
				System.out.println("Erreur de requete :" +requete);
				
			}
			return unUser;
			
		}
	 public static void updateUser(User unUser) {
			String requete = "update user set nom='"+unUser.getNom()
			+"',prenom='"+unUser.getPrenom()
			+"',email='"+unUser.getEmail()
			+"',mdp='"+unUser.getMdp()
			+"',role='"+unUser.getRole()
		 
			+"' where iduser = "+unUser.getIduser()+";";
			
			try {	
				uneBdd.seConnecter();
				//Création d'un curseur pour éxecuter la requete
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				unStat.execute(requete);
				unStat.close();
				uneBdd.seDeConnecter();
				
			}catch(SQLException exp) {
				System.out.println("Erreur de requete :" +requete);
				
			}
		

			
		}

	 
	/*********************Gestion des PILOTES**************/
    public static Pilote selectWherePilote (String email,String mdp) {
		
		Pilote unPilote = null;
		String requete ="select * from pilote where email='" +email+ "' and mdp='"+mdp+"';";
		
		try {
			uneBdd.seConnecter();
			//Création d'un curseur pour éxecuter la requete
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			//execution de la requete et recuperation d'un résultat
			ResultSet unRes = unStat.executeQuery(requete);
			//s'il y a un resultat , on recupere les champs 
			if(unRes.next()) {
				unPilote = new Pilote(
						unRes.getInt("idpilote"),
						unRes.getString("nom"),
						unRes.getString("prenom"),
						unRes.getString("email"),
						unRes.getString("mdp"),
						unRes.getString("adresse"),
						unRes.getString("statut"),
						unRes.getInt("nbheuresvol")
						
						);
			}
			unStat.close();
			uneBdd.seDeConnecter();
			
		}
		catch(SQLException exp) {
			System.out.println("Erreur de requete :" +requete);
			
		}
		return unPilote;
		
	}
    
    public static void insertExterne(Externe unPilote) {
    	 
		String requete = "CALL insererPiloteExterne('" + unPilote.getNom() + "','" + unPilote.getPrenom() + "','"
				+ unPilote.getEmail() +  "','"
                        + unPilote.getMdp() +"','" + unPilote.getAdresse() + "','" + unPilote.getNbheuresvol() + "','"
                + unPilote.getStatut() + "','" + unPilote.getIdcompagnie() + "','" + unPilote.getDateDebut() + "','" + unPilote.getDateFin() + "');";
		System.out.println(requete);
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            unStat.execute(requete);
            unStat.close();
            uneBdd.seDeConnecter();

        } catch (SQLException exp) {
            System.out.println("Erreur de requete :" + requete);
            exp.printStackTrace();
        }
    }
    public static void insertInterne(Interne unPilote) {
  
		
		String requete = "CALL insererPiloteInterne('" + unPilote.getNom() + "','" + unPilote.getPrenom() + "','"
                + unPilote.getEmail()+ "','" + unPilote.getMdp() + "','" + unPilote.getAdresse() + "','" + unPilote.getNbheuresvol() + "','"
                + unPilote.getStatut() + "','" + unPilote.getDateEntree() + "','" + unPilote.getSalaire()+ "');";

        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            unStat.execute(requete);
            unStat.close();
            uneBdd.seDeConnecter();

        } catch (SQLException exp) {
            System.out.println("Erreur de requete :" + requete);
            exp.printStackTrace();
        }
    }
    
    
   
	public static void updatePilote(Pilote unPilote) {
		String requete = "update pilote set nom='"+unPilote.getNom()
		+"',prenom='"+unPilote.getPrenom()
		+"',email='"+unPilote.getEmail()
		+"',mdp='"+unPilote.getMdp()
		+"',adresse='"+unPilote.getAdresse()
		+"',statut='"+unPilote.getStatut()
		+"',nbheuresvol='"+unPilote.getNbheuresvol()
		+"' where idpilote = "+unPilote.getIdpilote()+";";
		
		try {	
			uneBdd.seConnecter();
			//Création d'un curseur pour éxecuter la requete
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeConnecter();
			
		}catch(SQLException exp) {
			System.out.println("Erreur de requete :" +requete);
			
		}
	

		
	}

	
	
	 public static void deletePilote(int idPilote) {
	        String requete = "delete from  pilote where idpilote=" + idPilote + ";";

	        try {
	            uneBdd.seConnecter();
	            Statement unStat = uneBdd.getMaConnexion().createStatement();
	            unStat.execute(requete);
	            unStat.close();
	            uneBdd.seDeConnecter();

	        } catch (SQLException exp) {
	            System.out.println("Erreur de requete :" + requete);

	        }
	    }
	 
	 
	 public static ArrayList<Pilote> selectAllPilotes(String filtre) {
			
		 ArrayList<Pilote> lesPilotes= new ArrayList<Pilote>();
			String requete ="";
			if(filtre.equals("")){
				requete="select * from pilote;";
			}else {
				requete = "select * from pilote where nom like '%"+filtre
						+"%' or prenom like '%"+filtre
						+"%' or email like'%"+filtre
						+"%' or mdp like'%"+filtre
						+"%' or adresse like'%"+filtre
                                              +"%' or statut like'%"+filtre
                                              +"%' or nbheuresvol like'%"+filtre

						+"%';";
				
			}
			
			try {
				uneBdd.seConnecter();
				
				// Création d'un curseur pour executer le nouveau
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				// Execution de la requete et récupération d'un résultat
				ResultSet desRes = unStat.executeQuery(requete);
				// Si il y a un résultat, on récupère les champs
				while (desRes.next())
				{
					Pilote unPilote = new Pilote(
							desRes.getInt("idpilote"),
							desRes.getString("nom"),
							desRes.getString("prenom"),
							
							desRes.getString("email"),
							desRes.getString("mdp"),
							desRes.getString("adresse"),
							desRes.getString("statut"),
							desRes.getFloat("nbheuresvol")
							);
					lesPilotes.add(unPilote);
				}
				unStat.close();
				uneBdd.seDeConnecter();	
			}
			catch (SQLException exp) {
				System.out.println("Erreur de requete : " + requete);
			}
			
			return lesPilotes;
		}
	 /*********************Gestion des avions**************/
	 public static void insertAvion(Avion unAvion) {
	        String requete = "insert into avion values (null,'" + unAvion.getConstructeur() + "','" + unAvion.getModele()
	                + "','" + unAvion.getCapacite() + "','" + unAvion.getPhoto() + "','" + unAvion.getIdaeroport()
	                + "');";

	        try {
	            uneBdd.seConnecter();
	            Statement unStat = uneBdd.getMaConnexion().createStatement();
	            unStat.execute(requete);
	            unStat.close();
	            uneBdd.seDeConnecter();

	        } catch (SQLException exp) {
	            System.out.println("Erreur de requete :" + requete);

	        }
	    }
	 
	 
	 public static Avion selectWhereAvion(int idavion) {
		    Avion unAvion = null;
		    String requete = "select * from avion where idavion=" + idavion;

		    try {
		        uneBdd.seConnecter();
		        Statement unStat = uneBdd.getMaConnexion().createStatement();
		        ResultSet unRes = unStat.executeQuery(requete);
		        
		        // Vérifie si la requête a renvoyé un résultat
		        if (unRes.next()) {
		            unAvion = new Avion(
		                    unRes.getInt("idavion"),
		                    unRes.getString("constructeur"),
		                    unRes.getString("modele"),
		                    unRes.getInt("capacite"),
		                    unRes.getString("photo"),
		                    unRes.getInt("idaeroport")
		            );
		        }
		        
		        unRes.close();
		        unStat.close();
		        uneBdd.seDeConnecter();
		    } catch (SQLException exp) {
		        exp.printStackTrace();
		        System.out.println("Erreur de requete :" + requete);
		    }

		    return unAvion;
		}

	 
	 public static ArrayList<Avion> selectAllAvions(String filtre){
			ArrayList<Avion> lesAvions = new ArrayList<Avion>();
			String requete ="";
			if(filtre.equals("")){
				requete="select * from avion;";
			}else {
				requete = "select * from avion where constructeur like '%"+filtre
						+"%' or modele like '%"+filtre
						+"%' or capacite like'%"+filtre
						+"%' or photo like'%"+filtre
						+"%' or idaeroport like'%"+filtre
						+"%';";
				
			}
		
			try {
				uneBdd.seConnecter();
				//Création d'un curseur pour éxecuter la requete
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				ResultSet desRes = unStat.executeQuery(requete);
				//tant que y a une suivant
				while(desRes.next()) {
					Avion unAvion = new Avion(
							desRes.getInt("idavion"),
							desRes.getString("constructeur"),
							desRes.getString("modele"),
							desRes.getInt("capacite"),
							desRes.getString("photo"),
							desRes.getInt("idaeroport")
							
							);
				lesAvions.add(unAvion);
					
				}
				unStat.close();
				uneBdd.seDeConnecter();
				
			}catch(SQLException exp) {
				System.out.println("Erreur de requete :" +requete);
				
			}
			return lesAvions;
			
			
		}
	 
	 
	 
	 
	 
	 
	 public static void deleteAvion(int idAvion) {
			String requete = "delete from avion where idavion = "+ idAvion+";";
			
			try {
				uneBdd.seConnecter();
				//Création d'un curseur pour éxecuter la requete
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				unStat.execute(requete);
				unStat.close();
				uneBdd.seDeConnecter();
				
			}catch(SQLException exp) {
				System.out.println("Erreur de requete :" +requete);
				
			}
		}
	 
	 
	 public static void updateAvion(Avion unAvion) {
			String requete = "update  avion set constructeur ='"+unAvion.getConstructeur()
			+"', modele=' "+unAvion.getModele()
			+"',capacitet='"+unAvion.getCapacite()
			+"',photo='"+unAvion.getPhoto()
			+"' ,idaeroport='"+unAvion.getIdaeroport()
			+ "	 where idavion ="+unAvion.getIdavion()+";";
			
			try {
				uneBdd.seConnecter();
				//Création d'un curseur pour éxecuter la requete
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				unStat.execute(requete);
				unStat.close();
				uneBdd.seDeConnecter();
				
			}catch(SQLException exp) {
				System.out.println("Erreur de requete :" +requete);
				
			}
		}

	 
	 
	
	 /*********************Gestion des aeroports**************/
	public static void insertAeroport(Aeroport unAeroport) {
        String requete = "insert into aeroport values (null, '" + unAeroport.getNom() + "', '"
                + unAeroport.getCode() + "', '" + unAeroport.getVille() + "', '" + unAeroport.getPays() + "', '"
                + unAeroport.getStatut() + "');";

        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            unStat.execute(requete);
            unStat.close();
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur de requete :" + requete);
        }
    }

    public static Aeroport selectWhereAeroport(int idAeroport) {
        Aeroport unAeroport = null;
        String requete = "select * from aeroport where idaeroport=" + idAeroport;

        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            ResultSet unRes = unStat.executeQuery(requete);

            if (unRes.next()) {
                unAeroport = new Aeroport(
                        unRes.getInt("idaeroport"),
                        unRes.getString("nom"),
                        unRes.getString("code"),
                        unRes.getString("ville"),
                        unRes.getString("pays"),
                        unRes.getString("statut")
                );
            }

            unRes.close();
            unStat.close();
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            exp.printStackTrace();
            System.out.println("Erreur de requete :" + requete);
        }

        return unAeroport;
    }

    public static ArrayList<Aeroport> selectAllAeroports(String filtre) {
        ArrayList<Aeroport> lesAeroports = new ArrayList<>();
        String requete = "";
        if (filtre.equals("")) {
            requete = "select * from aeroport;";
        } else {
            requete = "select * from aeroport where nom like '%" + filtre
                    + "%' or code like '%" + filtre
                    + "%' or ville like '%" + filtre
                    + "%' or pays like '%" + filtre
                    + "%' or statut like '%" + filtre
                    + "%';";
        }

        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            ResultSet desRes = unStat.executeQuery(requete);

            while (desRes.next()) {
                Aeroport unAeroport = new Aeroport(
                        desRes.getInt("idaeroport"),
                        desRes.getString("nom"),
                        desRes.getString("code"),
                        desRes.getString("ville"),
                        desRes.getString("pays"),
                        desRes.getString("statut")
                );
                lesAeroports.add(unAeroport);
            }

            unStat.close();
            uneBdd.seDeConnecter();

        } catch (SQLException exp) {
            System.out.println("Erreur de requete :" + requete);
        }
        return lesAeroports;
    }

    public static void deleteAeroport(int idAeroport) {
        String requete = "delete from aeroport where idaeroport = " + idAeroport + ";";

        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            unStat.execute(requete);
            unStat.close();
            uneBdd.seDeConnecter();

        } catch (SQLException exp) {
            System.out.println("Erreur de requete :" + requete);

        }
    }

    public static void updateAeroport(Aeroport unAeroport) {
        String requete = "update aeroport set nom='" + unAeroport.getNom()
                + "', code='" + unAeroport.getCode()
                + "', ville='" + unAeroport.getVille()
                + "', pays='" + unAeroport.getPays()
                + "', statut='" + unAeroport.getStatut()
                + "' where idaeroport =" + unAeroport.getIdaeroport() + ";";

        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            unStat.execute(requete);
            unStat.close();
            uneBdd.seDeConnecter();

        } catch (SQLException exp) {
            System.out.println("Erreur de requete :" + requete);

        }
    }
    
    
    
    public static void insertVol(Vol unVol) {
        String requete = "INSERT INTO vol (numero_vol, date_depart, heure_depart, heure_arrivee, ville_depart, ville_arrivee, classe_vol, idavion, idpilote) VALUES ('" 
                + unVol.getNumeroVol() + "', '" 
                + unVol.getDateDepart() + "', '" 
                + unVol.getHeureDepart() + "', '" 
                + unVol.getHeureArrivee() + "', '" 
                + unVol.getDateDepart() + "', '" 
                + unVol.getVilleArrivee() + "', '" 
                + unVol.getClasseVol() + "', " 
                + unVol.getIdAvion() + ", " 
                +  unVol.getIdPilote() + ");";
            
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            unStat.execute(requete);
            unStat.close();
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur de requete :" + requete);
        }
    }


    public static Vol selectWhereVol(int idVol) {
        Vol unVol = null;
        String requete = "select * from vol where idvol=" + idVol;

        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            ResultSet unRes = unStat.executeQuery(requete);

            if (unRes.next()) {
                unVol = new Vol(
                		unRes.getInt("idvol"),
                		unRes.getString("numero_vol"),
                		unRes.getString("date_depart"),
                		unRes.getString("heure_depart"),
                		unRes.getString("heure_arrivee"),
                		unRes.getString("ville_depart"),
                		unRes.getString("ville_arrivee"),
                		unRes.getString("classe_vol"),
                		unRes.getInt("idavion"),
                		unRes.getInt("idpilote")
                );
            }

            unRes.close();
            unStat.close();
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            exp.printStackTrace();
            System.out.println("Erreur de requete :" + requete);
        }

        return unVol;
    }

    public static ArrayList<Vol> selectAllVols(String filtre) {
        ArrayList<Vol> lesVols = new ArrayList<>();
        String requete = "";
        if (filtre.equals("")) {
            requete = "select * from vol;";
        } else {
            // Ajoutez votre filtre personnalisé ici
        }

        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            ResultSet desRes = unStat.executeQuery(requete);

            while (desRes.next()) {
                Vol unVol = new Vol(
                        desRes.getInt("idvol"),
                        desRes.getString("numero_vol"),
                        desRes.getString("date_depart"),
                        desRes.getString("heure_depart"),
                        desRes.getString("heure_arrivee"),
                        desRes.getString("ville_depart"),
                        desRes.getString("ville_arrivee"),
                        desRes.getString("classe_vol"),
                        desRes.getInt("idavion"),
                        desRes.getInt("idpilote")
                );
                lesVols.add(unVol);
            }

            unStat.close();
            uneBdd.seDeConnecter();

        } catch (SQLException exp) {
            System.out.println("Erreur de requete :" + requete);
        }
        return lesVols;
    }

    public static void deleteVol(int idVol) {
        String requete = "delete from vol where idvol = " + idVol + ";";

        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            unStat.execute(requete);
            unStat.close();
            uneBdd.seDeConnecter();

        } catch (SQLException exp) {
            System.out.println("Erreur de requete :" + requete);

        }
    }

    public static void updateVol(Vol unVol) {
        String requete = "update vol set numero_vol='" + unVol.getNumeroVol()
                + "', date_depart='" + unVol.getDateDepart()
                + "', heure_depart='" + unVol.getDateDepart()
                + "', heure_arrivee='" + unVol.getHeureArrivee()
                + "', ville_depart='" + unVol.getDateDepart()
                + "', ville_arrivee='" + unVol.getHeureArrivee()
                + "', classe_vol='" + unVol.getClasseVol()
                + "', idavion='" + unVol.getIdAvion()
                + "', idpilote='" + unVol.getIdPilote()
                + "' where idvol =" + unVol.getIdVol() + ";";

        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            unStat.execute(requete);
            unStat.close();
            uneBdd.seDeConnecter();

        } catch (SQLException exp) {
            System.out.println("Erreur de requete :" + requete);

        }
    }
    
    
    ////////compagnies////
    
    
    public static void insertCompagnie(Compagnie uneCompagnie) {
        String requete = "insert into compagnie values (null, '" + uneCompagnie.getLibelle() + "', '" + uneCompagnie.getPays() + "');";

        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            unStat.execute(requete);
            unStat.close();
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur de requete :" + requete);
        }
    }

    public static Compagnie selectWhereCompagnie(int idCompagnie) {
    	Compagnie uneCompagnie = null;
        String requete = "select * from compagnie where idcompagnie=" + idCompagnie;

        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            ResultSet unRes = unStat.executeQuery(requete);

            if (unRes.next()) {
                uneCompagnie = new Compagnie(
                        unRes.getInt("idcompagnie"),
                        unRes.getString("lieblle"),
                        unRes.getString("pays")
                        
                );
            }

            unRes.close();
            unStat.close();
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            exp.printStackTrace();
            System.out.println("Erreur de requete :" + requete);
        }

        return uneCompagnie;
    }

    public static ArrayList<Compagnie> selectAllCompagnies(String filtre) {
        ArrayList<Compagnie> lesCompagnies = new ArrayList<>();
        String requete = "";
        if (filtre.equals("")) {
            requete = "select * from compagnie;";
        } else {
            requete = "select * from compagnie where libelle like '%" + filtre
                    + "%' or pays like '%" + filtre
                    + "%';";
        }


        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            ResultSet desRes = unStat.executeQuery(requete);

            while (desRes.next()) {
                Compagnie uneCompagnie = new Compagnie(
                        desRes.getInt("idcompagnie"),
                        desRes.getString("libelle"),
                        
                        desRes.getString("pays")
                       
                );
                lesCompagnies.add(uneCompagnie);
            }

            unStat.close();
            uneBdd.seDeConnecter();

        } catch (SQLException exp) {
            System.out.println("Erreur de requete :" + requete);
        }
        return lesCompagnies;
    }

    public static void deleteCompagnie(int idCompagnie) {
        String requete = "delete from compagnie where idcompagnie = " + idCompagnie + ";";

        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            unStat.execute(requete);
            unStat.close();
            uneBdd.seDeConnecter();

        } catch (SQLException exp) {
            System.out.println("Erreur de requete :" + requete);

        }
    }

    public static void updateCompagnie(Compagnie uneCompagnie) {
        String requete = "update compagnie set libelle='" + uneCompagnie.getLibelle()
                
                + "', pays='" + uneCompagnie.getPays()
               
                + "' where idcompagnie =" + uneCompagnie.getIdcompagnie() + ";";

        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            unStat.execute(requete);
            unStat.close();
            uneBdd.seDeConnecter();

        } catch (SQLException exp) {
            System.out.println("Erreur de requete :" + requete);

        }
    }

    public static int getLastInsertedVolId() {
        int lastInsertedId = 0;
        String requete = "SELECT MAX(idvol) AS last_id FROM vol;";

        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            ResultSet unRes = unStat.executeQuery(requete);

            if (unRes.next()) {
                lastInsertedId = unRes.getInt("last_id");
            }

            unRes.close();
            unStat.close();
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur de requete :" + requete);
        }

        return lastInsertedId;
    }
	public static int getLastInsertedAvionId() {
		  int lastId = 0;
	        try {
	            // Exécutez une requête SQL pour récupérer l'ID du dernier avion inséré
	            String requete = "SELECT MAX(idAvion) AS last_id FROM avion; ";
	            uneBdd.seConnecter();
	            Statement unStat = uneBdd.getMaConnexion().createStatement();
	            ResultSet rs = unStat.executeQuery(requete);
	            if (rs.next()) {
	                lastId = rs.getInt("last_id");
	            }
	            rs.close();
	            unStat.close();
	            uneBdd.seDeConnecter();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return lastId;
	}
	public static int getLastInsertedAeroportId() {
		int lastId = 0;
        try {
            // Exécutez une requête SQL pour récupérer l'ID du dernier aeroport inséré
            String requete = "SELECT MAX(idAeroport) AS last_id FROM aeroport ;";
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            
            ResultSet rs = unStat.executeQuery(requete);
            
            if (rs.next()) {
                lastId = rs.getInt("last_id");
            }
            rs.close();
            unStat.close();
            uneBdd.seDeConnecter();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastId;
	}   
	public static int getLastInsertedCompagnieId() {
		int lastId = 0;
        try {
            // Exécutez une requête SQL pour récupérer l'ID du dernier aeroport inséré
            String requete = "SELECT MAX(idCompagnie) AS last_id FROM compagnie ;";
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            
            ResultSet rs = unStat.executeQuery(requete);
            
            if (rs.next()) {
                lastId = rs.getInt("last_id");
            }
            rs.close();
            unStat.close();
            uneBdd.seDeConnecter();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastId;
	}   
	public static ArrayList<ViewPilote> selectAllViewPilotes() {
		 ArrayList<ViewPilote> lesViewPilotes = new ArrayList<ViewPilote>();
	        String requete = "select * from VueNombreVolsParPilote;";
	        try {
	            uneBdd.seConnecter();
	            Statement unStat = uneBdd.getMaConnexion().createStatement();
	            ResultSet desRes = unStat.executeQuery(requete);
	            while (desRes.next()) {
	            	ViewPilote unViewPilote = new ViewPilote(desRes.getString("nom"),
	                        desRes.getString("prenom"), 
	                        desRes.getInt("nombre_de_vols"), desRes.getInt("totalHeures"));
	                lesViewPilotes.add(unViewPilote);
	            }
	            unStat.close();
	            uneBdd.seDeConnecter();

	        } catch (SQLException exp) {
	            System.out.println("Erreur de requete :" + requete);

	        }
	        return lesViewPilotes;
	}   
	 
	 
	 
}	
	