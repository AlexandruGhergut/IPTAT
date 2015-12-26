package iptat.util;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Polygon2D implements Subject {
	
	private final static double RADIUS = 6;
	
	private List<Observer> observers;
	
	private LinkedList<Point2D.Double> points;
	private LinkedList<Line2D.Double> edges;
	private Stack<Point2D.Double> redoPoints;
	private Stack<Line2D.Double> redoEdges;
	
	public Polygon2D() {
		observers = new ArrayList<Observer>();
		points = new LinkedList<Point2D.Double>();
		edges = new LinkedList<Line2D.Double>();
		redoPoints = new Stack<Point2D.Double>();
		redoEdges = new Stack<Line2D.Double>();
	}
	
	public void addPoint(Point2D.Double point) {
		if (points.size() >= 1) // we need at least 2 points to begin construction of edges list
			edges.add(new Line2D.Double(points.get(points.size() - 1), point));
		
		points.add(point);
		
		notifyObservers(ObserverConstants.DRAWBOARD_REPAINT);
		
		redoPoints.clear();
		redoEdges.clear();
	}
	
	public void addPoint(double x, double y) {
		Point2D.Double point = new Point2D.Double(x, y);
		addPoint(point);
	}
	
	// removes the last added point
	public boolean removeLast() {
		if (!points.isEmpty()) {
			redoPoints.push(points.removeLast());
			if (!edges.isEmpty())
				redoEdges.push(edges.removeLast());
			
			notifyObservers(ObserverConstants.DRAWBOARD_REPAINT);
			return true;
		}
		
		return false;
	}
	
	// restores the last removed point
	public boolean restoreLast() {
		if (!redoPoints.empty()) {
			points.add(redoPoints.pop());
			
			if (points.size() > 1)
				edges.add(redoEdges.pop());
			
			notifyObservers(ObserverConstants.DRAWBOARD_REPAINT);
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
	
	public int getPointsCount() {
		return points.size();
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

	@Override
	public void addObserver(Observer ob) {
		observers.add(ob);
	}

	@Override
	public void removeObserver(Observer ob) {
		int index = observers.indexOf(ob);
		if (index != -1)
			observers.remove(index);
	}

	@Override
	public void notifyObservers(int state) {
		for (Observer ob: observers)
			ob.update(state);
	}
}
