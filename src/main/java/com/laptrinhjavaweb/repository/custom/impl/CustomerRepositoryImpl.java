package com.laptrinhjavaweb.repository.custom.impl;

import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.laptrinhjavaweb.builder.CustomerSearchBuilder;
import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.repository.custom.CustomerRepositoryCustom;
import com.laptrinhjavaweb.repository.entity.CustomerEntity;

@Repository
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<CustomerEntity> findCustomer(CustomerSearchBuilder builder) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from customer");
		sql.append(buildsqljoin(builder));
		sql.append(" " + SystemConstant.one_equal_one + " ");
		sql.append(buildSqlCommon(builder));
		Query query = entityManager.createNativeQuery(sql.toString(), CustomerEntity.class);
		return query.getResultList();
	}

	private StringBuilder buildsqljoin(CustomerSearchBuilder builder) {
		StringBuilder sql = new StringBuilder();
		Long staffid = builder.getStaffId();
		if (staffid != null) {
			sql.append(
					" inner join assginmentcustomer on  customer.id = assginmentcustomer.customerid and assginmentcustomer.staffid = "
							+ staffid);
		}
		return sql;
	}

	private StringBuilder buildSqlCommon(CustomerSearchBuilder builder) {
		StringBuilder sql = new StringBuilder();
		try {
			Field[] fields = CustomerSearchBuilder.class.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				String fieldName = field.getName();
				if (!fieldName.equals("staffid")) {
					Object objectValue = field.get(builder);
					if (objectValue != null && !objectValue.toString().isEmpty()) {
						if (field.getType().getName().equals("java.lang.String")) {
							sql.append(" and customer." + fieldName.toLowerCase() + " like '%" + objectValue + "%'");
						} else if (field.getType().getName().equals("java.lang.Integer")) {
							sql.append(" and customer." + fieldName.toLowerCase() + " = " + objectValue + "");
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sql;

	}

}
