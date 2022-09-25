package com.laptrinhjavaweb.repository.entity;

import javax.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class UserEntity  {

    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String userName;

   
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "fullname")
    private String fullName;

    @OneToMany(mappedBy = "user")
    private List<UserRoleEntity> userRoles = new ArrayList<>();
    
    @OneToMany(mappedBy = "user")
    private List<AssignmentBuildingEntity> assignmentBuildings = new ArrayList<>();
    
    @OneToMany(mappedBy = "user")
    private List<AssignmentCustomerEntity> assignmentCustomers = new ArrayList<>();

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "user_role",
//            joinColumns = @JoinColumn(name = "user_id", nullable = false),
//            inverseJoinColumns = @JoinColumn(name = "role_id", nullable = false))
//    private List<RoleEntity> roles = new ArrayList<>();
    
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

//    public List<RoleEntity> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(List<RoleEntity> roles) {
//        this.roles = roles;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public List<UserRoleEntity> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRoleEntity> userRoles) {
		this.userRoles = userRoles;
	}

	public List<AssignmentBuildingEntity> getAssignmentBuildings() {
		return assignmentBuildings;
	}

	public void setAssignmentBuildings(List<AssignmentBuildingEntity> assignmentBuildings) {
		this.assignmentBuildings = assignmentBuildings;
	}

	public List<AssignmentCustomerEntity> getAssignmentCustomers() {
		return assignmentCustomers;
	}

	public void setAssignmentCustomers(List<AssignmentCustomerEntity> assignmentCustomers) {
		this.assignmentCustomers = assignmentCustomers;
	}
    
	
    
}
