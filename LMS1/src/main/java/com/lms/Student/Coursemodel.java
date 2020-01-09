package com.lms.Student;

public class Coursemodel {
	
	private int courseid;
	private String coursename;
	private int courseduration;
	private int departmentid;
	private int tutorid;
	public int getTutorid() {
		return tutorid;
	}
	public void setTutorid(int tutorid) {
		this.tutorid = tutorid;
	}
	public int getDepartmentid() {
		return departmentid;
	}
	public void setDepartmentid(int departmentid) {
		this.departmentid = departmentid;
	}
	public int getCourseid() {
		return courseid;
	}
	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public int getCourseduration() {
		return courseduration;
	}
	public void setCourseduration(int courseduration) {
		this.courseduration = courseduration;
	}
}
