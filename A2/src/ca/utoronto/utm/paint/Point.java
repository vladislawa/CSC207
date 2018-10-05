package ca.utoronto.utm.paint;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
/**
 * Creates Point
 * 
 * @author professor, csc207 student
 *
 */
public class Point implements Command{
	
	//Declare variables
	private int x, y;
	private Color color;
	private float thickness;
	
	//Constructor
	Point(int x, int y, Color color, float thickness){
		this.x = x; 
		this.y = y; 
		this.color = color;
		this.thickness = thickness;

	}
	
	/** 
	 * @return x value of point
	 */
	public int getX() {
		return x;
	}
	
	/** 
	 * @param x
	 * 				an integer to be set as x value of point
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/** 
	 * @return y value of point
	 */
	public int getY() {
		return y;
	}
	
	/** 
	 * @param y
	 * 				an integer to be set as y value of point
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/** 
	 * @return color of point
	 */
	public Color getColor() {
		return color;
	}
	
	/** 
	 * @return line thickness of point
	 */
	public float getThickness() {
		return thickness;
	}
	
	/** 
	 * Draws point
	 */
	@Override
	public void execute(Graphics2D g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.draw((Shape) this);
	}

}
