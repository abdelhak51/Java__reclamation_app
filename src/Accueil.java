
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Button;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;

public class Accueil extends JFrame {//acceuil administrateur

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Accueil frame = new Accueil();
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setTitle("Accueil administrateur");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Accueil() {
		setTitle("Accueil administrateur");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 593, 411);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(0, 0, 577, 372);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bienvenue");
		lblNewLabel.setForeground(new Color(0, 0, 51));
		lblNewLabel.setFont(lblNewLabel.getFont().deriveFont(lblNewLabel.getFont().getStyle() | Font.ITALIC, lblNewLabel.getFont().getSize() + 17f));
		lblNewLabel.setBounds(225, 40, 175, 43);
		panel.add(lblNewLabel);
		//bouton voir statistique
		JButton btnNewButton = new JButton("Voir Statistique");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Static s=new Static();// apres le clique sur le bouton statistique on va vers l'interface qui genere les statistiques
				 s.main(null);setVisible(true);dispose(); 
			}
		});
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(btnNewButton.getFont().deriveFont(btnNewButton.getFont().getStyle() | Font.BOLD, btnNewButton.getFont().getSize() + 9f));
		btnNewButton.setBounds(10, 157, 225, 63);
		panel.add(btnNewButton);
		
		//bouton gerer reclamation
		JButton btnNewButton_1 = new JButton("Gerer Reclamation");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 Reclamation c=new  Reclamation(); // apres le clique sur le bouton gerer reclamation on va vers l'interface qui liste les nouvelles reclamations
			  c.main(null);setVisible(true);dispose(); 
			}
		});
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setFont(btnNewButton_1.getFont().deriveFont(btnNewButton_1.getFont().getStyle() | Font.BOLD, btnNewButton_1.getFont().getSize() + 9f));
		btnNewButton_1.setBounds(322, 157, 245, 63);
		panel.add(btnNewButton_1);

		//bouton quitter
		Button button = new Button("Quitter");
		button.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		button.setForeground(Color.RED);
		button.setBounds(155, 250, 245, 63);
		panel.add(button);
	}

}
