package be.camco.entities;

import java.util.SortedSet;
import java.util.TreeSet;

public class DeviceGroup implements Comparable<DeviceGroup>{
	private String name;
	private SortedSet<Device> devices = new TreeSet<>();
	
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

	@Override
	public int compareTo(DeviceGroup o) {
		return this.toString().compareTo(o.toString());
	}

	@Override
	public String toString() {
		return "DeviceGroup [name=" + name + ", devices=" + devices + "]";
	}
}
