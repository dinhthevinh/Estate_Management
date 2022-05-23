package com.laptrinhjavaweb.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.laptrinhjavaweb.repository.DistrictRepository;
import com.laptrinhjavaweb.repository.entity.DistrictEntity;
import com.laptrinhjavaweb.utils.ConnectionUtils;

@Repository
public class DistrictRepositoryImpl implements DistrictRepository {

	@Override
	public DistrictEntity findDistrictById(Long districtId) {
		DistrictEntity resutl = new DistrictEntity();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		// Open a connection
		try {
			conn = ConnectionUtils.getConnection();
			stmt = conn.createStatement();	
			String sql ="select district.name from district join building on district.id = "+ districtId;
			rs = stmt.executeQuery(sql);
			// Extract data from result set
			while (rs.next()) {		
				DistrictEntity districtEntity = new DistrictEntity();
				districtEntity.setName(rs.getString("name"));	
			}
			return resutl;
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
