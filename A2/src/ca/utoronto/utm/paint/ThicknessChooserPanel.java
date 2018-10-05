package ca.utoronto.utm.paint;
/**
 * Creates panel for line thickness.
 * 
 * @author csc207 student
 *
 */

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

// https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html
// https://docs.oracle.com/javase/tutorial/2d/

class ThicknessChooserPanel extends JPanel implements ChangeListener {
	
	//Declare variables
	private View view; // So we can talk to our parent or other components of the view
	private JSlider thickness; // the JSlider to be used for the thickness panel
	
	//Constructor
	public ThicknessChooserPanel(View view) {	
		
		this.view=view;
		
		//Layout Design
		this.setLayout(new GridLayout(2, 0));
		
		//Create new label
		JLabel thick = new JLabel();
		thick.setHorizontalAlignment(SwingConstants.CENTER);
		thick.setFont(new Font("Arial", Font.BOLD, 20));
		thick.setBackground(Color.LIGHT_GRAY);
		thick.setText("Thickness:");
		
		//Add label to panel
		this.add(thick);
		
		//Create new slider
		thickness = new JSlider(1, 100, 1);
		thickness.setPreferredSize(new Dimension (50, 60));
		thickness.setPaintTicks(true);
		thickness.setPaintLabels(true);
		
		Hashtable<Integer, JLabel> labels = new Hashtable<Integer, JLabel>();
		labels.put(1, new JLabel ("1"));
		labels.put(25,  new JLabel ("25"));
		labels.put(50,  new JLabel ("50"));
		labels.put(75,  new JLabel ("75"));
		labels.put(100,  new JLabel ("100"));
		labels.put(0,  new JLabel ("00000000"));
		
		thickness.setLabelTable(labels);
		
		//Add slider to panel
		this.add(thickness);
		
		//Hook up to Change Listener
		thickness.addChangeListener(this);
		
	}
	
	/**
	 * Controller aspect of this
	 */
	public void actionPerformed(ActionEvent e) {
		this.view.getPaintPanel().setThicknessMode(e.getActionCommand());
		System.out.println(e.getActionCommand());
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		int thicknessv = ((JSlider)e.getSource()).getValue();
		String v = "" + thicknessv;
		this.view.getPaintPanel().setThicknessMode(v);
	}
	
	/**
	 * Resets the thicknessChooserPanel
	 */
	public void reset() {
		thickness.setValue(1);
	}
}