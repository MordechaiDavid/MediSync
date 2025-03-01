package com.medisync.dto;

import com.medisync.entity.Doctor;
import com.medisync.enums.MedicalSpecialization;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto {
    private MedicalSpecialization specialization;

    public static DoctorDto fromDoctor(Doctor doctor){
        DoctorDto dto = new DoctorDto(doctor.getSpecialization());
        return dto;
    }
}

