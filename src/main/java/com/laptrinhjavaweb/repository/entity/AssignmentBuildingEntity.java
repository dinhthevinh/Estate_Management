package com.laptrinhjavaweb.repository.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "assignmentbuilding")
public class AssignmentBuildingEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buildingid")
    private BuildingEntity building;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staffid")
    private UserEntity user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BuildingEntity getBuilding() {
		return building;
	}

	public void setBuilding(BuildingEntity building) {
		this.building = building;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
	
	
	
	
}
