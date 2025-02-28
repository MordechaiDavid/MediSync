package com.medisync.dto;

import com.medisync.entity.Patient;
import jakarta.persistence.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {

    private Long userID;
    private Date dateOfBirth;

    public static PatientDto fromPatient(Patient patient){
        PatientDto dto = new PatientDto(patient.getUserID(), patient.getDateOfBirth());
        return dto;
    }
}


