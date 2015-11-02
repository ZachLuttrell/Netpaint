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

@SuppressWarnings("serial")
public class Client extends JFrame
{

	private static final String ADDRESS = "localhost";
	

	public static void main(String[] args) throws UnknownHostException, IOException
	{
		
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

	private class ServerListener extends Thread
	{

		@Override
		public void run()
		{
			// TODO 9: Repeatedly accept String objects from the server and add
			// them to our model.
			
		}
	}
}
