package iptat.gui;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;

import iptat.util.CommandGenerator;

public class Toolbar extends JToolBar {
	
	private final int IMAGE_SIZE = 16;
	private CommandGenerator commandGenerator;
		
	public Toolbar() {
		commandGenerator=CommandGenerator.getInstance();
		
		super.setFloatable(false);
		addFileButtons();
		super.addSeparator();
		addEditButtons();
		super.addSeparator();
		addTriangulationButton();
		
		super.add(Box.createHorizontalGlue());
		addHelpButton();
	}
	
	private void addFileButtons() {
		ImageIcon load = new ImageIcon("res/img/open.png");
		load.setImage(getResizedImage(load.getImage(), IMAGE_SIZE, IMAGE_SIZE));
		JButton loadPolygon = new JButton(load);
		super.add(loadPolygon);
		loadPolygon.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				commandGenerator.triggerLoadPolygon();
			}	
		});
		loadPolygon.setToolTipText("Load Polygon (Ctrl+O)");
		
		ImageIcon save = new ImageIcon("res/img/save.png");
		save.setImage(getResizedImage(save.getImage(), IMAGE_SIZE, IMAGE_SIZE));
		JButton savePolygon = new JButton(save);
		super.add(savePolygon);
		savePolygon.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				commandGenerator.triggerSavePolygon();
			}		
		});
		savePolygon.setToolTipText("Save Polygon (Ctrl+S)");
	}
	
	private void addEditButtons(){
		ImageIcon add = new ImageIcon("res/img/add.png");
		add.setImage(getResizedImage(add.getImage(), IMAGE_SIZE, IMAGE_SIZE));
		JButton addPoints = new JButton(add);
		super.add(addPoints);
		addPoints.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				commandGenerator.triggerAddPoints();
			}		
		});
		addPoints.setToolTipText("Add Points (Ctrl+A)");
		
		ImageIcon delete = new ImageIcon("res/img/clear.png");
		delete.setImage(getResizedImage(delete.getImage(), IMAGE_SIZE, IMAGE_SIZE));
		JButton deletePoints = new JButton(delete);
		super.add(deletePoints);
		deletePoints.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				commandGenerator.triggerClear();
			}
		});
		deletePoints.setToolTipText("Reset (R)");
		
		ImageIcon undo = new ImageIcon("res/img/undo.png");
		undo.setImage(getResizedImage(undo.getImage(), IMAGE_SIZE, IMAGE_SIZE));
		JButton undoButton = new JButton(undo);
		super.add(undoButton);
		undoButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				commandGenerator.triggerUndo();
			}			
		});
		undoButton.setToolTipText("Undo (Ctrl+Z)");
		
		ImageIcon redo = new ImageIcon("res/img/redo.png");
		redo.setImage(getResizedImage(redo.getImage(), IMAGE_SIZE, IMAGE_SIZE));
		JButton redoButton = new JButton(redo);
		super.add(redoButton);
		redoButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				commandGenerator.triggerRedo();
			}
			
		});
		redoButton.setToolTipText("Redo (Ctrl+X)");
	}
	
	private void addHelpButton(){
		ImageIcon help = new ImageIcon("res/img/help.png");
		help.setImage(getResizedImage(help.getImage(), IMAGE_SIZE, IMAGE_SIZE));
		JButton helpButton = new JButton(help);
		super.add(helpButton);
		helpButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "The purpose of this application is to show " +
			"the invariance of polygon triangulations under affine transformations.\n"+
			"There are three ways you can use to provide a polygon:\n"+
			"1. You can click on the drawing board for each vertex you want to add to your polygon;\n"+
			"2. You can load a file containing the coordinates of a polygon vertex on each line;\n"+
			"3. You can use the \"Add Points (Ctrl+A)\" button;\n"+
			"Note: if the polygon vertices are not provided in a counterclockwise order, "+
			"the algorithm will reverse their order!\n"+
			"There are two available affine transformations:\n"
			+ "1. Zoom (by scrolling the mouse wheel);\n"
			+ "2. Translation (by dragging your mouse while pressing its left button);");
			}
			
		});
		helpButton.setToolTipText("Help");
	}
	private void addTriangulationButton(){
		ImageIcon triangulation = new ImageIcon("res/img/triangle.png");
		triangulation.setImage(getResizedImage(triangulation.getImage(), IMAGE_SIZE, IMAGE_SIZE));
		JButton triangulationButton = new JButton(triangulation);
		super.add(triangulationButton);
		triangulationButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				commandGenerator.triggerTriangulate();
			}
		});
		triangulationButton.setToolTipText("Triangulate (T)");
	}
	
	private	Image getResizedImage(Image src, int width, int height) {
		BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImage.createGraphics();

		g2.drawImage(src, 0, 0, width, height, null);
		g2.dispose();
		
		return resizedImage;
	}
}
