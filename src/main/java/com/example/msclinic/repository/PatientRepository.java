package com.example.msclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.msclinic.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
