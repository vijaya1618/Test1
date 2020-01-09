package com.lms.Student;

import java.util.List;

import javax.servlet.ServletContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.Composer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Window;


public class QuestionController extends Window implements Composer<Component>
{

	private static final long serialVersionUID = 1L;
	
	protected Dao examdao;
	
	@Wire
	private Window Win;
	
	private ListModel<QuestionModel> questions;
	
	
	private int count=0;
	
	public void onCreate()
	{
		
		Clients.evalJavaScript("countdown()");
	ApplicationContext ctx = 
			WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
	examdao=(Dao)ctx.getBean("taskDAO2");
	Listbox li=(Listbox)this.getFellow("questionPaperList");
	Session examname=Sessions.getCurrent();
	String s=(String)examname.getAttribute("selectedExam");
	
	
	
    ListModelList<QuestionModel> list= new ListModelList(examdao.getQuestions(s));
	li.setModel(list);
}
	


	public void user(String id) 
	{
		ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
		examdao=(Dao)ctx.getBean("taskDAO2");
		Radiogroup x=(Radiogroup)this.getFellow(id);
		String s=x.getSelectedItem().getValue();
		System.out.println(s);
		
		
		String ans=examdao.getAnswerById(id);
		System.out.println(ans);
		if(s.equalsIgnoreCase(ans)) {
		count++;
		}
		
		System.out.println(count);
	}
		
	public void submit() {
		Marksmodel m=new Marksmodel();
		
		Session s1=Sessions.getCurrent();
		int id=(Integer)s1.getAttribute("studentid");
		
		String c=(String)s1.getAttribute("selectedExam");
		m.setId(id);
		m.setCoursename(c);
		m.setMarks(count);
		examdao.insert(m);
		
		Executions.sendRedirect("/Student/navigate.zul");
	}
	public void method()
	{
		Executions.sendRedirect("/Student/Mainpage.zul");
	}
		



	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
 }
