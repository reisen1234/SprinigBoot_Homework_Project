package com.example.exercise.controller;

import io.swagger.annotations.Api;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Api(tags = "用户管理接口")
public class UserController {

}
