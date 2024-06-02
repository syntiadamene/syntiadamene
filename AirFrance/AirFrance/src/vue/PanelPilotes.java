package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;

import controleur.Controleur;
import controleur.Externe;
import controleur.Interne;
import controleur.Pilote;
import controleur.Tableau;

public class PanelPilotes extends PanelPrincipal implements ActionListener {

    private JPanel panelForm = new JPanel();
    private JTextField txtNom = new JTextField();
    private JTextField txtPrenom = new JTextField();
    private JTextField txtEmail = new JTextField();
    private JPasswordField txtMdp = new JPasswordField();
    private JTextField txtAdresse = new JTextField();
    private JComboBox<String> txtStatut = new JComboBox<String>();
    private JTextField txtNbHeuresVol = new JTextField();
    private JButton btAnnuler = new JButton("Annuler");
    private JButton btEnregistrer = new JButton("Enregistrer");

    private JTable tablePilotes;
    private JScrollPane uneScroll;
    private Tableau unTableau;

    private JPanel panelRech = new JPanel();
    private JTextField txtFiltre = new JTextField();
    private JButton btFiltre = new JButton("Filtrer");
    private JLabel lbPilotes;
   
    private JPanel panelExterne = new  JPanel (); 
    private JTextField txtDateDebut= new JTextField();
    private JTextField txtDateFin = new JTextField();
    private JTextField txtIdCompagnie= new JTextField();
    
    private JPanel panelInterne = new  JPanel (); 
    private JTextField txtSalaire = new JTextField();
    private JTextField txtDateEntree = new JTextField();
    

