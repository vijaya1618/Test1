package com.lms.example1;


import javax.servlet.ServletContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;


public class Student_Logincontroller  extends Window{
	
	private static final long serialVersionUID = 1L;
	Student_LoginDAO db;
	private Component loginform;
	public void LoginUser() throws Exception
	{
		 
		@SuppressWarnings("deprecation")
		
		/***
		 * Here we are Retrieving the database bean properties
		 */
		ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext(
					(ServletContext)getDesktop().getWebApp().getNativeContext());
		db=(Student_LoginDAO)ctx.getBean("taskDAO3");
		
		/***
		 * connecting zul page to Controller for taking inputs from the user
		 */
		Student_LoginModel task=new Student_LoginModel();
		Textbox user=(Textbox)this.getFellow("firstname");
		String username=user.getValue();
		Textbox last=(Textbox)this.getFellow("lastname");
		String lastname=last.getValue();
		Textbox email=(Textbox)this.getFellow("email");
		String emailid=email.getValue();
		Datebox dob=(Datebox)this.getFellow("dob");
		java.util.Date dateofbirth=(java.util.Date) dob.getValue();
		
		Radiogroup rd=(Radiogroup)this.getFellow("gender");	
		String gender=rd.getSelectedItem().getLabel();
		
		Longbox num=(Longbox)this.getFellow("mobile");
		Long number=num.getValue();
		Textbox desing=(Textbox)this.getFellow("designation");
		String designation=desing.getValue();
		Textbox pass=(Textbox)this.getFellow("password");
		String password=pass.getValue();
		Textbox cpass=(Textbox)this.getFellow("cpassword");
		String cpassword=cpass.getValue();  
		
		
		/***
		 * assign user entered details to the model class
		 */		
		if(password.equals(cpassword))
		{
			task.setFirstname(username);
			task.setLastname(lastname);
			task.setEmail(emailid);
			task.setDob(dateofbirth);
			task.setGender(gender);
			task.setMobile(number);
			task.setDesignation(designation);
			task.setPassword(password);
		}
		
		else
		{
			showNotify("password and confirm password not equal","error",loginform);
		}
		/***
		 * calling implementation by passing Model class as input
		 */
		
		int j=db.LoginInsert(task);	
		if(j==1)
		{
			Messagebox.show("successfully Registered");
		}
		else
		{
			Messagebox.show("There was a probelm try again");
		}
		
	}
	private void showNotify(String msg, String type, Component ref) {
		// TODO Auto-generated method stub
		Clients.showNotification(msg, type, ref, "end_center", 2000);
	}
	public void RegisterTutor() throws Exception
	{
		 
		@SuppressWarnings("deprecation")
		
		/***
		 * Here we are Retrieving the database bean properties
		 */
		ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext(
					(ServletContext)getDesktop().getWebApp().getNativeContext());
		db=(Student_LoginDAO)ctx.getBean("taskDAO3");
		
		/***
		 * connecting zul page to Controller for taking inputs from the user
		 */
		Tutor_Model task=new Tutor_Model();
		Textbox user=(Textbox)this.getFellow("firstname");
		String username=user.getValue();
		
		Textbox email=(Textbox)this.getFellow("email");
		String emailid=email.getValue();
		
				Longbox num=(Longbox)this.getFellow("mobile");
		Long number=num.getValue();
		
		Textbox pass=(Textbox)this.getFellow("password");
		String password=pass.getValue();
		
		Textbox cpass=(Textbox)this.getFellow("cpassword");
		String cpassword=cpass.getValue();
		/***
		 * assign user entered details to the model class
		 */			
		if(password.equals(cpassword))
		{
			task.setName(username);
			
			task.setEmail(emailid);
		
			task.setMobile(number);
		
			task.setPassword(password);
		}
		else
		{
			showNotify1("password and confirm password not equal","error",loginform);
		}
		/***
		 * calling implementation by passing Model class as input
		 */
		
		int j=db.Registertutor(task);
		if(j==1)
		{
			Messagebox.show("successfully Registered");
		}
		else
		{
			Messagebox.show("There was a probelm try again");
		}
	}
	private void showNotify1(String msg, String type, Component ref) {
		// TODO Auto-generated method stub
		Clients.showNotification(msg, type, ref, "end_center", 2000);
	}
	
}
