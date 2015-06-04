package org.studentfolower.ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jdesktop.swingx.JXDatePicker;
import org.studentfolower.data.management.Cour;
import org.studentfolower.data.physical.Profesor;

/**
 * 
 * Permet Ã  l'utilisateur de retrouver un cours ayant eu lieu plus tÃ´t pour
 * faire des modifications.
 * 
 * @author MoulardS
 *
 */

public class Historique extends JFrame {

	// TODO All the buttons and shit

	private JButton back = new JButton("Retour");

	private Map<Cour, HistButton> map = new HashMap<Cour, HistButton>();

	private JPanel mainPanel;

	private HistButton butt;

	private Date date;

	private JXDatePicker picker;

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

		picker = new JXDatePicker();
		picker.setDate(Calendar.getInstance().getTime());
		picker.setFormats(new SimpleDateFormat("dd.MM.yyyy"));

		date = picker.getDate();

		picker.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {

				date = picker.getDate();
				System.out.println(date);
				try {
					if (Cour.getBy(date, Profesor.getAll().get(0)) != null) {
						// IndexOutOfBonds ici, j'sais pas pourquoi.
						// TODO S'occuper de ça. Moi je trouve pas le problème :/
						// p-e dans la génération de data.

						for (Cour cour : Cour.getBy(date, Profesor.getAll().get(0))) {
							//Out of bonds ici aussi.
							map.put(cour, new HistButton(cour, cour.toString()));
						}

					}
				} catch (java.lang.IndexOutOfBoundsException e) {
					e.printStackTrace();
				} finally {
					System.out.println("Aucun cours");
				}

			}
		});

		this.add(picker, BorderLayout.NORTH);

		// Deconne à partir d'ici.
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(1, 10));

		// Génération de boutons une fois la date choisie + listener pour
		// afficher le cour.
		for (Cour cour : map.keySet()) {
			butt = map.get(cour);
			butt.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent evt) {

					mainPanel.removeAll();
					FrameFactory f = butt.getFrameFactory();
					mainPanel.add(f);
					repaint();

				}
			});

			mainPanel.add(map.get(cour));

		}

		this.add(mainPanel);

		pack();
		this.setVisible(true);

	}

	/**
	 * Fonction servant Ã  retourner le bouton back de l'historique
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
