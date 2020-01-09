package com.lms.example1;

import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.springframework.stereotype.Component;


@Component
public class Student_LoginDAOImpl implements Student_LoginDAO{
	
//private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
protected JdbcTemplate jdbcTemplate;
int i;
protected DataFieldMaxValueIncrementer taskIncer;
public void setDataSource(DataSource dataSource) {
	this.jdbcTemplate = new JdbcTemplate(dataSource);
	
}


public int LoginInsert(Student_LoginModel lm) {
try{
	//System.out.println(current);
	String sql = "INSERT INTO lms_student_profile VALUES(?,?,?,?,?,?,?,?)";	
	Object[] params = new Object[] { lm.getFirstname(),lm.getLastname(),lm.getEmail(),lm.getDob(),lm.getGender(),lm.getDesignation(),lm.getPassword(),lm.getMobile()};
	int[] types = new int[] { Types.VARCHAR, Types.VARCHAR,Types.VARCHAR,Types.DATE,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.BIGINT};
	i=jdbcTemplate.update(sql, params, types);
	}
	catch(Exception e){
		System.out.println(e);
	}
return i;
	}


public int Registertutor(Tutor_Model lm) {
	// TODO Auto-generated method stub
	try{
		String sql = "INSERT INTO lms_tutor_credentials VALUES(?,?,?,?)";	
		Object[] params = new Object[] { lm.getName(),lm.getEmail(),lm.getPassword(),lm.getMobile()};
		int[] types = new int[] { Types.VARCHAR, Types.VARCHAR,Types.VARCHAR,Types.BIGINT};
		i=jdbcTemplate.update(sql, params, types);	
		}
	catch(Exception e){
		System.out.println(e);
	}
	return i;
}	
}
