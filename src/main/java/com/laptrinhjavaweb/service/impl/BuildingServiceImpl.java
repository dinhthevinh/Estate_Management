package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.model.response.BuildingSearchResponse;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.DistrictRepository;
import com.laptrinhjavaweb.repository.RentAreaRepository;
import com.laptrinhjavaweb.repository.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.entity.DistrictEntity;
import com.laptrinhjavaweb.repository.entity.RentAreaEntity;
import com.laptrinhjavaweb.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService {
	
	@Autowired
	private BuildingRepository buildingRepository;
	@Autowired
	private DistrictRepository districtRepository;
	@Autowired
	private RentAreaRepository rentAreaRopository;

	@Override
	public List<BuildingSearchResponse> findAll(Map<String, Object> requestParams, List<String> types) {
		List<BuildingSearchResponse> results = new ArrayList<>();
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(requestParams, types);	
		StringBuilder areaEmpty = new StringBuilder();	
		for (BuildingEntity item : buildingEntities) {	
			BuildingSearchResponse buildingSearchResponse = new BuildingSearchResponse();
			DistrictEntity districtEntity = districtRepository.findDistrictById(item.getDistrictId());
			List<RentAreaEntity> rentAreaEntities = rentAreaRopository.areaEmptyByBuildingId(item.getId());
			for (RentAreaEntity a : rentAreaEntities) {
				if (areaEmpty.length() > 1) {
					areaEmpty.append(", ");
				}
				areaEmpty.append(a.getValue());
			}
			buildingSearchResponse.setAreaEmpty(areaEmpty.toString());
			buildingSearchResponse.setCreatedDate(item.getCreatedDate());
			buildingSearchResponse.setName(item.getName());
			buildingSearchResponse.setAddress(item.getStreet()+", "+ item.getStreet()+", " + districtEntity.getName());
			buildingSearchResponse.setFloorArea(item.getFloorArea());
			buildingSearchResponse.setManagerName(item.getManagerName());
			buildingSearchResponse.setManagerPhone(item.getManagerPhone());
			buildingSearchResponse.setRentPrice(item.getRentPrice());
			buildingSearchResponse.setServiceFee(item.getServiceFee());
			buildingSearchResponse.setBorkerageFee(item.getBorkerageFee());
			results.add(buildingSearchResponse);
		}
		return results;
	}
	
}
