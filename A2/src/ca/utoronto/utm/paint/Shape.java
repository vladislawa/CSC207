package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
/**
 * Creates Shapes
 * 
 * @author csc207 student
 *
 */
public class Shape {
	
	//Declare variables
	private Object obj;
	private Circle circle;
	private Square square;
	private Rectangle rectangle;
	private Polyline polyline;
	private Eraser eraser;
	private Squiggle squiggle;
	private Color color;
	private float thickness;
	private String shape;
	
	//Constructor
	public Shape(Object obj, String shape){
		this.obj = obj;
		this.shape = shape;
	}

	/** 
	 * @return name of shape
	 */
	public String getShape() {
		return shape;
	}
	
	/** 
	 * @return color of each shape
	 */
	public Color getColor() {
		if (this.shape == "circle") {
			return ((Circle) obj).getColor();
		} else if (this.shape == "rectangle") {
			return ((Rectangle) obj).getColor();
		} else if (this.shape == "square") {
			return ((Square) obj).getColor();
		} else if (this.shape == "polyline") {
			return ((Polyline) obj).getColor();
		} else if (this.shape == "eraser") {
			return ((Eraser) obj).getColor();
		} else if (this.shape == "squiggle"){
			return ((Squiggle) obj).getColor();
		} else {
			return Color.BLACK;
		}
	}
	
	/** 
	 * @return line thickness(stroke) of each shape
	 */
	public float getLineThickness() {
		if (this.shape == "circle") {
			return ((Circle) obj).getThickness();
		} else if (this.shape == "rectangle") {
			return ((Rectangle) obj).getThickness();
		} else if (this.shape == "square") {
			return ((Square) obj).getThickness();
		} else if (this.shape == "polyline") {
			return ((Polyline) obj).getThickness();
		} else if (this.shape == "eraser") {
			return ((Eraser) obj).getThickness();
		} else if (this.shape == "squiggle") {
			return ((Squiggle) obj).getThickness();
		} else {
			return 1;
		}
	}
	
	/** 
	 * @return if each shape is filled
	 */
	public boolean isFilled() {
		if (this.shape == "circle") {
			return ((Circle) obj).isFilled();
		} else if (this.shape == "rectangle") {
			return ((Rectangle) obj).isFilled();
		} else if (this.shape == "square") {
			return ((Square) obj).isFilled();
		} else if (this.shape == "eraser") {
			return ((Eraser) obj).isFilled();
		} else if (this.shape == "polyline") {
			return ((Polyline) obj).isFilled();
		} else if (this.shape == "squiggle") {
			return ((Squiggle) obj).isFilled();
		} else {
			return false;
		}
	}
	
	/** 
	 * @return Calls execute methods for a specified shape
	 */
	public void execute(Graphics2D g) {
		if (this.shape == "circle") {
			((Circle) obj).execute(g);
		} else if (this.shape == "square") {
			((Square) obj).execute(g);
		} else if (this.shape == "rectangle") {
			((Rectangle) obj).execute(g);
		} else if (this.shape == "eraser") {
			((Eraser) obj).execute(g);
		} else if (this.shape == "polyline") {
			((Polyline) obj).execute(g);
		} else if (this.shape == "squiggle") {
			((Squiggle) obj).execute(g);
		} else if (this.shape == "select") {
			((Select) obj).execute(g);
		} else if(this.shape == "emoji"){
			((Emoji) obj).execute(g);
		} else {
			if (((ImageShape) obj).getPasted()) {
				((ImageShape) obj).execute(g);
			} 
		}
	}


}//end Shape class
