package com.medisync.dto.response;

import com.medisync.entity.User;
import com.medisync.enums.UserType;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {
    private Long id;
    private String name;
    private String identityNumber;
    private LocalDate dateOfBirth;
    private String email;

    public static UserResponseDto fromUser(User user){
        UserResponseDto dto = new UserResponseDto(user.getId(), user.getName(),
                user.getIdentityNumber(), user.getDateOfBirth(),user.getEmail());
        return dto;
    }
}

