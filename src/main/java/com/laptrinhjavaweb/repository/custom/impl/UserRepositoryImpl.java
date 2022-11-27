package com.laptrinhjavaweb.repository.custom.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.laptrinhjavaweb.repository.custom.UserRepositoryCustom;
import com.laptrinhjavaweb.repository.entity.UserEntity;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<UserEntity> findByStatusAndRolesCode(Integer status, String roleCode) {
		String sql = "select * from user inner join userrole on user.id= userrole.userid inner join role on role.id= userrole.roleid "
				+ "where user.status = " + status + " and role.code = '" + roleCode + "'";
		return entityManager.createNativeQuery(sql, UserEntity.class).getResultList();

	}

}
