package com.springsecurity_jwt.jwt.Service;

import com.springsecurity_jwt.jwt.Model.Building;
import com.springsecurity_jwt.jwt.Repository.BuildingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingService {

  @Autowired
  BuildingRepo buildingRepo;

  public List<Building> buildingList() {
    return buildingRepo.findAll();
  }

  public Building addBuilding(Building building) {
    return buildingRepo.save(building);
  }
}
