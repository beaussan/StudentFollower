package org.studentfolower.ihm;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * 
 * Permet à l'utilisateur de retrouver un cours ayant eu lieu plus tôt
 * pour faire des modifications.
 * 
 * @author MoulardS
 *
 */

public class Historique extends JFrame {
	
	private JPanel panel1 = new JPanel();
	private JButton back = new JButton("Retour");
	
	public Historique() {
		
		System.out.println("oui");
		
		this.setPreferredSize(new Dimension(330, 500));
		this.setTitle("StudentFollower : Historique");
		this.setLocation(700, 250);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		
		panel1.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		
		c.ipady = 0;
		c.weightx = 0.5;
		c.gridx = 3;
		c.gridy = 3;
		c.anchor = GridBagConstraints.LAST_LINE_END;
		
		panel1.add(back,c);
		
		pack();
		this.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Historique();
			}
		});
	}

}
