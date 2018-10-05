package ca.utoronto.utm.paint;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import ca.utoronto.utm.paint.Point;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.*; 
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
/**
 * Creates panel for user to draw on.
 * 
 * @author professor, csc207 student
 *
 */
// https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html
// https://docs.oracle.com/javase/tutorial/2d/

class PaintPanel extends JPanel implements Observer, MouseMotionListener, MouseListener  {
	
	//Model
	private PaintModel model; // slight departure from MVC, because of the way painting works
	
	//View
	private View view; // So we can talk to our parent or other components of the view
	
	//Declare variables
	private Circle circle; //the circle we are building.
	private Rectangle rectangle; //the rectangle we are building.
	private Select select; //the select we are building.
	private Square square; //the square we are building.
	private Polyline polyline; //the polyline we are building.
	private ImageShape imageShape; //the image we are building.
	private BufferedImage image; //the image to be used when creating a New Panel.
	private Image eimage; //the image to be used when print emoji.
	private Emoji emoji; //the emoji we are building
	private Eraser currentEraser; //the current eraser being used in panel.
	private Squiggle currentLine; //the current line squiggle being used in panel.
	private List<Eraser> erasers; // a list of all erasers.
	private List<Squiggle> lines; //a list of all squiggle lines.
	private Color color; //the color to be used for drawing.
	private float lineThickness; //the line thickness to be used for drawing.
	private boolean fillShape; //this is for either the shapes are in filled or outlined mode.
	private int cx; //this is for Eye Dropper to know the x-position of color chosen.
	private int cy; //this is for Eye Dropper to know the y-position of color chosen.
	private int originPointX; //this is used for squares and rectangles to be drawn in any direction.
	private int originPointY; //this is used for squares and rectangles to be drawn in any direction.
	private boolean hasDrawn; //this is to see if the current panel's been drawn on.
    private int startInd, endInd; //these are start and end points of the rectangle being created.
    private boolean isSelected; //this is to see if an area is selected in paint panel.
    private ShapeManipulatorStrategy strategy; //this is used to know which shape is being created.
    
	//Constructor
	public PaintPanel(PaintModel model, View view){
		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(300,300));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		this.cx = 0;
		this.cy = 0;
		this.originPointX = 0;
		this.originPointY = 0;
		this.color = Color.black;
		this.lineThickness = 1;
		this.erasers = new ArrayList<Eraser>();
		this.lines = new ArrayList<Squiggle>();
		this.fillShape = false;
		this.hasDrawn = false;
		this.isSelected = false;
		
		this.model = model;
		this.model.addObserver(this);
		
