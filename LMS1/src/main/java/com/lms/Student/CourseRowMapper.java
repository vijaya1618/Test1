package com.lms.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class CourseRowMapper implements RowMapper {

	public Coursemodel mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		
		Coursemodel cm=new Coursemodel();
		cm.setCourseid(rs.getInt("course_id"));
		cm.setCoursename(rs.getString("course_name"));
		cm.setCourseduration(rs.getInt("course_duration"));
		cm.setDepartmentid(rs.getInt("department_id"));
		return cm;
	}

}
