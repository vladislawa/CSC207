package ca.utoronto.utm.paint;

import java.util.ArrayList;

public class Squiggle extends Shape {
	private ArrayList<Point> points=new ArrayList<Point>();
	
	public Squiggle(){
		
	}
	public void add(Point p){ this.points.add(p); }
	public ArrayList<Point> getPoints(){ return this.points; }
	
	public String shapeInfo() {
		String s = "";
		s += this.toString();
		s += "\tpoints\n";
		for(Point point : this.getPoints()) {
			s += "\t\tpoint:(" + point.x + "," + point.y + ")\n";
		}
		s +="\tend points";
		return s;
	}
}