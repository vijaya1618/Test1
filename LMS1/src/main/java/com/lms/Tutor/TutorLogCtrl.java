package com.lms.Tutor;

import org.zkoss.zk.ui.*;
import org.zkoss.zk.ui.event.*;
import org.zkoss.zk.ui.util.*;
import org.zkoss.zk.ui.ext.*;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.au.*;
import org.zkoss.zk.au.out.*;
import org.zkoss.zul.*;
import org.zkoss.zul.Window;
public class TutorLogCtrl extends SelectorComposer<Component>{
	@Listen("onClick=#exam")	
	public void button2()
	{
	   Window w=(Window)Executions.createComponents("/chapter1/iframe.zul", null,null);
	 System.out.println("success");
	   w.doModal();
	}
	@Listen("onClick=#courses")	
	public void button3()
	{
	   Window w=(Window)Executions.createComponents("/Tutor/TutorCourseCheck.zul", null,null);
	 System.out.println("success");
	   w.doModal();
	}




}

