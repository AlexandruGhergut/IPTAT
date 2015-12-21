package ipstat.util;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Polygon2D {
	
	private List<Point2D.Double> points;
	private List<Line2D.Double> edges;
	
	public Polygon2D() {
		points = new ArrayList<Point2D.Double>();
		edges = new ArrayList<Line2D.Double>();
	}
	
	public void addPoint(Point2D.Double point) {
		if (points.size() >= 1) // we need at least 2 points to begin construction of edges list
			edges.add(new Line2D.Double(points.get(points.size() - 1), point));
		
		points.add(point);
	}
	
	public<T> void addPoint(double x, double y) {
		Point2D.Double point = new Point2D.Double(x, y);
		addPoint(point);
	}
	
	public List<Point2D.Double> getPointsList() {
		return points;
	}
	
	public List<Line2D.Double>getEdgesList() {
		return edges;
	}
	
	public void draw(Graphics2D g) {
		for (Line2D.Double edge: edges)
			g.draw(edge);
		
		// draw a line between the first point and the last
		if (points.size() >= 3) {
			int lastIndex = points.size() - 1;
			g.draw(new Line2D.Double(points.get(lastIndex), points.get(0)));
		}
	}
}
