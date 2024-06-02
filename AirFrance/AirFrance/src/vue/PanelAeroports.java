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
import controleur.Aeroport;
import controleur.Controleur;
import controleur.Tableau;
import javax.swing.JComboBox;

public class PanelAeroports extends PanelPrincipal implements ActionListener {

    private JPanel panelForm = new JPanel();
    private JTextField txtNom = new JTextField();
    private JTextField txtCode = new JTextField();
    private JTextField txtVille = new JTextField();
    private JTextField txtPays = new JTextField();
    private JComboBox<String> cbStatut = new JComboBox<>(new String[]{"national", "international", "autre"});

    private JButton btAnnuler = new JButton("Annuler");
    private JButton btEnregistrer = new JButton("Enregistrer");

    private JTable tableAeroports;
    private JScrollPane uneScroll;
    private Tableau unTableau;

    private JPanel panelRech = new JPanel();
    private JTextField txtFiltre = new JTextField();
    private JButton btFiltre = new JButton("Filtrer");
    private JLabel lbAeroports;

    public PanelAeroports() {
        super(new Color(166, 216, 222));

        this.panelForm.setBounds(50, 50, 300, 250);
        this.panelForm.setBackground(new Color(166, 216, 222));
        this.panelForm.setLayout(new GridLayout(6, 2));
        this.panelForm.add(new JLabel("Nom :"));
        this.panelForm.add(this.txtNom);
        this.panelForm.add(new JLabel("Code :"));
        this.panelForm.add(this.txtCode);
        this.panelForm.add(new JLabel("Ville :"));
        this.panelForm.add(this.txtVille);
        this.panelForm.add(new JLabel("Pays :"));
        this.panelForm.add(this.txtPays);
        
        this.panelForm.add(new JLabel("Statut :"));
        this.panelForm.add(this.cbStatut);

        this.panelForm.add(this.btAnnuler);
        this.panelForm.add(this.btEnregistrer);
        this.add(this.panelForm);
        this.panelForm.setVisible(true);

        String entetes[] = {"ID Aéroport", "Nom", "Code", "Ville", "Pays", "Statut"};
        this.unTableau = new Tableau(entetes, this.obtenirDonnees(""));
        this.tableAeroports = new JTable(this.unTableau);
        this.uneScroll = new JScrollPane(this.tableAeroports);
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
        this.tableAeroports.getTableHeader().setReorderingAllowed(false);

        this.tableAeroports.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int numLigne = 0;
                int idAeroport = 0;
                if (e.getClickCount() >= 2) {

                    numLigne = tableAeroports.getSelectedRow();
                    idAeroport = Integer.parseInt(tableAeroports.getValueAt(numLigne, 0).toString());
                    int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer cet aeroport ? ",
                            "Suppression Aerport", JOptionPane.YES_NO_OPTION);
                    if (reponse == 0) {

                        Controleur.deleteAeroport(idAeroport);
                        unTableau.supprimerLigne(numLigne);
                        lbAeroports.setText("Nombre de Aeroports disponibles : " + unTableau.getRowCount());
                    }
                } else {

                    numLigne = tableAeroports.getSelectedRow();
                    idAeroport = Integer.parseInt(tableAeroports.getValueAt(numLigne, 0).toString());

                    String nom = tableAeroports.getValueAt(numLigne, 1).toString();
                    String code = tableAeroports.getValueAt(numLigne, 2).toString();
                    String ville = tableAeroports.getValueAt(numLigne, 3).toString();
                    String pays = tableAeroports.getValueAt(numLigne,4).toString();
                    String statut = tableAeroports.getValueAt(numLigne, 5).toString();

                    txtNom.setText(nom);
                    txtCode.setText(code);
                    txtVille.setText(ville);
                    txtVille.setText(pays);
                    txtVille.setText(statut);
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

        int nbAeroports = this.unTableau.getRowCount();
        lbAeroports = new JLabel("Nombre d'aéroports disponibles : " + nbAeroports);
        lbAeroports.setBounds(300, 360, 300, 20);
        this.add(lbAeroports);
    }

    public Object[][] obtenirDonnees(String filtre) {
        ArrayList<Aeroport> lesAeroports = Controleur.selectAllAeroports(filtre);
        Object[][] matrice = new Object[lesAeroports.size()][6];
        int i = 0;
        for (Aeroport unAeroport : lesAeroports) {
            matrice[i][0] = unAeroport.getIdaeroport();
            matrice[i][1] = unAeroport.getNom();
            matrice[i][2] = unAeroport.getCode();
            matrice[i][3] = unAeroport.getVille();
            matrice[i][4] = unAeroport.getPays();
            matrice[i][5] = unAeroport.getStatut();
            i++;
        }
        return matrice;
    }
    
    
    

    public void viderChamps() {
        this.txtNom.setText("");
        this.txtCode.setText("");
        this.txtVille.setText("");
        this.txtPays.setText("");
        this.cbStatut.setSelectedIndex(0);
        this.btEnregistrer.setText("Enregistrer");
    }

   
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btAnnuler) {
            this.viderChamps();
        } else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")) {
            String nom = this.txtNom.getText();
            String code = this.txtCode.getText();
            String ville = this.txtVille.getText();
            String pays = this.txtPays.getText();
            String statut = this.cbStatut.getSelectedItem().toString();


            Aeroport unAeroport = new Aeroport(nom, code, ville, pays, statut);
            Controleur.insertAeroport(unAeroport);

            // Récupérer l'ID de l'aéroport nouvellement inséré
            int idAeroport = Controleur.getLastInsertedAeroportId();

            JOptionPane.showMessageDialog(this, "Aéroport inséré avec succès dans la BDD");

            Object ligne[] = {idAeroport, nom, code, ville, pays, statut};
            this.unTableau.ajouterLigne(ligne);
            lbAeroports.setText("Nombre d'aéroports disponibles : " + unTableau.getRowCount());

            this.viderChamps();
        } else if (e.getSource() == this.btFiltre) {
            String filtre = this.txtFiltre.getText();
            Object matrice[][] = this.obtenirDonnees(filtre);
            this.unTableau.setDonnees(matrice);
        } else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Modifier")) {
            String nom = this.txtNom.getText();
            String code = this.txtCode.getText();
            String ville = this.txtVille.getText();
            String pays = this.txtPays.getText();
            
            
            String statut = this.cbStatut.getSelectedItem().toString(); // Utiliser la valeur sélectionnée dans la JComboBox
            
            
            int numLigne = tableAeroports.getSelectedRow();
            int idAeroport = Integer.parseInt(tableAeroports.getValueAt(numLigne, 0).toString());

            Aeroport unAeroport = new Aeroport(idAeroport, nom, code, ville, pays, statut);
            Controleur.updateAeroport(unAeroport);

            Object ligne[] = {idAeroport, nom, code, ville, pays, statut};
            this.unTableau.modifierLigne(numLigne, ligne);
            JOptionPane.showMessageDialog(this, "Modification effectuée");
            this.viderChamps();
            this.btEnregistrer.setText("Enregistrer");
        }
    }
}