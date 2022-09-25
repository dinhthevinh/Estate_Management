package com.laptrinhjavaweb.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.model.BuildingDTO;
import com.laptrinhjavaweb.model.response.StaffResponseDTO;
import com.laptrinhjavaweb.repository.entity.AssignmentBuildingEntity;
import com.laptrinhjavaweb.repository.entity.BuildingEntity;
@Component
public class AssignmentBuildingConverter {
	@Autowired
	private ModelMapper modelMapper;
	
	public StaffResponseDTO convertEntityToStaffResponseDTO (AssignmentBuildingEntity assignmentBuildingEntity) {
		StaffResponseDTO result = new StaffResponseDTO();
		result.setChecked("checked");
		result.setFullName(assignmentBuildingEntity.getUser().getFullName());
		result.setStaffId(assignmentBuildingEntity.getUser().getId());
		return result;
	}

}
