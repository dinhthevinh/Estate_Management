package com.laptrinhjavaweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laptrinhjavaweb.repository.custom.UserRepositoryCustom;
import com.laptrinhjavaweb.repository.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>, UserRepositoryCustom {
	UserEntity findOneByUserNameAndStatus(String name, Integer status);

	List<UserEntity> findByIdIn(List<Long> staffIdsNew);
}
