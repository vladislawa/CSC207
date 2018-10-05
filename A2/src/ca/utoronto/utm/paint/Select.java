package ca.utoronto.utm.paint;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
/**
 * Creates shape select.
 * 
 * @author csc207 student
 *
 */
public class Select implements Command {
	//Declare variables
	private Point cornertop;
	private int height;
	private int width;
	
	//Constructor
	public Select(Point cornertop, int height, int width) {
		this.cornertop = cornertop;
		this.height = height;
		this.width = width;
	}
	
	/** 
	 * @return corner top point of select
	 */
	public Point getCornerTop() {
		return cornertop;
	}
	
	/** 
	 * @param cornertop
	 * 				a Point thats to be set as top corner point of select
	 */
	public void setCornerTop(Point cornertop) {
		this.cornertop = cornertop;
	}
	
	/** 
	 * @return height of select
	 */
	public int getHeight() {
		return height;
	}
	
	/** 
	 * @return width of select
	 */
	public int getWidth() {
		return width;
	}
	
	/** 
	 * @param height
	 * 				an integer to be set as height of select
	 */
	public void setHeight (int height) {
		this.height = height;
	}
	
	/** 
	 * @param width
	 * 				an integer to be set as width of select
	 */
	public void setWidth (int width) {
		this.width = width;
	}
	
	/** 
	 * Selects the area with a dotted rectangle
	 */
	@Override
	public void execute(Graphics2D g2d) {
		g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
		g2d.drawRect( cornertop.getX(), cornertop.getY(), width, height);
	}
}//end class
