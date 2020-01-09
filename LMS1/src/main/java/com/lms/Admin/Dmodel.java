package com.lms.Admin;

public class Dmodel {
	private int course_id;
	private int examid;
	private int student_id;
    private int feedback_id;
    private int tutor_id;
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int ans) {
		this.course_id = ans;
	}
	public int getExamid() {
		return examid;
	}
	public void setExamid(int examid) {
		this.examid = examid;
	}
	public int getStudent_id() {
		return student_id;
	}
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}
	public int getFeedback_id() {
		return feedback_id;
	}
	public void setFeedback_id(int feedback_id) {
		this.feedback_id = feedback_id;
	}
	public int getTutor_id() {
		return tutor_id;
	}
	public void setTutor_id(int tutor_id) {
		this.tutor_id = tutor_id;
	}
}
