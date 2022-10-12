package com.laptrinhjavaweb.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.converter.UserConverter;
import com.laptrinhjavaweb.model.UserDTO;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.repository.entity.UserEntity;
import com.laptrinhjavaweb.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserConverter userConverter;

	@Override
	public Map<Long, String> getStaffMap() {
		Map<Long, String> result = new HashMap<>();
		List<UserEntity> staffs = userRepository.findByStatusAndRolesCode(1, "staff");
		for (UserEntity item : staffs) {
			result.put(item.getId(), item.getFullName());
		}
		return result;
	}

	@Override
	public UserDTO findOneByUserNameAndStatus(String name, Integer status) {
		UserEntity entity = userRepository.findOneByUserNameAndStatus(name, status);
		UserDTO userDTO = userConverter.convertEntityToDTO(entity);
		return userDTO;
	}
}
