package com.laptrinhjavaweb.repository.custom.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.repository.custom.BuildingRepositoryCustom;
import com.laptrinhjavaweb.repository.entity.BuildingEntity;
import com.laptrinhjavaweb.utils.MapUtils;
import com.laptrinhjavaweb.utils.NumberUtils;
import com.laptrinhjavaweb.utils.StringUtils;

@Primary
@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<BuildingEntity> findBuilding(Map<String, String> requestParams, List<String> types) {
		StringBuilder sql = new StringBuilder();
		sql = buildSqlJoining(requestParams, types, sql);
		sql.append(" " + SystemConstant.one_equal_one + " ");
		sql = buildSqlCommon(requestParams, types, sql);
		sql = buildSqlSpecial(requestParams, types, sql);
		sql.append(" group by building.id");
		Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
		return query.getResultList();

	}

	private StringBuilder buildSqlJoining(Map<String, String> requestParams, List<String> types, StringBuilder sql) {
		sql.append("select * from building inner join  district on building.districtid = district.id");
		Long staffid = MapUtils.getObject(requestParams, "staffid", Long.class);
		if (staffid != null) {
			sql.append(
					" inner join assignmentbuilding on  building.id = assignmentbuilding.buildingid and assignmentbuilding.staffid = "
							+ requestParams.get("staffid"));
		}
		if (types != null) {
			sql.append(" inner join  buildingrenttype on building.id = buildingrenttype.buildingid \r\n"
					+ "inner join  renttype on buildingrenttype.renttypeid = renttype.id ");
			sql.append(" and (");
			String sqlJoin = types.stream().map(item -> "renttype.code = '" + item + "'")
					.collect(Collectors.joining(" or "));
			sql.append(sqlJoin);
			sql.append(")");
		}
		return sql;
	}

	private StringBuilder buildSqlCommon(Map<String, String> requestParams, List<String> types, StringBuilder sql) {

		for (Map.Entry<String, String> item : requestParams.entrySet()) {
			if (!item.getKey().equals("staffid") && !item.getKey().equals("types") && !item.getKey().equals("district")
					&& !item.getKey().startsWith("rentarea") && !item.getKey().startsWith("rentprice")) {
				String value = item.getValue().toString();
				if (!(item.getKey().equals("ward")) && !(item.getKey().equals("street"))
						&& !(item.getKey().equals("name")) && !(item.getKey().equals("managerName"))
						&& !(item.getKey().equals("managerPhone"))) {
					if (NumberUtils.isInteger(value)) {
						sql.append(" and building." + item.getKey().toLowerCase() + " = " + Integer.parseInt(value));
					}
				} else {
					if (!StringUtils.isNullOrEmpty(value)) {
						sql.append(" and building." + item.getKey().toLowerCase() + " like '%" + value + "%'");
					}
				}
			}
		}
		return sql;
	}

	private StringBuilder buildSqlSpecial(Map<String, String> requestParams, List<String> types, StringBuilder sql) {
		Integer rentAreaTo = MapUtils.getObject(requestParams, "rentareato", Integer.class);
		Integer rentAreaFrom = MapUtils.getObject(requestParams, "rentareafrom", Integer.class);
		Integer rentPriceTo = MapUtils.getObject(requestParams, "rentpriceto", Integer.class);
		Integer rentpricefrom = MapUtils.getObject(requestParams, "rentpricefrom", Integer.class);
		String district = MapUtils.getObject(requestParams, "district", String.class);
		if (!StringUtils.isNullOrEmpty(district)) {
			sql.append(" and district.code = '" + district + "'");
		}
		if (rentPriceTo != null) {
			sql.append(" and building.rentprice <=" + rentPriceTo + "");
		}
		if (rentpricefrom != null) {
			sql.append(" and building.rentprice >=" + rentpricefrom + "");
		}

		if (rentAreaFrom != null || rentAreaTo != null) {
			sql.append(" and EXISTS (SELECT value from rentarea where building.id = rentarea.id");
			if (rentAreaFrom != null) {
				sql.append(" and  rentarea.value >= " + rentAreaFrom + "");
			}
			if (rentAreaTo != null) {
				sql.append(" and  rentarea.value <= " + rentAreaTo + "");
			}
			sql.append(")");
		}

		return sql;
	}

	@Override
	public List<BuildingEntity> findBuilding(BuildingSearchBuilder builder) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from building");
		sql = buildSqlJoiningUsingBuilder(builder, sql);
		sql.append(" " + SystemConstant.one_equal_one + " ");
		sql = buildSqlCommonUsingBuilder(builder, sql);
		sql = buildSqlSpecialUsingBuilder(builder, sql);
		sql.append(" group by building.id");
		Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
		return query.getResultList();
	}

	private StringBuilder buildSqlJoiningUsingBuilder(BuildingSearchBuilder builder, StringBuilder sql) {
		Long staffid = builder.getStaffId();
		if (staffid != null) {
			sql.append(
					" inner join assginmentbuilding on  building.id = assginmentbuilding.buildingid and assginmentbuilding.staffid = "
							+ staffid);
		}
		return sql;
	}

	private StringBuilder buildSqlCommonUsingBuilder(BuildingSearchBuilder builder, StringBuilder sql) {

		try {
			Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				String fieldName = field.getName();
				if (!fieldName.equals("staffid") && !fieldName.startsWith("rentArea")
						&& !fieldName.startsWith("rentPrice")) {
					Object objectValue = field.get(builder);
					if (objectValue != null && !objectValue.toString().isEmpty()) {
						if (field.getType().getName().equals("java.lang.String")) {
							sql.append(" and building." + fieldName.toLowerCase() + " like '%" + objectValue + "%'");
						} else if (field.getType().getName().equals("java.lang.Integer")) {
							sql.append(" and building." + fieldName.toLowerCase() + " = " + objectValue + "");
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sql;
	}

	private StringBuilder buildSqlSpecialUsingBuilder(BuildingSearchBuilder builder, StringBuilder sql) {

		Integer rentPriceFrom = builder.getRentPriceFrom();
		Integer rentPriceTo = builder.getRentPriceTo();
		Integer rentAreaFrom = builder.getRentAreaFrom();
		Integer rentAreaTo = builder.getRentAreaTo();
		List<String> buildingTypes = builder.getBuildingTypes();
		if (buildingTypes != null && buildingTypes.size() != 0) {
			sql.append(" and (");
			String sqlJoin = buildingTypes.stream().map(item -> "building.type like '%" + item + "%'")
					.collect(Collectors.joining(" or "));
			sql.append(sqlJoin);
			sql.append(")");
		}
		if (rentPriceTo != null) {
			sql.append(" and building.rentprice <=" + rentPriceTo + "");
		}
		if (rentPriceFrom != null) {
			sql.append(" and building.rentprice >=" + rentPriceFrom + "");
		}

		if (rentAreaFrom != null || rentAreaTo != null) {
			sql.append(" and EXISTS (SELECT value from rentarea where building.id = rentarea.id");
			if (rentAreaFrom != null) {
				sql.append(" and  rentarea.value >= " + rentAreaFrom + "");
			}
			if (rentAreaTo != null) {
				sql.append(" and  rentarea.value <= " + rentAreaTo + "");
			}
			sql.append(")");

		}
		return sql;
	}
}
