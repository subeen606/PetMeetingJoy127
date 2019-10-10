package com.petmeeting.joy.funding.model;

import java.io.Serializable;

/*
 DROP TABLE PM_FUNDINGMEM
CASCADE CONSTRAINTS;

DROP SEQUENCE PM_FUNDINGMEM_SEQ;

CREATE TABLE PM_FUNDINGMEM(
	SEQ NUMBER PRIMARY KEY,
	FUNDING_SEQ NUMBER,
	EMAIL VARCHAR2(50),
	DONATION NUMBER
);

CREATE SEQUENCE PM_FUNDINGMEM_SEQ
START WITH 1
INCREMENT BY 1;

ALTER TABLE PM_FUNDINGMEM
ADD CONSTRAINT PM_FUNDINGMEM_FK1 FOREIGN KEY(EMAIL)
REFERENCES PM_MEMBER(EMAIL);

ALTER TABLE PM_FUNDINGMEM
ADD CONSTRAINT PM_FUNDINGMEM_FK2 FOREIGN KEY(FUNDING_SEQ)
REFERENCES PM_FUNDINGBOARD(SEQ);

 */

public class FundingmemDto implements Serializable {

	private int seq;
	private int funding_seq;
	private String email;
	private int donation;
	
	public FundingmemDto() {}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getFunding_seq() {
		return funding_seq;
	}

	public void setFunding_seq(int funding_seq) {
		this.funding_seq = funding_seq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getDonation() {
		return donation;
	}

	public void setDonation(int donation) {
		this.donation = donation;
	}

	public FundingmemDto(int seq, int funding_seq, String email, int donation) {
		super();
		this.seq = seq;
		this.funding_seq = funding_seq;
		this.email = email;
		this.donation = donation;
	}

	public FundingmemDto(int funding_seq, String email, int donation) {
		super();
		this.funding_seq = funding_seq;
		this.email = email;
		this.donation = donation;
	}

	public FundingmemDto(int funding_seq, String email) {
		super();
		this.funding_seq = funding_seq;
		this.email = email;
	}

	@Override
	public String toString() {
		return "FundingmemDto [seq=" + seq + ", funding_seq=" + funding_seq + ", email=" + email + ", donation="
				+ donation + "]";
	}
	
	
}
