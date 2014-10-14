package be.camco.dom;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class VersionClientSocket {
	Hulp hulp = new Hulp();
	private final int PORT = 8383;
	private final String HOSTNAME = "localhost";
	//private String version;
	Socket client;
	String versionCheck;
	
//	public VersionClientSocket (String version){
//		this.version=version;
//	}
	
	public void connect() throws UnknownHostException, IOException{
		System.out.println("Attempting to connect to: "+HOSTNAME+": "+PORT);
		client = new Socket(HOSTNAME, PORT);
		System.out.println("Connection established");
	}
	
	public void versionRequest() throws IOException {
			OutputStream os = client.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(new String("version"));
			//oos.writeObject(new String(version));
			System.out.println("sendRequest versionclient");
			
	}
	
	public void readVersionResponse() throws IOException, ClassNotFoundException{
		InputStream is = client.getInputStream();
		ObjectInputStream ois = new ObjectInputStream(is);
		versionCheck=((String)ois.readObject());
		System.out.println(versionCheck);
		ois.close();
		is.close();
		System.out.println("readResponse version client");
		
	}
	
	public static void main (String[] args){
		//Creating a VersionClientSocket
		VersionClientSocket client = new VersionClientSocket();
		
		try{
			//trying to establish connection to the server
			client.connect();
			client.versionRequest();
			client.readVersionResponse();
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
