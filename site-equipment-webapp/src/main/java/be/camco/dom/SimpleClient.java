package be.camco.dom;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SimpleClient {
	public static void main(String [] args){
		try{
			Socket s = new Socket("localhost", 8383);
			OutputStream os = s.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
//			TestObject to = new TestObject(1, "Object from client");
//			oos.writeObject(to);
			oos.writeObject(new String("another object from the client"));
			oos.close();
			os.close();
			s.close();
		} catch (Exception ex){
			System.err.println(ex.getMessage());
		}
	}
}
