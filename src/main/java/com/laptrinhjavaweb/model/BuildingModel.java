package com.laptrinhjavaweb.model;

public class BuildingModel {
	
	private String createdDate;
	private String name;
	private String address;
	private String managerName;
	private String managerPhone;
	private Integer floorArea;
	private String areaEmpty;
	private Integer rentPrice;
	private Integer serviceFee;
	private Integer borkerageFee;
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getAreaEmpty() {
		return areaEmpty;
	}
	public void setAreaEmpty(String areaEmpty) {
		this.areaEmpty = areaEmpty;
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
	public Integer getBorkerageFee() {
		return borkerageFee;
	}
	public void setBorkerageFee(Integer borkerageFee) {
		this.borkerageFee = borkerageFee;
	}

}
