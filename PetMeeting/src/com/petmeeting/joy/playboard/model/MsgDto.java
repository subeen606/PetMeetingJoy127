package com.petmeeting.joy.playboard.model;

import java.io.Serializable;

public class MsgDto implements Serializable{
	
	private int seq;
	
	private String to_email;
	private String from_email;
	
	private String content;
	
	private String senddate;
	private String readdate;
	
	private int important;	 //0 보통 1 중요
	private int readcheck;
	
	public MsgDto() {

	}

	public MsgDto(int seq, String to_email, String from_email, String content, String senddate, String readdate,
			int important, int readcheck) {
		super();
		this.seq = seq;
		this.to_email = to_email;
		this.from_email = from_email;
		this.content = content;
		this.senddate = senddate;
		this.readdate = readdate;
		this.important = important;
		this.readcheck = readcheck;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getTo_email() {
		return to_email;
	}

	public void setTo_email(String to_email) {
		this.to_email = to_email;
	}

	public String getFrom_email() {
		return from_email;
	}

	public void setFrom_email(String from_email) {
		this.from_email = from_email;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSenddate() {
		return senddate;
	}

	public void setSenddate(String senddate) {
		this.senddate = senddate;
	}

	public String getReaddate() {
		return readdate;
	}

	public void setReaddate(String readdate) {
		this.readdate = readdate;
	}

	public int getImportant() {
		return important;
	}

	public void setImportant(int important) {
		this.important = important;
	}

	public int getReadcheck() {
		return readcheck;
	}

	public void setReadcheck(int readcheck) {
		this.readcheck = readcheck;
	}

	@Override
	public String toString() {
		return "MsgDto [seq=" + seq + ", to_email=" + to_email + ", from_email=" + from_email + ", content=" + content
				+ ", senddate=" + senddate + ", readdate=" + readdate + ", important=" + important + ", readcheck="
				+ readcheck + "]";
	}
	
   
}
