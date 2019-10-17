package com.petmeeting.joy.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petmeeting.joy.admin.dao.AdminDao;
import com.petmeeting.joy.admin.model.BoardReportDto;
import com.petmeeting.joy.admin.service.AdminService;
import com.petmeeting.joy.funding.model.FundingDto;
import com.petmeeting.joy.funding.model.fundingBean;
import com.petmeeting.joy.playboard.Util.DateUtil;
import com.petmeeting.joy.playboard.model.PlayboardDto;
import com.petmeeting.joy.playboard.model.PlayboardSearchBean;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	AdminDao adminDao;
	
	@Override
	public List<PlayboardDto> getAllPlayboardList(PlayboardSearchBean search) {
		List<PlayboardDto> all =  adminDao.getAllPlayboardList(search);
		List<PlayboardDto> checkList = new ArrayList<PlayboardDto>();
		
		for (PlayboardDto dto : all) {
			if(DateUtil.isEnd(dto.getEdate()) == true) {	// 마감
				dto.setDeadlineCheck(true);
			}else if(DateUtil.isEnd(dto.getEdate()) == false) {
				dto.setDeadlineCheck(false);
			}
			
			if(dto.getPeople() == dto.getPersoncount()) {	// 모집인원이 다 찬경우
				dto.setFullCheck(true);
			}else {
				dto.setFullCheck(false);
			}
			
			if(DateUtil.isEnd(dto.getPdate()) == true) {	// 모임예정일 지남
				dto.setPdateCheck(true);
			}else {
				dto.setPdateCheck(false);
			}
			
			checkList.add(dto);
		}
		return checkList;
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
	
	@Override
	public PlayboardDto getPlayboardDetail(int seq) {
		PlayboardDto detail = adminDao.getPlayboardDetail(seq);
		if(DateUtil.isEnd(detail.getEdate()) == true) {	// 마감
			detail.setDeadlineCheck(true);
		}else if(DateUtil.isEnd(detail.getEdate()) == false) {
			detail.setDeadlineCheck(false);
		}
		
		if(detail.getPeople() == detail.getPersoncount()) {	// 모집인원이 다 찬경우
			detail.setFullCheck(true);
		}else {
			detail.setFullCheck(false);
		}			
		return detail;
	}

}
