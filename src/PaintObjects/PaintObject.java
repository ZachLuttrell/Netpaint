package PaintObjects;
import java.awt.Color;
import java.awt.Graphics2D;

public abstract class PaintObject {
	
	// FIELDS
	protected Graphics2D graphic;
	protected int startX, startY, endX, endY;
	protected Color objectColor;
	
	// GETTERS
	public Graphics2D getGraphic(){ return graphic; }
	public int getStartX(){ return startX; }
	public int getStartY(){ return startY; }
	public int getEndX(){ return endX; }
	public int getEndY(){ return endY; }
	public Color getColor(){ return objectColor; }
	
	// SETTERS
	public void setGraphics(Graphics2D g){ graphic = g; }
	public void setStartX(int x){ startX = x; }
	public void setStartY(int y){ startY = y; }
	public void setEndX(int x){ endX = x; }
	public void setEndY(int y){ endY = y; }
	public void setColor(Color c){ objectColor = c; }
	
}
