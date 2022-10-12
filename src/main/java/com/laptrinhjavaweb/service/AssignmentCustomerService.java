package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.model.request.BuildingAssignmentRequest;
import com.laptrinhjavaweb.model.request.CustomerAssignmentRequest;

public interface AssignmentCustomerService {
	void updateAssignmentCustomer(CustomerAssignmentRequest assignmentRequest);
}
