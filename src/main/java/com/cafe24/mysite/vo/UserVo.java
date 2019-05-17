package com.cafe24.mysite.vo;

public class UserVo {
	private Long no;
	private String name;
	private String email;
	private String password;
	private String gender;
	private String joinDate;
	
	public UserVo() {
	}
	
	public UserVo(String email, String password) {
		this.email=email;
		this.password=password;
	}
	public UserVo(Long no, String name, String password, String gender) {
		this.no=no;
		this.name=name;		
		this.password=password;
		this.gender=gender;

	}
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	@Override
	public String toString() {
		return "UserVo [no=" + no + ", name=" + name + ", email=" + email + ", password=" + password + ", gender="
				+ gender + ", joinDate=" + joinDate + "]";
	}
	
}

		
