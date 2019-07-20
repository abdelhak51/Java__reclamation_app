import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Log {

	private JFrame frame;
	private JTextField nom;
	private JTextField pre;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField nom1;
	private JTextField pre1;
	private JPasswordField passwordField_2;
	private JComboBox comboBox;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {//interface authentification enseignant
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Log window = new Log();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Log() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 503, 325);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Authentification enseignant");

		frame.setResizable(false);
		JLayeredPane layeredPane = new JLayeredPane();
		frame.getContentPane().add(layeredPane, BorderLayout.CENTER);
		layeredPane.setLayout(new GridLayout(1,2));
		//partie identification
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 207, 261);
		panel_1.setBorder(BorderFactory.createTitledBorder("S'identifier"));
		panel_1.setLayout(null);
		layeredPane.add(panel_1);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(20, 30, 64, 14);
		panel_1.add(lblNom);
		
		JLabel lblPrenom = new JLabel("Prenom");
		lblPrenom.setBounds(20, 60, 64, 14);
		panel_1.add(lblPrenom);
		
		JLabel lblGrade = new JLabel("Grade");
		lblGrade.setBounds(20, 85, 64, 14);
		panel_1.add(lblGrade);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe");
		lblMotDePasse.setBounds(20, 113, 80, 14);
		panel_1.add(lblMotDePasse);
		
		JLabel lblConfirmer = new JLabel("Confirmer");
		lblConfirmer.setBounds(20, 140, 64, 14);
		panel_1.add(lblConfirmer);
		
		nom = new JTextField();
		nom.setBounds(101, 27, 130, 20);
		panel_1.add(nom);
		nom.setColumns(10);
		
		pre = new JTextField();
		pre.setBounds(101, 55, 130, 20);
		panel_1.add(pre);
		pre.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.setBounds(101, 80, 130, 20);
		comboBox.addItem("Prof"); 
	    comboBox.addItem("MCA"); 
	    comboBox.addItem("MCB"); 
	    comboBox.addItem("MAA"); 
	    comboBox.addItem("MAB"); 
	    comboBox.addItem("Autre"); 
	    
	   
		panel_1.add(comboBox);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(101, 110, 130, 20);
		panel_1.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(101, 135, 130, 20);
		panel_1.add(passwordField_1);
		
		JButton btnSidentifier = newButton1();
		btnSidentifier.setBounds(60, 168, 100, 23);
		panel_1.add(btnSidentifier);
		//partie connexion au compte
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(208, 0, 226, 261);
		panel_2.setBorder(BorderFactory.createTitledBorder("Se connecter"));
		panel_2.setLayout(null);
		layeredPane.add(panel_2);
		
		lblNom1 = new JLabel("Nom");
		lblNom1.setBounds(20, 30, 64, 14);
		panel_2.add(lblNom1);
		
		lblPrenom_1 = new JLabel("Prenom");
		lblPrenom_1.setBounds(20, 60, 64, 14);
		panel_2.add(lblPrenom_1);
		
		JLabel lblMotDePasse_1 = new JLabel("Mot de passe");
		lblMotDePasse_1.setBounds(20, 85, 80, 14);
		panel_2.add(lblMotDePasse_1);
		
		nom1 = new JTextField();
		nom1.setColumns(10);
		nom1.setBounds(101, 27, 130, 20);
		panel_2.add(nom1);
		
		pre1 = new JTextField();
		pre1.setColumns(10);
		pre1.setBounds(101, 55, 130, 20);
		panel_2.add(pre1);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(101, 82, 130, 20);
		panel_2.add(passwordField_2);
		
		JButton btnSeConnecter = newButton();
		btnSeConnecter.setBounds(60, 123, 110, 23);
		panel_2.add(btnSeConnecter);
	}
	JLabel lblNom1;
	JLabel lblPrenom_1;
	public static String nm;
	public static String pnm;
	public static String an;
	public static String sp;
	public static String sc;
	public static String mt;
	public static String g;
	
	private JButton newButton() {
		JButton btnSeConnecter=new JButton("Se connecter");
		btnSeConnecter.addActionListener(new ActionListener() {//si on appuie sur bouton se connecter le systeme verifie si les données saisies existes et on va vers l'acceuil de l'enseignant
			
			
			//connecter
			
			public void actionPerformed(ActionEvent ev) 
			{
				System.out.println("Test Connection to MySQL");
				
				Connection con =null;

				try {
					Class.forName("com.mysql.jdbc.Driver");
					
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/gestreclam", "root", "");
					
					System.out.println("Connection to DB created");
					Statement statment = con.createStatement();
					
					String sql  = "SELECT * FROM `enseignant` WHERE `nom` ='"+nom1.getText()+"'AND `prenom`='"+pre1.getText()+"'AND `mdp`='"+String.valueOf(passwordField_2.getPassword())+"'";
					ResultSet rs = statment.executeQuery(sql);
					
				    if(rs.next())
				   {	
				    	nm=rs.getString("nom");
				    	 pnm=rs.getString("prenom");
				    	 System.out.println("u r in");
				    	 AccEns.main(null);
						 frame.setVisible(false);
				         
				   }
				    else
				    {
				    	JOptionPane.showMessageDialog(null, "Champs sont vides ou pas bien remplis. Si non il faut s'identifier", "ERROR", JOptionPane.ERROR_MESSAGE);
				    }
				   /* rs.close();**/
				    statment.close();
				    con.close();	
				      
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		return btnSeConnecter;
	}
	
	
	//identifier
	
	
	private JButton newButton1() {
		JButton btnSidentifier=new JButton("S'identifier");
		btnSidentifier.addActionListener(new ActionListener() {//si on appuie sur bouton s'dentidier le systeme enregistre les données saisies dans la BDD et on va vers l'acceuil de l'enseignant
			
			
			public void actionPerformed(ActionEvent ev) {
				
				System.out.println("Test Connection to MySQL");
				try { //connection a la BDD
					Class.forName("com.mysql.jdbc.Driver");
					System.out.println("Driver loaded");
				} catch (ClassNotFoundException e) {
					System.out.println("Error loading jdbc driver");
					e.printStackTrace();
				}

				String s=(String)comboBox.getSelectedItem();
				
				try {
					Connection con =null;
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/gestreclam", "root", "");
					System.out.println("Connection to DB created");
				
					
					if(nom.getText().equals("") || pre.getText().equals("") || s.equals("") || String.valueOf(passwordField.getPassword()).equals(""))
					{
						new champVide();
					}
					else
					{
							if(String.valueOf(passwordField.getPassword()).equals(String.valueOf(passwordField_1.getPassword())))
							{	
								con=DriverManager.getConnection("jdbc:mysql://localhost:3306/gestreclam", "root", "");
								System.out.println("Connection to DB created");
							
								Statement statment = con.createStatement();
								System.out.println("Statement created");
							
								String sql;//inserer les informations saisies dans les champs pour creer un nouveau compte
							    sql = "INSERT INTO `enseignant`(`nom`, `prenom`, `grad`, `mdp`) VALUES ('"+nom.getText()+"','"+pre.getText()+"','"+s+"','"+String.valueOf(passwordField.getPassword())+"')";
							    statment.executeUpdate(sql);
							    nm=nom.getText();
							    pnm=pre.getText();

								AccEns.main(null);
								frame.setVisible(false);
							    statment.close();
							    con.close();
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Mot de passe non identique", "ERROR", JOptionPane.ERROR_MESSAGE);
							}
						
					}
					 
				      
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
				
		});
		return btnSidentifier;
	}
}
