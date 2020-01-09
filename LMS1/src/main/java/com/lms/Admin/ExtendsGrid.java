package com.lms.Admin;

import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Component;

import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.*;

public class ExtendsGrid extends Div{

	private static final long serialVersionUID = 1L;
	AdminDao db;

	
	protected List tasks;

	
	
	public void courseEntry() throws Exception
	{
		 
		
	
		ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext(
					(ServletContext)getDesktop().getWebApp().getNativeContext());
		db=(AdminDao)ctx.getBean("DeptDao");
	CourseInsert ci=new CourseInsert();
		
		
		Textbox tname=(Textbox)this.getFellow("Dept_names");
		String depname=tname.getValue();
		Textbox cname=(Textbox)this.getFellow("Course_Name");
		String coname=cname.getValue();
		 Intbox cd=(Intbox)this.getFellow("Course_Duration");
		int uduration=cd.getValue();
		Intbox user=(Intbox)this.getFellow("Tutor_Id");
		int uid=user.getValue();
		
		
		ci.setDept_name(depname);
		ci.setCourse_Name(coname);
		ci.setCourse_Duration(uduration);
		ci.setTutor_Id(uid);
		db.UpdateCourse(ci);
		
}
	
	protected void render() {
		Combobox cb = (Combobox) this.getFellow("Dept_names");
		cb.getItems().clear();

		for (Iterator it = tasks.iterator(); it.hasNext();) {
			DeptNames t = (DeptNames) it.next();
			// Task t=(Task)it.next();
		
			Comboitem ct = new Comboitem();
			ct.setLabel(t.getDeptName());
		 ct.setValue(t.getDeptId());
			// ct.appendChild(new Combocell(t.getDeptName()));
		 System.out.println(t.getDeptId());
			cb.appendChild(ct);
			
		}
	}

	public void onCreate() throws Exception {
		// spring bean, taskDAO
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext((ServletContext) getDesktop().getWebApp().getNativeContext());
		db = (AdminDao) ctx.getBean("DeptDao");

		tasks = db.Dept_Names();
		render();
	}


	
}