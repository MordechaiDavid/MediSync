package com.medisync.entity;

import com.medisync.dto.request.create.DoctorCreateDto;
import com.medisync.dto.request.create.PatientCreateDto;
import com.medisync.enums.DoctorTitle;
import com.medisync.enums.MedicalSpecialization;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String licenseNumber;

    @Enumerated(EnumType.STRING)
    private DoctorTitle title;

    @Column(nullable = false)
    private String phone;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private MedicalSpecialization specialization;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public static Doctor fromDto(DoctorCreateDto dto){
        return Doctor.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .licenseNumber(dto.getLicenseNumber())
                .title(dto.getTitle())
                .specialization(dto.getSpecialization())
                .build();
    }



}
