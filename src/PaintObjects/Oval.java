package PaintObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class Oval extends PaintObject {

	private Shape oval;
	
	public Oval(Graphics graphic, int startX, int startY, int endX, int endY, Color color){
		//Initialize fields
		setGraphics((Graphics2D)graphic);
		setStartX(startX);
		setStartY(startY);
		setEndX(endX);
		setEndY(endY);
		setColor(color);
		setImage(null);
		setObjectType(2);
		
		//Initialize shape
		int height, width;
		height = endY - startY;
		width = endX - startX;
		oval = new Ellipse2D.Double(startX, startY, width, height);
		setShape(oval);
	}
}
