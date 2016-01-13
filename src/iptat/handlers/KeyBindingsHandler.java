package iptat.handlers;

import java.awt.event.ActionEvent;
import java.util.Scanner;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import iptat.gui.DrawingBoard;
import iptat.util.EarCutter;
import iptat.util.Polygon2D;
import iptat.util.Polygon2DFileIO;

public class KeyBindingsHandler {
	
	private KeyBindingsHandler() {
	}
	
	public static void init(DrawingBoard drawingBoard) {
		InputMap inputMap = drawingBoard.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMap = drawingBoard.getActionMap();
		
		Action reset = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				drawingBoard.getPolygon().clear();
			}
		};
		
		inputMap.put(KeyStroke.getKeyStroke("R"), "reset");
		actionMap.put("reset", reset);
		
		Action undo = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				drawingBoard.getPolygon().removeLast();
			}
		};
		
		inputMap.put(KeyStroke.getKeyStroke("control Z"), "undo");
		actionMap.put("undo", undo);
		
		Action redo = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				drawingBoard.getPolygon().restoreLast();
			}
		};
		
		inputMap.put(KeyStroke.getKeyStroke("control X"), "redo");
		actionMap.put("redo", redo);
		
		Action triangulate = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				Polygon2D polygon = drawingBoard.getPolygon();
				
				if (!polygon.isTriangulated() && !polygon.isTriangulating()) {
					TriangulationHandler th = new TriangulationHandler(new EarCutter());
					
					try {
						th.triangulate(polygon);
						polygon.setTriangulationInProgress();
					} catch (Exception exc) {
						JOptionPane.showMessageDialog(null, exc.getMessage(), 
								"Unable to start triangulation!", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		};
		
		inputMap.put(KeyStroke.getKeyStroke("T"), "triangulate");
		actionMap.put("triangulate", triangulate);
		
		Action save = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				Polygon2DFileIO.savePolygon(drawingBoard.getPolygon());
			}
		};
		
		inputMap.put(KeyStroke.getKeyStroke("control S"), "save");
		actionMap.put("save", save);
		
		Action load = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				Polygon2DFileIO.loadPolygon(drawingBoard.getPolygon());
			}
		};
		
		inputMap.put(KeyStroke.getKeyStroke("control O"), "load");
		actionMap.put("load", load);
		
		Action addPoints = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				String inputString = JOptionPane.showInputDialog(null,
									"Example: \"1 5 2 8\" will add point (1, 5) and point (2, 8)", 
									"Add Points", JOptionPane.PLAIN_MESSAGE);
				if (inputString == null)
					return;
				
				Scanner reader = new Scanner(inputString);
				Polygon2D polygon = drawingBoard.getPolygon();
				double x, y;
				
				while (reader.hasNextDouble()) {
					x = reader.nextDouble();
					if (reader.hasNextDouble()) {
						y = reader.nextDouble();
						polygon.addPoint(x, y);
					}
				}
				reader.close();
			}
		};
		
		inputMap.put(KeyStroke.getKeyStroke("control A"), "addPoints");
		actionMap.put("addPoints", addPoints);
	}
}
