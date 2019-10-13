package com.petmeeting.joy.admin.dao;

import java.util.List;

import com.petmeeting.joy.admin.model.BoardReportDto;
import com.petmeeting.joy.funding.model.FundingDto;
import com.petmeeting.joy.funding.model.fundingBean;
import com.petmeeting.joy.playboard.model.PlayboardDto;
import com.petmeeting.joy.playboard.model.PlayboardSearchBean;

public interface AdminDao {
	/*playboard*/
	public List<PlayboardDto> getAllPlayboardList(PlayboardSearchBean search);
	public int getPlayboardTotalRowCount(PlayboardSearchBean search);
	
	public void deletePlayboard(int seq);
	public void deletePlayMem(int seq);
	public void deletePlayboardQnA(int seq);
	
	public PlayboardDto getPlayboardDetail(int seq);
	
	public List<BoardReportDto> getBoardReportReason(BoardReportDto reportDto);
	
	public void deleteBoardReport(BoardReportDto reportDto);
	public void minusReportCount(BoardReportDto reportDto);
	
	
	
	
	
	/*funding*/
	public List<FundingDto> getFundingList(fundingBean fbean);
	public int getFundingCount(fundingBean fbean);




}
