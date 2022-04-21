package com.device.emp.DeviceAllocation;

import java.util.List;

public interface DeviceAllocationService {
	// using this function to get all records
	List<DeviceAllocation> getAllEmployees();

	// Using this function store all allocated records
	void saveEmployee(DeviceAllocation employee);

	DeviceAllocation getEmployeeById(long id);

	List<DeviceAllocation> listAll(String keyword);

	// Using this function declaration with help of get all device Id's
	List<String> getAllDeviceIds();

	// Using this function declaration with help of get all employee Id's
	List<String> getAllEmpIds();


	void updateDeviceAllocation(DeviceAllocation deviceAllocationObject);
	
	List<DeviceAllocation> getAllocatedDevice();
}
