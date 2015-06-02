package org.studentfolower.ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jdesktop.swingx.JXDatePicker;

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
		this.add(buttonPanel, BorderLayout.SOUTH);

		JXDatePicker picker = new JXDatePicker();
		picker.setDate(Calendar.getInstance().getTime());
		picker.setFormats(new SimpleDateFormat("dd.MM.yyyy"));

		this.add(picker, BorderLayout.NORTH);

		Date date = picker.getDate();

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
