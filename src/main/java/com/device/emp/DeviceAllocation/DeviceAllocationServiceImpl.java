package com.device.emp.DeviceAllocation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceAllocationServiceImpl implements DeviceAllocationService {

	@Autowired
	private DeviceAllocationRepository allocateRepository;

	@Override
	public List<DeviceAllocation> getAllEmployees() {
		return allocateRepository.findAll();
	}

	@Override
	public List<String> getAllDeviceIds() {
		return allocateRepository.getAllDeviceIds();
	}

	@Override
	public List<String> getAllEmpIds() {
		return allocateRepository.getAllEmpIds();
	}

	@Override
	public void saveEmployee(DeviceAllocation allocation) {
		this.allocateRepository.save(allocation);
	}

	@Override
	public DeviceAllocation getEmployeeById(long id) {
		Optional<DeviceAllocation> optional = allocateRepository.findById(id);
		DeviceAllocation allocation = null;
		if (optional.isPresent()) {
			allocation = optional.get();
		} else {
			throw new RuntimeException(" Employee not found for id :: " + id);
		}
		return allocation;
	}

	@Override
	public List<DeviceAllocation> listAll(String keyword) {
		if (keyword != null) {
			return allocateRepository.getAllEmployees(keyword);
		}
		return allocateRepository.findAll();
	}

	@Override
	public void updateDeviceAllocation(DeviceAllocation deviceAllocationObject) {

	}

	@Override
	public List<DeviceAllocation> getAllocatedDevice() {
		return allocateRepository.getAllocatedDevices();
	}
}