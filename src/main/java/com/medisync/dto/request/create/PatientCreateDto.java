package com.medisync.dto.request.create;

import com.medisync.enums.Gender;
import com.medisync.enums.InsuranceInfo;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientCreateDto {

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private Gender gender;

    // TODO Add password, address and email field
    private String phone;

    private String email;

    private String password;

    private InsuranceInfo insuranceInfo;
}
