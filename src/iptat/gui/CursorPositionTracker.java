package iptat.gui;

import java.awt.MouseInfo;

import javax.swing.JComponent;
import javax.swing.JLabel;

import iptat.util.Observer;

public class CursorPositionTracker extends JLabel implements Observer {
	
	/* component on which we track the cursor position
	 * if this is null, we track it relative to the screen
	 */
	JComponent component;
	
	public CursorPositionTracker() {
		component = null;
	}
	
	public CursorPositionTracker(JComponent component) {
		this.component = component;
	}
	
	
	@Override
	public void update(int state) {
		String posString = "";
		if (component == null)
			posString = "X: " + MouseInfo.getPointerInfo().getLocation().getX() +
						"    Y: " + MouseInfo.getPointerInfo().getLocation().getY();
		else 
			posString = "X: " + component.getMousePosition().getX() +
						"    Y: " + component.getMousePosition().getY();
		super.setText(posString);
	}

}
