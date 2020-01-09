package com.lms.Tutor;

import javax.servlet.ServletContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import org.zkoss.zk.ui.Component;

import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class EpaperController  extends Window {


	TutorDAO db;

	 
	
	
	
	private void showNotify(String msg, String type, Component ref) {
		// TODO Auto-generated method stub
		Clients.showNotification(msg, type, ref, "end_center", 2000);
	}
		
	public void LoginUser2()
	{
		 
			ApplicationContext ctx = 
					WebApplicationContextUtils.getRequiredWebApplicationContext(
						(ServletContext)getDesktop().getWebApp().getNativeContext());
			db=(TutorDAO)ctx.getBean("tutDAO");
		
		
			EpaperModel task=new EpaperModel();
			
	
			
			Textbox qsn=(Textbox)this.getFellow("qsn");
			String question=qsn.getValue();
			
			Textbox opt_a=(Textbox)this.getFellow("OptA");
			String option_a=opt_a.getValue();
			
			
			Textbox opt_b=(Textbox)this.getFellow("OptB");
			String option_b=opt_b.getValue();
			
			Textbox opt_c=(Textbox)this.getFellow("OptC");	
			String option_c=opt_c.getValue();
			
			Textbox opt_d=(Textbox)this.getFellow("OptD");	
			String option_d=opt_d.getValue();
			
			
			Textbox ans=(Textbox)this.getFellow("Ans");
			String correctanswer=ans.getValue();
			
			Textbox qtype=(Textbox)this.getFellow("etype");
			String questiontype=qtype.getValue();
			
			System.out.println(questiontype);
			task.setQuestion(question);
			task.setOption_a(option_a);
			task.setOption_b(option_b);
			task.setOption_c(option_c);
			task.setOption_d(option_d);
			task.setCorrect_ans(correctanswer);
			task.setQuestion_type(questiontype);
			int i1=0;	
			if(questiontype.equals("java")){
				System.out.println("java");
				
				i1=db.Epaper(task);
				
			}
			else if(questiontype.equals("c")){
				System.out.println("c");
					i1=db.Epaper1(task);
			}
			
			else if(questiontype.equals("html")){
				System.out.println("html");
				i1=db.Epaper2(task);
			}
						if(i1==1){
							
							Messagebox.show("Question entered successfully");
							Textbox questtype=(Textbox)this.getFellow("qsn");
							questtype.setValue(null);
							
							Textbox opn_a=(Textbox)this.getFellow("OptA");
							 opn_a.setValue(null);
							
							
							Textbox opn_b=(Textbox)this.getFellow("OptB");
							 opn_b.setValue(null);
							Textbox opn_c=(Textbox)this.getFellow("OptC");	
							opn_c.setValue(null);
							
							Textbox opn_d=(Textbox)this.getFellow("OptD");	
						opn_d.setValue(null);
							
							
							Textbox answer=(Textbox)this.getFellow("Ans");
							answer.setValue(null);
							
							Textbox questype=(Textbox)this.getFellow("etype");
							questype.setValue(null);
							
							
						}
						
						else{
							Messagebox.show("Error while submitting");
						}
						
						
	}	
}





