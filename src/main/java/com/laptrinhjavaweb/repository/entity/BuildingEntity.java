package com.laptrinhjavaweb.repository.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "building")
public class BuildingEntity  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @Column(name = "name", nullable = false)
    private String name; 
    
    @Column(name = "createddate")
	private String createdDate;
    
    @Column(name = "district")
	private String district;
    
    @Column(name = "ward")
	private String ward;
    
    @Column(name = "street")
	private String street;
    
    @Column(name = "managername")
	private String managerName;
    
    @Column(name = "managerphone")
	private String managerPhone;
    
    @Column(name = "floorarea")
	private Integer floorArea;
    
    @Column(name = "rentprice")
	private Integer rentPrice;
    
    @Column(name = "servicefee")
	private Integer serviceFee;
    
    @Column(name = "brokeragefee")
	private Integer brokerageFee;
    
    @Column(name = "numberofbasement")
	private Integer numberOfBasement;
    
    @Column(name = "rentpricedescription")
	private String rentPriceDescription;
    
    @Column(name = "structure")
	private String structure;
    
    @Column(name = "carfee")
	private String carFee;
    
    @Column(name = "motofee")
	private String motoFee;
    
    @Column(name = "overtimefee")
	private String overtimeFee;
    
    @Column(name = "waterfee")
	private String waterFee;
    
    @Column(name = "electricityfee")
	private String electricityFee;
    
    @Column(name = "deposit")
	private String deposit;
    
    @Column(name = "payment")
	private String payment;
    
    @Column(name = "renttime")
	private String rentTime;
    
    @Column(name = "decorationtime")
	private String decorationTime;
    
    @Column(name = "level")
	private String level;
    
    @Column(name = "direction")
	private String direction;
    
    @Column(name = "note")
	private String note;
    
    @Column(name = "type")
	private String type;
    
    @OneToMany(mappedBy = "building", fetch = FetchType.LAZY)
    private List<RentAreaEntity> rentAreas = new ArrayList<>();
    
    @OneToMany(mappedBy = "building", fetch = FetchType.LAZY)
    private List<AssignmentBuildingEntity> assignmentBuildings = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}

	public Integer getFloorArea() {
		return floorArea;
	}

	public void setFloorArea(Integer floorArea) {
		this.floorArea = floorArea;
	}

	public Integer getRentPrice() {
		return rentPrice;
	}

	public void setRentPrice(Integer rentPrice) {
		this.rentPrice = rentPrice;
	}

	public Integer getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(Integer serviceFee) {
		this.serviceFee = serviceFee;
	}

	public Integer getBrokerageFee() {
		return brokerageFee;
	}

	public void setBrokerageFee(Integer brokerageFee) {
		this.brokerageFee = brokerageFee;
	}

	public List<RentAreaEntity> getRentAreas() {
		return rentAreas;
	}

	public void setRentAreas(List<RentAreaEntity> rentAreas) {
		this.rentAreas = rentAreas;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<AssignmentBuildingEntity> getAssignmentBuildings() {
		return assignmentBuildings;
	}

	public void setAssignmentBuildings(List<AssignmentBuildingEntity> assignmentBuildings) {
		this.assignmentBuildings = assignmentBuildings;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	
    
}
