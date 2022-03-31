package com.sprint03.controller;

import com.sprint03.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping({"/v1/user"})
public class UserController {

    private final UserService userService;
}