    public PanelPilotes() {
        super(new Color(166, 216, 222));
  
        
        this.txtStatut.addItem("Externe");
        this.txtStatut.addItem("Interne");
        
        this.panelForm.setBounds(50, 30, 300, 250);
        this.panelForm.setBackground(new Color(166, 216, 222));
        this.panelForm.setLayout(new GridLayout(8, 2));
        this.panelForm.add(new JLabel("Nom :"));
        this.panelForm.add(this.txtNom);
        this.panelForm.add(new JLabel("Prénom :"));
        this.panelForm.add(this.txtPrenom);
        this.panelForm.add(new JLabel("Email :"));
        this.panelForm.add(this.txtEmail);
        this.panelForm.add(new JLabel("Mot de passe :"));
        this.panelForm.add(this.txtMdp);
        this.panelForm.add(new JLabel("Adresse :"));
        this.panelForm.add(this.txtAdresse);
        this.panelForm.add(new JLabel("Nb Heures Vol :"));
        this.panelForm.add(this.txtNbHeuresVol);
        this.panelForm.add(new JLabel("Statut :"));
        this.panelForm.add(this.txtStatut);
        
        //this.panelForm.add(this.btAnnuler);
       // this.panelForm.add(this.btEnregistrer);
        this.add(this.panelForm);
        this.panelForm.setVisible(true);
        
        this.panelExterne.setBounds(50, 280, 300, 80);
        this.panelExterne.setBackground(new Color(166, 216, 222));
        this.panelExterne.setLayout(new GridLayout(3, 2));
        this.panelExterne.add(new JLabel("Date Debut :"));
        this.panelExterne.add(this.txtDateDebut);
        this.panelExterne.add(new JLabel("Date Fin :"));
        this.panelExterne.add(this.txtDateFin);
        this.panelExterne.add(new JLabel("Compagnie :"));
        this.panelExterne.add(this.txtIdCompagnie);
        this.add(this.panelExterne);
        this.panelExterne.setVisible(true);
        
        this.panelInterne.setBounds(50, 280, 300, 80);
        this.panelInterne.setBackground(new Color(166, 216, 222));
        this.panelInterne.setLayout(new GridLayout(2, 2));
        this.panelInterne.add(new JLabel("Date Entree :"));
        this.panelInterne.add(this.txtDateEntree);
        this.panelInterne.add(new JLabel("Salaire:"));
        this.panelInterne.add(this.txtSalaire);
        this.add(this.panelInterne);
        this.panelInterne.setVisible(false);
        
        this.btAnnuler.setBounds(50, 380, 120, 30);
        this.add(this.btAnnuler);
        
        this.btEnregistrer.setBounds(210, 380, 120, 30);
        this.add(this.btEnregistrer);
        
        this.txtStatut.addActionListener(this);
        
        String entetes[] = {"ID Pilote", "Nom", "Prénom", "Email", "Adresse", "Statut", "Nb Heures Vol"};
        this.unTableau = new Tableau(entetes, this.obtenirDonnees(""));
        this.tablePilotes = new JTable(this.unTableau);
        this.uneScroll = new JScrollPane(this.tablePilotes);
        this.uneScroll.setBounds(400, 80, 400, 250);
        this.add(this.uneScroll);

        this.panelRech.setBounds(450, 10, 300, 20);
        this.panelRech.setBackground(new Color(166, 216, 222));
        this.panelRech.setLayout(new GridLayout(1, 3));
        this.panelRech.add(new JLabel("Filtrer par :"));
        this.panelRech.add(this.txtFiltre);
        this.panelRech.add(this.btFiltre);
        this.panelRech.setVisible(true);
        this.add(this.panelRech);

        this.btAnnuler.addActionListener(this);
        this.btEnregistrer.addActionListener(this);
        this.btFiltre.addActionListener(this);
        
        this.tablePilotes.getTableHeader().setReorderingAllowed(false);

        this.tablePilotes.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int numLigne = 0;
                int idPilote = 0;
                if (e.getClickCount() >= 2) {

                    numLigne = tablePilotes.getSelectedRow();
                    idPilote = Integer.parseInt(tablePilotes.getValueAt(numLigne, 0).toString());
                    int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer ce pilote ? ",
                            "Suppression Pilote", JOptionPane.YES_NO_OPTION);
                    if (reponse == 0) {

                        Controleur.deletePilote(idPilote);
                        unTableau.supprimerLigne(numLigne);
                        lbPilotes.setText("Nombre de pilotes disponibles : " + unTableau.getRowCount());
                    }
                } else if (numLigne >= 0 && numLigne < tablePilotes.getRowCount() && tablePilotes.getColumnCount() > 0) {

                    numLigne = tablePilotes.getSelectedRow();
                    idPilote = Integer.parseInt(tablePilotes.getValueAt(numLigne, 0).toString());

                    String nom = tablePilotes.getValueAt(numLigne, 1).toString();
                    String prenom = tablePilotes.getValueAt(numLigne, 2).toString();
                    String email = tablePilotes.getValueAt(numLigne, 3).toString();
                    String mdp = tablePilotes.getValueAt(numLigne, 4).toString();
                    String adresse = tablePilotes.getValueAt(numLigne, 5).toString();
                    String statut = tablePilotes.getValueAt(numLigne, 6).toString();
                    String nbheuresvol = tablePilotes.getValueAt(numLigne, 7).toString();

                    txtNom.setText(nom);
                    txtPrenom.setText(prenom);
                    txtEmail.setText(email);
                    txtMdp.setText(mdp);
                    txtAdresse.setText(adresse);
                    txtStatut.setSelectedItem(statut);
                    txtNbHeuresVol.setText(nbheuresvol);
                    btEnregistrer.setText("Modifier");
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }
        });

        int nbPilotes = this.unTableau.getRowCount();
        lbPilotes = new JLabel("Nombre de pilotes disponibles :" + nbPilotes);
        lbPilotes.setBounds(400, 360, 300, 20);
        this.add(lbPilotes);
    }

    public Object[][] obtenirDonnees(String filtre) {
        ArrayList<Pilote> lesPilotes = Controleur.selectAllPilotes(filtre);
        Object[][] matrice = new Object[lesPilotes.size()][7];
        int i = 0;
        for (Pilote unPilote : lesPilotes) {
            matrice[i][0] = unPilote.getIdpilote();
            matrice[i][1] = unPilote.getNom();
            matrice[i][2] = unPilote.getPrenom();
            matrice[i][3] = unPilote.getEmail();
            matrice[i][4] = unPilote.getAdresse();
            matrice[i][5] = unPilote.getStatut();
            matrice[i][6] = unPilote.getNbheuresvol();
            i++;
        }
        return matrice;
    }

    public void viderChamps() {
        this.txtNom.setText("");
        this.txtPrenom.setText("");
        this.txtEmail.setText("");
        this.txtMdp.setText("");
        this.txtAdresse.setText("");
         
        this.txtNbHeuresVol.setText("");
        this.btEnregistrer.setText("Enregistrer");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	  if (e.getSource() == this.txtStatut) {
    		  if ( this.txtStatut.getSelectedItem().toString().equals("Externe")) {
    			  this.panelExterne.setVisible(true);
        		  this.panelInterne.setVisible(false);
    		  }else {
    		  this.panelExterne.setVisible(false);
    		  this.panelInterne.setVisible(true);
    		  }
    	  }
    	  else if (e.getSource() == this.btAnnuler) {
            this.viderChamps();
        } 
                else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")) {
                    String nom = this.txtNom.getText();
                    String prenom = this.txtPrenom.getText();
                    String email = this.txtEmail.getText();
                    String mdp = new String(this.txtMdp.getPassword());
                    String adresse = this.txtAdresse.getText();
                    String statut = "";
                    int nbHeuresVol = Integer.parseInt(this.txtNbHeuresVol.getText());

                    if (!email.contains("@") || !email.contains(".")) {
                        JOptionPane.showMessageDialog(this, "L'adresse e-mail n'est pas valide.");
                        this.txtEmail.setBackground(Color.RED);
                        return;
                    } else {
                        // Si l'adresse e-mail est valide, remettre la couleur de fond à sa couleur normale
                        this.txtEmail.setBackground(UIManager.getColor("TextField.background"));
                    }
                    

                    if (this.txtStatut.getSelectedItem().toString().equals("Externe")) {
                        String dateDebut = this.txtDateDebut.getText();
                        String dateFin = this.txtDateFin.getText();
                        int idCompagnie = Integer.parseInt(this.txtIdCompagnie.getText());
                        statut = "PiloteExterne";
                        Externe externe = new Externe(nom, prenom, email, mdp, adresse, statut, nbHeuresVol, idCompagnie, dateDebut, dateFin);
                        Controleur.insertExterne(externe);
                        Pilote unPilote = Controleur.selectWherePilote(email, mdp);
                        JOptionPane.showMessageDialog(this, "Pilote externe inséré avec succès dans la BDD");
                        Object ligne[] = {unPilote.getIdpilote(), nom, prenom, email, adresse, statut, nbHeuresVol};
                        this.unTableau.ajouterLigne(ligne);
                        lbPilotes.setText("Nombre de pilotes disponibles : " + unTableau.getRowCount());
                    } else {
                        String dateEntree = this.txtDateEntree.getText();
                        float salaire = Float.parseFloat(this.txtSalaire.getText());
                        statut = "PiloteInterne";
                        Interne interne = new Interne(nom, prenom, email, mdp, adresse, statut, nbHeuresVol, salaire, dateEntree);
                        Controleur.insertInterne(interne);
                        Pilote piloteInsere = Controleur.selectWherePilote(email, mdp);
                        JOptionPane.showMessageDialog(this, "Pilote interne inséré avec succès dans la BDD");


                // Ajouter une nouvelle ligne au tableau affichant les pilotes
              

                Object ligne[] = {piloteInsere.getIdpilote(), nom, prenom, email, adresse, statut, nbHeuresVol};
                this.unTableau.ajouterLigne(ligne);
                lbPilotes.setText("Nombre de pilotes disponibles : " + unTableau.getRowCount());
            }

            this.viderChamps();
        } else if (e.getSource() == this.btFiltre) {
            String filtre = this.txtFiltre.getText();
            Object matrice[][] = this.obtenirDonnees(filtre);
            this.unTableau.setDonnees(matrice);
        } else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Modifier")) {
            String nom = this.txtNom.getText();
            String prenom = this.txtPrenom.getText();
            String email = this.txtEmail.getText();
            String mdp = this.txtMdp.getText();
            String adresse = this.txtAdresse.getText();
            String statut = this.txtStatut.getSelectedItem().toString();
            int nbHeuresVol = Integer.parseInt(this.txtNbHeuresVol.getText());

            int numLigne = 0;
            int idPilote = 0;
            numLigne = tablePilotes.getSelectedRow();
            idPilote = Integer.parseInt(tablePilotes.getValueAt(numLigne, 0).toString());

            Pilote unPilote = new Pilote(idPilote, nom, prenom, email, mdp, adresse, statut, nbHeuresVol);
            Controleur.updatePilote(unPilote);

            Object ligne[] = {idPilote, nom, prenom, email, adresse, statut, nbHeuresVol};
            this.unTableau.modifierLigne(numLigne, ligne);
            JOptionPane.showMessageDialog(this, "Modification effectuée");
            this.viderChamps();
            this.btEnregistrer.setText("Enregistrer");
        }
    }
}