package com.lms.Student;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.Composer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.lms.Student.Dao;

public class feedbackcontroller extends Window implements Composer<Component>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Dao db;
	public void feedback() throws Exception{
		
		Textbox sub=(Textbox)this.getFellow("sub");
		String s=sub.getValue();
		
		Textbox desc=(Textbox)this.getFellow("desc");
		String des=desc.getValue();
	//	taskDAO4
		ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext(
					(ServletContext)getDesktop().getWebApp().getNativeContext());
		db=(Dao)ctx.getBean("taskDAO2");
		
		feedbackmodel fm=new feedbackmodel();
		fm.setId(2071);
		fm.setSub(s);
		fm.setDesc(des);
		int i=db.feedbacksubmit(fm);
		if(i==1)
		{
			Messagebox.show("feedback submitted");
		}
		else
		{
			Messagebox.show("There was an year");
		}
		
	}
	@Override
	public void doAfterCompose(Component arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
