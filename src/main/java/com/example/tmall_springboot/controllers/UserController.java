package com.example.tmall_springboot.controllers;

import com.example.tmall_springboot.domains.User;
import com.example.tmall_springboot.services.UserService;
import com.example.tmall_springboot.utils.Page4Navigator;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private static final int NAVIGATE_PAGES = 5;
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public Page4Navigator<User> list(@RequestParam(value = "start", defaultValue = "0") int start,
                                     @RequestParam(value = "size", defaultValue = "5") int size) {

        start = start < 0 ? 0 : start;
        Page<User> page = userService.pageFromJpa(start, size);

        return new Page4Navigator<>(page, NAVIGATE_PAGES);
    }
}

