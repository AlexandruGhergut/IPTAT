package iptat.handlers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import iptat.gui.DrawingBoard;

public class MouseEventHandler implements MouseListener {
	
	private DrawingBoard drawingBoard;
	
	public MouseEventHandler(DrawingBoard drawingBoard) {
		this.drawingBoard = drawingBoard;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		drawingBoard.getPolygon().addPoint(e.getX(), e.getY());

		drawingBoard.repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
