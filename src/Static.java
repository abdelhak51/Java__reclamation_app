
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Static {
	private static DefaultCategoryDataset dataset;
	private static JFreeChart chart;
	private static Connection con=connect.getConnection();
	;
	private static PreparedStatement prepared=null;
	private static PreparedStatement prepared1=null;
	private static ResultSet resultat=null;
	private static ResultSet resultat1=null;
	private static int i=0;
	private static int j=0;
	private static ChartFrame f1;
	private static JFrame f;
	
	public static void main (String args[]){//interface de statistiques
		
		update();
		dataset=new DefaultCategoryDataset();
		dataset.setValue(i,"","Nombre de Reclamation Repondue"); 
		dataset.setValue(j,"","Nombre de Reclamation non Repondue");
		dataset.setNotify(true);
	    
		
		
		chart=ChartFactory.createBarChart("Statistique de Reclamation ","","Nombre de Reclamation", dataset,PlotOrientation.VERTICAL,false,true,false);
	
		chart.getTitle().setPaint(Color.BLACK);
		
		CategoryPlot p=chart.getCategoryPlot();
		
		p.setRangeGridlinePaint(Color.BLACK);
		
	
		ChartFrame f1=new ChartFrame("Statistique",chart);
		
		
		f1.setVisible(true);
		f1.setSize(700,400);

	}
	
	// la methode de update 
	public static void update(){
	
	try {
		   String nbrYES="select id from reclamation where etat='oui'"; //selectionner les reclamations rectifiées
		   String nbrNO="select id from reclamation where etat='non'"; //selectionner les reclamations non rectifiées
		   prepared = con.prepareStatement(nbrYES);
		   prepared1 = con.prepareStatement(nbrNO);
		   resultat=prepared.executeQuery();
		   resultat1=prepared1.executeQuery();
		   
		   while(resultat.next()){
			   i++; //incrementer le nombre les reclamation rectifiées
		   }
		   while(resultat1.next()){
			   j++; //incrementer le nombre les reclamation non rectifiées
		   }
		   
	    }catch(SQLException e){
	    	e.printStackTrace();
	    }
	}
}
