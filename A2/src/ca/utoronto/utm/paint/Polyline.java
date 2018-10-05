package ca.utoronto.utm.paint;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * Creates line polyline.
 * 
 * @author professor, csc207 student 
 *
 */
public class Polyline implements Command {
	
	//Declare variables
	private Point start_point;
	private Point end_point;
	private Color color;
	private float thickness;
	
	//Constructor
	public Polyline(Color color, float thickness){
		this.start_point = new Point(0, 0, color, thickness);
		this.end_point = new Point(0, 0, color, thickness);
		this.color = color;
		this.thickness = thickness;
	}
	
	/** 
	 * @return start
	 */
		public Point getStartpoint() {
			return this.start_point;
	}
	
	/** 
	 * @return end point
	 */
	public Point getEndpoint() {
		return this.end_point;
	}
	
	/** 
	 * sets start point values
	 */
	public void setStartpoint(Point start_point) {
		this.start_point.setX(start_point.getX());
		this.start_point.setY(start_point.getY());
	}
	
	/** 
	 * sets end point values
	 */
	public void setEndpoint(Point end_point) {
		this.end_point.setX(end_point.getX());
		this.end_point.setY(end_point.getY());
	}
	
	/** 
	 * update end point values
	 */
	public void updateValues(int x, int y) {
		this.setEndpoint(new Point(x, y, color, thickness));
	};

	/** 
	 * @return color of polyline
	 */
	public Color getColor() {
		return color;
	}
	
	/** 
	 * @return line thickness of polyline
	 */
	public float getThickness() {
		return thickness;
	}

	/** 
	 * @return if circle is filled (a boolean)
	 */
	public boolean isFilled() {
		return false;
	}

	/** 
	 * Draws polyline
	 */
	@Override 
	public void execute(Graphics2D g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawLine(start_point.getX(), start_point.getY(), end_point.getX(), end_point.getY());
	}
}//end class