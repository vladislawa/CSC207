package ca.utoronto.utm.paint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.border.Border;
/**
 * Models a Paint Program with different types of shapes
 * 
 * @author professor, csc207 student
 *
 */
public class PaintModel extends Observable {
	
	//Declare variables
	private ArrayList<Shape> drawingList = new ArrayList<Shape>(); //list of shapes that have been drawn 
	private ArrayList<Shape> undoList = new ArrayList<Shape>(); //list of shapes for Undo command
	private ArrayList<Shape> redoList = new ArrayList<Shape>(); //list of shapes for Redo command
	
	/**
	 * Adds Point to drawing list stack
	 * 
	 * @param instance of a class Point
	 */
	public void addPoint(Point point){
		redoList = new ArrayList<Shape>();
		Shape s = new Shape(point, "point");
		this.drawingList.add(s);
		
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Adds Circle to drawing list stack
	 * 
	 *  @param instance of a class Circle
	 */
	public void addCircle(Circle circle){
		redoList = new ArrayList<Shape>();
		Shape s = new Shape(circle, "circle");
		this.drawingList.add(s);
		
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Adds Rectangle to drawing list stack
	 * 
	 *  @param instance of a class Rectangle
	 */
	public void addRectangle(Rectangle rect){
		redoList = new ArrayList<Shape>();
		Shape s = new Shape(rect, "rectangle");
		this.drawingList.add(s);
		
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Adds Square to drawing list stack
	 * 
	 * @param instance of a class Square
	 */
	public void addSquare(Square square){
		redoList = new ArrayList<Shape>();
		Shape s = new Shape(square, "square");
		this.drawingList.add(s);
		
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Adds Eraser to drawing list stack
	 * 
	 * @param instance of a class Eraser
	 */
	public void addEraser(Eraser eraser){
		redoList = new ArrayList<Shape>();
		Shape s = new Shape(eraser, "eraser");
		this.drawingList.add(s);
		
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Adds Squiggle to drawing list stack
	 * 
	 * @param instance of a class Squiggle
	 */
	public void addSquiggle(Squiggle squiggle){
		redoList = new ArrayList<Shape>();
		Shape s = new Shape(squiggle, "squiggle");
		this.drawingList.add(s);
		
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Adds Polyline to drawing list stack
	 * 
	 * @param instance of a class Polyline
	 */
	public void addPolyline(Polyline poly){
		redoList = new ArrayList<Shape>();
		Shape s = new Shape(poly, "polyline");
		this.drawingList.add(s);
		
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Adds Select to drawing list stack
	 * 
	 * @param instance of a class Select
	 */
	public void addSelect(Select select) {
		redoList = new ArrayList<Shape>();
		Shape s = new Shape(select, "select");
		this.drawingList.add(s);
		
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Adds Image to drawing list stack
	 * 
	 * @param instance of a class BufferedImage
	 */
	public void addImage(BufferedImage image) {
		redoList = new ArrayList<Shape>();
		Shape s = new Shape(image , "image");
		this.drawingList.add(s);
		
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Adds Emoji to drawing list stack
	 * 
	 *  @param instance of a class Emoji
	 */
	public void addEmoji(Emoji emoji){
		redoList = new ArrayList<Shape>();
		Shape s = new Shape(emoji, "emoji");
		this.drawingList.add(s);
		
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Returns list of all shape that have been drawn on canvas
	 * 
	 * @return arraylist of drawings
	 */
	public ArrayList<Shape> getDrawingList(){
		return drawingList;
	}
	
	/**
	 * Sets the drawing list
	 * 
	 * @param list of shapes
	 */
	public void setDrawingList(ArrayList<Shape> drawList){
		this.drawingList = drawList;
	}
	
	/**
	 * Performs the undo option on the stack
	 */
	public void undo(Boolean isSelected) {
		if (!drawingList.isEmpty() && !isSelected){
			undoList.add(drawingList.get(drawingList.size()-1));
			redoList.add(drawingList.get(drawingList.size()-1));
			drawingList.remove(drawingList.size()-1);
		}
	}
	
	/**
	 * Performs the redo option on the stack
	 */
	public void redo() {
		if (!redoList.isEmpty()) {
			drawingList.add(redoList.get(redoList.size()-1));
			redoList.remove(redoList.size()-1);
		}
	}

	/** 
	 * Reset all arraylists of shapes to empty
	 */
	public void reset() {
		drawingList = new ArrayList<Shape>();
	}
}//end class
