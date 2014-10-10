package be.camco.dao;


import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.stereotype.Repository;

import be.camco.dom.ReadXml;
import be.camco.entities.Area;
import be.camco.entities.Device;
import be.camco.entities.DeviceGroup;
import be.camco.enums.DeviceType;

@Repository
public class EquipmentDAOImpl implements EquipmentDAO{
	private final SortedMap<String, Area> areas = new TreeMap<>();
	private final SortedMap<String, DeviceGroup> deviceGroups = new TreeMap<>();
	private final SortedMap<String, Device> devices = new TreeMap<>();
	private final ReadXml read = new ReadXml();
	public EquipmentDAOImpl(){
		}
	@Override
	public Iterable<Area> findAllAreas() {
		if (areas.isEmpty()){
			this.getRecentList();
		} else {
			if (read.checkVersion() == false){
				getRecentList();
			}
		}
		return areas.values();
	}
	@Override
	public Area readArea(String name) {
		if (read.checkVersion() == false){
			getRecentList();
		}
		return areas.get(name);
	}
	@Override
	public Device readDevice(String name) {
		if (read.checkVersion() == false){
			getRecentList();
		}
		return devices.get(name);
	}
	@Override
	public Iterable<Device> findAllDevices() {
		if (devices.isEmpty()){
			getRecentList();
		} else {
			if (read.checkVersion() == false){
				getRecentList();
			}
		}
		return devices.values();
	}
	@Override
	public Iterable<Device> findByType(DeviceType type) {
		if (devices.isEmpty()){
			getRecentList();
		} else {
			if (read.checkVersion() == false){
			getRecentList();
			}
		}
		List<Device> byType = new ArrayList<>();
		for (Device device:devices.values()){
			DeviceType deviceType = device.getDeviceType();
			if (deviceType == type){
				byType.add(device);
			}
		}
		return byType;
	}
	@Override
	public DeviceGroup readDeviceGroup(String name) {
		if (read.checkVersion()== false){
			getRecentList();
		}
		return deviceGroups.get(name);
	}
	@Override
	public Iterable<DeviceGroup> findAllDeviceGroups() {
		if (deviceGroups.isEmpty()){
			getRecentList();
		} else {
			if ( read.checkVersion()== false){//read.checkLastModified() == false &&
				getRecentList();
			}
		}
		return deviceGroups.values();
	}
	
	public void getRecentList(){
		areas.clear();
		deviceGroups.clear();
		devices.clear();
		read.parseXml();
		for (Area area:read.getAreas()){
			areas.put(area.getName(), area);
		}
		for (DeviceGroup deviceGroup:read.getDeviceGroups()){
			deviceGroups.put(deviceGroup.getName(), deviceGroup);
		}
		for (Device device:read.getDevices()){
			devices.put(device.getName(), device);
		}
		read.setLocalVersion();
	}
	
}
