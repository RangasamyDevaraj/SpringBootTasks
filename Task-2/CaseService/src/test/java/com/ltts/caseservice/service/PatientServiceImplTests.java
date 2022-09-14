package com.ltts.caseservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

import com.ltts.caseservice.dao.CaseRepository;
import com.ltts.caseservice.model.Patient;

@SpringBootTest(classes = {PatientServiceImplTests.class})
public class PatientServiceImplTests {

	@Mock
    CaseRepository repo;

	@InjectMocks
	PatientServiceImpl service;
	
	@Test
	@Order(1)
	public void createPatientTest() {
		
		Patient patient=new Patient(1,"ramesh","Kumar", "ramesh@gmail.com","1995-05-27","ramesh", "ramesh@123");
		
		when(repo.save(patient)).thenReturn(patient);
		
		assertEquals(patient, service.createPatient(patient));
	}
	
	@Test
	@Order(2)
	public void getPatientTest() {
		
		int patientId=1;
		
		List<Patient> records=new ArrayList<Patient>();
		
		records.add(new Patient(patientId,"ramesh","Kumar", "ramesh@gmail.com","1995-05-27","ramesh", "ramesh@123"));
		records.add(new Patient(2,"ashok","kumar", "ashok@gmail.com","1992-09-31","ashok", "ashok@123"));
		
		Optional<Patient> patient = Optional.of(new Patient(patientId,"ramesh","Kumar", "ramesh@gmail.com","1995-05-27","ramesh", "ramesh@123"));
		
		when(repo.findById(patientId)).thenReturn(patient);
		
		assertEquals(1, service.getPatientById(patientId).getPatientId());
	}
	
	@Test
	@Order(3)
	public void updatepatientTest()
	{

		Patient newRecord = new Patient(1,"Ramesh","Kumar", "ramesh@gmail.com","1995-05-27","ramesh", "ramesh@123");
		
		when(repo.save(newRecord)).thenReturn(newRecord);
		
		assertEquals(newRecord, service.updatePatient(newRecord));
	}
	
	@Test
	@Order(4)
	public void deletePatientTest()
	{
		
		Patient record = new Patient(1,"Ramesh","Kumar", "ramesh@gmail.com","1995-05-27","ramesh", "ramesh@123");
		
		service.deletePatientById(record.getPatientId());
		
		verify(repo,times(1)).deleteById(record.getPatientId());
	}
	
	@Test
	@Order(5)
	public void isPatientExistsTest(){
		
		int patientId=1;
		when(repo.existsById(patientId)).thenReturn(false);
		assertFalse(service.isPatientExists(patientId));
		when(repo.existsById(patientId)).thenReturn(true);
		assertTrue(service.isPatientExists(patientId));
	}	
}
