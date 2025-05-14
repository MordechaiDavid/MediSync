package com.medisync.dto.response;

import com.medisync.dto.request.create.PrescriptionCreateDto;
import com.medisync.entity.Prescription;
import lombok.*;

import java.time.LocalDate;


@Builder
@Getter
public class PrescriptionResponseDto {
    private Long id;
    private Long medicationId;
    private Long doctorId;
    private Long patientId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String filePath;

    public static PrescriptionResponseDto fromPrescription(Prescription p){
        return PrescriptionResponseDto.builder()
                .id(p.getId()).doctorId(p.getDoctorId()).patientId(p.getPatientId())
                .endDate(p.getEndDate()).startDate(p.getStartDate())
                .medicationId(p.getMedicationId()).build();
    }

}
