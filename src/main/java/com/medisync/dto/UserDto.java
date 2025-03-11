package com.medisync.dto;

import com.medisync.entity.User;
import com.medisync.enums.UserType;
import jakarta.persistence.Column;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto implements Serializable {
    private String name;
    private String idNumber;
    private LocalDate dateOfBirth;
    private String email;
    private String password;
    private UserType role;

    public static UserDto fromUser(User user){
        UserDto dto = new UserDto(user.getName(),
                user.getIdNumber(), user.getDateOfBirth(),user.getEmail(), user.getPassword(), user.getRole());
        return dto;
    }
}
