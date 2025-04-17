package com.medisync.dto.request.create;

import com.medisync.enums.UserType;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateDto {
    private String name;
    private String identityNumber;
    private LocalDate dateOfBirth;
    private String email;
    private String password;
    private UserType role;

}
