package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.Timer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
/**
 * Parse a file in Version 1.0 PaintSaveFile format. An instance of this class
 * understands the paint save file format, storing information about
 * its effort to parse a file. After a successful parse, an instance
 * will have an ArrayList of PaintCommand suitable for rendering.
 * If there is an error in the parse, the instance stores information
 * about the error. For more on the format of Version 1.0 of the paint 
 * save file format, see the associated documentation.
 * 
 * @author 
 *
 */
public class PaintSaveFileParser {
	private int lineNumber = 0; // the current line being parsed
	private String errorMessage =""; // error encountered during parse
	private ArrayList<PaintCommand> commands; // created as a result of the parse
	
	/**
	 * Below are Patterns used in parsing 
	 */
	private Pattern pFileStart=Pattern.compile("^PaintSaveFileVersion1.0$");
	private Pattern pFileEnd=Pattern.compile("^EndPaintSaveFile$");

	// Circle Patterns 
	private Pattern pCircleStart=Pattern.compile("^Circle$");
	private Pattern pCircleEnd=Pattern.compile("^EndCircle$");
	private Pattern pColor = Pattern.compile("^color:(\\d{1,3}),(\\d{1,3}),(\\d{1,3})$");
	private Pattern pFilled = Pattern.compile("^filled:(true|false)$");
	private Pattern pCenter = Pattern.compile("^center:[(](\\d{1,}),(\\d{1,})[)]$");
	private Pattern pRadius = Pattern.compile("^radius:(\\d{1,})$");
		
	// Squiggle Patterns 
	private Pattern pSquiggleStart=Pattern.compile("^Squiggle$");
	private Pattern pSquiggleEnd=Pattern.compile("^EndSquiggle$");
	private Pattern pStartPoints = Pattern.compile("^points$");
	private Pattern pEndPoints = Pattern.compile("^endpoints$");
	private Pattern pSquigglePoints = Pattern.compile("^point:[(](\\d{1,}),(\\d{1,})[)]$");
	
	
	// Rectangle Patterns 
	private Pattern pRectangleStart=Pattern.compile("^Rectangle$");
	private Pattern pRectangleEnd=Pattern.compile("^EndRectangle$");
	private Pattern pRPoint1 = Pattern.compile("^p1:[(](\\d{1,}),(\\d{1,})[)]$");
	private Pattern pRPoint2 = Pattern.compile("^p2:[(](\\d{1,}),(\\d{1,})[)]$");
	
