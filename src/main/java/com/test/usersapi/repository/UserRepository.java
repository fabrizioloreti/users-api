package com.test.usersapi.repository;

import com.test.usersapi.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<Users, Long> {

    List<Users> findByNameOrSurname(String name, String surname);
}
