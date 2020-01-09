package com.lms.Admin;

import java.io.FileNotFoundException;
import java.util.List;


public interface AdminDao {
	public List Dept_Names() throws Exception;
	public List FeedBack() throws Exception;

	public String UpdateCourse(CourseInsert t) throws Exception;
	public List TutorList() throws Exception;
	public void UpdateExam(ExamSchedule es) throws Exception;
	
	
	
	
	
	public String  update(ExamModel em) throws Exception;
	public void delete(ExamModel em) throws Exception;
	public void deleteUser(UserInfo ui) throws Exception;
	public Task findById(int id) throws Exception;
	public String ExamInsert(ExamModel em);
	public List findAll() throws Exception;
	public List findAllUsers() throws Exception;
	public List findExams() throws Exception;
	public void file(byte[] b) throws FileNotFoundException;
	public void retrieve();
	
	
	
	public void file(ImageBean ib) throws FileNotFoundException;
	public ImageBean retrieve1() throws Exception;
	public List findTotalMarks() throws Exception;
	 public Dmodel  procedure();
}
