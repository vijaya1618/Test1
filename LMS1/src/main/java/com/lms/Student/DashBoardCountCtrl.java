package com.lms.Student;

import javax.servlet.ServletContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Label;
import org.zkoss.zul.Window;

import com.lms.Admin.AdminDao;

public class DashBoardCountCtrl extends Window{
	protected 	Dao db;
	
	public void onCreate(){
		ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext(
					(ServletContext)getDesktop().getWebApp().getNativeContext());
			db=(Dao)ctx.getBean("taskDAO2");
		DashboardModel d1=new DashboardModel();

			Label l1=(Label)this.getFellow("t1");
			
			
			Label l2=(Label)this.getFellow("t2");
			
			
			Label l3=(Label)this.getFellow("t3");
			DashboardModel d=db.countDashboard();
			l1.setValue(String.valueOf(d.getCourseCount()));
			l2.setValue(String.valueOf((d.getMaterialCount())));
			
			l3.setValue(String.valueOf(d.getExamsCount()));
			
			
	}
	
}
