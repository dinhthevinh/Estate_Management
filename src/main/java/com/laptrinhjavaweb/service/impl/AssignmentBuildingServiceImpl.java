package com.laptrinhjavaweb.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhjavaweb.model.request.BuildingAssignmentRequest;
import com.laptrinhjavaweb.repository.AssignmentBuildingRepository;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.repository.entity.AssignmentBuildingEntity;
import com.laptrinhjavaweb.repository.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.entity.UserEntity;
import com.laptrinhjavaweb.service.AssignmentBuildingService;

@Service
public class AssignmentBuildingServiceImpl implements AssignmentBuildingService{

	@Autowired
	private AssignmentBuildingRepository assignmentBuildingRepository;
	@Autowired
	private BuildingRepository buildingRepository;
	@Autowired
	private UserRepository userRepository;
	@Override
	@Transactional
	public void updateAssignmentBuilding(BuildingAssignmentRequest assignmentRequest) {
		List<Long> staffIdsOld = assignmentRequest.getStaffIdsOld();
		List<Long> staffIdsNew = assignmentRequest.getStaffIdsNew();
		for (int i = 0; i < staffIdsNew.size(); i++) {
			if (staffIdsOld.contains(staffIdsNew.get(i))) {
				staffIdsOld.remove(staffIdsNew.get(i));
				staffIdsNew.remove(staffIdsNew.get(i));
				i--;
			}
		}
		for (Long item:staffIdsNew) {
			AssignmentBuildingEntity assignmentBuildingEntity = new AssignmentBuildingEntity();
			BuildingEntity buildingEntity = buildingRepository.findById(assignmentRequest.getBuildingId()).get();
			UserEntity userEntity = userRepository.findById(item).get();
			assignmentBuildingEntity.setUser(userEntity);
			assignmentBuildingEntity.setBuilding(buildingEntity);
			assignmentBuildingRepository.save(assignmentBuildingEntity);	
		}
		for (Long itemOld:staffIdsOld) {
			AssignmentBuildingEntity assignmentBuildingEntity = new AssignmentBuildingEntity();
			/*BuildingEntity buildingEntity = buildingRepository.findById(assignmentRequest.getBuildingId()).get();
			UserEntity userEntity = userRepository.findById(itemOld).get();
			assignmentBuildingEntity.setUser(userEntity);
			assignmentBuildingEntity.setBuilding(buildingEntity);
			assignmentBuildingEntity.setId(14L);*/
			assignmentBuildingRepository.deleteByUserIdAndBuildingId(itemOld, assignmentRequest.getBuildingId());
		}
		
	}

		
	
	
}
