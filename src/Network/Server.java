package Network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server
{
	public static final int SERVER_PORT = 9001;

	private static ServerSocket sock;
	private static List<ObjectOutputStream> clients = new ArrayList<ObjectOutputStream>();

	public static void main(String[] args) throws IOException
	{
		sock = new ServerSocket(SERVER_PORT);
		System.out.println("Server started on port " + SERVER_PORT);

		while (true)
		{
			// TODO 1: Accept a connection from the ServerSocket.
			Socket s = sock.accept();

			ObjectInputStream is = new ObjectInputStream(s.getInputStream());
			ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());

			// TODO 2: Save the output stream to our clients list so we can
			// broadcast to this client later
			clients.add(os);

			// TODO 3: Start a new ClientHandler thread for this client.
			ClientHandler clientHandler = new ClientHandler(is, clients);
			clientHandler.start();

			System.out.println("Accepted a new connection from " + s.getInetAddress());
		}
	}
}

class ClientHandler extends Thread
{

	ObjectInputStream input;
	List<ObjectOutputStream> clients;

	public ClientHandler(ObjectInputStream input, List<ObjectOutputStream> clients)
	{
		this.input = input;
		this.clients = clients;
	}

	@Override
	public void run()
	{
		while (true)
		{

			String s = null;

			// TODO 4: Read a String from the client
			try
			{
				s = (String) input.readObject();
				writeStringToClients(s);
			} catch (ClassNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void writeStringToClients(String s)
	{
		// TODO 5: Send a string to all clients in the client list
		for (ObjectOutputStream os : clients)
		{
			try
			{
				os.writeObject(s);
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// TODO (When you get the chance): Write a method that closes all the
	// resources of a ClientHandler and logs a message, and call it from every
	// place that a fatal error occurs in ClientHandler (the catch blocks that
	// you can't recover from).
}
