package PaintObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;

public class Oval extends PaintObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4820349760586267402L;
	// private Shape oval;
	int height, width;
	
	public Oval(Graphics graphic, int startX, int startY, int endX, int endY, Color color){
		//Initialize fields
		//super.setGraphics(graphic);
		super.setStartX(startX);
		super.setStartY(startY);
		super.setEndX(endX);
		super.setEndY(endY);
		super.setColor(color);
		super.setImage(null);
		super.setObjectType(2);
		
		//Initialize shape
		
		height = endY - startY;
		width = endX - startX;
	}
	
	public void drawMe()
	{
		Shape oval;
		
		oval = new Ellipse2D.Double(startX, startY, width, height);
		super.setShape(oval);
	}
}
