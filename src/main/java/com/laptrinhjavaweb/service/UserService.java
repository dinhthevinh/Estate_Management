package com.laptrinhjavaweb.service;

import java.util.Map;

import com.laptrinhjavaweb.model.UserDTO;

public interface UserService {
	Map<Long, String> getStaffMap();

	UserDTO findOneByUserNameAndStatus(String name, Integer status);
}
