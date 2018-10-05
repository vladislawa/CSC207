package ca.utoronto.utm.paint;
import java.awt.Color;
import java.awt.Graphics2D;
/**
 * Creates shape rectangle.
 * 
 * @author professor, csc207 student
 *
 */
public class Rectangle implements Command {
	//Declare variables
	private Point cornertop;
	private int height;
	private int width;
	private Color color;
	private float thickness;
	private boolean fillShape;
	
	//Constructor
	public Rectangle(Point cornertop, int height, int width, Color color, float thickness, boolean fillShape) {
		this.cornertop = cornertop;
		this.height = height;
		this.width = width;
		this.color = color;
		this.thickness = thickness;
		this.fillShape = fillShape;
	}
	
	/** 
	 * @return corner top point of rectangle
	 */
	public Point getCornerTop() {
		return cornertop;
	}
	
	/** 
	 * @param cornertop
	 * 				a Point thats to be set as top corner point of rectangle
	 */
	public void setCornerTop(Point cornertop) {
		this.cornertop = cornertop;
	}
	
	/** 
	 * @return height of rectangle
	 */
	public int getHeight() {
		return height;
	}
	
	/** 
	 * @return width of rectangle
	 */
	public int getWidth() {
		return width;
	}
	
	/** 
	 * @param height
	 * 				an integer to be set as height of rectangle
	 */
	public void setHeight (int height) {
		this.height = height;
	}
	
	/** 
	 * @param width
	 * 				an integer to be set as width of rectangle
	 */
	public void setWidth (int width) {
		this.width = width;
	}
	
	/** 
	 * @return color of rectangle
	 */
	public Color getColor() {
		return color;
	}
	
	/** 
	 * @return line thickness of rectangle
	 */
	public float getThickness() {
		return thickness;
	}
	
	/** 
	 * @return if rectangle is filled (a boolean)
	 */
	public boolean isFilled() {
		return fillShape;
	}

	/** 
	 * Draws a filled or outlined rectangle depending on the mode selected
	 */
	@Override
	public void execute(Graphics2D g2d) {
		if (this.isFilled()) {
			g2d.fillRect( cornertop.getX(), cornertop.getY(), width, height);
		} else {
			g2d.drawRect( cornertop.getX(), cornertop.getY(), width, height);
		}
	}
}//end class
