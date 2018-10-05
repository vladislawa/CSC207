package ca.utoronto.utm.paint;

import java.awt.event.MouseEvent;

/**
 * The ShapeManipulatorStrategy Interface 
 * 
 * @author csc207 student
 *
 */
public interface ShapeManipulatorStrategy {

	public void mouseDragged(MouseEvent e, PaintPanel paintPanel);

	public void mousePressed(MouseEvent e, PaintPanel paintPanel);
	
	public void mouseReleased(MouseEvent e, PaintPanel paintPanel);
	
}
