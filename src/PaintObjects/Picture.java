package PaintObjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Picture extends PaintObject {

	private Image image;
	
	public Picture(Graphics2D graphic, int startX, int startY, int endX, int endY, Color color){
		//Initialize fields
		setGraphics(graphic);
		setStartX(startX);
		setStartY(startY);
		setEndX(endX);
		setEndY(endY);
		setColor(color);
				
		//Initialize shape
		/*
		try {
			//image = ImageIO.read(new File());
		} catch (IOException e) {
			
		}*/
	}
	
	
	public void draw(){
		//graphic.drawImage(image);
	}
	
}
