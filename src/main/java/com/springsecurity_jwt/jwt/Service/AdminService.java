package com.springsecurity_jwt.jwt.Service;

import com.springsecurity_jwt.jwt.Model.Users;
import com.springsecurity_jwt.jwt.Repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

  @Autowired
  UsersRepo usersRepo;

  public List<Users> getAllUsers(){
    return usersRepo.findAll();
  }

}
