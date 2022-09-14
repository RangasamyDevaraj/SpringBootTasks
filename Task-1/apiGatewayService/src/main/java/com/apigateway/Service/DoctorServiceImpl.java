package com.apigateway.Service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.apigateway.dao.DoctorRepository;
import com.apigateway.model.Doctor;

@Service
public class DoctorServiceImpl implements DoctorService {
	
	@Autowired
	DoctorRepository repository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public Doctor createDoctor(Doctor doctor)
	{
		return repository.save(doctor);
	}
	
	@Override
	public Doctor getDoctor(int id)
	{
		return repository.findById(id).get();
	}
	
	@Override
	public void deleteDoctor(int id)
	{
		repository.deleteById(id);
	}
	@Override
	public Doctor updatedoctor(Doctor doctorequest)
	{
		
		Doctor updateddoctor=repository.save(doctorequest);
		
		return updateddoctor;
	}
	
	@Override
	public Doctor getDoctor(String username) {

		Doctor doctor=repository.findByuserName(username);
		
		return doctor;
	}

	@Override
	public Boolean isDoctorExists(int id) {
		
		return repository.existsById(id);
	}

	}

