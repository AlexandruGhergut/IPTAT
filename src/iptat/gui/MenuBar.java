package iptat.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import iptat.util.CommandGenerator;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {
	
	private JFrame frame;
	private CommandGenerator commandGenerator;
	
	public MenuBar(JFrame frame) {
		this.frame = frame;
		
		commandGenerator = CommandGenerator.getInstance();
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
				commandGenerator.triggerLoadPolygon();
			}
			
		});
		loadPolygon.setAccelerator(KeyStroke.getKeyStroke("control O"));
		fileMenu.add(loadPolygon);
		
		JMenuItem savePolygon = new JMenuItem("Save Polygon...");
		savePolygon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				commandGenerator.triggerSavePolygon();
			}
			
		});
		savePolygon.setAccelerator(KeyStroke.getKeyStroke("control S"));
		fileMenu.add(savePolygon);
		
		fileMenu.addSeparator();
		
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				commandGenerator.triggerExit();
			}
			
		});
		exit.setAccelerator(KeyStroke.getKeyStroke("ESCAPE"));
		fileMenu.add(exit);
		
		super.add(fileMenu);
	}
	
	private void addEditMenu() {
	
		JMenu editMenu = new JMenu("Edit");
		
		JMenuItem undo = new JMenuItem("Undo");
		undo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				commandGenerator.triggerUndo();
			}
			
		});
		undo.setAccelerator(KeyStroke.getKeyStroke("control Z"));
		editMenu.add(undo);
		
		JMenuItem redo = new JMenuItem("Redo");
		redo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				commandGenerator.triggerRedo();
			}
			
		});
		redo.setAccelerator(KeyStroke.getKeyStroke("control X"));
		editMenu.add(redo);
		
		editMenu.addSeparator();
		
		JMenuItem reset = new JMenuItem("Reset");
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				commandGenerator.triggerClear();
			}
			
		});
		reset.setAccelerator(KeyStroke.getKeyStroke("R"));
		editMenu.add(reset);
		
		editMenu.addSeparator();
		
		JMenuItem addPoints = new JMenuItem("Add points...");
		addPoints.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				commandGenerator.triggerAddPoints();
			}
			
		});
		addPoints.setAccelerator(KeyStroke.getKeyStroke("control A"));
		editMenu.add(addPoints);
		
		super.add(editMenu);
	}
	
	private void addTriangulationMenu() {
		JMenu triangulationMenu = new JMenu("Triangulation");
		
		JMenuItem triangulate = new JMenuItem("Triangulate");
		triangulate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				commandGenerator.triggerTriangulate();
			}
			
		});
		triangulate.setAccelerator(KeyStroke.getKeyStroke("T"));
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
