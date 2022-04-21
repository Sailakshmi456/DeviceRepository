package com.device.emp.DeviceAllocation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceAllocationRepository extends JpaRepository<DeviceAllocation, Long> {
	// To select one field to display list of allocated records
	@Query(value = "select * from devices_allo r where r.device_Id like %:keyword% or r.emp_Id like %:keyword% or r.status_Type like %:keyword% ", nativeQuery = true)
	List<DeviceAllocation> getAllEmployees(@Param("keyword") String keyword);
	
	//  to display list of available devices in drop down 
	@Query(value = "select device_id from devices where status_type='Idle'", nativeQuery = true)
	List<String> getAllDeviceIds();
	
	// To display list of employees in drop down 
	@Query(value = "select emp_id from Employees ", nativeQuery = true)
	List<String> getAllEmpIds();
	
	@Query(value = "SELECT * FROM departmentemp.devices_allo WHERE id IN (SELECT MAX(id) FROM departmentemp.devices_allo GROUP BY device_id) AND  status_type = 'Allocated'", nativeQuery = true)
	List<DeviceAllocation> getAllocatedDevices();
}