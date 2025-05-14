package com.medisync.entity;

import com.medisync.dto.request.create.UserCreateDto;
import com.medisync.dto.request.update.UserUpdateDto;
import com.medisync.enums.UserType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String identityNumber;

    private LocalDate dateOfBirth;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserType role;

    @CreationTimestamp
    private LocalDateTime createdAt;


    public static User fromUserDto(UserCreateDto dto){
        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .role(dto.getRole())
                .dateOfBirth(dto.getDateOfBirth())
                .identityNumber(dto.getIdentityNumber())
                .build();
        return user;
    }
    public static User fromUserDto(UserUpdateDto dto){
        User user = User.builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .role(dto.getRole())
                .build();
        return user;
    }


}

