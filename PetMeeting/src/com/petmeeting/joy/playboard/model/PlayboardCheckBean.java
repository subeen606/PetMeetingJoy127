package com.petmeeting.joy.playboard.model;

public class PlayboardCheckBean {
	
	private boolean likeCheck;			// 좋아요 누른 글이면 true 누르지 않았으면 false
	private boolean partCheck;			// 참여하는 모임이면 true 아니면 false
	private boolean reportCheck;		
	
	public PlayboardCheckBean() {
	}

	public PlayboardCheckBean(boolean likeCheck, boolean partCheck, boolean reportCheck) {
		super();
		this.likeCheck = likeCheck;
		this.partCheck = partCheck;
		this.reportCheck = reportCheck;
	}

	public boolean isLikeCheck() {
		return likeCheck;
	}

	public void setLikeCheck(boolean likeCheck) {
		this.likeCheck = likeCheck;
	}

	public boolean isPartCheck() {
		return partCheck;
	}

	public void setPartCheck(boolean partCheck) {
		this.partCheck = partCheck;
	}

	public boolean isReportCheck() {
		return reportCheck;
	}

	public void setReportCheck(boolean reportCheck) {
		this.reportCheck = reportCheck;
	}

	@Override
	public String toString() {
		return "PlayboardCheckBean [likeCheck=" + likeCheck + ", partCheck=" + partCheck + ", reportCheck="
				+ reportCheck + "]";
	}
	
}
