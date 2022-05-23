package com.laptrinhjavaweb.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.laptrinhjavaweb.repository.RentAreaRepository;
import com.laptrinhjavaweb.repository.entity.RentAreaEntity;
import com.laptrinhjavaweb.utils.ConnectionUtils;

@Repository
public class RentAreaRepositoryImpl implements RentAreaRepository{

	@Override
	public List<RentAreaEntity> areaEmptyByBuildingId(Long buildingId) {
		List<RentAreaEntity> resutls = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		// Open a connection
		try {
			conn = ConnectionUtils.getConnection();
			stmt = conn.createStatement();
			String sql ="select rentarea.value from rentarea where rentarea.buildingid= "+buildingId;		
			rs = stmt.executeQuery(sql);
			// Extract data from result set
			while (rs.next()) {		
				RentAreaEntity rentAreaEntity = new RentAreaEntity();
				rentAreaEntity.setValue(rs.getInt("value"));		
				resutls.add(rentAreaEntity);	
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
