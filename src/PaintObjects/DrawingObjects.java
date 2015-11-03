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
		type = line.getObjectType();
		line = theLine;
	}
	
	public DrawingObjects(Rectangle theRectangle)
	{
		type = rectangle.getObjectType();
		rectangle = theRectangle;
	}
	
	public DrawingObjects(Oval theOval)
	{
		type = oval.getObjectType();
		oval = theOval;
	}
	
	public DrawingObjects(Picture thePicture)
	{
		type = picture.getObjectType();
		picture = thePicture;
	}
}
