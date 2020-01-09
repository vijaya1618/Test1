package com.lms.Admin;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.util.Composer;
import org.zkoss.zul.Label;
import org.zkoss.zul.Menu;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class BannerController extends Window implements Composer<Component> {

	Session sess = null;

	public void onCreate() throws Exception {

		sess = Sessions.getCurrent();

		int s1 = (Integer) sess.getAttribute("adminid");
		String s2 = (String) sess.getAttribute("username");
		Menu m = (Menu) this.getFellow("name");
		m.setLabel(s2);

	}
	public void logOut()
{
		Session sess=Sessions.getCurrent();
		sess.removeAttribute("loginmodel");
		
		Executions.sendRedirect("/Login/index.zul");
	
}
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub

	}

}