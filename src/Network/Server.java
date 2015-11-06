package Network;

import java.awt.image.BufferedImage;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Callable;

import javax.imageio.ImageIO;

import Model.Canvas;
import PaintObjects.DrawingObjects;
import PaintObjects.Line;
import PaintObjects.Oval;
import PaintObjects.PaintObject;
import PaintObjects.Picture;
import PaintObjects.Rectangle;

public class Server
{
	public static final int SERVER_PORT = 9001;

	private static ServerSocket sock;
	private static Canvas serverCanvas;
	private static List<ObjectOutputStream> clients = new ArrayList<ObjectOutputStream>();
	public DrawingObjects drawObject;
	public static PaintObject paintObject;
	public static Vector<PaintObject> mainObjectList;
	
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
			ClientHandler clientHandler = new ClientHandler(is, (ArrayList<ObjectOutputStream>) clients, os);
			clientHandler.start();
			
			
			System.out.println("Accepted a new connection from " + s.getInetAddress());
		}
	}
}

class ClientHandler<T> extends Thread
{
	ObjectInputStream input;
	PaintObject drawObject;
	ArrayList<ObjectOutputStream> clients;
	Vector<PaintObject> objectList;
	int objectType;
	ObjectOutputStream output;
	
	public ClientHandler(ObjectInputStream input, ArrayList<ObjectOutputStream> clientList, ObjectOutputStream theOS)
	{
		clients = clientList;
		this.input = input;
		this.output = theOS;
		System.out.println("Intialized the clients, input, paintObject for the client handler");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void run()
	{
		updateClientCanvases();
		while (true)
		{
			//Try and read the object list in then update all of the clients on the server.
			try {
				
				System.out.println("Waiting to read the object in");
				
				//Read the object list in
				objectList = (Vector<PaintObject>) input.readObject();
								
				System.out.println("The object was successfully read in");

			} catch (EOFException e) {
				System.out.println("EOFException terminating this client");
				int i = 0;
				for(ObjectOutputStream os : clients)
				{
					if(os == output)
					{
						clients.remove(i);
						System.out.println("Found the client and removed it");
						break;
					}
					++i;
				}
				return;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			//Update the canvases 
			updateClientCanvases();
			
		}
	}

	public void updateClientCanvases() {
		System.out.println("Trying to write the server's objectList to each client");
		//Update each clients list of objects to draw
		if(objectList != null)
		{
			for(ObjectOutputStream os : clients){
				try {	
					//Reset output stream
					os.reset();
					
					//Try and write the list out
					os.writeObject(objectList);
					System.out.println("Server objectList has been written to a client");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
