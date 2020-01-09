package com.lms.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class TutorRowmapper implements RowMapper {

	public Tutormodel mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Tutormodel tm=new Tutormodel();
		tm.setTutorid(rs.getInt("course_tutor_id"));	
		return tm;
	}

}
