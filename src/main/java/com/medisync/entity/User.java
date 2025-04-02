package com.medisync.entity;

import com.medisync.dto.UserDto;
import com.medisync.enums.UserType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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


    public static User fromUserDto(UserDto dto){
        User user = new User(null,
                dto.getName(),
                dto.getIdentityNumber(), dto.getDateOfBirth(),dto.getEmail()
                , dto.getPassword(), dto.getRole());

        return user;


    }


}

