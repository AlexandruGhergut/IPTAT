package iptat.util;

import java.awt.geom.Point2D;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Polygon2DFileIO {
	
	private static JFileChooser fileChooser = new JFileChooser();
	
	public static void savePolygon(Polygon2D polygon) {
		int selectedOption = fileChooser.showSaveDialog(null);
		
		if (selectedOption == JFileChooser.APPROVE_OPTION) {
			LinkedList<Point2D.Double> points = polygon.getPointsList();
			
			try {
				FileWriter fileWriter = new FileWriter(fileChooser.getSelectedFile());
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				
				for (Point2D.Double point: points)
					bufferedWriter.write(point.getX() + " " + point.getY() + "\n");
				
				bufferedWriter.close();
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void loadPolygon(Polygon2D polygon) {
		int selectedOption = fileChooser.showOpenDialog(null);
		
		if (selectedOption == JFileChooser.APPROVE_OPTION) {
			try {
				Scanner reader = new Scanner(fileChooser.getSelectedFile());
				
				double x, y;
				polygon.clear();
				while (reader.hasNextDouble()) {
					x = reader.nextDouble();
					
					if (!reader.hasNextDouble()) 
						JOptionPane.showMessageDialog(fileChooser, "Invalid file!",
														"Error", JOptionPane.ERROR_MESSAGE);
					else {
						y = reader.nextDouble();
						polygon.addPoint(x, y);
					}
				}
				reader.close();
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(fileChooser, "File not found!",
												"Error", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
	}
}
