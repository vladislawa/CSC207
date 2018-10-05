package ca.utoronto.utm.paint;
import java.awt.Color;
import java.awt.Graphics2D;
/**
 * Creates shape square.
 * 
 * @author csc207 student
 *
 */
public class Square implements Command{
	
	//Declare variables
	private Point top;
	private int width;
	private Color color;
	private float thickness;
	private boolean fillShape;
	
	//Constructor
	public Square(Point top, int width, Color color, float thickness, boolean fillShape){
		this.top = top;
		this.width = width;
		this.color = color;
		this.thickness = thickness;
		this.fillShape = fillShape;
	}
	
	/** 
	 * @return corner top point of square
	 */
	public Point getTop() {
		return this.top;
	}
	
	/** 
	 * @param cornertop
	 * 				a Point thats to be set as top corner point of square
	 */
	public void setCornerTop(Point cornertop) {
		this.top = cornertop;
	}
	
	/** 
	 * @return width of square
	 */
	public int getWidth() {
		return width;
	}
	
	/** 
	 * @param width
	 * 				an integer to be set as width of square
	 */
	public void setWidth (int width) {
		this.width = width;
	}
	
	/** 
	 * @return color of square
	 */
	public Color getColor() {
		return color;
	}
	
	/** 
	 * @return line thickness of square
	 */
	public float getThickness() {
		return thickness;
	}
	
	/** 
	 * @return if square is filled (a boolean)
	 */
	public boolean isFilled() {
		return fillShape;
	}

	/** 
	 * Draws a filled or an outlined square depending on the mode selected
	 */
	@Override
	public void execute(Graphics2D g2d) {
		if (this.isFilled()) {
			g2d.fillRect(top.getX(),top.getY(), width, width);
		} else {
			g2d.drawRect(top.getX(),top.getY(), width, width);
		}
	}
}//end class
