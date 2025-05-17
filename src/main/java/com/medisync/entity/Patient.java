package com.medisync.entity;

import com.fasterxml.jackson.databind.annotation.EnumNaming;
import com.medisync.dto.request.create.PatientCreateDto;
import com.medisync.enums.BloodType;
import com.medisync.enums.Gender;
import com.medisync.enums.InsuranceInfo;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    // TODO Add address, password and email field
    @Column(nullable = false)
    private String phone;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private InsuranceInfo insuranceInfo;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public static Patient fromDto(PatientCreateDto dto){
        return Patient.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .gender(dto.getGender())
                .insuranceInfo(dto.getInsuranceInfo())
                .phone(dto.getPhone())
                .dateOfBirth(dto.getDateOfBirth())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }
}

