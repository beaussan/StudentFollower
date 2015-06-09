package org.studentfolower.ihm;

import javax.swing.JButton;

import org.studentfolower.data.management.Cour;

public class HistButton extends JButton {

	private Cour cour;
	private FrameFactory f;

	public HistButton(Cour cour , String label) {
		super(label);
		this.cour = cour;
		f = new FrameFactory(cour);
	}

	public Cour getCour() {
		return cour;
	}
	
	public FrameFactory getFrameFactory() {
		return f;
	}

}
