package be.camco.entities;

import java.util.List;
import java.util.SortedSet;

import be.camco.enums.DeviceType;
import be.camco.enums.RemoteAccessibleType;
import be.camco.interfaces.RemoteAccessible;
import be.camco.valueobjects.DeviceProperty;


public class RemoteAccessibleDevice extends Device implements RemoteAccessible{
	private RemoteAccessibleType remoteAccessibleType;
	private String port;
	private String password;
	private String username;
	
	public RemoteAccessibleDevice(){
		
	}
	
	
	
	public RemoteAccessibleDevice(String name, DeviceType deviceType, String address, String parent, List<String> comments, SortedSet<DeviceProperty> properties,
			RemoteAccessibleType remoteAccessibleType, String port, String password, String username) {
		super(name, deviceType, address, parent, comments, properties);
		this.setRemoteAccessibleType(remoteAccessibleType);
		this.setPort(port);
		this.setPassword(password);
		this.setUsername(username);
	}



	@Override
	public void setRemoteAccessibleType(RemoteAccessibleType remoteAccessibleType) {
		this.remoteAccessibleType=remoteAccessibleType;
	}
	
	@Override
	public RemoteAccessibleType getRemoteAccessibleType() {
		return remoteAccessibleType;
	}

	@Override
	public String toString() {
		return "RemoteAccessibleDevice [remoteAccessibleType="
				+ remoteAccessibleType + ", port=" + port + ", password="
				+ password + ", username=" + username + "]";
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

	public int compareTo(RemoteAccessibleDevice remoteAccessibleDevice){
		return this.toString().compareTo(remoteAccessibleDevice.toString());
	}

	

}
