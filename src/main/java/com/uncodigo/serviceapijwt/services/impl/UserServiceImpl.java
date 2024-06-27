package com.uncodigo.serviceapijwt.services.impl;

import com.uncodigo.serviceapijwt.dtos.*;
import com.uncodigo.serviceapijwt.models.BankAccount;
import com.uncodigo.serviceapijwt.models.User;
import com.uncodigo.serviceapijwt.repositories.IRoleRepository;
import com.uncodigo.serviceapijwt.repositories.IUserRepository;
import com.uncodigo.serviceapijwt.services.IBankAccountService;
import com.uncodigo.serviceapijwt.services.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final IBankAccountService bankAccountService;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(IUserRepository userRepository, IRoleRepository roleRepository, IBankAccountService bankAccountService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bankAccountService = bankAccountService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public User save(UserRegisterDto userRegisterDto) {
        // Buscar si existe el usuario
        Optional<User> userOptional = userRepository.findByEmail(userRegisterDto.getEmail());
        if (userOptional.isPresent()) {
            throw new RuntimeException("El usuario ya existe");
        }

        // Crear el usuario
        User user = new User();
        user.setName(userRegisterDto.getName());
        user.setEmail(userRegisterDto.getEmail());
        user.setEnabled(true);
        // Password encoder
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        user.setRoles(roleRepository.findByName("ROLE_USER"));

        // Crear la cuenta bancaria
        User userSaved = userRepository.save(user);
        BankAccount bankAccountSaved = bankAccountService.createBankAccount(userSaved);
        userSaved.setBankAccount(bankAccountSaved);
        userSaved = userRepository.save(userSaved);


        Optional<User> userSavedOnDb = userRepository.findByEmail(userSaved.getEmail());

        if (userSavedOnDb.isPresent()) {
            return userSavedOnDb.get();
        } else {
            throw new RuntimeException("No se pudo guardar el usuario");
        }
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
