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
		super.setGraphics((Graphics2D)graphic);
		super.setStartX(startX);
		super.setStartY(startY);
		super.setEndX(endX);
		super.setEndY(endY);
		super.setColor(color);
		super.setShape(null);
		super.setObjectType(3);
		
		//Initialize image
		try {
			image = ImageIO.read(new File("Image/GratefulDeadSkeleton.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		super.setImage(image);
	}
}
