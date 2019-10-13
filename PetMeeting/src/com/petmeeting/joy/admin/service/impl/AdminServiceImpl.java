package com.petmeeting.joy.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petmeeting.joy.admin.dao.AdminDao;
import com.petmeeting.joy.admin.model.BoardReportDto;
import com.petmeeting.joy.admin.service.AdminService;
import com.petmeeting.joy.funding.model.FundingDto;
import com.petmeeting.joy.funding.model.fundingBean;
import com.petmeeting.joy.playboard.model.PlayboardDto;
import com.petmeeting.joy.playboard.model.PlayboardSearchBean;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	AdminDao adminDao;
	
	@Override
	public List<PlayboardDto> getAllPlayboardList(PlayboardSearchBean search) {
		return adminDao.getAllPlayboardList(search);
	}

	@Override
	public int getPlayboardTotalRowCount(PlayboardSearchBean search) {
		return adminDao.getPlayboardTotalRowCount(search);
	}

	@Override
	public void deletePlayboard(int seq) {
		adminDao.deletePlayboard(seq);
		adminDao.deletePlayMem(seq);
		adminDao.deletePlayboardQnA(seq);
	}

	@Override
	public List<BoardReportDto> getBoardReportReason(BoardReportDto reportDto) {
		return adminDao.getBoardReportReason(reportDto);
	}

	@Override
	public void deleteBoardReport(BoardReportDto reportDto) {
		adminDao.deleteBoardReport(reportDto);
		adminDao.minusReportCount(reportDto);
	}

	@Override
	public List<FundingDto> getFundingList(fundingBean fbean) {
		return adminDao.getFundingList(fbean);
	}

	@Override
	public int getFundingCount(fundingBean fbean) {
		return adminDao.getFundingCount(fbean);
	}
	
<<<<<<< HEAD
=======

	@Override
>>>>>>> 4e5675b2cdc8c87dd4c27907aa466a1a244516af
	public PlayboardDto getPlayboardDetail(int seq) {
		return adminDao.getPlayboardDetail(seq);

	}

}
