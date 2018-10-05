package ca.utoronto.utm.paint;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
/**
 * Creates panel for colors.
 * 
 * @author csc207 student
 *
 */
class ColorChooserPanel extends JPanel implements ActionListener {
	
	//Declare variables
	private View view; // So we can talk to our parent or other components of the view
	private JButton originalButton; //the initial button clicked
	private HashMap<JButton, Border> buttons = new HashMap<JButton, Border>(); //to access the buttons used and there borders
	
	//Constructor
	public ColorChooserPanel (View view) {
		
		this.view = view;
		
		//Create dictionary of color names with their color icon.
		 HashMap<String, Color> colorDict = new HashMap<String, Color>();
		 
		 //Add color names with their colors to dictionary
		 colorDict.put("Black", Color.black);
		 colorDict.put("Blue", Color.blue);
		 colorDict.put("Cyan", Color.cyan);
		 colorDict.put("Gray", Color.gray);
		 colorDict.put("Dark Gray", Color.darkGray);
		 colorDict.put("Green", Color.green);
		 colorDict.put("Magenta", Color.magenta);
		 colorDict.put("Pink", Color.pink);
		 colorDict.put("Red", Color.red);
		 colorDict.put("Orange", Color.orange);
		 colorDict.put("Yellow", Color.yellow);
		 colorDict.put("White", Color.white);
		
		 //Set up layout
		this.setLayout(new GridLayout(2, 6));
		
		for (Map.Entry<String, Color> color : colorDict.entrySet()) {
			//Create buttons for colors
			JButton Color = new JButton();
			Color.setPreferredSize(new Dimension (45, 45));
			Color.setBackground(color.getValue());
			Color.setName(color.getKey());
			this.add(Color);
			buttons.put(Color, Color.getBorder());
			Color.addActionListener(this);
		}
	}

	// ActionListener below 
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();

		if (originalButton == null) {
			
			this.view.getPaintPanel().setPaintMode(button.getName());
			button.setBorder(BorderFactory.createRaisedBevelBorder());
			button.setFocusPainted(true);
			originalButton = button;
			
		} else {
		
			originalButton.setBorder(button.getBorder());
			originalButton.setFocusPainted(false);
			this.view.getPaintPanel().setPaintMode(button.getName());
			button.setBorder(BorderFactory.createRaisedBevelBorder());
			button.setFocusPainted(true);
			originalButton = button;
		}
	}
	
	/**
	 * Resets the colorChooserPanel
	 */
	public void reset() {
		for (Map.Entry<JButton, Border> button : buttons.entrySet()) {
			button.getKey().setBorder(button.getValue());
		}
		originalButton = null;
	}
}//end class
