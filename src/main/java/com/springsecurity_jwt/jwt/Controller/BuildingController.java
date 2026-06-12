package com.springsecurity_jwt.jwt.Controller;

import com.springsecurity_jwt.jwt.Model.Building;
import com.springsecurity_jwt.jwt.Model.Users;
import com.springsecurity_jwt.jwt.Service.BuildingService;
import com.springsecurity_jwt.jwt.Service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.security.Principal;
import java.util.List;

@RestController
public class BuildingController {

  @Autowired
  BuildingService buildingService;

  @Autowired
  JWTService jwtService;

    @GetMapping("/")
    public String buildingHome(){
      return "<h1 align = 'center'>Welcome to Building Home Page</h1>";
    }

    @GetMapping("/building")
    public List<Building> buildingList(){
      return buildingService.buildingList();
    }

    @GetMapping("/csrf-token")
    public CsrfToken csrfToken(CsrfToken csrfToken){
      return csrfToken;
    }

    @PostMapping("/addBuilding")
    public String addBuilding(@RequestBody Building building){
      Building buildingResponse = buildingService.addBuilding(building);
      if(buildingResponse.equals(building))
        return "Building added successfully";
      else
        return "Failed to add building";
    }

    @GetMapping("/myjwt")
    public String getMyJwtToken(Principal principal)
    {
      return jwtService.generateToken(principal.getName());
    }
}
