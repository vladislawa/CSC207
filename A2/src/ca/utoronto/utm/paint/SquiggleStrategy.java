package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Creates a manipulator strategy for Squiggle.
 * 
 * @author csc207 student
 *
 */
public class SquiggleStrategy implements ShapeManipulatorStrategy{

	// MouseMotionListener below
	@Override
	public void mouseDragged(MouseEvent e, PaintPanel paintPanel) {
		paintPanel.getSquiggle().addPoint(e.getX(), e.getY());
		paintPanel.getModel().addSquiggle(paintPanel.getSquiggle());
	}

	// MouseMotionListener below
	@Override
	public void mousePressed(MouseEvent e, PaintPanel paintPanel) {
		Color color = paintPanel.getColor();
		float lineThickness = paintPanel.getLineThickness();
		paintPanel.setSquiggle(new Squiggle(color, lineThickness));
		paintPanel.getSquiggle().addPoint(e.getX(), e.getY());
		paintPanel.getLines().add(paintPanel.getSquiggle());
		paintPanel.setStartInd(paintPanel.getModel().getDrawingList().size());
	}

	// MouseMotionListener below
	@Override
	public void mouseReleased(MouseEvent e, PaintPanel paintPanel) {
		paintPanel.getSquiggle().addPoint(e.getX(), e.getY());
		
	    ArrayList<Shape> l = paintPanel.getModel().getDrawingList();
	    paintPanel.setEndInd(paintPanel.getModel().getDrawingList().size());
	    
	    while (paintPanel.getStartInd() != paintPanel.getEndInd()) {
		     l.remove(paintPanel.getStartInd());
		     paintPanel.setEndInd(paintPanel.getEndInd() - 1);
	    }
	    
	    paintPanel.getModel().setDrawingList(l);
	    paintPanel.getModel().addSquiggle(paintPanel.getSquiggle());
	    paintPanel.setSquiggle(null);
	    paintPanel.setPolyline(null);
		paintPanel.setDrawn(true);
	}
}
