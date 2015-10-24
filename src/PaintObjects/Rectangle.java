package PaintObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class Rectangle extends PaintObject {
	
	private Shape rectangle;
	
	public Rectangle(Graphics graphic, int startX, int startY, int endX, int endY, Color color){
		//Initialize fields
		super.setGraphics((Graphics2D)graphic);
		super.setStartX(startX);
		super.setStartY(startY);
		super.setEndX(endX);
		super.setEndY(endY);
		super.setColor(color);
		super.setImage(null);
		super.setObjectType(1);
		
		//Initialize shape
		int height, width;
		height = endY - startY;
		width = endX - startX;
		rectangle = new Rectangle2D.Double(startX, startY, width, height);
		super.setShape(rectangle);
		
	}	
}
