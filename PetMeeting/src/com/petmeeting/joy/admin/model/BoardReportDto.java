package com.petmeeting.joy.admin.model;

import java.io.Serializable;

public class BoardReportDto implements Serializable {
	private int seq;
	
	private String board_code;
	private int board_seq;
	
	private String email;
	private String nickname;
	private String reason;
	
	public BoardReportDto() {
	}

	public BoardReportDto(int seq, String board_code, int board_seq, String email, String nickname, String reason) {
		super();
		this.seq = seq;
		this.board_code = board_code;
		this.board_seq = board_seq;
		this.email = email;
		this.nickname = nickname;
		this.reason = reason;
	}

	public BoardReportDto(String board_code, int board_seq) {
		super();
		this.board_code = board_code;
		this.board_seq = board_seq;
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

	@Override
	public String toString() {
		return "BoardReportDto [seq=" + seq + ", board_code=" + board_code + ", board_seq=" + board_seq + ", email="
				+ email + ", nickname=" + nickname + ", reason=" + reason + "]";
	}

	
	
	
}
