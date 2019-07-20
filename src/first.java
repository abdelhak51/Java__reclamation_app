
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class first extends JFrame implements ActionListener{ //1ere fenetre de selection (etudiant/enseignant/administrateur)

	public first() 
	{
		JPanel p1=new JPanel();
		JPanel p=new JPanel();
		JPanel top=new JPanel();
		
		JButton b1=new JButton("Etudiant",new ImageIcon("C:\\Users\\admin\\Desktop\\Home\\etudes\\L3\\IHM\\TP\\TP1\\Projet\\src\\etu.png"));
		b1.addActionListener(this);
		
		JButton b2=new JButton("Enseignant",new ImageIcon("C:\\Users\\admin\\Desktop\\Home\\etudes\\L3\\IHM\\TP\\TP1\\Projet\\src\\esn.png"));
		b2.addActionListener(this);
			
		JButton b3=new JButton("Administrateur",new ImageIcon("C:\\Users\\admin\\Desktop\\Home\\etudes\\L3\\IHM\\TP\\TP1\\Projet\\src\\ad.png"));
		b3.addActionListener(this);
		
		JLabel ch=new JLabel("Qui êtes vous?");
		Font font=new Font("Garamonod",Font.BOLD,20);
		ch.setFont(font);
		ch.setHorizontalAlignment(JLabel.CENTER);
		ch.setPreferredSize(new Dimension(400,50));
		top.setBackground(Color.white);
		top.add(ch);
		
		b1.setPreferredSize(new Dimension(200,400));
		b2.setPreferredSize(new Dimension(200,400));
		b3.setPreferredSize(new Dimension(200,400));

		
		JLabel lblNewLabel_6 = new JLabel("X");
		lblNewLabel_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblNewLabel_6.setFont(lblNewLabel_6.getFont().deriveFont(lblNewLabel_6.getFont().getSize() + 10f));
		lblNewLabel_6.setForeground(Color.RED);
		lblNewLabel_6.setBounds(580, 2, 56, 14);
		p.add(lblNewLabel_6);
		
		p1.add(b1);
		p1.add(b2);
		p1.add(b3);

		p1.setLayout(new GridLayout(1,3));
		
		p.setLayout(new BorderLayout());
		
		p.add(top,BorderLayout.NORTH);
		p.add(p1,BorderLayout.CENTER);
		
		
		f.setSize(600, 400);
		f.setTitle("Gestion de reclamation");
		f.setBackground(Color.white);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(p);
		f.setResizable(false);
		f.setUndecorated(true);
		f.setVisible(true);
		
	}
	JFrame f=new JFrame();
	public static void main(String[]args)
	{
		first fir=new first();
	}

	public void actionPerformed(ActionEvent e) 
	{ 
		if(e.getActionCommand().equals("Etudiant"))//si on appuie sur bouton etudiant on va vers l'interface d'authentification de l'etudiant
		{
			FormEtud fe=new FormEtud();
			f.setVisible(false);
		}
		if(e.getActionCommand().equals("Enseignant"))//si on appuie sur bouton enseignant on va vers l'interface d'authentification de l'enseignant
		{
			Log.main(null);
			f.setVisible(false);
		}
		if(e.getActionCommand().equals("Administrateur"))//si on appuie sur bouton l'administrateur on va vers l'interface d'authentification de l'administrateur
		{
			LogSign.main(null);
			f.setVisible(false);
		}
		
	}
}
