import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.NumberFormat;
import net.proteanit.sql.DbUtils;

public class hist extends JFrame implements ActionListener{//historique de l'etudiant
	JFrame f=new JFrame();
	  static JTable t=new JTable();	
	public hist() {
	
		JPanel p=new JPanel();
		JPanel top=new JPanel();
		JPanel mid=new JPanel();
		JPanel bot=new JPanel();
		JLabel l=new JLabel("Historique");
      
		
		
		JButton re=new JButton("Retour");
		re.addActionListener(this);
		
		
	
		JScrollPane sp=new JScrollPane(t,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		

		top.setBackground(Color.WHITE);
		top.add(l);
		mid.setLayout(new BorderLayout());
		mid.add(sp,BorderLayout.CENTER);
		
		bot.add(re);
		
		p.setLayout(new BorderLayout());
		p.add(top,BorderLayout.NORTH);
		p.add(mid,BorderLayout.CENTER);
		p.add(bot,BorderLayout.SOUTH);
		
		f.setSize(600,300);
		f.setTitle("Historique de réclamation");
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setContentPane(p);
		f.setResizable(false);
		f.setVisible(true);
		
	}

	public static void main(String[] args) {
		hist h=new hist();
		
		try {// connecter a la BDD et lister toutes les reclamations de l'etudiant
			 String sql="select mat As Matricule,module as Module,note_aff As Note_affiché,note_rl As Note_réelle,nomEns As Enseignant,etat as Etat  from reclamation WHERE mat='"+FormEtud.mt+"'";
			 
		 
				Connection con ;
			
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/gestreclam", "root", "");
				PreparedStatement st;
				st = con.prepareStatement(sql);
			    ResultSet rs = st.executeQuery(sql);
			    t.setModel(DbUtils.resultSetToTableModel(rs));//remplir le JTable avec les données recupérées de la BDD 
			    
				
			      
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//h.pack();	
	}
	
	public void actionPerformed(ActionEvent e) 
	{ 
		if(e.getActionCommand().equals("Retour"))// bouton qui mene a l'acceuil etudiant
		{ AccEtud a=new AccEtud();
			f.setVisible(false);
		}
		
	}
	

}
