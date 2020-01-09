package com.lms.Student;

import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Window;


public class Controller extends Window{

	/**
	 * 
	 */
	String x;
	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	Dao db;
	private String statement;
	private static final long serialVersionUID = 1L;
		

			@Init
			public void init() {
				Session s=Sessions.getCurrent();
				x=(String)s.getAttribute("username");
				statement="/Student/Dashboard.zul";
			}
			
			@Command
			@NotifyChange("statement")
			public void dashboard() {
				statement="/Student/Dashboard.zul";
			}
			@Command
			@NotifyChange("statement")
			public void courses() {
				statement="/Student/Course.zul";
			}
			
			
			@Command
			@NotifyChange("statement")
			public void profile() {
				statement="/Student/profile.zul";
			}
			@Command
			@NotifyChange("statement")
			public void news() {
				statement="/Student/News.zul";
			}
			@Command
			@NotifyChange("statement")
			public void status() {
				statement="/Student/Status.zul";
			}
			@Command
			@NotifyChange("statement")
			public void feedback() {
				statement="/Student/feedback.zul";
			}
			@Command
			@NotifyChange("statement")
			public void courseNews() {
				statement="/Student/CourseNews.zul";
			}
			@Command
			@NotifyChange("statement")
			public void materials() {
				statement="/Student/Materials.zul";
			}
			@Command
			@NotifyChange("statement")
			public void exam() {
				statement="/Student/ExamOpen.zul";
			}
			
			@Command
			public void logout(){
				Session sess=Sessions.getCurrent();
				sess.removeAttribute("loginmodel");
				
				Executions.sendRedirect("/Login/index.zul");
			}

			
			
	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}
	
	public void onCreate()
	{
		Session s=Sessions.getCurrent();
		int id=(Integer)s.getAttribute("studentid");
		Listbox lb=(Listbox)this.getFellow("list");
		lb.getItems().clear();
		@SuppressWarnings("deprecation")
		ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext(
					(ServletContext)getDesktop().getWebApp().getNativeContext());
		db=(Dao)ctx.getBean("taskDAO2");
		List<Model> result=db.find(id);
		
		Iterator<Model> iterator=result.iterator();
		while(iterator.hasNext())
		{
			Model m=iterator.next();
			Listitem lt=new Listitem();
			lt.appendChild(new Listcell(m.getCoursename()));	
			lb.appendChild(lt);
		}
	}
	
	
	
	
}
