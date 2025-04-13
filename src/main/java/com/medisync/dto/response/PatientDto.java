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
public class PatientDto {
    private Long id;
    private Long userId;
    private BloodType bloodType;

    public static PatientDto fromPatient(Patient patient){
        PatientDto dto = new PatientDto(patient.getId(), patient.getUserId(), patient.getBloodType());
        return dto;
    }
}


