package Network;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.sun.webkit.ThemeClient;

import Model.Canvas;
import PaintObjects.DrawingObjects;
import PaintObjects.Line;
import PaintObjects.Oval;
import PaintObjects.PaintObject;
import PaintObjects.Picture;
import PaintObjects.Rectangle;
import View.GUI;

@SuppressWarnings("serial")
public class Client extends JFrame
{
	private static final String ADDRESS = "localhost";

	public Canvas clientCanvas;
	public GUI clientGUI;
	public PaintObject paintObject;
	private static Client theClient;
	
	public static void main(String[] args) throws UnknownHostException,
					IOException
	{
		theClient = new Client();
	}

	Socket socket;
	ObjectOutputStream oos;
	ObjectInputStream ois;

	public Client() throws UnknownHostException, IOException
	{
		clientGUI = new GUI(this);
		clientCanvas = clientGUI.getCanvas();
		openConnection();

		// TODO 7: Start a new ServerListener thread
		ServerListener s = new ServerListener();
		s.start();
		System.out.println("Server Listener Started");
	}
	
	private void openConnection()
	{
		/* Our server is on our computer, but make sure to use the same port. */
		try
		{
			// TODO 6: Connect to the Server
			socket = new Socket(ADDRESS, Server.SERVER_PORT);

			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void updateServerCanvas(Vector<PaintObject> vector){
		
		//Try and write the object list out
		try {
			oos.reset();
//			if(vector.get(vector.size() - 1).getObjectType() != 3)
//			{
				oos.writeObject(vector);
//			}
//			else
//			{
//				ImageIO.write((RenderedImage)vector.get(vector.size() - 1).getImage(), "jpg", oos);
//			}
			System.out.println("object list has been written to the server");
			System.out.println("client writing to server, shape: " + vector.get(0));
			System.out.println("client writing to server, end x point: " + vector.get(0).getEndX());
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			System.out.println("Object didn't make it to server");
			e.printStackTrace();
			
		}
	}

	//This listener listens for objects being written in and then attempts to draw them to the canvas
	private class ServerListener extends Thread
	{
		
		public Vector<PaintObject> objectList;
		
		@SuppressWarnings("unchecked")
		@Override
		public void run()
		{
			//Once started run continuously
			while(true)
			{
				//If an object list is being written in, read it then draw it to the canvas
				try {
					
					System.out.println("Trying to read an objectList from the server");
					
					//Read the objectList in then update this clients list
					objectList = (Vector<PaintObject>) ois.readObject();
					clientGUI.getCanvas().setObjectList(objectList);
					//System.out.println("Object list elements: " + objectList.firstElement().getShape());
					//DEBUG STUFF
					System.out.println("Read it in succesfully");
					//System.out.println("drawObjects coordinates: " + paintObject.getStartX() + ", " + paintObject.getStartY() + ", " + paintObject.getEndX() + ", " + paintObject.getEndY());
					//System.out.println(paintObject.getShape());
					
					//Draw the object
					//clientGUI.clientDrawing(paintObject);
					
					//Repaint to show the new object list
					System.out.println("Should be drawn?");
					//clientGUI.getCanvas().repaint();
					System.out.println("Finished repainting");
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
