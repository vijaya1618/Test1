package com.lms.Student;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

public class Coursecontroller extends Window{

	/**
	 * 
	 */
	
	Dao db;
	private static final long serialVersionUID = 1L;
	
	public void onCreate()
	{
		List<Coursemodel> result;
		
		Listbox lb=(Listbox)this.getFellow("course");
		
		lb.getItems().clear();
		
		ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext(
					(ServletContext)getDesktop().getWebApp().getNativeContext());
		db=(Dao)ctx.getBean("taskDAO2");
		
		ListModelList<Coursemodel> lst=new ListModelList<Coursemodel>(db.findall());
		
		lb.setModel(lst);
		
	}

	 public void Courselogin(String id) {
		 Session s=Sessions.getCurrent();
		 s.setAttribute("courseid", id);
           Window window = (Window)Executions.createComponents("EnrollCourse.zul", null, null);
           window.setBorder(true);
           window.setClosable(true);
           window.setTitle("Title");
           window.doModal();
       } 
	
}
