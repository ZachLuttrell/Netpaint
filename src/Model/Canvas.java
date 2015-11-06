package Model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import PaintObjects.PaintObject;
import View.GUI;

public class Canvas extends JPanel {
	
	//FIELDS
	private GUI gui;
	private Vector<PaintObject> objectsToDraw; //Not sure if it's necessary to add it but it could make things easier later
	private Graphics graphics;
	
	public Canvas(GUI inputGUI){
		gui = inputGUI;
		objectsToDraw = new Vector<PaintObject>();
	}
	
	public void addShape(PaintObject object){
		
		//Add to the list of objects
		objectsToDraw.add(object);
		
		//Update the drawing
		repaint();
	}
	
	public Vector<PaintObject> getPaintObjects(){ return objectsToDraw; }
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		int height, width;
		// Graphics2D g2 = (Graphics2D) g;
		if(!objectsToDraw.isEmpty()){
			for(PaintObject p : objectsToDraw){
				if(p.getObjectType() == 0){
			
					System.out.println(p.getShape());
					//SHAPE
					System.out.println("Painting the objects");

					g.setColor(p.getColor());
					g.drawLine(p.getStartX(), p.getStartY(), p.getEndX(), p.getEndY());
				} 
				else if (p.getObjectType() == 1)
				{
					height = p.getEndY() - p.getStartY();
					width = p.getEndX() - p.getStartX();
					g.setColor(p.getColor());
					g.drawRect(p.getStartX(), p.getStartY(), width, height);
					g.fillRect(p.getStartX(), p.getStartY(), width, height);
				}
				else if (p.getObjectType() == 2)
				{
					g.setColor(p.getColor());
					height = p.getEndY() - p.getStartY();
					width = p.getEndX() - p.getStartX();
					g.drawOval(p.getStartX(), p.getStartY(), width, height);
					g.fillOval(p.getStartX(), p.getStartY(), width, height);				
				}
				else 
				{
					//IMAGE
					//g2.drawImage(p.getImage(), p.getStartX(), p.getStartY(), p.getEndX(), p.getEndY(), null);
					height = p.getEndY() - p.getStartY();
					width = p.getEndX() - p.getStartX();
					Image image = null;
					try {
						image = ImageIO.read(new File("Image/GratefulDeadSkeleton.jpg"));
					} catch (IOException e) {
						e.printStackTrace();
					}
					g.drawImage(image, p.getStartX(), p.getStartY(), width, height, null);
				}
			}
		}
	}

	public void setObjectList(Vector<PaintObject> objectList){ 
		objectsToDraw = objectList; 
		repaint();
		}
}
