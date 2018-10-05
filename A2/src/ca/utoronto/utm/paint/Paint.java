package ca.utoronto.utm.paint;

import javax.swing.JFrame;
/**
 * Creates instances of classes needed to initiate the Paint program.
 * 
 *  @author professor
 *
 */
public class Paint {
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Paint();
			}
		});
	}

	PaintModel model; // Model
	View view; // View+Controller

	public Paint() {
		// Create MVC components and hook them together

		// Model
		this.model = new PaintModel();

		// View+Controller
		this.view = new View(model);
		
	}
}
