package be.camco.entities;

import java.util.List;
import java.util.SortedSet;

import be.camco.enums.DeviceType;
import be.camco.valueobjects.DeviceProperty;

public class Device implements Comparable<Device>{
	private String name;
	private DeviceType deviceType;
	private String address;
	private String parent;
	

	private List<String> comments;
	private SortedSet<DeviceProperty> properties;
	
	public Device(){
		
	}
	
	
	public Device(String name, DeviceType deviceType, String address,String parent, List<String> comments,SortedSet<DeviceProperty> properties) {
		this.name = name;
		this.deviceType = deviceType;
		this.address = address;
		this.parent = parent;
		this.comments = comments;
		this.properties = properties;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<String> getComments() {
		return comments;
	}

	public void setComments(List<String> comments) {
		this.comments = comments;
	}
	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public SortedSet<DeviceProperty> getProperties() {
		return properties;
	}

	public void setProperties(SortedSet<DeviceProperty> properties) {
		this.properties = properties;
	}

	public int compareTo(Device o) {
		return this.toString().compareTo(o.toString());
	}
	
	public void addComment(String comment){
		comments.add(comment);
	}

	@Override
	public String toString() {
		return "Device [name=" + name + ", deviceType=" + deviceType
				+ ", address=" + address + ", comments=" + comments + "]";
	}

}
