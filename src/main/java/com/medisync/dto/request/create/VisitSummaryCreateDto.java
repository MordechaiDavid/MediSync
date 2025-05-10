package com.medisync.dto.request.create;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VisitSummaryCreateDto {
    private Long doctorId;
    private Long patientId;
}
