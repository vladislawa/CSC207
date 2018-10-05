package ca.utoronto.utm.paint;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.*;
import java.io.File;
/**
 * This is the top level View+Controller, it contains other aspects of the View+Controller.
 * @author professor, csc207 student
 *
 */
public class View extends JFrame implements ActionListener {
	
	//Declare static variables
	private static final long serialVersionUID = 1L;
	public Container c1;
	
	//Model
	private PaintModel model;
	
	// The components that make this up
	private PaintPanel paintPanel;
	private ShapeChooserPanel shapeChooserPanel;
	private ColorChooserPanel colorChooserPanel;
	private ThicknessChooserPanel thicknessChooserPanel;
	private EmojiPanel emojiPanel;
	
	//Constructor
	public View(PaintModel model) {
		super("Paint"); // set the title and do other JFrame init
		
		//Layout Components
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createAndShowGUI(model);
	}
	
	/**
	 *  The GUI contents of the Paint program.
	 */
	public void createAndShowGUI(PaintModel model) {
		this.setJMenuBar(createMenuBar());
		
		c1 = this.getContentPane();
		c1.removeAll();

		this.shapeChooserPanel = new ShapeChooserPanel(this);
		c1.add(this.shapeChooserPanel,BorderLayout.WEST);
		
		this.thicknessChooserPanel = new ThicknessChooserPanel(this);
		
		//Create new textfield
		JTextField greetingText = new JTextField(10);
		greetingText.setText("Welcome to Our First Ever Paint Program!");
		greetingText.setHorizontalAlignment(NORMAL);
		greetingText.setFont(new Font("Arial", Font.BOLD, 14));
		greetingText.setBackground(Color.LIGHT_GRAY);
		greetingText.setEditable(false);
		
		c1.add(greetingText, BorderLayout.NORTH);
		
		this.colorChooserPanel = new ColorChooserPanel(this);
		this.emojiPanel = new EmojiPanel(this);
		
		JPanel c2 = new JPanel(new BorderLayout());
		
		//Add Color Chooser Panel and Thickness Chooser Panel to the panel
		c2.add(this.colorChooserPanel, BorderLayout.WEST);
		c2.add(this.emojiPanel, BorderLayout.EAST);
		c2.add(this.thicknessChooserPanel, BorderLayout.CENTER);
		
		//Add panel to frame
		c1.add(c2, BorderLayout.SOUTH);
	
		this.model = model;
		
		this.paintPanel = new PaintPanel(model, this);
		c1.add(this.paintPanel, BorderLayout.CENTER);
		
		// Frame Layout Design
		this.pack();
		this.setSize(600, 800);
		this.setVisible(true);
	}
	
	/**
	 * Resets the View.
	 */
	public void resetView() {
		this.shapeChooserPanel.reset();
		this.colorChooserPanel.reset();
		this.thicknessChooserPanel.reset();
		this.emojiPanel.reset();
	}
	
	/**
	 * Resets the ShapeChooserPanel.
	 */
	public void resetShapeChooserPanel() {
		this.shapeChooserPanel.reset();
	}

	/**
	 *  @return panel paintPanel
	 */	
	public PaintPanel getPaintPanel(){
		return paintPanel;
	}
	
	/**
	 *  @return panel shapeChooserPanel
	 */	
	public ShapeChooserPanel getShapeChooserPanel() {
		return shapeChooserPanel;
	}
	
	/**
	 *  @return panel colorChooserPanel
	 */
	public ColorChooserPanel getColorChooserPanel() {
		return colorChooserPanel;
	}
	
	/**
	 *  @return panel emojiPanel
	 */
	public EmojiPanel getEmojiPanel() {
		return emojiPanel;
	}
	
	/**
	 *  @return panel thicknessChooserPanel
	 */
	public ThicknessChooserPanel getThicknessChooserPanel() {
		return thicknessChooserPanel;
	}
	
	/**
	 *  Creates Menu bar for frame.
	 */
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu;
		JMenuItem menuItem;

		menu = new JMenu("File");

		// a group of JMenuItems
		menuItem = new JMenuItem("New");
		menuItem.addActionListener(new NewPanelListener());
		menu.add(menuItem);

		menuItem = new JMenuItem("Open");
		menuItem.addActionListener(new OpenListener());
		menu.add(menuItem);

		menuItem = new JMenuItem("Save");
		menuItem.addActionListener(new SaveListener());
		menu.add(menuItem);

