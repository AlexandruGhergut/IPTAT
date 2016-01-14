package iptat.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import iptat.handlers.KeyBindingsHandler;
import iptat.handlers.MouseEventHandler;
import iptat.util.Observer;
import iptat.util.ObserverConstants;
import iptat.util.Polygon2D;
import iptat.util.Subject;

@SuppressWarnings("serial")
public class DrawingBoard extends JPanel implements Subject, Observer {
	
	private final double ZOOM_FACTOR;
	
	private List<Observer> observers;
	
	private Polygon2D polygon;
	private Point2D.Double cursorPosition;
	private boolean drawAxis;
	private double scaleX;	
	private double scaleY;
	private double translateX;
	private double translateY;
	
	public DrawingBoard() {
		ZOOM_FACTOR = 0.1;
		
		super.setBackground(Color.WHITE);
		
		observers = new ArrayList<Observer>();
		setPolygon(new Polygon2D());
		
		MouseEventHandler mouseHandler = new MouseEventHandler(this);
		super.addMouseListener(mouseHandler);
		super.addMouseMotionListener(mouseHandler);
		super.addMouseWheelListener(mouseHandler);
		KeyBindingsHandler.init(this);
		
		drawAxis = false;
		cursorPosition = new Point2D.Double(0, 0);
		setScaleX(1);
		setScaleY(1);
		setTranslateX(0);
		setTranslateY(0);
	}

	public double getTranslateY() {
		return translateY;
	}
	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);

		Graphics2D g2 = (Graphics2D) graphics;
		
		// draw with sub-pixel precision
		g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
                RenderingHints.VALUE_STROKE_PURE);
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		if (drawAxis) {
			Color prevColor = g2.getColor();
			
			g2.setColor(Color.BLUE);
			g2.drawLine(0, super.getHeight() / 2, super.getWidth(), super.getHeight() / 2);
			g2.drawLine(super.getWidth() / 2, 0, super.getWidth() / 2, super.getHeight());
			g2.fillOval(super.getWidth() / 2 - 3, super.getHeight() / 2 - 3, 6, 6);
			
			final int distance = 10, width = super.getWidth(), height = super.getHeight();
			
			int[] arrow1X = {width, width - distance, width - distance};
			int[] arrow1Y = {height / 2, height / 2 - distance, height / 2 + distance};
			g2.fillPolygon(arrow1X, arrow1Y, 3);
			
			int[] arrow2X = {width / 2, width / 2 - distance, width / 2 + distance};
			int[] arrow2Y = {0, distance, distance};
			g2.fillPolygon(arrow2X, arrow2Y, 3);
			
			g2.setColor(prevColor);
		}
		
		AffineTransform transformer = g2.getTransform();
		// set origin to center of the drawingboard
		transformer.translate(super.getWidth() / 2 + translateX, super.getHeight() / 2 + translateY);
		// flip Y-axis
		transformer.scale(scaleX, -scaleY); // flip Y axis
		
		
		g2.setTransform(transformer);
		
		polygon.draw(g2);
	}
	
	public Polygon2D getPolygon() {
		return polygon;
	}
	
	public void setPolygon(Polygon2D polygon) {
		polygon.removeObserver(this);
		
		this.polygon = polygon;
		polygon.addObserver(this);
		
		update(ObserverConstants.DRAWBOARD_REPAINT);
	}

	@Override
	public void update(int state) {
		if (state == ObserverConstants.DRAWBOARD_REPAINT)
			repaint();
		
		notifyObservers(state);
	}

	@Override
	public void addObserver(Observer ob) {
		observers.add(ob);
	}

	@Override
	public void removeObserver(Observer ob) {
		int index = observers.indexOf(ob);
		if (index != -1)
			observers.remove(ob);
	}

	@Override
	public void notifyObservers(int state) {
		for (Observer ob: observers) 
			ob.update(state);
	}
	
	public Point2D.Double getCursorPosition() {
		return cursorPosition;
	}
	
	public void setCursorPosition(Point2D.Double point) {
		cursorPosition = point;
		update(ObserverConstants.CURSOR_POS_UPDATE);
	}
	
	public boolean incrementScaleX(double amount) {
		if (scaleX + amount >= 0.09) {
			scaleX += amount;
			update(ObserverConstants.DRAWBOARD_REPAINT);
			
			return true;
		}
		
		return false;
	}
	
	public boolean incrementScaleX() {
		return incrementScaleX(ZOOM_FACTOR);
	}
	
	public boolean incrementScaleY(double amount) {
		if (scaleY + amount >= 0.09) {
			scaleY += amount;
			update(ObserverConstants.DRAWBOARD_REPAINT);
			
			return true;
		}
		
		return false;
	}
	
	public boolean incrementScaleY() {
		return incrementScaleY(ZOOM_FACTOR);
	}
	
	public boolean incrementScale(double amount) {
		if (scaleX + amount >= 0.09 && scaleY + amount >= 0.09) {
			incrementScaleX(amount);
			incrementScaleY(amount);
		}
		
		return false;
	}
	
	public boolean incrementScale() {
		return incrementScale(ZOOM_FACTOR);
	}
	
	public boolean decrementScaleX(double amount) {
		return incrementScaleX(-amount);
	}
	
	public boolean decrementScaleX() {
		return decrementScaleX(ZOOM_FACTOR);
	}
	
	public boolean decrementScaleY(double amount) {
		return incrementScaleY(-amount);
	}
	
	public boolean decrementScaleY() {
		return decrementScaleY(ZOOM_FACTOR);
	}
	
	public boolean decrementScale(double amount) {
		return incrementScale(-amount);
	}
	
	public boolean decrementScale() {
		return decrementScale(ZOOM_FACTOR);
	}
	
	public boolean setScaleX(double amount) {
		return incrementScaleX(-scaleX + amount);
	}
	
	public boolean setScaleY(double amount) {
		return incrementScaleY(-scaleY + amount);
	}
	
	public boolean setScale(double amount) {
		if (amount >= 0.1) {
			setScaleX(amount);
			setScaleY(amount);
			return true;
		}
		
		return false;
	}
	
	public double getScaleX() {
		return scaleX;
	}
	
	public double getScaleY() {
		return scaleY;
	}
	
	public void setTranslateX(double translateX) {
		this.translateX = translateX;
		update(ObserverConstants.DRAWBOARD_REPAINT);
	}

	public void setTranslateY(double translateY) {
		this.translateY = translateY;
		update(ObserverConstants.DRAWBOARD_REPAINT);
	}

	public double getTranslateX() {
		return translateX;
	}
	
	public void toggleAxisDraw() {
		drawAxis = !drawAxis;
		update(ObserverConstants.DRAWBOARD_REPAINT);
	}
}
