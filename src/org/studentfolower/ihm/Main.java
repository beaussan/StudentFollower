package org.studentfolower.ihm;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import org.studentfolower.data.management.Cour;
import org.studentfolower.data.management.Groupe;
import org.studentfolower.util.ArrayUtils;
import org.studentfolower.util.DataPopulating;
import org.studentfolower.util.PersonUtil;

/**
 * Modélise l'écran principal de l'application, avec deux ComboBox pour le
 * groupe et la salle, un bouton pour les options, un JScrollPane pour
 * l'affichage des élèves du cours et trois autres boutons pour changer de vue
 * 
 * @author MoulardS
 */

public class Main extends JFrame {

	private JPanel panel1 = new JPanel();
	private JPanel panel2 = new JPanel();
	private JPanel panel3 = new JPanel();
	private JPanel panel4 = new JPanel();

	private JButton options = new JButton("O");
	private JButton ba = new JButton("a");
	private JButton bb = new JButton("b");
	private JButton bc = new JButton("c");

	private JComboBox<String> combo1;
	private JComboBox<String> combo2;

	private FrameFactory ff;
	private JScrollPane scroll;

	public Main() {

		List<String> ls1 = Groupe.getAllStr();

		combo1 = new JComboBox<String>(ArrayUtils.toArray(ls1));
		Groupe grp = Groupe.getAll().get(combo1.getSelectedIndex());

		combo2 = new JComboBox<String>(ArrayUtils.toArray(Cour.getBy(grp)
				.values()));

		combo1.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {

				Groupe g = Groupe.getAll().get(combo1.getSelectedIndex());

				DefaultComboBoxModel mod = new DefaultComboBoxModel(ArrayUtils
						.toArray(Cour.getBy(g).values()));

				combo2.setModel(mod);

				ff.setCour((Cour) Cour.getBy(g).keySet().toArray()[combo2
						.getSelectedIndex()]);

			}

		});

		combo2.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				Groupe g = Groupe.getAll().get(combo1.getSelectedIndex());
				ff.setCour((Cour) Cour.getBy(g).keySet().toArray()[combo2
						.getSelectedIndex()]);

			}
		});

		Groupe g = Groupe.getAll().get(combo1.getSelectedIndex());
		ff = new FrameFactory(
				(Cour) Cour.getBy(g).keySet().toArray()[combo2
						.getSelectedIndex()]);
		ff.setRenderType(RenderType.PICTURE_TEXT);
		scroll = new JScrollPane(ff);

		this.setPreferredSize(new Dimension(330, 500));
		this.setTitle("StudentFollower");
		this.setLocation(700, 250);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);

		panel2.setLayout(new GridLayout(1, 3));
		panel2.add(combo1);
		panel2.add(combo2);
		panel2.add(options);

		panel1.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;
		c.weightx = 0.5;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 0;

		panel1.add(panel2, c);

		c.ipady = 400;
		c.weightx = 0.0;
		c.gridx = 0;
		c.gridy = 1;

		panel1.add(scroll, c);

		panel4.setLayout(new GridLayout(1, 3));
		panel4.add(ba);
		panel4.add(bb);
		panel4.add(bc);

		c.ipady = 0;
		c.weightx = 0.0;
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.PAGE_END;

		panel1.add(panel4, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;
		c.weightx = 0.5;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 0;

		this.add(panel1);

		pack();

		this.setVisible(true);

		options.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {

				String[] stropt = { "Historique", "Statistiques" };
				int code = JOptionPane.showOptionDialog(panel1, null,
						"Options", 0, JOptionPane.PLAIN_MESSAGE, null, stropt,
						null);

				if (code == 0) {

					final Historique historique = new Historique();

					historique.getBack().addActionListener(
							new java.awt.event.ActionListener() {
								@Override
								public void actionPerformed(
										java.awt.event.ActionEvent evt) {

									historique.dispose();

								}
							});

				}

			}
		});

		ba.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				
				ff.setRenderType(RenderType.TEXT_ONLY);
				repaint();

			}
		});

		bb.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {

				ff.setRenderType(RenderType.PICTURE_TEXT);
				repaint();

			}
		});

		bc.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {

				ff.setRenderType(RenderType.PICTURE_ONLY);
				repaint();

			}
		});

		KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,
				0, false);
		Action escapeAction = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				Main.this.dispose();
			}
		};
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				escapeKeyStroke, "ESCAPE");
		getRootPane().getActionMap().put("ESCAPE", escapeAction);

	}
	
	/**
	 * Fonction principale
	 */
	
	public static void main(String[] args) {

		//PersonUtil.offline = true;
		System.setProperty("http.proxyHost", "cache.univ-lille1.fr");
		System.setProperty("http.proxyPort", "3128");
		System.setProperty("https.proxyHost",
				"cache.univ-lille1.fr");
		System.setProperty("https.proxyPort", "3128");
		PersonUtil.isProxyOn=true;

		DataPopulating.createAll();

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Main();
			}
		});
	}

}
