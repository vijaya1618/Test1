package com.lms.Tutor;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Window;
public class LogController extends Window{
	private String statement;
	private static final long serialVersionUID = 1L;
	
	String x;

	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Init
	public void init() {
		Session s=Sessions.getCurrent();
		 x=(String)s.getAttribute("username");
		 System.out.println(x);
		statement="/Tutor/TutorHome.zul";
	}
	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}
	@Command
	@NotifyChange("statement")
	public void materialUp() {
		statement="/Tutor/MaterialUpload.zul";
	}
	@Command
	@NotifyChange("statement")
	public void dashBoard() {
		statement="/Tutor/TutorHome.zul";
	}
	@Command
	@NotifyChange("statement")
	public void examPaper() {
		statement="/Tutor/Exampaperinsert.zul";
	}
	
	
	@Command
	@NotifyChange("statement")
	public void examEntry() {
		statement="/Tutor/TutorExamEntry.zul";
	}
	@Command
	@NotifyChange("statement")
	public void profile() {
		statement="/Tutor/TutorProfile.zul";
	}
	@Command
	public void logout(){
		Session sess=Sessions.getCurrent();
		sess.removeAttribute("loginmodel");
		
		Executions.sendRedirect("/Login/index.zul");
	}
}
