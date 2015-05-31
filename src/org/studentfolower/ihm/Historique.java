package org.studentfolower.ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
		
		/*
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
		*/

		/*
		JPanel boxPanel = new JPanel();
		boxPanel.setLayout(new GridLayout(1, 3));
		boxPanel.add(day);
		boxPanel.add(month);
		boxPanel.add(year);
		this.add(boxPanel, BorderLayout.NORTH);
		*/
		
		this.setLayout(new BorderLayout());
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.add(back);
		this.add(buttonPanel, BorderLayout.SOUTH);
		
		/*UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Aujourd'hui");
		p.put("text.month", "Mois");
		p.put("text.year", "Ann�e");
		JDatePanelImpl datePanel = new JDatePanelImpl(model,p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel,null);
		Date selectedDate = (Date) datePicker.getModel().getValue();

		this.add(datePicker,BorderLayout.NORTH);*/
		
		JXDatePicker picker = new JXDatePicker();
        picker.setDate(Calendar.getInstance().getTime());
        picker.setFormats(new SimpleDateFormat("dd.MM.yyyy"));

        this.add(picker,BorderLayout.NORTH);

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
