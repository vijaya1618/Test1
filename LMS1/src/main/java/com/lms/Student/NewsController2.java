package com.lms.Student;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

public class NewsController2 extends Window{
	Dao db;
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	public void onCreate()
	{
		List<Coursemodel> result;
		
		Listbox lb=(Listbox)this.getFellow("course");
		
		lb.getItems().clear();
		
		LocalDate da=java.time.LocalDate.now();  
		
		@SuppressWarnings("deprecation")
		ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext(
					(ServletContext)getDesktop().getWebApp().getNativeContext());
		db=(Dao)ctx.getBean("taskDAO2");
		
		System.out.println(da);
		
		ListModelList<Coursemodel> lst=new ListModelList<Coursemodel>(db.findnews(da));
		if(lst==null){
			Messagebox.show("No courses");
		}
		else
		{
			lb.setModel(lst);
		}	
	}
	 public void Courselogin(String id) {
         Window window = (Window)Executions.createComponents("EnrollCourse.zul", null, null);
         window.setBorder(true);
         window.setClosable(true);
         window.setTitle("Title");
         window.doModal();
     } 
}
