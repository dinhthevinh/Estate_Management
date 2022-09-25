package com.laptrinhjavaweb.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.model.RentAreaDTO;
import com.laptrinhjavaweb.repository.entity.RentAreaEntity;

@Component
public class RentAreaConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public RentAreaDTO convertEntityToDto (RentAreaEntity entity) {
		RentAreaDTO result = modelMapper.map(entity, RentAreaDTO.class);
		return result;
	}
}
