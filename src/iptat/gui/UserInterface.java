package iptat.gui;

import java.awt.Toolkit;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class UserInterface implements Runnable {
	
	private JFrame frame;

	@Override
	public void run() {
		frame = new JFrame("Main Window");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		frame.setPreferredSize(new Dimension(screenSize.width / 2, screenSize.height / 2));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		createComponents(frame.getContentPane());
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	private void createComponents(Container container) {
		frame.setLayout(new GridBagLayout());
		
		DrawingBoard drawingBoard = new DrawingBoard();
		
		GridBagConstraints constraints;
		
		Toolbar toolbar = new Toolbar();
		constraints = getConstraints(0, 0, 2, 1, 0, GridBagConstraints.HORIZONTAL);
		container.add(toolbar, constraints);
		
		CursorPositionTracker positionLabel = new CursorPositionTracker(drawingBoard);
		constraints = getConstraints(0, 2, 1, 1, 0, GridBagConstraints.HORIZONTAL);
		container.add(positionLabel, constraints);
		
		PolygonPointCounter pointCountLabel = new PolygonPointCounter(drawingBoard);
		constraints = getConstraints(1, 2, 1, 0, 0, GridBagConstraints.HORIZONTAL);
		container.add(pointCountLabel, constraints);
		
		constraints = getConstraints(0, 1, 2, 1, 1, GridBagConstraints.BOTH);
		container.add(drawingBoard, constraints);
		
		drawingBoard.addObserver(positionLabel);
		drawingBoard.addObserver(pointCountLabel);
	}
	
	private GridBagConstraints getConstraints(int x, int y, int width, int weightx, int weighty, int fill) {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = x;
		c.gridy = y;
		c.gridwidth = width;
		c.weightx = weightx;
		c.weighty = weighty;
		c.fill = fill;
		
		return c;
	}
}
