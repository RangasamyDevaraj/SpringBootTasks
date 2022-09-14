package com.ltts.caseservice.service;


import org.springframework.stereotype.Service;

import com.ltts.caseservice.model.Patient;

@Service
public interface PatientService {
	
	Patient createPatient(Patient newPatient);
	
	Patient updatePatient(Patient patient);
	
	Patient getPatientById(int id);
	
	void deletePatientById(int id);
	
	Boolean isPatientExists(int id);

}
