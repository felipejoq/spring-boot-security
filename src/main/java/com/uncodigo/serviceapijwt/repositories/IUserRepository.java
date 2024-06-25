package com.uncodigo.serviceapijwt.repositories;

import com.uncodigo.serviceapijwt.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(@NonNull String email);
    @NonNull
    Page<User> findAll(@NonNull Pageable pageable);
}
