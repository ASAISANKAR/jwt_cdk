package com.springsecurity_jwt.jwt.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Building {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private int id;

  private String name;
  private int nooffloors;
  @OneToMany(mappedBy = "building", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Floor> floors = new ArrayList<>();



  public void setFloors(List<Floor> floors) {
    this.floors = floors;

    for (Floor floor : floors) {
      floor.setBuilding(this);
    }
  }



  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getNooffloors() {
    return nooffloors;
  }

  public void setNooffloors(int nooffloors) {
    this.nooffloors = nooffloors;
  }

  public List<Floor> getFloors() {
    return floors;
  }


  @Override
  public String toString() {
    return "Building{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", nooffloors=" + nooffloors +
        ", floors=" + floors +
        '}';
  }
}
