package com.device.emp.devices;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
	@Query(value = "select * from Devices r where r.device_Id like %:keyword% or r.device_Type like %:keyword% or r.status_Type like %:keyword% ", nativeQuery = true)
	List<Device> getAllDevices(@Param("keyword") String keyword);

	@Query(value = "select * from Devices r where r.status_type = 'Idle'", nativeQuery = true)
	List<Device> getAvailableDeviceList();

	@Query(value = "update Devices r set r.status_type = 'Allocated' where r.device_id = ''", nativeQuery = true)
	void updateDeviceStatus(@Param("deviceId") String deviceId, @Param("deviceStatus") String deviceStatus);
}