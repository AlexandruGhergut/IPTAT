package iptat.handlers;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.util.Scanner;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import com.sun.glass.events.KeyEvent;

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
		
		initFileBindings(drawingBoard, actionMap, inputMap);
		initEditBindings(drawingBoard, actionMap, inputMap);
		initTriangulationBindings(drawingBoard, actionMap, inputMap);
		affineTransformBindings(drawingBoard, actionMap, inputMap);
	}
	
	private static void initFileBindings(DrawingBoard drawingBoard, 
			ActionMap actionMap, InputMap inputMap) {
		
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
	
	private static void initEditBindings(DrawingBoard drawingBoard, 
			ActionMap actionMap, InputMap inputMap) {
		
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
		
		Action reset = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				drawingBoard.setTranslateX(0);
				drawingBoard.setTranslateY(0);
				drawingBoard.setScaleX(1);
				drawingBoard.setScaleY(1);
				
				drawingBoard.getPolygon().clear();
			}
		};
		
		inputMap.put(KeyStroke.getKeyStroke("R"), "reset");
		actionMap.put("reset", reset);
	}
	
	private static void initTriangulationBindings(DrawingBoard drawingBoard, 
			ActionMap actionMap, InputMap inputMap) {
		
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
	}
	
	private static void affineTransformBindings(DrawingBoard drawingBoard,
			ActionMap actionMap, InputMap inputMap) {
		
		Action zoomIn = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				drawingBoard.incrementScale();
			}
		};
		
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ADD, InputEvent.CTRL_DOWN_MASK), "zoomIn");
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, InputEvent.CTRL_DOWN_MASK), "zoomIn");
		actionMap.put("zoomIn", zoomIn);
		
		Action zoomOut = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				drawingBoard.decrementScale();
			}
		};
		
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SUBTRACT, InputEvent.CTRL_DOWN_MASK), "zoomOut");
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, InputEvent.CTRL_DOWN_MASK), "zoomOut");
		actionMap.put("zoomOut", zoomOut);
		
		Action zoom = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				String line = JOptionPane.showInputDialog(null, "Set zoom level to (in percentage): ", 
						"Set Zoom Level", JOptionPane.PLAIN_MESSAGE);
				
				if (line == null)
					return;
				
				if (drawingBoard.setScale(Double.parseDouble(line) / 100) != false)
					System.out.println("False");
			}
		};
		
		inputMap.put(KeyStroke.getKeyStroke("F1"), "zoom");
		actionMap.put("zoom", zoom);
		
		
		JPanel translatePanel = new JPanel();
		BoxLayout panelLayout = new BoxLayout(translatePanel, BoxLayout.PAGE_AXIS);
		translatePanel.setLayout(panelLayout);
		
		translatePanel.add(new JLabel("Translate X by: "));
		JTextField xField = new JTextField(20);
		translatePanel.add(xField);
		
		translatePanel.add(new JLabel("Translate Y by: "));
		JTextField yField = new JTextField(20);
		translatePanel.add(yField);

		Action translate = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				xField.setText("");
				yField.setText("");
				int answer = JOptionPane.showConfirmDialog(null, translatePanel, 
						"Set Translation", JOptionPane.OK_CANCEL_OPTION);
				
				if (answer == JOptionPane.OK_OPTION) {
					double x = 0, y = 0;
					
					try {
						x = Double.parseDouble(xField.getText());
						y = Double.parseDouble(yField.getText());
					} catch (NullPointerException | NumberFormatException exc) {
						x = 0;
						y = 0;
					} finally {
						drawingBoard.setTranslateX(drawingBoard.getTranslateX() + x);
						drawingBoard.setTranslateY(drawingBoard.getTranslateY() - y);
					}
				}
			}
		};
		
		inputMap.put(KeyStroke.getKeyStroke("F2"), "translate");
		actionMap.put("translate", translate);
	}
}
