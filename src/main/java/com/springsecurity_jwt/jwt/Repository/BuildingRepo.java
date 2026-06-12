package com.springsecurity_jwt.jwt.Repository;

import com.springsecurity_jwt.jwt.Model.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepo extends JpaRepository<Building, Integer> {

}
