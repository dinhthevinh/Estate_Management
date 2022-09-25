package com.laptrinhjavaweb.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.model.BuildingDTO;
import com.laptrinhjavaweb.model.request.BuildingAssignmentRequest;
import com.laptrinhjavaweb.model.response.BuildingSearchResponse;
import com.laptrinhjavaweb.model.response.ResponseDTO;
import com.laptrinhjavaweb.model.response.StaffResponseDTO;
import com.laptrinhjavaweb.service.AssignmentBuildingService;
import com.laptrinhjavaweb.service.BuildingService;

//@Controller
@RestController
@RequestMapping("/api/building")
public class BuildingAPI {
	
	@Autowired
	private BuildingService buildingService;
	@Autowired
	private AssignmentBuildingService assignmentBuildingService;
	
	
//	@RequestMapping(method = RequestMethod.GET)
	
	@GetMapping
	public List<BuildingSearchResponse> findBuilding(@RequestParam Map<String, String> requestParams,@RequestParam(value = "types", required = false) List<String> types  ) {	
		List<BuildingSearchResponse> resutls = buildingService.findBuilding(requestParams, types);	
		return resutls;	
		
	}

//	@RequestMapping(method = RequestMethod.POST)
	@PostMapping
	public BuildingDTO insertBuilding(@RequestBody BuildingDTO newBuilding) {
		buildingService.save(newBuilding);
		return newBuilding;
	}

//	@RequestMapping( method = RequestMethod.PUT)
	@PutMapping
	public BuildingDTO updateBuilding(@RequestBody BuildingDTO updateBuilding) {
		buildingService.save(updateBuilding);
		return updateBuilding;
	}
	@DeleteMapping
	public BuildingDTO deleteBuilding(@RequestBody BuildingDTO buildingDTO) {
		buildingService.delete(buildingDTO);
		return buildingDTO;
	}

//	@RequestMapping(value ="/{buildingid}", method = RequestMethod.GET)
	@GetMapping("/{buildingid}")
	public BuildingDTO getBuildingDetail(@PathVariable("buildingid") Long id) {
		BuildingDTO resutl = new BuildingDTO();
		return resutl;
	}
	
	@PostMapping("/assignment")
	public void assignBuilding(@RequestBody BuildingAssignmentRequest assignmentRequest) {
		assignmentBuildingService.updateAssignmentBuilding(assignmentRequest);
	}
	
	@GetMapping("/{buildingid}/staffs")
	public ResponseDTO loadStaff(@PathVariable("buildingid") Long id) {
		ResponseDTO resutl = new ResponseDTO();
		resutl.setMessage("success");
		resutl.setData(buildingService.findAssignmentBuildingByBuildingId(id));
		return resutl;
	}
	
}
