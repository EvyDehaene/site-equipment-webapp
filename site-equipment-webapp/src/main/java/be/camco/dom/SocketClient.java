package be.camco.dom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {
	
	private String hostname;
	private int port;
	Socket socketClient;
	
	public SocketClient (String hostname, int port){
		this.hostname=hostname;
		this.port=port;
	}
	
	public void connect() throws UnknownHostException, IOException{
		System.out.println("Attempting to connect to "+hostname+":"+port);
		socketClient = new Socket(hostname, port);
		System.out.println("Connection established");
	}
	
	public void readResponse() throws IOException{
		String userInput;
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
		
		System.out.println("Response from server:");
		while((userInput = stdIn.readLine())!= null){
			System.out.println(userInput);
		}
	}
	
	public static void main(String[] args){
		//Creating a SocketClient object
		SocketClient client = new SocketClient("localhost", 9990);
		try{
			//trying to establish connection to server
			client.connect();
			//if succesful, read response from server
			client.readResponse();
		} catch (UnknownHostException ex){
			System.err.println("Host unknown. Cannot establish connection");
		} catch (IOException ex){
			System.err.println("Cannot establish connection. Server may not be up."+ex.getMessage());
		}
	}

}
