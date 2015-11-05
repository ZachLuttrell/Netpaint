package PaintObjects;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.io.Serializable;

public class Line extends PaintObject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5121945836975584053L;
	
	public Line(Graphics graphics, int startX, int startY, int endX, int endY, Color color){
		//Initialize fields
		//super.setGraphics(graphics);
		super.setStartX(startX);
		super.setStartY(startY);
		super.setEndX(endX);
		super.setEndY(endY);
		super.setColor(color);
		super.setImage(null);
		super.setObjectType(0);
		
		//Initialize shape
		drawMe();
		}
	
	public void drawMe()
	{
		
		Shape line = new Line2D.Double(startX, startY, endX, endY);
		
		super.setShape(line);
		
	}
}
