package com.lms.Student;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JOptionPane;

import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import java.util.Random;

public class forgotpassword extends Window{
/**
*
*/
private static final long serialVersionUID = 1L;
int otp;


public void sendotp() throws Exception{
Longbox num=(Longbox)this.getFellow("mobile");
Long number=num.getValue();

        try {
           
       
            Random rd1=new Random();
            otp=rd1.nextInt(999999);
           
// Construct data+
            //LqiIzyQRH7E-mtfMvcYUnzbplNMUDUgQaK6nrx7BKi
            //QTZ7V3/ao8Q-KB1zKCzGDoZUAqoDimmgTBrd7Zlyt4
String apiKey = "apikey=" + "QTZ7V3/ao8Q-KB1zKCzGDoZUAqoDimmgTBrd7Zlyt4";
String message = "&message=" +"Hey Your OTP"+" "+ otp;
String sender = "&sender=" + "TXTLCL";
String numbers = "&numbers=" +"91"+ number;

// Send data
HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
String data = apiKey + numbers + message + sender;
conn.setDoOutput(true);
conn.setRequestMethod("POST");
conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
conn.getOutputStream().write(data.getBytes("UTF-8"));
final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
String line;
while ((line = rd.readLine()) != null) {
//JOptionPane.showMessageDialog(null," message"+ line);
Messagebox.show("OTP send successfully");
}
rd.close();
//return stringBuffer.toString();
} catch (Exception e) {
//System.out.println("Error SMS "+e);
//return "Error "+e;
       System.out.println(e);
}
    }
public void verify() throws Exception
{
Intbox num=(Intbox)this.getFellow("otp");
int number=num.getValue();

if(number==otp) {

Executions.sendRedirect("/Student/reenter.zul");

}else
{
Messagebox.show("wrong otp");
}
}
}


