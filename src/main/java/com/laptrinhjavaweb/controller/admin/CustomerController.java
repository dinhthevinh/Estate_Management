package com.laptrinhjavaweb.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhjavaweb.model.request.CustomerSearchRequest;
import com.laptrinhjavaweb.service.BuildingService;
import com.laptrinhjavaweb.service.CustomerService;
import com.laptrinhjavaweb.service.TransactionService;
import com.laptrinhjavaweb.service.UserService;

@Controller(value = "customerControllerOfAdmin")
public class CustomerController {

	@Autowired
	private BuildingService buildingService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private UserService userService;
	@Autowired
	private TransactionService transactionService;

	@RequestMapping(value = "/admin/customer-list", method = RequestMethod.GET)
	public ModelAndView customerList(@ModelAttribute("modelSearch") CustomerSearchRequest request) {
		ModelAndView mav = new ModelAndView("admin/customer/customer-list");
		mav.addObject("modelSearch", request);
		mav.addObject("staffmaps", userService.getStaffMap());
		mav.addObject("customers", customerService.findCustomer(request));
		return mav;
	}

	@RequestMapping(value = "/admin/customer-edit", method = RequestMethod.GET)
	public ModelAndView customerEdit() {
		ModelAndView mav = new ModelAndView("admin/customer/customer-edit");
		return mav;
	}

	@RequestMapping(value = "/admin/customer-edit-{customerid}", method = RequestMethod.GET)
	public ModelAndView customerEdit(@PathVariable("customerid") Long customerid,
			@ModelAttribute("modelSearch") CustomerSearchRequest request) {
		ModelAndView mav = new ModelAndView("admin/customer/customer-edit");
		mav.addObject("customerModel", customerService.findCustomerById(customerid));
		mav.addObject("transactions", transactionService.findTransactionByCustomerId(customerid));
		mav.addObject("transactionEnums", customerService.getTransactionTypes());
		return mav;
	}
}
