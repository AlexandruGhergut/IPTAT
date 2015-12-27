package iptat.handlers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;

import iptat.gui.DrawingBoard;
import iptat.util.ObserverConstants;

public class MouseEventHandler implements MouseListener, MouseMotionListener {
	
	private DrawingBoard drawingBoard;
	
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
		drawingBoard.getPolygon().addPoint(e.getX(), -e.getY());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Point2D.Double point = new Point2D.Double(e.getX(), e.getY());
		drawingBoard.setCursorPosition(point);
		drawingBoard.update(ObserverConstants.CURSOR_POS_UPDATE);
	}

}
