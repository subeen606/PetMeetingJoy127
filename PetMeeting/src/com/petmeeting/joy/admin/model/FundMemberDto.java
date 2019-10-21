package com.petmeeting.joy.admin.model;

import java.io.Serializable;
import java.util.Date;

public class FundMemberDto implements Serializable{

	private String email;
	private int funding_seq;
	private String nickname;
	private String myprofile_img;
	private String name;
	private int donation;
	private Date d_date;
	
	
	public FundMemberDto() {}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public String getMyprofile_img() {
		return myprofile_img;
	}


	public void setMyprofile_img(String myprofile_img) {
		this.myprofile_img = myprofile_img;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getDonation() {
		return donation;
	}


	public void setDonation(int donation) {
		this.donation = donation;
	}


	public Date getD_date() {
		return d_date;
	}


	public void setD_date(Date d_date) {
		this.d_date = d_date;
	}
	
	public int getFunding_seq() {
		return funding_seq;
	}


	public void setFunding_seq(int funding_seq) {
		this.funding_seq = funding_seq;
	}


	public FundMemberDto(String email, int funding_seq, String nickname, String myprofile_img, String name,
			int donation, Date d_date) {
		super();
		this.email = email;
		this.funding_seq = funding_seq;
		this.nickname = nickname;
		this.myprofile_img = myprofile_img;
		this.name = name;
		this.donation = donation;
		this.d_date = d_date;
	}


	@Override
	public String toString() {
		return "FundMemberDto [email=" + email + ", funding_seq=" + funding_seq + ", nickname=" + nickname
				+ ", myprofile_img=" + myprofile_img + ", name=" + name + ", donation=" + donation + ", d_date="
				+ d_date + "]";
	}

	
	
}
