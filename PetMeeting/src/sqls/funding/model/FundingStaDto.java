package com.petmeeting.joy.funding.model;

import java.io.Serializable;

/*
DROP TABLE PM_FUNDING_STA
CASCADE CONSTRAINTS;

DROP SEQUENCE PM_FUNDING_STA_SEQ;

CREATE TABLE PM_FUNDING_STA(
	SEQ NUMBER PRIMARY KEY,
	EMAIL VARCHAR2(50),
	CONTENT CLOB
);

CREATE SEQUENCE PM_FUNDING_STA_SEQ
START WITH 1
INCREMENT BY 1;

ALTER TABLE  PM_FUNDING_STA
ADD CONSTRAINT  PM_FUNDING_STA_FK FOREIGN KEY(SEQ)
REFERENCES PM_FUNDINGBOARD(SEQ);
 */


public class FundingStaDto implements Serializable{

	private int seq;
	private String email;
	private String content;
	
	public FundingStaDto() {}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public FundingStaDto(int seq, String email, String content) {
		super();
		this.seq = seq;
		this.email = email;
		this.content = content;
	}

	@Override
	public String toString() {
		return "FundingStaDto [seq=" + seq + ", email=" + email + ", content=" + content + "]";
	}
	
	
	
}
