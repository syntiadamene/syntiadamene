package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controleur.Controleur;
import controleur.ViewPilote;
import controleur.Tableau;

public class PanelStats extends PanelPrincipal {
    private JTable tableView;
    private JScrollPane uneScroll;
    private Tableau unTableau;

    public PanelStats() {
        super(new Color(166, 216, 222));

        String entetes[] = { "Nom", "Prenom", "Nb Vols", "Total Heures Vols" };
        this.unTableau = new Tableau(entetes, this.obtenirDonnees());
        this.tableView = new JTable(this.unTableau);
        this.uneScroll = new JScrollPane(tableView);

        ImageIcon imageIcon = new ImageIcon("src/images/stats.png");
        Image image = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel labelImage = new JLabel(new ImageIcon(image));

        this.uneScroll.setBounds(80, 80, 300, 200);
        labelImage.setBounds(400, 80, 100, 100); // Position de l'image à ajuster selon vos besoins
        labelImage.setPreferredSize(new Dimension(100, 100)); // Ajustement de la taille de la JLabel

        this.add(this.uneScroll);
        this.add(labelImage);
    }

    public Object[][] obtenirDonnees() {
        ArrayList<ViewPilote> lesViewPilotes = Controleur.selectAllViewPilotes();
        Object[][] matrice = new Object[lesViewPilotes.size()][4];
        int i = 0;
        for (ViewPilote unViewPilote : lesViewPilotes) {
            matrice[i][0] = unViewPilote.getNom();
            matrice[i][1] = unViewPilote.getPrenom();
            matrice[i][2] = unViewPilote.getNombre_de_vols();
            matrice[i][3] = unViewPilote.getTotalHeures();
            i++;
        }
        return matrice;
    }
}
