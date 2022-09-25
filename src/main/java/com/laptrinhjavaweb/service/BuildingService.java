package com.laptrinhjavaweb.service;

import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.model.BuildingDTO;
import com.laptrinhjavaweb.model.RentAreaDTO;
import com.laptrinhjavaweb.model.request.BuildingSearchRequest;
import com.laptrinhjavaweb.model.response.BuildingSearchResponse;
import com.laptrinhjavaweb.model.response.StaffResponseDTO;
import com.laptrinhjavaweb.repository.entity.BuildingEntity;

public interface BuildingService {
	List<BuildingSearchResponse> findBuilding(Map<String, String> requestParams, List<String> types);
	List<BuildingSearchResponse> findBuilding(BuildingSearchRequest request);
	List<RentAreaDTO> findRentAreaByBuildingId (Long id);
	void save(BuildingDTO buildingDTO);
	void delete (BuildingDTO buildingDTO);
	Map<String, String> getDistricts();
	Map<String, String> getBuildingTypes();
	List<BuildingDTO> findAll();
	List<StaffResponseDTO> findAssignmentBuildingByBuildingId(Long id);
	BuildingDTO findBuildingById(Long id);

}
