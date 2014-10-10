package be.camco.entities;

import be.camco.enums.RemoteAccessibleType;
import be.camco.interfaces.RemoteAccessible;


public class RemoteAccessibleDevice extends Device implements RemoteAccessible{
	private RemoteAccessibleType remoteAccessibleType;
	private String port;
	private String password;
	private String username;
	
	
	@Override
	public void setType(RemoteAccessibleType remoteAccessibleType) {
		this.remoteAccessibleType=remoteAccessibleType;
	}
	
	@Override
	public RemoteAccessibleType getType() {
		return remoteAccessibleType;
	}

	@Override
	public void setPort(String port) {
		this.port=port;
	}

	@Override
	public String getPort() {
		return port;
	}

	@Override
	public void setUsername(String username) {
		this.username=username;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public void setPassword(String password) {
		this.password=password;
	}

	@Override
	public String getPassword() {
		return password;
	}

	

	

}
