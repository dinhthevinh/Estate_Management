package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhjavaweb.builder.CustomerSearchBuilder;
import com.laptrinhjavaweb.converter.CustomerConverter;
import com.laptrinhjavaweb.converter.StaffResponseConverter;
import com.laptrinhjavaweb.converter.UserConverter;
import com.laptrinhjavaweb.enums.TransactionTypesEnum;
import com.laptrinhjavaweb.model.CustomerDTO;
import com.laptrinhjavaweb.model.MyUserDetail;
import com.laptrinhjavaweb.model.request.CustomerSearchRequest;
import com.laptrinhjavaweb.model.response.CustomerSearchResponse;
import com.laptrinhjavaweb.model.response.StaffResponseDTO;
import com.laptrinhjavaweb.repository.CustomerRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.repository.entity.CustomerEntity;
import com.laptrinhjavaweb.repository.entity.UserEntity;
import com.laptrinhjavaweb.security.utils.SecurityUtils;
import com.laptrinhjavaweb.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private CustomerConverter customerConverter;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserConverter userConverter;
	@Autowired
	private StaffResponseConverter staffResponseConverter;

	@Override
	public List<CustomerSearchResponse> findCustomer(CustomerSearchRequest customerSearchRequest) {
		List<String> roles = SecurityUtils.getAuthorities();
		List<CustomerSearchResponse> results = new ArrayList<>();
		if (roles.contains("ROLE_staff")) {
			MyUserDetail myUserDetail = MyUserDetail.class
					.cast(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
			customerSearchRequest.setStaffId(myUserDetail.getId());
		}
		CustomerSearchBuilder builder = customerConverter.convertSearchRequestToSearchBuilder(customerSearchRequest);
		List<CustomerEntity> customerEntities = customerRepository.findCustomer(builder);
		for (CustomerEntity item : customerEntities) {
			CustomerSearchResponse customerSearchResponse = customerConverter.convertEntityToSearchResponse(item);
			results.add(customerSearchResponse);
		}

		return results;
	}

	@Override
	public List<StaffResponseDTO> findAssignmentCustomerByCustomerId(Long id) {
		CustomerEntity customerEntity = customerRepository.findById(id).get();
		List<StaffResponseDTO> results = new ArrayList<>();
		List<UserEntity> userEntities = customerEntity.getUsers();
		List<UserEntity> staffs = userRepository.findByStatusAndRolesCode(1, "staff");
		List<Long> customerIds = new ArrayList<>();
		for (UserEntity item : userEntities) {
			results.add(staffResponseConverter.convertEntityToStaffResponseDTO(item));
			customerIds.add(item.getId());
		}
		for (UserEntity item : staffs) {
			if (!customerIds.contains(item.getId())) {
				StaffResponseDTO staffResponseDTO = userConverter.covertEntityToStaffResponseDTO(item);
				results.add(staffResponseDTO);
			}
		}
		return results;

	}

	@Override
	public CustomerDTO findCustomerById(Long customerid) {
		CustomerEntity customerEntity = customerRepository.findById(customerid).get();
		CustomerDTO result = customerConverter.convertEntityToDTO(customerEntity);
		return result;
	}

	@Override
	@Transactional
	public void save(CustomerDTO customerDTO) {
		CustomerEntity customerEntity = customerConverter.convertDTOToEntity(customerDTO);
		if (customerDTO.getId() != null) {
			CustomerEntity customerEntityOld = customerRepository.findById(customerDTO.getId()).get();
			customerEntity.setUsers(customerEntityOld.getUsers());
			customerEntity.setTransactions(customerEntityOld.getTransactions());
		}
		customerRepository.save(customerEntity);
	}

	@Override
	@Transactional
	public void delete(CustomerDTO customerDTO) {
		List<String> roles = SecurityUtils.getAuthorities();
		if (roles.contains("ROLE_manager")) {
			List<Long> customerIds = customerDTO.getCustomerIds();
			customerRepository.deleteByIdIn(customerIds);
		}
	}

	@Override
	public Map<String, String> getTransactionTypes() {
		Map<String, String> getTransactionTypes = new HashMap<>();
		for (TransactionTypesEnum item : TransactionTypesEnum.values()) {
			getTransactionTypes.put(item.toString(), item.getTransactionValue());
		}
		return getTransactionTypes;
	}

}
