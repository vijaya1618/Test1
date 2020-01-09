package com.lms.Login;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window; 
public class ContactController extends Window{
	private static final long serialVersionUID = 1L;
	Login_DAO db;
		public void ContactUser() throws Exception
		{
			 
			@SuppressWarnings("deprecation")
		
			ApplicationContext ctx = 
					WebApplicationContextUtils.getRequiredWebApplicationContext(
						(ServletContext)getDesktop().getWebApp().getNativeContext());
			db=(Login_DAO)ctx.getBean("taskDAO1");
		contact task=new contact();
			
			Textbox user=(Textbox)this.getFellow("name");
			String  uname=user.getValue();
			Textbox last=(Textbox)this.getFellow("e");
			String uemail=last.getValue();
			Textbox sub=(Textbox)this.getFellow("sub");
			String usubject=sub.getValue();
			Textbox msg=(Textbox)this.getFellow("mess");
			String umessage=msg.getValue();
			
			task.setName(uname);
			task.setEmail(uemail);
			task.setSubject(usubject);
			task.setMessage(umessage);
			db.contactInsert(task);
			Messagebox.show("details entered successfully");
		}	
}