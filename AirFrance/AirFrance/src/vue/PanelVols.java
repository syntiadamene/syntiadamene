package vue ;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import controleur.Aeroport;
import controleur.Avion;
import controleur.Controleur;
import controleur.Pilote;
import controleur.Tableau;
import controleur.Vol;

public class PanelVols extends PanelPrincipal implements ActionListener {

    private JPanel panelForm = new JPanel();
    private JTextField txtNumeroVol = new JTextField();
    private JTextField txtDateDepart = new JTextField();
    private JTextField txtHeureDepart = new JTextField();
    private JTextField txtHeureArrivee = new JTextField();
    private JTextField txtVilleDepart = new JTextField();
    private JTextField txtVilleArrivee = new JTextField();
    private JComboBox<String> cmbClasseVol = new JComboBox<String>();
    private JComboBox<String> cmbIdPilote = new JComboBox<String>();
    private JComboBox<String> cmbIdAvion = new JComboBox<String>();
    private JButton btAnnuler = new JButton("Annuler");
    private JButton btEnregistrer = new JButton("Enregistrer");

    private JTable tableVols;
    private JScrollPane uneScroll;
    private Tableau unTableau;

    private JPanel panelRech = new JPanel();
    private JTextField txtFiltre = new JTextField();
    private JButton btFiltre = new JButton("Filtrer");
    private JLabel lbVols;

