package com.laptrinhjavaweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laptrinhjavaweb.repository.custom.CustomerRepositoryCustom;
import com.laptrinhjavaweb.repository.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>, CustomerRepositoryCustom {
	void deleteByIdIn(List<Long> ids);
}
