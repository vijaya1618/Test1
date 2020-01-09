package com.lms.Student;
import java.util.*;
import javax.servlet.ServletContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


import org.zkoss.zul.Window;

import com.lms.Admin.AdminDao;
import com.lms.Admin.ExamModel;

import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

public class DisplayMarksCtrl extends Window  {

	
	Dao db;
	Marksmodel m=new Marksmodel();
	protected List tasks;
	
	
	public void onCreate() throws Exception {
		
		Session s=Sessions.getCurrent();
		int id=(Integer)s.getAttribute("studentid");
		ApplicationContext ctx = 
			WebApplicationContextUtils.getRequiredWebApplicationContext(
				(ServletContext)getDesktop().getWebApp().getNativeContext());
		db=(Dao)ctx.getBean("taskDAO2");
		tasks=db.displayMarks();
			render();
	}
	

	
	protected void render() {
		Listbox lb = (Listbox)this.getFellow("marks");
		lb.getItems().clear();
		
		for (Iterator it = tasks.iterator(); it.hasNext(); ) {
			
		 m=(Marksmodel) it.next();
			Listitem lt = new Listitem();
			lt.setValue(m);
		
			
			lt.appendChild(new Listcell(m.getCoursename()));
			lt.appendChild(new Listcell(String.valueOf(m.getMarks())));
			
			lb.appendChild(lt);
		}
	}
	

	

	
	
}
