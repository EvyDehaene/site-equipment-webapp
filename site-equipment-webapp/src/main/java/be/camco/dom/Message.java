package be.camco.dom;

import java.io.Serializable;

public class Message implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int number;
	Message (int number){
		this.number=number;
	}
}
