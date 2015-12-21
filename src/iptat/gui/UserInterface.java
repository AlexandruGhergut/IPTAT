package iptat.gui;

import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

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
		container.add(new DrawingBoard());
		Toolbar toolbar = new Toolbar();
		container.add(toolbar, BorderLayout.PAGE_START);
	}
}
