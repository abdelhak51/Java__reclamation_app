import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Window;
import java.text.NumberFormat;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class ReclEns //interface reclamation consulté par l'enseignant pour rectifier les notes 
{

	private JFrame frame;
	private JTextField textField;
	private JTextArea irec;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReclEns window = new ReclEns();
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
	public ReclEns() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
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
		//left(informations sur l'etudiant qui a reclamé)
		JLabel nom=new JLabel("	Nom");
		JLabel inom=new JLabel(AccEns.nom);
		nom.setHorizontalAlignment(JLabel.CENTER);
		p1.add(nom);
		p1.add(inom);
		
		JLabel prenom=new JLabel("		Prenom");
		JLabel iprenom=new JLabel(AccEns.pre);
		prenom.setHorizontalAlignment(JLabel.CENTER);
		p1.add(prenom);
		p1.add(iprenom);
		
		JLabel mat=new JLabel("Matricule");
		JLabel imat=new JLabel(AccEns.mat);
		mat.setHorizontalAlignment(JLabel.CENTER);
		p1.add(mat);
		p1.add(imat);

		JLabel module=new JLabel("Module");
		JLabel imodule=new JLabel(AccEns.mod);
		module.setHorizontalAlignment(JLabel.CENTER);
		p1.add(module);
		p1.add(imodule);
		
		JLabel annee=new JLabel("Ann\u00E9e");
		JLabel iannee=new JLabel(AccEns.an);
		annee.setHorizontalAlignment(JLabel.CENTER);
		p1.add(annee);
		p1.add(iannee);
		
		JLabel spec=new JLabel("Specialit\u00E9");
		JLabel ispec=new JLabel(AccEns.sp);
		spec.setHorizontalAlignment(JLabel.CENTER);
		p1.add(spec);
		p1.add(ispec);
		
		JLabel grp=new JLabel("Groupe");
		JLabel igrp=new JLabel(AccEns.grp);
		grp.setHorizontalAlignment(JLabel.CENTER);
		p1.add(grp);
		p1.add(igrp);
		
		
		p1.setBorder(BorderFactory.createTitledBorder("Informations"));
	
		//center(a remplir)
		JLabel rec=new JLabel("Remarque (Etudiant)");
		rec.setBounds(122, 114, 120, 14);
		JScrollPane sp=new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setBounds(246, 208, 166, 150);
		JLabel nr=new JLabel("Note r\u00E9elle");
		nr.setBounds(364, 24, 66, 14);
		JFormattedTextField inr=new JFormattedTextField(NumberFormat.getNumberInstance());
		inr.setText(AccEns.nr);
		inr.setBounds(429, 21, 40, 20);
		inr.setEditable(false);
		inr.setPreferredSize(new Dimension(40,20));
		JLabel na=new JLabel("Note affich\u00E9e");
		na.setBounds(241, 24, 78, 14);
		JFormattedTextField ina=new JFormattedTextField(NumberFormat.getNumberInstance());
		ina.setText(AccEns.na);
		ina.setBounds(321, 21, 40, 20);
		ina.setEditable(false);
		ina.setPreferredSize(new Dimension(40,20));
		JLabel num=new JLabel("Numero de tel");
		num.setBounds(27, 24, 79, 14);
		JFormattedTextField inum=new JFormattedTextField(NumberFormat.getNumberInstance());
		inum.setText(AccEns.tel);
		inum.setBounds(111, 21, 120, 20);
		inum.setEditable(false);
		inum.setPreferredSize(new Dimension(120,20));
		p2.setLayout(null);
		
		p2.add(num);
		p2.add(inum);
		p2.add(na);
		p2.add(ina);
		p2.add(nr);
		p2.add(inr);
		p2.add(rec);
		p2.add(sp);
		 irec=new JTextArea(8,14);
		sp.setViewportView(irec);
		p2.setBorder(BorderFactory.createTitledBorder("A remplir"));
		
		//bot
		JButton r=newButton1();
		JButton bck= newButton();
		p3.add(bck);
		p3.add(r);
			
		p1.setLayout(new GridLayout(7,2));
		p.setLayout(new BorderLayout());
		
		p.add(top,BorderLayout.NORTH);
		p.add(p1,BorderLayout.WEST);
		p.add(p2,BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("Votre remarque svp");
		lblNewLabel.setBounds(122, 268, 114, 14);
		JScrollPane sp1=new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		p2.add(lblNewLabel);
		p2.add(sp1);
		
		JLabel lblNewLabel_1 = new JLabel("Nouvelle note");
		lblNewLabel_1.setBounds(152, 389, 79, 14);
		p2.add(lblNewLabel_1);
		sp1.setBounds(246, 49, 166, 150);
				
						JTextArea iremarque=new JTextArea(8,14);
						iremarque.setText(AccEns.rq);
						sp1.setViewportView(iremarque);
						iremarque.setEditable(false);
						
						textField = new JTextField();
						textField.setBounds(246, 386, 60, 20);
						p2.add(textField);
						textField.setColumns(10);
		
		p.add(p3,BorderLayout.SOUTH);
		
		frame.setSize(665,540);
		frame.setTitle("Gestion de reclamation");
		frame.setBackground(Color.white);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(p);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.dispose();
	}

	private JButton newButton() {
		JButton bck=new JButton("Retour");
		bck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//retour a l'acceuil enseignant
				AccEns.main(null);
				frame.setVisible(false);
			}
		});
		return bck;
	}
	private JButton newButton1() 
	{
		JButton r=new JButton("Approuve");
		r.addActionListener(new ActionListener() //approuver une reclamation
		{
			public void actionPerformed(ActionEvent ev) 
			{	
				try {
					Class.forName("com.mysql.jdbc.Driver");//connecter a la BDD
					System.out.println("Driver loaded");
				} catch (ClassNotFoundException e) {
					System.out.println("Error loading jdbc driver");
					e.printStackTrace();
				}
				Connection con =null;
				try {
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/gestreclam", "root", "");
					
					Statement statment = con.createStatement();
					//modifier la reclamation a "rectifié" et ajouter la remarque de l'enseignant et la nouvelle note
					String sql ="UPDATE `reclamation` SET `etat`='oui',`nvl_note`='"+textField.getText()+"',`rem_en`='"+irec.getText()+"' WHERE `id`='"+AccEns.id+"' "; // where a modifi
					 statment.executeUpdate(sql);
					rectFaite rf=new rectFaite();
					AccEns.main(null);
					frame.setVisible(false);
				    statment.close();
				    con.close(); 
				} catch (SQLException e) {
					new champVide();				    
					e.printStackTrace();
				}
				
			}
		});
		return r;
	}
}
