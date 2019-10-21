package com.petmeeting.joy.admin.model;

import java.io.Serializable;

public class ReportDto implements Serializable{
	
	private int seq;
	
	private String board_code;
	private int board_seq;
	
	private String bad_email;
	
	private String email;
	private String nickname;
	private String reason;
	
	private String reasonTxt;
	
	public ReportDto() {
	}

	public ReportDto(int seq, String board_code, int board_seq, String bad_email, String email, String nickname,
			String reason, String reasonTxt) {
		super();
		this.seq = seq;
		this.board_code = board_code;
		this.board_seq = board_seq;
		this.bad_email = bad_email;
		this.email = email;
		this.nickname = nickname;
		this.reason = reason;
		this.reasonTxt = reasonTxt;
	}

	public ReportDto(int board_seq, String email, String reason, String reasonTxt) {
		super();
		this.board_seq = board_seq;
		this.email = email;
		this.reason = reason;
		this.reasonTxt = reasonTxt;
	}
	
	public ReportDto(String board_code, int board_seq) {
		super();
		this.board_code = board_code;
		this.board_seq = board_seq;
	}

	public ReportDto(String bad_email, String email, String reason, String reasonTxt) {
		super();
		this.bad_email = bad_email;
		this.email = email;
		this.reason = reason;
		this.reasonTxt = reasonTxt;
	}
	
	
	/* 회원 활중용 생성자 */
	public ReportDto(String email, String reason) {
		super();
		this.email = email;
		this.reason = reason;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getBoard_code() {
		return board_code;
	}

	public void setBoard_code(String board_code) {
		this.board_code = board_code;
	}

	public int getBoard_seq() {
		return board_seq;
	}

	public void setBoard_seq(int board_seq) {
		this.board_seq = board_seq;
	}

	public String getBad_email() {
		return bad_email;
	}

	public void setBad_email(String bad_email) {
		this.bad_email = bad_email;
	}

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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReasonTxt() {
		return reasonTxt;
	}

	public void setReasonTxt(String reasonTxt) {
		this.reasonTxt = reasonTxt;
	}

	@Override
	public String toString() {
		return "ReportDto [seq=" + seq + ", board_code=" + board_code + ", board_seq=" + board_seq + ", bad_email="
				+ bad_email + ", email=" + email + ", nickname=" + nickname + ", reason=" + reason + ", reasonTxt="
				+ reasonTxt + "]";
	}

	

}
