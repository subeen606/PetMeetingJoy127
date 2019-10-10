package com.petmeeting.joy.playboard.model;

import java.io.Serializable;

public class PlayboardHashTagDto implements Serializable{
	
	private int seq;
	private String board_code;
	private int board_seq;
	private String hash1;
	private String hash2;
	private String hash3;
	private String hash4;
	private String hash5;
	
	public PlayboardHashTagDto() {
	}

	public PlayboardHashTagDto(int seq, String board_code, int board_seq, String hash1, String hash2, String hash3,
			String hash4, String hash5) {
		super();
		this.seq = seq;
		this.board_code = board_code;
		this.board_seq = board_seq;
		this.hash1 = hash1;
		this.hash2 = hash2;
		this.hash3 = hash3;
		this.hash4 = hash4;
		this.hash5 = hash5;
	}

	public PlayboardHashTagDto(String hash1, String hash2, String hash3, String hash4, String hash5) {
		super();
		this.hash1 = hash1;
		this.hash2 = hash2;
		this.hash3 = hash3;
		this.hash4 = hash4;
		this.hash5 = hash5;
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

	public String getHash1() {
		return hash1;
	}

	public void setHash1(String hash1) {
		this.hash1 = hash1;
	}

	public String getHash2() {
		return hash2;
	}

	public void setHash2(String hash2) {
		this.hash2 = hash2;
	}

	public String getHash3() {
		return hash3;
	}

	public void setHash3(String hash3) {
		this.hash3 = hash3;
	}

	public String getHash4() {
		return hash4;
	}

	public void setHash4(String hash4) {
		this.hash4 = hash4;
	}

	public String getHash5() {
		return hash5;
	}

	public void setHash5(String hash5) {
		this.hash5 = hash5;
	}

	@Override
	public String toString() {
		return "PlayboardHashTagBean [seq=" + seq + ", board_code=" + board_code + ", board_seq=" + board_seq
				+ ", hash1=" + hash1 + ", hash2=" + hash2 + ", hash3=" + hash3 + ", hash4=" + hash4 + ", hash5=" + hash5
				+ "]";
	}
	
	

}
