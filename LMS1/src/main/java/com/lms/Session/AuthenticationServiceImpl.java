package com.lms.Session;

import java.io.Serializable;

import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

import com.lms.Login.Login_model;

public class AuthenticationServiceImpl  implements  AuthenticationService,Serializable {

	public Login_model getUserCredentials() {
		// TODO Auto-generated method stub
		 Session sess = Sessions.getCurrent();
		 Login_model lm =(Login_model)sess.getAttribute("loginmodel");		 
		return lm;
	}

}
