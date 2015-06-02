/*
 * This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.studentfolower.test;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.studentfolower.data.physical.Etudiant;

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
		Etudiant etu = new Etudiant("Jean");
		img = etu.getImg();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 5, 5, null);
	}
}
