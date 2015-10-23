package PaintObjects;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;

public class Line extends PaintObject {
	
	private Shape line;
	
	public Line(Graphics graphics, int startX, int startY, int endX, int endY, Color color){
		//Initialize fields
		setGraphics((Graphics2D)graphics);
		setStartX(startX);
		setStartY(startY);
		setEndX(endX);
		setEndY(endY);
		setColor(color);
		setImage(null);
		setObjectType(0);
		
		//Initialize shape
		line = new Line2D.Double(startX, startY, endX, endY);
		setShape(line);
	}
}
