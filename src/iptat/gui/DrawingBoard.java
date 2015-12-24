package iptat.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Observer;

import javax.swing.JPanel;

import iptat.handlers.KeyBindingsHandler;
import iptat.handlers.MouseEventHandler;
import iptat.util.Polygon2D;

public class DrawingBoard extends JPanel implements Observer {
	
	private Polygon2D polygon;
	
	public DrawingBoard() {
		super.setBackground(Color.WHITE);
		
		setPolygon(new Polygon2D());
		
		super.addMouseListener(new MouseEventHandler(this));
		KeyBindingsHandler.init(this);
	}
	
	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);

		Graphics2D g2 = (Graphics2D) graphics;
		
		// draw with sub-pixel precision
		g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
                RenderingHints.VALUE_STROKE_PURE);
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		polygon.draw(g2);
	}
	
	public Polygon2D getPolygon() {
		return polygon;
	}
	
	public void setPolygon(Polygon2D polygon) {
		polygon.deleteObserver(this);
		
		this.polygon = polygon;
		polygon.addObserver(this);
		
		update(polygon, null);
	}

	@Override
	public void update(java.util.Observable o, Object arg) {
		repaint();
	}
}
