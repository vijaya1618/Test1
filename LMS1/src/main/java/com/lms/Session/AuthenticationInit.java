package com.lms.Session;

import java.util.Map;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.util.Initiator;


import com.lms.Login.Login_model;

public class AuthenticationInit implements Initiator {
 AuthenticationService as=new AuthenticationServiceImpl();
	public void doInit(Page page, Map<String, Object> args) throws Exception {
		// TODO Auto-generated method stub
		Login_model lm=as.getUserCredentials();
		if(lm==null){
			Executions.sendRedirect("/Login/index.zul");
			return;
		}
		
	}
}