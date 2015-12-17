package iptat.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

import javax.swing.JPanel;

public class DrawingBoard extends JPanel {
	
	private Polygon polygon;
	
	public DrawingBoard() {
		polygon = new Polygon();
	}
	
	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent((Graphics2D) graphics);
	}
}
