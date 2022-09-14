package com.apigateway.Service;

import org.springframework.stereotype.Service;

import com.apigateway.model.Doctor;

@Service
public interface DoctorService {
	
	//Method to create new Doctor Record
	Doctor createDoctor(Doctor doctor);
	
	//Method to get the doctor record by Doctor_Id
	Doctor getDoctor(int id);
	
	//Method to update the particular doctor record by Doctor_Id 
	Doctor updatedoctor(Doctor doctorRequest);
	
	//Method to get the particular doctor record by userName and password
	Doctor getDoctor(String username) ;
	
	//Method to delete doctor record by Doctor_Id
	void deleteDoctor(int id);
	
	//Method to find is doctor record is exist or not 
	Boolean isDoctorExists(int id);


}
