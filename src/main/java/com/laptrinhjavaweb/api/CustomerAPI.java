package com.laptrinhjavaweb.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.model.CustomerDTO;
import com.laptrinhjavaweb.model.request.CustomerAssignmentRequest;
import com.laptrinhjavaweb.model.response.ResponseDTO;
import com.laptrinhjavaweb.service.AssignmentCustomerService;
import com.laptrinhjavaweb.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerAPI {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private AssignmentCustomerService assignmentCustomerService;

	@GetMapping("/{customerid}/staffs")
	public ResponseDTO loadStaff(@PathVariable("customerid") Long id) {
		ResponseDTO resutl = new ResponseDTO();
		resutl.setMessage("success");
		resutl.setData(customerService.findAssignmentCustomerByCustomerId(id));
		return resutl;
	}

	@PostMapping("/assignment")
	public void assignCustomer(@RequestBody CustomerAssignmentRequest assignmentRequest) {
		assignmentCustomerService.updateAssignmentCustomer(assignmentRequest);
	}

	@PostMapping
	public CustomerDTO insertBuilding(@RequestBody CustomerDTO customerDTO) {
		customerService.save(customerDTO);
		return customerDTO;
	}

	@DeleteMapping
	public CustomerDTO deleteBuilding(@RequestBody CustomerDTO customerDTO) {
		customerService.delete(customerDTO);
		return customerDTO;
	}

}
