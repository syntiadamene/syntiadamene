package vue;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelPrincipal extends JPanel {
    public PanelPrincipal(Color uneCouleur) {
        this.setBackground(uneCouleur);
        this.setBounds(40, 100, 900, 440);
        this.setLayout(null);
        this.setVisible(false);

        // Création d'un JLabel pour afficher l'image
      
    }
}
