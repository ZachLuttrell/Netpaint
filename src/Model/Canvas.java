package Model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import PaintObjects.PaintObject;
import View.GUI;

public class Canvas extends JPanel {
	
	//FIELDS
	private GUI gui;
	private ArrayList<PaintObject> objectsToDraw; //Not sure if it's necessary to add it but it could make things easier later
	private Graphics graphics;
	
	public Canvas(GUI inputGUI){
		gui = inputGUI;
		objectsToDraw = new ArrayList<PaintObject>();
	}
	
	public void addShape(PaintObject object){
		//Add to the list of objects
		objectsToDraw.add(object);
		
		//Update the drawing
		repaint();
	}
	
	public ArrayList<PaintObject> getPaintObjects(){ return objectsToDraw; }
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if(!objectsToDraw.isEmpty()){
			for(PaintObject p : objectsToDraw){
				if(p.getObjectType() < 3){
				
					//SHAPE
					g2.setColor(p.getColor());
					g2.draw(p.getShape());
					g2.fill(p.getShape());
				} else {
				
					//IMAGE
					g2.drawImage(p.getImage(), p.getStartX(), p.getStartY(), p.getEndX(), p.getEndY(), null);
				}
			}
		}
	}
}
