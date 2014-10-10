package be.camco.interfaces;

import be.camco.enums.RemoteAccessibleType;

public interface RemoteAccessible {
	public void setRemoteAccessibleType(RemoteAccessibleType remoteAccessibleType);
	public RemoteAccessibleType getRemoteAccessibleType();
	public void setPort(String port);
	public String getPort();
	public void setUsername(String username);
	public String getUsername();
	public void setPassword(String password);
	public String getPassword();
}
