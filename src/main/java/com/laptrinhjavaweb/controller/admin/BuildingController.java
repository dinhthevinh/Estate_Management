package com.laptrinhjavaweb.controller.admin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.model.BuildingDTO;
import com.laptrinhjavaweb.model.request.BuildingSearchRequest;
import com.laptrinhjavaweb.service.BuildingService;
import com.laptrinhjavaweb.service.UserService;


@Controller(value = "buildingControllerOfAdmin")
public class BuildingController {
	
	@Autowired
	private BuildingService buildingService;
	
	@Autowired
	private UserService userService;


	
	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	public ModelAndView buildingList(@ModelAttribute("modelSearch")  BuildingSearchRequest request) {
		ModelAndView mav = new ModelAndView("admin/building/list");
		mav.addObject("modelSearch", request);
		mav.addObject("staffmaps", userService.getStaffMap());
		mav.addObject("districts", buildingService.getDistricts());
		mav.addObject("buildingTypes", buildingService.getBuildingTypes());
		mav.addObject("buildings", buildingService.findBuilding(request));
		return mav;
	}
	
	@RequestMapping(value = "/admin/edit", method = RequestMethod.GET)
	public ModelAndView buildingEdit(@RequestParam(value = "buildingid", required = false) Long buildingid, @ModelAttribute("modelSearch")  BuildingSearchRequest request) {
		ModelAndView mav = new ModelAndView("admin/building/edit");
		mav.addObject("buildingTypes", buildingService.getBuildingTypes());
		mav.addObject("districts", buildingService.getDistricts());
		if(!(buildingid == null)) {
			mav.addObject("buildingModel", buildingService.findBuildingById(buildingid));
		}
		return mav;
	}
}