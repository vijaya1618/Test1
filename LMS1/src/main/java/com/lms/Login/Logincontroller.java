package com.lms.Login;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.lang.Expectable;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Button;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class Logincontroller extends Window implements Expectable {

	private static final long serialVersionUID = 1L;
	Login_DAO db;

     Session sess=null;
	public void Validate() throws Exception {
		@SuppressWarnings("deprecation")
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext((ServletContext) getDesktop().getWebApp().getNativeContext());
		db = (Login_DAO) ctx.getBean("taskDAO1");

		Textbox user = (Textbox) this.getFellow("name");
		String username = user.getValue();

		Textbox pwd = (Textbox) this.getFellow("pwd");
		String password = pwd.getValue();

		Login_model s = new Login_model();
		
		
		
		s.setUsername(username);
		s.setPassword(password);
		sess=Sessions.getCurrent();
		sess.setAttribute("loginmodel",s);
		
		Button b = (Button) this.getFellow("submitbutton");
		String id = b.getLabel();
		s.setRole(id);
		//System.out.println(id);
		if (id.equals("Student")) {
			Login_model k = db.LoginStudent(s);
			if (k.getStatus() == 1) {
				Executions.sendRedirect("/Student/Mainpage.zul");
				sess=Sessions.getCurrent();
				sess.setAttribute("username",s.getUsername());
				sess.setAttribute("studentid",k.getStudentid());
				
				String a=(String) sess.getAttribute("username");
				System.out.println(a);
			} else if (k.getStatus() == 2) {
				Messagebox.show("username and password mismatched");

			} else {
				Messagebox.show("please try again");
			}
		} else if (id.equals("Admin")) {
		Login_model k = db.LoginAdmin(s);
		if (k.getStatus() == 1) {
				Executions.sendRedirect("/Admin/index.zul");
				sess=Sessions.getCurrent();
				sess.setAttribute("username",s.getUsername());
				sess.setAttribute("adminid",k.getAdminid());
				String a=(String) sess.getAttribute("username");
				System.out.println(a);
			} else if (k.getStatus() == 2) {
				// Messagebox.show("password mismatch");

			} else {
				Messagebox.show("username and password mismatched");
			}
		} else {
			Login_model k = db.LoginTutor(s);
			if (k.getStatus() == 1) {
				Executions.sendRedirect("/Tutor/TutorIndex.zul");
				sess=Sessions.getCurrent();
				sess.setAttribute("username",s.getUsername());
				sess.setAttribute("tutorid",k.getTutorid());
				String a=(String) sess.getAttribute("username");
				System.out.println(a);
			} else if (k.getStatus() == 2) {
				// Messagebox.show("password mismatch");

			} else {
				Messagebox.show("username and password mismatched");
			}
		}

	}

}
