package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Creates a manipulator strategy for circle.
 * 
 * @author csc207 student
 *
 */
public class CircleStrategy implements ShapeManipulatorStrategy{

	// MouseMotionListener below
	@Override
	public void mouseDragged(MouseEvent e, PaintPanel paintPanel) {
		int radius = Math.abs(paintPanel.getCircle().getCentre().getX()-e.getX())* 2;
		paintPanel.getCircle().setRadius(radius);
		paintPanel.getModel().addCircle(paintPanel.getCircle());
	}
	
	// MouseMotionListener below
	@Override
	public void mousePressed(MouseEvent e, PaintPanel paintPanel) {
		Color circleColor = paintPanel.getColor();
		float circleThickness = paintPanel.getLineThickness();
		boolean fillCircle = paintPanel.shapeIsFilled();
		Point centre = new Point(e.getX(), e.getY(), circleColor, circleThickness);
		int radius = 0;
		paintPanel.setCircle(new Circle(centre, radius, circleColor, circleThickness, fillCircle));
		paintPanel.setStartInd(paintPanel.getModel().getDrawingList().size());
	}

	// MouseMotionListener below
	@Override
	public void mouseReleased(MouseEvent e, PaintPanel paintPanel) {
		int radius = Math.abs(paintPanel.getCircle().getCentre().getX()-e.getX())*2;
		paintPanel.getCircle().setRadius(radius);
		
	    ArrayList<Shape> l = paintPanel.getModel().getDrawingList();
	    paintPanel.setEndInd(paintPanel.getModel().getDrawingList().size());
	    
	    while (paintPanel.getStartInd() != paintPanel.getEndInd()) {
			l.remove(paintPanel.getStartInd());
			paintPanel.setEndInd(paintPanel.getEndInd() - 1);
	    }
	    
	    paintPanel.getModel().setDrawingList(l);
		paintPanel.getModel().addCircle(paintPanel.getCircle());
		paintPanel.setCircle(null);
		paintPanel.setPolyline(null);
		paintPanel.setDrawn(true);
	}
}
