package com.laptrinhjavaweb.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.entity.BuildingEntity;
import com.laptrinhjavaweb.utils.ConnectionUtils;
import com.laptrinhjavaweb.utils.StringUtils;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {

	@Override
	public List<BuildingEntity> findAll(Map<String, Object> requestParams, List<String> types) {
		List<BuildingEntity> resutls = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		// Open a connection
		
//		, types, )
//		,(String)requestParams.get("name") ,Integer.parseInt((String) requestParams.get("floorarea")), (String)requestParams.get("ward")
//		,(String) requestParams.get("street"),Integer.parseInt((String) requestParams.get("numberofbasement")), (String)requestParams.get("direction"),
//		(String)requestParams.get("level"),Integer.parseInt((String)requestParams.get("rentareafrom")) ,Integer.parseInt((String)requestParams.get("rentareato")) ,Integer.parseInt((String)requestParams.get("rentpricefrom")),
//		Integer.parseInt((String)requestParams.get("rentpriceto")),(String)requestParams.get("managername") ,(String)requestParams.get("managerphone") 
		try {
			conn = ConnectionUtils.getConnection();
			stmt = conn.createStatement();		
			StringBuilder sql = new StringBuilder(
					"select building.* from building");
			sql.append(" inner join  district on building.districtid = district.id");
			Long staffid = Long.parseLong((String) requestParams.get("staffid"));
			Integer rentareato = Integer.parseInt((String)requestParams.get("rentareato"));
			Integer rentareafrom = Integer.parseInt((String)requestParams.get("rentareafrom"));
			Integer floorarea = Integer.parseInt((String)requestParams.get("floorarea"));
			Integer numberofbasement = Integer.parseInt((String)requestParams.get("numberofbasement"));
			Integer rentpricefrom = Integer.parseInt((String)requestParams.get("rentpricefrom"));
			Integer rentpriceto = Integer.parseInt((String)requestParams.get("rentpriceto"));
			if (!StringUtils.isNullOrEmpty((String)requestParams.get("district"))) {
				sql.append(" and district.code = '" + (String)requestParams.get("district") + "'");
			}
			
			if (staffid != null) {
				sql.append(
						" inner join assignmentbuilding on  building.id = assignmentbuilding.buildingid and assignmentbuilding.staffid = "
								+ staffid);
			}
			if (types != null) {
				sql.append(" inner join  buildingrenttype on building.id = buildingrenttype.buildingid \r\n"
						+ "inner join  renttype on buildingrenttype.renttypeid = renttype.id and (");
				for (int i = 0; i < types.size(); i++) {
					if (types.get(i).equals("tang-tret")) {
						sql.append(" renttype.code = 'tang-tret' or");
					} else if (types.get(i).equals("nguyen-can")) {
						sql.append(" renttype.code = 'nguyen-can' or");
					} else if (types.get(i).equals("noi-that")) {
						sql.append(" renttype.code = 'noi-that' or");
					}
				}
				sql.append(" 1=2 )");
			}
			
			sql.append(" inner join  rentarea on building.id = rentarea.buildingid");
			if (rentareato != null) {
				sql.append(" and  rentarea.value <= " + rentareato + "");
			}
			
			if (rentareafrom != null) {
				sql.append(" and  rentarea.value >= " + rentareafrom + "");
			}
			sql.append(" where 1=1");
			if (!StringUtils.isNullOrEmpty((String)requestParams.get("name"))) {
				sql.append(" and building.name like '%" + (String)requestParams.get("name") + "%'");
			}
			
			if (floorarea != null) {
				sql.append(" and building.floorarea = " + floorarea + "");
			}

			if (!StringUtils.isNullOrEmpty((String)requestParams.get("ward"))) {
				sql.append(" and building.ward like '%" + (String)requestParams.get("ward") + "%'");
			}
			if (!StringUtils.isNullOrEmpty((String)requestParams.get("street"))) {
				sql.append(" and building.street like '%" + (String)requestParams.get("street") + "%'");
			}
			
			if (numberofbasement != null) {
				sql.append(" and building.numberofbasement = " + numberofbasement + "");
			}
			if (!StringUtils.isNullOrEmpty((String)requestParams.get("direction"))) {
				sql.append(" and building.direction like '%" + (String)requestParams.get("direction") + "%'");
			}
			if (!StringUtils.isNullOrEmpty((String)requestParams.get("level"))) {
				sql.append(" and building.level like '%" + (String)requestParams.get("level") + "%'");
			}
			
			if (rentpricefrom != null) {
				sql.append(" and building.rentprice >= " + rentpricefrom + "");
			}
			
			if (rentpriceto != null) {
				sql.append(" and building.rentprice <= " + rentpriceto + "");
			}

			if (!StringUtils.isNullOrEmpty((String)requestParams.get("managername"))) {
				sql.append(" and building.managername like '%" + (String)requestParams.get("managername") + "%'");
			}
			if (!StringUtils.isNullOrEmpty((String)requestParams.get("managerphone"))) {
				sql.append(" and building.managerphone like '%" + (String)requestParams.get("managerphone") + "%'");
			}
			sql.append(" group by building.id");		
			rs = stmt.executeQuery(sql.toString());
			// Extract data from result set
			while (rs.next()) {		
				BuildingEntity buildingEntity = new BuildingEntity();
				buildingEntity.setId(rs.getLong("id"));
				buildingEntity.setCreatedDate(rs.getString("createddate"));
				buildingEntity.setName(rs.getString("name"));
				buildingEntity.setDistrictId(rs.getLong("districtid"));
				buildingEntity.setWard(rs.getString("ward"));
				buildingEntity.setStreet(rs.getString("street"));
				buildingEntity.setManagerName(rs.getString("managername"));
				buildingEntity.setManagerPhone(rs.getString("managerphone"));
				buildingEntity.setFloorArea(rs.getInt("floorarea"));
				buildingEntity.setRentPrice(rs.getInt("rentprice"));
				buildingEntity.setServiceFee(rs.getInt("servicefee"));
				buildingEntity.setBorkerageFee(rs.getInt("brokeragefee"));
				resutls.add(buildingEntity);	
			}
			return resutls;
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
		return null;
	}
	
}

	


	