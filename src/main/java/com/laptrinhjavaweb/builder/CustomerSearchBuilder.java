package com.laptrinhjavaweb.builder;

public class CustomerSearchBuilder {
	private String fullName;
	private String phone;
	private String email;
	private Long staffId;
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getStaffId() {
		return staffId;
	}
	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}
	
	public CustomerSearchBuilder(Builder builder){
		this.fullName = builder.fullName;
		this.phone = builder.phone;
		this.email = builder.email;
		this.staffId = builder.staffId;
	}
	
	public static class Builder {
		private String fullName;
		private String phone;
		private String email;
		private Long staffId;
		
		public Builder setFullName(String fullName) {
			this.fullName = fullName;
			return this;
		}
		public Builder setPhone(String phone) {
			this.phone = phone;
			return this;
		}
		public Builder setEmail(String email) {
			this.email = email;
			return this;
		}
		public Builder setStaffId(Long staffId) {
			this.staffId = staffId;
			return this;
		}
		public CustomerSearchBuilder build() {
			return new CustomerSearchBuilder(this);
		}
	}
	
	
	
}
