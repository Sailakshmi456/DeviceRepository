package com.device.emp.devices;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DeviceController {

	@Autowired
	private DeviceService deviceService;

	// To display list of devices
	@GetMapping("/availableDevicesList")
	public String viewHomePage(Model model) {
		//model.addAttribute("listDevices", deviceService.getAllDevices());
		model.addAttribute("listDevices", deviceService.listAll("Idle"));
		return "devices_report";
	}

	// get records to the form
	@GetMapping("/showNewDeviceForm")
	public String showNewDeviceForm(Model model) {
		// create model attribute to bind form data
		Device device = new Device();
		model.addAttribute("device", device);
		return "device_registration";
	}

	// save device to database
	@PostMapping("/showNewDeviceForm")
	public String processRegister(Device device) {
		Device dev;
		try {
			dev = deviceService.saveDevice(device);
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			return "duplicate_device";
		}
		return "success_message";
	}

	@RequestMapping("/deviceAvailableSearch")
	public String viewHomePage(Model model, @Param("keyword") String keyword) {
		List<Device> listAll = deviceService.listAll(keyword);
		model.addAttribute("listDevices", listAll);
		model.addAttribute("keyword", keyword);
		return "devices_report";

	}

	@SuppressWarnings("unchecked")
	@GetMapping(path = "/availableDevicelist", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getListEmployees() {
		JSONArray deviceJsonList = new JSONArray();
		List<Device> deviceList = deviceService.getAvailableDeviceList();
		for (Device device : deviceList) {
			JSONObject obj = new JSONObject();
			obj.put("id", device.getId());
			obj.put("device_id", device.getDeviceId());
			obj.put("device_name", device.getDeviceName());
			obj.put("device_type", device.getDeviceType());
			deviceJsonList.add(obj);
		}
		return new ResponseEntity<Object>(deviceJsonList.toJSONString(), HttpStatus.OK);
	}

	@PostMapping("/updateDeviceStatus")
	public void updateDeviceStatus(@ModelAttribute("device") Device device) {
		try {
			deviceService.saveDevice(device);
		} catch (Exception excp) {
			excp.printStackTrace();
		}
	}
}