	/**
	 * Store an appropriate error message in this, including 
	 * lineNumber where the error occurred.
	 * @param mesg
	 */
	private void error(String mesg){
		this.errorMessage = "Error in line "+lineNumber+" "+mesg;
		System.out.println(this.errorMessage);
		JOptionPane pane = new JOptionPane(this.errorMessage);
		final JDialog error = pane.createDialog("Cannot open the file");
		//closes the error message box without user response
		//So JUnit testing does not wait for user input
		Timer closeBox = new Timer(1000, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				error.setVisible(false);
			}});
		closeBox.setRepeats(false);
		closeBox.start();
		error.setVisible(true);
	}
	/**
	 * 
	 * @return the PaintCommands resulting from the parse
	 */
	public ArrayList<PaintCommand> getCommands(){
		return this.commands;
	}
	/**
	 * 
	 * @return the error message resulting from an unsuccessful parse
	 */
	public String getErrorMessage(){
		return this.errorMessage;
	}
	
	/**
	 * Parse the inputStream as a Paint Save File Format file.
	 * The result of the parse is stored as an ArrayList of Paint command.
	 * If the parse was not successful, this.errorMessage is appropriately
	 * set, with a useful error message.
	 * 
	 * @param inputStream the open file to parse
	 * @return whether the complete file was successfully parsed
	 */
	public boolean parse(BufferedReader inputStream) {
		this.commands = new ArrayList<PaintCommand>();
		this.errorMessage="";
		
		// During the parse, we will be building one of the 
		// following shapes. As we parse the file, we modify 
		// the appropriate shape.
		
		Circle circle = null; 
		Rectangle rectangle = null;
		Squiggle squiggle = null;
		Color color = null;
		Boolean filled = null;
	
		try {	
			int state=0; Matcher m; String l;
			
			this.lineNumber=0;
			
			while ((l = inputStream.readLine()) != null) {
				if (!l.isEmpty()) { // check if line is not empty
				l = l.replaceAll("\\s+",""); // strip all white space
				this.lineNumber++;
				System.out.println(lineNumber+" "+l+" "+state);
				switch(state){
					case 0:
						m=pFileStart.matcher(l);
						if(m.matches()){
							state=1;
							break;
						}
						error("- Expected Start of Paint Save File");
						return false;
					case 1: // Looking for the start of a new object or end of the save file
						m=pCircleStart.matcher(l);
						if(m.matches()){
							state=2; 
							circle = new Circle();
							break;
						}
						m=pRectangleStart.matcher(l);
						if(m.matches()){
							state=7; 
							rectangle = new Rectangle();
							break;
						}
						m=pSquiggleStart.matcher(l);
						if(m.matches()){
							state=12; 
							squiggle = new Squiggle();
							break;
						}
						m=pFileEnd.matcher(l);
						if(m.matches()){
							state = 2; 
							break;
						}
						error("- Expected End of file or start of new shape");
						return false;
					case 2: // Looking for circle color
						m=pColor.matcher(l);
						if(m.matches()){
							int r = Integer.parseInt(m.group(1));
							int g = Integer.parseInt(m.group(2));
							int b = Integer.parseInt(m.group(3));
							if ((0 <=r && r <= 255) && (0 <=g && g <= 255) && (0 <=b && b <= 255)) {
							state = 3; 
							color = new Color(r, g, b);
							circle.setColor(color);
							break;
							}
						}
						error("- Expected colour of circle");
						return false;
					case 3: // Looking for filled boolean of circle
						m=pFilled.matcher(l);
						if(m.matches()){
							state = 4; 
							filled = Boolean.parseBoolean(m.group(1));
							circle.setFill(filled);
							break;
						}
						error("- Expected filed boolean of circle");
						return false;
					case 4:	 // Looking for circle center
						m=pCenter.matcher(l);
						if(m.matches()){
							state = 5; 
							int centerp1 = Integer.parseInt(m.group(1));
							int centerp2 = Integer.parseInt(m.group(2));
							circle.setCentre(new Point (centerp1, centerp2));
							break;
						}
						error("- Expected center of circle");
						return false;
					case 5: // Looking for circle radius
						m=pRadius.matcher(l);
						if(m.matches()){
							state = 6; 
							int radius = Integer.parseInt(m.group(1));
							circle.setRadius(radius);
							break;
						}
						error("- Expected radius of circle");
						return false;
					case 6: // Looking for end of circle 
						m=pCircleEnd.matcher(l);
						if(m.matches()){
							state=1; 
							commands.add(new CircleCommand (circle));
							break;
						}
					case 7: // Looking for rectangle color
						m=pColor.matcher(l);
						if(m.matches()){
							int r = Integer.parseInt(m.group(1));
							int g = Integer.parseInt(m.group(2));
							int b = Integer.parseInt(m.group(3));
							if ((0 <=r && r <= 255) && (0 <=g && g <= 255) && (0 <=b && b <= 255)) {
							state = 8; 
							color = new Color(r, g, b);
							rectangle.setColor(color);
							break;
							}
						}
						error("- Expected colour of rectangle");
						return false;
					case 8: // Looking for filled boolean of rectangle 
						m=pFilled.matcher(l);
						if(m.matches()){
							state = 9; 
							filled = Boolean.parseBoolean(m.group(1));
							rectangle.setFill(filled);
							break;
						}
						error("- Expected filled boolean of rectangle");
						return false;
					case 9: // Looking for rectangle p1
						m=pRPoint1.matcher(l);
						if(m.matches()){
							state = 10; 
							int p1x = Integer.parseInt(m.group(1));
							int p1y = Integer.parseInt(m.group(2));
							rectangle.setP1(new Point(p1x, p1y));
							break;
						}
						error("- Expected point 1 of rectangle");
						return false;
					case 10: // Looking for rectangle p2
						m=pRPoint2.matcher(l);
						if(m.matches()){
							state = 11; 
							int p2x = Integer.parseInt(m.group(1));
							int p2y = Integer.parseInt(m.group(2));
							rectangle.setP2(new Point(p2x, p2y));
							break;
						}
						error("- Expected point 2 of rectangle");
						return false;
					case 11: // Looking for end of rectangle 
						m=pRectangleEnd.matcher(l);
						if(m.matches()){
							state=1; 
							commands.add(new RectangleCommand(rectangle));
							break;
						}
						error("- Expected End of rectangle");
						return false;
					case 12: // Looking for squiggle color
						m=pColor.matcher(l);
						if(m.matches()){
							int r = Integer.parseInt(m.group(1));
							int g = Integer.parseInt(m.group(2));
							int b = Integer.parseInt(m.group(3));
							if ((0 <=r && r <= 255) && (0 <=g && g <= 255) && (0 <=b && b <= 255)) {
							state = 13; 
							color = new Color(r, g, b);
							squiggle.setColor(color);
							break;
							}
						}
						error("- Expected colour of squiggle");
						return false;
					case 13: // Looking for squiggle filled boolean
						m=pFilled.matcher(l);
						if(m.matches()){
							state = 14; 
							filled = Boolean.parseBoolean(m.group(1));
							squiggle.setFill(filled);
							break;
						}
						error("- Expected filed boolean of squiggle");
						return false;
					case 14: // Looking for squiggle start of points
						m=pStartPoints.matcher(l);
						if(m.matches()){
							state = 15; 
							break;
						}
						error("- Expected start of points - squiggle");
						return false;
						
					case 15: // Looking for squiggle point
						m=pSquigglePoints.matcher(l);
						if(m.matches()){
							state = 15; 
							int pX = Integer.parseInt(m.group(1));
							int pY = Integer.parseInt(m.group(2));
							squiggle.add(new Point(pX, pY));
							break;
						}
						if (squiggle.getPoints().isEmpty()) {
							error("- Expected at least one point");
							return false;
						} 
						// Looking for end of the points
						m=pEndPoints.matcher(l);
						if(m.matches()){
							state = 16; 
							break;
						}
						error("- Expected end of points for squiggle");
						return false;
					case 16: // Looking for end of squiggle 
						m=pSquiggleEnd.matcher(l);
						if(m.matches()){
							state=1; 
							commands.add(new SquiggleCommand(squiggle));
							break;
						}
						error("- Expected End of Squiggle");
						return false;
				}
				}
			}
		}  catch (Exception e){
			
		}
		return true;
	}
}
