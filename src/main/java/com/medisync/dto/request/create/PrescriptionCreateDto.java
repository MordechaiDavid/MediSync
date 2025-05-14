package com.medisync.dto.request.create;

import lombok.*;
import java.time.LocalDate;

@Getter
public class PrescriptionCreateDto {
    private Long medicationId;
    private Long doctorId;
    private Long patientId;
    private LocalDate startDate;
    private LocalDate endDate;

}
