package com.lms.Student;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.Composer;
import org.zkoss.zul.Window;

public class LogController extends Window implements Composer<Component> {

	public void examOpen(){
		
		Executions.sendRedirect("/Student/Exampaper.zul");
	}
	
	

	@Override
	public void doAfterCompose(Component arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
