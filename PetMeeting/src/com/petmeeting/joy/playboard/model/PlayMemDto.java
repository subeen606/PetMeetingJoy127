package com.petmeeting.joy.playboard.model;

import java.io.Serializable;

public class PlayMemDto implements Serializable{
	private int seq;
	private int play_seq;
	private String email;
	
	private String nickname;
	
	private MyProfileDto memProfile;
	
	public PlayMemDto() {
	}

	public PlayMemDto(int seq, int play_seq, String email) {
		super();
		this.seq = seq;
		this.play_seq = play_seq;
		this.email = email;
	}

	public PlayMemDto(int seq, int play_seq, String email, String nickname) {
		super();
		this.seq = seq;
		this.play_seq = play_seq;
		this.email = email;
		this.nickname = nickname;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getPlay_seq() {
		return play_seq;
	}

	public void setPlay_seq(int play_seq) {
		this.play_seq = play_seq;
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

	public MyProfileDto getMemProfile() {
		return memProfile;
	}

	public void setMemProfile(MyProfileDto memProfile) {
		this.memProfile = memProfile;
	}

	@Override
	public String toString() {
		return "PlayMemDto [seq=" + seq + ", play_seq=" + play_seq + ", email=" + email + ", nickname=" + nickname
				+ ", memProfile=" + memProfile + "]";
	}
	
	
}
