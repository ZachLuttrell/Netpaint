package PaintObjects;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;

public class Line extends PaintObject {
	
	private Line2D.Double line;
	
	public Line(Graphics2D graphic, int startX, int startY, int endX, int endY, Color color){
		//Initialize fields
		setGraphics(graphic);
		setStartX(startX);
		setStartY(startY);
		setEndX(endX);
		setEndY(endY);
		setColor(color);
		
		//Initialize shape
		line = new Line2D.Double(startX, startY, endX, endY);
	}
	
	public void draw(){
		graphic.setColor(getColor());
		graphic.draw(line);
	}
}
