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
package org.studentfolower.ihm;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import org.studentfolower.data.management.Cour;
import org.studentfolower.data.physical.Etudiant;

public class SingleFrame extends JPanel {

	class ImagePanel extends JPanel {

		private static final long serialVersionUID = 1L;
		private BufferedImage tmpBi;

		public ImagePanel(Dimension dim) {
			setPreferredSize(dim);
			repaint();
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (tmpBi == null) {
				return;
			}
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2.drawImage(tmpBi, 0, 0, this);
			int thickness = tmpBi.getHeight() / 10;
			g2.setStroke(new BasicStroke(thickness));

			switch (cour.getStatuEtu(etu)) {
			case RETARD:
				g2.setColor(Color.ORANGE);
				g2.drawRect(0, 0, tmpBi.getWidth() - thickness / 2,
						tmpBi.getHeight() - thickness / 2);
				break;
			case ABSENT:
				g2.setColor(Color.RED);
				g2.drawRect(0, 0, tmpBi.getWidth() - thickness / 2,
						tmpBi.getHeight() - thickness / 2);

			default:
				break;
			}
		}

		public void setTmpBi(BufferedImage tmpBi) {
			this.tmpBi = tmpBi;
		}

	}

	private static final long serialVersionUID = 5181434714316435347L;
	private Etudiant etu;

	private BufferedImage bufLarge;
	private BufferedImage bufSmall;

	private Cour cour;

	private ImagePanel ip;

	private RenderType type;

	private JLabel name;

	private final EtuListeners etList;

	public SingleFrame(Etudiant etu, Cour cour) {
		super();
		this.etu = etu;
		this.cour = cour;
		etList = new EtuListeners(etu, cour);
		addMouseListener(etList);

		ip = new ImagePanel(FrameFactory.DIM_IMGS.get(RenderType.PICTURE_ONLY));

		name = new JLabel(etu.getName());

		List<BufferedImage> lsImgs = etu.getLsImg();
		bufLarge = lsImgs.get(0);
		bufSmall = lsImgs.get(1);
		ip.setTmpBi(bufSmall);

		setType(RenderType.TEXT_ONLY);
		repaint();
	}

	@Override
	public void repaint() {
		super.repaint();
		try {
			cour.getStatuEtu(etu);
		} catch (NullPointerException e) {
			return;
		}

		switch (type) {
		case TEXT_ONLY:
			setType0();
			break;
		case PICTURE_ONLY:
			setType1();
			break;
		case PICTURE_TEXT:
			setType2();
			break;
		default:
			break;
		}

	}

	public void setType(RenderType type) {
		this.type = type;
		removeAll();
		switch (type) {
		case PICTURE_ONLY:
			ip.setTmpBi(bufLarge);
			ip.setPreferredSize(FrameFactory.DIM_IMGS.get(type));
			add(ip);
			break;
		case PICTURE_TEXT:
			ip.setTmpBi(bufSmall);
			ip.setPreferredSize(FrameFactory.DIM_IMGS.get(type));
			add(ip);
			add(name);
			break;
		case TEXT_ONLY:
			add(name);
			break;
		default:
			break;
		}
		ip.repaint();
		repaint();
	}

	public void setType0() {
		switch (cour.getStatuEtu(etu)) {
		case RETARD:
			Border br = BorderFactory.createLineBorder(Color.ORANGE, 5);
			name.setBorder(br);
			break;
		case ABSENT:
			Border ba = BorderFactory.createLineBorder(Color.RED, 5);
			name.setBorder(ba);
			break;
		case PRESENT:
			name.setBorder(null);
			break;
		default:
			break;
		}
	}

	public void setType1() {

	}

	public void setType2() {

	}

}
