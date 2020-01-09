package com.lms.Tutor;
import java.util.*;
import javax.servlet.ServletContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.Composer;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;


import java.sql.Date;
public class TutorCourseCheckCtrl extends Window implements Composer<Component> {

	
	private static final long serialVersionUID = 1L;
	protected List courses;
	protected TutorDAO tutorDAO;
	CourseInfo c=new CourseInfo();
	
	protected void render() {
		Listbox lb = (Listbox)this.getFellow("courseDetails");
		lb.getItems().clear();
		
		for (Iterator it = courses.iterator(); it.hasNext(); ) {
			//Task t = (Task) it.next();
		 c=(CourseInfo) it.next();
			Listitem lt = new Listitem();
			lt.setValue(c);
			
			//lt.appendChild(new Listcell(t.getTitle()));
			//lt.appendChild(new Listcell(t.getDescription()));
		
			
			
			lt.appendChild(new Listcell(String.valueOf(c.getCourse_id())));
			lt.appendChild(new Listcell(String.valueOf(c.getDepartment_id())));
			lt.appendChild(new Listcell(c.getCourse_name()));
			lt.appendChild(new Listcell(String.valueOf(c.getCourse_duration())));
			lt.appendChild(new Listcell(String.valueOf(c.getTutor_id())));
			lb.appendChild(lt);
		}
	}
	public void onCreate() throws Exception {
		//	spring bean, taskDAO
		
		System.out.println("entering");
		ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext(
					(ServletContext)getDesktop().getWebApp().getNativeContext());
				tutorDAO = (TutorDAO)ctx.getBean("tutDAO");
					courses=tutorDAO.findCoursesInfo();
			render();
	}
	

	
	@Override
	public void doAfterCompose(Component arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
}
