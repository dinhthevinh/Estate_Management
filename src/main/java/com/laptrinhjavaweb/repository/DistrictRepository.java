package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.repository.entity.DistrictEntity;

public interface DistrictRepository {
	DistrictEntity findDistrictById (Long districtId);
}
