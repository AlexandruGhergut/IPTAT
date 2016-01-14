package iptat.gui;

import java.awt.Toolkit;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import java.io.File;
import java.io.IOException;

public class UserInterface implements Runnable {
	
	private JFrame frame;

	@Override
	public void run() {	
		File settings = new File("config/settings.ini");
		if (!settings.exists()) {
			String line = "Hello there!\n" + 
						"It looks like this is the first time you're running this application.\n" +
						"Note: If the polygon vertices are not provided in a counterclockwise order, "+
			"the algorithm will reverse their order!\n"
			+ "If you need extra information you can check the help button,"
			+ " on the right side of the toolbar.";
			JOptionPane.showMessageDialog(null, line);
			try {
				settings.getParentFile().mkdirs();
				settings.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		frame = new JFrame("Main Window");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		frame.setPreferredSize(new Dimension(screenSize.width / 2, screenSize.height / 2));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		frame.setJMenuBar(new MenuBar(frame));
		
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
