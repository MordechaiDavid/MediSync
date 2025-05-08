package com.medisync.dto.response;

import com.medisync.entity.VisitSummary;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class VisitSummaryResponseDto {
    private Long id;
    private Long doctorId;
    private Long patientId;
    private String documentUrl;
    private LocalDateTime createdAt;

    public static VisitSummaryResponseDto fromVisitSummary(VisitSummary vs){
        VisitSummaryResponseDto dto = new VisitSummaryResponseDto(
                vs.getId(), vs.getDoctorId(), vs.getPatientId(), vs.getDocumentUrl(),
                vs.getCreatedAt()
        );
        return dto;
    }
}
