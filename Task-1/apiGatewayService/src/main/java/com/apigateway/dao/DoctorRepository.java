package com.apigateway.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apigateway.model.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer>{
	
	Doctor findByuserName(String username);

}
