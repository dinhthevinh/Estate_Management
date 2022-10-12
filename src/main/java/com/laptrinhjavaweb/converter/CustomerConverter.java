package com.laptrinhjavaweb.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.builder.CustomerSearchBuilder;
import com.laptrinhjavaweb.model.CustomerDTO;
import com.laptrinhjavaweb.model.request.CustomerSearchRequest;
import com.laptrinhjavaweb.model.response.CustomerSearchResponse;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.repository.entity.CustomerEntity;
import com.laptrinhjavaweb.repository.entity.UserEntity;

@Component
public class CustomerConverter {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepository userRepository;

	public CustomerSearchBuilder convertSearchRequestToSearchBuilder(CustomerSearchRequest request) {
		CustomerSearchBuilder result = new CustomerSearchBuilder.Builder().setFullName(request.getFullName())
				.setPhone(request.getPhone()).setEmail(request.getEmail()).setStaffId(request.getStaffId()).build();
		return result;
	}

	public CustomerSearchResponse convertEntityToSearchResponse(CustomerEntity customerEntity) {
		CustomerSearchResponse result = modelMapper.map(customerEntity, CustomerSearchResponse.class);
		List<UserEntity> userEntities = customerEntity.getUsers();
		String managerNamer = "";

		List<String> fullNames = new ArrayList<String>();
		for (UserEntity item : userEntities) {
			fullNames.add(item.getFullName());
		}
		managerNamer = String.join(", ", fullNames);

		result.setManagerName(managerNamer);
		return result;
	}

	public CustomerDTO convertEntityToDTO(CustomerEntity customerEntity) {
		CustomerDTO result = modelMapper.map(customerEntity, CustomerDTO.class);
		return result;
	}

	public CustomerEntity convertDTOToEntity(CustomerDTO customerDTO) {
		CustomerEntity result = modelMapper.map(customerDTO, CustomerEntity.class);
		return result;
	}

}
