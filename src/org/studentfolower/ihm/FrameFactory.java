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

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import org.studentfolower.data.management.Cour;
import org.studentfolower.data.management.Groupe;
import org.studentfolower.data.physical.Etudiant;

public class FrameFactory extends JPanel {
	private static final long serialVersionUID = 1L;
	public static final int SIZE_LARGE = 108;
	public static final int SIZE_SMALL = 48;
	public static final Map<RenderType, Dimension> DIM_IMGS = new HashMap<RenderType, Dimension>();
	static {
		DIM_IMGS.put(RenderType.PICTURE_ONLY, new Dimension(SIZE_LARGE,
				SIZE_LARGE));
		DIM_IMGS.put(RenderType.PICTURE_TEXT, new Dimension(SIZE_SMALL,
				SIZE_SMALL));
	}

	@SuppressWarnings("unused")
	private Cour cour;
	private Groupe gr;
	private RenderType type;
	private List<SingleFrame> lsFrames;

	public FrameFactory(Cour cour) {

		super();
		lsFrames = new ArrayList<SingleFrame>();
		this.cour = cour;
		gr = cour.getGr();
		for (Etudiant etu : gr.getLsEtu()) {
			lsFrames.add(new SingleFrame(etu, cour));
		}
	}

	public void setCour(Cour cour) {
		System.out.println("Cour changé");
		removeAll();
		lsFrames = new ArrayList<SingleFrame>();
		this.cour = cour;
		gr = cour.getGr();
		for (Etudiant etu : gr.getLsEtu()) {
			lsFrames.add(new SingleFrame(etu, cour));
		}
		setRenderType(type);
		repaint();
	}

	private void setGrindLayout() {
		setLayout(new GridLayout((lsFrames.size() / 2) + 1, 2));
	}

	private void setLineLayout() {

		GridLayout grid = new GridLayout(lsFrames.size() + 1, 1);
		setLayout(grid);
	}

	public void setRenderType(RenderType type) {
		removeAll();
		switch (type) {
		case PICTURE_ONLY:
			setGrindLayout();
			break;
		case PICTURE_TEXT:
		case TEXT_ONLY:
			setLineLayout();
		default:
			break;
		}
		this.type = type;
		for (SingleFrame singleFrame : lsFrames) {
			singleFrame.setType(type);
			add(singleFrame);

		}
		revalidate();
		repaint();
	}
	
	public Cour getCour() {
		return cour;
	}

}
