package com.lms.Student;

import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.util.Composer;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Window;

public class ExamController extends Window implements Composer<Component>{

	Dao db;
	private static final long serialVersionUID = 1L;
	
	
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
			public void startExam()
			{
				
				Label l=(Label)this.getFellow("listitem");
				String ltr=l.getValue();
				System.out.println(ltr);
					Session examname=Sessions.getCurrent();
					examname.setAttribute("selectedExam",ltr);
					Executions.sendRedirect("/Student/Exampaper.zul");
				
				
			}
			
	@Override
	public void doAfterCompose(Component arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
