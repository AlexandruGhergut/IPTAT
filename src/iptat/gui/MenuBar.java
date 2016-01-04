package iptat.gui;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.sun.glass.events.KeyEvent;

public class MenuBar extends JMenuBar {
	
	private Robot robot;
	
	public MenuBar() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		
		addFileMenu();
		addEditMenu();
		addTriangulationMenu();
	}
	
	private void addFileMenu() {
		
		JMenu fileMenu = new JMenu("File");
		
		JMenuItem loadPolygon = new JMenuItem("Load Polygon...");
		loadPolygon.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_O);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				robot.keyRelease(KeyEvent.VK_O);
			}
			
		});
		fileMenu.add(loadPolygon);
		
		JMenuItem savePolygon = new JMenuItem("Save Polygon...");
		savePolygon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_S);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				robot.keyRelease(KeyEvent.VK_S);
			}
			
		});
		fileMenu.add(savePolygon);
		
		fileMenu.addSeparator();
		
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
		fileMenu.add(exit);
		
		super.add(fileMenu);
	}
	
	private void addEditMenu() {
	
		JMenu editMenu = new JMenu("Edit");
		
		JMenuItem undo = new JMenuItem("Undo");
		undo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_Z);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				robot.keyRelease(KeyEvent.VK_Z);
			}
			
		});
		editMenu.add(undo);
		
		JMenuItem redo = new JMenuItem("Redo");
		redo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_X);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				robot.keyRelease(KeyEvent.VK_X);
			}
			
		});
		editMenu.add(redo);
		
		editMenu.addSeparator();
		
		JMenuItem clear = new JMenuItem("Clear");
		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				robot.keyPress(KeyEvent.VK_R);
				robot.keyRelease(KeyEvent.VK_R);
			}
			
		});
		editMenu.add(clear);
		
		editMenu.addSeparator();
		
		JMenuItem addPoints = new JMenuItem("Add points...");
		addPoints.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_A);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				robot.keyRelease(KeyEvent.VK_A);
			}
			
		});
		editMenu.add(addPoints);
		
		super.add(editMenu);
	}
	
	private void addTriangulationMenu() {
		JMenu triangulationMenu = new JMenu("Triangulation");
		
		JMenuItem triangulate = new JMenuItem("Triangulate");
		triangulate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				robot.keyPress(KeyEvent.VK_T);
				robot.keyRelease(KeyEvent.VK_T);
			}
			
		});
		triangulationMenu.add(triangulate);
		
		super.add(triangulationMenu);
	}
}
