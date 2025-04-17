package com.medisync.dto.request.update;

import com.medisync.enums.UserType;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdateDto {
    private Long id;
    private String name;
    private String email;
    private UserType role;
}
