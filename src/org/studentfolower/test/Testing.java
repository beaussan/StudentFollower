package org.studentfolower.test;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.studentfolower.util.PersonUtil;

public class Testing extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				String str = (String) JOptionPane.showInputDialog(null,
						"Je demande un nom !", "Customized Dialog",
						JOptionPane.PLAIN_MESSAGE, null, null, "");
				JFrame fram = new JFrame(str);
				fram.add(new Testing(str));
				fram.setPreferredSize(new Dimension(300, 300));
				fram.setLocation(300, 300);
				fram.pack();
				fram.setVisible(true);

			}
		});
		System.out.println("Bonjour".hashCode());
	}

	BufferedImage img;

	public Testing(String str) {
		setVisible(true);
		img = PersonUtil.getImage(str, 255);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 5, 5, null);
	}
}
