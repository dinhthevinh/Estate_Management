package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.builder.BuildingSearchBuilder.Builder;
import com.laptrinhjavaweb.converter.AssignmentBuildingConverter;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.converter.RentAreaConverter;
import com.laptrinhjavaweb.enums.BuildingTypesEnum;
import com.laptrinhjavaweb.enums.DistrictsEnum;
import com.laptrinhjavaweb.model.BuildingDTO;
import com.laptrinhjavaweb.model.RentAreaDTO;
import com.laptrinhjavaweb.model.request.BuildingSearchRequest;
import com.laptrinhjavaweb.model.response.BuildingSearchResponse;
import com.laptrinhjavaweb.model.response.StaffResponseDTO;
import com.laptrinhjavaweb.repository.AssignmentBuildingRepository;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.RentAreaRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.repository.custom.BuildingRepositoryCustom;
import com.laptrinhjavaweb.repository.entity.AssignmentBuildingEntity;
import com.laptrinhjavaweb.repository.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.entity.RentAreaEntity;
import com.laptrinhjavaweb.repository.entity.UserEntity;
import com.laptrinhjavaweb.service.BuildingService;
import com.laptrinhjavaweb.utils.MapUtils;

@Service
public class BuildingServiceImpl implements BuildingService {

	
	@Autowired
	private BuildingRepository buildingRepository;	
	@Autowired
	private RentAreaRepository rentAreaRopository;
	@Autowired
	private RentAreaConverter rentAreaConverter;
	@Autowired
	private BuildingConverter buildingConverter;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AssignmentBuildingRepository assignmentBuildingRepository;
	@Autowired
	private AssignmentBuildingConverter assignmentBuildingConverter;

	@Override
	public List<BuildingSearchResponse> findBuilding(Map<String, String> requestParams, List<String> types) {
		List<BuildingSearchResponse> results = new ArrayList<>();
//		List<BuildingEntity> buildingEntities = buildingRepository.findBuilding(requestParams, types);
		BuildingSearchBuilder builderSearchBuilder = coverToBuildingSearchBuilder(requestParams, types);
		List<BuildingEntity> buildingEntities = buildingRepository.findBuilding(builderSearchBuilder);
		for (BuildingEntity item : buildingEntities) {
			BuildingSearchResponse buildingSearchResponse = buildingConverter.convertEntityToBuildingSearchResponse(item);
			results.add(buildingSearchResponse);
		}
		return results;
	}

	private BuildingSearchBuilder coverToBuildingSearchBuilder(Map<String, String> requestParams, List<String> types) {		
		BuildingSearchBuilder result = new BuildingSearchBuilder.Builder()
				.setName(MapUtils.getObject(requestParams, "name", String.class))
				.setDistrict(MapUtils.getObject(requestParams, "district", String.class))
				.setWard(MapUtils.getObject(requestParams, "ward", String.class))
				.setStreet(MapUtils.getObject(requestParams, "street", String.class))
				.setDirection(MapUtils.getObject(requestParams, "direction", String.class))
				.setLevel(MapUtils.getObject(requestParams, "level", String.class))
				.setFloorArea(MapUtils.getObject(requestParams, "floorarea", Integer.class))
				.setNumberOfBasement(MapUtils.getObject(requestParams, "numberofbasement", Integer.class))
				.setRentPriceFrom(MapUtils.getObject(requestParams, "rentpricefrom", Integer.class))
				.setRentPriceTo(MapUtils.getObject(requestParams, "rentpriceto", Integer.class))
				.setRentAreaFrom(MapUtils.getObject(requestParams, "rentareafrom", Integer.class))
				.setRentAreaTo(MapUtils.getObject(requestParams, "rentareato", Integer.class))
				.setStaffId(MapUtils.getObject(requestParams, "staffid", Long.class))
				.setBuildingTypes(types)
				.build();
		return result;
	}



	@Override
	public List<RentAreaDTO> findRentAreaByBuildingId(Long buildingId) {
		List<RentAreaDTO> results = new ArrayList<>();
		BuildingEntity buildingEntity = buildingRepository.findById(buildingId).get();
//		List<RentAreaEntity> rentAreaEntities = buildingEntity.getRentAreas(); 
		List<RentAreaEntity> rentAreaEntities = rentAreaRopository.findByBuildingId(buildingId);
		results = rentAreaEntities.stream().map(item -> rentAreaConverter.convertEntityToDto(item))
				.collect(Collectors.toList());
		return results;
	}

