package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Creates a manipulator strategy for Square.
 * 
 * @author csc207 student
 *
 */
public class SquareStrategy implements ShapeManipulatorStrategy{

	// MouseMotionListener below
	@Override
	public void mouseDragged(MouseEvent e, PaintPanel paintPanel) {
		paintPanel.getSquare().getTop().setX(Math.min(e.getX(), paintPanel.getOriginPointX()));
	    
		int width = Math.abs(paintPanel.getOriginPointX() - e.getX());
		
		paintPanel.getSquare().setWidth(width);
		
		paintPanel.getModel().addSquare(paintPanel.getSquare());
	}

	// MouseMotionListener below
	@Override
	public void mousePressed(MouseEvent e, PaintPanel paintPanel) {
		paintPanel.setOriginPointX(e.getX());
		paintPanel.setOriginPointY(e.getY());
		Color color = paintPanel.getColor();
		float lineThickness = paintPanel.getLineThickness();
		boolean fillShape = paintPanel.shapeIsFilled();
		Point top = new Point(e.getX(), e.getY(), color, lineThickness);
		int width = 0;
		paintPanel.setSquare(new Square(top, width, color, lineThickness, fillShape));
		paintPanel.setStartInd(paintPanel.getModel().getDrawingList().size());
	}

	// MouseMotionListener below
	@Override
	public void mouseReleased(MouseEvent e, PaintPanel paintPanel) {		
		paintPanel.getSquare().getTop().setX(Math.min(e.getX(), paintPanel.getOriginPointX()));
		int width = Math.abs(paintPanel.getOriginPointX() - e.getX());
		paintPanel.getSquare().setWidth(width);
		
	    ArrayList<Shape> l = paintPanel.getModel().getDrawingList();
	    paintPanel.setEndInd(paintPanel.getModel().getDrawingList().size());
	    
	    while (paintPanel.getStartInd() != paintPanel.getEndInd()) {
		     l.remove(paintPanel.getStartInd());
		     paintPanel.setEndInd(paintPanel.getEndInd() - 1);
	    }
	    
	    paintPanel.getModel().setDrawingList(l);
		paintPanel.getModel().addSquare(paintPanel.getSquare());
		paintPanel.setSquare(null);
		paintPanel.setPolyline(null);
		paintPanel.setDrawn(true);
	}
}
