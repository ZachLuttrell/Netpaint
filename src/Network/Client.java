package Network;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.sun.webkit.ThemeClient;

import Model.Canvas;
import PaintObjects.DrawingObjects;
import PaintObjects.PaintObject;
import View.GUI;

@SuppressWarnings("serial")
public class Client extends JFrame
{
	private static final String ADDRESS = "localhost";

	public Canvas clientCanvas;
	public GUI clientGUI;
	public DrawingObjects paintObject;
	
	public static void main(String[] args) throws UnknownHostException, IOException
	{
		Client theClient;
		theClient = new Client();
		theClient.clientGUI = new GUI(theClient);
		theClient.clientCanvas = theClient.clientGUI.getCanvas();
	}

	Socket socket;
	ObjectOutputStream oos;
	ObjectInputStream ois;

	public Client() throws UnknownHostException, IOException
	{
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
	
	public void updateServerCanvas(DrawingObjects inputObject){
		paintObject = inputObject;
		System.out.println("Trying to write the server's object");
		System.out.println(paintObject);
		try {
			oos.writeObject(paintObject);
			System.out.println("Object has been written to server");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Object didn't make it to server");
			e.printStackTrace();
		}
	}

	private class ServerListener extends Thread
	{
		@Override
		public void run()
		{
			while(true)
			{
			try {
				System.out.println("Trying to read a object from the server");
				paintObject = (DrawingObjects) ois.readObject();
				System.out.println("Read it in succesfully");
				clientGUI.clientDrawing(paintObject);
				System.out.println("Should be drawn?");
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
