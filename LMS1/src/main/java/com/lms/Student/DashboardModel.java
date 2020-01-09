package com.lms.Student;

public class DashboardModel {

	private int courseCount;

	public int getCourseCount() {
		return courseCount;
	}

	public void setCourseCount(int courseCount) {
		this.courseCount = courseCount;
	}

	public int getMaterialCount() {
		return materialCount;
	}

	public void setMaterialCount(int materialCount) {
		this.materialCount = materialCount;
	}

	public int getExamsCount() {
		return examsCount;
	}

	public void setExamsCount(int examsCount) {
		this.examsCount = examsCount;
	}

	private int materialCount;
	private int examsCount;
}
