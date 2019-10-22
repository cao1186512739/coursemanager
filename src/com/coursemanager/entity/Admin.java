package com.coursemanager.entity;

public class Admin {
	private int a_id;
	private String a_no;
	private String a_password;
	private int a_type;
	private String a_name;
	public int getA_type() {
		return a_type;
	}
	public void setA_type(int a_type) {
		this.a_type = a_type;
	}
	public int getA_id() {
		return a_id;
	}
	public void setA_id(int a_id) {
		this.a_id = a_id;
	}
	public String getA_no() {
		return a_no;
	}
	public void setA_no(String a_no) {
		this.a_no = a_no;
	}
	public String getA_password() {
		return a_password;
	}
	public void setA_password(String a_password) {
		this.a_password = a_password;
	}	
	public String getA_name() {
		return a_name;
	}
	public void setA_name(String a_name) {
		this.a_name = a_name;
	}
	@Override
	public String toString() {
		return "Admin [a_id=" + a_id + ", a_no=" + a_no + ", a_password=" + a_password + ", a_type=" + a_type + "]";
	}
	
}
