package com.medisync.entity;

import com.medisync.dto.request.UserCreateDto;
import com.medisync.dto.request.UserUpdateDto;
import com.medisync.enums.UserType;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "identity_number", nullable = false, unique = true)
    private String identityNumber;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;
    @Enumerated(EnumType.STRING)
    private UserType role;


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

