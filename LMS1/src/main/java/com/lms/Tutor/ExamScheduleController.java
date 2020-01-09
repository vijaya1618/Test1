package com.lms.Tutor;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class ExamScheduleController extends Window {
	
	
	protected TutorDAO DeptDao;
	protected List tasks;

	
	
	public void onCreate()
	{
		
		Session s=Sessions.getCurrent();
		int i=(Integer)s.getAttribute("tutorid");
		String tutor=(String)s.getAttribute("username");
		Intbox id = (Intbox) this.getFellow("Tutorid");
		Textbox tname = (Textbox) this.getFellow("TutorName");
	 id.setValue(i);
	id.setReadonly(true);
		tname.setValue(tutor);
		tname.setReadonly(true);
	}
	public void ExamEntry() throws Exception {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext((ServletContext) getDesktop().getWebApp().getNativeContext());
		DeptDao = (TutorDAO ) ctx.getBean("tutDAO");
		
		ExamSchedule es=new ExamSchedule();
		Intbox id = (Intbox) this.getFellow("Tutorid");
		int tid = id.getValue();
		Textbox tname = (Textbox) this.getFellow("TutorName");
		String tuname = tname.getValue();
		//System.out.println(coname);
		Textbox ename = (Textbox) this.getFellow("ExamName");
		String exname = ename.getValue();
		Textbox sname = (Textbox) this.getFellow("SubName");
		String suname = sname.getValue();
		Intbox marks = (Intbox) this.getFellow("Marks");
		int tmarks = marks.getValue();

		es.setTutorid(tid);
		es.setTutorName(tuname);
		es.setExamName(exname);
		es.setSubName(suname);
		es.setMarks(tmarks);
		System.out.println(es.getTutorid());
		String res;
		res=DeptDao.UpdateExam(es);
		if(res.equals("success"))
		{
			Messagebox.show("details entered successfully");
			Textbox examname = (Textbox) this.getFellow("ExamName");
			examname.setValue(null);
			Textbox subname = (Textbox) this.getFellow("SubName");
			subname.setValue(null);
			Intbox smarks = (Intbox) this.getFellow("Marks");
			smarks.setValue(null);
		}
		
		
		else if(res.equals("fail"))
		{
			Messagebox.show("Error while submitting  "+res+" !");
		}
				
	}

}
