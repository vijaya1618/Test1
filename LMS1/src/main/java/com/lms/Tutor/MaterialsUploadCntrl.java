package com.lms.Tutor;

import java.io.FileNotFoundException;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.lms.Tutor.TutorDAO;
import com.lms.Tutor.ImageBean;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.util.Composer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

public class MaterialsUploadCntrl extends Div implements Composer<Component> {
	 ImageBean ib=new ImageBean();
	private static final long serialVersionUID = 1L;
	protected TutorDAO tutdao;
	public void fileUpload()  throws FileNotFoundException {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext((ServletContext) getDesktop().getWebApp().getNativeContext());
		tutdao = (TutorDAO) ctx.getBean("tutDAO");
		// System.out.println("HELLO1");
		 
		EventListener<UploadEvent> el = new EventListener<UploadEvent>() {
			public void onEvent(UploadEvent ev) throws FileNotFoundException {
				Media m = ev.getMedia();
				// System.out.println("HELLO2");
				// System.out.println(m.getName());
				byte[] b = m.getByteData();
				
				
				
				// FileInputStream fis = new FileInputStream(m.getByteData());
				
				ib.setImage(b);
				
				
			}
		};
		
		

		Fileupload.get(el);
		Button b=(Button)this.getFellow("submit");
		b.setVisible(true);
		Button b2=(Button)this.getFellow("upload");
		b2.setVisible(false);
	}
	public void MaterialDetailsUpload() throws FileNotFoundException   {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext((ServletContext) getDesktop().getWebApp().getNativeContext());
		tutdao = (TutorDAO) ctx.getBean("tutDAO");
	Intbox i=(Intbox)this.getFellow("courseid");
	Textbox c=(Textbox)this.getFellow("coursename");
	Textbox f=(Textbox)this.getFellow("filename");
	ib.setFilename(f.getValue());
	ib.setCourseid(i.getValue());
	ib.setCoursename(c.getValue());
	System.out.println(f.getValue());
	
	
	String res;
        res=tutdao.file(ib);
        if(res.equals("success"))
		{
			Messagebox.show("File Uploaded successfully");
			
		}
		else if(res.equals("fail"))
		{
			Messagebox.show("Error while inserting  "+res+" !");
		}
	}
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		
	}


}
