package com.ltts.caseservice.controller;

import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltts.caseservice.config.MessageQueueConfig;
import com.ltts.caseservice.dto.PatientDto;
import com.ltts.caseservice.exception.InvalidCustomeExcetion;
import com.ltts.caseservice.model.Patient;

@RestController
@RequestMapping("patientservice")
public class GatewayController {
	
	@Autowired
	ModelMapper modelmapper;
	
	@Autowired
	private RabbitTemplate template;
	
	@PostMapping(path = "register", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PatientDto> registerPatient(@RequestBody @Valid PatientDto patientDto)
	{
		PatientDto patientResponse = (PatientDto) template.convertSendAndReceive(MessageQueueConfig.EXCHANGE,MessageQueueConfig.CREATE_ROUTING_KEY,patientDto);
		
		return new ResponseEntity<>(patientResponse,HttpStatus.ACCEPTED) ;
	}
	
	@PutMapping(path = "update/{patientId}",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Patient> updatePatient(@PathVariable("patientId") int id,@RequestBody @Valid PatientDto patientdto) throws InvalidCustomeExcetion
	{
		
		Patient patient = modelmapper.map(patientdto, Patient.class);
		
		patient.setPatientId(id);
		
		Patient updatedPatient =(Patient) template.convertSendAndReceive(MessageQueueConfig.EXCHANGE,MessageQueueConfig.UPDATE_ROUTING_KEY,patient);
		
		if(updatedPatient == null)
		{
			throw new InvalidCustomeExcetion();
		}
		else
		{
			return new ResponseEntity<>(updatedPatient,HttpStatus.ACCEPTED);
		}
			
	}
	
	
	@GetMapping("get/{patientId}")
	public ResponseEntity<Patient> getPatient(@PathVariable("patientId") int id) throws InvalidCustomeExcetion
	{
		Patient patient =(Patient) template.convertSendAndReceive(MessageQueueConfig.EXCHANGE,MessageQueueConfig.GET_ROUTING_KEY,id);

		if(patient.getPatientId() == 0)
		{
			throw new InvalidCustomeExcetion();
		}	
		
		else {
			return new ResponseEntity<>(patient,HttpStatus.ACCEPTED);
		}
	}
	
	@DeleteMapping("delete/{patientId}")
	public ResponseEntity<Object> deletePatient(@PathVariable("patientId") int id)
	{
		template.convertAndSend(MessageQueueConfig.EXCHANGE,MessageQueueConfig.DELETE_ROUTING_KEY, id);
		
		return new ResponseEntity<>("sent successfully",HttpStatus.ACCEPTED);
	}

}