	@Override
	@Transactional
	public void save(BuildingDTO buildingDTO) {
		BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);
		List<RentAreaEntity> rentAreaEntities = buildingEntity.getRentAreas();
		if(buildingDTO.getId()!= null) {
			List<RentAreaEntity> rentAreaEntitiesOld = buildingRepository.findById(buildingDTO.getId()).get().getRentAreas();
			for (RentAreaEntity items: rentAreaEntitiesOld) {
				rentAreaRopository.deleteById(items.getId());
			}
		}
		BuildingEntity result =  buildingRepository.save(buildingEntity);
		for (RentAreaEntity item : rentAreaEntities) {		
				if(item.getValue() != null) {
					item.setBuilding(result);
					rentAreaRopository.save(item);
				}				
		}
		
	}
	
	@Override
	@Transactional
	public void delete(BuildingDTO buildingDTO) {		
		List<Long> buildingId= buildingDTO.getBuildingIds();
		for (Long item : buildingId) {
			BuildingEntity buildingEntity = buildingRepository.findById(item).get();
			List<RentAreaEntity> rentAreaEntities = buildingEntity.getRentAreas();
			for (RentAreaEntity items: rentAreaEntities) {
				rentAreaRopository.deleteById(items.getId());
			}	
			List<AssignmentBuildingEntity> assignmentBuildingEntities = buildingEntity.getAssignmentBuildings();
			for (AssignmentBuildingEntity items: assignmentBuildingEntities) {
				assignmentBuildingRepository.deleteById(items.getId());
			}
			buildingRepository.deleteById(item);				
		}
		
	}
	

	@Override
	public List<BuildingSearchResponse> findBuilding(BuildingSearchRequest request) {
		List<BuildingSearchResponse> results = new ArrayList<>();
		BuildingSearchBuilder builderSearchBuilder = buildingConverter.convertSearchRequestToSearchBuilder(request);
		List<BuildingEntity> buildingEntities = buildingRepository.findBuilding(builderSearchBuilder);
		for (BuildingEntity item : buildingEntities) {
			BuildingSearchResponse buildingSearchResponse = buildingConverter.convertEntityToBuildingSearchResponse(item);
			results.add(buildingSearchResponse);
		}
		return results;
	}

	@Override
	public Map<String, String> getDistricts() {
		Map<String, String> districts = new HashMap<>();
		for (DistrictsEnum item: DistrictsEnum.values()) {
			districts.put(item.toString(), item.getDistrictValue());
		}
		return districts;
	}

	@Override
	public Map<String, String> getBuildingTypes() {
		Map<String, String> buildingTypes = new HashMap<>();
		for (BuildingTypesEnum item: BuildingTypesEnum.values()) {
			buildingTypes.put(item.toString(), item.getBuildingTypeValue());
		}
		return buildingTypes;
	}

	@Override
	public List<BuildingDTO> findAll() {
		List<BuildingDTO> results = new ArrayList<>();
		List<BuildingEntity> buildingEntities = buildingRepository.findAll();
		for (BuildingEntity item : buildingEntities ) {
			BuildingDTO buildingDTO = buildingConverter.convertToDTO(item);
			results.add(buildingDTO);
		}
		return results;
	}

	@Override
	public List<StaffResponseDTO> findAssignmentBuildingByBuildingId(Long id) {
		BuildingEntity buildingEntity = buildingRepository.findById(id).get();
		List<StaffResponseDTO> results = new ArrayList<>();
		List<AssignmentBuildingEntity> assignmentBuildingEntities = buildingEntity.getAssignmentBuildings();
		List<UserEntity> userEntities = userRepository.findByStatusAndRolesCode(1, "staff");
		List<Long> buildingIds= new ArrayList<>();
		for (AssignmentBuildingEntity item : assignmentBuildingEntities) {
			results.add(assignmentBuildingConverter.convertEntityToStaffResponseDTO(item));
			buildingIds.add(item.getUser().getId());
			
		}
		for (UserEntity item : userEntities) {
			if(!buildingIds.contains(item.getId())) {
				StaffResponseDTO staffResponseDTO= new StaffResponseDTO();
				staffResponseDTO.setStaffId(item.getId());
				staffResponseDTO.setFullName(item.getFullName());
				staffResponseDTO.setChecked("");
				results.add(staffResponseDTO);
			}
		}
		return results;
	}

	@Override
	public BuildingDTO findBuildingById(Long id) {
		BuildingEntity buildingEntity = buildingRepository.findById(id).get();
		BuildingDTO buildingDTO = buildingConverter.convertToDTO(buildingEntity);
		return buildingDTO;
	}

	

}
