package com.lms.Student;

import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;



public class DaoImpl implements Dao{
	protected JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		
	}
	
	public List<Model> find(int id){
		
		String sql="select *from lms_registered_courses where student_id=?";
		
		List<Model> lm= (List<Model>)jdbcTemplate.query(sql,new Object[]{id},new usermapper());
		
		return lm;
	}

	public Updatemodel updateprofile(int id) {
		// TODO Auto-generated method stub
		
		String sql="select *from lms_student_profile where student_id=?";
		
	return (Updatemodel) jdbcTemplate.queryForObject(sql,new Object[]{id},new usermapper1());
	}

	public int Updateinsert(int id,Updatemodel um) {
		
		String sql="update lms_student_profile set student_firstname=?,student_lastname=?,student_email=?,student_designation=?"
				+ ", student_mobilenumber=? where student_id=?";
		// TODO Auto-generated method stub
		int i=jdbcTemplate.update(sql,new Object[]{um.getFirstname(),um.getLastname(),um.getEmail(),um.getDesignation(),um.getNumber(),id});
		return i;
	}

	public int Updatepassword(int id,String password) {
		// TODO Auto-generated method stub
		String sql="update lms_student_profile set student_password=? where student_id=?";
		int i=jdbcTemplate.update(sql,new Object[]{password,id});
		return i;
	}

	public List<Coursemodel> findall() {
		// TODO Auto-generated method stub
		String sql="select *from lms_course";
		
		List<Coursemodel> lm=(List<Coursemodel>)jdbcTemplate.query(sql,new CourseRowMapper());
		
		return lm;
	}

	public Coursemodel findcourse(int id) {

		String sql="select *from lms_course where course_id=?";
		 
		Coursemodel cm=(Coursemodel)jdbcTemplate.queryForObject(sql,new Object[]{id},new CourseRowMapper());
		
		return cm;
	}

	public List<Tutormodel> findTutor() {
		// TODO Auto-generated method stub
		String id="dbms";
		String sql="select course_tutor_id from lms_course where course_name=?";
		
		List<Tutormodel> lm = (List<Tutormodel>)jdbcTemplate.query(sql,new Object[]{id}, new TutorRowmapper());
		
		return lm;
	}

	public int enroll(Coursemodel cm) {
		// TODO Auto-generated method stub
		Session s=Sessions.getCurrent();
		int id=(Integer)s.getAttribute("studentid");
		System.out.println(id);
	String date=null;
		
		String sql="insert into lms_registered_courses(course_id,course_name,student_id,tutor_id) values(?,?,?,?)";
		int i=jdbcTemplate.update(sql, new Object[]{cm.getCourseid(),cm.getCoursename(),id,cm.getTutorid()},new int[]{Types.INTEGER,Types.VARCHAR,Types.INTEGER,Types.INTEGER});
		return i;
	}
	public List<Coursemodel> findnews(LocalDate d) {
		// TODO Auto-generated method stub
		List<Coursemodel> lm=null;
String sql="select *from lms_course where course_date='"+d+"'";
		try{
		lm=(List<Coursemodel>)jdbcTemplate.query(sql,new CourseRowMapper());
		}
		catch(Exception e)
		{
			return null;
		}
		return lm;
	}
	
	protected class ImageMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			System.out.println("Query Executed");
			ImageBean ib = new ImageBean();
			ib.setImage(rs.getBytes("course_document"));
			ib.setFilename(rs.getString("course_name"));
			return ib;
		}
		}
	
	public ImageBean retrieve1(String s) throws Exception {
		System.out.println("In DAO CONNECTION ESTABLISED");
		StringBuilder sql2 = new StringBuilder();
				sql2.append(" select * from materials where course_name='"+s);
				sql2.append(".pdf'");
		
		List<ImageBean> ib= jdbcTemplate.query(sql2.toString(), new ImageMapper());
		System.out.println("Image Returned");
		return ib.get(0);

	  }

	public void file(ImageBean ib) throws FileNotFoundException {			
		String sql2="insert into fileupload values(?,?)";
		Object[] params = new Object[] {ib.getImage(),ib.getFilename()};
		int types[] = new int[] {Types.LONGVARBINARY,Types.VARCHAR}; 
		jdbcTemplate.update(sql2, params, types);
		
	}	
	

    public List<QuestionModel> getQuestions(String name) {
    	StringBuilder sql2 = new StringBuilder();
    	sql2.append("select * from lms_exam_");
	sql2.append(name);
	
	
	List<QuestionModel> quesList = (List<QuestionModel>)jdbcTemplate.query(sql2.toString(),new QuestionMapper());
	return quesList;
}


     protected class  QuestionMapper implements RowMapper
     {
	  public Object mapRow(ResultSet rs,int rowNum) throws SQLException{
		QuestionModel qm=new QuestionModel();
		qm.setQuestion_id(rs.getInt("quest_is"));
		qm.setQuestion(rs.getString("question"));
		qm.setOption_A(rs.getString("option_a"));
		qm.setOption_B(rs.getString("option_b"));
		qm.setOption_C(rs.getString("option_c"));
		qm.setOption_D(rs.getString("option_d"));
		qm.setCorrect_ans(rs.getString("correct_ans"));
		qm.setQuestion_type(rs.getString("question_type"));
		
		return qm;
	}
}


	public String getAnswerById(String id) {
		// TODO Auto-generated method stub
		Session examname=Sessions.getCurrent();
		String subName=(String)examname.getAttribute("selectedExam");
		StringBuilder sql2 = new StringBuilder();
		
		
		sql2.append("select correct_ans from lms_exam_");
		sql2.append(subName);
				sql2.append(" where quest_is=?");
		
		String ans=jdbcTemplate.queryForObject(sql2.toString(),new Object[] {id},String.class);
		return ans;
	}

	public void insert(Marksmodel m) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO lms_Totalmarks VALUES(?,?,?)";
		Object[] params = new Object[] { m. getId(),m.getCoursename(),m.getMarks()};
		int[] types = new int[] { Types.INTEGER,Types.VARCHAR, Types.INTEGER};
		jdbcTemplate.update(sql, params, types);
		
	}
	
	public int feedbacksubmit(feedbackmodel fm) {
		// TODO Auto-generated method stub
	System.out.println(fm.getId());
	System.out.println(fm.getSub());
	System.out.println(fm.getDesc());
		String sql="insert into lms_user_feedback values(?,?,?)";
		int i=jdbcTemplate.update(sql,new Object[]{fm.getId(),fm.getSub(),fm.getDesc()});
		return i;
	}

	@Override
	public List<Marksmodel> displayMarks() {
		Session s=Sessions.getCurrent();
		int id=(Integer)s.getAttribute("studentid");
		
		
		String sql="select examname,marks from lms_Totalmarks where studentid=?";
		
		List<Marksmodel> lm = (List<Marksmodel>)jdbcTemplate.query(sql,new Object[]{id}, new MarksRowmapper());
		
		return lm;
	}
	
	public class MarksRowmapper implements RowMapper {

		public Marksmodel mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			Marksmodel tm=new Marksmodel();
			
			
		tm.setCoursename(rs.getString("examname"));
		tm.setMarks(rs.getInt("marks"));	
			return tm;
		}
	}

	@Override
	public DashboardModel countDashboard() {
		
		DashboardModel d=new DashboardModel();
		Session s=Sessions.getCurrent();
		int id=(Integer)s.getAttribute("studentid");
		String qry="select count(*) from lms_registered_courses where student_id=?";
		String ans=jdbcTemplate.queryForObject(qry,new Object[] {id},String.class);
		d.setCourseCount(Integer.parseInt(ans));
		System.out.print(ans);
		
	
		
		String qry2="select count(*) from lms_Totalmarks where studentid=?";
		String ans2=jdbcTemplate.queryForObject(qry2,new Object[] {id},String.class);
		d.setExamsCount(Integer.parseInt(ans2));
		
		
		
		String qry1="select count(*) from materials";
		String ans1=jdbcTemplate.queryForObject(qry1,new Object[] {},String.class);
		d.setMaterialCount(Integer.parseInt(ans1));
		System.out.print(ans1);
		
		return d;
	}
	
					
	
	
	
}
