package com.lms.Tutor;

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
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;

public class TutorCardsInfo extends Window implements Composer<Component> {
	
	
	private static final long serialVersionUID = 1L;
	protected TutorDAO tutorDAO;
	TutorCards tc=new TutorCards();
	public void onCreate() throws Exception {
		
	System.out.println("hiii");
		ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext(
					(ServletContext)getDesktop().getWebApp().getNativeContext());
				tutorDAO = (TutorDAO)ctx.getBean("tutDAO");
					tc=tutorDAO.findCards();

							Label l1=(Label)this.getFellow("l1");
							l1.setValue(tc.getStudents());
							
							Label l2=(Label)this.getFellow("l2");
							l2.setValue(String.valueOf((tc.getScount())));
							
							Label l3=(Label)this.getFellow("l3");
							l3.setValue(tc.getCourses());
							
							Label l4=(Label)this.getFellow("l4");					
						    l4.setValue(String.valueOf(tc.getCcount()));
						    
						    Label l5=(Label)this.getFellow("l5");		
						    l5.setValue(tc.getFilesnames());
						    
						    Label l6=(Label)this.getFellow("l6");		
							l6.setValue(String.valueOf(tc.getFcount()));
							
							Label l7=(Label)this.getFellow("l7");
							l7.setValue(tc.getLectures());
							
							Label l8=(Label)this.getFellow("l8");		
								l8.setValue(String.valueOf(tc.getCcount()));
						
	}
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
