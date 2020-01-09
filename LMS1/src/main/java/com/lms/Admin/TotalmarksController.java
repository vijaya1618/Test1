package com.lms.Admin;

import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Window;

public class TotalmarksController extends Window {

	
		private static final long serialVersionUID = 1L;
		protected AdminDao marks;
		protected List marklist;
		
		protected void render() {
			Listbox lb = (Listbox)this.getFellow("taskslb");
			lb.getItems().clear();
			
			for (Iterator it = marklist.iterator(); it.hasNext(); ) {
				Totalmarks t = (Totalmarks) it.next();
				
				Listitem lt = new Listitem();
				lt.setValue(t);
				
				lt.appendChild(new Listcell(String.valueOf(t.getId())));
				lt.appendChild(new Listcell(t.getExamname()));
				lt.appendChild(new Listcell(String.valueOf(t.getMarks())));
				
				
				lb.appendChild(lt);
			}
		}
		
		public void onCreate() throws Exception {
			//	spring bean, taskDAO
			ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext(
					(ServletContext)getDesktop().getWebApp().getNativeContext());
			marks = (AdminDao)ctx.getBean("taskDAO");
			marklist = marks.findTotalMarks() ;
			render();
		}
		

}
