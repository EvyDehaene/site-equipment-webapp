package be.camco.dom;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSocket {
	public final static int SOCKET_PORT = 8181;
	public final static String SERVER = "127.0.0.1";
	public final static String FILE_TO_RECEIVE = "C:\\Users\\evy\\equipment-temp.xml";
	int bytesRead;
	int current = 0;
	FileOutputStream fos = null;
	BufferedOutputStream bos = null;
	Socket socket = null;
	public final static int FILE_SIZE = 6022386; //file size temporary hard coded, should be bigger that the file to be downloaded
	
	public void start() throws UnknownHostException, IOException {
		System.out.println("Attempt to connect to "+SERVER+": "+SOCKET_PORT);
		socket = new Socket(SERVER, SOCKET_PORT);
		System.out.println("Connection established");
	}
	
	public void requestXml() throws IOException{
		byte[] mybytearray = new byte[FILE_SIZE];
		InputStream is = socket.getInputStream();
		fos = new FileOutputStream(FILE_TO_RECEIVE);
		bos = new BufferedOutputStream(fos);
		bytesRead = is.read(mybytearray, 0, mybytearray.length);
		current = bytesRead;
		do {
			bytesRead = is.read(mybytearray, current, (mybytearray.length-current));
			if (bytesRead >= 0){
				current += bytesRead;
			}
		} while(bytesRead > -1);
		bos.write(mybytearray, 0, current);
		bos.flush();
		System.out.println("File "+FILE_TO_RECEIVE + " downloaded ("+current+" bytes read)");
		fos.close();
		bos.close();
		socket.close();
	}
	
	public static void main(String[] args) throws IOException {
		XmlClientSocket xmlClientSocket = new XmlClientSocket();
			try{
				xmlClientSocket.connect();
				xmlClientSocket.readResponse();
			} catch (UnknownHostException ex){
				System.err.println("UnknownHostException."+ex.getMessage());
			} catch (IOException ex){
				System.err.println("IOException."+ex.getMessage());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		
	}
}