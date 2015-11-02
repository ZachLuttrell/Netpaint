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

import Model.Canvas;
import View.GUI;

@SuppressWarnings("serial")
public class Client extends JFrame
{

	private static final String ADDRESS = "localhost";
	public static Canvas clientCanvas;
	public static GUI clientGUI;

	public static void main(String[] args) throws UnknownHostException, IOException
	{
		clientGUI = new GUI();
		clientCanvas = new Canvas(clientGUI);
	}

	Socket socket;
	ObjectOutputStream oos;
	ObjectInputStream ois;

	public Client(String username) throws UnknownHostException, IOException
	{
		openConnection();

		// TODO 7: Start a new ServerListener thread
		ServerListener s = new ServerListener();
		s.start();
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
	
	public void updateServerCanvas(){
		System.out.println("Trying to write to the server's canvas");
		try {
			oos.writeObject(clientCanvas);
			System.out.println("Canvas has been written to server");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Canvas didn't make it to server");
			e.printStackTrace();
		}
	}

	private class ServerListener extends Thread
	{

		@Override
		public void run()
		{
			try {
				System.out.println("Trying to read a canvas from the server");
				clientCanvas = (Canvas) ois.readObject();
				System.out.println("canvas read from the server");
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
