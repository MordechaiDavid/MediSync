package com.medisync.entity;

import com.medisync.enums.DosageUnit;
import com.medisync.enums.UnitType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double dosageAmount;

    @Enumerated(EnumType.STRING)
    private DosageUnit dosageUnit;

    private Integer unitsPerPack;

    @Enumerated(EnumType.STRING)
    private UnitType unitType;



}
