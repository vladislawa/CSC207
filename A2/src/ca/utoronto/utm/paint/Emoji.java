package ca.utoronto.utm.paint;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
/**
 * Creates shape emoji.
 * 
 * @author csc207 student
 *
 */
public class Emoji implements Command {
	
	//Declare variables
	private Image image;
	private PaintPanel paintPanel;
	private int x; 
	private int y;
	
	//Constructor
	public Emoji(Image image, PaintPanel paintPanel, int x, int y) {
		this.image = image;
		this.paintPanel = paintPanel;
		this.x = x;
		this.y = y;
	
	}
	
	/** 
	 * Draws an emoji
	 */
	@Override
	public void execute(Graphics2D g2d) {
		g2d.drawImage(image, x, y, paintPanel);
	}

}

