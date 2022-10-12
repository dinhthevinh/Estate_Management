package com.laptrinhjavaweb.repository.custom;

import java.util.List;

import com.laptrinhjavaweb.builder.CustomerSearchBuilder;
import com.laptrinhjavaweb.repository.entity.UserEntity;

public interface UserRepositoryCustom {	
	List<UserEntity> findByStatusAndRolesCode(Integer status, String roleCode);
	List<UserEntity> findCustomer(CustomerSearchBuilder customerSearchBuilder);
}
