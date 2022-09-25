package com.laptrinhjavaweb.repository.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class CustomerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "fullname")
	private String fullName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone", unique = true)
    private String phone;
    
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<TransactionEntity> transactions = new ArrayList<>();
    
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<AssignmentCustomerEntity> assignmentCustomers = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<TransactionEntity> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<TransactionEntity> transactions) {
		this.transactions = transactions;
	}

	public List<AssignmentCustomerEntity> getAssignmentCustomers() {
		return assignmentCustomers;
	}

	public void setAssignmentCustomers(List<AssignmentCustomerEntity> assignmentCustomers) {
		this.assignmentCustomers = assignmentCustomers;
	}
    
    
}
