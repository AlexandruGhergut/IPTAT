package iptat.gui;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.sun.glass.events.KeyEvent;

public class MenuBar extends JMenuBar {
	
	private Robot robot;
	private JFrame frame;
	
	public MenuBar(JFrame frame) {
		this.frame = frame;
		
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		
		addFileMenu();
		addEditMenu();
		addTriangulationMenu();
		addThemeMenu();
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
	
	private void addThemeMenu() {
		JMenu themeMenu = new JMenu("Theme");
		
		ButtonGroup themesGroup = new ButtonGroup();
		
		JRadioButtonMenuItem javaTheme = new JRadioButtonMenuItem("Java");
		javaTheme.setSelected(true);
		javaTheme.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				javaTheme.setSelected(true);
				changeTheme(UIManager.getCrossPlatformLookAndFeelClassName());
			}
			
		});
		themesGroup.add(javaTheme);
		themeMenu.add(javaTheme);
		
		JRadioButtonMenuItem nativeTheme = new JRadioButtonMenuItem("Native");
		nativeTheme.setSelected(true);
		nativeTheme.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nativeTheme.setSelected(true);
				changeTheme(UIManager.getSystemLookAndFeelClassName());
			}
			
		});
		themesGroup.add(nativeTheme);
		themeMenu.add(nativeTheme);
		
		super.add(themeMenu);
	}
	
	private void changeTheme(String theme) {
		try {
			UIManager.setLookAndFeel(theme);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(frame);
		frame.pack();
	}
}
