package com.ltts.caseservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ltts.caseservice.model.Patient;

@Repository
public interface CaseRepository extends JpaRepository<Patient, Integer>{

}
