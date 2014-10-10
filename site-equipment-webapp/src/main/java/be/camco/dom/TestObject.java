package be.camco.dom;

import java.io.Serializable;

public class TestObject implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int value;
	String id;
	public TestObject (int i, String s){
		this.value=i;
		this.id=s;
	}
}
