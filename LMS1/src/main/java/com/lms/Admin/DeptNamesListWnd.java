
package com.lms.Admin;

import java.util.*;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Div;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class DeptNamesListWnd extends Div {

	private static final long serialVersionUID = 1L;

	protected AdminDao DeptDao;
	protected List tasks;


	public void courseEntry() throws Exception {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext((ServletContext) getDesktop().getWebApp().getNativeContext());
		DeptDao = (AdminDao) ctx.getBean("DeptDao");

		CourseInsert ci = new CourseInsert();
		Combobox tname = (Combobox) this.getFellow("Dept_names");
		int depid = (Integer)tname.getSelectedItem().getValue();
		//System.out.println(depid);
		Textbox cname = (Textbox) this.getFellow("Course_Name");
		String coname = cname.getValue();
		//System.out.println(coname);
		Intbox cd = (Intbox) this.getFellow("Course_Duration");
		int uduration = cd.getValue();
		Intbox user = (Intbox) this.getFellow("Tutor_Id");
		int uid = user.getValue();

		ci.setDept_id(depid);
		ci.setCourse_Name(coname);
		ci.setCourse_Duration(uduration);
		ci.setTutor_Id(uid);
		String res;
		res=DeptDao.UpdateCourse(ci);
		if(res.equals("success"))
		{
			Messagebox.show("details entered successfully");
			Combobox departmentname = (Combobox) this.getFellow("Dept_names");
			int departid = (Integer)tname.getSelectedItem().getValue();
			//System.out.println(depid);
			departmentname.setSelectedIndex(0);
		
			Textbox coursename = (Textbox) this.getFellow("Course_Name");
			coursename.setValue(null);
			
			Intbox cousreduration = (Intbox) this.getFellow("Course_Duration");
			cousreduration.setValue(null);
			Intbox tutorId = (Intbox) this.getFellow("Tutor_Id");
		tutorId.setValue(null);
		}
		else if(res.equals("fail"))
		{
			Messagebox.show("Error while submitting  "+res+" !");
		}
		
		// System.out.println(ci.getTutor_Id());
	}
	
	
	
	

	protected void render() {
		Combobox cb = (Combobox) this.getFellow("Dept_names");
		cb.getItems().clear();
		Comboitem ct=new Comboitem();
		ct.setLabel("--select DeptName--");
		ct.setValue("--select DeptName--");
		cb.appendChild(ct);
		for (Iterator it = tasks.iterator(); it.hasNext();) {
			DeptNames t = (DeptNames) it.next();
			// Task t=(Task)it.next();

			ct=new Comboitem();
			ct.setLabel(t.getDeptName());
			ct.setValue(t.getDeptId());
			// ct.appendChild(new Combocell(t.getDeptName()));
			//System.out.println(t.getDeptId());
			cb.appendChild(ct);

		}
	}

	public void onCreate() throws Exception {
		// spring bean, taskDAO
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext((ServletContext) getDesktop().getWebApp().getNativeContext());
		DeptDao = (AdminDao) ctx.getBean("DeptDao");

		tasks = DeptDao.Dept_Names();
		render();
	}
}