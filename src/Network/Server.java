package Network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import Model.Canvas;

public class Server
{
	public static final int SERVER_PORT = 9001;

	private static ServerSocket sock;
	private static List<ObjectOutputStream> clients = new ArrayList<ObjectOutputStream>();
	public static Canvas serverCanvas;

	public static void main(String[] args) throws IOException
	{
		sock = new ServerSocket(SERVER_PORT);
		System.out.println("Server started on port " + SERVER_PORT);
		serverCanvas = new Canvas(null);
		
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
			ClientHandler clientHandler = new ClientHandler(is, serverCanvas, (ArrayList<ObjectOutputStream>) clients);
			clientHandler.start();
			
			System.out.println("Accepted a new connection from " + s.getInetAddress());
			
		}
	}

	public void updateServerCanvas() {
		// TODO Auto-generated method stub
		
	}
}

class ClientHandler extends Thread
{

	ObjectInputStream input;
	Canvas canvas;
	ArrayList<ObjectOutputStream> clients;

	public ClientHandler(ObjectInputStream input, Canvas canvas, ArrayList<ObjectOutputStream> clientList)
	{
		clients = clientList;
		this.input = input;
		this.canvas = canvas;
	}

	@Override
	public void run()
	{
		while (true)
		{
			try {
				canvas = (Canvas) input.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			updateClientCanvases();
		}
	}

	private void updateClientCanvases() {
		// TODO Auto-generated method stub
		System.out.println("Trying to write the server's canvas to each client");
		for(ObjectOutputStream os : clients){
			try {
				os.writeObject(canvas);
				System.out.println("Server canvas has been written to a client");
			} catch (IOException e) {
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
