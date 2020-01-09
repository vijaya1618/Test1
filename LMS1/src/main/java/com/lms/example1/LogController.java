package com.lms.example1;


import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zul.Window;


	public class LogController extends SelectorComposer<Component>{
				/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

				@Listen("onClick = #log")		
				 public void login() {
			            Window window = (Window)Executions.createComponents("Login.zul", null, null);
			            window.setBorder(true);
			            window.setClosable(true);
			           window.setTitle("SIGN IN");
			            window.doModal();
			        } 

				 @Listen("onClick = #register")		
				 public void register() { 
			         Window window = (Window)Executions.createComponents("Radio.zul", null, null);
			         window.setBorder(true);
			            window.setClosable(true);
			         window.setTitle("SIGN UP");
			          window.doModal();
					
			}	        
			    }
		
