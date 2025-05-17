package com.medisync.dto.response;

import com.medisync.entity.Doctor;
import com.medisync.enums.DoctorTitle;
import com.medisync.enums.MedicalSpecialization;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorResponseDto {
    Long id;
    private String firstName;
    private String lastName;
    private String licenseNumber;
    private DoctorTitle title;
    private String phone;
    private String email;
    private MedicalSpecialization specialization;
    public static DoctorResponseDto fromDoctor(Doctor doctor){
        return DoctorResponseDto.builder()
                .firstName(doctor.getFirstName())
                .lastName(doctor.getLastName())
                .licenseNumber(doctor.getLicenseNumber())
                .title(doctor.getTitle())
                .email(doctor.getEmail())
                .phone(doctor.getPhone())
                .id(doctor.getId())
                .specialization(doctor.getSpecialization())
                .build();
    }
}

