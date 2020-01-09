package com.lms.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class usermapper1 implements RowMapper {

		// TODO Auto-generated method stub
		public Updatemodel mapRow(ResultSet rs, int rowNum) throws SQLException {
		Updatemodel m=new Updatemodel();
			m.setFirstname(rs.getString("student_firstname"));
			m.setLastname(rs.getString("student_lastname"));
			m.setEmail(rs.getString("student_email"));
			m.setDesignation(rs.getString("student_designation"));
			m.setNumber(rs.getLong("student_mobilenumber"));
			return m;
		
	}

}
