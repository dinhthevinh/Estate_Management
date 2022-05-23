package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.model.response.BuildingSearchResponse;

public interface BuildingService {
	List<BuildingSearchResponse> findAll(String district, List<String> types, Long staffid, String name, Integer floorarea,
			String ward, String street, Integer numberofbasement, String direction, String level, Integer rentareafrom,
			Integer rentareato, Integer rentpricefrom, Integer rentpriceto, String managername, String managerphone);

}
