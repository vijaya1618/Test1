package com.lms.Login;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.springframework.stereotype.Component;

@Component
public class Login_DAOImpl implements Login_DAO {
	
	protected JdbcTemplate jdbcTemplate;
	protected DataFieldMaxValueIncrementer taskIncer;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Login_model LoginStudent(Login_model lm) {
		// TODO Auto-generated method stub
		Login_model s=new Login_model();
		String sql = "select student_password from lms_student_profile where student_firstname=?";
		try {
			String i = jdbcTemplate.queryForObject(sql, new Object[] { lm.getUsername() }, String.class);
			if (i.equals(lm.getPassword())) {
				s.setStatus(1);
				int j = Student_IdRetriever(lm.getUsername());
				s.setStudentid(j);
			} else {
				s.setStatus(2);
			}
		} catch (Exception e) {
			s.setStatus(3);
		}
		return s;
	}

	public Login_model LoginTutor(Login_model lm) {
		// TODO Auto-generated method stub
		 Login_model t=new Login_model();
		String sql = "select tutor_password from lms_tutor_credentials where tutor_name=?";
		try {
			String i = jdbcTemplate.queryForObject(sql, new Object[] { lm.getUsername() }, String.class);
			if (i.equals(lm.getPassword())) {
				t.setStatus(1);
				int j = Tutor_IdRetriever(lm.getUsername());
				t.setTutorid(j);
			} else {
				t.setStatus(2);
			}
		} catch (Exception e) {
			t.setStatus(3);
		}
		return t;
	}

	public Login_model LoginAdmin(Login_model sm) {
		// TODO Auto-generated method stub

		Login_model a = new Login_model();
		String sql = "select admin_password from lms_admin_credentials where admin_name=?";
		try {
			String i = jdbcTemplate.queryForObject(sql, new Object[] { sm.getUsername() }, String.class);
			if (i.equals(sm.getPassword())) {
				
				a.setStatus(1);
				int j = Admin_IdRetriever(sm.getUsername());
				a.setAdminid(j);

			} else {
				a.setStatus(2);
			}
		} catch (Exception e) {
			a.setStatus(3);
		}
		return a;
	}

	public int Student_IdRetriever(String fn) {
		// TODO Auto-generated method stub
		int i = 0;

		String sql = "select student_id from [LMS].[dbo].[lms_student_profile] where student_firstname=?";
		try {
			i = jdbcTemplate.queryForObject(sql, new Object[] { fn }, Integer.class);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	public int Tutor_IdRetriever(String fn) {
		// TODO Auto-generated method stub
		int i = 0;

		String sql = "select tutor_id from  [LMS].[dbo].[lms_tutor_credentials] where tutor_name=?";
		try {
			i = jdbcTemplate.queryForObject(sql, new Object[] { fn }, Integer.class);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	public int Admin_IdRetriever(String fn) {
		// TODO Auto-generated method stub
		int i = 0;

		String sql = "select admin_id from [LMS].[dbo].[lms_admin_credentials] where admin_name=?";
		try {
			i = jdbcTemplate.queryForObject(sql, new Object[] { fn }, Integer.class);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	public void contactInsert(contact c) {
		try{
			//System.out.println(current);
			String sql = "INSERT INTO Contact VALUES(?,?,?,?)";
			System.out.println(c.getName());
			System.out.println(c.getEmail());
			System.out.println(c.getSubject());
			System.out.println(c.getMessage());
			
			
			Object[] params = new Object[] { c.getName(),c.getEmail(),c.getSubject(),c.getMessage()};
			int[] types = new int[] { Types.VARCHAR, Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
			jdbcTemplate.update(sql, params, types);	
			
			}
			catch(Exception e){
				System.out.println(e);
			}
			}	
}
