package com.laptrinhjavaweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laptrinhjavaweb.repository.custom.UserRepositoryCustom;
import com.laptrinhjavaweb.repository.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>, UserRepositoryCustom{
	//List<UserEntity> findByStatusAndRoles_Code(Integer status, String roleCode);
}
