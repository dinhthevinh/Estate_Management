package com.laptrinhjavaweb.repository.custom;

import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.model.request.BuildingSearchRequest;
import com.laptrinhjavaweb.repository.entity.BuildingEntity;

public interface BuildingRepositoryCustom {
	List<BuildingEntity> findBuilding(Map<String, String> requestParams, List<String> types);
//	BuildingEntity findById(Long id);
//	void save(BuildingEntity newBuilding);
//	void delete (BuildingEntity buildingEntity);
	List<BuildingEntity> findBuilding(BuildingSearchBuilder builder);
}
