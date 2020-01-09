package com.lms.Admin;

public class CourseInsert {
    private int Dept_id;
	public int getDept_id() {
		return Dept_id;
	}

	public void setDept_id(int dept_id) {
		Dept_id = dept_id;
	}

	private String Dept_name;
	private String Course_Name;
	private int Course_Duration;
	private int Tutor_Id;

	public String getDept_name() {
		return Dept_name;
	}

	public void setDept_name(String dept_name) {
		Dept_name = dept_name;
	}

	public String getCourse_Name() {
		return Course_Name;
	}

	public void setCourse_Name(String course_Name) {
		Course_Name = course_Name;
	}

	public int getCourse_Duration() {
		return Course_Duration;
	}

	public void setCourse_Duration(int course_Duration) {
		Course_Duration = course_Duration;
	}

	public int getTutor_Id() {
		return Tutor_Id;
	}

	public void setTutor_Id(int tutor_Id) {
		Tutor_Id = tutor_Id;
	}

}
