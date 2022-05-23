package com.laptrinhjavaweb.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.entity.BuildingEntity;
import com.laptrinhjavaweb.utils.ConnectionUtils;
import com.laptrinhjavaweb.utils.StringUtils;


@Repository
public class BuildingRepositoryImpl implements BuildingRepository{

	@Override
	public List<BuildingEntity> findAll(String district, List<String> types, Long staffid, String name,
			Integer floorarea, String ward, String street, Integer numberofbasement, String direction, String level,
			Integer rentareafrom, Integer rentareato, Integer rentpricefrom, Integer rentpriceto, String managername,
			String managerphone) {
		List<BuildingEntity> resutls = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		// Open a connection
		try {
			conn = ConnectionUtils.getConnection();
			stmt = conn.createStatement();
			StringBuilder sql = new StringBuilder(
					"select building.* from building");
			sql.append(" inner join  district on building.districtid = district.id");
			if (!StringUtils.isNullOrEmpty(district)) {
				sql.append(" and district.code = '" + district + "'");
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
