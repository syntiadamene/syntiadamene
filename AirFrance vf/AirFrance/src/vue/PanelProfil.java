package vue ; 
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import controleur.Controleur;
import controleur.User;

public class PanelProfil extends PanelPrincipal implements ActionListener {

    private JTextArea txtInfos = new JTextArea();
    private JButton btnModifier = new JButton("Modifier");
    private JPanel panelForm = new JPanel();
    private JTextField txtNom = new JTextField();
    private JTextField txtPrenom = new JTextField();
    private JTextField txtEmail = new JTextField();
    private JPasswordField txtMdp = new JPasswordField();
    private JTextField txtRole = new JTextField();
    private JButton btnAnnuler = new JButton("Annuler");
    private JButton btnEnregistrer = new JButton("Enregistrer");
    private User unUser;

    // Constantes pour les couleurs
    private static final Color PANEL_COLOR = new Color(166, 216, 222);
    private static final Color TEXTAREA_COLOR = new Color(166, 216, 222);

    public PanelProfil(User unUser) {
        super(PANEL_COLOR);
        this.unUser = unUser;

        // Texte d'informations
        txtInfos.setEditable(false);
        txtInfos.setBackground(TEXTAREA_COLOR);
        updateInfosText();

        // Panel Form
        panelForm.setBackground(PANEL_COLOR);
        panelForm.setLayout(new GridLayout(7, 2));
        addFormField("Nom :", txtNom);
        addFormField("Prénom :", txtPrenom);
        addFormField("Email :", txtEmail);
        addFormField("Mot de passe :", txtMdp);
        addFormField("Rôle :", txtRole);
        panelForm.add(btnAnnuler);
        panelForm.add(btnEnregistrer);
        panelForm.setVisible(false);

        // Bouton Modifier
        btnModifier.addActionListener(this);
        btnModifier.setBackground(Color.WHITE);
        btnModifier.setForeground(Color.BLUE);
        btnModifier.setFocusPainted(false);

        // Boutons Annuler et Enregistrer
        btnAnnuler.addActionListener(this);
        btnEnregistrer.addActionListener(this);

        // Remplir les champs avec les données de l'utilisateur
        txtNom.setText(unUser.getNom());
        txtPrenom.setText(unUser.getPrenom());
        txtEmail.setText(unUser.getEmail());
        txtRole.setText(unUser.getRole());
        txtMdp.setText(unUser.getMdp());

        // Ajouter les composants au panel principal
        setLayout(null);
        add(txtInfos);
        add(panelForm);
        add(btnModifier);

        // Définir les positions et les tailles des composants
        txtInfos.setBounds(40, 50, 260, 200);
        panelForm.setBounds(350, 50, 450, 300);
        btnModifier.setBounds(100, 300, 100, 30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnModifier) {
            panelForm.setVisible(true);
        } else if (e.getSource() == btnAnnuler) {
            panelForm.setVisible(false);
            resetFormFields();
        } else if (e.getSource() == btnEnregistrer) {
            updateUserFromForm();
            Controleur.updateUser(unUser);
            JOptionPane.showMessageDialog(this, "Modification effectuée");
            panelForm.setVisible(false);
            updateInfosText();
        }
    }

    private void addFormField(String label, JTextField field) {
        JLabel lbl = new JLabel(label);
        panelForm.add(lbl);
        panelForm.add(field);
    }

    private void resetFormFields() {
        txtNom.setText(unUser.getNom());
        txtPrenom.setText(unUser.getPrenom());
        txtEmail.setText(unUser.getEmail());
        txtRole.setText(unUser.getRole());
        txtMdp.setText(unUser.getMdp());
    }

    private void updateUserFromForm() {
        unUser.setNom(txtNom.getText());
        unUser.setPrenom(txtPrenom.getText());
        unUser.setEmail(txtEmail.getText());
        unUser.setMdp(new String(txtMdp.getPassword()));
        unUser.setRole(txtRole.getText());
    }

    private void updateInfosText() {
        String infos = "Informations de votre profil \n\n     " +
    
        		
                       
                       "    Nom : " + unUser.getNom() + "\n" +
                       
                       
                       
                       "     Prénom : " + unUser.getPrenom() + "\n" +
                       "   Email : " + unUser.getEmail() + "\n" +
                       "    Rôle : " + unUser.getRole();

        txtInfos.setText(infos);
        txtInfos.setForeground(Color.WHITE); // Couleur du texte en blanc
        txtInfos.setFont(new Font("Arial", Font.BOLD, 12)); // Police en gras
        txtInfos.setEditable(false); // Empêche la modification du texte
        txtInfos.setOpaque(false); // Rend la zone de texte transparente
        txtInfos.setBounds(0, 0, getWidth(), getHeight()); // Centre le texte au milieu du composant
        txtInfos.setAlignmentX(CENTER_ALIGNMENT); // Centre le texte horizontalement
        txtInfos.setAlignmentY(CENTER_ALIGNMENT); // Centre le texte verticalement
    }
}
