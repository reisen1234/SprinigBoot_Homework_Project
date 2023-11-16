package com.example.exercise;

import com.example.exercise.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ExerciseApplicationTests {
    @Autowired
    public UserService userService;
    @Test
    void contextLoads() {
        userService.transfer(1,2,3);
    }

}
