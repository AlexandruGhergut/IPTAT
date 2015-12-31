package iptat.handlers;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import iptat.gui.DrawingBoard;
import iptat.util.Earcutting;
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
				if (!polygon.isTriangulated())
					polygon.setTriangulation(Earcutting.earcutting(polygon));
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
	}
}
