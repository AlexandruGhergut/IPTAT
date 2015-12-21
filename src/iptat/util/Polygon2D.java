package iptat.util;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Polygon2D {
	
	private final static double RADIUS = 6;
	
	private LinkedList<Point2D.Double> points;
	private LinkedList<Line2D.Double> edges;
	private Stack<Point2D.Double> redoPoints;
	private Stack<Line2D.Double> redoEdges;
	
	public Polygon2D() {
		points = new LinkedList<Point2D.Double>();
		edges = new LinkedList<Line2D.Double>();
		redoPoints = new Stack<Point2D.Double>();
		redoEdges = new Stack<Line2D.Double>();
	}
	
	public void addPoint(Point2D.Double point) {
		if (points.size() >= 1) // we need at least 2 points to begin construction of edges list
			edges.add(new Line2D.Double(points.get(points.size() - 1), point));
		
		points.add(point);
		
		redoPoints.clear();
		redoEdges.clear();
	}
	
	public void addPoint(double x, double y) {
		Point2D.Double point = new Point2D.Double(x, y);
		addPoint(point);
	}
	
	public boolean removeLast() {
		if (!points.isEmpty()) {
			redoPoints.push(points.removeLast());
			if (!edges.isEmpty())
				redoEdges.push(edges.removeLast());
			
			return true;
		}
		
		return false;
	}
	
	public boolean restoreLast() {
		if (!redoPoints.empty()) {
			points.add(redoPoints.pop());
			
			if (points.size() > 1)
				edges.add(redoEdges.pop());
			
			return true;
		}
		
		return false;
	}
	
	public List<Point2D.Double> getPointsList() {
		return points;
	}
	
	public List<Line2D.Double> getEdgesList() {
		return edges;
	}
	
	public void draw(Graphics2D g) {
		for (Line2D.Double edge: edges)
			g.draw(edge);
		
		// emphasize points by drawing circles centered on them
		for (Point2D.Double point: points)
			g.fill(new Ellipse2D.Double(point.getX() - RADIUS / 2,
					point.getY() - RADIUS / 2, RADIUS, RADIUS));
		
		// draw a line between the first point and the last
		if (points.size() >= 3) {
			int lastIndex = points.size() - 1;
			g.draw(new Line2D.Double(points.get(lastIndex), points.get(0)));
		}
	}
}
