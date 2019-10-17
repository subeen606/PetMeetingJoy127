package com.petmeeting.joy.admin.service;

import java.util.List;

import com.petmeeting.joy.admin.model.BoardReportDto;
import com.petmeeting.joy.admin.model.FundMemberDto;
import com.petmeeting.joy.funding.model.DayBean;
import com.petmeeting.joy.funding.model.FundingDto;
import com.petmeeting.joy.funding.model.FundingStaDto;
import com.petmeeting.joy.funding.model.fundingBean;
import com.petmeeting.joy.playboard.model.PlayboardDto;
import com.petmeeting.joy.playboard.model.PlayboardSearchBean;

public interface AdminService {
	public List<PlayboardDto> getAllPlayboardList(PlayboardSearchBean search);
	public int getPlayboardTotalRowCount(PlayboardSearchBean search);
	
	public void deletePlayboard(int seq);
	
	public List<BoardReportDto> getBoardReportReason(BoardReportDto reportDto);
	
	public void deleteBoardReport(BoardReportDto reportDto);
	public PlayboardDto getPlayboardDetail(int seq);
	
	
	
	public boolean addFunding(FundingDto dto, DayBean bean);
	public List<FundingDto> getFundingList(fundingBean fbean);
	public int getFundingCount(fundingBean fbean);
	public void deletefunding(int seq);
	public boolean fundUpdate(FundingDto dto, DayBean bean);
	public boolean addfundingSta(FundingStaDto sta);
	public FundingDto fundingDetail(int seq);
	public List<FundMemberDto> whofundingMem(int seq);

}
