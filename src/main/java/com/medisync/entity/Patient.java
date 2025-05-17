package com.medisync.entity;

import com.fasterxml.jackson.databind.annotation.EnumNaming;
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

    // TODO Add address field

    private String phone;

    @Enumerated(EnumType.STRING)
    private InsuranceInfo insuranceInfo;

    @CreationTimestamp
    private LocalDateTime createdAt;
}

