package be.camco.services;

import be.camco.entities.Area;
import be.camco.entities.Device;
import be.camco.entities.DeviceGroup;
import be.camco.enums.DeviceType;

public interface EquipmentService {
	public Iterable<Area> findAllAreas();
	public Area readArea(String name);
	public Device readDevice(String name);
	public Iterable<Device> findAllDevices();
	public Iterable<Device> findByType(DeviceType type);
	public DeviceGroup readDeviceGroup(String name);
	public Iterable<DeviceGroup> findAllDeviceGroups();
	
}
