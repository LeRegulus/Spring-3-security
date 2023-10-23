package com.regulus.sms.repositories;

import com.regulus.sms.models.entities.Role;
import com.regulus.sms.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    User findByRoles(Role role);
}
