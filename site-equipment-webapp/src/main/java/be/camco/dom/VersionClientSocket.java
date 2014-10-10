package be.camco.dom;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class VersionClientSocket {
	private final int PORT = 8282;
	private final String HOSTNAME = "localhost";
	private String version;
	Socket socketClient;
	String versieControle;
	
	public VersionClientSocket (String version){
		this.version=version;
	}
	
	public void connect() throws UnknownHostException, IOException{
		System.out.println("Attempting to connect to: "+HOSTNAME+": "+PORT);
		socketClient = new Socket(HOSTNAME, PORT);
		System.out.println("Connection established");
	}
	
	public void sendRequest() throws IOException {
			OutputStream os = socketClient.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(new String(version));
			
	}
	
	public void readResponse() throws IOException, ClassNotFoundException{
		InputStream is = socketClient.getInputStream();
		ObjectInputStream ois = new ObjectInputStream(is);
		versieControle=((String)ois.readObject());
		System.out.println(versieControle);
		ois.close();
		is.close();
		
	}
	
	public static void main (String[] args){
		//Creating a VersionClientSocket
		VersionClientSocket client = new VersionClientSocket("0.2");
		
		try{
			//trying to establish connection to the server
			client.connect();
			client.sendRequest();
			client.readResponse();
			//if successful, read response from server
			//client.readResponse();
		} catch (UnknownHostException ex){
			System.err.println("Host unknown. Cannot establish connection.");
		} catch (IOException ex){
			System.err.println("Cannot establish connection. Server may not be up."+ex.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.println("Cannot find class."+ e.getMessage()+".");
			e.printStackTrace();
		}
	}
	
}
