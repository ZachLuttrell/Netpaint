package PaintObjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Rectangle extends PaintObject {
	
	private Rectangle2D.Double rectangle;
	
	public Rectangle(Graphics2D graphic, int startX, int startY, int endX, int endY, Color color){
		//Initialize fields
		setGraphics(graphic);
		setStartX(startX);
		setStartY(startY);
		setEndX(endX);
		setEndY(endY);
		setColor(color);
		
		//Initialize shape
		int height, width;
		height = endY - startY;
		width = endX - startX;
		rectangle = new Rectangle2D.Double(startX, startY, width, height);
	}
	
	public void draw(){
		graphic.setColor(getColor());
		graphic.draw(rectangle);
	}
	
}
