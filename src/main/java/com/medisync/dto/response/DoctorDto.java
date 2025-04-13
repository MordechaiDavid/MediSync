package com.medisync.dto.response;

import com.medisync.entity.Doctor;
import com.medisync.enums.MedicalSpecialization;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto {
    Long id;
    private Long userId;
    private MedicalSpecialization specialization;
    public static DoctorDto fromDoctor(Doctor doctor){
        DoctorDto dto = new DoctorDto(doctor.getId(), doctor.getUserId(), doctor.getSpecialization());
        return dto;
    }
}

