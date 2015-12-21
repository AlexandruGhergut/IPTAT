package iptat.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import iptat.util.Polygon2D;

public class MouseEventListener implements MouseListener {
	
	private JPanel panel;
	private Polygon2D polygon;
	
	public MouseEventListener(JPanel panel, Polygon2D polygon) {
		this.panel = panel;
		this.polygon = polygon;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		polygon.addPoint(e.getX(), e.getY());

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
