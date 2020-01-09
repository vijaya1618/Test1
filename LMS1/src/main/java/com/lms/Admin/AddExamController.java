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

import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Textbox;

public class AddExamController extends Window implements Composer<Component>{
	protected AdminDao taskDAO;
	@Wire
	private Window resultWin;
	private static final long serialVersionUID = 1L;
	protected List tasks;
	
	ExamModel em=new ExamModel();
	
	protected void render2() {
		Listbox lb = (Listbox)this.getFellow("addedExams");
		lb.getItems().clear();
		
		for (Iterator it = tasks.iterator(); it.hasNext(); ) {
			//Task t = (Task) it.next();
		 em=(ExamModel) it.next();
			Listitem lt = new Listitem();
			lt.setValue(em);
			
			//lt.appendChild(new Listcell(t.getTitle()));
			//lt.appendChild(new Listcell(t.getDescription()));
		
			
			
			lt.appendChild(new Listcell(String.valueOf(em.getExamId())));
			lt.appendChild(new Listcell(em.getExamname()));
			lt.appendChild(new Listcell(em.getSubjectname()));
			lt.appendChild(new Listcell(String.valueOf(em.getExamdate())));
			lt.appendChild(new Listcell(String.valueOf(em.getMarks())));
			lb.appendChild(lt);
		}
	}
	
	public void onCreate() throws Exception {
		//	spring bean, taskDAO
		ApplicationContext ctx = 
			WebApplicationContextUtils.getRequiredWebApplicationContext(
				(ServletContext)getDesktop().getWebApp().getNativeContext());
		taskDAO = (AdminDao)ctx.getBean("taskDAO");
			tasks=taskDAO.findExams();
			render2();
	}
	
	public void addExam() throws Exception
	{
		 
		@SuppressWarnings("deprecation")
		
		ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext(
					(ServletContext)getDesktop().getWebApp().getNativeContext());
		taskDAO = (AdminDao)ctx.getBean("taskDAO");
		
		
		//LoginModel task=new LoginModel();
		
		ExamModel em=new ExamModel();
		
		Textbox user=(Textbox)this.getFellow("examname");
		String examname=user.getValue();
		Textbox coarsename=(Textbox)this.getFellow("subjectname");
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
					System.out.println("start");
			res=taskDAO.ExamInsert(em);	
			System.out.println(res);
			System.out.println("new1");
			if(res.equals("success"))
			{
				Messagebox.show("details entered successfully");
				onCreate();
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
		Textbox coarsename2=(Textbox)this.getFellow("subjectname");
		coarsename2.setValue(null);
		Datebox dateofexam2=(Datebox)this.getFellow("examdate");
		dateofexam2.setValue(null);
		Intbox mark2=(Intbox)this.getFellow("marks");
		mark2.setValue(null);
		System.out.println("new2");		
		Button b=(Button)this.getFellow("update");
		b.setVisible(true);
		Button b2=(Button)this.getFellow("editSave");
		b2.setVisible(false);
    }
 public void onDelete() throws Exception {
		Listbox lb = (Listbox)this.getFellow("addedExams");
		Listitem lt = lb.getSelectedItem();
		if (lt == null)
			return;
		ExamModel em = (ExamModel)lt.getValue();
		taskDAO.delete(em);
		lt.detach();
	}
 
	public void onUpdateEdit() throws Exception {
		Listbox lb = (Listbox)this.getFellow("addedExams");
		Listitem lt = lb.getSelectedItem();
		if (lt == null)
			return;
		em = (ExamModel)lt.getValue();
					
		Textbox user2=(Textbox)this.getFellow("examname");
		user2.setValue(em.getExamname());
		Textbox coarsename2=(Textbox)this.getFellow("subjectname");
		coarsename2.setValue(em.getSubjectname());
		Datebox dateofexam2=(Datebox)this.getFellow("examdate");
		dateofexam2.setValue(em.getExamdate());
		Intbox mark2=(Intbox)this.getFellow("marks");
		mark2.setValue(em.getMarks());
		Button b=(Button)this.getFellow("update");
	b.setVisible(false);
	Button b2=(Button)this.getFellow("editSave");
	b2.setVisible(true);	
	}

			public void onUpdate() throws Exception {

				Textbox user=(Textbox)this.getFellow("examname");
				em.setExamname(user.getValue());
				Textbox coarsename=(Textbox)this.getFellow("subjectname");
				em.setSubjectname(coarsename.getValue());
				
				Datebox dateofexam=(Datebox)this.getFellow("examdate");
				java.util.Date examdate=(java.util.Date) dateofexam.getValue();
				SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
				final String stringDate= dateFormat.format(examdate);

				em.setExamdate(Date.valueOf(stringDate));
				Intbox mark=(Intbox)this.getFellow("marks");
				em.setMarks(mark.getValue());
				System.out.println("start");
				
				String res;	
				res=taskDAO.update(em);	
				onCreate();
				if(res.equals("success")){
					Messagebox.show("details Updated successfully");
					
				}
				else if(res.equals("fail"))
				{
					Messagebox.show("Error while submitting  "+res+" !");
				}
				Button b=(Button)this.getFellow("update");
				b.setVisible(true);
				Button b2=(Button)this.getFellow("editSave");
				b2.setVisible(false);	
				System.out.println("stop");
			}
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
