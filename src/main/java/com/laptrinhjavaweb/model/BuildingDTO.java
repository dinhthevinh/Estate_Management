package com.laptrinhjavaweb.model;

import java.util.List;

public class BuildingDTO {
	
	private Long id;
	private String name;
	private String district;
	private String ward;
	private String street;
	private String managerName;
	private String managerPhone;
	private Integer floorArea;
	private Integer rentPrice;
	private String areaEmpty;	
	private Integer serviceFee;
	private Integer brokerageFee;
	private Integer numberOfBasement;
	private String rentPriceDescription;
	private String structure;
	private String carFee;
	private String motoFee;
	private String overtimeFee;
	private String waterFee;
	private String electricityFee;
	private String deposit;
	private String payment;
	private String rentTime;
	private String decorationTime;
	private String note;
	private String direction;
	private String level;
	private Long staffId;
	private String rentArea;
	private List<String> types;
	private List<Long> buildingIds;
	private String address;
	


	public List<Long> getBuildingIds() {
		return buildingIds;
	}
	public void setBuildingIds(List<Long> buildingIds) {
		this.buildingIds = buildingIds;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
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
	public Integer getBrokerageFee() {
		return brokerageFee;
	}
	public void setBrokerageFee(Integer brokerageFee) {
		this.brokerageFee = brokerageFee;
	}
	public Long getStaffId() {
		return staffId;
	}
	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}
	public void setNumberOfBasement(Integer numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}
	public String getRentPriceDescription() {
		return rentPriceDescription;
	}
	public void setRentPriceDescription(String rentPriceDescription) {
		this.rentPriceDescription = rentPriceDescription;
	}
	public String getStructure() {
		return structure;
	}
	public void setStructure(String structure) {
		this.structure = structure;
	}
	public String getCarFee() {
		return carFee;
	}
	public void setCarFee(String carFee) {
		this.carFee = carFee;
	}
	public String getMotoFee() {
		return motoFee;
	}
	public void setMotoFee(String motoFee) {
		this.motoFee = motoFee;
	}
	public String getOvertimeFee() {
		return overtimeFee;
	}
	public void setOvertimeFee(String overtimeFee) {
		this.overtimeFee = overtimeFee;
	}
	public String getWaterFee() {
		return waterFee;
	}
	public void setWaterFee(String waterFee) {
		this.waterFee = waterFee;
	}
	public String getElectricityFee() {
		return electricityFee;
	}
	public void setElectricityFee(String electricityFee) {
		this.electricityFee = electricityFee;
	}
	public String getDeposit() {
		return deposit;
	}
	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getRentTime() {
		return rentTime;
	}
	public void setRentTime(String rentTime) {
		this.rentTime = rentTime;
	}
	public String getDecorationTime() {
		return decorationTime;
	}
	public void setDecorationTime(String decorationTime) {
		this.decorationTime = decorationTime;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public List<String> getTypes() {
		return types;
	}
	public void setTypes(List<String> types) {
		this.types = types;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getRentArea() {
		return rentArea;
	}
	public void setRentArea(String rentArea) {
		this.rentArea = rentArea;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	


	

}
