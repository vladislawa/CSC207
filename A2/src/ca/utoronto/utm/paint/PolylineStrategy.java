package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Creates a manipulator strategy for Polyline.
 * 
 * @author csc207 student
 *
 */

public class PolylineStrategy implements ShapeManipulatorStrategy{

	// MouseMotionListener below
	@Override
	public void mouseDragged(MouseEvent e, PaintPanel paintPanel) {
		paintPanel.getPolyline().updateValues(e.getX(), e.getY());
		paintPanel.getModel().addPolyline(paintPanel.getPolyline());
	}

	// MouseMotionListener below
	@Override
	public void mousePressed(MouseEvent e, PaintPanel paintPanel) {
		Color color = paintPanel.getColor();
		float lineThickness = paintPanel.getLineThickness();
		Point start;
		if (paintPanel.getPolyline() != null) {
			start = paintPanel.getPolyline().getEndpoint();
		} else {
			start = new Point(e.getX(), e.getY(), color, lineThickness);
		}
		paintPanel.setPolyline(new Polyline(color, lineThickness));
		paintPanel.getPolyline().setStartpoint(start);
		paintPanel.setStartInd(paintPanel.getModel().getDrawingList().size());
	}

	// MouseMotionListener below
	@Override
	public void mouseReleased(MouseEvent e, PaintPanel paintPanel) {
		paintPanel.getPolyline().updateValues(e.getX(), e.getY());
		
	    ArrayList<Shape> l = paintPanel.getModel().getDrawingList();
	    paintPanel.setEndInd(paintPanel.getModel().getDrawingList().size());
	    
	    while (paintPanel.getStartInd() != paintPanel.getEndInd()) {
	    		l.remove(paintPanel.getStartInd());
	    		paintPanel.setEndInd(paintPanel.getEndInd() - 1);
	    }
	    
	    paintPanel.getModel().setDrawingList(l);
		paintPanel.getModel().addPolyline(paintPanel.getPolyline());
		paintPanel.setDrawn(true);
	}
}
