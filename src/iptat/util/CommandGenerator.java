package iptat.util;

import java.awt.AWTException;
import java.awt.Robot;

import com.sun.glass.events.KeyEvent;

public class CommandGenerator {
	
	private static CommandGenerator commandGenerator = new CommandGenerator();
	private static Robot robot;
	
	private CommandGenerator() {
	}
	
	public static CommandGenerator getInstance() {
		if (robot == null)
			try {
				robot = new Robot();
			} catch (AWTException e) {
				e.printStackTrace();
			}
		
		return commandGenerator;
	}
	
	public void triggerLoadPolygon() {
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_O);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_O);
	}
	
	public void triggerSavePolygon() {
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_S);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_S);
	}
	
	public void triggerExit() {
		System.exit(0);
	}
	
	public void triggerUndo() {
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_Z);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_Z);
	}
	
	public void triggerRedo() {
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_X);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_X);
	}
	
	public void triggerClear() {
		robot.keyPress(KeyEvent.VK_R);
		robot.keyRelease(KeyEvent.VK_R);
	}
	
	public void triggerAddPoints() {
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_A);
	}
	
	public void triggerTriangulate() {
		robot.keyPress(KeyEvent.VK_T);
		robot.keyRelease(KeyEvent.VK_T);
	}
}
