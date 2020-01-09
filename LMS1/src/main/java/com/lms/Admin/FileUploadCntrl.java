package com.lms.Admin;

import java.io.FileNotFoundException;

import javax.servlet.ServletContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Fileupload;
import org.zkoss.image.AImage;
import org.zkoss.zul.Image;



public class FileUploadCntrl extends Div {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected AdminDao regdao;
	
	public void fileUpload() throws FileNotFoundException
	{
		ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
		regdao=(AdminDao)ctx.getBean("DeptDao");	
		//System.out.println("HELLO1");
	        EventListener<UploadEvent> el = new EventListener<UploadEvent>() {
	            public void onEvent(UploadEvent ev) throws FileNotFoundException {
	               Media m = ev.getMedia();
	      //         System.out.println("HELLO2");
	        //       System.out.println(m.getName());
	               byte[] b=m.getByteData();
	               //FileInputStream fis = new FileInputStream(m.getByteData());
	               ImageBean ib=new ImageBean();
	       			ib.setImage(b);
	       			ib.setFilename(m.getName());
	       			regdao.file(ib);
	            }
	        };
	      
	        Fileupload.get(el);
		
	}
	public void fileretrival() throws Exception
	{
		ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
		regdao=(AdminDao)ctx.getBean("DeptDao");
		ImageBean ib=regdao.retrieve1();
		//AImage aImage = new AImage(ib.getFilename(),  ib.getImage());	
		
		
		Filedownload.save(ib.getImage(),ib.getImage().toString(),ib.getFilename()+"'s material.pdf");
		//Image img = (Image)this.getFellow("image");
		//img.setContent(aImage);
	}
	
	

}
