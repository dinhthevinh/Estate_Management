package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhjavaweb.model.request.BuildingAssignmentRequest;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.repository.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.entity.UserEntity;
import com.laptrinhjavaweb.security.utils.SecurityUtils;
import com.laptrinhjavaweb.service.AssignmentBuildingService;

@Service
public class AssignmentBuildingServiceImpl implements AssignmentBuildingService {

	@Autowired
	private BuildingRepository buildingRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public void updateAssignmentBuilding(BuildingAssignmentRequest assignmentRequest) {
		List<String> roles = SecurityUtils.getAuthorities();
		if (roles.contains("ROLE_manager")) {
			List<Long> staffIdsNew = assignmentRequest.getStaffIdsNew();
			BuildingEntity buildingEntity = buildingRepository.findById(assignmentRequest.getBuildingId()).get();
			List<UserEntity> userEntities = new ArrayList<UserEntity>();
			for (Long item : staffIdsNew) {
				UserEntity userEntity = userRepository.findById(item).get();
				userEntities.add(userEntity);
			}
			buildingEntity.setUsers(userEntities);
			buildingRepository.save(buildingEntity);
		}
	}

}
