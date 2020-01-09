package com.lms.Student;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;


public class NewsController extends SelectorComposer<Component>{
	
	 @Listen("onClick= #py")		
	 public void py() { 
         Window window = (Window)Executions.createComponents("pythonupdates.zul", null, null);
          window.doModal();
}
	 
	 @Listen("onClick= #li")		
	 public void li() { 
         Window window = (Window)Executions.createComponents("linuxupdates.zul", null, null);
          window.doModal();
}
	 
	 @Listen("onClick= #pm")		
	 public void pm() { 
         Window window = (Window)Executions.createComponents("projectupdates.zul", null, null);
          window.doModal();
}
	 
	 @Listen("onClick= #ar")		
	 public void ar() { 
         Window window = (Window)Executions.createComponents("arduinoupdates.zul", null, null);
          window.doModal();
}
}
