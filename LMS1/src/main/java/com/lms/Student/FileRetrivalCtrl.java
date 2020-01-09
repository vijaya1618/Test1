package com.lms.Student;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;

import javax.servlet.ServletContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.util.media.AMedia;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Composer;
import org.zkoss.zul.Div;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Iframe;
import org.zkoss.image.AImage;
import org.zkoss.zul.Image;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;

public class FileRetrivalCtrl extends Div implements Composer<Component>{
	@Wire
	Iframe report;

	private static final long serialVersionUID = 1L;
	protected Dao stdao;
	
	

	public void fileretrival(String s) throws Exception {

		Iframe i = (Iframe) this.getFellow("report");
		i.setVisible(true);
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext((ServletContext) getDesktop().getWebApp().getNativeContext());
		stdao = (Dao) ctx.getBean("taskDAO2");
			
		System.out.println(s);
		ImageBean ib = stdao.retrieve1(s);
	System.out.println("hi");
		AMedia amedia = null;
		ByteArrayInputStream b = new ByteArrayInputStream(ib.getImage());
		amedia = new AMedia(ib.getFilename(), "pdf", "application/pdf", b);
		Filedownload.save(ib.getImage(), ib.getImage().toString(), ib.getFilename() + "'s material");
		i.setContent(amedia);
		/*
		 * System.out.println(ib.getFilename()); //System.out.println(ib.getImage()); );
		 * //x.setContent(amedia); //Image img = (Image)this.getFellow("image");
		 * //img.setContent(aImage);
		 */

	}
	
	
	public void fileUpload() throws FileNotFoundException {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext((ServletContext) getDesktop().getWebApp().getNativeContext());
		stdao = (Dao) ctx.getBean("taskDAO2");
		// System.out.println("HELLO1");
		EventListener<UploadEvent> el = new EventListener<UploadEvent>() {
			public void onEvent(UploadEvent ev) throws FileNotFoundException {
				Media m = ev.getMedia();
				// System.out.println("HELLO2");
				// System.out.println(m.getName());
				byte[] b = m.getByteData();
				// FileInputStream fis = new FileInputStream(m.getByteData());
				ImageBean ib = new ImageBean();
				ib.setImage(b);
				ib.setFilename(m.getName());
				stdao.file(ib);
			}
		};

		Fileupload.get(el);

	}
	
			public void closeIframe(){
			
				System.out.println("entering");
				Iframe i = (Iframe) this.getFellow("report");
				i.setVisible(false);
				System.out.println("leaving");
			}
	
	
	@Override
	public void doAfterCompose(Component arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
		



}




