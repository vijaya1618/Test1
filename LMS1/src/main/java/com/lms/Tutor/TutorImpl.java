package com.lms.Tutor;


import java.util.List;
import java.io.FileNotFoundException;
import java.sql.*;
import javax.sql.*;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.zkoss.zul.Messagebox;




public class TutorImpl implements TutorDAO{
	private JdbcTemplate jdbcTemplate;
	protected DataFieldMaxValueIncrementer taskIncer;
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void setJdbcTemplate(JdbcTemplate  jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setTaskIncer(DataFieldMaxValueIncrementer taskIncer) {
		this.taskIncer = taskIncer;
	}
	
	
	protected class TaskMapper1 implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		
			CourseInfo c=new CourseInfo();
				System.out.println(c.getCourse_id());
			c.setCourse_id(rs.getInt("course_id"));
			
			c.setDepartment_id(rs.getInt("department_id"));
			c.setCourse_name(rs.getString("course_name"));
			c.setCourse_duration(rs.getInt("course_duration"));
			c.setTutor_id(rs.getInt("course_tutor_id"));
			
			return c;
		}
	}
	protected class TaskMapper2 implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

			TutorCards tc=new TutorCards();
			tc.setStudents(rs.getString("students"));
			tc.setScount(rs.getInt("scount"));
			tc.setCourses(rs.getString("courses"));
			tc.setCcount(rs.getInt("ccount"));
			tc.setFilesnames(rs.getString("filesnames"));
			tc.setFcount(rs.getInt("fcount"));
			tc.setLectures(rs.getString("lectures"));
			tc.setLcount(rs.getInt("lcount"));
			
			return tc;
	
			
		
		}
	}


	@Override
	public TutorCards findCards() throws Exception {
		String sql = "SELECT * FROM tutor_cards";
		List<TutorCards> ts =jdbcTemplate.query(sql, new TaskMapper2());
				return ts.get(0);
		// TODO Auto-generated method stub
	
	}
	
	public List findCoursesInfo() throws Exception {
		String sql = "select * from lms_course";
		return jdbcTemplate.query(sql, new TaskMapper1());
	}
	public String  file(ImageBean ib) throws FileNotFoundException {			
		String sql2="insert into [LMS].[dbo].[materials] values(?,?,?)";
		Object[] params = new Object[] {ib.getCourseid(),ib.getCoursename(),ib.getImage()};
		int types[] = new int[] {Types.INTEGER,Types.VARCHAR,Types.LONGVARBINARY}; 
		int i=jdbcTemplate.update(sql2, params, types);
		if(i==1)
			return "success";
		else
			return "fail";
	}
	public int Epaper(EpaperModel epm) {
		int i=0;
		try{
			//System.out.println(current);
			
			String sql = "insert into lms_exam_java(question,option_a,option_b,option_c,option_d,correct_ans,question_type) values(?,?,?,?,?,?,?)";

			Object[] params = new Object[] {epm.getQuestion(),epm.getOption_a(),epm.getOption_b(),epm.getOption_c(),epm.getOption_d(),epm.getCorrect_ans(),epm.getQuestion_type()};
			int[] types = new int[] { Types.VARCHAR, Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};

			 i=jdbcTemplate.update(sql, params, types);
			
			
			}
			catch(Exception e){
				System.out.println(e);
				
			}
		return i;
			}

	public int Epaper1(EpaperModel epm) {
		int i=0;
		// TODO Auto-generated method stub
		try{
			//System.out.println(current);
			
			String sql = "insert into lms_exam_c(question,option_a,option_b,option_c,option_d,correct_ans,question_type) values(?,?,?,?,?,?,?)";

			Object[] params = new Object[] {epm.getQuestion(),epm.getOption_a(),epm.getOption_b(),epm.getOption_c(),epm.getOption_d(),epm.getCorrect_ans(),epm.getQuestion_type()};
			int[] types = new int[] { Types.VARCHAR, Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
			 i=jdbcTemplate.update(sql, params, types);
			
			
			}
			catch(Exception e){
				System.out.println(e);
				
			}
		return i;
	}
	

	public int Epaper2(EpaperModel epm) {
		int i=0;
		// TODO Auto-generated method stub
		try{
			//System.out.println(current);
			
			String sql = "insert into lms_exam_html(question,option_a,option_b,option_c,option_d,correct_ans,question_type) values(?,?,?,?,?,?,?)";

			Object[] params = new Object[] {epm.getQuestion(),epm.getOption_a(),epm.getOption_b(),epm.getOption_c(),epm.getOption_d(),epm.getCorrect_ans(),epm.getQuestion_type()};
			int[] types = new int[] {  Types.VARCHAR, Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};

			i=jdbcTemplate.update(sql, params, types);
			
			
			}
			catch(Exception e){
				System.out.println(e);
				
			}
				return i;
	}	
	

	public String UpdateExam(ExamSchedule t) throws Exception{
		int i=0;
		try{
			//System.out.println(t.getDept_id());
			String sql = "INSERT INTO tutor_exam_model(tutorid,tutorname,examname,subjectname,marks) VALUES(?,?,?,?,?)";
		
			
			Object[] params = new Object[] { t.getTutorid(),t.getTutorName(),t.getExamName(),t.getSubName(),t.getMarks()};
			int[] types = new int[] { Types.INTEGER, Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER};
			i=jdbcTemplate.update(sql, params, types);	
			
			}
			catch(Exception e){
				System.out.println(e);
			}
		if(i==1)
		return "success";
		else
			return "fail";
			}	
	public Updatetutormodel updateprofile(int id) {
		// TODO Auto-generated method stub
		String sql = "select * from lms_tutor_credentials where tutor_id=?";

		return (Updatetutormodel) jdbcTemplate.queryForObject(sql, new Object[] { id }, new tutormapper());
	}

	public int updatetutor(Updatetutormodel um) {
		// TODO Auto-generated method stub
		
		String sql = "update lms_tutor_credentials set tutor_name=?,tutor_email=?,tutor_mobile=? where tutor_id=?";
		// TODO Auto-generated method stub
		int i = jdbcTemplate.update(sql, new Object[] { um.getName(),um.getEmail(),um.getNum(),um.getId() });
		return i;
	}
	
	
	public class tutormapper implements RowMapper {

		public Updatetutormodel mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			Updatetutormodel um=new Updatetutormodel();
			um.setId(rs.getInt(1));
			um.setName(rs.getString(2));
			um.setEmail(rs.getString(3));
			um.setNum(rs.getLong(5));
			return um;
		}

	}
	
	
	
	
	
	
	
	
}
