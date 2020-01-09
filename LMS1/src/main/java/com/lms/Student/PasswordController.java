package com.lms.Student;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class PasswordController extends Window{

	/**
	 * 
	 */
	int j;
	Dao db;
	private static final long serialVersionUID = 1L;
	private Component loginform;
	public void Updatepassword() throws Exception{
		
		Textbox pass=(Textbox)this.getFellow("password");
		String password=pass.getValue();
		
		Textbox cpass=(Textbox)this.getFellow("cpassword");
		String cpassword=cpass.getValue();
		
		if(password.equals(cpassword))
		{
			int j=db.Updatepassword(2071, "password");
		}
		else
		{
			
			showNotify("password and confirm password not equal","error",loginform);
		}
		
		if(j==1)
		{
			Messagebox.show("password successfully updated");
		}else
		{
			Messagebox.show("There was an error while updating");
		}
		
	}

	private void showNotify(String msg, String type, Component ref) {
		// TODO Auto-generated method stub
		Clients.showNotification(msg, type, ref, "end_center", 2000);
	}

}
