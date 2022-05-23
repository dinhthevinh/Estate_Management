package com.laptrinhjavaweb.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.model.UserModel;

@RestController
public class UserAPI {
	@GetMapping("/api/staffs")
	public List<UserModel> getBuildingDetail(@RequestParam(value = "buildingid", required = false) Long buildingid) {
		List<UserModel> resutls = new ArrayList<>();
		return resutls;
	}
}
