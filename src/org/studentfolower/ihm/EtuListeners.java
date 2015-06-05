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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.studentfolower.data.management.Cour;
import org.studentfolower.data.physical.Etudiant;

public class EtuListeners implements MouseListener {
	
	private JButton back = new JButton("Retour");
	
	private static final long msTime = 300;
	
	private Etudiant etu;

	private Cour cour;

	private long timeClick;
	
	private JFrame fr;

	public EtuListeners(Etudiant etu, Cour cour) {
		this.etu = etu;
		this.cour = cour;
	}

	private void longClick() {
		
		// TODO TODO TODO TODO TODO TODO faire la popup des info de l'�tudiant
		
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
		infoPanel.setLayout(new GridLayout(0,1));
		
		if (this.etu.getName() != null) {
			JLabel name = new JLabel(etu.getName());
			infoPanel.add(name);
		}
		if (this.etu.getGroup() != null) {
			JLabel grp = new JLabel(etu.getGroup().toString());
			infoPanel.add(grp);
		}
		if (this.etu.getNumber() != null) {
			JLabel num = new JLabel(etu.getNumber());
			infoPanel.add(num);
		}
		if (this.etu.getEmail() != null) {
			JLabel mail = new JLabel(etu.getEmail());
			infoPanel.add(mail);
		}
		if (this.etu.getPostal() != null) {
			JLabel post = new JLabel(etu.getPostal());
			infoPanel.add(post);
		}
		
		fr.add(infoPanel, BorderLayout.CENTER);
		
		back.addActionListener(
				new java.awt.event.ActionListener() {
					@Override
					public void actionPerformed(
							java.awt.event.ActionEvent evt) {

						fr.dispose();

					}
				});
		
		fr.pack();
		fr.setVisible(true);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		timeClick = System.currentTimeMillis();

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		long timeEnd = System.currentTimeMillis();
		if (timeEnd - timeClick > msTime) {
			longClick();
		} else {
			singleClick();
			e.getComponent().repaint();
		}
	}

	private void singleClick() {
		cour.setNextStatus(etu);
	}

}
