package com.springsecurity_jwt.jwt.Repository;

import com.springsecurity_jwt.jwt.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<Users, Integer> {

  Users findByUsername(String username);

}
