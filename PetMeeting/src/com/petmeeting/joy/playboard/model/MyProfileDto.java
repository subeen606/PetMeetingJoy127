package com.petmeeting.joy.playboard.model;

import java.io.Serializable;

public class MyProfileDto implements Serializable{
	
	private String email;
	private String myprofile_img;
	private int myage;
	private int mysex;
	private String myintro;
	
	public MyProfileDto() {
	}

	public MyProfileDto(String email, String myprofile_img, int myage, int mysex, String myintro) {
		super();
		this.email = email;
		this.myprofile_img = myprofile_img;
		this.myage = myage;
		this.mysex = mysex;
		this.myintro = myintro;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMyprofile_img() {
		return myprofile_img;
	}

	public void setMyprofile_img(String myprofile_img) {
		this.myprofile_img = myprofile_img;
	}

	public int getMyage() {
		return myage;
	}

	public void setMyage(int myage) {
		this.myage = myage;
	}

	public int getMysex() {
		return mysex;
	}

	public void setMysex(int mysex) {
		this.mysex = mysex;
	}

	public String getMyintro() {
		return myintro;
	}

	public void setMyintro(String myintro) {
		this.myintro = myintro;
	}

	@Override
	public String toString() {
		return "MyProfileDto [email=" + email + ", myprofile_img=" + myprofile_img + ", myage=" + myage + ", mysex="
				+ mysex + ", myintro=" + myintro + "]";
	}
	
	
}
