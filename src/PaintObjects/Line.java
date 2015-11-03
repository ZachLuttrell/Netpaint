package PaintObjects;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;
<<<<<<< HEAD
import java.io.Serializable;

public class Line extends PaintObject implements Serializable{
=======

public class Line extends PaintObject {
>>>>>>> 2772d73c871168fbcee43f643719d40c3ac6aeb3
	
	private Shape line;
	
	public Line(Graphics graphics, int startX, int startY, int endX, int endY, Color color){
		//Initialize fields
		super.setGraphics((Graphics2D)graphics);
		super.setStartX(startX);
		super.setStartY(startY);
		super.setEndX(endX);
		super.setEndY(endY);
		super.setColor(color);
		super.setImage(null);
		super.setObjectType(0);
		
		//Initialize shape
		line = new Line2D.Double(startX, startY, endX, endY);
		super.setShape(line);
	}
}
