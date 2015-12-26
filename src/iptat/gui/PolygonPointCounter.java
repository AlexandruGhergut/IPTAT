package iptat.gui;

import javax.swing.JLabel;

import iptat.util.Observer;
import iptat.util.ObserverConstants;

public class PolygonPointCounter extends JLabel implements Observer {

	private DrawingBoard drawingBoard;
	
	public PolygonPointCounter(DrawingBoard drawingBoard) {
		this.drawingBoard = drawingBoard;
	}
	@Override
	public void update(int state) {
		if (state == ObserverConstants.DRAWBOARD_REPAINT)
			super.setText("Number of points: " + drawingBoard.getPolygon().getPointsCount() + " ");
	}
}
