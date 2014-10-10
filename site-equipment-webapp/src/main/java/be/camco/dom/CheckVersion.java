package be.camco.dom;

import java.io.IOException;
import java.net.UnknownHostException;

public class CheckVersion {
	//SocketClient client = new SocketClient("localhost", 8181);
	
	public static void main(String[] args){
		SocketClient socketClient = new SocketClient("localhost", 8181);
		try{
			//trying to establish connection to server
			socketClient.connect();
			//if succesful, read response from server
			socketClient.readResponse();
		} catch (UnknownHostException ex){
			System.err.println("Host unknown. Cannot establish connection");
		} catch (IOException ex){
			System.err.println("Cannot establish connection. Server may not be up."+ex.getMessage());
		}
	}
}
