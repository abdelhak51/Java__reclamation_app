import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
public class AccEns 
{ //acceuil enseignant
	private JFrame frmAccueil;
	private JTable Table;
	public static String mat;
	public static String mod;
	public static String nr;
	public static String na;
	public static String rq;
	public static String id;

	public static String nom;
	public static String pre;
	public static String an;
	public static String sp;
	public static String grp;
	public static String tel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					AccEns window = new AccEns();
					window.frmAccueil.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AccEns() {
		initialize();
		UpdateTable();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAccueil = new JFrame();
		frmAccueil.setTitle("Accueil enseignant");
		frmAccueil.setBounds(0, 0, 500, 363);
		frmAccueil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAccueil.setLocationRelativeTo(null);
		frmAccueil.setResizable(false);
		JLayeredPane layeredPane = new JLayeredPane();
		frmAccueil.getContentPane().add(layeredPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 484,50);
		layeredPane.add(panel);
		
		JLabel lblNotifications = new JLabel("Notifications");
		lblNotifications.setBounds(120, 10, 238, 30);
		Font police = new Font("Arial", Font.BOLD, 40); 
		lblNotifications.setFont(police);
		panel.add(lblNotifications);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 50, 484,211);
		panel_1.setLayout(null);
		layeredPane.add(panel_1);
		
		
		
		JScrollPane ScrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		ScrollPane.setBounds(457, 0, 17, 48);
		panel_1.setLayout(new BorderLayout());
		panel_1.add(ScrollPane,BorderLayout.CENTER);
		
		
		Table = new JTable();//tableau des reclamations
		Table.addMouseListener(new MouseAdapter() //si on clique sur une ligne du tableau on va vers la reclamation 
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				int i = Table.getSelectedRow();
				TableModel model =Table.getModel();
				mat= model.getValueAt(i, 0).toString();
				ReclEns.main(null);
				frmAccueil.setVisible(false);
				Connection con=null;
				try 
				{					//recuperer les données de la table reclamation
					String sql = "SELECT * FROM `reclamation` WHERE `mat`= '"+mat+"' and `nomEns`= '"+Log.nm+"'and `preEns`= '"+Log.pnm+"' and  `etat`= 'non' and `notif`='oui'  ";
					
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestreclam","root","");
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery(sql);
					
					if(rs.next())
					{
						id=rs.getString("id");
						mod=rs.getString("module");
						nr=rs.getString("note_rl");
						na=rs.getString("note_aff");
						rq=rs.getString("rem_et");
						
					}
					
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
				
				
				try
				{				//recuperer les données de la table etudiant
					String sql1 = "SELECT * FROM `etudiant` WHERE `matricule`= '"+mat+"'";
					Statement st1 = con.createStatement();
					ResultSet rs1 = st1.executeQuery(sql1);

					if(rs1.next())
					{
						nom=rs1.getString("nom");
						pre=rs1.getString("prenom");
						an=rs1.getString("annee");
						sp=rs1.getString("specialite");
						grp=rs1.getString("groupe");
						tel=rs1.getString("tel");

					}
				}
				 catch (Exception e) 
				{
					e.printStackTrace();
				}
				
				
			}
		});
		Table.setModel(new DefaultTableModel(//definir le model du JTable  (colonnes)
			new Object[][] {
			},
			new String[] {
				"Matricule", "Module", "Note r\u00E9elle", "Note affich\u00E9e"
			}
		));
		
		ScrollPane.setViewportView(Table);//ajouter scroll bar
		
		JButton btnActualiser = new JButton("Actualiser");
		btnActualiser.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
					UpdateTable();
			}
		});
		btnActualiser.setBounds(192, 290, 100, 23);
		layeredPane.add(btnActualiser);
		
	}
	
	public void UpdateTable()//methode qui actualise la liste des reclamations
	{
		try 
		{	// recuperer les informations necessaires de la table reclamation
			String sql = "SELECT mat As Matricule,note_aff as Note_affiché,note_rl as Note_réelle,module as Module FROM `reclamation` WHERE `etat`= 'non' and `notif`='oui' and `nomEns`='"+Log.nm+"' and `preEns`='"+Log.pnm+"'";
			Connection con=null;
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestreclam","root","");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			st = con.createStatement();
			rs = st.executeQuery(sql);
			
			Table.setModel(DbUtils.resultSetToTableModel(rs)); // remplir le JTable avec les information recupérées
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
}
















