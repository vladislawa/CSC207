package ca.utoronto.utm.paint;

import java.awt.event.MouseEvent;
/**
 * Creates a manipulator strategy for Eye Dropper.
 * 
 * @author csc207 student
 *
 */
public class EyeDropperStrategy implements ShapeManipulatorStrategy{
	
	// MouseMotionListener below
	@Override
	public void mouseDragged(MouseEvent e, PaintPanel paintPanel) {
	}

	// MouseMotionListener below
	@Override
	public void mousePressed(MouseEvent e, PaintPanel paintPanel) {
		paintPanel.setCX(e.getX());
		paintPanel.setCY(e.getY());
		paintPanel.eyeDropper();
	}

	// MouseMotionListener below
	@Override
	public void mouseReleased(MouseEvent e, PaintPanel paintPanel) {
	}
}