		this.view = view;
	}
	
	/**
	 *  View aspect of this
	 */
	public void paintComponent(Graphics g) { 
		// Use g to draw on the JPanel, lookup java.awt.Graphics in
		// the javadoc to see more of what this can do for you!!
		
        super.paintComponent(g); //paint background
        
        Graphics2D g2d = (Graphics2D) g; // lets use the advanced api
		// Origin is at the top left of the window 50 over, 75 down
        
		g2d.setColor(Color.black);
		g2d.setStroke(new BasicStroke(1));
		
		g2d.drawImage(image, 0, 0, null);
		
		repaint();	
		
		// Draw Shapes
		ArrayList<Shape> shapes = this.model.getDrawingList();

		for (Shape shape: shapes) { 
			g2d.setColor(shape.getColor());
			g2d.setStroke(new BasicStroke(shape.getLineThickness()));
			shape.execute(g2d);
		}
	
		g2d.dispose();
	}

	@Override
	public void update(Observable o, Object arg) {
		// Not exactly how MVC works, but similar.
		this.repaint(); // Schedule a call to paintComponent
	}
	
	/**
	 *  Sets line thickness to the one chosen by user
	 */
	public void setThicknessMode(String lineThickness){
		this.lineThickness = Float.parseFloat(lineThickness);
	}
	
	/**
	 *  Sets fill mode to the one chosen by user
	 */
	public void setFillShapeMode(boolean FillShape){
		this.fillShape = FillShape;
	}
	
	/**
	 *  Sets colour mode to the one chosen by user
	 */
	public void setPaintMode(String mode) {
		if (mode == "Black") {
			this.color = Color.black;
		} else if(mode == "Blue") {
			this.color = Color.blue;
		} else if(mode == "Cyan") {
			this.color = Color.cyan;
		} else if(mode == "Dark Gray") {
			this.color = Color.darkGray;
		} else if(mode == "Gray") {
			this.color = Color.gray;
		} else if(mode == "Green") {
			this.color = Color.green;
		} else if(mode == "Magenta") {
			this.color = Color.magenta;
		} else if(mode == "Orange") {
			this.color = Color.orange;
		} else if(mode == "Pink") {
			this.color = Color.pink;
		} else if(mode == "Red") {
			this.color = Color.red;
		} else if(mode == "White") {
			this.color = Color.white;
		} else if(mode == "Yellow") {
			this.color = Color.yellow;
		}
	}
	
	// MouseMotionListener below
	@Override
	public void mouseMoved(MouseEvent e) {
		
	}
	
	// MouseMotionListener below
	@Override
	public void mouseDragged(MouseEvent e) {
		if (this.strategy != null){
			this.strategy.mouseDragged(e, this);
		}
	}

	// MouseListener below
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	// MouseMotionListener below
	@Override
	public void mousePressed(MouseEvent e) {
		if (this.isSelected){
			this.model.getDrawingList().remove(this.model.getDrawingList().size()-1);
			this.isSelected = false;
		}
		if (this.strategy != null){
			this.strategy.mousePressed(e, this);
		}
	}
	
	// MouseMotionListener below
	@Override
	public void mouseReleased(MouseEvent e) {
		if (this.strategy != null){
			this.strategy.mouseReleased(e, this);
		}
	}
	
	// MouseMotionListener below
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	// MouseMotionListener below
	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
	/**
	 *  Method to pick color from object and print color.
	 */	
	public void eyeDropper() {
		BufferedImage image = new BufferedImage(getWidth(),getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = image.createGraphics();
		paint(g2d);
		int pcolor = image.getRGB(cx,cy);
		Color c = new Color(pcolor, true);
		color = c;
		System.out.println("color:  " + color);
	}
	
	/**
	 *  Method to copy an image
	 */	
	public void copy() {
		if(this.isSelected) {
			BufferedImage bufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics2D g2 = bufferedImage.createGraphics();
			paint(g2);
			
			int x = this.select.getCornerTop().getX();
			int y = this.select.getCornerTop().getY();
			int w = this.select.getWidth();
			int h = this.select.getHeight();
			
			this.model.getDrawingList().remove(this.model.getDrawingList().size() - 1);
			this.isSelected = false;
			ImageShape image = new ImageShape(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB, bufferedImage, this, false, x, y, w, h);
			
			model.addImage(image);
			this.imageShape = image;
			System.out.println("Image copied.");
		}
		else {
			System.out.println("Nothing was selected.");
		}
	}
	
	/**
	 *  Method to cut an image
	 */	
	public void cut() {
		if(this.isSelected) {
			BufferedImage bufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics2D g2 = bufferedImage.createGraphics();
			paint(g2);
			
			int x = this.select.getCornerTop().getX();
			int y = this.select.getCornerTop().getY();
			int w = this.select.getWidth();
			int h = this.select.getHeight();
			
			this.model.getDrawingList().remove(this.model.getDrawingList().size() - 1);
			this.isSelected = false;
			ImageShape image = new ImageShape(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB, bufferedImage, this, false, x, y, w, h);
			
			g2.drawImage(bufferedImage, 0, 0, null);
			g2.setColor(Color.WHITE);
			Point corner = new Point(x, y, Color.white, 3);
			Rectangle r = new Rectangle(corner, h, w, Color.WHITE, 3, true);
			Shape s = new Shape(r, "rectangle");
			ArrayList<Shape> list = this.model.getDrawingList();
			list.add(s);
			repaint();
			 
			model.addImage(image);
			this.imageShape = image;
			System.out.println("Image cut.");
		}
		else {
			System.out.println("Nothing was selected.");
		}
	}
	
	/**
	 *  Method to delete an image
	 */	
	public void delete() {
		if(this.isSelected) {
			BufferedImage bufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics2D g2 = bufferedImage.createGraphics();
			paint(g2);
			
			int x = this.select.getCornerTop().getX();
			int y = this.select.getCornerTop().getY();
			int w = this.select.getWidth();
			int h = this.select.getHeight();
		
			g2.drawImage(bufferedImage, 0, 0, null);
			g2.setColor(Color.WHITE);
			Point corner = new Point(x, y, Color.white, 3);
			Rectangle r = new Rectangle(corner, h, w, Color.WHITE, 3, true);
			this.model.getDrawingList().remove(this.model.getDrawingList().size() - 1);
			this.isSelected = false;
			Shape s = new Shape(r, "rectangle");
			ArrayList<Shape> list = this.model.getDrawingList();
			list.add(s);
			repaint();
	
			System.out.println("Image deleted.");
		} else {
			System.out.println("Nothing was selected.");
		}
	}
	
	/**
	 *  Method to paste an image
	 */	
	public void paste() {
		imageShape.setPasted(true);
	}
	
	/**
	 *  Method to open another file/panel
	 */	
	public void open() {
		newPanel();
		JFileChooser open = new JFileChooser();
		open.setDialogTitle("Open a File");
		if (open.showOpenDialog(PaintPanel.this) == JFileChooser.APPROVE_OPTION) {
			open.addChoosableFileFilter(new FileNameExtensionFilter("PNG Files", ".png"));
			File file = open.getSelectedFile(); 
			System.out.println("Selected file: " + file.getAbsolutePath());
			try {                
				image = ImageIO.read(new File(file.getAbsolutePath())); 
		    } 
		    catch (IOException e) {} 	
		}
	}
	
	/**
	 *  Selects an area of a canvas
	 */
	public void select() {
		strategy = new SelectStrategy();
		view.resetShapeChooserPanel();
	}
	
	/**
	 *  Method to create new panel
	 */	
	public void newPanel() {
		BufferedImage image = new BufferedImage(getWidth(),getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = image.createGraphics();
		paint(g2);
		g2.setBackground(Color.white);
		color = Color.black;
		lineThickness = 1;
		fillShape = false;
		model.reset();
		view.resetView();
	    repaint();
	}
	
	/**
	 *  Method to save panel
	 */	
	public void save() {
		BufferedImage image = new BufferedImage(getWidth(),getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = image.createGraphics();
		paint(g2);
		JFileChooser save = new JFileChooser();
		save.setDialogTitle("Save a File");
		
		if (save.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			try {
				ImageIO.write(image, "PNG", save.getSelectedFile());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 *  Method to see if user has drawn on panel
	 *  
	 *  @return true if user has drawn, false otherwise
	 */	
	public boolean hasDrawn() {
		return this.hasDrawn;
	}
	
	/**
	 *  Returns circle 
	 *  
	 *  @return an instance of a class Circle 
	 */
	public Circle getCircle() {
		return this.circle;
	}
	
	/**
	 *  Sets the circle 
	 *  	 
	 *  @param an instance of a class Circle 
	 */
	public void setCircle(Circle circle) {
		this.circle = circle;
	}
	
	/**
	 *  Returns square 
	 *  
	 *  @return an instance of a class Square 
	 */
	public Square getSquare() {
		return this.square;
	}
	
	/**
	 *  Sets the square 
	 *  
	 *  @param an instance of a class Square 
	 */
	public void setSquare(Square square) {
		this.square = square;
	}
	
	/**
	 *  Returns rectangle 
	 *  
	 *  @return an instance of a class Rectangle 
	 */
	public Rectangle getRectangle() {
		return this.rectangle;
	}
	
	/**
	 *  Sets the rectangle 
	 *  
	 *  @param an instance of a class Rectangle 
	 */
	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}
	
	/**
	 *  Returns polyline 
	 *  
	 *  @return an instance of a class Polyline 
	 */
	public Polyline getPolyline() {
		return this.polyline;
	}
	
	/**
	 *  Sets the polyline 
	 *  
	 *  @param an instance of a class Polyline
	 */
	public void setPolyline(Polyline polyline) {
		this.polyline = polyline;
	}
	
	/**
	 *  Returns squiggle 
	 *  
	 *  @return an instance of a class Squiggle 
	 */
	public Squiggle getSquiggle() {
		return this.currentLine;
	}
	
	/**
	 *  Sets the squiggle 
	 *  
	 *  @param an instance of a class Squiggle
	 */
	public void setSquiggle(Squiggle squiggle) {
		this.currentLine = squiggle;
	}
	
	/**
	 *  Returns select 
	 *  
	 *  @return an instance of a class Select 
	 */
	public Select getSelect() {
		return this.select;
	}
	
	/**
	 *  Sets the select 
	 *  
	 *  @param an instance of a class Select
	 */
	public void setSelect(Select select) {
		this.select = select;
	}
	
	/**
	 *  Returns list of squiggles
	 *  
	 *  @return a list of instances of a class Squiggle
	 */
	public List<Squiggle> getLines() {
		return this.lines;
	}
	
	/**
	 *  Returns eraser
	 *  
	 *  @return an instance of a class Eraser
	 */
	public Eraser getEraser() {
		return this.currentEraser;
	}
	
	/**
	 *  Sets eraser
	 *  
	 *  @param  an instance of a class Eraser
	 */
	public void setEraser(Eraser eraser) {
		this.currentEraser = eraser;
	}
	
	/**
	 *  Returns a list of erasers
	 *  
	 *  @return a list of instances of a class Eraser
	 */
	public List<Eraser> getErasers() {
		return this.erasers;
	}
	
	/**
	 *  Returns a colour
	 *  
	 *  @return an instances of a class Color
	 */
	public Color getColor() {
		return this.color;
	}
	
	/**
	 *  Returns true if fill shape mode is selected, false otherwise
	 *  
	 *  @return boolean
	 */
	public boolean shapeIsFilled() {
		return this.fillShape;
	}
	
	/**
	 *  Returns a line thickness
	 *  
	 *  @return integer
	 */
	public float getLineThickness() {
		return this.lineThickness;
	}
	
	/**
	 *  Returns an origin point X
	 *  
	 *  @return integer
	 */
	public int getOriginPointX() {
		return this.originPointX;
	}
	
	/**
	 *  Returns an origin point Y
	 *  
	 *  @return integer
	 */
	public int getOriginPointY() {
		return this.originPointY;
	}
	
	/**
	 *  Sets an origin point X
	 *  
	 *  @param integer
	 */
	public void setOriginPointX(int originPointX) {
		this.originPointX = originPointX;
	}
	
	/**
	 *  Sets an origin point Y
	 *  
	 *  @param integer
	 */
	public void setOriginPointY(int originPointY) {
		this.originPointY = originPointY;
	}
	
	/**
	 *  Returns a starting index in the list of shapes when user starts drawing a new shape
	 *  
	 *  @return integer
	 */
	public int getStartInd() {
		return this.startInd;
	}
	
	/**
	 *  Sets a starting index 
	 *  
	 *  @param integer
	 */
	public void setStartInd(int startInd) {
		this.startInd = startInd;
	}
	
	/**
	 *  Returns an ending index in the list of shapes when user stops drawing a new shape
	 *  
	 *  @return integer
	 */
	public int getEndInd() {
		return this.endInd;
	}

	/**
	 *  Sets an ending index 
	 *  
	 *  @param integer
	 */
	public void setEndInd(int endInd) {
		this.endInd = endInd;
	}
	
	/**
	 *  Returns a coordinate X
	 *  
	 *  @return integer
	 */
	public int getCX() {
		return this.cx;
	}
	
	/**
	 *  Sets a coordinate X
	 *  
	 *  @param integer
	 */
	public void setCX(int cx) {
		this.cx = cx;
	}
	
	/**
	 *  Returns a coordinate Y
	 *  
	 *  @return integer
	 */
	public int getCY() {
		return this.cy;
	}
	
	/**
	 *  Sets a coordinate Y
	 *  
	 *  @param integer
	 */
	public void setCY(int cy) {
		this.cy = cy;
	}
	
	/**
	 *  Returns true if user has drawn something on canvas, false otherwise
	 *  
	 *  @return boolean
	 */
	public void setDrawn(boolean isDrawn) {
		this.hasDrawn = isDrawn;
	}
	
	/**
	 *  Returns true if user selected an area, false otherwise
	 *  
	 *  @return boolean
	 */
	public boolean getIsSelected() {
		return this.isSelected;
	}
	
	/**
	 *  Sets isSelected to boolean
	 *  
	 *  @return boolean
	 */
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	/**
	 *  Returns a paint model
	 *  
	 *  @return instance of a class PaintModel
	 */
	public PaintModel getModel() {
		return this.model;
	}
	
	/**
	 *  Returns a shape strategy selected by user
	 *  
	 *  @return instance of a class ShapeManipulatorStrategy
	 */
	public ShapeManipulatorStrategy getStrategy() {
		return this.strategy; 
	}
	
	/**
	 *  Sets a shape strategy 
	 *  
	 *  @param instance of a class ShapeManipulatorStrategy
	 */
	public void setStrategy(ShapeManipulatorStrategy strategy) {
		this.strategy = strategy;
	}
	
	/**
	 *  Returns emoji 
	 *  
	 *  @return instance of a class Emoji
	 */
	public Emoji getEmoji() {
		 return this.emoji;
	}
	
	/**
	 *  Sets emoji
	 *  
	 *  @param instance of a class Emoji
	 */
	public void setEmoji(Emoji emoji) {
		this.emoji = emoji;
	}
	
	/**
	 *  Returns image of emoji
	 *  
	 *  @return instance of a class Image
	 */
	public Image getEmojiImage() {
		 return this.eimage;
	}
	
	/**
	 *  Sets image of emoji
	 *  
	 *  @param instance of a class Image
	 */
	public void setEmojiImage(Image eimage) {
		this.eimage = eimage;
	}
}
