package iptat.util;

import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

public class EarCutter implements Triangulator {
	private List<List<Point2D.Double>> triangles;

	public List<List<Point2D.Double>> getTriangulation(Polygon2D polygon) {
		List<Point2D.Double> points = new LinkedList<>(polygon.getPointsList());
		triangles = new LinkedList<List<Point2D.Double>>();
		getTriangles(points);
		return triangles;
	}

	private void getTriangles(List<Point2D.Double> points) {
		int n = points.size();
		
		// true means the current point is a principal vertex
		boolean flag = true;
		while (n > 2) {
			flag = true;
			Point2D.Double pointA = new Point2D.Double(points.get(n - 3).getX(), points.get(n - 3).getY());
			Point2D.Double pointB = new Point2D.Double(points.get(n - 2).getX(), points.get(n - 2).getY());
			Point2D.Double pointC = new Point2D.Double(points.get(n - 1).getX(), points.get(n - 1).getY());
			double delta = getDet2(pointA, pointB, pointC);
			
			// pointB is a convex vertex
			if (delta > 0) {
				for (int i = 0; i < n - 3 && flag; i++) {
					double a = Math.abs(getDet2(pointA, points.get(i), pointB));
					double b = Math.abs(getDet2(pointB, points.get(i), pointC));
					double c = Math.abs(getDet2(pointC, points.get(i), pointA));
					
					if (delta == (a + b + c)) {
						// the current point is included in the triangle
						// pointB is a non-principal vertex
						flag = false;
					}
				}
				
				if (flag == true) {
					// we cut the "ear"
					points.remove(n - 2);
					LinkedList<Point2D.Double> triangle = new LinkedList<Point2D.Double>();
					triangle.add(pointA);
					triangle.add(pointB);
					triangle.add(pointC);
					triangles.add(triangle);
				} else {
					points.remove(n - 1);
					points.add(0, pointC);
				}
			} else {
				points.remove(n - 1);
				points.add(0, pointC);
			}
			
			n = points.size();
		}
	}

	public double getDet2(Point2D.Double a, Point2D.Double b, Point2D.Double c) {
		return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
	}

}
