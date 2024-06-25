package com.uncodigo.serviceapijwt.services;

import com.uncodigo.serviceapijwt.dtos.PageResponseDto;
import com.uncodigo.serviceapijwt.dtos.UserDto;
import com.uncodigo.serviceapijwt.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IUserService {
    Optional<User> findByEmail(String email);
    PageResponseDto<UserDto> findAll(Pageable pageable);
}
