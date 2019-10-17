package com.petmeeting.joy.admin.dao;

import java.util.List;

import com.petmeeting.joy.admin.model.BoardReportDto;
import com.petmeeting.joy.admin.model.FundMemberDto;
import com.petmeeting.joy.funding.model.FundingDto;
import com.petmeeting.joy.funding.model.FundingStaDto;
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
<<<<<<< HEAD
=======
	
>>>>>>> 6eac61176ac647a6359fee8f70b0f5df7a799ba9
	public List<BoardReportDto> getBoardReportReason(BoardReportDto reportDto);
	
	public void deleteBoardReport(BoardReportDto reportDto);
	public void minusReportCount(BoardReportDto reportDto);

<<<<<<< HEAD
=======

>>>>>>> 6eac61176ac647a6359fee8f70b0f5df7a799ba9
	
	
	

	/*funding*/
	public boolean addFunding(FundingDto dto);
	public List<FundingDto> getFundingList(fundingBean fbean);
	public int getFundingCount(fundingBean fbean);

	public FundingDto fundingDetail(int seq);
	public boolean addfundingSta(FundingStaDto sta);
	
	public boolean fundingUpdate(FundingDto dto);
	public void fundingDelete(int seq);
	public void fundingStaDel(int seq);
	public int fundingStacheck(int seq);
	
	public List<FundMemberDto> whofundingMem(int seq);
<<<<<<< HEAD
=======
	
>>>>>>> 6eac61176ac647a6359fee8f70b0f5df7a799ba9



}
