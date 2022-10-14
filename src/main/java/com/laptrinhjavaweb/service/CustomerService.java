package com.laptrinhjavaweb.service;

import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.model.CustomerDTO;
import com.laptrinhjavaweb.model.request.CustomerSearchRequest;
import com.laptrinhjavaweb.model.response.CustomerSearchResponse;
import com.laptrinhjavaweb.model.response.StaffResponseDTO;

public interface CustomerService {
	List<CustomerSearchResponse> findCustomer(CustomerSearchRequest customerSearchRequest);

	List<StaffResponseDTO> findAssignmentCustomerByCustomerId(Long id);

	CustomerDTO findCustomerById(Long customerid);

	void save(CustomerDTO customerDTO);

	void delete(CustomerDTO customerDTO);

	Map<String, String> getTransactionTypes();
}
