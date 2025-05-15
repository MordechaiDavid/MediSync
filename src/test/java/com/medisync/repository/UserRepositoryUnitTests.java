package com.medisync.repository;

import com.medisync.entity.User;
import com.medisync.enums.UserType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserRepositoryUnitTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    @DisplayName("Test 1:Save Employee Test")
    public void saveEmployeeTest(){
        User user = User.builder()
                .name("Sam")
                .email("sam@gmail.com")
                .identityNumber("4366654324")
                .role(UserType.PATIENT)
                .dateOfBirth(LocalDate.now())
                .password("samPass12")
                .build();
        userRepository.save(user);
        System.out.println(user);
        Assertions.assertThat(user.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getUserTest(){
        User user = userRepository.findById(1L).get();
        System.out.println(user);
        Assertions.assertThat(user.getId()).isEqualTo(1L);
    }




}
