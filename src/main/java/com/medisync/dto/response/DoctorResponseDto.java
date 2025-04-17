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
public class DoctorResponseDto {
    Long id;
    private Long userId;
    private MedicalSpecialization specialization;
    public static DoctorResponseDto fromDoctor(Doctor doctor){
        DoctorResponseDto dto = new DoctorResponseDto(doctor.getId(), doctor.getUserId(), doctor.getSpecialization());
        return dto;
    }
}

