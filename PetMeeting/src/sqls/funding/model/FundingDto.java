package com.petmeeting.joy.funding.model;

import java.io.Serializable;
import java.util.Date;
/*

DROP TABLE PM_FUNDINGBOARD
CASCADE CONSTRAINTS;

DROP SEQUENCE PM_FUNDINGBOARD_SEQ;

CREATE TABLE PM_FUNDINGBOARD(
	SEQ NUMBER PRIMARY KEY,
	BOARD_CODE VARCHAR2(10),
	EMAIL VARCHAR2(50),
	TITLE VARCHAR2(200),
	INTRO VARCHAR2(500),
	CONTENT CLOB,
	THUMBNAIL VARCHAR2(50),
	MAX_PRICE NUMBER,
	CURRENT_PRICE NUMBER,
	PERSONCOUNT NUMBER,
	LIKECOUNT NUMBER,
	READCOUNT NUMBER,
	SDATE DATE,
	EDATE DATE,
	REGDATE DATE
);

CREATE SEQUENCE PM_FUNDINGBOARD_SEQ
START WITH 1
INCREMENT BY 1;

ALTER TABLE PM_FUNDINGBOARD
ADD CONSTRAINT PM_FUNDINGBOARD_FK FOREIGN KEY(EMAIL)
REFERENCES PM_MEMBER(EMAIL);

 */

public class FundingDto implements Serializable {
	
	private int seq;
	private String board_code;
	private String email;
	private String title;
	private String intro;
	private String content;
	private String thumbnail;
	private int max_price;
	private int current_price;
	private int personcount;
	private int likecount;
	private int readcount;
	private Date sdate;
	private Date edate;
	private Date regdate;
	
	/* like check 하는 것 */
	private boolean islike;
	/* funding check 하는 것 */
	private boolean isfunding;
	/* 내역서 check 하는 것*/
	private boolean isfundingsta;

	public FundingDto() {}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public int getMax_price() {
		return max_price;
	}

	public void setMax_price(int max_price) {
		this.max_price = max_price;
	}

	public int getCurrent_price() {
		return current_price;
	}

	public void setCurrent_price(int current_price) {
		this.current_price = current_price;
	}

	public int getPersoncount() {
		return personcount;
	}

	public void setPersoncount(int personcount) {
		this.personcount = personcount;
	}

	public int getLikecount() {
		return likecount;
	}

	public void setLikecount(int likecount) {
		this.likecount = likecount;
	}
	
	

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	public Date getSdate() {
		return sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	public Date getEdate() {
		return edate;
	}

	public void setEdate(Date edate) {
		this.edate = edate;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	

	public boolean isIslike() {
		return islike;
	}

	public void setIslike(boolean islike) {
		this.islike = islike;
	}
	

	public boolean isIsfunding() {
		return isfunding;
	}

	public void setIsfunding(boolean isfunding) {
		this.isfunding = isfunding;
	}

	public boolean isIsfundingsta() {
		return isfundingsta;
	}

	public void setIsfundingsta(boolean isfundingsta) {
		this.isfundingsta = isfundingsta;
	}

	public FundingDto(int seq, String email) {
		super();
		this.seq = seq;
		this.email = email;
	}
	
	

	public FundingDto(int seq, String email, boolean islike) {
		super();
		this.seq = seq;
		this.email = email;
		this.islike = islike;
	}

	public FundingDto(String email, String title, String intro, String content, int max_price, Date sdate, Date edate) {
		super();
		this.email = email;
		this.title = title;
		this.intro = intro;
		this.content = content;
		this.max_price = max_price;
		this.sdate = sdate;
		this.edate = edate;
	}

	public FundingDto(String email, String title, String intro, String content, String thumbnail, int max_price,
			Date sdate, Date edate) {
		super();
		this.email = email;
		this.title = title;
		this.intro = intro;
		this.content = content;
		this.thumbnail = thumbnail;
		this.max_price = max_price;
		this.sdate = sdate;
		this.edate = edate;
	}

	public FundingDto(int seq, String board_code, String email, String title, String intro, String content,
			String thumbnail, int max_price, int current_price, int personcount, int likecount, Date sdate, Date edate,
			Date regdate) {
		super();
		this.seq = seq;
		this.board_code = board_code;
		this.email = email;
		this.title = title;
		this.intro = intro;
		this.content = content;
		this.thumbnail = thumbnail;
		this.max_price = max_price;
		this.current_price = current_price;
		this.personcount = personcount;
		this.likecount = likecount;
		this.sdate = sdate;
		this.edate = edate;
		this.regdate = regdate;
	}
	
	

	public FundingDto(int seq, String email, String title, String intro, String content, String thumbnail,
			int max_price, Date sdate, Date edate) {
		super();
		this.seq = seq;
		this.email = email;
		this.title = title;
		this.intro = intro;
		this.content = content;
		this.thumbnail = thumbnail;
		this.max_price = max_price;
		this.sdate = sdate;
		this.edate = edate;
	}

	public FundingDto(int seq, String board_code, String email, String title, String intro, String content,
			String thumbnail, int max_price, int current_price, int personcount, int likecount, int readcount,
			Date sdate, Date edate, Date regdate, boolean islike, boolean isfunding) {
		super();
		this.seq = seq;
		this.board_code = board_code;
		this.email = email;
		this.title = title;
		this.intro = intro;
		this.content = content;
		this.thumbnail = thumbnail;
		this.max_price = max_price;
		this.current_price = current_price;
		this.personcount = personcount;
		this.likecount = likecount;
		this.readcount = readcount;
		this.sdate = sdate;
		this.edate = edate;
		this.regdate = regdate;
		this.islike = islike;
		this.isfunding = isfunding;
	}

	@Override
	public String toString() {
		return "FundingDto [seq=" + seq + ", board_code=" + board_code + ", email=" + email + ", title=" + title
				+ ", intro=" + intro + ", content=" + content + ", thumbnail=" + thumbnail + ", max_price=" + max_price
				+ ", current_price=" + current_price + ", personcount=" + personcount + ", likecount=" + likecount
				+ ", readcount=" + readcount + ", sdate=" + sdate + ", edate=" + edate + ", regdate=" + regdate
				+ ", islike=" + islike + ", isfunding=" + isfunding + "]";
	}

}
