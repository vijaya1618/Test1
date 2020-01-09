package com.lms.Login;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zk.ui.util.Composer;

public class ComboController  extends Div implements Composer<Component> {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Listen("onSelect = #cm")
	public void user() {
	
	Combobox x=(Combobox)this.getFellow("cm");
	String s=x.getSelectedItem().getLabel();
	
	if(s.equals("c"))
	{
		Executions.sendRedirect("/Login/C.zul");
	}
	else if(s.equals("java"))
	{
		Executions.sendRedirect("/Login/Java.zul");
	}
	
	else if(s.equals("c++"))
	{
		Executions.sendRedirect("/Login/C++.zul");
	}
	}

	@Override
	public void doAfterCompose(Component arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
}
