package com.laptrinhjavaweb.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.model.RentAreaDTO;
import com.laptrinhjavaweb.service.BuildingService;

@RestController
public class RentAreaAPI {
	
	@Autowired 
	private BuildingService buildingService;
	
//	@GetMapping("/api/rentareas")
//	public List<RentAreaDTO> getRentArea(@RequestParam(value = "buildingid", required = false) Long buildingId) {
//		return buildingService.findRentAreaByBuildingId(buildingId);
//
//	}
}
