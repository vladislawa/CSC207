package ca.utoronto.utm.paint;

import java.awt.Graphics2D;
/**
 * The Command Interface for Command Design Pattern
 * 
 * @author csc207 student
 *
 */
public interface Command {
	
	public void execute(Graphics2D g2d);

}
