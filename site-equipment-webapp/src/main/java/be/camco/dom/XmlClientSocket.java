package be.camco.dom;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class XmlClientSocket {
	private final int PORT = 8181;
	private final String HOSTNAME = "localhost";
	Socket client;
	File receivedXml;
//	public void start() throws UnknownHostException, IOException {
//		System.out.println("Attempt to connect to "+SERVER+": "+SOCKET_PORT);
//		socket = new Socket(SERVER, SOCKET_PORT);
//		System.out.println("Connection established");
//	}
//	
//	public void requestXml() throws IOException{
//		byte[] mybytearray = new byte[FILE_SIZE];
//		InputStream is = socket.getInputStream();
//		fos = new FileOutputStream(FILE_TO_RECEIVE);
//		bos = new BufferedOutputStream(fos);
//		bytesRead = is.read(mybytearray, 0, mybytearray.length);
//		current = bytesRead;
//		do {
//			bytesRead = is.read(mybytearray, current, (mybytearray.length-current));
//			if (bytesRead >= 0){
//				current += bytesRead;
//			}
//		} while(bytesRead > -1);
//		bos.write(mybytearray, 0, current);
//		bos.flush();
//		System.out.println("File "+FILE_TO_RECEIVE + " downloaded ("+current+" bytes read)");
//		fos.close();
//		bos.close();
//		socket.close();
//	}
	
	public void connect() throws UnknownHostException, IOException{
		System.out.println("Attempting to connect to: "+HOSTNAME+": "+PORT);
		client = new Socket(HOSTNAME, PORT);
		System.out.println("Connection established.");
	}
	
	public void readResponse() throws IOException, ClassNotFoundException{
		InputStream is = client.getInputStream();
		ObjectInputStream ois = new ObjectInputStream(is);
		receivedXml = ((File)ois.readObject());
		System.out.println("File gotten");
		ois.close();
		is.close();
	}
	
	public static void main(String[] args) throws IOException {
		XmlClientSocket client = new XmlClientSocket();
		try{
			client.connect();
			client.readResponse();
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
