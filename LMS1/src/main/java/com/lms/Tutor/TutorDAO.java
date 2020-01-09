package com.lms.Tutor;
import java.io.FileNotFoundException;
import java.util.*;
public interface TutorDAO {

	public TutorCards findCards() throws Exception;	
	public List findCoursesInfo() throws Exception;
	public  String file(ImageBean ib) throws FileNotFoundException;			

	public int Epaper(EpaperModel epm);
	public int Epaper1(EpaperModel epm);
	public int Epaper2(EpaperModel epm);
	public String  UpdateExam(ExamSchedule t) throws Exception;
	public Updatetutormodel updateprofile(int id);
	public int updatetutor(Updatetutormodel um);
}
