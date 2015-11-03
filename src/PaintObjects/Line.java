package PaintObjects;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.io.Serializable;

public class Line extends PaintObject implements Serializable{
	
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
