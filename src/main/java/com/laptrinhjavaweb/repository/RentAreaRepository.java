package com.laptrinhjavaweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laptrinhjavaweb.repository.custom.RentAreaRepositoryCustom;
import com.laptrinhjavaweb.repository.entity.RentAreaEntity;

public interface RentAreaRepository extends JpaRepository<RentAreaEntity, Long>, RentAreaRepositoryCustom {
	List<RentAreaEntity> findByBuildingId (Long buildingId);
//	void deleteByBuildingId(Long buildingId);

}
