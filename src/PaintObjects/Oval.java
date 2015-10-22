package PaintObjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Oval extends PaintObject {

	private Ellipse2D.Double oval;
	
	public Oval(Graphics2D graphic, int startX, int startY, int endX, int endY, Color color){
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
		oval = new Ellipse2D.Double(startX, startY, width, height);
	}
	
	public void draw(){
		graphic.setColor(getColor());
		graphic.draw(oval);
	}
}
