package iptat.util;

import java.awt.geom.Point2D;
import java.util.LinkedList;

public class Earcutting {
	private static LinkedList<LinkedList<Point2D.Double>> triangles;
	public static LinkedList<LinkedList<Point2D.Double>> earcutting(Polygon2D polygon)
	{
		LinkedList<Point2D.Double> points = new LinkedList<>(polygon.getPointsList());
		triangles = new LinkedList<LinkedList<Point2D.Double>>();
		getTriangles(points);
		return triangles;
	}
	private static void getTriangles(LinkedList<Point2D.Double> points)
	{
		int n = points.size();
		boolean semafor = true;//true=e principal
		if (n > 2)
		{
			Point2D.Double Pa = new Point2D.Double(points.get(n-3).getX(),points.get(n-3).getY());
			Point2D.Double Pb = new Point2D.Double(points.get(n-2).getX(),points.get(n-2).getY());
			Point2D.Double Pc  = new Point2D.Double(points.get(n-1).getX(),points.get(n-1).getY());
			double delta = determinant(Pa,Pb,Pc);
			if (delta > 0)//Pb e varf convex
			{
				for (int i = 0; i<n-3 && semafor==true;i++)
				{
					if (delta == Math.abs(determinant(Pa,points.get(i),Pb)) + Math.abs(determinant(Pb,points.get(i),Pc))+ Math.abs(determinant(Pc,points.get(i),Pa)))
					{
						//apartine triunghiului
						//Pb e varf neprincipal
						semafor = false;
					}
				}
				if (semafor == true)
				{
					points.remove(n-2);//eliminam "urechea"
					LinkedList<Point2D.Double> triangle = new LinkedList<Point2D.Double>();
					triangle.add(Pa);
					triangle.add(Pb);
					triangle.add(Pc);
					triangles.add(triangle);
				}
				else
				{
					points.remove(n-1);
					points.add(0,Pc);
				}
				getTriangles(points);
			}
			else
			{
				points.remove(n-1);
				points.add(0,Pc);
				getTriangles(points);
			}
		}
	
	}
	public static double determinant(Point2D.Double a,Point2D.Double b,Point2D.Double c)
	{
		return (a.x*b.y+a.y*c.x+b.x*c.y-c.x*b.y-a.x*c.y-b.x*a.y);
	}


}
