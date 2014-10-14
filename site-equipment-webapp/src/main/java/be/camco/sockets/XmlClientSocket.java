package be.camco.sockets;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class XmlClientSocket {
	private final int PORT = 8383;
	private final String HOSTNAME = "localhost";
	public Socket client;
	private int bytesRead;
	private int current = 0;
	final String FILE_TO_RECEIVE="C:/site-equipment-temp.xml";
	public final int FILE_SIZE = 6022386;

	public void connect() throws UnknownHostException, IOException{
		System.out.println("Attempting to connect to: "+HOSTNAME+": "+PORT);
		client = new Socket(HOSTNAME, PORT);
		System.out.println("Connection established.");
	}
	
	public void xmlRequest() throws IOException{
		OutputStream os = client.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(os);
		oos.writeObject(new String ("xml"));
		System.out.println("sendXmlRequest");
	}
	
	public void readXmlResponse() throws IOException, ClassNotFoundException{
		byte[] mybytearray = new byte[FILE_SIZE];
		InputStream is = client.getInputStream();
		FileOutputStream fos = new FileOutputStream(FILE_TO_RECEIVE);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		bytesRead = is.read(mybytearray, 0, mybytearray.length);
		current = bytesRead;
		do{
			bytesRead = is.read(mybytearray, current, (mybytearray.length-current));
			if (bytesRead >= 0){
				current += bytesRead;
			}
		} while (bytesRead >-1);
		bos.write(mybytearray, 0, current);
		bos.flush();
		System.out.println("File " + FILE_TO_RECEIVE + " downloaded ("+current+" bytes read)");
		fos.close();
		bos.close();
		is.close();
		client.close();
	}
	
	public static void main(String[] args) throws IOException {
		XmlClientSocket client = new XmlClientSocket();
		try{
			client.connect();
			client.xmlRequest();
			client.readXmlResponse();
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
