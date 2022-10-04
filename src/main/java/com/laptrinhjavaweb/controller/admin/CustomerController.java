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
import com.laptrinhjavaweb.model.request.CustomerSearchRequest;
import com.laptrinhjavaweb.service.BuildingService;
import com.laptrinhjavaweb.service.CustomerService;
import com.laptrinhjavaweb.service.UserService;


@Controller(value = "customerControllerOfAdmin")
public class CustomerController {
	
	@Autowired
	private BuildingService buildingService;
	
	@Autowired
	private CustomerService customerService;


	
	@RequestMapping(value = "/admin/customer-list", method = RequestMethod.GET)
	public ModelAndView buildingList(@ModelAttribute("modelSearch")  CustomerSearchRequest request) {
		ModelAndView mav = new ModelAndView("admin/customer/customer-list");
		mav.addObject("modelSearch", request);
		mav.addObject("customers", customerService.findCustomer(request));
		return mav;
	}
	
	@RequestMapping(value = "/admin/customer-edit", method = RequestMethod.GET)
	public ModelAndView buildingEdit(@RequestParam(value = "buildingid", required = false) Long buildingid, @ModelAttribute("modelSearch")  BuildingSearchRequest request) {
		ModelAndView mav = new ModelAndView("admin/customer/customer-edit");
		return mav;
	}
}
