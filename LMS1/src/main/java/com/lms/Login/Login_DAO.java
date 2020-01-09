package com.lms.Login;

public interface Login_DAO {
	public Login_model LoginStudent(Login_model s);
	public Login_model LoginTutor(Login_model s);
	public Login_model LoginAdmin(Login_model s);
	public void contactInsert(contact c);
}
