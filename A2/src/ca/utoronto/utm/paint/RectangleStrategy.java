package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Creates a manipulator strategy for Rectangle.
 * 
 * @author csc207 student
 *
 */
public class RectangleStrategy implements ShapeManipulatorStrategy{

	// MouseMotionListener below
	@Override
	public void mouseDragged(MouseEvent e, PaintPanel paintPanel) {
		paintPanel.getRectangle().getCornerTop().setX(Math.min(e.getX(), paintPanel.getOriginPointX()));
		paintPanel.getRectangle().getCornerTop().setY(Math.min(e.getY(), paintPanel.getOriginPointY()));
	    
		int height = Math.abs(paintPanel.getOriginPointY() - e.getY());
		int width = Math.abs(paintPanel.getOriginPointX() - e.getX());
		
		paintPanel.getRectangle().setHeight(height);
		paintPanel.getRectangle().setWidth(width);
		
		paintPanel.getModel().addRectangle(paintPanel.getRectangle());
	}

	// MouseMotionListener below
	@Override
	public void mousePressed(MouseEvent e, PaintPanel paintPanel) {
		paintPanel.setOriginPointX(e.getX());
		paintPanel.setOriginPointY(e.getY());
		Color color = paintPanel.getColor();
		float lineThickness = paintPanel.getLineThickness();
		boolean fillShape = paintPanel.shapeIsFilled();
		Point cornertop = new Point(e.getX(), e.getY(), color, lineThickness);
		int height = 0;
		int width = 0;
		paintPanel.setRectangle(new Rectangle(cornertop, width, height, color, lineThickness, fillShape));
		paintPanel.setStartInd(paintPanel.getModel().getDrawingList().size());
	}
	
	// MouseMotionListener below
	@Override
	public void mouseReleased(MouseEvent e, PaintPanel paintPanel) {
		paintPanel.getRectangle().getCornerTop().setX(Math.min(e.getX(), paintPanel.getOriginPointX()));
		paintPanel.getRectangle().getCornerTop().setY(Math.min(e.getY(), paintPanel.getOriginPointY()));
	    
		int height = Math.abs(paintPanel.getOriginPointY() - e.getY());
		int width = Math.abs(paintPanel.getOriginPointX() - e.getX());
		
		paintPanel.getRectangle().setHeight(height);
		paintPanel.getRectangle().setWidth(width);
		
	    ArrayList<Shape> l = paintPanel.getModel().getDrawingList();
	    paintPanel.setEndInd(paintPanel.getModel().getDrawingList().size());
	    
	    while (paintPanel.getStartInd() != paintPanel.getEndInd()) {
	    		l.remove(paintPanel.getStartInd());
	    		paintPanel.setEndInd(paintPanel.getEndInd() - 1);
	    }
	    
	    paintPanel.getModel().setDrawingList(l);
		paintPanel.getModel().addRectangle(paintPanel.getRectangle());
		paintPanel.setRectangle(null);
		paintPanel.setPolyline(null);
		paintPanel.setDrawn(true);
	}
}
