package be.camco.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.camco.dao.EquipmentDAO;
import be.camco.entities.Area;
import be.camco.entities.Device;
import be.camco.entities.DeviceGroup;
import be.camco.enums.DeviceType;
@Service
public class EquipmentServiceImpl implements EquipmentService{
	private final EquipmentDAO equipmentDAO;
	@Autowired
	public EquipmentServiceImpl(EquipmentDAO equipmentDAO){
		this.equipmentDAO=equipmentDAO;
	}
	
	@Override
	public Iterable<Area> findAllAreas() {
		return equipmentDAO.findAllAreas();
	}

	@Override
	public Area readArea(String name) {
		return equipmentDAO.readArea(name);
	}

	@Override
	public Device readDevice(String name) {
		return equipmentDAO.readDevice(name);
	}

	@Override
	public Iterable<Device> findAllDevices() {
		return equipmentDAO.findAllDevices();
	}

	@Override
	public Iterable<Device> findByType(DeviceType type) {
		return equipmentDAO.findByType(type);
	}

	@Override
	public DeviceGroup readDeviceGroup(String name) {
		return equipmentDAO.readDeviceGroup(name);
	}

	@Override
	public Iterable<DeviceGroup> findAllDeviceGroups() {
		return equipmentDAO.findAllDeviceGroups();
	}
	
}
