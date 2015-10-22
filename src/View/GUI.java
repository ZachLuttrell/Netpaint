package View;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class GUI extends JFrame
{
	private JPanel paintPanel = new JPanel();
	private JPanel radioPanel = new JPanel();	
	private JPanel swatchPanel = new JPanel();

	private ButtonGroup radioButtons = new ButtonGroup();
	
	private JRadioButton line = new JRadioButton();
	private JRadioButton rectangle = new JRadioButton();
	private JRadioButton oval = new JRadioButton();
	private JRadioButton image = new JRadioButton();
	
	private static JColorChooser colorChooser;
	
	public static void main(String[] args)
	{
		new GUI().setVisible(true);
		// colorChooser = new JColorChooser();
	}
	
	public GUI()
	{
		layoutTheGUI();
		registerListeners();
	}
	
	private void registerListeners()
	{
		// TODO Auto-generated method stub
		
	}

	public void layoutTheGUI()
	{
		this.setTitle("NetPaint Client");
		this.setSize(900, 900);
		this.setLocation(0, 0);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setFocusable(true);
		
		paintPanel.setSize(900, 425);
		paintPanel.setLocation(0, 0);
		paintPanel.setBackground(Color.BLUE);
		paintPanel.setVisible(true);
		
		radioPanel.setSize(900, 50);
		radioPanel.setLocation(0, 425);
		radioPanel.setVisible(true);
		
		radioButtons.add(line);
		radioButtons.add(rectangle);
		radioButtons.add(oval);
		radioButtons.add(image);
		
		radioPanel.add(line);
		radioPanel.add(rectangle);
		radioPanel.add(oval);
		radioPanel.add(image);
		
		swatchPanel.setSize(900, 425);
		swatchPanel.setLocation(0, 475);
		swatchPanel.setVisible(true);
		// swatchPanel.add(colorChooser);
		
		this.add(paintPanel);
		this.add(radioPanel);
		this.add(swatchPanel);
		
		line.setText("Line");
		rectangle.setText("Rectangle");
		oval.setText("Circle");
		image.setText("Image");
		//TEst comments
	}
}
