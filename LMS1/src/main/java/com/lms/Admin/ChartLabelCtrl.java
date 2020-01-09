package com.lms.Admin;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Div;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
public class ChartLabelCtrl extends Div {
	protected AdminDao taskDAO;
public void onCreate() throws Exception {
        
        
        
        @SuppressWarnings("deprecation")
		
		/***
		 * Here we are Retrieving the database bean properties
		 */
		ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext(
					(ServletContext)getDesktop().getWebApp().getNativeContext());
    	taskDAO = (AdminDao)ctx.getBean("taskDAO");
		
		/***
		 * connecting zul page to Controller for taking inputs from the user
		 */
		Dmodel tc=new Dmodel();
	
		Label l1=(Label)this.getFellow("l1");
		
		
		Label l2=(Label)this.getFellow("l2");
		
		
		Label l3=(Label)this.getFellow("l3");
		
		
		Label l4=(Label)this.getFellow("l4");					
	   
	    
	    Label l5=(Label)this.getFellow("l5");		
	   
		
		Dmodel m=taskDAO.procedure();
		l1.setValue(String.valueOf(m.getCourse_id()));
		l2.setValue(String.valueOf((m.getExamid())));
		l3.setValue(String.valueOf(m.getStudent_id()));
		   l4.setValue(String.valueOf(m.getFeedback_id()));
		    l5.setValue(String.valueOf(m.getTutor_id()));
}
}
