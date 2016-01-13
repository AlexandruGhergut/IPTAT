package iptat.handlers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Point2D;

import iptat.gui.DrawingBoard;
import iptat.util.ObserverConstants;

public class MouseEventHandler implements MouseListener, MouseMotionListener, MouseWheelListener {
	
	private DrawingBoard drawingBoard;
	private MouseEvent lastPress; 
	
	private final double ZOOM_FACTOR;
	
	private double currentTranslateX;
	private double currentTranslateY;
	
	private Point2D.Double getPointOnBoard(MouseEvent e) {
		Point2D.Double point = new Point2D.Double();
		double x = (e.getX() - drawingBoard.getWidth() / 2 - drawingBoard.getTranslateX()) / drawingBoard.getScaleX();
		double y = (drawingBoard.getHeight() / 2 - e.getY() + drawingBoard.getTranslateY()) / drawingBoard.getScaleY();
		point.setLocation(x, y);
		return point;
	}
	
	public MouseEventHandler(DrawingBoard drawingBoard) {
		ZOOM_FACTOR = 0.1;
		
		this.drawingBoard = drawingBoard;
		currentTranslateX = currentTranslateY = 0;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		drawingBoard.getPolygon().addPoint(getPointOnBoard(e));
	}

	@Override
	public void mousePressed(MouseEvent e) {
		lastPress = e;
		
		currentTranslateX = drawingBoard.getTranslateX();
		currentTranslateY = drawingBoard.getTranslateY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		drawingBoard.setTranslateX(currentTranslateX + e.getX() - lastPress.getX());
		drawingBoard.setTranslateY(currentTranslateY + e.getY() - lastPress.getY());
		
		drawingBoard.update(ObserverConstants.DRAWBOARD_REPAINT);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		drawingBoard.setTranslateX(currentTranslateX + e.getX() - lastPress.getX());
		drawingBoard.setTranslateY(currentTranslateY + e.getY() - lastPress.getY());
		
		drawingBoard.update(ObserverConstants.DRAWBOARD_REPAINT);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		drawingBoard.setCursorPosition(getPointOnBoard(e));
		drawingBoard.update(ObserverConstants.CURSOR_POS_UPDATE);
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (e.getWheelRotation() < 0)
			drawingBoard.incrementScale(ZOOM_FACTOR);
		else
			drawingBoard.decrementScale(ZOOM_FACTOR);
		
		drawingBoard.update(ObserverConstants.DRAWBOARD_REPAINT);
	}

}
