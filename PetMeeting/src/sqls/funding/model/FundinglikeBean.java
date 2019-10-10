package com.petmeeting.joy.funding.model;

import java.io.Serializable;


public class FundinglikeBean implements Serializable{

	private int board_seq;
	private String email;
	
	public FundinglikeBean() {}
	
	public int getBoard_seq() {
		return board_seq;
	}
	public void setBoard_seq(int board_seq) {
		this.board_seq = board_seq;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public FundinglikeBean(int board_seq, String email) {
		super();
		this.board_seq = board_seq;
		this.email = email;
	}
	@Override
	public String toString() {
		return "FundinglikeBean [board_seq=" + board_seq + ", email=" + email + "]";
	}
	
	
	
}
