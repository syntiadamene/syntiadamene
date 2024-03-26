package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controleur.AirFrance;
import controleur.User;

public class VueGenerale extends JFrame implements ActionListener {
    private JButton btProfil = new JButton("Profil", resizeIcon("src/images/profil.png", 18, 18));
    private JButton btPilote = new JButton("Pilotes", resizeIcon("src/images/pilote.jpeg", 18, 18));
    private JButton btAeroport = new JButton("Aeroports", resizeIcon("src/images/aeroport.jpeg", 18, 18));
    private JButton btVols = new JButton("Vols", resizeIcon("src/images/vol.jpeg", 18, 18));
    private JButton btAvion = new JButton("Avions", resizeIcon("src/images/avion.jpeg", 18, 18));
    private JButton btCompagnie = new JButton("Compagnies", resizeIcon("src/images/compagnie.png", 18, 18));

    private JButton btStats = new JButton("Stats", resizeIcon("src/images/stats.png", 18, 18));

    private JButton btQuitter= new JButton("Quitter", resizeIcon("src/images/deconnexion.png", 18, 18));

    private JPanel panelMenu = new JPanel();
    private static PanelProfil unPanelProfil;
    private static PanelPilotes unPanelPilotes = new PanelPilotes();
    private static PanelAvions unPanelAvions= new PanelAvions();
    private static PanelAeroports unPanelAeroports = new PanelAeroports();
    private static PanelVols unPanelVols = new PanelVols();
    private static PanelCompagnies unPanelCompagnies= new PanelCompagnies();
    private static PanelStats unPanelStats = new PanelStats();

    public VueGenerale(User unUser) {
        unPanelProfil = new PanelProfil(unUser);
        this.setTitle("Air France");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 1000, 600);
        this.getContentPane().setBackground(new Color(166, 216, 222));
        this.setLayout(null);
        this.setResizable(false);
        
        // Installation du panel menu 
        panelMenu.setBounds(100, 20, 800, 30);
        panelMenu.setLayout(new GridLayout(1, 6));
        panelMenu.setBackground(new Color(166, 216, 222));
        
        // Rendre les boutons cliquables
        btQuitter.addActionListener(this);
        btPilote.addActionListener(this);
        btAvion.addActionListener(this);
        btAeroport.addActionListener(this);
        btVols.addActionListener(this);
        btCompagnie.addActionListener(this);
        btStats.addActionListener(this);
        btProfil.addActionListener(this);
        
        // Ajout des pannels dans la fenêtre
        this.add(unPanelProfil);
        this.add(unPanelPilotes);
        this.add(unPanelAvions);
        this.add(unPanelAeroports);
        this.add(unPanelVols);
        this.add(unPanelCompagnies);
        this.add(unPanelStats);
        
        // Personnalisation des boutons
        JButton[] boutons = { btProfil, btPilote, btAeroport, btVols, btAvion, btCompagnie, btStats, btQuitter };
        for (JButton bouton : boutons) {
            bouton.setHorizontalTextPosition(JButton.CENTER);
            bouton.setVerticalTextPosition(JButton.BOTTOM);
            panelMenu.add(bouton);
        }
        
        this.add(panelMenu);
        this.setVisible(true);
    }

    public void afficher(int choix) {
        unPanelProfil.setVisible(false);
        unPanelPilotes.setVisible(false);
        unPanelAvions.setVisible(false);
        unPanelAeroports.setVisible(false);
        unPanelVols.setVisible(false);
        unPanelCompagnies.setVisible(false);
        unPanelStats.setVisible(false);

        switch (choix) {
            case 1:
                unPanelProfil.setVisible(true);
                break;
            case 2:
                unPanelPilotes.setVisible(true);
                break;
            case 3:
                unPanelAvions.setVisible(true);
                break;
            case 4:
                unPanelAeroports.setVisible(true);
                break;
            case 5:
                unPanelVols.setVisible(true);
                break;
            case 6:
                unPanelCompagnies.setVisible(true);
                break;
            case 7:
                unPanelStats.setVisible(true);
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.btQuitter == e.getSource()) {
            AirFrance.rendreVisibleGenerale(false, null);
            AirFrance.rendreVisibleConnexion(true);
        } else if (e.getSource() == this.btProfil) {
            afficher(1);
        } else if (e.getSource() == this.btPilote) {
            afficher(2);
        } else if (e.getSource() == this.btAvion) {
            afficher(3);
        } else if (e.getSource() == this.btAeroport) {
            afficher(4);
        } else if (e.getSource() == this.btVols) {
            afficher(5);
        } else if (e.getSource() == this.btCompagnie) {
            afficher(6);
        } else if (e.getSource() == this.btStats) {
            afficher(7);
        }
    }

    private static ImageIcon resizeIcon(String filePath, int width, int height) {
        ImageIcon icon = new ImageIcon(filePath);
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }
}
