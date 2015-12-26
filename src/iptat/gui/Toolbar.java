package iptat.gui;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JToolBar;

public class Toolbar extends JToolBar {
	
	private final int IMAGE_SIZE = 16;
	
	public Toolbar() {
		super.setFloatable(false);
		addButtons();
	}
	
	// TO-DO
	private void addButtons() {
		super.add(new JButton("Example"));
	}
	
	private	Image getResizedImage(Image src, int width, int height) {
		BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImage.createGraphics();

		g2.drawImage(src, 0, 0, width, height, null);
		g2.dispose();
		
		return resizedImage;
	}
}
