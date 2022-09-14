package com.ltts.caseservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ltts.caseservice.dao.CaseRepository;
import com.ltts.caseservice.model.Patient;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	CaseRepository repository;
	
	@Override
	public Patient createPatient(Patient newPatient) {
		
		Patient patient=repository.save(newPatient);
		return patient;
	}

	@Override
	public Patient getPatientById(int id) {
		// TODO Auto-generated method stub
		return repository.findById(id).get();
	}

	@Override
	public Patient updatePatient(Patient patient) {
		// TODO Auto-generated method stub
		return repository.save(patient);
	}

	@Override
	public void deletePatientById(int id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

	@Override
	public Boolean isPatientExists(int id) {
		// TODO Auto-generated method stub
		return repository.existsById(id);
	}

	
}
