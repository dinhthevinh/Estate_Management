package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.converter.RentAreaConverter;
import com.laptrinhjavaweb.converter.StaffResponseConverter;
import com.laptrinhjavaweb.enums.BuildingTypesEnum;
import com.laptrinhjavaweb.enums.DistrictsEnum;
import com.laptrinhjavaweb.model.BuildingDTO;
import com.laptrinhjavaweb.model.MyUserDetail;
import com.laptrinhjavaweb.model.RentAreaDTO;
import com.laptrinhjavaweb.model.request.BuildingSearchRequest;
import com.laptrinhjavaweb.model.response.BuildingSearchResponse;
import com.laptrinhjavaweb.model.response.StaffResponseDTO;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.RentAreaRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.repository.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.entity.RentAreaEntity;
import com.laptrinhjavaweb.repository.entity.UserEntity;
import com.laptrinhjavaweb.security.utils.SecurityUtils;
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
	private StaffResponseConverter staffResponseConverter;

	@Override
	public List<BuildingSearchResponse> findBuilding(Map<String, String> requestParams, List<String> types) {
		List<BuildingSearchResponse> results = new ArrayList<>();
		BuildingSearchBuilder builderSearchBuilder = coverToBuildingSearchBuilder(requestParams, types);
		List<BuildingEntity> buildingEntities = buildingRepository.findBuilding(builderSearchBuilder);
		for (BuildingEntity item : buildingEntities) {
			BuildingSearchResponse buildingSearchResponse = buildingConverter
					.convertEntityToBuildingSearchResponse(item);
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
				.setStaffId(MapUtils.getObject(requestParams, "staffid", Long.class)).setBuildingTypes(types).build();
		return result;
	}

	@Override
	public List<RentAreaDTO> findRentAreaByBuildingId(Long buildingId) {
		List<RentAreaDTO> results = new ArrayList<>();
		BuildingEntity buildingEntity = buildingRepository.findById(buildingId).get();
		List<RentAreaEntity> rentAreaEntities = buildingEntity.getRentAreas();
		results = rentAreaEntities.stream().map(item -> rentAreaConverter.convertEntityToDto(item))
				.collect(Collectors.toList());
		return results;
	}

	@Override
	@Transactional
	public void save(BuildingDTO buildingDTO) {
		BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);
		List<RentAreaEntity> rentAreaEntities = new ArrayList<>();
		if (buildingDTO.getId() != null) {
			rentAreaEntities = buildingRepository.findById(buildingDTO.getId()).get().getRentAreas();
			buildingEntity.getRentAreas().removeAll(rentAreaEntities);
			List<UserEntity> userEntities = buildingRepository.findById(buildingDTO.getId()).get().getUsers();
			buildingEntity.setUsers(userEntities);
		}
		for (RentAreaEntity item : buildingEntity.getRentAreas()) {
			item.setBuilding(buildingEntity);
		}
		buildingRepository.save(buildingEntity);

	}

	@Override
	@Transactional
	public void delete(BuildingDTO buildingDTO) {
		List<String> roles = SecurityUtils.getAuthorities();
		if (roles.contains("ROLE_manager")) {
			List<Long> buildingId = buildingDTO.getBuildingIds();
			buildingRepository.deleteByIdIn(buildingId);
		}

	}

	@Override
	public List<BuildingSearchResponse> findBuilding(BuildingSearchRequest request) {
		List<String> roles = SecurityUtils.getAuthorities();
		List<BuildingSearchResponse> results = new ArrayList<>();
		if (roles.contains("ROLE_staff")) {
			MyUserDetail myUserDetail = MyUserDetail.class
					.cast(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//			BuildingSearchBuilder builderSearchBuilder = buildingConverter.convertSearchRequestToSearchBuilder(request);
			UserEntity userEntity = userRepository.findById(myUserDetail.getId()).get();
			List<BuildingEntity> buildingEntities = userEntity.getBuildings();
			for (BuildingEntity item : buildingEntities) {
				BuildingSearchResponse buildingSearchResponse = buildingConverter
						.convertEntityToBuildingSearchResponse(item);
				results.add(buildingSearchResponse);
			}
		} else {
			BuildingSearchBuilder builderSearchBuilder = buildingConverter.convertSearchRequestToSearchBuilder(request);
			List<BuildingEntity> buildingEntities = buildingRepository.findBuilding(builderSearchBuilder);
			for (BuildingEntity item : buildingEntities) {
				BuildingSearchResponse buildingSearchResponse = buildingConverter
						.convertEntityToBuildingSearchResponse(item);
				results.add(buildingSearchResponse);
			}
		}
		return results;

	}

	@Override
	public Map<String, String> getDistricts() {
		Map<String, String> districts = new HashMap<>();
		for (DistrictsEnum item : DistrictsEnum.values()) {
			districts.put(item.toString(), item.getDistrictValue());
		}
		return districts;
	}

	@Override
	public Map<String, String> getBuildingTypes() {
		Map<String, String> buildingTypes = new HashMap<>();
		for (BuildingTypesEnum item : BuildingTypesEnum.values()) {
			buildingTypes.put(item.toString(), item.getBuildingTypeValue());
		}
		return buildingTypes;
	}

	@Override
	public List<BuildingDTO> findAll() {
		List<BuildingDTO> results = new ArrayList<>();
		List<BuildingEntity> buildingEntities = buildingRepository.findAll();
		for (BuildingEntity item : buildingEntities) {
			BuildingDTO buildingDTO = buildingConverter.convertToDTO(item);
			results.add(buildingDTO);
		}
		return results;
	}

	@Override
	public List<StaffResponseDTO> findAssignmentBuildingByBuildingId(Long id) {
		BuildingEntity buildingEntity = buildingRepository.findById(id).get();
		List<StaffResponseDTO> results = new ArrayList<>();
		List<UserEntity> userEntities = buildingEntity.getUsers();
		List<UserEntity> staffs = userRepository.findByStatusAndRolesCode(1, "staff");
		List<Long> buildingIds = new ArrayList<>();
		for (UserEntity item : userEntities) {
			results.add(staffResponseConverter.convertEntityToStaffResponseDTO(item));
			buildingIds.add(item.getId());
		}
		for (UserEntity item : staffs) {
			if (!buildingIds.contains(item.getId())) {
				StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
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
