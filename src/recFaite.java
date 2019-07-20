import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class recFaite extends JFrame implements ActionListener{

	public recFaite() {//boite de dialogue qui indique que la reclamation est envoyée
		JOptionPane jop=new JOptionPane();
		jop.showMessageDialog(null, "Reclamation envoyée.", "Message", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void main(String[] args) {
		recFaite e=new recFaite();
		//e.pack();
	}
	
	public void actionPerformed(ActionEvent e) 
	{ 
		if(e.getActionCommand().equals("OK"))
		{
			AccEtud fe=new AccEtud();
			//this.setVisible(false);
		}
		
		
	}

}