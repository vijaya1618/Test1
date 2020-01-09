package com.lms.Admin;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class ExamScheduleController extends Window {
	
	
	protected AdminDao DeptDao;
	protected List tasks;
	
	public void ExamEntry() throws Exception {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext((ServletContext) getDesktop().getWebApp().getNativeContext());
		DeptDao = (AdminDao) ctx.getBean("DeptDao");
		
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
		DeptDao.UpdateExam(es);
	}

}
