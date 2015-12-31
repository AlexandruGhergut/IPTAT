package iptat.gui;

import javax.swing.JLabel;

import iptat.util.Observer;

public class CursorPositionTracker extends JLabel implements Observer {
	
	private DrawingBoard drawingBoard;
	
	public CursorPositionTracker(DrawingBoard drawingBoard) {
		this.drawingBoard = drawingBoard;
	}
	
	@Override
	public void update(int state) {
		double x = drawingBoard.getCursorPosition().getX();
		double y = drawingBoard.getCursorPosition().getY();
		
		super.setText("X: " + x + "   Y: " + y);
	}

}
