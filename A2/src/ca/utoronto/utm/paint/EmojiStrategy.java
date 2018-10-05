package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.event.MouseEvent;

/**
 * Creates a manipulator strategy for emoji.
 * 
 * @author csc207 student
 *
 */
public class EmojiStrategy  implements ShapeManipulatorStrategy{
	
	// MouseMotionListener below
	@Override
	public void mouseDragged(MouseEvent e, PaintPanel paintPanel) {

	}
	
	// MouseMotionListener below
	@Override
	public void mousePressed(MouseEvent e, PaintPanel paintPanel) {
		paintPanel.setEmoji(new Emoji(paintPanel.getEmojiImage(), paintPanel, e.getX(), e.getY()));
		paintPanel.getModel().addEmoji(paintPanel.getEmoji());
	}
	
	// MouseMotionListener below
	@Override
	public void mouseReleased(MouseEvent e, PaintPanel paintPanel) {
		
	}
}
