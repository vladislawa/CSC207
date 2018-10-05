package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


/**
 * Creates a manipulator strategy for Select.
 * 
 * @author csc207 student
 *
 */
public class SelectStrategy implements ShapeManipulatorStrategy {
	
	// MouseMotionListener below
	@Override
	public void mouseDragged(MouseEvent e, PaintPanel paintPanel) {
		paintPanel.getSelect().getCornerTop().setX(Math.min(e.getX(), paintPanel.getOriginPointX()));
		paintPanel.getSelect().getCornerTop().setY(Math.min(e.getY(), paintPanel.getOriginPointY()));
	    
		int height = Math.abs(paintPanel.getOriginPointY() - e.getY());
		int width = Math.abs(paintPanel.getOriginPointX() - e.getX());
		
		paintPanel.getSelect().setHeight(height);
		paintPanel.getSelect().setWidth(width);
		
		paintPanel.getModel().addSelect(paintPanel.getSelect());
	}

	// MouseMotionListener below
	@Override
	public void mousePressed(MouseEvent e, PaintPanel paintPanel) {
		paintPanel.setOriginPointX(e.getX());
		paintPanel.setOriginPointY(e.getY());
		Color color = paintPanel.getColor();
		Point cornertop = new Point(e.getX(), e.getY(), color, 1);
		int height = 0;
		int width = 0;
		paintPanel.setSelect(new Select(cornertop, width, height));
		paintPanel.setStartInd(paintPanel.getModel().getDrawingList().size());
	}

	// MouseMotionListener below
	@Override
	public void mouseReleased(MouseEvent e, PaintPanel paintPanel) {
		paintPanel.getSelect().getCornerTop().setX(Math.min(e.getX(), paintPanel.getOriginPointX()));
		paintPanel.getSelect().getCornerTop().setY(Math.min(e.getY(), paintPanel.getOriginPointY()));
	    
		int height = Math.abs(paintPanel.getOriginPointY() - e.getY());
		int width = Math.abs(paintPanel.getOriginPointX() - e.getX());
		
		paintPanel.getSelect().setHeight(height);
		paintPanel.getSelect().setWidth(width);
		
	    ArrayList<Shape> l = paintPanel.getModel().getDrawingList();
	    paintPanel.setEndInd(paintPanel.getModel().getDrawingList().size());
	    
	    while (paintPanel.getStartInd() != paintPanel.getEndInd()) {
		    	l.remove(paintPanel.getStartInd());
		    	paintPanel.setEndInd(paintPanel.getEndInd() - 1);
	    }
	    
	    paintPanel.getModel().setDrawingList(l);
		paintPanel.getModel().addSelect(paintPanel.getSelect());

		paintPanel.setDrawn(true);
		paintPanel.setSelected(true);
		paintPanel.setStrategy(null);
		System.out.println("Area selected.");
	}
}
