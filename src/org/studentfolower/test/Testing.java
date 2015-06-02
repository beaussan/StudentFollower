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
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.studentfolower.data.management.Cour;
import org.studentfolower.data.physical.Etudiant;
import org.studentfolower.ihm.FrameFactory;
import org.studentfolower.ihm.RenderType;
import org.studentfolower.util.DataPopulating;

public class Testing extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		DataPopulating.createAll();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame fram = new JFrame("lol");
				// fram.add(new Testing("lol"));

				// fram.add(new Testing("lol"));
				// SingleFrame sf = new SingleFrame(etu, co);
				// fram.add(sf);
				// sf.setType(RenderType.PICTURE_TEXT);
				// sf.setType(RenderType.PICTURE_TEXT);
				FrameFactory ff = new FrameFactory(Cour.getAll().get(0));
				fram.add(ff);
				ff.setRenderType(RenderType.TEXT_ONLY);
				fram.setPreferredSize(new Dimension(400, 400));
				fram.setLocation(400, 400);
				fram.pack();
				fram.setVisible(true);

			}
		});
	}

	BufferedImage img;

	@SuppressWarnings("deprecation")
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
