package com.device.emp.devices;

import java.util.List;

public interface DeviceService {
	// Using this function get all devices
	List<Device> getAllDevices();

	Device saveDevice(Device device);

	Device getDeviceById(long id);

	void deleteDeviceById(long id);

	List<Device> listAll(String keyword);

	List<Device> getAvailableDeviceList();

	void updateDeviceStatus(String deviceId, String deviceStatus);

	Device getDeviceByDeviceId(String deviceId);
}
