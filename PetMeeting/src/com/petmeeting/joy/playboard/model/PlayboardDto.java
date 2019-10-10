package com.petmeeting.joy.playboard.model;

import java.io.Serializable;
import java.util.Date;

public class PlayboardDto implements Serializable{
	
	
   private int seq;
   private String board_code;
   
   private String email;
   private String nickname;
   
   private String title;
   private String content;
   private String filename;
   
   private String location;
   private String location_detail;
   private String category;
   private int price;
   
   private int people;
   private int personcount;
   private int readcount;
   private int likecount;
   private int reportcount;
   private Date pdate;
   private Date edate;
   private Date regdate;
   
   private int del;
   
   private boolean deadlineCheck;		// 모집기한 지났으면 true 지나지 않았으면 false
   private boolean fullCheck;			// 모집인원이 다 찼으면 true 차지 않았으면 false
   
   private PlayboardHashTagDto hashs;
   
   public PlayboardDto() {

   }


public PlayboardDto(int seq, String board_code, String email, String nickname, String title, String content,
		String filename, String location, String location_detail, String category, int price, int people,
		int personcount, int readcount, int likecount, int reportcount, Date pdate, Date edate, Date regdate, int del,
		boolean deadlineCheck, boolean fullCheck) {
	super();
	this.seq = seq;
	this.board_code = board_code;
	this.email = email;
	this.nickname = nickname;
	this.title = title;
	this.content = content;
	this.filename = filename;
	this.location = location;
	this.location_detail = location_detail;
	this.category = category;
	this.price = price;
	this.people = people;
	this.personcount = personcount;
	this.readcount = readcount;
	this.likecount = likecount;
	this.reportcount = reportcount;
	this.pdate = pdate;
	this.edate = edate;
	this.regdate = regdate;
	this.del = del;
	this.deadlineCheck = deadlineCheck;
	this.fullCheck = fullCheck;
}


public PlayboardDto(int seq, String email) {
	super();
	this.seq = seq;
	this.email = email;
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


public String getTitle() {
	return title;
}


public void setTitle(String title) {
	this.title = title;
}


public String getContent() {
	return content;
}


public void setContent(String content) {
	this.content = content;
}


public String getFilename() {
	return filename;
}


public void setFilename(String filename) {
	this.filename = filename;
}


public String getLocation() {
	return location;
}


public void setLocation(String location) {
	this.location = location;
}


public String getLocation_detail() {
	return location_detail;
}


public void setLocation_detail(String location_detail) {
	this.location_detail = location_detail;
}


public String getCategory() {
	return category;
}


public void setCategory(String category) {
	this.category = category;
}


public int getPrice() {
	return price;
}


public void setPrice(int price) {
	this.price = price;
}


public int getPeople() {
	return people;
}


public void setPeople(int people) {
	this.people = people;
}


public int getPersoncount() {
	return personcount;
}


public void setPersoncount(int personcount) {
	this.personcount = personcount;
}


public int getReadcount() {
	return readcount;
}


public void setReadcount(int readcount) {
	this.readcount = readcount;
}


public int getLikecount() {
	return likecount;
}


public void setLikecount(int likecount) {
	this.likecount = likecount;
}


public int getReportcount() {
	return reportcount;
}


public void setReportcount(int reportcount) {
	this.reportcount = reportcount;
}


public Date getPdate() {
	return pdate;
}


public void setPdate(Date pdate) {
	this.pdate = pdate;
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


public int getDel() {
	return del;
}


public void setDel(int del) {
	this.del = del;
}


public boolean isDeadlineCheck() {
	return deadlineCheck;
}


public void setDeadlineCheck(boolean deadlineCheck) {
	this.deadlineCheck = deadlineCheck;
}


public boolean isFullCheck() {
	return fullCheck;
}


public void setFullCheck(boolean fullCheck) {
	this.fullCheck = fullCheck;
}


public PlayboardHashTagDto getHashs() {
	return hashs;
}


public void setHashs(PlayboardHashTagDto hashs) {
	this.hashs = hashs;
}


@Override
public String toString() {
	return "PlayboardDto [seq=" + seq + ", board_code=" + board_code + ", email=" + email + ", nickname=" + nickname
			+ ", title=" + title + ", content=" + content + ", filename=" + filename + ", location=" + location
			+ ", location_detail=" + location_detail + ", category=" + category + ", price=" + price + ", people="
			+ people + ", personcount=" + personcount + ", readcount=" + readcount + ", likecount=" + likecount
			+ ", reportcount=" + reportcount + ", pdate=" + pdate + ", edate=" + edate + ", regdate=" + regdate
			+ ", del=" + del + ", deadlineCheck=" + deadlineCheck + ", fullCheck=" + fullCheck + ", hashs=" + hashs
			+ "]";
}




   
}
