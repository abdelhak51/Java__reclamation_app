
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JSeparator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;


import java.sql.SQLException;

import javax.swing.JPasswordField;



public class LogSign extends JFrame {//interface authentification administrateur

	private JPanel contentPane;
	private JTextField text2;
	private JTextField text3;
	private JTextField Pseudo;
	private JPasswordField passwordField;
	private JPasswordField pass1;
	Connection con=null;
	PreparedStatement prepared=null;
	ResultSet resultat=null;
	private JTextField text1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogSign frame = new LogSign();
				//	frame.setUndecorated(false);
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setTitle("Authentification administrateur");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LogSign() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 658, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		con=connect.getConnection();   // object de connection 
		// connecter a un compte
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 287, 416);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Pseudo");
		lblNewLabel_3.setBounds(10, 72, 43, 34);
		panel.add(lblNewLabel_3);
		
		JLabel lblMotDePasse = new JLabel("Mot de Passe");
		lblMotDePasse.setBounds(10, 158, 87, 34);
		panel.add(lblMotDePasse);
		
		Pseudo = new JTextField();
		Pseudo.setBounds(90, 72, 177, 34);
		panel.add(Pseudo);
		Pseudo.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Se connecter");
		lblNewLabel_4.setBounds(10, 0, 97, 34);
		panel.add(lblNewLabel_4);
		
		JButton btnNewButton_1 = new JButton("Se connecter");
		btnNewButton_1.addActionListener(new ActionListener() {//si on appuie sur bouton se connecter le systeme verifie si les données saisies existes et on va vers l'acceuil de l'administrateur
			public void actionPerformed(ActionEvent e) {
				String name=Pseudo.getText().toString();
				String psw=passwordField.getText().toString();
				
				String sql=("select pseudo,mdp from admin");
				if( Pseudo.getText().equals("") || String.valueOf(passwordField.getPassword()).equals(""))
				{				
					JOptionPane.showMessageDialog(null, "Champs est vides ou pas bien remplis. Si non il faut s'identifier", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					try {
						prepared = con.prepareStatement(sql);
						resultat=prepared.executeQuery();
						int i=0;boolean b=false;
						while (resultat.next())
						{
							 String name1=resultat.getString("pseudo");
							 String psw1=resultat.getString("mdp");
							 
							 if((name1).equals(name) && (psw1).equals(psw))
							 {
								 b=true;
								  JOptionPane.showMessageDialog(null,"vous etes connecter ");
								
								  Accueil c=new Accueil();
								  c.setVisible(true);dispose();
							
						     }
					    }
						if(!b)
				          {
					         JOptionPane.showMessageDialog(null,"invalide pseudo ou mot de passe  ");
			              }
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
				
		        }});
		
		btnNewButton_1.setBounds(100, 249, 120, 34);
		panel.add(btnNewButton_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(90, 158, 177, 34);
		panel.add(passwordField);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(286, 0, 356, 416);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Prenom");
		lblNewLabel_1.setBounds(10, 131, 69, 20);
		panel_1.add(lblNewLabel_1);
		
		//s'identifier
		JLabel lblNewLabel = new JLabel("Pseudo");
		lblNewLabel.setBounds(10, 209, 82, 14);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Mot de Passe");
		lblNewLabel_2.setBounds(10, 274, 100, 20);
		panel_1.add(lblNewLabel_2);
		
		text2 = new JTextField();
		text2.setBounds(100, 122, 200, 38);
		panel_1.add(text2);
		text2.setColumns(10);
		
		text3 = new JTextField();
		text3.setBounds(100, 197, 200, 38);
		panel_1.add(text3);
		text3.setColumns(10);
		
		JButton btnNewButton = new JButton("S'identifier");
		btnNewButton.addActionListener(new ActionListener() {//si on appuie sur bouton s'identifier le systeme enregistre les données saisies dans la BDD et on va vers l'acceuil de l'administrateur
			public void actionPerformed(ActionEvent e) {
				//inserer les informations saisies lors de l'identification
				String sql=("insert into admin (nom,prenom,pseudo,mdp) values(?,?,?,?)");
				if(text1.getText().equals("") || text2.getText().equals("") || text3.getText().equals("") || String.valueOf(pass1.getPassword()).equals(""))
				{
					new champVide();
				}
				else
				{
					try {
						prepared=con.prepareStatement(sql);
						prepared.setString(1,text1.getText().toString());
						prepared.setString(2,text2.getText().toString());
						prepared.setString(3,text3.getText().toString());
						prepared.setString(4,pass1.getText().toString());
						prepared.execute();
						
						JOptionPane.showMessageDialog(null,"Ajoute avec Succes");
						
						Accueil c=new Accueil();
						c.setVisible(true);dispose();
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		btnNewButton.setBounds(120, 334, 119, 38);
		panel_1.add(btnNewButton);
		
		JLabel lblNewLabel_5 = new JLabel("S'identifier");
		lblNewLabel_5.setBounds(10, 0, 82, 26);
		panel_1.add(lblNewLabel_5);
		
		/*JLabel lblNewLabel_6 = new JLabel("X");
		lblNewLabel_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblNewLabel_6.setFont(lblNewLabel_6.getFont().deriveFont(lblNewLabel_6.getFont().getSize() + 10f));
		lblNewLabel_6.setForeground(Color.RED);
		lblNewLabel_6.setBounds(340, 2, 56, 14);
		panel_1.add(lblNewLabel_6);*/
		
		pass1 = new JPasswordField();
		pass1.setBounds(100, 274, 200, 38);
		panel_1.add(pass1);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(21, 62, 46, 14);
		panel_1.add(lblNom);
		
		text1 = new JTextField();
		text1.setBounds(100, 50, 200, 38);
		panel_1.add(text1);
		text1.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setForeground(Color.BLACK);
		separator.setBounds(286, 415, 1, -414);
		contentPane.add(separator);
	}
}
