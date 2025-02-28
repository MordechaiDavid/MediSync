package com.medisync.entity;

import com.medisync.dto.UserDto;
import com.medisync.enums.UserType;
import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "id_number", nullable = false, unique = true)
    private String idNumber;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;
    @Enumerated(EnumType.STRING)
    private UserType role;


    public static User fromUserDto(UserDto dto){
        User user = new User(dto.getId(),
                dto.getName(),
                dto.getIdNumber(), dto.getEmail()
                , dto.getPassword(), dto.getRole());

        return user;


    }


}

