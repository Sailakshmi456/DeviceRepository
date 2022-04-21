package com.device.emp.devices;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceServiceImpl implements DeviceService {

	@Autowired
	private DeviceRepository deviceRepository;

	@Override
	public List<Device> getAllDevices() {
		return deviceRepository.findAll();
	}

	@Override
	public Device saveDevice(Device device) {
		return this.deviceRepository.save(device);
	}

	@Override
	public Device getDeviceById(long id) {
		Optional<Device> optional = deviceRepository.findById(id);
		Device device = null;
		if (optional.isPresent()) {
			device = optional.get();
		} else {
			throw new RuntimeException(" Device not found for id :: " + id);
		}
		return device;
	}

	@Override
	public void deleteDeviceById(long id) {
		this.deviceRepository.deleteById(id);
	}

	@Override
	public List<Device> listAll(String keyword) {
		if (keyword != null) {
			return deviceRepository.getAllDevices(keyword);
		}
		return deviceRepository.findAll();
	}

	@Override
	public List<Device> getAvailableDeviceList() {
		// Handle for active devices: pending
		return deviceRepository.getAvailableDeviceList();
	}

	@Override
	public void updateDeviceStatus(String deviceId, String deviceStatus) {
		this.deviceRepository.updateDeviceStatus(deviceId, deviceStatus);
	}

	@Override
	public Device getDeviceByDeviceId(String deviceId) {
		List<Device> deviceList = deviceRepository.getAllDevices(deviceId);
		if (deviceList.size() > 0) {
			return deviceList.get(0);
		}
		return null;
	}
}