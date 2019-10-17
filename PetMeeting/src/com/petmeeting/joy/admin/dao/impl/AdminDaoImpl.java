package com.petmeeting.joy.admin.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.petmeeting.joy.admin.dao.AdminDao;
import com.petmeeting.joy.admin.model.BoardReportDto;
import com.petmeeting.joy.admin.model.FundMemberDto;
import com.petmeeting.joy.funding.model.FundingDto;
import com.petmeeting.joy.funding.model.FundingStaDto;
import com.petmeeting.joy.funding.model.fundingBean;
import com.petmeeting.joy.playboard.model.PlayboardDto;
import com.petmeeting.joy.playboard.model.PlayboardSearchBean;

@Repository
public class AdminDaoImpl implements AdminDao {
	
	@Autowired
	SqlSessionTemplate sqlSession;
	
	String namespace = "Admin.";
	
	/*playboard*/
	@Override
	public List<PlayboardDto> getAllPlayboardList(PlayboardSearchBean search) {
		return sqlSession.selectList(namespace+"getPlayboardList", search);
	}

	@Override
	public int getPlayboardTotalRowCount(PlayboardSearchBean search) {
		return sqlSession.selectOne(namespace+"getTotalRowCount", search);
	}

	@Override
	public void deletePlayboard(int seq) {
		sqlSession.delete(namespace+"deletePlayboard", seq);
	}

	@Override
	public void deletePlayMem(int seq) {
		sqlSession.delete(namespace+"deletePlayMem", seq);
	}

	@Override
	public void deletePlayboardQnA(int seq) {
		sqlSession.delete(namespace+"deletePlayboardQnA", seq);
	}
	

	@Override
	public PlayboardDto getPlayboardDetail(int seq) {
		return sqlSession.selectOne(namespace+"getPlayboardDetail", seq);
	}


	@Override
	public List<BoardReportDto> getBoardReportReason(BoardReportDto reportDto) {
		return sqlSession.selectList(namespace+"getBoardReportReason", reportDto);
	}
		
	@Override
	public void deleteBoardReport(BoardReportDto reportDto) {
		sqlSession.delete(namespace+"deleteBoardReport", reportDto);
		
	}

	@Override
	public void minusReportCount(BoardReportDto reportDto) {
		sqlSession.update(namespace+"minusReportCount", reportDto);
	}
	
	
	
	/*funding*/
	@Override
	public boolean addFunding(FundingDto dto) {
		int n = sqlSession.insert(namespace + "addFunding", dto);
		
		return n>0? true:false;
	}
	
	@Override
	public int getFundingCount(fundingBean fbean) {
		return sqlSession.selectOne(namespace + "getFundingCount", fbean);
	}

	
	@Override
	public List<FundingDto> getFundingList(fundingBean fbean) {	
		return sqlSession.selectList(namespace + "getFundingList", fbean);
	}

	
	@Override
	public boolean fundingUpdate(FundingDto dto) {
		int n = sqlSession.update(namespace + "fundingUpdate", dto);
		return n>0?true:false;
	}

	@Override
	public boolean addfundingSta(FundingStaDto sta) {
		int n = sqlSession.insert(namespace + "addfundingSta", sta);
		return n>0?true:false;
	}
	
	@Override
	public int fundingStacheck(int seq) {
		return sqlSession.selectOne(namespace + "fundingStacheck", seq);
	}

	@Override
	public void fundingDelete(int seq) {		
		sqlSession.delete(namespace + "fundingDelete" , seq);
	}

	@Override
	public void fundingStaDel(int seq) {
		sqlSession.delete(namespace + "fundingStaDel", seq);
	}

	@Override
	public FundingDto fundingDetail(int seq) {
		return sqlSession.selectOne(namespace + "adminfundingDetail", seq);
	}

	@Override
	public List<FundMemberDto> whofundingMem(int seq) {
		return sqlSession.selectList(namespace + "whofundingMem", seq);
	}

}
