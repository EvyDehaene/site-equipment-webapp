package be.camco.entities;

import java.util.SortedSet;

public class Area implements Comparable<Area>{
	private String name;
	private String description;
	private SortedSet<DeviceGroup> deviceGroups;

public Area(String name, String description){
		this.setName(name);
		this.setDescription(description);
	}
	
	public Area(){}

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public SortedSet<DeviceGroup> getDeviceGroups() {
		return deviceGroups;
	}
	public void setDeviceGroups(SortedSet<DeviceGroup> deviceGroups) {
		this.deviceGroups = deviceGroups;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void addDeviceGroup(DeviceGroup deviceGroup){
		this.deviceGroups.add(deviceGroup);
	}
	
	@Override
	public String toString() {
		return "Area [name=" + name + ", deviceGroups=" + deviceGroups + "]";
	}
	@Override
	public int compareTo(Area o) {
		return this.toString().compareTo(o.toString());
	}
}
