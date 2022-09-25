package com.laptrinhjavaweb.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.repository.entity.UserEntity;
import com.laptrinhjavaweb.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Map<Long, String> getStaffMap() {
		Map<Long, String> result = new HashMap<>();
		List<UserEntity> staffs	 = userRepository.findByStatusAndRolesCode(1, "staff");
		for (UserEntity item : staffs) {
			result.put(item.getId(),item.getFullName());
		}
		return result;
	}

}
