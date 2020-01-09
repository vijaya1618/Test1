package com.lms.Admin;

import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.*;
import javax.sql.*;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;

public class AdminDaoImpl implements AdminDao {
	private JdbcTemplate jdbcTemplate;
	protected DataFieldMaxValueIncrementer taskIncer;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	protected class DeptMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			DeptNames t = new DeptNames();
			// Task t1=new Task();
			t.setDeptName(rs.getString("department_name"));
			t.setDeptId(rs.getInt("department_id"));
			return t;
		}
	}

	protected class FeedMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserFeedback t = new UserFeedback();
			// Task t1=new Task();
			t.setId(rs.getInt("student_id"));
			t.setSub(rs.getString("feedback_sub"));
			t.setDesc(rs.getString("feedback"));

			return t;
		}
	}

	public List Dept_Names() throws Exception {

		String sql = "SELECT * FROM lms_departments";
		return jdbcTemplate.query(sql, new DeptMapper());

	}

	public List FeedBack() throws Exception {

		String sql = "SELECT * FROM lms_user_feedback";
		return jdbcTemplate.query(sql, new FeedMapper());

	}

	public String UpdateCourse(CourseInsert t) throws Exception {
		int i = 0;
		try {
			// System.out.println(t.getDept_id());
			String sql = "INSERT INTO lms_course(department_id,course_name,course_duration,course_tutor_id) VALUES(?,?,?,?)";

			Object[] params = new Object[] { t.getDept_id(), t.getCourse_Name(), t.getCourse_Duration(),
					t.getTutor_Id() };
			int[] types = new int[] { Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.INTEGER };
			i = jdbcTemplate.update(sql, params, types);

		} catch (Exception e) {
			System.out.println(e);
		}

		if (i == 1)
			return "success";
		else
			return "fail";
	}

	public List TutorList() throws Exception {

		String sql = "SELECT * FROM lms_tutor_credentials";
		return jdbcTemplate.query(sql, new TutorMapper());

	}

	protected class TutorMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			TutorDetails t = new TutorDetails();
			// Task t1=new Task();
			t.setTutor_Id(rs.getInt("tutor_id"));
			t.setTutor_Name(rs.getString("tutor_name"));
			t.setTutor_Mobile(rs.getLong("tutor_mobile"));

			return t;
		}
	}

	public void UpdateExam(ExamSchedule t) throws Exception {
		try {
			// System.out.println(t.getDept_id());
			String sql = "INSERT INTO tutor_exam_model(tutorid,tutorname,examname,subjectname,marks) VALUES(?,?,?,?,?)";

			Object[] params = new Object[] { t.getTutorid(), t.getTutorName(), t.getExamName(), t.getSubName(),
					t.getMarks() };
			int[] types = new int[] { Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER };
			jdbcTemplate.update(sql, params, types);

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	int i = 0;

	public String update(ExamModel em) {
		String sql = "UPDATE exammodel SET examname = ?, subjectname = ?, examdate = ?, marks = ?  WHERE examid = ?";
		System.out.println(em.getExamId());

		Object[] params = new Object[] { em.getExamname(), em.getSubjectname(), em.getExamdate(), em.getMarks(),
				em.getExamId() };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.DATE, Types.INTEGER, Types.INTEGER };
		int i = jdbcTemplate.update(sql, params, types);
		if (i == 1)
			return "success";
		else
			return "fail";
	}

	public void delete(ExamModel em) throws Exception {
		String sql = "DELETE FROM exammodel WHERE examid = ?";
		Object[] params = new Object[] { em.getExamId() };
		int types[] = new int[] { Types.INTEGER };
		jdbcTemplate.update(sql, params, types);
	}

	public void deleteUser(UserInfo um) throws Exception {
		String sql = "DELETE FROM users WHERE id = ?";
		Object[] params = new Object[] { um.getId() };
		int types[] = new int[] { Types.INTEGER };
		jdbcTemplate.update(sql, params, types);
	}

	int st = 0;

	public String ExamInsert(ExamModel em) {
		try {
			// System.out.println(current);
			String sql = "INSERT INTO exammodel VALUES(NEXT VALUE FOR id_seq,?,?,?,?)";
			Object[] params = new Object[] { em.getExamname(), em.getSubjectname(), em.getExamdate(), em.getMarks() };
			int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.DATE, Types.INTEGER };
			st = jdbcTemplate.update(sql, params, types);
			if (st == 1)
				return "success";
			else
				return "fail";
		}

		catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			return e.getMessage();
		}
	}

	protected class TaskMapper1 implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			// Task t = new Task();
			// t.setTitle(rs.getString("title"));
			// t.setDescription(rs.getString("description"));

			UserInfo ui = new UserInfo();
			ui.setId(rs.getInt("student_id"));
			ui.setName(rs.getString("student_firstname"));
			ui.setMail(rs.getString("student_email"));
			ui.setGender(rs.getString("student_gender"));
			ui.setDesignation(rs.getString("student_designation"));
			ui.setMobile(rs.getLong("student_mobilenumber"));

			return ui;
		}
	}

	protected class TaskMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			// Task t = new Task();
			// t.setTitle(rs.getString("title"));
			// t.setDescription(rs.getString("description"));

			TutorExam t = new TutorExam();
			t.setTutorid(rs.getString("tutorid"));
			t.setTutorname(rs.getString("tutorname"));
			t.setExamname(rs.getString("examname"));
			t.setSubjectname(rs.getString("subjectname"));
			t.setMarks(rs.getInt("marks"));

			return t;
		}
	}

	protected class TaskMapper2 implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			// Task t = new Task();
			// t.setTitle(rs.getString("title"));
			// t.setDescription(rs.getString("description"));

			ExamModel em = new ExamModel();
			em.setExamId(rs.getInt("examid"));
			em.setExamname(rs.getString("examname"));
			em.setSubjectname(rs.getString("subjectname"));
			em.setExamdate(rs.getDate("examdate"));
			em.setMarks(rs.getInt("marks"));

			return em;
		}
	}

	public Task findById(int id) throws Exception {
		String sql = "SELECT * FROM tasks WHERE id = ?";
		Object[] params = new Object[] { id };
		int types[] = new int[] { Types.INTEGER };

		List ts = jdbcTemplate.query(sql, params, types, new TaskMapper());
		if (ts.isEmpty())
			return null;
		return (Task) ts.get(0);
	}

	public List findAll() throws Exception {
		String sql = "SELECT * FROM tutor_exam_model";
		return jdbcTemplate.query(sql, new TaskMapper());
	}

	public List findAllUsers() throws Exception {
		String sql = "SELECT student_id,student_firstname,student_email,student_gender,student_designation,student_mobilenumber FROM lms_student_profile";
		return jdbcTemplate.query(sql, new TaskMapper1());
	}

	public List findExams() throws Exception {
		String sql = "SELECT * FROM exammodel";
		return jdbcTemplate.query(sql, new TaskMapper2());
	}

	public void file(ImageBean ib) throws FileNotFoundException {
		String sql2 = "insert into fileupload values(?,?)";
		Object[] params = new Object[] { ib.getImage(), ib.getFilename() };
		int types[] = new int[] { Types.LONGVARBINARY, Types.VARCHAR };
		jdbcTemplate.update(sql2, params, types);

	}

	public ImageBean retrieve1() throws Exception {
		System.out.println("In DAO CONNECTION ESTABLISED");
		String sql2 = " select * from fileupload where names='ms_sql_server_tutorial.pdf'";
		List<ImageBean> ib = jdbcTemplate.query(sql2, new ImageMapper());
		System.out.println("Image Returned");
		return ib.get(0);
	}

	protected class ImageMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			System.out.println("Query Executed");
			ImageBean ib = new ImageBean();
			ib.setImage(rs.getBytes("images"));
			ib.setFilename(rs.getString("names"));
			return ib;

		}
	}

	protected class TaskMapper4 implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Totalmarks t = new Totalmarks();

			t.setId(rs.getInt("studentid"));
			t.setExamname(rs.getString("examname"));
			t.setMarks(rs.getInt("marks"));

			return t;
		}
	}

	public List findTotalMarks() throws Exception {
		String sql = "select * from lms_Totalmarks";
		return jdbcTemplate.query(sql, new TaskMapper4());
	}

	@Override
	public void file(byte[] b) throws FileNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public void retrieve() {
		// TODO Auto-generated method stub

	}
	public Dmodel procedure()  
	{
		
		Dmodel m=new Dmodel();
       
		String qry="select count(*) from lms_course";
		String ans=jdbcTemplate.queryForObject(qry,new Object[] {},String.class);
		m.setCourse_id(Integer.parseInt(ans));
		System.out.print(ans);
		
		String qry1="select count(*) from exammodel";
		String ans1=jdbcTemplate.queryForObject(qry1,new Object[] {},String.class);
		m.setExamid(Integer.parseInt(ans1));
		System.out.print(ans1);
		
		String qry2="select count(*) from lms_student_profile";
		String ans2=jdbcTemplate.queryForObject(qry2,new Object[] {},String.class);
		m.setStudent_id(Integer.parseInt(ans2));
		
		String qry3="select count(*) from lms_user_feedback";
		String ans3=jdbcTemplate.queryForObject(qry3,new Object[] {},String.class);
		m.setFeedback_id(Integer.parseInt(ans3));
		
		String qry4="select count(*) from lms_tutor_credentials";
		String ans4=jdbcTemplate.queryForObject(qry4,new Object[] {},String.class);
		m.setTutor_id(Integer.parseInt(ans4));
		
	/*
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("dashboard3"); 
		Map<String, Object> out = jdbcCall.execute();
		
		int a=((Integer) out.get("course_id")).intValue();  
		m.setCourse_id(a);
	    m.setExamid(((Integer) out.get("examid")).intValue());  
	    m.setStudent_id(((Integer) out.get("student_id")).intValue());  
	    m.setFeedback_id(((Integer) out.get("feedback_id")).intValue()); 
	    m.setTutor_id(((Integer) out.get("tutor_id")).intValue());  
	    System.out.println(m);*/
	    return m;
	   
	} 
}
