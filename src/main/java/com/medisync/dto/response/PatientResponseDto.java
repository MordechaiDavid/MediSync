package com.medisync.dto.response;

import com.medisync.entity.Patient;
import com.medisync.enums.BloodType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientResponseDto {
    private Long id;
    private Long userId;
    private BloodType bloodType;

    public static PatientResponseDto fromPatient(Patient patient){
        PatientResponseDto dto = new PatientResponseDto(patient.getId(), patient.getUserId(), patient.getBloodType());
        return dto;
    }
}


