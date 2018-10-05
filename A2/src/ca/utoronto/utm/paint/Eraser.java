package ca.utoronto.utm.paint;
import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * Creates Eraser
 * 
 * @author csc207 student
 *
 */
public class Eraser implements Command{
	//Declare variables
	private ArrayList<Integer> list_x;
	private ArrayList<Integer> list_y;
	private Color colour;
	private float eraserThickness;
	
	//Constructor
	public Eraser (Color colour, float eraserThickness){
		this.list_x = new ArrayList<Integer>();
		this.list_y = new ArrayList<Integer>();
		this.colour = colour;
		this.eraserThickness = eraserThickness;
	}
	
	/** 
  	 * Add point to array list
  	 */
	public void addPoint(int x, int y) {
		list_x.add(x);
		list_y.add(y);
	}
	
	/** 
	 * @return color of eraser
	 */
	public Color getColor() {
		return this.colour;
	}
	
	/** 
	 * @param colour
	 * 				a Color to be set as color of eraser
	 */
	public void setColor(Color colour) {
		this.colour = colour;
	}
	
	/** 
	 * @return line thickness of eraser
	 */
	public float getThickness() { 
		return this.eraserThickness;
	}
	
	/** 
	 * @return if circle is filled (a boolean)
	 */
	public boolean isFilled() {
		return false;
	}

	/** 
	 * Erases the drawings on canvas
	 */
	@Override
	public void execute(Graphics2D g) {
		for(int i = 0; i < list_x.size()-1; i++) {
			g.drawLine((int)list_x.get(i), (int)list_y.get(i), (int)list_x.get(i+1), (int)list_y.get(i+1));
		}
	}
}//end class
