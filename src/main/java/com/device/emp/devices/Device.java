package com.device.emp.devices;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Devices")
public class Device {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "device_Id", nullable = true, length = 20)
	private String deviceId;

	@Column(name = "device_Name", nullable = true, length = 20)
	private String deviceName;

	@Column(name = "device_Type", nullable = true, length = 20)
	private String deviceType;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@Column(name = "date_of_register", nullable = true, length = 20)
	private LocalDateTime dateOfRegister;

	@Column(name = "status_Type", nullable = true, length = 20)
	private String status;

	public LocalDateTime getDateOfRegister() {
		return dateOfRegister;
	}

	public void setDateOfRegister(LocalDateTime dateOfRegister) {
		this.dateOfRegister = dateOfRegister;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
