package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.model.RentAreaDTO;

public interface RentAreaService {
	List<RentAreaDTO> findRentAreaByBuildingId (Long id);
}
