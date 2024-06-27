package com.uncodigo.serviceapijwt.controllers;

import com.uncodigo.serviceapijwt.dtos.PageResponseDto;
import com.uncodigo.serviceapijwt.dtos.UserDto;
import com.uncodigo.serviceapijwt.dtos.UserRegisterDto;
import com.uncodigo.serviceapijwt.models.User;
import com.uncodigo.serviceapijwt.services.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<?> createUser(@RequestBody UserRegisterDto userRegisterDto) {
        try {
            log.info("Creating user");
            User userSaved = userService.save(userRegisterDto);
            return new ResponseEntity<>(UserDto.fromUser(userSaved), HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Error al crear el usuario");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
