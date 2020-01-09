
package com.lms.Admin;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Include;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class LogController extends  SelectorComposer<Component> {
	@Wire
	public Include x;
	private static final long serialVersionUID = 1L;

	@Listen("onClick = #HomePage")
	public void mainpage() {
		x.setSrc("/Admin/main.zul");
	}

	@Listen("onClick = #UpdateCourses")
	public void updatecourses() {
		x.setSrc("/Admin/UpdateCourses.zul");
	}

	@Listen("onClick = #ExamSchedule")
	public void examschedule() {
		x.setSrc("/Admin/CreateExam.zul");
	}

	@Listen("onClick = #TutorInfo")
	public void tutorinfo() {
		Window window = (Window) Executions.createComponents("TutorInfo.zul", null, null);
		window.doModal();
	}

	@Listen("onClick = #StudentFeedback")
	public void stufeedback() {
		x.setSrc("/Admin/UserFeedback.zul");
	}
	@Listen("onClick = #RegisteredUsers")
	public void regusers() {
		x.setSrc("/Admin/RegisteredUsers.zul");
	}
@Listen("onClick = #UsersScores")
public void usersScore()
{
	x.setSrc("/Admin/Totalmarks.zul");
}
}


