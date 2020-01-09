package com.lms.Student;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;

public interface Dao {
	
	public List<Model> find(int id);
	
	public Updatemodel updateprofile(int id);

	public int Updateinsert(int id,Updatemodel um);
	
	public int Updatepassword(int id,String passowrd);
	
	public List<Coursemodel> findall();
	
	public Coursemodel findcourse( int id);
	
	public List<Tutormodel> findTutor();
	
	public int enroll(Coursemodel cm);
	public List<Coursemodel> findnews(LocalDate d); 
	public ImageBean retrieve1(String s) throws Exception;
	public void file(ImageBean ib) throws FileNotFoundException;
	 
    public java.util.List<QuestionModel> getQuestions(String s);
    public String getAnswerById(String id);
    public void insert(Marksmodel m);
    public int feedbacksubmit(feedbackmodel fm);
    public List<Marksmodel> displayMarks();
    public DashboardModel countDashboard();
}

