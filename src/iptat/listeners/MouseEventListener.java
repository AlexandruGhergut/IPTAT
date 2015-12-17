package iptat.listeners;

import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class MouseEventListener implements MouseListener {
	
	private JPanel panel;
	private Polygon polygon;
	
	public MouseEventListener(JPanel panel, Polygon polygon) {
		this.panel = panel;
		this.polygon = polygon;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		polygon.addPoint(e.getX(), e.getY());
		//panel.revalidate();
		panel.repaint();
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
