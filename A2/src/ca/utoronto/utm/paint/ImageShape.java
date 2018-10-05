package ca.utoronto.utm.paint;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
/**
 * Creates an image shape.
 * 
 * @author csc207 student
 *
 */
public class ImageShape extends BufferedImage implements Command {

	//Declare variables
	private BufferedImage image;
	private PaintPanel paintPanel;
	private boolean isPasted;
	private int x;
	private int y;
	private int w;
	private int h;
	
	//Constructor
	public ImageShape(int width, int height, int imageType, BufferedImage image, PaintPanel paintPanel, boolean isPasted, int x, int y, int w, int h) {
		super(width, height, imageType);
		this.image = image;
		this.paintPanel = paintPanel;
		this.isPasted = isPasted;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	/**
	 * @return true if image needs pasting, false otherwise
	 */
	public boolean getPasted() {
		return isPasted;
	}
	
	/**
	 * Sets True if image needs pasting, false otherwise
	 * @param boolean isPasted
	 */
	public void setPasted(boolean isPasted) {
		this.isPasted = isPasted;
	}

	/**
	 * Draws image
	 */
	@Override
	public void execute(Graphics2D g2d) {
		g2d.drawImage(image.getSubimage(x+3,y+3,w-4,h-4), 200, 200, paintPanel);
	}
	 
}
