package com.laptrinhjavaweb.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.model.BuildingModel;
import com.laptrinhjavaweb.model.response.BuildingSearchResponse;
import com.laptrinhjavaweb.service.BuildingService;

//@Controller
@RestController
@RequestMapping("/api/building")
public class BuildingAPI {
	
	@Autowired
	private BuildingService buildingService;

//	@RequestMapping(method = RequestMethod.GET)
	@GetMapping
	public List<BuildingSearchResponse> findAll(@RequestParam(value = "district", required = false) String district,
			@RequestParam(value = "types", required = false) List<String> types,
			@RequestParam(value = "staffid", required = false) Long staffid,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "floorarea", required = false) Integer floorarea,
			@RequestParam(value = "ward", required = false) String ward,
			@RequestParam(value = "street", required = false) String street,
			@RequestParam(value = "numberofbasement", required = false) Integer numberofbasement,
			@RequestParam(value = "direction", required = false) String direction,
			@RequestParam(value = "level", required = false) String level,
			@RequestParam(value = "rentareafrom", required = false) Integer rentareafrom,
			@RequestParam(value = "rentareato", required = false) Integer rentareato,
			@RequestParam(value = "rentpricefrom", required = false) Integer rentpricefrom,
			@RequestParam(value = "rentpriceto", required = false) Integer rentpriceto,
			@RequestParam(value = "managername", required = false) String managername,
			@RequestParam(value = "managerphone", required = false) String managerphone) {
		List<BuildingSearchResponse> resutls = buildingService.findAll(district, types, staffid, name, floorarea, ward, street, numberofbasement, direction, level, rentareafrom, rentareato, rentpricefrom, rentpriceto, managername, managerphone);
		return resutls;
	}

//	@RequestMapping(method = RequestMethod.POST)
	@PostMapping
	public BuildingModel insertBuilding(@RequestBody BuildingModel newBuilding) {
		BuildingModel resutl = new BuildingModel();
		return resutl;
	}

//	@RequestMapping( method = RequestMethod.PUT)
	@PutMapping
	public BuildingModel updateBuilding(@RequestBody BuildingModel updateBuilding) {
		BuildingModel resutl = new BuildingModel();
		return resutl;
	}

//	@RequestMapping(value ="/{buildingid}", method = RequestMethod.GET)
	@GetMapping("/{buildingid}")
	public BuildingModel getBuildingDetail(@PathVariable("buildingid") Long id) {
		BuildingModel resutl = new BuildingModel();
		return resutl;
	}
}
