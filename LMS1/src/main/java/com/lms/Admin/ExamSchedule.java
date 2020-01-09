package com.lms.Admin;

public class ExamSchedule {
	 private int Tutorid;
	 private String TutorName;
	 public int getTutorid() {
		return Tutorid;
	}
	public void setTutorid(int tutorid) {
		Tutorid = tutorid;
	}
	public String getTutorName() {
		return TutorName;
	}
	public void setTutorName(String tutorName) {
		TutorName = tutorName;
	}
	public String getExamName() {
		return ExamName;
	}
	public void setExamName(String examName) {
		ExamName = examName;
	}
	public String getSubName() {
		return SubName;
	}
	public void setSubName(String subName) {
		SubName = subName;
	}
	public int getMarks() {
		return Marks;
	}
	public void setMarks(int marks) {
		Marks = marks;
	}
	private String ExamName;
	 private String SubName; 
	 private int Marks;
	 
}
