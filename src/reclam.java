import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
public class reclam extends JFrame implements ActionListener{
	public static JFrame f=new JFrame();
	public reclam() //formulaire de reclamation
	{
		JLayeredPane p=new JLayeredPane();
		JPanel top=new JPanel();
		JPanel p1=new JPanel();
		JPanel p2=new JPanel();
		JPanel p3=new JPanel();
		//top
		JLabel l=new JLabel("Veuillez remplir les champs necessaires");
		top.add(l);
		top.setBackground(Color.WHITE);
		top.setBorder(BorderFactory.createTitledBorder(""));
		
		//left (données etudiant)
		JLabel nom=new JLabel("		Nom");
		JLabel inom=new JLabel(FormEtud.nm);
		nom.setHorizontalAlignment(JLabel.CENTER);
		p1.add(nom);
		p1.add(inom);
		
		JLabel prenom=new JLabel("		Prenom");
		JLabel iprenom=new JLabel(FormEtud.pnm);
		prenom.setHorizontalAlignment(JLabel.CENTER);
		p1.add(prenom);
		p1.add(iprenom);
		
		JLabel mat=new JLabel("Matricule");
		JLabel imat=new JLabel(FormEtud.mt);
		mat.setHorizontalAlignment(JLabel.CENTER);
		p1.add(mat);
		p1.add(imat);
		
		JLabel annee=new JLabel("Année");
		JLabel iannee=new JLabel(FormEtud.an);
		annee.setHorizontalAlignment(JLabel.CENTER);
		p1.add(annee);
		p1.add(iannee);
		
		JLabel spec=new JLabel("Specialité");
		JLabel ispec=new JLabel(FormEtud.sp);
		spec.setHorizontalAlignment(JLabel.CENTER);
		p1.add(spec);
		p1.add(ispec);
		
		JLabel sec=new JLabel("Section");
		JLabel isec=new JLabel(FormEtud.sc);
		sec.setHorizontalAlignment(JLabel.CENTER);
		p1.add(sec);
		p1.add(isec);
		
		JLabel grp=new JLabel("Groupe");
		JLabel igrp=new JLabel();
		igrp.setText(FormEtud.g);
		grp.setHorizontalAlignment(JLabel.CENTER);
		p1.add(grp);
		p1.add(igrp);
		
		p1.setBorder(BorderFactory.createTitledBorder("Informations"));
	
		//center (champs à remplir)
		JLabel rec=new JLabel("Remarque (TD,TP...)");
		JScrollPane sp=new JScrollPane(irec,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		rec.setBounds(300, 20, 120, 34);
		sp.setBounds(300, 50, 230, 100);
		
		JLabel nens=new JLabel("Nom enseignant");
		nens.setBounds(10, 170, 97, 34);
		inens.setBounds(140, 180, 120, 20);
		JLabel pens=new JLabel("Prenom enseignant");
		pens.setBounds(10, 140, 130, 34);
		ipens.setBounds(140, 150, 120, 20);
		JLabel mod=new JLabel("Module");
		mod.setBounds(10, 110, 97, 34);
		imod.setBounds(140, 120, 120, 20);
		JLabel nr=new JLabel("Note réelle");
		nr.setBounds(10, 80, 97, 34);
		inr.setBounds(140, 90, 120, 20);
		inr.setPreferredSize(new Dimension(40,20));
		JLabel na=new JLabel("Note affichée");
		na.setBounds(10, 50, 97, 34);
		ina.setBounds(140, 60, 120, 20);
		ina.setPreferredSize(new Dimension(40,20));
		JLabel num=new JLabel("Numero de tel");
		num.setBounds(10, 20, 97, 34);
		inum.setBounds(140, 30, 120, 20);
		inum.setPreferredSize(new Dimension(120,20));
		
		p2.add(num);
		p2.add(inum);
		p2.add(mod);
		p2.add(imod);
		p2.add(nens);
		p2.add(inens);
		p2.add(pens);
		p2.add(ipens);
		p2.add(na);
		p2.add(ina);
		p2.add(nr);
		p2.add(inr);
		p2.add(rec);
		p2.add(sp);
		p2.setBorder(BorderFactory.createTitledBorder("A remplir"));
		p2.setLayout(null);
		
		//bot
		JButton r=new JButton("Reclamer");
		r.addActionListener(this);
		JButton bck=new JButton("Retour");
		bck.addActionListener(this);
		
		p3.add(bck);
		p3.add(r);
			
		p1.setLayout(new GridLayout(7,2));
		p.setLayout(new BorderLayout());
		
		p.add(top,BorderLayout.NORTH);
		p.add(p1,BorderLayout.WEST);
		p.add(p2,BorderLayout.CENTER);
		p.add(p3,BorderLayout.SOUTH);
		
		f.setSize(724,350);
		f.setTitle("Gestion de reclamation");
		f.setBackground(Color.white);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setContentPane(p);
		f.setResizable(false);
		f.setVisible(true);
	}
	
	JTextField inens=new JTextField(10);
	JTextField ipens=new JTextField(10);
	JTextField imod=new JTextField(10);
	JTextArea irec=new JTextArea(5,20);
	JFormattedTextField inr=new JFormattedTextField();
	JFormattedTextField ina=new JFormattedTextField();
	JFormattedTextField inum=new JFormattedTextField();
	
	public static void main(String[] args) 
	{
		reclam r=new reclam();
		
	}
	
	public void actionPerformed(ActionEvent ev) 
	{ 
		if(ev.getActionCommand().equals("Retour")) //si on appuie sur bouton retour on revient a l'acceuil etudiant
		{ AccEtud a=new AccEtud();
			f.setVisible(false);
		}
		if(ev.getActionCommand().equals("Reclamer")) //si on appuie sur bouton reclamer on envoie la reclamation et retoune vers l'acceuil etudiant
		{
			System.out.println("Test Connection to MySQL");
			try {
				Class.forName("com.mysql.jdbc.Driver"); //connection a la base de données
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
				System.out.println("Connection to DB created for reclam");
				
				Statement statment = con.createStatement();
				System.out.println("Statement created reclam");
				
				String sql; //inserer les données de la reclamations dans la BDD
			    sql = "INSERT INTO `reclamation`(`mat`, `nomEns`, `preEns`, `module`, `note_aff`, `note_rl`, `rem_et`,`etat`,`notif`) VALUES ('"+FormEtud.mt+"','"+inens.getText()+"','"+ipens.getText()+"','"+imod.getText()+"','"+ina.getText()+"','"+inr.getText()+"','"+irec.getText()+"','non','non')";
			    statment.executeUpdate(sql);

				   System.out.println("data insered ");
			    //mettre a jour le compte de l'etudiant avec son numero de telephone
			   String sql1=" UPDATE `etudiant` SET `tel`='"+inum.getText()+"' WHERE `matricule`='"+FormEtud.mt+"'";
			   statment.executeUpdate(sql1);
			   
			   System.out.println(inum.getText()+" updated.");
			   
			    recFaite r=new recFaite();
				f.setVisible(false);
				AccEtud.main(null);
			    statment.close();
			    con.close(); 
			      
			} catch (SQLException e) {
				new champVide();
				e.printStackTrace();
			}
		
		}
		
	}
	
}
