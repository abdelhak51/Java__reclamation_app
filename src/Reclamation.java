
import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.CompoundBorder;

import java.awt.Component;
import java.awt.Cursor;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;
import javax.swing.JScrollPane;

import net.proteanit.sql.DbUtils;

import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Reclamation { // interface de gestion des reclamations

	private JFrame frame;
	private JTable table_1;
    private String[] column_headers={"abc","abc"};
    private String [] column={"abc"};
    private JTable table;
    Connection con=connect.getConnection();//connection to data base
	PreparedStatement prepared=null;
	ResultSet resultat=null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reclamation window = new Reclamation();
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
	public Reclamation() {
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Gestion de réclamations");
		frame.setBounds(100, 100, 770, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		
		JButton chargerLesReclamation = new JButton("charger les reclamation");//bouton qui actualise et rafraichie la liste de reclamations
		chargerLesReclamation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTable();
			}
		});
		chargerLesReclamation.setFont(new Font("Tahoma", Font.ITALIC, 15));
		chargerLesReclamation.setBounds(575, 63, 190, 44);
		frame.getContentPane().add(chargerLesReclamation);
		
		JButton notifie = new JButton("Notifier  Enseignant");
		notifie.addActionListener(new ActionListener() {// notifier l'enseinant concerné qu'il a une nouvelle reclamation 
			public void actionPerformed(ActionEvent e) {
				
					// mettre a jour la reclamation son champs 'notif' à oui
	                String sql=("update reclamation set  notif ='oui' where etat='non';");
				
				   try {
					prepared=con.prepareStatement(sql);
					prepared.execute();
					
					JOptionPane.showMessageDialog(null," Notifie avec Succes");	
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				
				
			}
		}});
		notifie.setFont(new Font("Tahoma", Font.ITALIC, 15));
		notifie.setBounds(0, 63, 190, 44);
		frame.getContentPane().add(notifie);
		
		JButton btnRetoutner = new JButton("Retourner");
		btnRetoutner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					Accueil c=new Accueil();
					c.setVisible(true);frame.dispose();
			}

		});
		btnRetoutner.setForeground(Color.RED);
		btnRetoutner.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRetoutner.setBounds(287, 0, 196, 44);
		frame.getContentPane().add(btnRetoutner);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 106, 765, 333);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
		
	}
	//methode qui actualise et rafraichie la liste de reclamations
	public void updateTable()
	{
		try 
		{	
		   String sql="select mat As Matricule, module as Module,etat As Repondue_ou_non ,notif as Notifiée_ou_non  from reclamation where etat='non' and notif='non' ;";
		   prepared =con.prepareStatement(sql);
		   resultat=prepared.executeQuery();
	       table.setModel(DbUtils.resultSetToTableModel(resultat));
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}  
	}
	
	
	
	
	
}
