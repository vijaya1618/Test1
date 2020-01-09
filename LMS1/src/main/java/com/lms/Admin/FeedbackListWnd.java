
package com.lms.Admin;

import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Progressmeter;
import org.zkoss.zul.Window;
import org.zkoss.zk.ui.AbstractComponent;

public class FeedbackListWnd extends Window {

	private static final long serialVersionUID = 1L;
	protected AdminDao DeptDao;
	protected List tasks;
	
	
	public void onCreate() throws Exception {
		// spring bean, taskDAO
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext((ServletContext) getDesktop().getWebApp().getNativeContext());
		DeptDao = (AdminDao) ctx.getBean("DeptDao");

		tasks = DeptDao.FeedBack();
		
		render();
	}


	protected void render() {
		Listbox lb = (Listbox) this.getFellow("FeedbackList");
		//Progressmeter p=(Progressmeter)this.getFellow("progress");
		lb.getItems().clear();
		for (Iterator it = tasks.iterator(); it.hasNext();) {
			UserFeedback t = (UserFeedback) it.next();

			Listitem lt = new Listitem();
			lt.setValue(t);
			lt.appendChild(new Listcell(String.valueOf(t.getId())));
			lt.appendChild(new Listcell(t.getSub()));

			lt.appendChild(new Listcell(t.getDesc()));
			lb.appendChild(lt);
			//p.setValue(100);
			
		}
	}

	

	
}
