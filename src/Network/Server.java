package Network;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

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
	public static Vector<PaintObject> objectList;
	public static ObjectListHolder objectListHolder;
	
	public static void main(String[] args) throws IOException
	{
		objectListHolder = new ObjectListHolder();
		System.out.println("Creating objectListHolder " + objectListHolder);
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
			System.out.println("Passing in objectListHolder " + objectListHolder);
			ClientHandler clientHandler = new ClientHandler(is, (ArrayList<ObjectOutputStream>) clients, objectListHolder);
			clientHandler.start();
			
			System.out.println("Accepted a new connection from " + s.getInetAddress());
			
		}
	}

	public static void updateObjectList(Vector<PaintObject> theObjectList)
	{
		objectList = theObjectList;
	}
	public void updateServerCanvas() {
		// TODO Auto-generated method stub
		
	}
}

class ObjectListHolder
{
	Vector<PaintObject> objectList;
	
	public void updateObjectList(Vector<PaintObject> theObjectList)
	{
		objectList = theObjectList;
	}
}

class ClientHandler extends Thread
{

	ObjectInputStream input;
	PaintObject drawObject;
	ArrayList<ObjectOutputStream> clients;
	Vector<PaintObject> objectList;
	ObjectListHolder objectListHolder;
	int objectType;
	
	public ClientHandler(ObjectInputStream input, ArrayList<ObjectOutputStream> clientList, ObjectListHolder objectListHolder)
	{
		clients = clientList;
		this.input = input;
		this.objectListHolder = objectListHolder;
		this.objectList = objectListHolder.objectList;
		System.out.println("Intialized the clients, input, paintObject for the client handler");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void run()
	{
		//Update the canvases 
		updateClientCanvases();
		while (true)
		{
			//Try and read the object list in then update all of the clients on the server.
			try {
				
				System.out.println("Waiting to read the object in");
				
				//Read the object list in

				objectList = (Vector<PaintObject>) input.readObject();
				System.out.println("objectListHolder = " + objectListHolder);
				System.out.println("objectList = " + objectList);
				objectListHolder.updateObjectList(objectList);

				// System.out.println(((Vector<PaintObject>) input.readObject()).get(0).getEndX());
				System.out.println("Server reading it in: " + objectList.get(0));
				System.out.println("Server read in object end x point: " + objectList.get(0).getEndX());
				/*int objectType = (int) input.readObject();
				switch(objectType){
				case 0:
					drawObject = (Line) input.readObject();
					break;
				case 1:
					drawObject = (Rectangle) input.readObject();
					break;
				case 2:
					drawObject = (Oval) input.readObject();
					break;
				case 3:
					drawObject = (Picture) input.readObject();
				}*/
				
				//DEBUG STUFF
				System.out.println("The object was successfully read in");
				//System.out.println(drawObject.getShape());
				//System.out.println("drawObjects coordinates: " + drawObject.getStartX() + ", " + drawObject.getStartY() + ", " + drawObject.getEndX() + ", " + drawObject.getEndY());

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

	private void updateClientCanvases() {
		System.out.println("Trying to write the server's objectList to each client");
		//Update each clients list of objects to draw
		System.out.println("Object list is: " + objectList);
		if (objectList != null)
		{
			for (ObjectOutputStream os : clients)
			{
				try
				{

					// Reset output stream
					os.reset();

					// Try and write the list out
					os.writeObject(objectList);
					System.out.println("Server objectList has been written to a client");
					System.out.println("The server outputting the shape: "
									+ objectList.get(0).getShape());
				}
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
