package com.petmeeting.joy.playboard.model;

import java.io.Serializable;
import java.util.Date;

public class PlayboardQnADto implements Serializable{
	
	private int seq;
	private int board_seq;
	
	private String email;
	private String nickname;
	
	private int ref;
	private int parent;

	private String content;
	private Date regdate;
	
	private int secret;			// 0은 공개글 1은 비밀글
	private int del;
	
	private boolean replyCheck;	// 답변이 있으면 true 없으면 false
	
	private MyProfileDto memProfile;
	
	private int currPage;		// 현재 페이지 넘버
	
	private int start;			// 한 페이지의 시작 
	private int end;			// 한 페이지의 끝
	
	public PlayboardQnADto() {
	}

	public PlayboardQnADto(int seq, int board_seq, String email, String nickname, int ref, int parent, String content,
			Date regdate, int secret, int del, boolean replyCheck, MyProfileDto memProfile, int currPage, int start,
			int end) {
		super();
		this.seq = seq;
		this.board_seq = board_seq;
		this.email = email;
		this.nickname = nickname;
		this.ref = ref;
		this.parent = parent;
		this.content = content;
		this.regdate = regdate;
		this.secret = secret;
		this.del = del;
		this.replyCheck = replyCheck;
		this.memProfile = memProfile;
		this.currPage = currPage;
		this.start = start;
		this.end = end;
	}

	public PlayboardQnADto(int board_seq, int currPage, int start, int end) {
		super();
		this.board_seq = board_seq;
		this.currPage = currPage;
		this.start = start;
		this.end = end;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
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

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public int getSecret() {
		return secret;
	}

	public void setSecret(int secret) {
		this.secret = secret;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	public boolean isReplyCheck() {
		return replyCheck;
	}

	public void setReplyCheck(boolean replyCheck) {
		this.replyCheck = replyCheck;
	}

	public MyProfileDto getMemProfile() {
		return memProfile;
	}

	public void setMemProfile(MyProfileDto memProfile) {
		this.memProfile = memProfile;
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "PlayboardQnADto [seq=" + seq + ", board_seq=" + board_seq + ", email=" + email + ", nickname="
				+ nickname + ", ref=" + ref + ", parent=" + parent + ", content=" + content + ", regdate=" + regdate
				+ ", secret=" + secret + ", del=" + del + ", replyCheck=" + replyCheck + ", memProfile=" + memProfile
				+ ", currPage=" + currPage + ", start=" + start + ", end=" + end + "]";
	}

	

}
