package ca.utoronto.utm.paint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.*;
import java.awt.Color;
/**
 * Creates shape/line squiggle
 * 
 * @author professor, csc207 student
 *
 */
public class Squiggle implements Command{
	//Declare variables
	private ArrayList<Integer> List_of_x;
	private ArrayList<Integer> List_of_y; 
	private Color color;
	private float thickness;
 
   //Constructor
   public Squiggle(Color color, float thickness) {
      this.List_of_x = new ArrayList<Integer>();
      this.List_of_y = new ArrayList<Integer>();
      this.color = color;
      this.thickness = thickness;
   }
 
   /** 
  	 * Add point to array list
  	 */
   public void addPoint(int x, int y) {
      List_of_x.add(x);
      List_of_y.add(y);
   }
   
   /** 
	 * @return color of squiggle
	 */
   public Color getColor() {
		return color;
	}
   
   /** 
	 * @param colour
	 * 				a Color to be set as color of squiggle
	 */
   public void setColor(Color color) {
	   this.color = color;
   }
   
   /** 
	 * @return line thickness of squiggle
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
	 * Draws a squiggle
	 */
	@Override
	public void execute(Graphics2D g) {
		Graphics2D g2d = (Graphics2D) g;
	      for (int i = 0; i < List_of_x.size() - 1; ++i) {
	         g2d.drawLine((int)List_of_x.get(i), (int)List_of_y.get(i), (int)List_of_x.get(i + 1),
	               (int)List_of_y.get(i + 1));
	      }
	}
}
//end class
