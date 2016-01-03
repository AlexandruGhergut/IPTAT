package iptat.handlers;

import java.awt.geom.Point2D;
import java.util.Iterator;
import java.util.List;

import javax.swing.SwingWorker;

import iptat.util.Polygon2D;
import iptat.util.Triangulator;

public class TriangulationHandler extends SwingWorker<Void, Void> {
	
	private Triangulator triangulator;
	private Polygon2D polygon;
	private List<List<Point2D.Double>> triangulation; 
	
	public TriangulationHandler(Triangulator triangulator) {
		this.triangulator = triangulator;
		triangulation = null;
	}
	
	public void setTriangulator(Triangulator triangulator) {
		this.triangulator = triangulator;
	}

	// this uses the sign from a part of the Shoelace formula
	private boolean isOrderedCounterclockwise(Polygon2D polygon) {
		Iterator<Point2D.Double> it = polygon.getPointsList().iterator();
		
		// it is guaranteed that polygon has at least 3 points
		Point2D.Double previous = it.next();
		Point2D.Double current;
		
		// this uses the sign from a part of shoelace formula
		double result = 0;
		while (it.hasNext()) {
			current = it.next();	
			
			result += previous.getX() * current.getY() - current.getX() * previous.getY();
			previous = current;
		}
		
		// now first with last (previous is last)
		it = polygon.getPointsList().iterator();
		current = it.next();
		result += previous.getX() * current.getY() - current.getX() * previous.getY();
		
		return result > 0;
	}
	
	public void triangulate(Polygon2D polygon) throws Exception {
		if (triangulator == null)
			throw new Exception("Triangulator not set.");
		
		if (polygon.getPointsCount() < 3)
			throw new Exception("At least 3 points should be provided before starting a triangulation.");
		
		if (!isOrderedCounterclockwise(polygon))
			throw new Exception("Points are not supplied in a counterclockwise order.");
		
		this.polygon = polygon;
		
		this.execute();
	}
	
	@Override
	protected Void doInBackground() throws Exception {
		triangulation = triangulator.getTriangulation(polygon);
		
		return null;
	}
	
	@Override
	protected void done() {
		polygon.setTriangulation(triangulation);
	}

}
