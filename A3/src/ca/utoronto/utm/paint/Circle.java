package ca.utoronto.utm.paint;

public class Circle extends Shape {
	private Point centre;
	private int radius;
	
	public Circle(){
		this(new Point(0,0), 0);
	}
	public Circle(Point centre, int radius){
		this.centre = centre;
		this.radius = radius;
	}
	public Point getCentre() { return centre; }
	public void setCentre(Point centre) { this.centre = centre; }
	public int getRadius() { return radius; }
	public void setRadius(int radius) { this.radius = radius; }
	
	public String shapeInfo() {
		return this.toString() + "\tcenter:(" + this.getCentre().x + "," + this.getCentre().y + ")\n" + "\tradius:" + this.radius;
	}
}