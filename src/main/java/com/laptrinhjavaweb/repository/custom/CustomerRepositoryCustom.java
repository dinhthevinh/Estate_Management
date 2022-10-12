package com.laptrinhjavaweb.repository.custom;

import java.util.List;

import com.laptrinhjavaweb.builder.CustomerSearchBuilder;
import com.laptrinhjavaweb.model.request.CustomerSearchRequest;
import com.laptrinhjavaweb.repository.entity.CustomerEntity;

public interface CustomerRepositoryCustom {
	List<CustomerEntity> findCustomer (CustomerSearchBuilder builder);
}
