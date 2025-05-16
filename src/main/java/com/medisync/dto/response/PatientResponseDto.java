package com.medisync.dto.response;

import com.medisync.entity.Patient;
import com.medisync.enums.BloodType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientResponseDto {
    private Long id;
    private Long userId;
    private BloodType bloodType;

    public static PatientResponseDto fromPatient(Patient patient){
        return PatientResponseDto.builder()
                .id(patient.getId()).build();
    }
}


