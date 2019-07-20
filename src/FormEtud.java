import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class FormEtud extends JFrame implements ActionListener{
	JTextField inom;
	JTextField iprenom;
	JTextField imat;
	JTextField iannee;
	JTextField ispec;
	JTextField isec;
	JTextField igrp;
	JPasswordField ipw;
	
	public FormEtud() {//interface authentification etudiant
		
		JLayeredPane p=new JLayeredPane();
		JPanel p1=new JPanel();
		JPanel p2=new JPanel();
		
		JButton bl=new JButton("Se connecter");
		bl.addActionListener(this);
		JButton bs=new JButton("S'identifier");
		bs.addActionListener(this);
		//identifier
		JLabel nom=new JLabel("Nom");
		 inom=new JTextField(10);
		nom.setBounds(50,25,50,25);
		inom.setBounds(110,25,150,25);
		p1.add(nom);
		p1.add(inom);
		
		JLabel prenom=new JLabel("Prenom");
		iprenom=new JTextField(10);
		prenom.setBounds(50,60,50,25);
		iprenom.setBounds(110,60,150,25);
		p1.add(prenom);
		p1.add(iprenom);
		
		JLabel mat=new JLabel("Matricule");
		imat=new JTextField(15);
		mat.setBounds(50,95,80,25);
		imat.setBounds(110,95,150,25);
		p1.add(mat);
		p1.add(imat);
		
		JLabel annee=new JLabel("Année");
		iannee=new JTextField(10);
		annee.setBounds(50,130,80,25);
		iannee.setBounds(110,130,150,25);
		p1.add(annee);
		p1.add(iannee);
		
		JLabel spec=new JLabel("Specialité");
		 ispec=new JTextField(10);
		spec.setBounds(50,165,80,25);
		ispec.setBounds(110,165,150,25);
		p1.add(spec);
		p1.add(ispec);
		
		JLabel sec=new JLabel("Section");
		isec=new JTextField(1);
		sec.setBounds(50,200,80,25);
		isec.setBounds(110,200,30,25);
		p1.add(sec);
		p1.add(isec);
		
		JLabel grp=new JLabel("Groupe");
		igrp=new JTextField(2);
		grp.setBounds(150,200,80,25);
		igrp.setBounds(200,200,30,25);
		p1.add(grp);
		p1.add(igrp);
		
		JLabel pw=new JLabel("Mot de passe");
		 ipw=new JPasswordField(10);
		pw.setBounds(50,235,80,25);
		ipw.setBounds(130,235,150,25);
		p1.add(pw);
		p1.add(ipw);
		
		bs.setBounds(100,270,130,25);
		p1.add(bs);
		
		p1.setLayout(null);
		p1.setPreferredSize(new Dimension(300, 310));
		p1.setBorder(BorderFactory.createTitledBorder("S'identifier"));
		
		
		//connecter
		
		JLabel mat1=new JLabel("Matricule");
		
		mat1.setBounds(50,25,80,25);
		imat1.setBounds(130,25,150,25);
		p2.add(mat1);
		p2.add(imat1);
		
		JLabel pw1=new JLabel("Mot de passe");
		 ipw1=new JPasswordField(10);
		pw1.setBounds(50,60,80,25);
		ipw1.setBounds(130,60,150,25);
		p2.add(pw1);
		p2.add(ipw1);
		
		
		bl.setBounds(100,95,130,25);
		p2.add(bl);
		
		p2.setLayout(null);
		p2.setPreferredSize(new Dimension(300, 310));
		p2.setBorder(BorderFactory.createTitledBorder("Se connecter"));
		
		p.setLayout(new GridLayout(1,2));
		
		p.add(p1);
		p.add(p2);
		
		f.setSize(700,350);
		f.setTitle("Authentification étudiant");
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setContentPane(p);
		f.setResizable(false);
		f.setVisible(true);
		
		ipw1.setEditable(true);
	}
	
	JFrame f=new JFrame();
	static public JTextField imat1=new JTextField(15);
	JPasswordField ipw1=new JPasswordField();
	
	
	public static void main(String[] args) {
		FormEtud fe=new FormEtud();

	}

	public static String nm;
	public static String pnm;
	public static String an;
	public static String sp;
	public static String sc;
	public static String mt;
	public static String g;
	  
	public void actionPerformed(ActionEvent ev) 
	{ 
		
		if(ev.getActionCommand().equals("Se connecter"))//si on appuie sur bouton se connecter le systeme verifie si les données saisies existes et on va vers l'acceuil de l'etudiant
		{
				System.out.println("Test Connection to MySQL");
				

				System.out.println("mat "+imat1.getText()+" pw"+String.valueOf(ipw1.getPassword()));
				Connection con ;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/gestreclam", "root", "");
					System.out.println("Connection to DB created");
					
					Statement statment = con.createStatement();
					
					String sql  = "SELECT * FROM `etudiant` where `matricule` ='"+imat1.getText()+"'and `mdp`='"+String.valueOf(ipw1.getPassword())+"'";
				    ResultSet rs = statment.executeQuery(sql);
					
				    if(rs.next())
				   {	
				    	 System.out.println("u r in");
				    	  nm=rs.getString("nom");
				    	 pnm=rs.getString("prenom");
				    	 an=rs.getString("annee");
				    	 sp=rs.getString("specialite");
				    	 sc=rs.getString("section");
				    	 g=rs.getString("groupe");
				    	 mt= imat1.getText();
				    			 
				    	 System.out.println(nm+"  "+pnm+"  "+mt+"  "+an+"  "+sp+"  "+sc+"  "+g);
				         AccEtud a=new AccEtud();
				         f.setVisible(false);
				         
				   }
				    else
				    {
				    	JOptionPane.showMessageDialog(null, "Champs est vides ou pas bien remplis. Si non il faut s'identifier", "ERROR", JOptionPane.ERROR_MESSAGE);
				    }
				      
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Champs est vides ou pas bien remplis. Si non il faut s'identifier", "ERROR", JOptionPane.ERROR_MESSAGE);
				    
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}
		
		if(ev.getActionCommand().equals("S'identifier"))//si on appuie sur bouton s'dentifier le systeme enregistre les données saisies dans la base de données et on va vers l'acceuil de l'etudiant
			
		{
			System.out.println("Test Connection to MySQL");
			try {
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("Driver loaded");
			} catch (ClassNotFoundException e) {
				System.out.println("Error loading jdbc driver");
				e.printStackTrace();
			}
			
			String url = "jdbc:mysql://localhost:3306/gestreclam";
			String user = "root";
			String password = "";
			
			Connection con =null;
			try {
				con=DriverManager.getConnection(url, user, password);
				System.out.println("Connection to DB created");
				
				Statement statment = con.createStatement();
				System.out.println("Statement created");
				
				String sql; // inserer les informations saisies dans la BDD 
			    sql = "INSERT INTO `etudiant`(`matricule`, `nom`, `prenom`, `specialite`, `annee`, `section`, `groupe`, `mdp`) VALUES ('"+imat.getText()+"','"+inom.getText()+"','"+iprenom.getText()+"','"+ispec.getText()+"','"+iannee.getText()+"','"+isec.getText()+"','"+igrp.getText()+"','"+String.valueOf(ipw.getPassword())+"')";
			    int v= statment.executeUpdate(sql);
			   	
			    mt=imat.getText();
			    nm=inom.getText();
			    pnm=iprenom.getText();
			    sc=isec.getText();
			    sp=ispec.getText();
			    an=iannee.getText();
			    g=igrp.getText();
			    
			    AccEtud a=new AccEtud();
			    f.setVisible(false);
			    statment.close();
			    con.close(); 
			      
			} catch (SQLException e) {
				new champVide();
				e.printStackTrace();
			}
		}
		
		
	}
}