		menu.addSeparator();// -------------

		menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(new ExitListener());
		menu.add(menuItem);

		menuBar.add(menu);

		menu = new JMenu("Edit");

		// a group of JMenuItems
		menuItem = new JMenuItem("Select");
		menuItem.addActionListener(new SelectListener());
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Cut");
		menuItem.addActionListener(new CutListener());
		menu.add(menuItem);

		menuItem = new JMenuItem("Copy");
		menuItem.addActionListener(new CopyListener());
		menu.add(menuItem);

		menuItem = new JMenuItem("Paste");
		menuItem.addActionListener(new PasteListener());
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Delete");
		menuItem.addActionListener(new DeleteListener());
		menu.add(menuItem);

		menu.addSeparator();// -------------

		menuItem = new JMenuItem("Undo");
		menuItem.addActionListener(new UndoListener());
		menu.add(menuItem);

		menuItem = new JMenuItem("Redo");
		menuItem.addActionListener(new RedoListener());
		menu.add(menuItem);

		menuBar.add(menu);
		
		menu = new JMenu("Help");
		
		menuItem = new JMenuItem("About");
		menuItem.addActionListener(new AboutListener());
		menu.add(menuItem);
		
		menuBar.add(menu);

		return menuBar;
	}
	
	/**
	 *  Adds about function to the 'About' button.
	 */
	class AboutListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Hello, and welcome to our Paint program that was made for one of the CSC207 assignments.\n"
					   + "-------------------------------------------------------------------------------------------------------------------------------------\n"
					   + "> Currently our Paint program implements these shapes:\n\n"
				       + "      1. Circle.\n" + "      2. Square. \n" + "      3. Rectangle.\n" + "      4. Polyline. \n" + "      5. Squiggle.\n" + "      6. Emojies\n\n"
				       + "> The Paint program has these tools:\n\n"
				       + "      1. Cut.\n" + "      2. Copy.\n" + "      3. Paste.\n" + "      4. Select.\n" + "      5. Undo.\n" + "      6. Redo.\n" + "      7. Eye Dropper.\n" + "      8. Eraser.\n"
				       + "-------------------------------------------------------------------------------------------------------------------------------------\n"
				       + "The Paint program currently features 12 colors to be used for any shape to be drawn with.\n"
				       + "Lastly, the Paint program also has a Fill button which is used to draw filled shapes.");
		}
	}
		
	/**
	 *  Adds paste function to the 'Paste' button.
	 */
	class PasteListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			paintPanel.paste();
		}
	}

	/**
	 *  Adds copy function to the 'Copy' button.
	 */
	class CopyListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			paintPanel.copy();
		}
	}


	/**
	 *  Adds delete function to the 'Delete' button.
	 */

	class DeleteListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			paintPanel.delete();
		}
	}
		

	/**
	 *  Adds cut function to the 'Cut' button.
	 */

	class CutListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			paintPanel.cut();
		}
	}
		
	/**
	 *  Adds undo function to the 'Undo' button.
	 */
	class UndoListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			model.undo(paintPanel.getIsSelected());
		}
	}

	/**
	 *  Adds redo function to the 'Redo' button.
	 */
	class RedoListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			model.redo();
		}
	}

	/**
	 *  Adds select function to the 'Select' button.
	 */
	class SelectListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			paintPanel.select();
		}
	}

	/**
	 *  Adds new panel function to the 'New' button.
	 */
	class NewPanelListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			paintPanel.newPanel();
		}
	}
		
	/**
	 *  Adds open function to the 'Open' button.
	 */
	class OpenListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			paintPanel.open();
		}
	}
		
	/**
	 *  Adds save function to the 'Save' button.
	 */
	class SaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			paintPanel.save();
		}
	}
		
	/**
	 *  Adds exit function to the 'Exit' button.
	 */
	class ExitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (paintPanel.hasDrawn()) {
				
				int result = JOptionPane.showConfirmDialog(null,
						"Do you want to save the changes made to the file?", "Exit", JOptionPane.YES_NO_OPTION);
				
				if (result == JOptionPane.YES_OPTION) {
					
					paintPanel.save();
					
				}
			} 
			System.exit(0);
		}
	}

	/**
	 *  Returns paint panel
	 *  
	 *  @return instance of a class PaintPanel
	 */
	public PaintPanel getPanel() {
		return paintPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}//end class