package com.example.msclinic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.msclinic.model.Patient;
import com.example.msclinic.repository.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    @Override
    public Patient createPatient(Patient patient){
        return patientRepository.save(patient);
    }

    @Override
    public Patient updatePatient(Long id, Patient patient){
        if(patientRepository.existsById(id)){
            patient.setId(id);
            return patientRepository.save(patient);
        } 
        return null;
    }

    @Override
    public void deletePatient(Long id){
        patientRepository.deleteById(id);
    }
}
