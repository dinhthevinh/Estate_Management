package com.laptrinhjavaweb.model.request;

import java.util.ArrayList;
import java.util.List;

public class BuildingAssignmentRequest {
	
	private Long buildingId;
	private List<Long> staffIdsOld;
	private List<Long> staffIdsNew;
	public Long getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}
	public List<Long> getStaffIdsOld() {
		return staffIdsOld;
	}
	public void setStaffIdsOld(List<Long> staffIdsOld) {
		this.staffIdsOld = staffIdsOld;
	}
	public List<Long> getStaffIdsNew() {
		return staffIdsNew;
	}
	public void setStaffIdsNew(List<Long> staffIdsNew) {
		this.staffIdsNew = staffIdsNew;
	}

	
	
}
