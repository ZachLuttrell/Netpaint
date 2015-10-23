package PaintObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Picture extends PaintObject {

	private Image image;
	
	public Picture(Graphics graphic, int startX, int startY, int endX, int endY, Color color){
		//Initialize fields
		setGraphics((Graphics2D)graphic);
		setStartX(startX);
		setStartY(startY);
		setEndX(endX);
		setEndY(endY);
		setColor(color);
		setShape(null);
		setObjectType(3);

		//Initialize image
		try {
			image = ImageIO.read(new File("Image/GratefulDeadSkeleton.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void draw(){
		graphic.drawImage(image, startX, startY, endX, endY, null);
		
		//graphic.draw((Shape) image); //Other idea
	}
	
}
