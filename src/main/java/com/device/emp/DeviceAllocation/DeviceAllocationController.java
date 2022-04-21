package com.device.emp.DeviceAllocation;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.device.emp.devices.Device;
import com.device.emp.devices.DeviceService;

@Controller
public class DeviceAllocationController {

	@Autowired
	private DeviceAllocationService allocateService;
	@Autowired
	private DeviceService deviceService;

	// To display list of allocated devices to employees
	@GetMapping("/deviceAllocationreport")
	public String displayOfDevices(Model model) {
		model.addAttribute("listEmployees", allocateService.getAllocatedDevice());
		return "devicesAllocation_report";
	}

	@GetMapping("/deviceAllocationForm")
	public String showNewEmployeeForm(Model model) {
		// create model attribute to bind form data
		DeviceAllocation allocation = new DeviceAllocation();
		model.addAttribute("allocation", allocation);
		model.addAttribute("deviceIds", allocateService.getAllDeviceIds());
		return "device_allocation";
	}

	// To allocate a device to employee.
	@PostMapping("/saveDeviceAllocation")
	public String saveEmployee(@ModelAttribute("allocation") DeviceAllocation allocation) {
	Device device = deviceService.getDeviceByDeviceId(String.valueOf(allocation.getDeviceId()));
	device.setStatus(allocation.getStatus());
	deviceService.saveDevice(device);
	allocation.setId(null);
	allocateService.saveEmployee(allocation);
	return "home";
	}
	
	@PutMapping("/saveDeviceAllocation")
	public String updateDeviceAllocation(@ModelAttribute("allocation") DeviceAllocation allocation) {
		Device device = new Device();
		device.setId(null);
		return "home";
	}

	// To reallocate a device to employee
	@GetMapping("/reallocation")
	public String viewDeviceReallocation(Model model) {
		//model.addAttribute("listEmployees", allocateService.getAllEmployees());
		model.addAttribute("listEmployees", allocateService.getAllocatedDevice());
		return "device_reallocation";
	}

	// searching the devices in a list of records
	@RequestMapping("/searchDeviceReallocation")
	public String viewEmployeeDevicePage(Model model, @Param("keyword") String keyword) {
		List<DeviceAllocation> listAll = allocateService.listAll(keyword);
		model.addAttribute("listEmployees", listAll);
		model.addAttribute("keyword", keyword);
		return "device_reallocation";
	}

	// To allocate a device to employee.
	@GetMapping("/employeeDeviceReport")
	public String viewEmployeeDeviceList(Model model) {
		model.addAttribute("listEmployees", allocateService.getAllEmployees());
		return "employeedevice_report";
	}

	// searching the devices in a list of records
	@RequestMapping("/employeeDeviceList")
	public String viewEmployeeDeviceList(Model model, @Param("keyword") String keyword) {
		List<DeviceAllocation> listAll = allocateService.listAll(keyword);
		model.addAttribute("listEmployees", listAll);
		model.addAttribute("keyword", keyword);
		return "employeedevice_report";
	}

	// To allocate a device to employee and updating values.
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
		// get employee from the service
		DeviceAllocation allocation = allocateService.getEmployeeById(id);
		// set employee as a model attribute to pre-populate the form
		model.addAttribute("allocation", allocation);
		return "update_Reallocation";
	}

	// searching the list of records in devicelist
	@RequestMapping("/deviceAllocationSearch")
	public String displayOfDevices(Model model, @Param("keyword") String keyword) {
		List<DeviceAllocation> listAll = allocateService.listAll(keyword);
		model.addAttribute("listEmployees", listAll);
		model.addAttribute("keyword", keyword);
		return "devicesAllocation_report";
	}

	// fetching devicesIds from database using get mapping
	@GetMapping(path = "/empidslist", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getEmpIdsList(Model model) {
		// create model attribute to bind form data
		List<JSONObject> entities = new ArrayList<JSONObject>();
		JSONObject entity = new JSONObject();
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		entities.add(entity);
		List<String> list1 = allocateService.getAllEmpIds();
		String deviceId = StringUtils.join(list1, ",");
		return new ResponseEntity<Object>(deviceId, HttpStatus.OK);
	}
}