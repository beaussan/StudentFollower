package org.studentfolower.ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
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
	
	private JButton back = new JButton("Retour");
	private JButton cal = new JButton("C");
	private JTextField field = new JTextField("//");
	
	public Historique() {
		
		this.setPreferredSize(new Dimension(330, 500));
		this.setTitle("StudentFollower : Historique");
		this.setLocation(700, 250);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		
		this.setLayout(new BorderLayout());
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.add(back);
		this.add(buttonPanel,BorderLayout.SOUTH);
		
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;
		c.weightx = 0.5;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 0;
		fieldPanel.add(field,c);
		c.ipady = 0;
		c.weightx = 0.5;
		c.gridwidth = 1;
		c.gridx = 2;
		c.gridy = 0;
		fieldPanel.add(cal);
		this.add(fieldPanel, BorderLayout.NORTH);
		
		pack();
		this.setVisible(true);
		
		JPanel mainPanel = new JPanel();
		
	}
	
	/**
	 * Fonction servant à retourner le bouton back de l'historique
	 * 
	 * @return le bouton back
	 */
	
	public JButton getBack() {return back;}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Historique();
			}
		});
	}

}
