package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
/**
 * Creates a manipulator strategy for eraser.
 * 
 * @author csc207 student
 *
 */
public class EraserStrategy implements ShapeManipulatorStrategy{

	// MouseMotionListener below
	@Override
	public void mouseDragged(MouseEvent e, PaintPanel paintPanel) {
		paintPanel.getEraser().setColor(Color.white);
		paintPanel.getEraser().addPoint(e.getX(), e.getY());
		paintPanel.getModel().addEraser(paintPanel.getEraser());
	}
	
	// MouseMotionListener below
	@Override
	public void mousePressed(MouseEvent e, PaintPanel paintPanel) {
		Color color = Color.white;
		float lineThickness = paintPanel.getLineThickness();
		paintPanel.setEraser(new Eraser(color, lineThickness));
        paintPanel.getErasers().add(paintPanel.getEraser());
        paintPanel.getEraser().addPoint(e.getX(), e.getY());
        paintPanel.setStartInd(paintPanel.getModel().getDrawingList().size());
	}

	// MouseMotionListener below
	@Override
	public void mouseReleased(MouseEvent e, PaintPanel paintPanel) {
	    paintPanel.getEraser().addPoint(e.getX(), e.getY());
		
	    ArrayList<Shape> l = paintPanel.getModel().getDrawingList();
	    paintPanel.setEndInd(paintPanel.getModel().getDrawingList().size());
	    
	    while (paintPanel.getStartInd() != paintPanel.getEndInd()) {
	    		l.remove(paintPanel.getStartInd());
	    		paintPanel.setEndInd(paintPanel.getEndInd() - 1);
	    }
	    
	    paintPanel.getModel().setDrawingList(l);
	    paintPanel.getModel().addEraser(paintPanel.getEraser());
		paintPanel.setEraser(null);
		paintPanel.setDrawn(true);
	}
}
