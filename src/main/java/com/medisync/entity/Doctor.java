package com.medisync.entity;

import com.medisync.enums.MedicalSpecialization;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    @Enumerated(EnumType.STRING)
    private MedicalSpecialization specialization;

    public Doctor(Long userId){
        this.userId = userId;
    }



}
