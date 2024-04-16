package com.example.msclinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.msclinic.model.Patient;
import com.example.msclinic.service.PatientService;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/api")
public class PatientController {

    private static final Logger log = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private PatientService patientService;

    @GetMapping("/patients")
    public List<Patient> getAllPatients() {
        log.info("GET /Patients");
        log.info("Retornando todos los pacientes");
        return patientService.getAllPatients();
    }

    @GetMapping("patients/{id}")
    public ResponseEntity<Object> getPatientById(@PathVariable Long id) {
        Optional<Patient> patient = patientService.getPatientById(id);
        if (patient.isEmpty()) {
            log.error("No se encontró el paciente con ID {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontró el paciente con ID " + id));
        }
        return ResponseEntity.ok(patient);
    }

    @PostMapping("/patients")
    public ResponseEntity<Object> createPatient(@Validated @RequestBody Patient patient) {
        Patient createdPatient = patientService.createPatient(patient);
        if (createdPatient == null) {
            log.error("Error al crear el paciente {}", patient);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Error al crear el paciente"));
        }
        return ResponseEntity.ok(createdPatient);
    }
    
    @PutMapping("patients/{id}")
    public Patient updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        Patient currentPatient = patientService.getPatientById(id).get();
        if (currentPatient == null) {
            log.error("No se encontró el paciente con ID {}", id);
            return null;
        }
        return patientService.updatePatient(id, patient);
    }

    @DeleteMapping("patients/{id}")
    public void deletePatient(@PathVariable Long id){
        Patient currentPatient = patientService.getPatientById(id).get();
        if (currentPatient == null) {
            log.error("No se encontró el paciente con ID {}", id);
            return;
        }
        patientService.deletePatient(id);
    }

    static class ErrorResponse {
        private final String message;
    
        public ErrorResponse(String message) {
            this.message = message;
        }
    
        public String getMessage() {
            return message;
        }
    }


}
