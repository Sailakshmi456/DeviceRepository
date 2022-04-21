package com.device.emp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	 //Custom query
	 @Query(value = "select * from Employees s where s.first_Name like %:keyword% or s.emp_id like %:keyword%", nativeQuery = true)
	 List<Employee> findByKeyword(@Param("keyword") String keyword);
}