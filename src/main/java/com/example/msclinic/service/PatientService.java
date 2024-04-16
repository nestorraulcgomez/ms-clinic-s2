package com.example.msclinic.service;
import java.util.Optional;

import com.example.msclinic.model.Patient;

import java.util.List;

public interface PatientService {
    List<Patient> getAllPatients();
    Optional<Patient> getPatientById(Long id);
    Patient createPatient(Patient patient);
    Patient updatePatient(Long id,Patient patient);
    void deletePatient(Long id);
}
