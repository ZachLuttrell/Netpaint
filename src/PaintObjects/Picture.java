package PaintObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class Picture extends PaintObject implements Serializable{

	// private Image image;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8565789827615064365L;

	public Picture(Graphics graphic, int startX, int startY, int endX, int endY, Color color){
		//Initialize fields
		//super.setGraphics(graphic);
		super.setStartX(startX);
		super.setStartY(startY);
		super.setEndX(endX);
		super.setEndY(endY);
		super.setColor(color);
		super.setShape(null);
		super.setObjectType(3);
		
		//Initialize image
		/*
		try {
			image = ImageIO.read(new File("Image/GratefulDeadSkeleton.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		super.setImage(image);
		*/
		drawMe();
	}
	
	public void drawMe()
	{
		Image image = null;
		try {
			image = ImageIO.read(new File("Image/GratefulDeadSkeleton.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// super.setImage(image);
	}
}
