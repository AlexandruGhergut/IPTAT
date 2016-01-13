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
	
	private Point2D.Double getPointOnBoard(MouseEvent e) {
		Point2D.Double point = new Point2D.Double();
		double x = e.getX() - drawingBoard.getWidth() / 2;
		double y = drawingBoard.getHeight() / 2 - e.getY();
		point.setLocation(x, y);
		return point;
	}
	
	public MouseEventHandler(DrawingBoard drawingBoard) {
		this.drawingBoard = drawingBoard;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		drawingBoard.getPolygon().addPoint(getPointOnBoard(e));
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		drawingBoard.setCursorPosition(getPointOnBoard(e));
		drawingBoard.update(ObserverConstants.CURSOR_POS_UPDATE);
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if(e.getWheelRotation() < 0)
			drawingBoard.incrementScale(0.1);
		else
			drawingBoard.decrementScale(0.1);
		drawingBoard.update(ObserverConstants.DRAWBOARD_REPAINT);
	}

}
