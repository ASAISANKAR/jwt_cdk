package com.springsecurity_jwt.jwt.Model;

import jakarta.persistence.*;

@Entity
public class Floor{

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private int id;
  private int floorNo;
  private String companyname;
  @ManyToOne
  @JoinColumn(name = "building_id")
  private Building building;


  public Floor(int floorNo, String companyname) {
    this.floorNo = floorNo;
    this.companyname = companyname;
  }

  public Floor() {

  }

  public int getFloorNo() {
    return floorNo;
  }

  public void setFloorNo(int floorNo) {
    this.floorNo = floorNo;
  }

  public String getCompanyname() {
    return companyname;
  }

  public void setCompanyname(String companyname) {
    this.companyname = companyname;
  }

  @Override
  public String toString() {
    return "Floor{" +
        "floorNo=" + floorNo +
        ", companyname='" + companyname + '\'' +
        '}';
  }

  public void setBuilding(Building building) {
    this.building = building;
  }
}
