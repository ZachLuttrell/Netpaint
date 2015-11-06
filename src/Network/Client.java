package Network;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.RenderedImage;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
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
	public static Boolean isOpen;
	
	public static void main(String[] args) throws UnknownHostException, IOException
	{
		Client theClient;
		theClient = new Client();
		if(isOpen)
		{
			theClient.clientGUI = new GUI(theClient);
			theClient.clientCanvas = theClient.clientGUI.getCanvas();
		}
		else
		{
			System.out.println("No server is available, closing client");
		}
	}

	Socket socket;
	ObjectOutputStream oos;
	ObjectInputStream ois;

	public Client() throws UnknownHostException, IOException
	{
		isOpen = openConnection();

		if(isOpen)
		{
			// TODO 7: Start a new ServerListener thread
			ServerListener s = new ServerListener();
			s.start();
	
			System.out.println("Server Listener Started");
		}
		else
		{
			System.out.println("Unable to open connection");
		}
	}

	private boolean openConnection()
	{
		/* Our server is on our computer, but make sure to use the same port. */
		try
		{
			// TODO 6: Connect to the Server
			socket = new Socket(ADDRESS, Server.SERVER_PORT);

			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			return true;
		} catch (ConnectException e) {
			return false;
		} catch (IOException e)
		{
			e.printStackTrace();
			return false;
		} 
	}

	public void updateServerCanvas(Vector<PaintObject> vector)
	{

		// Try and write the object list out
		try
		{
			oos.reset();
			oos.writeObject(vector);

		} catch (IOException e)
		{

			// TODO Auto-generated catch block
			System.out.println("Object didn't make it to server");
			e.printStackTrace();

		}
	}

	// This listener listens for objects being written in and then attempts to
	// draw them to the canvas
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
					if(objectList != null)
					{
						clientGUI.getCanvas().setObjectList(objectList);

						//DEBUG STUFF
						System.out.println("Read it in succesfully");
						
						//Repaint to show the new object list
					}
					else
					{
						System.out.println("There was no Objects to paint");
					}
				} catch (EOFException e) {
					clientClose();
					System.out.println("Closing client, server was disconnected");
					clientGUI.setVisible(false); // Hide the window
					clientGUI.dispose(); // Destroy the window
					return;
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

	public void clientClose()
	{
		try
		{
			oos.close();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try
		{
			ois.close();

		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
