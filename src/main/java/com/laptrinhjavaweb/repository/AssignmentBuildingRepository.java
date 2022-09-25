package com.laptrinhjavaweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laptrinhjavaweb.repository.custom.AssignmentBuildingRepositoryCustom;
import com.laptrinhjavaweb.repository.entity.AssignmentBuildingEntity;

public interface AssignmentBuildingRepository extends JpaRepository<AssignmentBuildingEntity, Long>, AssignmentBuildingRepositoryCustom {
	void deleteByUserIdAndBuildingId(Long userId, Long buildingId);
//	void deleteByBuildingId(Long buildingId);

}
