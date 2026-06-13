package com.springsecurity_jwt.jwt.Model;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Users implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private int Id;
  private String username;
  private String password;

  @ElementCollection(fetch = FetchType.EAGER)
  private List<String> roles = new ArrayList<>(List.of("ROLE_USER"));

  public List<String> getRoles() {
    return roles;
  }

  public void setRoles(List<String> roles) {
    this.roles = roles;
  }

  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() { return true; }

  @Override
  public boolean isAccountNonLocked() { return true; }

  @Override
  public boolean isCredentialsNonExpired() { return true; }

  @Override
  public boolean isEnabled() { return true; }


  public void setUsername(String username) {
    this.username = username;
  }

  public int getId() {
    return Id;
  }

  public void setId(int id) {
    Id = id;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {

    return roles.stream()
        .map(SimpleGrantedAuthority::new)
        .toList();

  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}


