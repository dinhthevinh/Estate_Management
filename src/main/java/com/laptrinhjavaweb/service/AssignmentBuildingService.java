package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.model.request.BuildingAssignmentRequest;
import com.laptrinhjavaweb.repository.entity.BuildingEntity;

public interface AssignmentBuildingService {
	void updateAssignmentBuilding(BuildingAssignmentRequest assignmentRequest);
}
