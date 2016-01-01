package iptat.handlers;

import java.awt.geom.Point2D;
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
	
	public void triangulate(Polygon2D polygon) {
		if (triangulator == null)
			return;
		
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
