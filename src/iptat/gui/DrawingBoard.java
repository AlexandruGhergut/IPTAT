package iptat.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.JPanel;

import iptat.listeners.MouseEventListener;

public class DrawingBoard extends JPanel {
	
	private Polygon polygon;
	
	public DrawingBoard() {
		super.setBackground(Color.WHITE);
		
		polygon = new Polygon();
		super.addMouseListener(new MouseEventListener(this, polygon));
	}
	
	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		graphics.drawPolygon(polygon);
	}
}
