package ca.utoronto.utm.paint;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
/**
 * Creates panel for shapes, lines, and tools.
 * 
 * @author csc207 student
 *
 */
// https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html
// https://docs.oracle.com/javase/tutorial/2d/

class ShapeChooserPanel extends JPanel implements ActionListener {
	
	//Declare variables
	private View view; // So we can talk to our parent or other components of the view
	private JButton originalButton; //the initial button clicked
	private boolean fillShape = false; //boolean variable to see if fill shape button is clicked
	private Border defaultFillShapeBorder; //default border of fillShapeButton
	private HashMap<JButton, Border> buttons = new HashMap<JButton, Border>(); //to access the buttons used and there borders
	private ShapeManipulatorStrategy currentStrategy; //the strategy being used
	
	//Constructor
	public ShapeChooserPanel(View view) {	
		
		this.view=view;
		
		//Icons for buttons
		ImageIcon circle = new ImageIcon(getClass().getResource("circle.png"));
		ImageIcon rectangle = new ImageIcon(getClass().getResource("rectangle.png"));
		ImageIcon square = new ImageIcon(getClass().getResource("square.png"));
		ImageIcon squiggle = new ImageIcon(getClass().getResource("squigglyLine.png"));
		ImageIcon polyline = new ImageIcon(getClass().getResource("polyline.png"));
		ImageIcon fillTool = new ImageIcon(getClass().getResource("fillTool.png"));
		ImageIcon eraser = new ImageIcon(getClass().getResource("eraser.png"));
		ImageIcon eyedropper = new ImageIcon(getClass().getResource("eyedropper.png"));
		
		// Create dictionary to hold button names and its icons
		HashMap<String, ImageIcon> iconDict = new HashMap<String, ImageIcon>();
		
		//Add button names and icons to dictionary
		iconDict.put("Circle", circle);
		iconDict.put("Rectangle", rectangle);
		iconDict.put("Square", square);
		iconDict.put("Squiggle", squiggle);
		iconDict.put("Polyline", polyline);
		iconDict.put("Fill Shape", fillTool);
		iconDict.put("Eraser", eraser);
		iconDict.put("Eye Dropper", eyedropper);
		
		//Layout Design
		this.setLayout(new GridLayout(8, 1));
		
		for (Map.Entry<String, ImageIcon> icon : iconDict.entrySet()) {
			
			//Create button and set its name and icon
			JButton shapeButton = new JButton();
			Dimension d = new Dimension(60,60);
			shapeButton.setPreferredSize(d);
			shapeButton.setIcon(icon.getValue());
			shapeButton.setName(icon.getKey());
			shapeButton.setBackground(Color.lightGray);
			this.add(shapeButton);
			buttons.put(shapeButton, shapeButton.getBorder());
			shapeButton.addActionListener(this);
			
			shapeButton.addMouseListener(new java.awt.event.MouseAdapter() {
			    public void mouseEntered(java.awt.event.MouseEvent evt) {
			    		shapeButton.setToolTipText(icon.getKey());
			    }
			});
		}
	}
	
	/**
	 * Controller aspect of this. 
	 * Action Listener for Shape buttons.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		
		if (button.getName() == "Fill Shape") {
			
			if (fillShape == true) {
				
				fillShape = false;
				button.setBorder(defaultFillShapeBorder);
				button.setBackground(Color.lightGray);
				this.view.getPaintPanel().setFillShapeMode(fillShape);
				
			} else {
				
				fillShape = true;
				defaultFillShapeBorder = button.getBorder();
				this.view.getPaintPanel().setFillShapeMode(fillShape);
				button.setBorder(BorderFactory.createLoweredBevelBorder());
				button.setBackground(Color.gray);
			}
			
		} else {
				
			if (button.getName() == "Eye Dropper") {
				System.out.println(button.getName());
				currentStrategy = new EyeDropperStrategy();
				view.getPaintPanel().setStrategy(currentStrategy); 
			} else if (button.getName() == "Circle") {
				System.out.println(button.getName());
				currentStrategy = new CircleStrategy();
				view.getPaintPanel().setStrategy(currentStrategy); 
			} else if (button.getName() == "Rectangle") {
				System.out.println(button.getName());
				currentStrategy = new RectangleStrategy();
				view.getPaintPanel().setStrategy(currentStrategy); 
			} else if (button.getName() == "Square") {
				System.out.println(button.getName());
				currentStrategy = new SquareStrategy();
				view.getPaintPanel().setStrategy(currentStrategy); 
			} else if (button.getName() == "Squiggle") {
				System.out.println(button.getName());
				currentStrategy = new SquiggleStrategy();
				view.getPaintPanel().setStrategy(currentStrategy); 
			} else if (button.getName() == "Polyline") {
				System.out.println(button.getName());
				currentStrategy = new PolylineStrategy();
				view.getPaintPanel().setStrategy(currentStrategy); 
			} else {
				System.out.println(button.getName());
				currentStrategy = new EraserStrategy();
				view.getPaintPanel().setStrategy(currentStrategy); 
			}
			
			if (originalButton == null) {
				button.setBorder(BorderFactory.createLoweredBevelBorder());
				button.setBackground(Color.gray);
				originalButton = button;
			} else {
				originalButton.setBorder(button.getBorder());
				originalButton.setBackground(Color.lightGray);
				button.setBorder(BorderFactory.createLoweredBevelBorder());
				button.setBackground(Color.gray);
				originalButton = button;
			}
		}	
	}
	
	/**
	 * Resets the shapeChooserPanel
	 */
	public void reset() {
		for (Map.Entry<JButton, Border> button : buttons.entrySet()) {
			button.getKey().setBorder(button.getValue());
			button.getKey().setBackground(Color.lightGray);
		}
		fillShape = false;
		originalButton = null;
	}
}//end class
