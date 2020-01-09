package com.lms.Admin;

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


public class AddExamInfoPopup extends SelectorComposer<Component> {

@Listen("onClick=#submit")	
public void button()
{
   Window w=(Window)Executions.createComponents("/Admin/AddExam.zul", null,null);
   w.doModal();
}


	
}
