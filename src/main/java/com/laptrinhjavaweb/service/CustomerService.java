package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.model.request.CustomerSearchRequest;
import com.laptrinhjavaweb.model.response.CustomerSearchResponse;

public interface CustomerService {
	List<CustomerSearchResponse> findCustomer (CustomerSearchRequest customerSearchRequest);
}
