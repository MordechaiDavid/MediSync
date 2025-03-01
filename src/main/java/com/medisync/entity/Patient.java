package com.medisync.entity;

import com.medisync.dto.PatientDto;
import com.medisync.enums.BloodType;
import com.medisync.enums.MedicalSpecialization;
import jakarta.persistence.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "patients")
public class Patient {
    @Id
    private Long userId;
    @Enumerated(EnumType.STRING)
    private BloodType bloodType;

    public Patient(Long userId) {
        this.userId = userId;
    }

    public static Patient fromPatientDto(PatientDto dto){
        Patient patient = new Patient();
        patient.setBloodType(dto.getBloodType());
        return patient;
    }
}

