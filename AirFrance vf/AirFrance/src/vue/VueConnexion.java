package vue;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controleur.Controleur;
import controleur.AirFrance;
import controleur.Pilote;
import controleur.User;



public class VueConnexion extends JFrame implements ActionListener,KeyListener {
	
	
	 private JButton btConnexion = new JButton("Se Connecter");
	 private JButton btAnnuler = new JButton("Annuler");
	 
	 private JTextField txtEmail = new JTextField("a@gmail.com");
	 private JPasswordField txtMdp = new JPasswordField("123");
	 
	
     public VueConnexion() {
    	 this.setTitle("Air France");
    	 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	 this.setBounds(200, 200, 700, 340);
    	 this.getContentPane().setBackground(new Color (166, 216, 222 ));
         this.setLayout(null);
         this.setResizable(false);
         
         //installer le logo
         ImageIcon uneImage = new ImageIcon("src/images/logo.jpg");
         JLabel labelLogo =new JLabel(uneImage);
         labelLogo.setBounds(20, 20, 300, 250);
         this.add(labelLogo);
         
         //installation du panel connexion (div en html) et un surface 
         JPanel panelConnexion = new JPanel();
         panelConnexion.setBounds(360, 50, 300, 200);
         panelConnexion.setBackground(new Color (166, 216, 222 ));
         panelConnexion.setLayout(new GridLayout(3, 2));
         
         panelConnexion.add(new JLabel("Email:"));
         panelConnexion.add(this.txtEmail);
         panelConnexion.add(new JLabel("MDP:"));
         panelConnexion.add(this.txtMdp);
         panelConnexion.add(this.btAnnuler);
         panelConnexion.add(this.btConnexion);
         this.add(panelConnexion);
         
         //on rend les boutons ecoutables
         this.btAnnuler.addActionListener(this);
         this.btConnexion.addActionListener(this);
         
         
         //on rend les champs de saisie écoutables
         this.txtEmail.addKeyListener(this);
         this.txtMdp.addKeyListener(this);
         
        
         this.setVisible(true);
         
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btAnnuler) {
			this.txtEmail.setText("");
			this.txtMdp.setText("");
		}
		else if (e.getSource()==this.btConnexion) {
			this.traitement();
			
		}	
	}
	public void traitement() {
		String email= this.txtEmail.getText();
		String mdp =new String (this.txtMdp.getPassword());
		
		//on verifie dans la base de donnes à travers le modele
		User unUser = Controleur.selectWhereUser(email, mdp);
		if(unUser == null) {
			JOptionPane.showMessageDialog(this, "Veuillez vérifier vos identifiants!");
			this.txtEmail.setText("");
			this.txtMdp.setText("");
		}else {
		
		//on lance la vue généerale et on réduit la vue connexion .
		
		AirFrance.rendreVisibleGenerale(true, unUser);
		AirFrance.rendreVisibleConnexion(false);
		
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			this.traitement();
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}
