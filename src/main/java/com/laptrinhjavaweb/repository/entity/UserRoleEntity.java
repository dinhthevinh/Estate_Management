package com.laptrinhjavaweb.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "userrole")
public class UserRoleEntity {
	
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private UserEntity user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleid")
    private RoleEntity roles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public RoleEntity getRoles() {
		return roles;
	}

	public void setRoles(RoleEntity roles) {
		this.roles = roles;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
    
    
    
}
