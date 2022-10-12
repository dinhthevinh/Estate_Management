package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.model.request.CustomerAssignmentRequest;
import com.laptrinhjavaweb.repository.CustomerRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.repository.entity.CustomerEntity;
import com.laptrinhjavaweb.repository.entity.UserEntity;
import com.laptrinhjavaweb.service.AssignmentCustomerService;

@Service
public class AssignmentCustomerServiceImpl implements AssignmentCustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public void updateAssignmentCustomer(CustomerAssignmentRequest assignmentRequest) {
		List<Long> staffIdsNew = assignmentRequest.getStaffIdsNew();
		CustomerEntity customerEntity = customerRepository.findById(assignmentRequest.getCustomerId()).get();
		List<UserEntity> userEntities = new ArrayList<UserEntity>();
		for (Long item : staffIdsNew) {
			UserEntity userEntity = userRepository.findById(item).get();
			userEntities.add(userEntity);
		}
		customerEntity.setUsers(userEntities);
		customerRepository.save(customerEntity);
	}

}
