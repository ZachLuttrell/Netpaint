package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
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
	
	private JColorChooser colorChooser = new JColorChooser();
	
	public static void main(String[] args)
	{
		new GUI().setVisible(true);
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
		GridBagConstraints cont;
		cont = new GridBagConstraints();
		
		cont.fill = GridBagConstraints.HORIZONTAL;
		cont.ipady = 100;
		cont.weightx = 1;
		cont.weighty = 2;
		cont.gridx = 0;
		cont.gridy = 0;
		
		this.setTitle("NetPaint Client");
		this.setSize(900, 900);
		this.setLocation(0, 0);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		this.setVisible(true);
		this.setFocusable(true);
		this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		paintPanel.setPreferredSize(new Dimension(900, 425));
		// paintPanel.setLocation(0, 0);
		paintPanel.setBackground(Color.BLUE);
		paintPanel.setVisible(true);
		
		radioPanel.setPreferredSize(new Dimension(900, 50));
		// radioPanel.setLocation(0, 425);
		radioPanel.setVisible(true);
		
		radioButtons.add(line);
		radioButtons.add(rectangle);
		radioButtons.add(oval);
		radioButtons.add(image);
		
		radioPanel.add(line);
		radioPanel.add(rectangle);
		radioPanel.add(oval);
		radioPanel.add(image);
		
		swatchPanel.setPreferredSize(new Dimension(900, 425));
		swatchPanel.setVisible(true);
		swatchPanel.add(colorChooser);
		colorChooser.setPreferredSize(new Dimension(900, 300));
		
		this.add(paintPanel);
		this.add(radioPanel);
		this.add(swatchPanel);
		
		line.setText("Line");
		rectangle.setText("Rectangle");
		oval.setText("Circle");
		image.setText("Image");
		
		validate();
	}
		
	private class objectCreation implements MouseListener
	{
		private Point startPoint = null;
		private Point endPoint = null;
		@Override
		public void mouseClicked(MouseEvent e)
		{
			
		}

		@Override
		public void mousePressed(MouseEvent e)
		{
			if(radioButtons.isSelected((ButtonModel) line))
			{
				startPoint.setLocation(e.getX(), e.getY());
			}
			else if(radioButtons.isSelected((ButtonModel) rectangle))
			{
				startPoint.setLocation(e.getX(), e.getX());
			}
			else if(radioButtons.isSelected((ButtonModel) oval))
			{
				startPoint.setLocation(e.getX(), e.getY());
			}
			else if(radioButtons.isSelected((ButtonModel) image))
			{
				startPoint.setLocation(e.getX(), e.getY());
			}
		}

		@Override
		public void mouseReleased(MouseEvent e)
		{
			if(radioButtons.isSelected((ButtonModel) line))
			{
				endPoint.setLocation(e.getX(), e.getY());
			}
		}

		@Override
		public void mouseEntered(MouseEvent e)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e)
		{
			// TODO Auto-generated method stub
			
		}
	}
}
