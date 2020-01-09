package com.lms.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class usermapper implements RowMapper{


	public Model mapRow(ResultSet rs, int rowNum) throws SQLException {
		Model m=new Model();
		m.setCoursename(rs.getString("course_name"));
		return m;
	}

}
