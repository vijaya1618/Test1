package com.lms.Admin;

import java.sql.Date;

public class ExamModel {
	
private int examId;
private String examname;
private String subjectname;
private Date examdate;
private int marks;
public int getExamId() {
	return examId;
}
public void setExamId(int examId) {
	this.examId = examId;
}

public String getSubjectname() {
	return subjectname;
}
public void setSubjectname(String subjectname) {
	this.subjectname = subjectname;
}


public String getExamname() {
	return examname;
}
public void setExamname(String examname) {
	this.examname = examname;
}

public Date getExamdate() {
	return examdate;
}
public void setExamdate(Date examdate) {
	this.examdate = examdate;
}
public int getMarks() {
	return marks;
}
public void setMarks(int marks) {
	this.marks = marks;
}


}
