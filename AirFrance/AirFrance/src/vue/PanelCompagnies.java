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
import controleur.Compagnie;
import controleur.Controleur;
import controleur.Tableau;

public class PanelCompagnies extends PanelPrincipal implements ActionListener {
	 private Compagnie uneCompagnie;

    private JPanel panelForm = new JPanel();
    private JTextField txtLibelle = new JTextField();

    private JTextField txtPays = new JTextField();

    private JButton btAnnuler = new JButton("Annuler");
    private JButton btEnregistrer = new JButton("Enregistrer");

    private JTable tableCompagnies;
    private JScrollPane uneScroll;
    private Tableau unTableau;

    private JPanel panelRech = new JPanel();
    private JTextField txtFiltre = new JTextField();
    private JButton btFiltre = new JButton("Filtrer");
    private JLabel lbCompagnies;

    public PanelCompagnies() {
        super(new Color(166, 216, 222));

        this.panelForm.setBounds(50, 50, 300, 250);
        this.panelForm.setBackground(new Color(166, 216, 222));
        this.panelForm.setLayout(new GridLayout(6, 2));
        this.panelForm.add(new JLabel("Libelle :"));
        this.panelForm.add(this.txtLibelle);
        this.panelForm.add(new JLabel("Pays :"));
        this.panelForm.add(this.txtPays);
      
        this.panelForm.add(this.btAnnuler);
        this.panelForm.add(this.btEnregistrer);
        this.add(this.panelForm);
        this.panelForm.setVisible(true);

        String entetes[] = {"ID Compagnie", "Libelle",  "Pays"};
        this.unTableau = new Tableau(entetes, this.obtenirDonnees(""));
        this.tableCompagnies = new JTable(this.unTableau);
        this.uneScroll = new JScrollPane(this.tableCompagnies);
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
        this.tableCompagnies.getTableHeader().setReorderingAllowed(false);

        this.tableCompagnies.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	
            	
                int numLigne = 0;
                int idCompagnie = 0;
                if (e.getClickCount() >= 2) {
               numLigne = tableCompagnies.getSelectedRow();
                //if (numLigne >= 0 && numLigne < unTableau.getRowCount()) {
                     idCompagnie = Integer.parseInt(tableCompagnies.getValueAt(numLigne, 0).toString());
                     int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer cette compagnie ? ",
                             "Suppression Compagnie", JOptionPane.YES_NO_OPTION);
                     if (reponse == 0) {

                         Controleur.deleteCompagnie(idCompagnie);
                         unTableau.supprimerLigne(numLigne);
                         lbCompagnies.setText("Nombre de Compagnies disponibles : " + unTableau.getRowCount());
                     }
                 } else {
                	 numLigne = tableCompagnies.getSelectedRow();
                	  idCompagnie = Integer.parseInt(tableCompagnies.getValueAt(numLigne, 0).toString());
                    String libelle = tableCompagnies.getValueAt(numLigne, 1).toString();
                    String pays = tableCompagnies.getValueAt(numLigne, 2).toString();
                   

                    txtLibelle.setText(libelle);
                    
                    txtPays.setText(pays);
                  
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

        int nbCompagnies = this.unTableau.getRowCount();
        lbCompagnies = new JLabel("Nombre de compagnies disponibles : " + nbCompagnies);
        lbCompagnies.setBounds(300, 360, 300, 20);
        this.add(lbCompagnies);
    }

    public Object[][] obtenirDonnees(String filtre) {
        ArrayList<Compagnie> lesCompagnies = Controleur.selectAllCompagnies(filtre);
        Object[][] matrice = new Object[lesCompagnies.size()][6];
        int i = 0;
        for (Compagnie uneCompagnie : lesCompagnies) {
            matrice[i][0] = uneCompagnie.getIdcompagnie();
            matrice[i][1] = uneCompagnie.getLibelle();
            matrice[i][2] = uneCompagnie.getPays();
  
            i++;
        }
        return matrice;
    }

    public void viderChamps() {
        this.txtLibelle.setText("");
     
        this.txtPays.setText("");
   
        this.btEnregistrer.setText("Enregistrer");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btAnnuler) {
            this.viderChamps();
        } else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")) {
            String libelle = this.txtLibelle.getText();
          
            String pays = this.txtPays.getText();
        
           uneCompagnie = new Compagnie(libelle, pays);
            Controleur.insertCompagnie(uneCompagnie);

            // Récupérer l'ID de l'aéroport nouvellement inséré
            int idCompagnie = Controleur.getLastInsertedCompagnieId();

            JOptionPane.showMessageDialog(this, "Aéroport inséré avec succès dans la BDD");

            Object ligne[] = {idCompagnie, libelle, pays};
            this.unTableau.ajouterLigne(ligne);
            lbCompagnies.setText("Nombre de compagnies disponibles : " + unTableau.getRowCount());

            this.viderChamps();
        } else if (e.getSource() == this.btFiltre) {
            String filtre = this.txtFiltre.getText();
            Object matrice[][] = this.obtenirDonnees(filtre);
            this.unTableau.setDonnees(matrice);
        } else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Modifier")) {
            String libelle = this.txtLibelle.getText();
            
            String pays = this.txtPays.getText();
          
            int numLigne = tableCompagnies.getSelectedRow();
            int idCompagnie = Integer.parseInt(tableCompagnies.getValueAt(numLigne, 0).toString());

            Compagnie uneCompagnie = new Compagnie(idCompagnie, libelle, pays);
            Controleur.updateCompagnie(uneCompagnie);

            Object ligne[] = {idCompagnie, libelle, pays};
            this.unTableau.modifierLigne(numLigne, ligne);
            JOptionPane.showMessageDialog(this, "Modification effectuée");
            this.viderChamps();
            this.btEnregistrer.setText("Enregistrer");
        }
    }
}
