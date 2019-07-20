import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class rectFaite extends JFrame implements ActionListener{

	public rectFaite() {//boite de dialogue qui indique que la reclamation est rectifié
		JOptionPane jop=new JOptionPane();
		jop.showMessageDialog(null, "Reclamation rectifie.", "Message", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void main(String[] args) {
		rectFaite e=new rectFaite();
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