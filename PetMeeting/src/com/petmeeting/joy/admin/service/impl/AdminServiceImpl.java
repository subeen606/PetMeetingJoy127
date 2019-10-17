package com.petmeeting.joy.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petmeeting.joy.admin.dao.AdminDao;
import com.petmeeting.joy.admin.model.BoardReportDto;
import com.petmeeting.joy.admin.model.FundMemberDto;
import com.petmeeting.joy.admin.service.AdminService;
import com.petmeeting.joy.funding.model.DayBean;
import com.petmeeting.joy.funding.model.FundingDto;
import com.petmeeting.joy.funding.model.FundingStaDto;
import com.petmeeting.joy.funding.model.fundingBean;
import com.petmeeting.joy.playboard.model.PlayboardDto;
import com.petmeeting.joy.playboard.model.PlayboardSearchBean;

import oracle.security.o5logon.a;

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

	public PlayboardDto getPlayboardDetail(int seq) {
		return adminDao.getPlayboardDetail(seq);

	}

	
	
	
	
	@Override
	public boolean addFunding(FundingDto dto , DayBean bean) {
		
		FundingDto fundDto = new FundingDto(bean.getEmail(),
											dto.getTitle(), 
											dto.getIntro(), 
											dto.getContent(),
											dto.getThumbnail(),
											dto.getMax_price(), 
											bean.getSDate(), 
											bean.getEDate()
											);
			
		return adminDao.addFunding(fundDto);
	}
	
	@Override
	public List<FundingDto> getFundingList(fundingBean fbean) {
		List<FundingDto> list = adminDao.getFundingList(fbean);
		List<FundingDto> flist = new ArrayList<FundingDto>();
		
		for(FundingDto fund : list) {
			int seq = fund.getSeq();
			
			int count = adminDao.fundingStacheck(seq);
			
			if(count == 1) {
				fund.setIsfundingsta(true);
			}else {
				fund.setIsfundingsta(false);
			}
			flist.add(fund);
		}
		return flist;
	}

	@Override
	public int getFundingCount(fundingBean fbean) {
		return adminDao.getFundingCount(fbean);
	}
	
	@Override
	public void deletefunding(int seq) {
		adminDao.fundingStaDel(seq);
		adminDao.fundingDelete(seq);
	}
	
	
	@Override
	public boolean fundUpdate(FundingDto dto, DayBean bean) {
		System.out.println("서비스들어오는 dto 와 빈: " +dto.toString()+","+bean.toString());
		FundingDto fundDto = new FundingDto(
											dto.getSeq(),
											bean.getEmail(),
											dto.getTitle(), 
											dto.getIntro(), 
											dto.getContent(),
											dto.getThumbnail(),
											dto.getMax_price(), 
											bean.getSDate(), 
											bean.getEDate()
											);

		 return adminDao.fundingUpdate(fundDto);
	}

	@Override
	public boolean addfundingSta(FundingStaDto sta) {
		return adminDao.addfundingSta(sta);
	}

	@Override
	public FundingDto fundingDetail(int seq) {
		return adminDao.fundingDetail(seq);
	}

	@Override
	public List<FundMemberDto> whofundingMem(int seq) {
		return adminDao.whofundingMem(seq);
	}
}
