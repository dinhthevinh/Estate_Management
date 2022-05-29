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
	public List<BuildingEntity> findAll(Map<String, String> requestParams, List<String> types) {
		List<BuildingEntity> resutls = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		// Open a connection
		try {
			conn = ConnectionUtils.getConnection();
			stmt = conn.createStatement();
			StringBuilder sql = new StringBuilder();
			sql.append(joinTable(requestParams, types));
			sql.append(conditionQuery(requestParams));			
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
				if (conn != null) {
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
	
	public StringBuilder joinTable(Map<String, String> requestParams, List<String> types) {
		Long staffid = Long.parseLong( requestParams.get("staffid"));
		Integer rentareato = Integer.parseInt( requestParams.get("rentareato"));
		Integer rentareafrom = Integer.parseInt( requestParams.get("rentareafrom"));
		StringBuilder sql = new StringBuilder("select building.* from building");
		sql.append(" inner join  district on building.districtid = district.id");
		if (!StringUtils.isNullOrEmpty((String) requestParams.get("district"))) {
			sql.append(" and district.code = '" + (String) requestParams.get("district") + "'");
		}

		if ( !StringUtils.isNullOrEmpty(requestParams.get("staffid"))) {
			sql.append(
					" inner join assignmentbuilding on  building.id = assignmentbuilding.buildingid and assignmentbuilding.staffid = "
							+ requestParams.get("staffid"));
		}
		if (types != null) {
			sql.append(" inner join  buildingrenttype on building.id = buildingrenttype.buildingid \r\n"
					+ "inner join  renttype on buildingrenttype.renttypeid = renttype.id and (");
			for (int i = 0; i < types.size(); i++) {
				sql.append(String.join("'"+types.get(i)+"'" ," renttype.code = ", " or" ));
			}
			sql.append(" 1=2 )");
		}

		sql.append(" inner join  rentarea on building.id = rentarea.buildingid");
		if (!StringUtils.isNullOrEmpty(requestParams.get("rentareato"))) {
			sql.append(" and  rentarea.value <= " + requestParams.get("rentareato") + "");
		}

		if (!StringUtils.isNullOrEmpty(requestParams.get("rentareafrom"))) {
			sql.append(" and  rentarea.value >= " + requestParams.get("rentareafrom") + "");
		}
		sql.append(" where 1=1");
		return sql;
	}

	public StringBuilder conditionQuery(Map<String, String> requestParams) {
		StringBuilder sql = new StringBuilder();
		if (!StringUtils.isNullOrEmpty(requestParams.get("name"))) {
			sql.append(" and building.name like '%" + requestParams.get("name") + "%'");
		}

		if (!StringUtils.isNullOrEmpty(requestParams.get("floorarea")))  {
			sql.append(" and building.floorarea = " + requestParams.get("floorarea") + "");
		}

		if (!StringUtils.isNullOrEmpty(requestParams.get("ward"))) {
			sql.append(" and building.ward like '%" + requestParams.get("ward") + "%'");
		}
		if (!StringUtils.isNullOrEmpty(requestParams.get("street"))) {
			sql.append(" and building.street like '%" + requestParams.get("street") + "%'");
		}

		if (!StringUtils.isNullOrEmpty(requestParams.get("numberofbasement")) ) {
			sql.append(" and building.numberofbasement = " + requestParams.get("numberofbasement") + "");
		}
		if (!StringUtils.isNullOrEmpty(requestParams.get("direction"))) {
			sql.append(" and building.direction like '%" + requestParams.get("direction") + "%'");
		}
		if (!StringUtils.isNullOrEmpty(requestParams.get("level"))) {
			sql.append(" and building.level like '%" + requestParams.get("level") + "%'");
		}

		if (!StringUtils.isNullOrEmpty(requestParams.get("rentpricefrom"))) {
			sql.append(" and building.rentprice >= " + requestParams.get("rentpricefrom") + "");
		}

		if (!StringUtils.isNullOrEmpty(requestParams.get("rentpriceto"))) {
			sql.append(" and building.rentprice <= " + requestParams.get("rentpriceto") + "");
		}

		if (!StringUtils.isNullOrEmpty(requestParams.get("managername"))) {
			sql.append(" and building.managername like '%" + requestParams.get("managername") + "%'");
		}
		if (!StringUtils.isNullOrEmpty(requestParams.get("managerphone"))) {
			sql.append(" and building.managerphone like '%" + requestParams.get("managerphone") + "%'");
		}
		sql.append(" group by building.id");
		return sql;
	}

}
