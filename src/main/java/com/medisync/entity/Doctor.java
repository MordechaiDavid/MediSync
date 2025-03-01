package com.medisync.entity;

import com.medisync.dto.DoctorDto;
import com.medisync.enums.MedicalSpecialization;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "doctors")
public class Doctor {
    @Id
    private Long userId;

    @Enumerated(EnumType.STRING)
    private MedicalSpecialization specialization;

    public Doctor(Long userId){
        this.userId = userId;
    }

    public static Doctor fromDoctorDto(DoctorDto dto){
        Doctor doctor = new Doctor();
        doctor.setSpecialization(dto.getSpecialization());
        return doctor;
    }

}
