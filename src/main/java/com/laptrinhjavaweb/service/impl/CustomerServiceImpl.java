package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.model.request.CustomerSearchRequest;
import com.laptrinhjavaweb.model.response.CustomerSearchResponse;
import com.laptrinhjavaweb.repository.entity.CustomerEntity;
import com.laptrinhjavaweb.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Override
	public List<CustomerSearchResponse> findCustomer(CustomerSearchRequest customerSearchRequest) {
		List<CustomerSearchResponse> results = new ArrayList<>();
		return results;
	}
	
	
}
