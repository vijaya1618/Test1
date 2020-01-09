package com.lms.Tutor;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;



public class Updatecontroller extends Window {

	/**
	 * 
	 */
	TutorDAO db;
	private static final long serialVersionUID = 1L;

	public void update() throws Exception {
		Session ss = Sessions.getCurrent();
		int id = (Integer)ss.getAttribute("tutorid");
		

		Intbox i = (Intbox) this.getFellow("id");
		int tutorid = i.getValue();

		Textbox t1 = (Textbox) this.getFellow("firstname");
		String name = t1.getValue();

		Textbox t2 = (Textbox) this.getFellow("email");
		String email = t2.getValue();

		Longbox t3 = (Longbox) this.getFellow("mobile");
		Long phone = t3.getValue();

		Updatetutormodel um = new Updatetutormodel();
		um.setId(tutorid);
		um.setName(name);
		um.setEmail(email);
		um.setNum(phone);

		int result = db.updatetutor(um);
		if (result == 1) {
			Messagebox.show("successfully updated");
		} else {
			Messagebox.show("There was an error");
		}
	}

	public void onCreate() {
		Session ss = Sessions.getCurrent();
		int id = (Integer)ss.getAttribute("tutorid");
		

		Intbox i = (Intbox) this.getFellow("id");

		Textbox t1 = (Textbox) this.getFellow("firstname");

		Textbox t2 = (Textbox) this.getFellow("email");

		Longbox t3 = (Longbox) this.getFellow("mobile");

		@SuppressWarnings("deprecation")
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext((ServletContext) getDesktop().getWebApp().getNativeContext());
		db = (TutorDAO) ctx.getBean("tutDAO");
		Updatetutormodel result = db.updateprofile(id);
		i.setValue(result.getId());
		t1.setValue(result.getName());
		t2.setValue(result.getEmail());
		t3.setValue(result.getNum());

	}
}