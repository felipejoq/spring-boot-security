package com.uncodigo.serviceapijwt.repositories;

import com.uncodigo.serviceapijwt.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByName(String name);
}
