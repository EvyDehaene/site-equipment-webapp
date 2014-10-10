package be.camco.entities;

import java.util.SortedSet;

public class DeviceGroup {
	private String name;
	private SortedSet<Device> devices;
	
	public DeviceGroup(String name){
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setDevices(SortedSet<Device> devices){
		this.devices=devices;
	}
	
	public SortedSet<Device> getDevices(){
		return devices;
	}
	
	public void addDevice(Device device){
		this.devices.add(device);
	}
	public void removeDevice(String deviceName){
		for (Device device:devices){
			if (device.getName()==deviceName){
				devices.remove(device);
			}
		}
	}
}
