package com.apigateway.controller;

import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.apigateway.Service.DoctorService;
import com.apigateway.advice.ApiResponse;
import com.apigateway.dto.DoctorDto;
import com.apigateway.model.Doctor;

@RestController
@RequestMapping("/doctor")
public class GatewayController {
	
	@Autowired
	DoctorService doctorService;
	
	@Autowired
	ModelMapper modelMapper;
	
	
	//create new Doctor record
	
	@PostMapping("/create")
	public ResponseEntity<DoctorDto> createDoctor(@RequestBody @Valid DoctorDto doctordto)
	{
		
		Doctor doctorRequest = modelMapper.map(doctordto, Doctor.class);
		
		Doctor doctor=doctorService.createDoctor(doctorRequest);
		
		DoctorDto doctorResponse = modelMapper.map(doctor, DoctorDto.class);
		
		return new ResponseEntity<>(doctorResponse,HttpStatus.CREATED);
	}
	
	//Get Doctor record By Doctor_ID
	
	@GetMapping("/get/{doctor_id}")
	public ResponseEntity<DoctorDto> getDoctor(@PathVariable("doctor_id") int id,HttpServletRequest req) 
	{
		boolean isDoctorExists=doctorService.isDoctorExists(id);
		
		if (isDoctorExists) {
			
			Doctor doctor = doctorService.getDoctor(id);
			 
			DoctorDto doctorResponse = modelMapper.map(doctor, DoctorDto.class);
			
			return new ResponseEntity<>(doctorResponse,HttpStatus.OK);
		} 
		else
		{
			throw new NoSuchElementException("No doctor Present with Id = " + id);
		}
	}
	
	
	//Delete Doctor record by Doctor_ID
	
	@DeleteMapping("/delete/{doctor_id}")
	public ResponseEntity<ApiResponse> deleteDoctor(@PathVariable("doctor_id") int id)
	{
		boolean isDoctorExists=doctorService.isDoctorExists(id);
		if (isDoctorExists) {
			
			doctorService.deleteDoctor(id);
			
			ApiResponse apiResponse=new ApiResponse(HttpStatus.OK.value(),"Record deleted successfully");
			
			return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);

		} 
		else {
			
			throw new NoSuchElementException("No doctor Present with Id  = " + id + ". Enter the valid Id");
		}
		
	}
	
	//Update existing doctor record details  
	
	@PutMapping("/update/{doctor_id}")  
	public  ResponseEntity<DoctorDto> updateDoctoRecord(@PathVariable("doctor_id") int id,@RequestBody @Valid DoctorDto doctordto)   
	{  
		boolean isDoctorExists = doctorService.isDoctorExists(id);
		
		if (isDoctorExists) {
			
			Doctor doctorRequest = modelMapper.map(doctordto, Doctor.class);
			
			Doctor doctor = doctorService.updatedoctor(doctorRequest);
			
			DoctorDto doctorResponse =  modelMapper.map(doctor, DoctorDto.class);
			 
			return new ResponseEntity<DoctorDto>(doctorResponse,HttpStatus.OK);  
		}  
		else 
		{
			throw new NoSuchElementException("DOCTOR ID NOT FOUND  = " + id +" Enter the valid Id");
		}
	}  
	
	//Doctor login doctorService
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse> login(@RequestParam String username,@RequestParam String password)
	{
		
		Doctor doctor=doctorService.getDoctor(username);
		
		ApiResponse apiResponse = new ApiResponse();

		if(doctor == null) {
			
			apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
			apiResponse.setMessage("Incorrect Username");
			
		}
		else if(doctor.getpassword().equals(password) ) {
			
			apiResponse.setStatusCode(HttpStatus.OK.value());
			apiResponse.setMessage("Login successfully!!!");
			
		}
		else {
			
			apiResponse.setStatusCode(HttpStatus.UNAUTHORIZED.value());
			apiResponse.setMessage("Incorrect Password");
		}
		
		return new ResponseEntity<>(apiResponse,HttpStatus.OK);
	 
	}
	
}
