package com.springsecurity_jwt.jwt.Service;

import com.springsecurity_jwt.jwt.Model.Users;
import com.springsecurity_jwt.jwt.Repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

  @Autowired
  UsersRepo usersRepo;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserDetails user = usersRepo.findByUsername(username);
    if(user == null) throw new UsernameNotFoundException("User not found: " + username);
    return user;
  }
}
