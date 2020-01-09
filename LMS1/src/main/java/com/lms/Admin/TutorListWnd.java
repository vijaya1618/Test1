package com.lms.Admin;

import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Window;


public class TutorListWnd extends Window{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected AdminDao DeptDao;
	protected List tasks; 
	
	public void onCreate() throws Exception {
		// spring bean, taskDAO
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext((ServletContext) getDesktop().getWebApp().getNativeContext());
		DeptDao = (AdminDao) ctx.getBean("DeptDao");

		tasks = DeptDao.TutorList();
		
		render();
	}
	
	protected void render() {
		Listbox lb = (Listbox) this.getFellow("TutorList");
		lb.getItems().clear();
		for (Iterator it = tasks.iterator(); it.hasNext();) {
			TutorDetails t = (TutorDetails) it.next();

			Listitem lt = new Listitem();
			lt.setValue(t);
			lt.appendChild(new Listcell(String.valueOf(t.getTutor_Id())));
			lt.appendChild(new Listcell(t.getTutor_Name()));

			lt.appendChild(new Listcell(String.valueOf(t.getTutor_Mobile())));
			lb.appendChild(lt);
		}
	}
	
	
}
