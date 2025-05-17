package com.medisync.dto.request.create;

import com.medisync.enums.DoctorTitle;
import com.medisync.enums.MedicalSpecialization;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorCreateDto {
    private String firstName;
    private String lastName;
    private String licenseNumber;
    private DoctorTitle title;
    private String phone;
    private String email;
    private String password;
    private MedicalSpecialization specialization;


}
