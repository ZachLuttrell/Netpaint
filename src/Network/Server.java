package Network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import Model.Canvas;
import PaintObjects.DrawingObjects;

public class Server
{
	public static final int SERVER_PORT = 9001;

	private static ServerSocket sock;
	private static Canvas serverCanvas;
	private static List<ObjectOutputStream> clients = new ArrayList<ObjectOutputStream>();
	public static DrawingObjects paintObject;

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
			ClientHandler clientHandler = new ClientHandler(is, paintObject, (ArrayList<ObjectOutputStream>) clients);
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
	DrawingObjects paintObject;
	ArrayList<ObjectOutputStream> clients;

	public ClientHandler(ObjectInputStream input, DrawingObjects inputPaintObject, ArrayList<ObjectOutputStream> clientList)
	{
		clients = clientList;
		this.input = input;
		this.paintObject = inputPaintObject;
		System.out.println("Intialized the clients, input, paintObject for the client handler");
	}

	@Override
	public void run()
	{
		while (true)
		{
			try {
				paintObject = (DrawingObjects) input.readObject();
				System.out.println("The object was successfully read in");
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
		System.out.println("Trying to write the server's paintObject to each client");
		for(ObjectOutputStream os : clients){
			try {
				os.writeObject(paintObject);
				System.out.println("Server paintObject has been written to a client");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
