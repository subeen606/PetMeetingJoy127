package com.petmeeting.joy.funding.service;

import java.util.List;

import com.petmeeting.joy.funding.model.DayBean;
import com.petmeeting.joy.funding.model.FundingDto;
import com.petmeeting.joy.funding.model.FundingParam;
import com.petmeeting.joy.funding.model.FundingStaDto;
import com.petmeeting.joy.funding.model.FundinglikeBean;
import com.petmeeting.joy.funding.model.FundingmemDto;
import com.petmeeting.joy.login.model.MemberDto;

public interface FundingService {
	
	public boolean addFunding(FundingDto dto, DayBean bean);

	public List<FundingDto> fundingList(String email,FundingParam param);
	
	public FundingDto fundingDetail(int seq, FundinglikeBean bean);
	public MemberDto fundingMem(String email);
	
	public int isFundlike(FundinglikeBean bean);
	public boolean Fundlike(FundingDto dto);
	public boolean FundNO(FundingDto dto);
	public void likeCountUp(int seq);
	public void likeCountDown(int seq);
	
	
	public boolean addFundMem(FundingmemDto mem);
	public void personCount(int board_seq);
	
	public FundingDto fundingUpdate(int seq);
	
	public boolean fundUpdate(FundingDto dto, DayBean bean);
	public boolean fundingDelete(FundingDto dto);
	
	public boolean addfundingSta(FundingStaDto sta);
	public FundingStaDto fundingStaDetail(int seq);

}
