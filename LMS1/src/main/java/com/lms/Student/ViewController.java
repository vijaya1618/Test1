package com.lms.Student;

import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class ViewController extends Window{

	/**
	 * 
	 */
	Dao db;
	private static final long serialVersionUID = 1L;
	
	public void onCreate() throws Exception{
		
		Intbox t=(Intbox)this.getFellow("id");
		
		Textbox t1=(Textbox)this.getFellow("name");
		
		Intbox t2=(Intbox)this.getFellow("duration");
		
		Intbox t3=(Intbox)this.getFellow("deptid");
		
		@SuppressWarnings("deprecation")
		ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext(
					(ServletContext)getDesktop().getWebApp().getNativeContext());
		db=(Dao)ctx.getBean("taskDAO2");

		Session s=Sessions.getCurrent();
		String s2=(String)s.getAttribute("courseid");
		int c=(Integer.valueOf(s2));
		
		Coursemodel cm=(Coursemodel)db.findcourse(c);
		
		t.setValue(cm.getCourseid());
		t1.setValue(cm.getCoursename());
		t2.setValue(cm.getCourseduration());	
		t3.setValue(cm.getDepartmentid());
		
			Combobox lb = (Combobox)this.getFellow("select");
			lb.getItems().clear();
			
			List<Tutormodel> tm=db.findTutor();
			
			for (Iterator it = tm.iterator(); it.hasNext(); ) {
				Tutormodel m= (Tutormodel) it.next();
				
				Comboitem ct=new Comboitem();
				
				ct.setLabel(String.valueOf(m.getTutorid()));
				lb.appendChild(ct);
			}
		}
		public void enrollcourse() throws Exception{
			
		
			Intbox t=(Intbox)this.getFellow("id");
			int id=t.getValue();
			
			Textbox t1=(Textbox)this.getFellow("name");
			String name=t1.getValue();
			
			Intbox t2=(Intbox)this.getFellow("duration");
			int time=t2.getValue();
			
			Intbox t3=(Intbox)this.getFellow("deptid");
			int dept=t3.getValue();
			
			Combobox c=(Combobox)this.getFellow("select");
			int c1=Integer.parseInt(c.getSelectedItem().getLabel());
			
			Coursemodel cm=new Coursemodel();
			cm.setCourseid(id);
			cm.setCoursename(name);
			cm.setCourseduration(time);
			cm.setDepartmentid(dept);
			cm.setTutorid(c1);
			
			int res=db.enroll(cm);
		
			if(res==0)
			{
				Messagebox.show("Course was not Enrolled Successful");
			}
			else
			{
				
				Window w=(Window)this.getFellow("db");
				w.detach();
				Messagebox.show("Successfully Enrolled");
				
			}
			
		}
	}

