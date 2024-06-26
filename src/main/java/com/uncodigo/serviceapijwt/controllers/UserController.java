package com.uncodigo.serviceapijwt.controllers;

import com.uncodigo.serviceapijwt.dtos.PageResponseDto;
import com.uncodigo.serviceapijwt.dtos.UserDto;
import com.uncodigo.serviceapijwt.dtos.UserRegisterDto;
import com.uncodigo.serviceapijwt.models.User;
import com.uncodigo.serviceapijwt.services.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/", ""})
    public PageResponseDto<UserDto> getUsers(Pageable pageable) {
        log.info("Getting all users");
        return userService.findAll(pageable);
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(UserRegisterDto userRegisterDto) {
        log.info("Creating user");

        return new ResponseEntity<>(userRegisterDto, HttpStatus.CREATED);
    }
}
