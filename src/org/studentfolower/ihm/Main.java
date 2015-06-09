package org.studentfolower.ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneLayout;
import javax.swing.SwingUtilities;

import org.studentfolower.data.management.Cour;
import org.studentfolower.data.management.Groupe;
import org.studentfolower.data.management.Status;
import org.studentfolower.data.physical.Etudiant;
import org.studentfolower.util.ArrayUtils;
import org.studentfolower.util.DataPopulating;
import org.studentfolower.util.PersonUtil;

public class Main extends JFrame {

	/**
	 * Fonction principale
	 */

	public static void main(String[] args) {

		PersonUtil.offline = false;

		System.setProperty("http.proxyHost", "cache.univ-lille1.fr");
		System.setProperty("http.proxyPort", "3128");
		System.setProperty("https.proxyHost", "cache.univ-lille1.fr");
		System.setProperty("https.proxyPort", "3128");
		PersonUtil.isProxyOn = true;

		DataPopulating.createAll();

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Main();
			}
		});
	}

	private final JPanel panel1 = new JPanel();
	private final JPanel panel2 = new JPanel();

	// private JPanel panel3 = new JPanel();
	private final JPanel panel4 = new JPanel();
	private final JButton options = new JButton("O");
	private final JButton ba = new JButton("Texte");
	private final JButton bb = new JButton("Trombi");
	private final JButton bc = new JButton("Photos");

	private final JButton back = new JButton("Retour");
	private final JComboBox<String> combo1;

	private final JComboBox<String> combo2;
	private final FrameFactory ff;

	private final JScrollPane scroll;

	private JFrame fr;

	private JLabel lab;

	public Main() {

		List<String> ls1 = Groupe.getAllStr();

		combo1 = new JComboBox<String>(ArrayUtils.toArray(ls1));
		Groupe grp = Groupe.getAll().get(combo1.getSelectedIndex());

		combo2 = new JComboBox<String>(ArrayUtils.toArray(Cour.getBy(grp)
				.values()));

		Groupe g = Groupe.getAll().get(combo1.getSelectedIndex());
		ff = new FrameFactory(
				(Cour) Cour.getBy(g).keySet().toArray()[combo2
						.getSelectedIndex()]);
		ff.setRenderType(RenderType.PICTURE_TEXT);
		scroll = new JScrollPane(ff);

		combo1.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {

				Groupe g = Groupe.getAll().get(combo1.getSelectedIndex());

				DefaultComboBoxModel mod = new DefaultComboBoxModel(ArrayUtils
						.toArray(Cour.getBy(g).values()));

				combo2.setModel(mod);

				scroll.removeAll();
				ff.setCour((Cour) Cour.getBy(g).keySet().toArray()[combo2
						.getSelectedIndex()]);
				scroll.add(ff);
				repaint();

			}

		});

		combo2.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				Groupe g = Groupe.getAll().get(combo1.getSelectedIndex());
				scroll.removeAll();
				ff.setCour((Cour) Cour.getBy(g).keySet().toArray()[combo2
						.getSelectedIndex()]);
				scroll.add(ff);
				repaint();

			}
		});

		setPreferredSize(new Dimension(330, 500));
		setTitle("StudentFollower");
		this.setLocation(700, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);

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

		setVisible(true);

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

				if (code == 1) {

					fr = new JFrame();
					fr.setPreferredSize(new Dimension(330, 500));
					fr.setTitle("Informations de l'Etudiant");
					fr.setLocation(700, 250);
					fr.setUndecorated(true);

					fr.setLayout(new BorderLayout());
					JPanel buttonPanel = new JPanel();
					buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
					buttonPanel.add(back);
					fr.add(buttonPanel, BorderLayout.SOUTH);

					JPanel infoPanel = new JPanel();
					infoPanel.setLayout(new GridLayout(0, 1));
					JScrollPane s;

					Map<Etudiant, Map<Status, Integer>> a = ff.getCour()
							.getGr().getStatAbs();

					for (Etudiant etu : ff.getCour().getGr().getLsEtu()) {

						lab = new JLabel(etu.getName() + " | Absences = "
								+ a.get(etu).get(Status.PRESENT)
								+ " | Retards = "
								+ a.get(etu).get(Status.RETARD));
						infoPanel.add(lab);
					}

					s = new JScrollPane(infoPanel);
					s.setLayout(new ScrollPaneLayout());
					fr.add(s, BorderLayout.CENTER);

					fr.pack();
					fr.setVisible(true);

					back.addActionListener(

					new java.awt.event.ActionListener() {
						@Override
						public void actionPerformed(
								java.awt.event.ActionEvent evt) {

							fr.dispose();

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
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.this.dispose();
			}
		};
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				escapeKeyStroke, "ESCAPE");
		getRootPane().getActionMap().put("ESCAPE", escapeAction);

	}

}
