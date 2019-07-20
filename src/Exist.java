import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Exist extends JFrame implements ActionListener{

	public Exist() { // boite de dialogue compte existant
		JOptionPane jop3=new JOptionPane();
		jop3.showMessageDialog(null, "Compte existant!", "ERROR", JOptionPane.ERROR_MESSAGE);
	}

	public static void main(String[] args) {
		Exist e=new Exist();
	}
	
	public void actionPerformed(ActionEvent e) 
	{ 
		if(e.getActionCommand().equals("OK"))
		{
			AccEns fe=new AccEns();
			//this.setVisible(false);
		}
		
		
	}

}