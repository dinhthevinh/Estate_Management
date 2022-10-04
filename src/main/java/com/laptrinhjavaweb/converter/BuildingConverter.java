package com.laptrinhjavaweb.converter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.enums.DistrictsEnum;
import com.laptrinhjavaweb.model.BuildingDTO;
import com.laptrinhjavaweb.model.request.BuildingSearchRequest;
import com.laptrinhjavaweb.model.response.BuildingSearchResponse;
import com.laptrinhjavaweb.repository.RentAreaRepository;
import com.laptrinhjavaweb.repository.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.entity.RentAreaEntity;
import com.laptrinhjavaweb.utils.MapUtils;

@Component
public class BuildingConverter {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private RentAreaRepository rentAreaRopository;
	
	public BuildingSearchResponse convertEntityToBuildingSearchResponse (BuildingEntity buildingEntity) {
		BuildingSearchResponse result = modelMapper.map(buildingEntity, BuildingSearchResponse.class);
		StringBuilder areaEmpty = new StringBuilder();
		List<RentAreaEntity> rentAreaEntities = rentAreaRopository.findByBuildingId(buildingEntity.getId());
		for (RentAreaEntity item :rentAreaEntities) {
			if (areaEmpty.length()>1) {
				areaEmpty.append(", ");
			}
			areaEmpty.append(item.getValue());			
		}
		result.setAreaEmpty(areaEmpty.toString());
		String districtName= "";
		List<String> address= new ArrayList<>();
		address.add(buildingEntity.getStreet());
		address.add(buildingEntity.getWard());
		if(buildingEntity.getDistrict() != null) {
			districtName=DistrictsEnum.valueOf(buildingEntity.getDistrict()).getDistrictValue(); 
			address.add(districtName);
		}
		String addressString = String.join(",", address);
		result.setAddress(addressString);
		return result;
		
	}
	
	public BuildingEntity convertToEntity (BuildingDTO buildingDTO) {
		BuildingEntity result = modelMapper.map(buildingDTO, BuildingEntity.class);
		if(!buildingDTO.getRentArea().isEmpty()) {
			List<String> rentAreaStr= Arrays.asList(buildingDTO.getRentArea().split(","));
			List<RentAreaEntity> areaEntities = new ArrayList<>();
			for (String item : rentAreaStr) {
				RentAreaEntity areaEntity= new RentAreaEntity();
				areaEntity.setValue(Integer.parseInt(item.trim()));
				areaEntities.add(areaEntity);
			}
			result.setRentAreas(areaEntities);
		}
		List<String> buildingTypes= buildingDTO.getTypes();
			String type = String.join(",", buildingTypes);	
			result.setType(type);	
		return result;
	}
	
	public BuildingDTO convertToDTO (BuildingEntity buildingEntity) {
		BuildingDTO result = modelMapper.map(buildingEntity, BuildingDTO.class);
		StringBuilder areaEmpty = new StringBuilder();
		List<RentAreaEntity> rentAreaEntities = rentAreaRopository.findByBuildingId(buildingEntity.getId());
		for (RentAreaEntity item :rentAreaEntities) {
			if (areaEmpty.length()>1) {
				areaEmpty.append(", ");
			}
			areaEmpty.append(item.getValue());			
		}
		result.setRentArea(areaEmpty.toString());
		List<String> buildingTypes = new ArrayList<String>();
		String str= buildingEntity.getType();
		if(str != null) {

			buildingTypes = Arrays.asList(str.split(","));
			result.setTypes(buildingTypes);
		}
		return result;
	}
	
	public BuildingSearchBuilder convertSearchRequestToSearchBuilder (BuildingSearchRequest request) {
		BuildingSearchBuilder result = new BuildingSearchBuilder.Builder()
				.setBuildingTypes(request.getBuildingTypes())
				.setDirection(request.getDirection())
				.setName(request.getName()).setDistrict(request.getDistrict()).setFloorArea(request.getFloorArea()).setStreet(request.getStreet())
				.setWard(request.getWard()).setNumberOfBasement(request.getNumberOfBasement()).setRentAreaFrom(request.getRentAreaFrom())
				.setRentAreaTo(request.getRentAreaTo()).setRentPriceFrom(request.getRentPriceFrom()).setRentPriceTo(request.getRentPriceTo())
				.setStaffId(request.getStaffId()).setLevel(request.getLevel()).setManagerName(request.getManagerName()).setManagerPhone(request.getManagerPhone())
				.setBuildingTypes(request.getBuildingTypes()).build();			
		return result;
	}
}
