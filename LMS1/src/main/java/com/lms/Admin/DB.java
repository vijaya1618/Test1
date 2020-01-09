package com.lms.Admin;

import java.sql.*;

import org.zkoss.zul.Window;

import java.io.*;
 

 
public class DB extends Window{
	
    
	private static final long serialVersionUID = 1L;
	
	
	
	

		protected void dbConnect() throws Exception {
        
 
   
                try
                {
                        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                        Connection conn = DriverManager.getConnection(
                        		"jdbc:sqlserver://192.168.110.66:1433;database=LMS","TAPadmin","zxcvbn1@");
 
                        System.out.println("connected");
                        insertImage(conn,"d://filepath//test.JPG");
                        getImageData(conn);  
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                        
                }
        }
 
        public void insertImage(Connection conn,String img)
        {
                int len;
                String query;
                PreparedStatement pstmt;
                 
                try
                {
                        File file = new File(img);
                        FileInputStream fis = new FileInputStream(file);
                        len = (int)file.length();
 
                        query = ("insert into TableImage VALUES(?,?,?)");
                        pstmt = conn.prepareStatement(query);
                        pstmt.setString(1,file.getName());
                        pstmt.setInt(2, len);
   
                        // Method used to insert a stream of bytes
                        pstmt.setBinaryStream(3, fis, len); 
                        pstmt.executeUpdate();
 
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }
        }
 
        public void getImageData(Connection conn)
        {
                 
                 byte[] fileBytes;
                 String query;
                 try
                 {
                         query = "select data from tableimage";
                         Statement state = conn.createStatement();
                         ResultSet rs = state.executeQuery(query);
                         if (rs.next())
                        {
                                  fileBytes = rs.getBytes(1);
                                  OutputStream targetFile=  
                                  new FileOutputStream(
                                       "d://filepath//new.JPG");
 
                                  targetFile.write(fileBytes);
                                  targetFile.close();
                        }        
                         
                 }
                 catch (Exception e)
                 {
                         e.printStackTrace();
                 }
        }
};