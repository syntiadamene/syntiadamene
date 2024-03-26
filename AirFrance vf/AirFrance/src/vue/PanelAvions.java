package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import controleur.Aeroport;
import controleur.Avion;
import controleur.Controleur;
import controleur.Tableau;

public class PanelAvions extends PanelPrincipal implements ActionListener {

    private JPanel panelForm = new JPanel();
    private JTextField txtConstructeur = new JTextField();
    private JTextField txtModele = new JTextField();
    private JTextField txtCapacite = new JTextField();
    private JTextField txtPhoto = new JTextField();
    private JComboBox<String> cmbIdAeroport = new JComboBox<String>();

    private JButton btAnnuler = new JButton("Annuler");
    private JButton btEnregistrer = new JButton("Enregistrer");

    private JTable tableAvions;
    private JScrollPane uneScroll;
    private Tableau unTableau;

    private JPanel panelRech = new JPanel();
    private JTextField txtFiltre = new JTextField();
    private JButton btFiltre = new JButton("Filtrer");
    private JLabel lbAvions;

    public PanelAvions() {
        super(new Color(166, 216, 222));

        this.panelForm.setBounds(50, 50, 300, 250);
        this.panelForm.setBackground(new Color(166, 216, 222));
        this.panelForm.setLayout(new GridLayout(6, 2));
        this.panelForm.add(new JLabel("Constructeur :"));
        this.panelForm.add(this.txtConstructeur);
        this.panelForm.add(new JLabel("Modèle :"));
        this.panelForm.add(this.txtModele);
        this.panelForm.add(new JLabel("Capacité :"));
        this.panelForm.add(this.txtCapacite);
        this.panelForm.add(new JLabel("Photo :"));
        this.panelForm.add(this.txtPhoto);
        this.panelForm.add(new JLabel("ID Aéroport :"));
        this.panelForm.add(this.cmbIdAeroport);

        this.panelForm.add(this.btAnnuler);
        this.panelForm.add(this.btEnregistrer);
        this.add(this.panelForm);
        this.panelForm.setVisible(true);

        String entetes[] = {"ID Avion", "Constructeur", "Modèle", "Capacité", "Photo", "ID Aéroport"};
        this.unTableau = new Tableau(entetes, this.obtenirDonnees(""));
        this.tableAvions = new JTable(this.unTableau);
        this.uneScroll = new JScrollPane(this.tableAvions);
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
        this.tableAvions.getTableHeader().setReorderingAllowed(false);
        //remplir le CBX aeroport 
        this.remplirCbxAeroport();
        
        this.tableAvions.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	   int numLigne = tableAvions.getSelectedRow();
                   if (numLigne >= 0 && numLigne < unTableau.getRowCount()) {
                       int idAvion = Integer.parseInt(tableAvions.getValueAt(numLigne, 0).toString());
                       int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer cet avion ? ",
                               "Suppression Avion", JOptionPane.YES_NO_OPTION);
                       if (reponse == 0) {
                           Controleur.deleteAvion(idAvion);
                           unTableau.supprimerLigne(numLigne);
                           lbAvions.setText("Nombre d'avions disponibles : " + unTableau.getRowCount());
                       }
                   } else {
                       String constructeur = tableAvions.getValueAt(numLigne, 1).toString();
                       String modele = tableAvions.getValueAt(numLigne, 2).toString();
                       String capacite = tableAvions.getValueAt(numLigne, 3).toString();
                       String photo = tableAvions.getValueAt(numLigne, 4).toString();
                       String idAeroport = tableAvions.getValueAt(numLigne, 5).toString();

                       txtConstructeur.setText(constructeur);
                       txtModele.setText(modele);
                       txtCapacite.setText(capacite);
                       txtPhoto.setText(photo);
                       cmbIdAeroport.setSelectedItem(idAeroport);

                       btEnregistrer.setText("Modifier");
                   }
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });

        int nbAvions = this.unTableau.getRowCount();
        lbAvions = new JLabel("Nombre d'avions disponibles : " + nbAvions);
        lbAvions.setBounds(300, 360, 300, 20);
        this.add(lbAvions);
    }

    public void remplirCbxAeroport () {
        ArrayList<Aeroport> lesAeroports = Controleur.selectAllAeroports(""); 
        this.cmbIdAeroport.removeAllItems(); 
        for (Aeroport unAeroport : lesAeroports) {
            // Ajouter l'ID de l'aéroport au JComboBox
            this.cmbIdAeroport.addItem(unAeroport.getIdaeroport() +"-" + unAeroport.getNom()); 
        }
    }

    public Object[][] obtenirDonnees(String filtre) {
        ArrayList<Avion> lesAvions = Controleur.selectAllAvions(filtre);
        Object[][] matrice = new Object[lesAvions.size()][6];
        int i = 0;
        for (Avion unAvion : lesAvions) {
            matrice[i][0] = unAvion.getIdavion();
            matrice[i][1] = unAvion.getConstructeur();
            matrice[i][2] = unAvion.getModele();
            matrice[i][3] = unAvion.getCapacite();
            matrice[i][4] = unAvion.getPhoto();
            matrice[i][5] = unAvion.getIdaeroport();
            i++;
        }
        return matrice;
    }

    public void viderChamps() {
        this.txtConstructeur.setText("");
        this.txtModele.setText("");
        this.txtCapacite.setText("");
        this.txtPhoto.setText("");
        this.cmbIdAeroport.setSelectedIndex(0); // Sélectionner le premier élément par défaut
        this.btEnregistrer.setText("Enregistrer");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btAnnuler) {
            this.viderChamps();
        } else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")) {
            String constructeur = this.txtConstructeur.getText();
            String modele = this.txtModele.getText();
            int capacite = Integer.parseInt(this.txtCapacite.getText());
            String photo = this.txtPhoto.getText();
            String chaine = cmbIdAeroport.getSelectedItem().toString(); 
            String tab[] = chaine.split("-");            
            int idaeroport = Integer.parseInt(tab[0]);

            Avion unAvion = new Avion(constructeur, modele, capacite, photo, idaeroport);
            Controleur.insertAvion(unAvion);

            // Récupérer l'ID de l'avion nouvellement inséré
            int idAvion = Controleur.getLastInsertedAvionId();

            JOptionPane.showMessageDialog(this, "Avion inséré avec succès dans la BDD");

            Object ligne[] = {idAvion, constructeur, modele, capacite, photo, idaeroport};
            this.unTableau.ajouterLigne(ligne);
            lbAvions.setText("Nombre d'avions disponibles : " + unTableau.getRowCount());

            this.viderChamps();
        } else if (e.getSource() == this.btFiltre) {
            String filtre = this.txtFiltre.getText();
            Object matrice[][] = this.obtenirDonnees(filtre);
            this.unTableau.setDonnees(matrice);
        } else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Modifier")) {
            String constructeur = this.txtConstructeur.getText();
            String modele = this.txtModele.getText();
            int capacite = Integer.parseInt(this.txtCapacite.getText());
            String photo = this.txtPhoto.getText();
            String chaine = cmbIdAeroport.getSelectedItem().toString(); 
            String tab[] = chaine.split("-");            
            int idaeroport = Integer.parseInt(tab[0]);

            int numLigne = tableAvions.getSelectedRow();
            int idAvion = Integer.parseInt(tableAvions.getValueAt(numLigne, 0).toString());

            Avion unAvion = new Avion(idAvion, constructeur, modele, capacite, photo, idaeroport);
            Controleur.updateAvion(unAvion);

            Object ligne[] = {idAvion, constructeur, modele, capacite, photo, idaeroport};
            this.unTableau.modifierLigne(numLigne, ligne);
            JOptionPane.showMessageDialog(this, "Modification effectuée");
            this.viderChamps();
            this.btEnregistrer.setText("Enregistrer");
        }
    }
}
