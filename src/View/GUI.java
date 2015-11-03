package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import Model.Canvas;
import Network.Client;
import Network.Server;
import PaintObjects.Line;
import PaintObjects.Oval;
import PaintObjects.PaintObject;
import PaintObjects.Picture;
import PaintObjects.Rectangle;

public class GUI extends JFrame
{
	private Canvas paintPanel = new Canvas(this);
	private JPanel radioPanel = new JPanel();	
	private JPanel swatchPanel = new JPanel();

	private ButtonGroup radioButtons = new ButtonGroup();
	
	private JRadioButton line = new JRadioButton();
	private JRadioButton rectangle = new JRadioButton();
	private JRadioButton oval = new JRadioButton();
	private JRadioButton image = new JRadioButton();
	
	public JColorChooser colorChooser = new JColorChooser();
	
	public JScrollPane scrollPane = new JScrollPane(paintPanel);
	
	public Point startPoint = new Point();
	public Point endPoint = new Point();
	
	public boolean drawing = false;
	
	public Client client;
	public Server server;
	
	// public static void main(String[] args)
	// {
	//  	new GUI("").setVisible(true);
	// }
	
	public GUI(Client theClient)
	{
	client = theClient;
	layoutTheGUI();
	registerListeners();
	}
	
	private void registerListeners()
	{
		paintPanel.addMouseListener(new objectCreation());
		paintPanel.addMouseMotionListener(new objectUpdater());
	}

	public void layoutTheGUI()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    int screenSizeWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	    
		GridBagConstraints cont;
		cont = new GridBagConstraints();
		
		cont.fill = GridBagConstraints.HORIZONTAL;
		cont.ipady = 100;
		cont.weightx = 1;
		cont.weighty = 2;
		cont.gridx = 0;
		cont.gridy = 0;

	    this.setTitle("NetPaint Client");
		this.setBounds(0,0,screenSize.width, screenSize.height);
		this.setLocation(0, 0);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		this.setVisible(true);
		this.setFocusable(true);
		this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		paintPanel.setPreferredSize(new Dimension(2048, 1024));
		paintPanel.setBackground(Color.WHITE);
		paintPanel.setVisible(true);
		
		scrollPane.setPreferredSize(new Dimension(screenSizeWidth, 425));
		scrollPane.setViewportView(paintPanel);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		radioPanel.setPreferredSize(new Dimension(screenSizeWidth, 50));
		radioPanel.setVisible(true);
		
		radioButtons.add(line);
		radioButtons.add(rectangle);
		radioButtons.add(oval);
		radioButtons.add(image);
		
		radioPanel.add(line);
		radioPanel.add(rectangle);
		radioPanel.add(oval);
		radioPanel.add(image);
		
		swatchPanel.setPreferredSize(new Dimension(screenSizeWidth, 425));
		swatchPanel.setVisible(true);
		swatchPanel.add(colorChooser);
		colorChooser.setPreferredSize(new Dimension(screenSizeWidth, 300));
		
		// this.add(paintPanel);
		this.add(scrollPane);
		this.add(radioPanel);
		this.add(swatchPanel);
		
		
		line.setText("Line");
		rectangle.setText("Rectangle");
		oval.setText("Circle");
		image.setText("Image");
		
		// Set the line to be default
		line.setSelected(true);
		
		// Set the color, BLACK to be default
		colorChooser.setColor(0, 0, 0);
		
		validate();
	}
	
	private class objectUpdater implements MouseMotionListener {

		private ArrayList<PaintObject> objects;
		private PaintObject object;
		
		@Override
		public void mouseDragged(MouseEvent e) {}

		@Override
		public void mouseMoved(MouseEvent e) {
			if(drawing){
				endPoint.setLocation(e.getX(), e.getY());
				objects = paintPanel.getPaintObjects();
				object = objects.get(objects.size()-1);
				
				switch(object.getObjectType()){
				case 0:
					object.setShape(new Line(paintPanel.getGraphics(), (int)startPoint.getX(), (int)startPoint.getY(), (int)endPoint.getX(), (int)endPoint.getY(), colorChooser.getColor()).getShape());
					break;
				case 1:
					object.setShape(new Rectangle(paintPanel.getGraphics(), (int)startPoint.getX(), (int)startPoint.getY(), (int)endPoint.getX(), (int)endPoint.getY(), colorChooser.getColor()).getShape());
					break;
				case 2:
					object.setShape(new Oval(paintPanel.getGraphics(), (int)startPoint.getX(), (int)startPoint.getY(), (int)endPoint.getX(), (int)endPoint.getY(), colorChooser.getColor()).getShape());
					break;
				case 3:
					object.setEndX((int)endPoint.getX()-(int)startPoint.getX());
					object.setEndY((int)endPoint.getY()-(int)startPoint.getY());
					break;
				}
				
				paintPanel.repaint();
			}
		}
		
	}
	
	public Canvas getCanvas()
	{
		return paintPanel;
	}
	
	private class objectCreation implements MouseListener
	{
		
		@Override
		public void mouseClicked(MouseEvent e)
		{
			if(!drawing){
				//Assign start coordinates
				drawing = true;
				startPoint.setLocation(e.getX(), e.getY());

				
				if(line.isSelected())
				{
					paintPanel.addShape(new Line(paintPanel.getGraphics(), (int)startPoint.getX(), (int)startPoint.getY(), (int)startPoint.getX(), (int)startPoint.getY(), colorChooser.getColor()));
				}
				else if(rectangle.isSelected())
				{
					paintPanel.addShape(new Rectangle(paintPanel.getGraphics(), (int)startPoint.getX(), (int)startPoint.getY(), (int)startPoint.getX(), (int)startPoint.getY(), colorChooser.getColor()));
				}
				else if(oval.isSelected())
				{
					paintPanel.addShape(new Oval(paintPanel.getGraphics(), (int)startPoint.getX(), (int)startPoint.getY(), (int)startPoint.getX(), (int)startPoint.getY(), colorChooser.getColor()));
				}
				else if(image.isSelected())
				{
					paintPanel.addShape(new Picture(paintPanel.getGraphics(), (int)startPoint.getX(), (int)startPoint.getY(), 0, 0, colorChooser.getColor()));
				}
				
			}
			else // Second Click
			{
				drawing = false;
				System.out.println("Second Click : Try and update the server canvas");
				updateServerCanvas(paintPanel.getPaintObjects());
			}
		}

		private void updateServerCanvas(Object inputPO) {
			// TODO Auto-generated method stub
			System.out.println("Trying to push the canvas");
			System.out.println(inputPO);
			// server.updateServerCanvas();
			client.updateServerCanvas(inputPO);
		}

		@Override
		public void mousePressed(MouseEvent e)
		{

		}

		@Override
		public void mouseReleased(MouseEvent e)
		{

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
