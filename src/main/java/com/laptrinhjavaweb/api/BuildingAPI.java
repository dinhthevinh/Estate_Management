package com.laptrinhjavaweb.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.emyeuanh.BuildingEmyeuanh;
import com.laptrinhjavaweb.utils.StringUtils;

//@Controller
@RestController
@RequestMapping("/api/building")
public class BuildingAPI {
	static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	static final String USER = "root";
	static final String PASS = "123456789";

//	@RequestMapping(method = RequestMethod.GET)
	@GetMapping
	public List<BuildingEmyeuanh> findAll(@RequestParam(value = "district", required = false) String district,
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
		List<BuildingEmyeuanh> resutls = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		// Open a connection
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			StringBuilder sql = new StringBuilder("select building.id, building.name, building.createddate, district.name as district, rentarea.value as areaempty, building.street, building.ward, \r\n" + 
					"building.managername, building.managerphone, building.floorarea, building.rentprice, building.servicefee, building.brokeragefee  from building");  
			sql.append(" inner join  district on building.districtid = district.id");
			if(!StringUtils.isNullOrEmpty(district)) {
				sql.append(" and district.code = '"+district+"'");
			}
			if (staffid != null) {
				sql.append(" inner join assignmentbuilding on  building.id = assignmentbuilding.buildingid and assignmentbuilding.staffid = " +staffid);
			}
			if (types != null) {
				sql.append(" inner join  buildingrenttype on building.id = buildingrenttype.buildingid \r\n" + 
						"inner join  renttype on buildingrenttype.renttypeid = renttype.id and (");
				for (int i = 0; i < types.size(); i++) {
					if (types.get(i).equals("tang-tret")) {
						sql.append(" renttype.code = 'tang-tret' or");
					}else if (types.get(i).equals("nguyen-can")) {
						sql.append(" renttype.code = 'nguyen-can' or");
					}else if (types.get(i).equals("noi-that")) {
						sql.append(" renttype.code = 'noi-that' or");
					}						
				}
				sql.append(" 1=2 )");
			}
			 sql.append(" inner join  rentarea on building.id = rentarea.buildingid");
				if(rentareato != null ) {
					sql.append(" and  rentarea.value <= "+rentareato+"");
				}
				if(rentareafrom != null) {
					sql.append(" and  rentarea.value >= "+rentareafrom+"");				
			}			
			 sql.append(" where 1=1");
			if (!StringUtils.isNullOrEmpty(name)) {
				sql.append(" and building.name like '%" + name + "%'");
			}
			if (floorarea != null) {
				sql.append(" and building.floorarea = " + floorarea + "");
			}

			if (!StringUtils.isNullOrEmpty(ward)) {
				sql.append(" and building.ward like '%" + ward + "%'");
			}
			if (!StringUtils.isNullOrEmpty(street)) {
				sql.append(" and building.street like '%" + street + "%'");
			}
			if (numberofbasement != null) {
				sql.append(" and building.numberofbasement = " + numberofbasement + "");
			}
			if (!StringUtils.isNullOrEmpty(direction)) {
				sql.append(" and building.direction like '%" + direction + "%'");
			}
			if (!StringUtils.isNullOrEmpty(level)) {
				sql.append(" and building.level like '%" + level + "%'");
			}
			if (rentpricefrom != null) {
				sql.append(" and building.rentprice >= " + rentpricefrom + "");
			}
			if (rentpriceto != null) {
				sql.append(" and building.rentprice <= " + rentpriceto + "");
			}
			
			if (!StringUtils.isNullOrEmpty(managername)) {
				sql.append(" and building.managername like '%" + managername + "%'");
			}
			if (!StringUtils.isNullOrEmpty(managerphone)) {
				sql.append(" and building.managerphone like '%" + managerphone + "%'");
			}
			sql.append(" group by rentarea.id");
			
			rs = stmt.executeQuery(sql.toString());
			// Extract data from result set
			int idFirst = -1;
			BuildingEmyeuanh buildingEmyeuanh = new BuildingEmyeuanh();
			while (rs.next()) {
				if(idFirst != rs.getInt("id")) {
				buildingEmyeuanh = new BuildingEmyeuanh(); //9d 8f
				idFirst = rs.getInt("id"); 
				buildingEmyeuanh.setName(rs.getString("name"));	
				buildingEmyeuanh.setAddress(rs.getString("street")+", "+ rs.getString("ward")+", "+rs.getString("district"));
				buildingEmyeuanh.setManagername(rs.getString("managername"));
				buildingEmyeuanh.setManagerphone(rs.getString("managerphone"));
				buildingEmyeuanh.setFloorarea(rs.getInt("floorarea"));
				buildingEmyeuanh.setRentprice(rs.getInt("rentprice"));
				buildingEmyeuanh.setAreaempty(rs.getString("areaempty"));	
				resutls.add(buildingEmyeuanh);
				} else {
					buildingEmyeuanh.setAreaempty(buildingEmyeuanh.getAreaempty() +", "+ rs.getString("areaempty"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn!= null) {
					conn.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resutls;
	}

//	@RequestMapping(method = RequestMethod.POST)
	@PostMapping
	public BuildingEmyeuanh insertBuilding(@RequestBody BuildingEmyeuanh newBuilding) {
		BuildingEmyeuanh resutl = new BuildingEmyeuanh();
		return resutl;
	}

//	@RequestMapping( method = RequestMethod.PUT)
	@PutMapping
	public BuildingEmyeuanh updateBuilding(@RequestBody BuildingEmyeuanh updateBuilding) {
		BuildingEmyeuanh resutl = new BuildingEmyeuanh();
		return resutl;
	}

//	@RequestMapping(value ="/{buildingid}", method = RequestMethod.GET)
	@GetMapping("/{buildingid}")
	public BuildingEmyeuanh getBuildingDetail(@PathVariable("buildingid") Long id) {
		BuildingEmyeuanh resutl = new BuildingEmyeuanh();
		return resutl;
	}
}
