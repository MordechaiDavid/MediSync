package com.medisync.dto;

import com.medisync.entity.User;
import com.medisync.enums.UserType;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {
    private Long id;

    private String name;

    private String idNumber;

    private String email;

    private String password;

    private UserType role;

    public static UserDto fromUser(User user){
        UserDto dto = new UserDto(user.getId(), user.getName(),
                user.getIdNumber(), user.getEmail(), user.getPassword(), user.getRole());
        return dto;
    }
}
