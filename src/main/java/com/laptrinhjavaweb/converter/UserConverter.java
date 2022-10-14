package com.laptrinhjavaweb.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.model.UserDTO;
import com.laptrinhjavaweb.model.response.StaffResponseDTO;
import com.laptrinhjavaweb.repository.entity.UserEntity;

@Component
public class UserConverter {

	@Autowired
	private ModelMapper modelMapper;

	public UserDTO convertEntityToDTO(UserEntity entity) {
		UserDTO userDTO = modelMapper.map(entity, UserDTO.class);
		return userDTO;
	}

	public StaffResponseDTO covertEntityToStaffResponseDTO(UserEntity userEntity) {
		StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
		staffResponseDTO.setFullName(userEntity.getFullName());
		staffResponseDTO.setStaffId(userEntity.getId());
		staffResponseDTO.setChecked("");
		return staffResponseDTO;
	}

}
