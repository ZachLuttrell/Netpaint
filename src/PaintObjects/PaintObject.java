package PaintObjects;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.io.Serializable;

public abstract class PaintObject {
	
	// FIELDS
	protected Graphics2D graphic;
	protected int startX, startY, endX, endY;
	protected Color objectColor;
	protected Shape shape;
	protected Image image;
	protected int objectType; // 0 = Line, 1 = Rectangle, 2 = Oval, 3 = Image
	
	// GETTERS
	public Graphics2D getGraphic(){ return graphic; }
	public int getStartX(){ return startX; }
	public int getStartY(){ return startY; }
	public int getEndX(){ return endX; }
	public int getEndY(){ return endY; }
	public Color getColor(){ return objectColor; }
	public Shape getShape(){ return shape; }
	public Image getImage(){ return image; }
	public int getObjectType() { return objectType; }
	
	// SETTERS
	public void setGraphics(Graphics2D g){ graphic = g; }
	public void setStartX(int x){ startX = x; }
	public void setStartY(int y){ startY = y; }
	public void setEndX(int x){ endX = x; }
	public void setEndY(int y){ endY = y; }
	public void setColor(Color c){ objectColor = c; }
	public void setShape(Shape s){ shape = s; }
	public void setImage(Image i){ image = i; }
	public void setObjectType(int type){ objectType = type; }
	//FUNCTIONALITY
	/*public void draw(){

	}*/
	
}
