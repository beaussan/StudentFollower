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
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jdesktop.swingx.JXDatePicker;
import org.studentfolower.data.management.Cour;

/**
 * 
 * Permet à l'utilisateur de retrouver un cours ayant eu lieu plus tôt pour
 * faire des modifications.
 * 
 * @author MoulardS
 *
 */

public class Historique extends JFrame {
	
	//TODO All the buttons and shit

	private JButton back = new JButton("Retour");
	
	private Map<Cour,JButton> map = new HashMap<Cour,JButton>();

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
		
		//TODO is null really an ok argument?

		/*for (Cour cour : Cour.getBy(date, null)) {
		 
		 //Seems like no. NullPointer Exception
		  
			map.put(cour, new JButton(cour.toString()));
		}
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(1,0));
		for (Cour cour : map.keySet()) {
			mainPanel.add(map.get(cour));
		}
		
		this.add(mainPanel);
		*/
		
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