    public PanelVols() {
        super(new Color(166, 216, 222));

        this.panelForm.setBounds(30, 50, 760, 150);
        this.panelForm.setBackground(new Color(166, 216, 222));
        this.panelForm.setLayout(new GridLayout(5, 3));
        this.panelForm.add(new JLabel("Numéro de Vol :"));
        this.panelForm.add(this.txtNumeroVol);
        this.panelForm.add(new JLabel("Date de Départ :"));
        this.panelForm.add(this.txtDateDepart);
        this.panelForm.add(new JLabel("Heure de Départ :"));
        this.panelForm.add(this.txtHeureDepart);
        this.panelForm.add(new JLabel("Heure d'Arrivée :"));
        this.panelForm.add(this.txtHeureArrivee);
        this.panelForm.add(new JLabel("Ville de Départ :"));
        this.panelForm.add(this.txtVilleDepart);
        this.panelForm.add(new JLabel("Ville d'Arrivée :"));
        this.panelForm.add(this.txtVilleArrivee);
        this.panelForm.add(new JLabel("Classe de Vol :"));
        this.panelForm.add(this.cmbClasseVol);
        this.panelForm.add(new JLabel("ID Pilote :"));
        this.panelForm.add(this.cmbIdPilote);
        this.panelForm.add(new JLabel("ID Avion :"));
        this.panelForm.add(this.cmbIdAvion);
        this.panelForm.add(new JLabel(""));
        this.panelForm.add(new JLabel(""));
        this.panelForm.add(new JLabel(""));
        this.panelForm.add(new JLabel(""));
        this.panelForm.add(new JLabel(""));
        this.panelForm.add(new JLabel(""));
        this.panelForm.add(new JLabel(""));
        this.panelForm.add(new JLabel(""));
        this.panelForm.add(this.btAnnuler);
        this.panelForm.add(this.btEnregistrer);
        this.add(this.panelForm);
        this.panelForm.setVisible(true);

        String entetes[] = {" Vol", "N°Vol", "Départ", "Heure", "Arrivée", "Ville D", "Ville A", "Classe", "Pilote", "Avion"};
        this.unTableau = new Tableau(entetes, this.obtenirDonnees(""));
        this.tableVols = new JTable(this.unTableau);
        this.uneScroll = new JScrollPane(this.tableVols);
        this.uneScroll.setBounds(30, 280, 760, 250);
        this.add(this.uneScroll);

        this.panelRech.setBounds(300, 240, 300, 20);
        this.panelRech.setBackground(new Color(166, 216, 222));
        this.panelRech.setLayout(new GridLayout(1, 2));
        this.panelRech.add(new JLabel("Filtrer par :"));
        this.panelRech.add(this.txtFiltre);
        this.panelRech.add(this.btFiltre);
        this.panelRech.setVisible(true);
        this.add(this.panelRech);

        this.btAnnuler.addActionListener(this);
        this.btEnregistrer.addActionListener(this);
        this.btFiltre.addActionListener(this);
        this.tableVols.getTableHeader().setReorderingAllowed(false);
        this.remplirCbxPilote();
        this.remplirCbxAvion();
        this.remplirCbxClasseVol();

        this.tableVols.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int numLigne = tableVols.getSelectedRow();
                int idVol = Integer.parseInt(tableVols.getValueAt(numLigne, 0).toString());
                
                if (e.getClickCount() >= 2) {
                    int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer ce vol ? ", "Suppression vol", JOptionPane.YES_NO_OPTION);
                    if (reponse == 0) {
                        Controleur.deleteVol(idVol);
                        unTableau.supprimerLigne(numLigne);
                        lbVols.setText("Nombre de vols disponibles : " + unTableau.getRowCount());
                    }
                } else {
                    String numeroVol = tableVols.getValueAt(numLigne, 1).toString();
                    String dateDepart = tableVols.getValueAt(numLigne, 2).toString();
                    String heureDepart = tableVols.getValueAt(numLigne, 3).toString();
                    String heureArrivee = tableVols.getValueAt(numLigne, 4).toString();
                    String villeDepart = tableVols.getValueAt(numLigne, 5).toString();
                    String villeArrivee = tableVols.getValueAt(numLigne, 6).toString();
                    String classeVol = tableVols.getValueAt(numLigne, 7).toString();
                    
                    txtNumeroVol.setText(numeroVol);
                    txtDateDepart.setText(dateDepart);
                    txtHeureDepart.setText(heureDepart);
                    txtHeureArrivee.setText(heureArrivee);
                    txtVilleDepart.setText(villeDepart);
                    txtVilleArrivee.setText(villeArrivee);
                    cmbClasseVol.setSelectedItem(classeVol);
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

        int nbVols = this.unTableau.getRowCount();
        lbVols = new JLabel("Nombre de vols disponibles : " + nbVols);
        lbVols.setBounds(600, 360, 300, 20);
        this.add(lbVols);
    }

    public void remplirCbxAvion () {
        ArrayList<Avion> lesAvions = Controleur.selectAllAvions(""); 
        this.cmbIdAvion.removeAllItems(); 
        for (Avion unAvion : lesAvions) {
            this.cmbIdAvion.addItem(unAvion.getIdavion() +"-" + unAvion.getConstructeur()); 
        }
    }
    
    public void remplirCbxPilote () {
        ArrayList<Pilote> lesPilotes = Controleur.selectAllPilotes(""); 
        this.cmbIdPilote.removeAllItems(); 
        for (Pilote unPilote : lesPilotes) {
            this.cmbIdPilote.addItem(unPilote.getIdpilote() +"-" + unPilote.getNom()); 
        }
    }

    public void remplirCbxClasseVol () {
        String[] classes = {"Y Economie", "A Affaire", "Autres"};
        for (String classe : classes) {
            cmbClasseVol.addItem(classe);
        }
    }

    public Object[][] obtenirDonnees(String filtre) {
        ArrayList<Vol> lesVols = Controleur.selectAllVols(filtre);
        Object[][] matrice = new Object[lesVols.size()][10];
        int i = 0;
        for (Vol unVol : lesVols) {
            matrice[i][0] = unVol.getIdVol();
            matrice[i][1] = unVol.getNumeroVol();
            matrice[i][2] = unVol.getDateDepart();
            matrice[i][3] = unVol.getHeureDepart();
            matrice[i][4] = unVol.getHeureArrivee();
            matrice[i][5] = unVol.getVilleDepart();
            matrice[i][6] = unVol.getVilleArrivee();
            matrice[i][7] = unVol.getClasseVol();
            matrice[i][8] = unVol.getIdPilote();
            matrice[i][9] = unVol.getIdAvion();
            i++;
        }
        return matrice;
    }

    public void viderChamps() {
        this.txtNumeroVol.setText("");
        this.txtDateDepart.setText("");
        this.txtHeureDepart.setText("");
        this.txtHeureArrivee.setText("");
        this.txtVilleDepart.setText("");
        this.txtVilleArrivee.setText("");
        this.cmbClasseVol.setSelectedIndex(0);
        this.cmbIdPilote.setSelectedIndex(0); 
        this.cmbIdAvion.setSelectedIndex(0);
        this.btEnregistrer.setText("Enregistrer");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btAnnuler) {
            this.viderChamps();
        } else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")) {
            String numeroVol = this.txtNumeroVol.getText();
            String dateDepart = this.txtDateDepart.getText();
            String heureDepart = this.txtHeureDepart.getText();
            String heureArrivee = this.txtHeureArrivee.getText();
            String villeDepart = this.txtVilleDepart.getText();
            String villeArrivee = this.txtVilleArrivee.getText();
            String classeVol = this.cmbClasseVol.getSelectedItem().toString();
            
            String chaine = cmbIdPilote.getSelectedItem().toString(); 
            String tab[] = chaine.split("-");            
            int idpilote = Integer.parseInt(tab[0]);
            chaine = cmbIdAvion.getSelectedItem().toString(); 
            tab = chaine.split("-");            
            int idavion = Integer.parseInt(tab[0]);
            
            Vol unVol = new Vol(numeroVol ,dateDepart, heureDepart, heureArrivee,villeDepart,villeArrivee,classeVol ,idpilote,idavion);
            Controleur.insertVol(unVol);
            
            int idVol = Controleur.getLastInsertedVolId();
            
            JOptionPane.showMessageDialog(this, "Vol inséré avec succès dans la BDD");
            Object ligne[] = {idVol,numeroVol ,dateDepart, heureDepart, heureArrivee,villeDepart,villeArrivee,classeVol ,idpilote,idavion};
            this.unTableau.ajouterLigne(ligne);
            lbVols.setText("Nombre de vols disponibles : " + unTableau.getRowCount());
            this.viderChamps();
            
        } else if (e.getSource() == this.btFiltre) {
            String filtre = this.txtFiltre.getText();
            Object matrice[][] = this.obtenirDonnees(filtre);
            this.unTableau.setDonnees(matrice);
        } else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Modifier")) {
            String numeroVol = this.txtNumeroVol.getText();
            String dateDepart = this.txtDateDepart.getText();
            String heureDepart = this.txtHeureDepart.getText();
            String heureArrivee = this.txtHeureArrivee.getText();
            String villeDepart = this.txtVilleDepart.getText();
            String villeArrivee = this.txtVilleArrivee.getText();
            String classeVol = this.cmbClasseVol.getSelectedItem().toString();
            String chaine = cmbIdPilote.getSelectedItem().toString(); 
            String tab[] = chaine.split("-");            
            int idpilote = Integer.parseInt(tab[0]);
            chaine = cmbIdAvion.getSelectedItem().toString(); 
            tab = chaine.split("-");            
            int idavion = Integer.parseInt(tab[0]);
            int numLigne = tableVols.getSelectedRow();
            int idVol= Integer.parseInt(tableVols.getValueAt(numLigne, 0).toString());
            Vol unVol = new Vol(idVol,numeroVol ,dateDepart, heureDepart, heureArrivee,villeDepart,villeArrivee,classeVol ,idpilote,idavion);
            Controleur.updateVol(unVol);
            Object ligne[] = {idVol,numeroVol ,dateDepart, heureDepart, heureArrivee,villeDepart,villeArrivee,classeVol ,idpilote,idavion};
            this.unTableau.modifierLigne(numLigne, ligne);
            JOptionPane.showMessageDialog(this, "Modification effectuée");
            this.viderChamps();
            this.btEnregistrer.setText("Enregistrer");
        }
    }
}
