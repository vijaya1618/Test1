package com.lms.Student;

import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class UpdateController extends Window{
	
	/**
	 * 
	 */
	Dao db;
	private static final long serialVersionUID = 1L;

	public void update() throws Exception{
	
		Textbox user=(Textbox)this.getFellow("firstname");
		String first=user.getValue();
		
		Textbox last=(Textbox)this.getFellow("lastname");
		String lastname=last.getValue();
		
		Textbox email=(Textbox)this.getFellow("email");
		String emailid=email.getValue();
		
		Longbox lb=(Longbox)this.getFellow("mobile");
		Long number=lb.getValue();
		
		Textbox desing=(Textbox)this.getFellow("designation");
		String designation=desing.getValue();
		Session s=Sessions.getCurrent();
		int id=(Integer)s.getAttribute("studentid");
	
		Updatemodel um=new Updatemodel();
		um.setFirstname(first);
		um.setLastname(lastname);
		um.setEmail(emailid);
		um.setNumber(number);
		um.setDesignation(designation);
		
		int i=db.Updateinsert(id,um);
		if(i==1)
		{
			Messagebox.show("successfully updated");
		}
		else
		{
			Messagebox.show("There was an error");
		}
		
	}
	
	public void onCreate()
	{
		
		Textbox user=(Textbox)this.getFellow("firstname");
		
		
		Textbox last=(Textbox)this.getFellow("lastname");
	
		
		Textbox email=(Textbox)this.getFellow("email");
		
		
		Longbox lb=(Longbox)this.getFellow("mobile");
	
		
		Textbox desing=(Textbox)this.getFellow("designation");
	
		
		@SuppressWarnings("deprecation")
		ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext(
					(ServletContext)getDesktop().getWebApp().getNativeContext());
		db=(Dao)ctx.getBean("taskDAO2");
		int id=2071;
		Updatemodel result=db.updateprofile(id);	
		user.setValue(result.getFirstname());
		last.setValue(result.getLastname());
		email.setValue(result.getEmail());
		desing.setValue(result.getDesignation());
		lb.setValue(result.getNumber());

	}
	}
