package com.uncodigo.serviceapijwt.services.impl;

import com.uncodigo.serviceapijwt.dtos.PageResponseDto;
import com.uncodigo.serviceapijwt.dtos.UserDto;
import com.uncodigo.serviceapijwt.models.User;
import com.uncodigo.serviceapijwt.repositories.IUserRepository;
import com.uncodigo.serviceapijwt.services.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;

    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public PageResponseDto<UserDto> findAll(Pageable pageable) {
        Page<User> page = userRepository.findAll(pageable);
        Page<UserDto> userDtoPage = page.map(UserDto::fromUser);
        return new PageResponseDto<>(userDtoPage);
    }
}
