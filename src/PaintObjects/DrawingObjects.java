package PaintObjects;

import java.io.Serializable;

public class DrawingObjects implements Serializable
{
	int type;
	Line line;
	Rectangle rectangle;
	Oval oval;
	Picture picture;
	
	public DrawingObjects(Line theLine)
	{
		type = 0;
		line = theLine;
	}
	
	public DrawingObjects(Rectangle theRectangle)
	{
		type = 1;
		rectangle = theRectangle;
	}
	
	public DrawingObjects(Oval theOval)
	{
		type = 2;
		oval = theOval;
	}
	
	public DrawingObjects(Picture thePicture)
	{
		type = 3;
		picture = thePicture;
	}
	
	public int getType()
	{
		return type;
	}
}
