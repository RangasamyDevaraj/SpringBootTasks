package com.ltts.caseservice.controller;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.ltts.caseservice.config.MessageQueueConfig;
import com.ltts.caseservice.dto.PatientDto;
import com.ltts.caseservice.exceptions.NoSuchElementFoundException;
import com.ltts.caseservice.model.Patient;
import com.ltts.caseservice.service.PatientService;




@RestController
public class CaseServiceContoller {

	@Autowired
	ModelMapper modelmapper;
	
	@Autowired
	PatientService service;

	
	Logger logger=LoggerFactory.getLogger(CaseServiceContoller.class);
	
	@RabbitListener(queues =MessageQueueConfig.CREATE_QUEUE,returnExceptions = "true")
	public PatientDto createListener(PatientDto patientrequest) 
	{
		
		
		Patient patienRequest = modelmapper.map(patientrequest, Patient.class);
		
		logger.info(patientrequest.toString());
		
		Patient newpatient = service.createPatient(patienRequest);

		PatientDto patientResponse = modelmapper.map(newpatient, PatientDto.class);
		
		logger.info(patientResponse.toString());
	
		return patientrequest;
		
	}
	
	@RabbitListener(queues = MessageQueueConfig.UPDATE_QUEUE)
	public Patient updateListener(Patient patient)
	{
		boolean isPatientExist = service.isPatientExists(patient.getPatientId());
		
		Patient newpatient;
		
		if(isPatientExist)
		{
			newpatient = service.updatePatient(patient);
			 
			logger.info(patient.toString());
		}else {
			 newpatient = null;
		}
		
		return newpatient;
	}
	
	@RabbitListener(queues = MessageQueueConfig.GET_QUEUE,returnExceptions = "true")
	public Patient getListener(int id)
	{	
		
		Patient newpatient = service.getPatientById(id);
	
			logger.info(newpatient.toString());
			
			return newpatient;
		
		
	}
	
	@RabbitListener(queues = MessageQueueConfig.DELETE_QUEUE,returnExceptions = "true")
	public void deleteListener(int id) throws NoSuchElementFoundException
	{
		boolean isPatientExsists = service.isPatientExists(id);
		
		if(isPatientExsists)
		{
			logger.info("Deleted successfully");
			
			service.deletePatientById(id);
			
		}else {
			
			throw new NoSuchElementFoundException();
		}
		
		
	}
}
