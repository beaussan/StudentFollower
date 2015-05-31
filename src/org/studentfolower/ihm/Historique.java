package org.studentfolower.ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * 
 * Permet à l'utilisateur de retrouver un cours ayant eu lieu plus tôt pour
 * faire des modifications.
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

		String daystr[] = new String[31];
		for (int i = 1; i <= 31; i++) {
			if (i < 10) {
				daystr[i - 1] = "0" + i;
			} else {
				daystr[i - 1] = "" + i;
			}
		}

		String monthstr[] = new String[12];
		for (int j = 1; j <= 12; j++) {
			if (j < 10) {
				monthstr[j - 1] = "0" + j;
			} else {
				monthstr[j - 1] = "" + j;
			}
		}

		String yearstr[] = new String[16];
		int k = 2000;
		for (int l = 0; l < 16; l++) {
			int cat = k + l;
			yearstr[l] = "" + cat;
		}

		JComboBox<String> day = new JComboBox<String>(daystr);
		JComboBox<String> month = new JComboBox<String>(monthstr);
		JComboBox<String> year = new JComboBox<String>(yearstr);

		this.setLayout(new BorderLayout());
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.add(back);
		this.add(buttonPanel, BorderLayout.SOUTH);

		JPanel boxPanel = new JPanel();
		boxPanel.setLayout(new GridLayout(1, 4));
		boxPanel.add(day);
		boxPanel.add(month);
		boxPanel.add(year);
		boxPanel.add(cal);
		this.add(boxPanel, BorderLayout.NORTH);

		pack();
		this.setVisible(true);
	}

	/**
	 * Fonction servant à retourner le bouton back de l'historique
	 * 
	 * @return le bouton back
	 */

	public JButton getBack() {
		return back;
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
