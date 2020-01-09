package com.lms.Admin;

import java.util.*;
import javax.servlet.ServletContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.Composer;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Textbox;

public class TaskListWnd extends Window implements Composer<Component>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected AdminDao taskDAO;
	protected List tasks;

	
	protected void render() {
		Listbox lb = (Listbox)this.getFellow("taskslb");
		lb.getItems().clear();
		
		for (Iterator it = tasks.iterator(); it.hasNext(); ) {
			//Task t = (Task) it.next();
			TutorExam t=(TutorExam) it.next();
			Listitem lt = new Listitem();
			lt.setValue(t);
			
			//lt.appendChild(new Listcell(t.getTitle()));
			//lt.appendChild(new Listcell(t.getDescription()));
		
			
			lt.appendChild(new Listcell(t.getTutorid()));
			lt.appendChild(new Listcell(t.getTutorname()));
			lt.appendChild(new Listcell(t.getExamname()));
			lt.appendChild(new Listcell(t.getSubjectname()));
			lt.appendChild(new Listcell(String.valueOf(t.getMarks())));
			
			lb.appendChild(lt);

		}
	}
	
	public void onCreate() throws Exception {
		//	spring bean, taskDAO
		ApplicationContext ctx = 
			WebApplicationContextUtils.getRequiredWebApplicationContext(
				(ServletContext)getDesktop().getWebApp().getNativeContext());
		taskDAO = (AdminDao)ctx.getBean("DeptDao");
		
		tasks = taskDAO.findAll();

			render();
	}
	

	
	public void onNew() throws Exception {
		Window win = (Window)Executions.createComponents("task.zul", null, null);
		win.doModal();
		if (win.getAttribute("OK") != null) {	
			tasks = taskDAO.findAll();
			render();
		}
	}
	
	public void onUpdate() throws Exception {
		Listbox lb = (Listbox)this.getFellow("taskslb");
		Listitem lt = lb.getSelectedItem();
		if (lt == null)
			return;
		Task t = (Task)lt.getValue();

		Map<String, Task> params = new HashMap<String, Task>();
		params.put("task", t);
		Window win = (Window)Executions.createComponents("task.zul", null, params);
		win.doModal();
		if (win.getAttribute("OK") != null) {	
			tasks = taskDAO.findAll();
			render();
		}
	}
	
	public void onDelete() throws Exception {
		Listbox lb = (Listbox)this.getFellow("taskslb");
		Listitem lt = lb.getSelectedItem();
		if (lt == null)
			return;
		ExamModel t = (ExamModel)lt.getValue();
		taskDAO.delete(t);
		lt.detach();
	}
	public void addExam() throws Exception
	{
		 
		@SuppressWarnings("deprecation")
		
		ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext(
					(ServletContext)getDesktop().getWebApp().getNativeContext());
		taskDAO = (AdminDao)ctx.getBean("DeptDao");
		
		
		//LoginModel task=new LoginModel();
		
		ExamModel em=new ExamModel();
		
		Textbox user=(Textbox)this.getFellow("examname");
		String examname=user.getValue();
		Textbox coarsename=(Textbox)this.getFellow("coarsename");
		String cname=coarsename.getValue();
		
		   
	       // java.util.Date utilDate = ((Datebox)this.getFellow("dob")).getValue();
			Datebox dateofexam=(Datebox)this.getFellow("examdate");
			java.util.Date examdate=(java.util.Date) dateofexam.getValue();
			SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
			final String stringDate= dateFormat.format(examdate);

			Date sqlDate=Date.valueOf(stringDate);
		
		Intbox mark=(Intbox)this.getFellow("marks");
		int marks=mark.getValue();

		
		em.setExamname(examname);
			em.setSubjectname(cname);
		em.setExamdate(sqlDate);
			em.setMarks(marks);
					String res;
			res=taskDAO.ExamInsert(em);	
			System.out.println(res);
			if(res.equals("success"))
			{
				Messagebox.show("details entered successfully");
			}
			else if(res.equals("fail"))
			{
				Messagebox.show("Error while submitting  "+res+" !");
			}
			
	}
    public void onButton()
    {
		Textbox user2=(Textbox)this.getFellow("examname");
		user2.setValue(null);
		Textbox coarsename2=(Textbox)this.getFellow("coarsename");
		coarsename2.setValue(null);
		Datebox dateofexam2=(Datebox)this.getFellow("examdate");
		dateofexam2.setValue(null);
		Intbox mark2=(Intbox)this.getFellow("marks");
		mark2.setValue(null);
		
    }

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}