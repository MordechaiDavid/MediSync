package com.medisync.dto.response;

import com.medisync.entity.Patient;
import com.medisync.enums.BloodType;
import com.medisync.enums.Gender;
import com.medisync.enums.InsuranceInfo;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Gender gender;
    // TODO Add address and email field
    private String phone;
    private String email;
    private InsuranceInfo insuranceInfo;
    private LocalDateTime createdAt;

    public static PatientResponseDto fromPatient(Patient patient){
        return PatientResponseDto.builder()
                .id(patient.getId())
                .createdAt(patient.getCreatedAt())
                .gender(patient.getGender())
                .dateOfBirth(patient.getDateOfBirth())
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .phone(patient.getPhone())
                .insuranceInfo(patient.getInsuranceInfo())
                .email(patient.getEmail())
                .build();

    }

}


