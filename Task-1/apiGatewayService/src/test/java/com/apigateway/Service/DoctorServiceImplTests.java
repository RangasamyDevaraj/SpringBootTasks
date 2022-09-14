package com.apigateway.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.apigateway.dao.DoctorRepository;
import com.apigateway.model.Doctor;

@SpringBootTest(classes = {DoctorServiceImplTests.class})
class DoctorServiceImplTests {

	@Mock
    DoctorRepository repo;

	@InjectMocks
	DoctorServiceImpl service;
	
	@Test
	@Order(1)
	public void createDoctorTest() {
		
		Doctor doctor=new Doctor(1,"ramesh","Kumar", "ramesh@gmail.com","1995-05-27","ramesh", "ramesh@123");
		
		when(repo.save(doctor)).thenReturn(doctor);
		
		assertEquals(doctor, service.createDoctor(doctor));
	}
	
	@Test
	@Order(2)
	public void getDoctorTest() {
		
		int doctorId=1;
		
		List<Doctor> records=new ArrayList<Doctor>();
		
		records.add(new Doctor(doctorId,"ramesh","Kumar", "ramesh@gmail.com","1995-05-27","ramesh", "ramesh@123"));
		records.add(new Doctor(2,"ashok","kumar", "ashok@gmail.com","1992-09-31","ashok", "ashok@123"));
		
		Optional<Doctor> doctor = Optional.of(new Doctor(doctorId,"ramesh","Kumar", "ramesh@gmail.com","1995-05-27","ramesh", "ramesh@123"));
		
		when(repo.findById(doctorId)).thenReturn(doctor);
		
		assertEquals(1, service.getDoctor(doctorId).getDoctor_Id());
	}
	
	@Test
	@Order(3)
	public void updatedoctorTest()
	{

		Doctor newRecord = new Doctor(1,"Ramesh","Kumar", "ramesh@gmail.com","1995-05-27","ramesh", "ramesh@123");
		
		when(repo.save(newRecord)).thenReturn(newRecord);
		
		assertEquals(newRecord, service.updatedoctor(newRecord));
	}
	
	@Test
	@Order(4)
	public void deleteDoctorTest()
	{
		
		Doctor record = new Doctor(1,"Ramesh","Kumar", "ramesh@gmail.com","1995-05-27","ramesh", "ramesh@123");
		
		service.deleteDoctor(record.getDoctor_Id());
		
		verify(repo,times(1)).deleteById(record.getDoctor_Id());
	}
	
	@Test
	@Order(5)
	public void getDoctorbyUsernameTest() {
		
		String userName = "ramesh";
		
		Doctor record = new Doctor(1,"Ramesh","Kumar", "ramesh@gmail.com","1995-05-27","ramesh", "ramesh@123");
		
		when(repo.findByuserName(userName)).thenReturn(record);
		
		assertEquals(record, service.getDoctor(userName));
	}
	@Test
	@Order(5)
	public void isDoctorExistsTest(){
		
		int doctorId=1;
		
		when(repo.existsById(doctorId)).thenReturn(false);
		assertFalse(service.isDoctorExists(doctorId));
		when(repo.existsById(doctorId)).thenReturn(true);
		assertTrue(service.isDoctorExists(doctorId));
	}	
	

}
