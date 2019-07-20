import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class champVide extends JFrame implements ActionListener{// boite de dialogue qui indique que les champs sont vides

	public champVide() {
		JOptionPane jop2=new JOptionPane();
		jop2.showMessageDialog(null, "Champs sont vides ou pas bien remplis", "ERROR", JOptionPane.ERROR_MESSAGE);
	}

	public static void main(String[] args) {
		champVide e=new champVide();
		//e.pack();
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