package com.petmeeting.joy.funding.dao;

import java.util.List;

import com.petmeeting.joy.funding.model.FundingDto;
import com.petmeeting.joy.funding.model.FundingParam;
import com.petmeeting.joy.funding.model.FundingStaDto;
import com.petmeeting.joy.funding.model.FundinglikeBean;
import com.petmeeting.joy.funding.model.FundingmemDto;
import com.petmeeting.joy.login.model.MemberDto;

public interface FundingDao {

	public boolean addFunding(FundingDto dto);
	public List<FundingDto> fundingList(FundingParam param);
	public FundingDto fundingDetail(int seq);
	public MemberDto fundingMem(String email);
	public void readcount(int seq);
	/*수정*/
	public boolean fundingUpdate(FundingDto dto);
	/*삭제*/
	public boolean fundingDelete(FundingDto dto);
	
	/* 좋아요 체크 */
	public int isFundlike(FundinglikeBean bean);

	/* 좋아요 */
	public boolean Fundlike(FundingDto dto);
	public void likeCountUp(int seq);
	
	/* 좋아요취소 */
	public boolean FundNO(FundingDto dto);
	public void likeCounDown(int seq);
	
	/*funding한 사람관리*/
	public boolean addFundMem(FundingmemDto mem);
	public void personCount(int board_seq);
	public int isfunding(FundingmemDto mem);
	public int sumfund(int board_seq);
	
	public void cPrice(FundingmemDto mem);
	
	/*내역서*/
	public boolean addfundingSta(FundingStaDto sta);
	public FundingStaDto fundingStaDetail(int seq);
	public int fundingStacheck(int seq);
}
