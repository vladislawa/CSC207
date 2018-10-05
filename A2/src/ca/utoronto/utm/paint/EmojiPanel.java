package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import javax.print.DocFlavor.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * Creates panel for emojies.
 * 
 * @author csc207 student
 *
 */
// https://www.freepik.com/free-vector/smiley-patches_903109.htm

public class EmojiPanel extends JPanel implements ActionListener  {
	
	//Declare variables
	private View view; // So we can talk to our parent or other components of the view
	private HashMap<JButton, Border> ebuttons = new HashMap<JButton, Border>(); //to access the buttons used and there borders
	private ShapeManipulatorStrategy currentStrategy; //the strategy being used
	private JButton originalButton; //the initial button clicked
	
	public EmojiPanel (View view) {
		this.view=view;
		
		//Layout Design
		this.setLayout(new GridLayout(2, 2));
		
		ImageIcon one = new ImageIcon(getClass().getResource("minione.png"));
		ImageIcon two = new ImageIcon(getClass().getResource("minitwo.png"));
		ImageIcon three = new ImageIcon(getClass().getResource("minithree.png"));
		ImageIcon four = new ImageIcon(getClass().getResource("minifour.png"));
		
		// Create dictionary to hold button names and its icons
		HashMap<String, ImageIcon> iconDict = new HashMap<String, ImageIcon>();
		
		//Add button names and icons to dictionary
		iconDict.put("Smiling", one);
		iconDict.put("Crying", two);
		iconDict.put("Laughing", three);
		iconDict.put("Scared", four);
		
		for (Map.Entry<String, ImageIcon> icon : iconDict.entrySet()) {
			
			//Create button and set its name and icon
			JButton shapeButton = new JButton();
			Dimension d = new Dimension(60,60);
			shapeButton.setPreferredSize(d);
			shapeButton.setIcon(icon.getValue());
			shapeButton.setName(icon.getKey());
			shapeButton.setBackground(Color.lightGray);
			this.add(shapeButton);
			ebuttons.put(shapeButton, shapeButton.getBorder());
			shapeButton.addActionListener(this);
			
			shapeButton.addMouseListener(new java.awt.event.MouseAdapter() {
			    public void mouseEntered(java.awt.event.MouseEvent evt) {
			    		shapeButton.setToolTipText(icon.getKey());
			    }
			});
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		BufferedImage bufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = bufferedImage.createGraphics();
		paint(g2);
		JButton button = (JButton)e.getSource();
		if (button.getName() == "Smiling") {
			System.out.println(button.getName());
			java.net.URL url = getClass().getResource("minione.png");
			Image image = Toolkit.getDefaultToolkit().getImage(url.getPath());
			view.getPaintPanel().setEmojiImage(image);
			currentStrategy = new EmojiStrategy();
			view.getPaintPanel().setStrategy(currentStrategy); 
		}
		else if (button.getName() == "Crying") {
			System.out.println(button.getName());
			java.net.URL url = getClass().getResource("minitwo.png");
			Image image = Toolkit.getDefaultToolkit().getImage(url.getPath());
			view.getPaintPanel().setEmojiImage(image);
			currentStrategy = new EmojiStrategy();
			view.getPaintPanel().setStrategy(currentStrategy); 
		}
		else if (button.getName() == "Laughing") {
			System.out.println(button.getName());
			java.net.URL url = getClass().getResource("minithree.png");
			Image image = Toolkit.getDefaultToolkit().getImage(url.getPath());
			view.getPaintPanel().setEmojiImage(image);
			currentStrategy = new EmojiStrategy();
			view.getPaintPanel().setStrategy(currentStrategy); 
		}
		else{
			System.out.println(button.getName());
			java.net.URL url = getClass().getResource("minifour.png");
			Image image = Toolkit.getDefaultToolkit().getImage(url.getPath());
			view.getPaintPanel().setEmojiImage(image);
			currentStrategy = new EmojiStrategy();
			view.getPaintPanel().setStrategy(currentStrategy); 
		}
		
		if (originalButton == null) {
			button.setBorder(BorderFactory.createLoweredBevelBorder());
			button.setBackground(Color.gray);
			originalButton = button;
		} else {
			originalButton.setBorder(button.getBorder());
			originalButton.setBackground(Color.lightGray);
			button.setBorder(BorderFactory.createLoweredBevelBorder());
			button.setBackground(Color.gray);
			originalButton = button;
		}
	}
	
	/**
	 * Resets the emojiPanel
	 */
	public void reset() {
		for (Map.Entry<JButton, Border> button : ebuttons.entrySet()) {
			button.getKey().setBorder(button.getValue());
			button.getKey().setBackground(Color.lightGray);
		}
		originalButton = null;
	}
}
