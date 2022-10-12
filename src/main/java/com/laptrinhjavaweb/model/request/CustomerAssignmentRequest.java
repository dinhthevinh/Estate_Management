package com.laptrinhjavaweb.model.request;

import java.util.List;

public class CustomerAssignmentRequest {
	
	private Long customerId;
	private List<Long> staffIdsOld;
	private List<Long> staffIdsNew;
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
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
