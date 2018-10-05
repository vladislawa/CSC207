package ca.utoronto.utm.paint;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
/**
 * Creates shape circle.
 * 
 * @author professor, csc207 student
 *
 */
public class Circle implements Command{
	
	//Declare variables
	private Point centre;
	private int radius;
	private Color color;
	private float thickness;
	private boolean fillShape;
	
	//Constructor
	public Circle(Point centre, int radius, Color color, float thickness, boolean fillShape){
		this.centre = centre;
		this.radius = radius;
		this.color = color;
		this.thickness = thickness;
		this.fillShape = fillShape;

	}
	
	/** 
	 * @return centre point of circle
	 */
	public Point getCentre() {
		return centre;
	}
	
	/** 
	 * @param centre
	 * 				a Point thats to be set as centre of circle
	 */
	public void setCentre(Point centre) {
		this.centre = centre;
	}
	
	/** 
	 * @return radius of circle
	 */
	public int getRadius() {
		return radius;
	}
	
	/** 
	 * @param radius
	 * 				an integer to be set as radius of circle
	 */
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	/** 
	 * @return color of circle
	 */
	public Color getColor() {
		return color;
	}
	
	/** 
	 * @return line thickness of circle
	 */
	public float getThickness() {
		return thickness;
	}
	
	/** 
	 * @return if circle is filled (a boolean)
	 */
	public boolean isFilled() {
		return fillShape;
	}

	/** 
	 * Draws a filled or outlined circle depending on the mode selected
	 */
	@Override
	public void execute(Graphics2D g2d) {
		if (this.isFilled()) {
			g2d.fillOval( centre.getX() - (radius/2), centre.getY() - (radius/2), radius, radius);
		} else {
		g2d.drawOval( centre.getX() - (radius/2), centre.getY() - (radius/2), radius, radius);	
		}
	}
}

//end class
