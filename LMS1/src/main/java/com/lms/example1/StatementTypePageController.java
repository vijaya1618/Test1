package com.lms.example1;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

public class StatementTypePageController {

	private String statement;



	
	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	@Init
	public void init() {
		statement="Student_Registration.zul";
	}

	@Command
	@NotifyChange("statement")
	public void byDate() {
		statement="Student_Registration.zul";
	}

	@Command
	@NotifyChange("statement")
	public void byMonth() {
		statement="Tutor_Registration.zul";
	}
}
