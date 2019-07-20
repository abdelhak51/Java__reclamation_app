import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class AccEtud extends JFrame implements ActionListener{//acceuil etudiant
	public JFrame f=new JFrame();
	public AccEtud() {
		JPanel p=new JPanel();
		JPanel top=new JPanel();
		JPanel bot=new JPanel();
		
		JButton br=new JButton("Nouvelle reclamation");
		br.addActionListener(this);
		
		JButton bh=new JButton("Historique");
		bh.addActionListener(this);
		
		JLabel l=new JLabel("Bienvenue dans l'acceuil du systeme de gestion des reclamations");
		
		top.setBackground(Color.white);
		top.add(l);
		
		
		bot.add(br);
		bot.add(bh);
		
		p.setLayout(new BorderLayout());
		p.add(top,BorderLayout.NORTH);
		p.add(bot,BorderLayout.CENTER);
		
		f.setSize(500,200);
		f.setTitle("Acceuil étudiant");
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setContentPane(p);
		f.setResizable(false);
		f.setVisible(true);
		
	}

	public static void main(String[] args) {
		AccEtud ae=new AccEtud();

	}
	
	public void actionPerformed(ActionEvent e) 
	{ 
		
		if(e.getActionCommand().equals("Nouvelle reclamation"))// bouton qui mene a l'interface de reclamation
		{
			reclam.main(null);
			f.setVisible(false);
		}
		if(e.getActionCommand().equals("Historique"))// bouton qui mene a l'historique de l'etudiant
		{
			hist.main(null);
			f.setVisible(false);
		}
		
	}

}
