package com.lms.example1;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Include;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;



public class logincontroller extends SelectorComposer<Component>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Wire
	 public Include x;
	 @Wire
	 private Window resultwin;
	 @Wire
	 private Window win;
	
	
	 
		@Listen("onClick = #about")
		public void abot()
		{
		x.setSrc("Aboutus.zul");
		}

		@Listen("onClick = #contact")
		public void contact()
		{
		x.setSrc("contactus.zul");
		}
}

