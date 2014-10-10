package be.camco.web;

import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import be.camco.entities.Area;
import be.camco.entities.DeviceGroup;
import be.camco.enums.DeviceType;
import be.camco.services.EquipmentService;

@Controller
@RequestMapping("/")
public class EquipmentController {
	private final EquipmentService equipmentService;
	@Autowired
	public EquipmentController (EquipmentService equipmentService){
		this.equipmentService=equipmentService;
	}
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView index(){
		Iterable<Area> itArea = equipmentService.findAllAreas();
		Iterable<DeviceGroup> itDeviceGroup = equipmentService.findAllDeviceGroups();
		SortedSet<DeviceGroup> deviceGroupSet = new TreeSet<>();
		for (DeviceGroup deviceGroup:itDeviceGroup){
			deviceGroupSet.add(deviceGroup);
		}
		for(DeviceGroup deviceGroup:itDeviceGroup){
			for (Area area:itArea){
				if (area.getDeviceGroups().contains(deviceGroup)){
					deviceGroupSet.remove(deviceGroup);
				}
			}
		}
		return new ModelAndView("/index", "areas", itArea).addObject("deviceGroups", deviceGroupSet);
		
	}
	@RequestMapping(value="devices", method = RequestMethod.GET)
	public ModelAndView findAllDevices(){
		return new ModelAndView("devices/devices", "devices", equipmentService.findAllDevices());
	}
	@RequestMapping(value ="devices/type", method = RequestMethod.GET, params="type")
	public ModelAndView findDeviceByType(@RequestParam DeviceType type){
		return new ModelAndView("devices/type", "devices", equipmentService.findByType(type))
		.addObject("type", type);
	}
	@RequestMapping(value = "devices/wago", method = RequestMethod.GET, params="name")
	public ModelAndView readDevice(@RequestParam String name){
		return new ModelAndView("devices/wago", "wago", equipmentService.readDevice(name));
	}
	@RequestMapping(value ="devices/wagos", method = RequestMethod.GET)
	public ModelAndView findAllWagos(){
		return new ModelAndView("devices/wagos", "wagos", equipmentService.findByType(DeviceType.WAGO));
	}
	@RequestMapping(value="devices/deviceproperties", method = RequestMethod.GET, params="name")
	public ModelAndView findAllDeviceProperties(@RequestParam String name){
		return new ModelAndView("devices/deviceproperties", "device", equipmentService.readDevice(name));
	}
	@RequestMapping(value="devicegroups", method=RequestMethod.GET)
	public ModelAndView findAllDeviceGroups(){
		return new ModelAndView("devicegroups/devicegroups", "devicegroups", equipmentService.findAllDeviceGroups());
	}
	@RequestMapping(value="devicegroup", method=RequestMethod.GET, params="name")
	public ModelAndView readDeviceGroup(@RequestParam String name){
		return new ModelAndView("devicegroups/devicegroup", "devicegroup", equipmentService.readDeviceGroup(name));
	}
	@RequestMapping(value="areas", method = RequestMethod.GET)
	public ModelAndView findAllAreas(){
		Iterable<Area> areas = equipmentService.findAllAreas();
		return new ModelAndView("areas/areas", "areas", areas);
	}
	@RequestMapping(value="area", method = RequestMethod.GET, params="name")
	public ModelAndView readArea(@RequestParam String name){
		return new ModelAndView("areas/area", "area", equipmentService.readArea(name));
	}
}
