package com.secondspring;

import java.util.ArrayList;
import java.util.Date;

public class Users {

	private String FirstName;
	private String LastName;
	private int Age;
	private Date Bithday;
	private long Mobile;
	private ArrayList<String> StudentSkills;
	
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		Age = age;
	}
	public Date getBithday() {
		return Bithday;
	}
	public void setBithday(Date bithday) {
		Bithday = bithday;
	}
	public long getMobile() {
		return Mobile;
	}
	public void setMobile(long mobile) {
		Mobile = mobile;
	}
	public ArrayList<String> getStudentSkills() {
		return StudentSkills;
	}
	public void setStudentSkills(ArrayList<String> studentSkills) {
		StudentSkills = studentSkills;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String FirstName) {
		this.FirstName = FirstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String LastName) {
		this.LastName = LastName;
	}

	
}
