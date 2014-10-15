package be.camco.sockets;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSocket {
	private final int PORT=8282;
	private final String HOST = "localhost";
	private final String REQUEST_XML = "U:/git/new/site-equipment-webapp/src/main/resources/siteequipmentrequest.xml";//locatie siteEquipmentRequest.xml
	private final String REQUEST_VERSION = "U:/git/new/site-equipment-webapp/src/main/resources/versionrequest.xml";//locatie versionRequest.xml
	private final String RECEIVED_RESPONSE="U:/git/new/site-equipment-webapp/src/main/resources/receivedresponse.xml";//locatie voor stockeren ontvangen response
	private final static int FILE_SIZE = 6022386;
	final String RECEIVED_XML="C:/site-equipment-temp.xml";//locatie lokaal xml-bestand
	public String version;
	public Socket client;
	
	public void connect() throws UnknownHostException, IOException{
		System.out.println("Attempting to connect to: "+HOST+": "+PORT);
		client = new Socket (HOST, PORT);
		System.out.println("Connection established");
	}
	
	private void readResponse() throws IOException{
		int bytesRead;
		int current = 0;
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		try{
			byte[] mybytearray = new byte[FILE_SIZE];
			InputStream is = client.getInputStream();
			fos = new FileOutputStream(RECEIVED_RESPONSE);
			bos = new BufferedOutputStream(fos);
			bytesRead = is.read(mybytearray, 0, mybytearray.length);
			current = bytesRead;
			do{
				bytesRead=is.read(mybytearray, current,(mybytearray.length-current));
				if (bytesRead >= 0){
					current += bytesRead;
				}
			} while(bytesRead > -1);
			bos.write(mybytearray, 0, current);
			bos.flush();
			System.out.println("File "+RECEIVED_RESPONSE+" downloaded ("+current+" bytes read)");
		} finally {
			if (fos != null){fos.close();}
			if (bos != null){bos.close();}
			if (client != null){client.close();}
		}
	}
	
	public void versionRequest() throws IOException{
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		OutputStream os = null;
		try{
			File myFile = new File(REQUEST_VERSION);
			byte[] mybytearray = new byte[(int)myFile.length()];
			fis = new FileInputStream(myFile);
			bis = new BufferedInputStream(fis);
			bis.read(mybytearray, 0, mybytearray.length);
			os = client.getOutputStream();
			System.out.println("Sending "+REQUEST_VERSION+" ("+mybytearray.length+" bytes)");
			os.write(mybytearray, 0, mybytearray.length);
			os.flush();
			System.out.println("Done.");
		} finally {
			if (bis != null){bis.close();}
			if (os != null){os.close();}
			if (client != null){client.close();}
		}
	}
	
	public static void main (String[] args){
		ClientSocket client = new ClientSocket();
		try {
			client.connect();
			client.versionRequest();
			client.readResponse();
		} catch (UnknownHostException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
}
