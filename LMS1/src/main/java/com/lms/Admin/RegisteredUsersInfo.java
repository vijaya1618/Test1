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
public class RegisteredUsersInfo extends Window implements Composer<Component>{
	private static final long serialVersionUID = 1L;
	protected AdminDao taskDAO;
	protected List tasks;

	
	protected void render() {
		Listbox lb = (Listbox)this.getFellow("taskslb");
		lb.getItems().clear();
		
		for (Iterator it = tasks.iterator(); it.hasNext(); ) {
			//Task t = (Task) it.next();
			//TutorExam t=(TutorExam) it.next();
			UserInfo ui=(UserInfo) it.next();
			Listitem lt = new Listitem();
			lt.setValue(ui);
			
			//lt.appendChild(new Listcell(t.getTitle()));
			//lt.appendChild(new Listcell(t.getDescription()));
		
		
			lt.appendChild(new Listcell(String.valueOf(ui.getId())));
			lt.appendChild(new Listcell(ui.getName()));
			lt.appendChild(new Listcell(ui.getMail()));
			lt.appendChild(new Listcell(ui.getGender()));
			lt.appendChild(new Listcell(ui.getDesignation()));
			lt.appendChild(new Listcell(String.valueOf(ui.getMobile())));
			
			lb.appendChild(lt);

		}
	}
	
	public void onCreate() throws Exception {
		//	spring bean, taskDAO
		ApplicationContext ctx = 
			WebApplicationContextUtils.getRequiredWebApplicationContext(
				(ServletContext)getDesktop().getWebApp().getNativeContext());
		taskDAO = (AdminDao)ctx.getBean("DeptDao");
					System.out.println("start");
		tasks = taskDAO.findAllUsers();

			render();
	}
	

	
	public void onNew() throws Exception {
		Window win = (Window)Executions.createComponents("task.zul", null, null);
		win.doModal();
		if (win.getAttribute("OK") != null) {	//stupid
			tasks = taskDAO.findAll();
			render();
		}
	}
	

	
	public void onDelete() throws Exception {
		Listbox lb = (Listbox)this.getFellow("taskslb");
		Listitem lt = lb.getSelectedItem();
		if (lt == null)
			return;
		UserInfo  t = (UserInfo)lt.getValue();
		taskDAO.deleteUser(t);
		lt.detach();
	}

 

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
