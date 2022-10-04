package com.laptrinhjavaweb.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.model.response.StaffResponseDTO;
import com.laptrinhjavaweb.repository.entity.UserEntity;
@Component
public class StaffResponseConverter {
	@Autowired
	private ModelMapper modelMapper;
	
	public StaffResponseDTO convertEntityToStaffResponseDTO (UserEntity userEntity) {
		StaffResponseDTO result = new StaffResponseDTO();
		result.setChecked("checked");
		result.setFullName(userEntity.getFullName());
		result.setStaffId(userEntity.getId());
		return result;
	}

}
